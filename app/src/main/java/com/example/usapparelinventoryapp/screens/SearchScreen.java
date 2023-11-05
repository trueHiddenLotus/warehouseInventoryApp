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

import com.example.usapparelinventoryapp.customAdapters.CustomAdapterSearch;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.dto.StyleSearchDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchScreen extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final String TAG = "PERMISSION_TAG";
    Button btn_create_search, btn_search;
    AutoCompleteTextView searchAutoCompleteTextView ;
    ListView lv_searchLista;
    private ArrayList<String> allsearch;
    DataBaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        searchAutoCompleteTextView = findViewById(R.id.searchAutoCompleteTextView);
        btn_create_search = findViewById(R.id.btn_create_search);
        btn_search = findViewById(R.id.btn_search);
        lv_searchLista = findViewById(R.id.lv_searchLista);
        databaseHelper = new DataBaseHelper( SearchScreen.this);
        allsearch = databaseHelper.getAllSizesA();


        btn_search.setOnClickListener(v -> {
            if(checkPermission()) {
               DoItNow();


        }else{
                // If permission is not granted we will request for the Permission
                requestPermission();
            }});
        btn_create_search.setOnClickListener(v -> {

            String searchTerm = searchAutoCompleteTextView.getText().toString();

            DataBaseHelper dataBaseHelper = new DataBaseHelper(SearchScreen.this);

            dataBaseHelper.getStylesSearch(searchTerm);

            ShowSearchOnListView(dataBaseHelper, searchTerm);


        });

    }

    private void DoItNow() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(SearchScreen.this);

        String searchTerm = "";
        String[] each_item1 = dataBaseHelper.getStylesSearchcsv(searchTerm).toString().split("@");

        StringBuilder data = new StringBuilder();

        data.append("Style,Color,Size,Quantity,Location");

        final String[] each_item = Arrays.toString(each_item1).split("#");

        data.append("\n"+ Arrays.toString(each_item));

        int last = data.length() - 3;
        data.replace(last, last + 1, "");
        data.replace(last, last + 2, "");
        data.replace(last, last + 3, "");
        int start = data.length();
        data.replace(35, 39, "");

        CreateCSV(data);
    }


    private void CreateCSV(StringBuilder data) {
        Calendar calendar = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance();
        }
        long time= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            time = calendar.getTimeInMillis();
        }

        try {
            //
            FileOutputStream out = openFileOutput("CSV_Data_"+time+".csv", Context.MODE_PRIVATE);

            //store the data in CSV file by passing String Builder data
            out.write(data.toString().getBytes());
            out.close();

            Context context = getApplicationContext();
            final File newFile = new File(Environment.getExternalStorageDirectory(),"SimpleCVS");
            if(!newFile.exists())
            {
                newFile.mkdir();
            }

            File file = new File(context.getFilesDir(),"CSV_Data_"+time+".csv");

            Uri path = FileProvider.getUriForFile(context,"com.example.usapparelinventoryapp",file);

            //once the file is ready a share option will pop up using which you can share
            // the same CSV from via Gmail or store in Google Drive
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/csv");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            intent.putExtra(Intent.EXTRA_STREAM, path);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent,"Excel Data"));

        } catch (Exception e) {
            e.printStackTrace();
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

        CustomAdapterSearch customAdapterSearch = new CustomAdapterSearch(getApplicationContext(), (ArrayList<StyleSearchDTO>) dataBaseHelper.getStylesSearch(searchTerm));
        lv_searchLista.setAdapter(customAdapterSearch);
    }

}