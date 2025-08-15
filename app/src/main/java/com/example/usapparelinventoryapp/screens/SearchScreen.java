package com.example.usapparelinventoryapp.screens;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.usapparelinventoryapp.customAdapters.CustomAdapterSearch;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.dto.StyleSearchDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SearchScreen extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final String TAG = "PERMISSION_TAG";
    Button btn_create_search, btn_search, btn_update_sheet;
    AutoCompleteTextView searchAutoCompleteTextView ;
    ListView lv_searchLista;
    private ArrayList<String> allsearch;
    DataBaseHelper databaseHelper;
    ToggleButton toggleSearchType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);


        searchAutoCompleteTextView = findViewById(R.id.searchAutoCompleteTextView);
        btn_create_search = findViewById(R.id.btn_create_search);
        btn_search = findViewById(R.id.btn_search);
        btn_update_sheet = findViewById(R.id.btn_update_sheet);
        toggleSearchType = findViewById(R.id.toggle_search_type);
        lv_searchLista = findViewById(R.id.lv_searchLista);
        lv_searchLista.setOnItemClickListener((parent, view, position, id) -> {
            StyleSearchDTO selectedItem = (StyleSearchDTO) parent.getItemAtPosition(position);
            String location = selectedItem.getPallet_location();

            Intent i = new Intent(SearchScreen.this, PalletViewScreen.class);
            i.putExtra("location", location);
            startActivity(i);
        });
        databaseHelper = new DataBaseHelper( SearchScreen.this);
        allsearch = databaseHelper.getAllSizesA();


        btn_search.setOnClickListener(v -> {
            if(checkPermission()) {
               DoItNow();


        }else{
                // If permission is not granted we will request for the Permission
                requestPermission();
            }});

        btn_update_sheet.setOnClickListener(v -> {
            if (checkPermission()) {
                sendToGoogleSheet();
            } else {
                requestPermission();
            }
        });


        btn_create_search.setOnClickListener(v -> {

            String searchTerm = searchAutoCompleteTextView.getText().toString();
            Log.d("SearchDebug", "Search term: " + searchTerm);
            DataBaseHelper dataBaseHelper = new DataBaseHelper(SearchScreen.this);
            // Log the search term for debugging

            if (toggleSearchType.isChecked()) {
                // Search by location
                ShowSearchOnListViewByLocation(dataBaseHelper, searchTerm);
            } else {
                // Search by style
                // Log the search term for debugging
                Log.d("SearchDebug", "Search term 2: " + searchTerm);
                ShowSearchOnListView(dataBaseHelper, searchTerm);
            }


        });

    }

    private void DoItNow() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(SearchScreen.this);
        String searchTerm = "";

        List<String> items = dataBaseHelper.getStylesSearchcsv(searchTerm);
        StringBuilder data = new StringBuilder();
        data.append("Style,Color,Size,Quantity,Location,ASN,P.O");

        for (String item : items) {
            item = item.replace("\n", "").replace("@", "").trim();

            if (item.isEmpty()) continue;

            String[] fields = item.split("#");
            if (fields.length < 7) continue;

            data.append("\n")
                    .append(fields[0].trim()).append(",")
                    .append(fields[1].trim()).append(",")
                    .append(fields[2].trim()).append(",")
                    .append(fields[3].trim()).append(",")
                    .append(fields[4].trim()).append(",")
                    .append(fields[5].trim()).append(",")
                    .append(fields[6].trim());
        }

//        final String[] each_item = Arrays.toString(each_item1).split("#");
//
//        data.append("\n"+ Arrays.toString(each_item));
//
//        int last = data.length() - 3;
//        data.replace(last, last + 1, "");
//        data.replace(last, last + 2, "");
//        data.replace(last, last + 3, "");
//        int start = data.length();
//        data.replace(35, 39, "");
//
        CreateCSV(data);
    }


    private void CreateCSV(StringBuilder data) {
        // Get today's date
        String fileName = "SearchData_" + new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date()) + ".csv";

        File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "InventoryExports");
        if (!exportDir.exists()) {
            exportDir.mkdirs(); // create directory if it doesn't exist
        }

        // Delete old files in the folder
        File[] oldFiles = exportDir.listFiles();
        if (oldFiles != null) {
            for (File file : oldFiles) {
                if (file.getName().endsWith(".csv")) {
                    file.delete();
                }
            }
        }

        // Target CSV file path
        File csvFile = new File(exportDir, fileName);

        try {
            FileOutputStream out = new FileOutputStream(csvFile);
            out.write(data.toString().getBytes());
            out.close();

            // Share via intent
            Uri uri = FileProvider.getUriForFile(getApplicationContext(), "com.example.usapparelinventoryapp", csvFile);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/csv");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Pallet Search Data");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, "Export CSV Data"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "CSV Export Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){

            try {
                Log.d(TAG, "requestPermission: try");

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                storageActivityResultLauncher.launch(intent);
            }
            catch (Exception e){
                Log.e(TAG, "requestPermaission: catch", e);
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                storageActivityResultLauncher.launch(intent);

            }
        }
        else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION_CODE
            );
        }
    }

    private ActivityResultLauncher<Intent> storageActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, "onActivityResult: ");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                        if (Environment.isExternalStorageManager()){
                            Log.d(TAG, "onActivityResult: Manage External Storage Permission is granted");
                            DoItNow();
                        }
                        else{
                            Log.d(TAG, "onActivityResult: Manage External Storage Permission is denied");
                            Toast.makeText(SearchScreen.this, "Manage External Storgae Permaission is denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {

                    }
                }
            }
    );

    public  boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            return Environment.isExternalStorageManager();
        }
        else{
            int write = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

            return write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length > 0){
                boolean write = grantResults[0]  == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (write && read){
                    Log.d(TAG, "onRequestPermissionResult: External Storage permissions granted");
                    DoItNow();
                }
                else{
                    Log.d(TAG, "onRequestPermissionResult: External Storage permissions denied");
                    Toast.makeText(this, "External Storage permissions denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void ShowSearchOnListView(DataBaseHelper dataBaseHelper, String searchTerm) {

        // Log the search term for debugging
        Log.d("SearchDebug", "Search term6: " + searchTerm);

        CustomAdapterSearch customAdapterSearch = new CustomAdapterSearch(getApplicationContext(), (ArrayList<StyleSearchDTO>) dataBaseHelper.getStylesSearch(searchTerm));
        Log.d("SearchDebug", "Search list: " + customAdapterSearch);
        lv_searchLista.setAdapter(customAdapterSearch);
    }

    private void ShowSearchOnListViewByLocation(DataBaseHelper dataBaseHelper, String locationTerm) {
        ArrayList<StyleSearchDTO> list = new ArrayList<>();
        for (StyleSearchDTO dto : dataBaseHelper.getStylesSearch("")) {
            String location = dto.getPallet_location();
            if (location != null && location.toLowerCase().contains(locationTerm.toLowerCase())) {
                list.add(dto);
            }
        }
        CustomAdapterSearch customAdapterSearch = new CustomAdapterSearch(getApplicationContext(), list);

        lv_searchLista.setAdapter(customAdapterSearch);
    }

    private void updateGoogleSheet(List<String> csvRows) {
        OkHttpClient client = new OkHttpClient();

        JsonObject requestBody = new JsonObject();
        JsonArray values = new JsonArray();

        for (String row : csvRows) {
            JsonArray rowArray = new JsonArray();
            for (String field : row.split(",")) {
                rowArray.add(field.trim());
            }
            values.add(rowArray);
        }

        requestBody.add("values", values);

        // ✅ Log the data being sent
        Log.d("GoogleSheetPOST", "Payload: " + requestBody.toString());

        Request request = new Request.Builder()
                .url("https://script.google.com/macros/s/AKfycbwDG0cVHXXg2oPoZVc_ACtsiqwKtJYpBno8rNFGjh2TU7ajkH0Kjxs3SpdVoJqooEep/exec")
                .post(RequestBody.create(
                        MediaType.parse("application/json"),
                        requestBody.toString()
                ))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(SearchScreen.this, "Failed to update Google Sheet", Toast.LENGTH_SHORT).show()
                );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("GoogleSheetPOST", "Response: " + response.body().string());  // ✅ Log the response too
                runOnUiThread(() ->
                        Toast.makeText(SearchScreen.this, "Google Sheet Updated Successfully", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    private void sendToGoogleSheet() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(SearchScreen.this);
        List<String> items = dataBaseHelper.getStylesSearchcsv("");

        List<String> csvRows = new ArrayList<>();
        csvRows.add("Style,Color,Size,Quantity,Location,ASN,P.O");

        for (String item : items) {
            item = item.replace("\n", "").replace("@", "").trim();
            if (item.isEmpty()) continue;

            String[] fields = item.split("#");
            if (fields.length < 7) continue;

            csvRows.add(
                    fields[0].trim() + "," +
                            fields[1].trim() + "," +
                            fields[2].trim() + "," +
                            fields[3].trim() + "," +
                            fields[4].trim() + "," +
                            fields[5].trim() + "," +
                            fields[6].trim()
            );
        }

        updateGoogleSheet(csvRows); // Call the function you defined earlier
    }
}