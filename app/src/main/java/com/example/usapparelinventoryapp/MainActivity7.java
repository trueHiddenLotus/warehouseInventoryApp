package com.example.usapparelinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity7 extends AppCompatActivity {

    Button btn_create_size;
    AutoCompleteTextView sizeAutoCompleteTextView ;
    ListView lv_sizeLista;
    private ArrayList<String> allsize;
    //
    ArrayAdapter sizeArrayAdapter;
    ArrayAdapter sizeArrayAdaptera;
    //
    DAO databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        sizeAutoCompleteTextView = findViewById(R.id.sizeAutoCompleteTextView);
        btn_create_size = findViewById(R.id.btn_create_size);
        //        btn_search = findViewById(R.id.btn_search);
        lv_sizeLista = findViewById(R.id.lv_sizeLista);
        databaseHelper = new DAO( MainActivity7.this);
        allsize = databaseHelper.getAllSizesA();
        ShowSizesOnListView(databaseHelper);
        ShowSizesAuto(databaseHelper);


        btn_create_size.setOnClickListener(v -> {

            SizeModel sizeModel;
            if(sizeChecker(sizeAutoCompleteTextView.getText().toString()).equals("false")){

                Toast.makeText(this, "Size already exists", Toast.LENGTH_SHORT).show();

                return;
            } else if (sizeChecker(sizeAutoCompleteTextView.getText().toString()).equals("")){
                Toast.makeText(this, "Can not be blank", Toast.LENGTH_SHORT).show();

                return;

            }

            try {
                sizeModel = new SizeModel(lv_sizeLista.getCount() + 1 , sizeAutoCompleteTextView.getText().toString());
                Toast.makeText(MainActivity7.this, sizeModel.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(MainActivity7.this, "Error creating size", Toast.LENGTH_SHORT).show();
                sizeModel = new SizeModel(-1, "error");
            }

            DAO dataBaseHelper = new DAO(MainActivity7.this);

            boolean success = dataBaseHelper.addSize(sizeModel);

            ShowSizesOnListView(dataBaseHelper);


        });

        lv_sizeLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SizeModel selectedLocation = (SizeModel) lv_sizeLista.getItemAtPosition(position);
//                Toast.makeText(MainActivity5.this, selectedLocation + "", Toast.LENGTH_SHORT).show();
//
//                StringBuilder sb = new StringBuilder(selectedLocation);
//                sb.delete(10, sb.length()-1);
//                Toast.makeText(MainActivity5.this, sb.length() + "", Toast.LENGTH_SHORT).show();
////                sb.deleteCharAt(9);
//
//                selectedLocation = sb.toString();
//                Toast.makeText(MainActivity5.this, selectedLocation + "", Toast.LENGTH_SHORT).show();



                SizeModel finalSelectedLocation = selectedLocation;
                String finalSelectedLocation1 = selectedLocation.getSize();
                new AlertDialog.Builder(MainActivity7.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this location")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                databaseHelper.deleteSize(finalSelectedLocation);
                                Toast.makeText(MainActivity7.this,  "Location "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
                                ShowSizesOnListView(databaseHelper);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

            }

//            @Override
//            public boolean OnItemLongClick (AdapterView <?> parent,View view, int position, long id) {
//
//            }



        });

    }

    private String sizeChecker(String size) {

        List <String> sizeList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllSizes().size(); i++) {

            sizeList.add(databaseHelper.getAllSizes().get(i).getSize());

        }
        if (!size.isEmpty() && sizeList.contains(size)) {
            size = "false";

        }
        if (size.isEmpty()) {
            size = "";
        }

        return size;
    }
    private void ShowSizesOnListView(DAO dataBaseHelper) {
//        ArrayList  ListViewConverter = new ArrayList<>();
//        for (int i = 0; i< dataBaseHelper.getAllSizes().size(); i++){
////            List<String> location_id = Collections.singletonList(String.valueOf(Collections.singletonList(alllocationlv.get(i).getLocation_id())));
//
//            List<String> size = Collections.singletonList(dataBaseHelper.getAllSizes().get(i).getSize().toString());
////            String line1 =String.valueOf(location_id);
//
//            String line2 = String.valueOf(size);
//
//            String fullLine = "           Size: " + line2;
////            String fullLine = line1 + " - " + " LOCATION: " + line2;
//
//            ListViewConverter.add(fullLine);
//
//        }
        CustomAdapterSize  customAdapterSize = new CustomAdapterSize(getApplicationContext(), (ArrayList<SizeModel>) dataBaseHelper.getAllSizes());
        lv_sizeLista.setAdapter(customAdapterSize);
    }
    private void ShowSizesAuto(DAO databaseHelper) {
        sizeArrayAdaptera = new ArrayAdapter<>(MainActivity7.this, android.R.layout.simple_list_item_1, allsize);
        sizeAutoCompleteTextView.setAdapter(sizeArrayAdaptera);
    }
}