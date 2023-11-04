package com.example.usapparelinventoryapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.res.TypedArrayUtils;

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

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity8 extends AppCompatActivity {

//    private static final int PERMISSION_REQUEST_CODE = 100;

    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final String TAG = "PERMISSION_TAG";
    Button btn_create_search, btn_search;
    AutoCompleteTextView searchAutoCompleteTextView ;
    ListView lv_searchLista;
    private ArrayList<String> allsearch;
    //
    ArrayAdapter searchArrayAdapter;
    ArrayAdapter searchArrayAdaptera;
    //
    DAO databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        searchAutoCompleteTextView = findViewById(R.id.searchAutoCompleteTextView);
        btn_create_search = findViewById(R.id.btn_create_search);
                btn_search = findViewById(R.id.btn_search);
        lv_searchLista = findViewById(R.id.lv_searchLista);
        databaseHelper = new DAO( MainActivity8.this);
        allsearch = databaseHelper.getAllSizesA();
//        ShowSearchOnListView(databaseHelper, String searchTerm);
//        ShowSizesAuto(databaseHelper);




        btn_search.setOnClickListener(v -> {
            if(checkPermission()) {
               DoItNow();


        }else{
                // If permission is not granted we will request for the Permission
                requestPermission();
            }});
        btn_create_search.setOnClickListener(v -> {

            String searchTerm = searchAutoCompleteTextView.getText().toString();


            DAO dataBaseHelper = new DAO(MainActivity8.this);

            dataBaseHelper.getStylesSearch(searchTerm);
//            databaseHelper.getStylesSearch(searchTerm).add("Style Code", "style_color", "style_size", 0 , "pallet_location");

//            searchArrayAdapter = new ArrayAdapter<StyleSearchDTO>(MainActivity8.this, android.R.layout.simple_list_item_1, dataBaseHelper.getStylesSearch(searchTerm));
//            lv_searchLista.setAdapter(searchArrayAdapter);

            ShowSearchOnListView(dataBaseHelper, searchTerm);


        });

    }

    private void DoItNow() {
        DAO dataBaseHelper = new DAO(MainActivity8.this);

        String searchTerm = "";
        String[] each_item1 = dataBaseHelper.getStylesSearchcsv(searchTerm).toString().split("@");

        StringBuilder data = new StringBuilder();

        data.append("Style,Color,Size,Quantity,Location");

        final String[] each_item = Arrays.toString(each_item1).split("#");

//                for(int i = 0; i < dataBaseHelper.getStylesSearchcsv(searchTerm).toArray().length; i++){
//                    final String[] each_item = Arrays.toString(each_item1).split("#");
//                    final String each_item = dataBaseHelper.getStylesSearchcsv(searchTerm).toString();



//                    System.out.println("Splited # ID: "+ each_item[0]);
//                    System.out.println("Splited # Firstname? : "+ each_item[1]);
//                    System.out.println("Splited # Lastname? : "+ each_item[2]);
//                    System.out.println("Splited # Phone ? : "+ each_item[3]);

//                     then add each user data in data string builder
//                    data.append("\n "+ Arrays.toString(each_item));

//                }
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            Uri path = FileProvider.getUriForFile(this,getApplicationContext().getPackageName() +  ".FileProvider", new File(filePath + fileName));
//
//        } else{
//            Uri path =Uri.fromFile(new File(filePath + fileName))
//        }

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

//            getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            Uri path = FileProvider.getUriForFile(this,getApplicationContext().getPackageName() +  ".Environment.DIRECTORY_DOCUMENTS", file);

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

//     checking permission To WRITE
//    private boolean checkPermission() {
//        int result = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (result == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    // request permission for WRITE Access
//    private void requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity8.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            Toast.makeText(MainActivity8.this, "Write External Storage permission allows us to save files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(MainActivity8.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//        }
//    }
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
                            Toast.makeText(MainActivity8.this, "Manage External Storgae Permaission is denied", Toast.LENGTH_SHORT).show();
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


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("value", "Permission Granted, Now you can use local drive .");
//                } else {
//                    Log.e("value", "Permission Denied, You cannot use local drive .");
//                }
//                break;
//        }
//    }

    private void ShowSearchOnListView(DAO dataBaseHelper, String searchTerm) {
//        ArrayList  ListViewConverter = new ArrayList<>();
//        for (int i = 0; i< databaseHelper.getStylesSearch(searchTerm).size(); i++){
//            List<String> styleCode = Collections.singletonList(databaseHelper.getStylesSearch(searchTerm).get(i).getStyle_code().toString());
//            List<String> styleColor = Collections.singletonList(databaseHelper.getStylesSearch(searchTerm).get(i).getStyle_color().toString());
//            List<String> styleSize = Collections.singletonList(databaseHelper.getStylesSearch(searchTerm).get(i).getStyle_size().toString());
//            List<String> quantity = Collections.singletonList(String.valueOf(Collections.singletonList(databaseHelper.getStylesSearch(searchTerm).get(i).getQuantity())));
//            List<String> location = Collections.singletonList(databaseHelper.getStylesSearch(searchTerm).get(i).getPallet_location().toString());
//
//            String styleCodelv =String.valueOf(styleCode);
//            String styleColorlv = String.valueOf(styleColor);
//            String styleSizelv = String.valueOf(styleSize);
//            String quantitylv = String.valueOf(quantity);
//            String locationlv = String.valueOf(location);
//
//
//            String fullLine = "Style Code: " + styleCodelv + "\n" + "Style Color: " + styleColorlv + "\n" + "Style Size: " + styleSizelv + "\n" + "Quantity : " + quantitylv + "\n" + "Location : " + locationlv + "\n";
////            String fullLine = line1 + " - " + " LOCATION: " + line2;
//
//            ListViewConverter.add(fullLine);
//
//        }
        CustomAdapterSearch  customAdapterSearch = new CustomAdapterSearch(getApplicationContext(), (ArrayList<StyleSearchDTO>) dataBaseHelper.getStylesSearch(searchTerm));
//        searchArrayAdapter = new ArrayAdapter<StyleSearchDTO>(MainActivity8.this, android.R.layout.simple_list_item_1, ListViewConverter);
        lv_searchLista.setAdapter(customAdapterSearch);
    }
//    private void ShowSizesAuto(DAO databaseHelper) {
//        sizeArrayAdaptera = new ArrayAdapter<>(MainActivity7.this, android.R.layout.simple_list_item_1, allsize);
//        sizeAutoCompleteTextView.setAdapter(sizeArrayAdaptera);
//    }

}