package com.example.usapparelinventoryapp.screens;

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
import android.widget.ListView;
import android.widget.Toast;

import com.example.usapparelinventoryapp.customAdapters.CustomAdapterSize;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.models.SizeModel;

import java.util.ArrayList;
import java.util.List;

public class CreateSizesScreen extends AppCompatActivity {

    Button btn_create_size;
    AutoCompleteTextView sizeAutoCompleteTextView ;
    ListView lv_sizeLista;
    private ArrayList<String> allsize;
    ArrayAdapter sizeArrayAdaptera;
    //
    DataBaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        sizeAutoCompleteTextView = findViewById(R.id.sizeAutoCompleteTextView);
        btn_create_size = findViewById(R.id.btn_create_size);
        lv_sizeLista = findViewById(R.id.lv_sizeLista);
        databaseHelper = new DataBaseHelper( CreateSizesScreen.this);
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
                Toast.makeText(CreateSizesScreen.this, sizeModel.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(CreateSizesScreen.this, "Error creating size", Toast.LENGTH_SHORT).show();
                sizeModel = new SizeModel(-1, "error");
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(CreateSizesScreen.this);

            boolean success = dataBaseHelper.addSize(sizeModel);

            ShowSizesOnListView(dataBaseHelper);


        });

        lv_sizeLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SizeModel selectedLocation = (SizeModel) lv_sizeLista.getItemAtPosition(position);
                SizeModel finalSelectedLocation = selectedLocation;
                String finalSelectedLocation1 = selectedLocation.getSize();
                new AlertDialog.Builder(CreateSizesScreen.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this location")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                databaseHelper.deleteSize(finalSelectedLocation);
                                Toast.makeText(CreateSizesScreen.this,  "Location "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
                                ShowSizesOnListView(databaseHelper);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

            }
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
    private void ShowSizesOnListView(DataBaseHelper dataBaseHelper) {
        CustomAdapterSize customAdapterSize = new CustomAdapterSize(getApplicationContext(), (ArrayList<SizeModel>) dataBaseHelper.getAllSizes());
        lv_sizeLista.setAdapter(customAdapterSize);
    }
    private void ShowSizesAuto(DataBaseHelper databaseHelper) {
        sizeArrayAdaptera = new ArrayAdapter<>(CreateSizesScreen.this, android.R.layout.simple_list_item_1, allsize);
        sizeAutoCompleteTextView.setAdapter(sizeArrayAdaptera);
    }
}