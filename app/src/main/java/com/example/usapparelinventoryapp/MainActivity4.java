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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    Button btn_create;
    AutoCompleteTextView styleAutoCompleteTextView ;
    ListView lv_styleLista;
    private ArrayList<String> allstyle;
    //
    ArrayAdapter styleArrayAdapter;
    ArrayAdapter styleArrayAdaptera;
    //
    DAO databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        styleAutoCompleteTextView = findViewById(R.id.styleAutoCompleteTextView);
        btn_create = findViewById(R.id.btn_create);
//        btn_search = findViewById(R.id.btn_search);
        lv_styleLista = findViewById(R.id.lv_styleLista);
        databaseHelper = new DAO( MainActivity4.this);
        allstyle = databaseHelper.getAllStylesA();
        ShowStylesOnListView(databaseHelper);
        ShowStylesAuto(databaseHelper);


        btn_create.setOnClickListener(v -> {

            StyleModel styleModel;
            if(styleChecker(styleAutoCompleteTextView.getText().toString()).equals("false")){

                Toast.makeText(this, "Style already exists", Toast.LENGTH_SHORT).show();

                return;
            } else if (styleChecker(styleAutoCompleteTextView.getText().toString()).equals("")) {
                Toast.makeText(this, "Can not be blank", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                styleModel = new StyleModel(lv_styleLista.getCount() + 1 , styleAutoCompleteTextView.getText().toString());
                Toast.makeText(MainActivity4.this, styleModel.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(MainActivity4.this, "Error creating size", Toast.LENGTH_SHORT).show();
                styleModel = new StyleModel(-1, "error");
            }

            DAO dataBaseHelper = new DAO(MainActivity4.this);

            boolean success = dataBaseHelper.addStyle(styleModel);

            ShowStylesOnListView(dataBaseHelper);
//
//
        });

        lv_styleLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                StyleModel selectedLocation = (StyleModel) lv_styleLista.getItemAtPosition(position);

                StyleModel finalSelectedLocation = selectedLocation;
                String finalSelectedLocation1 = selectedLocation.getStyle();
                new AlertDialog.Builder(MainActivity4.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this style")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                databaseHelper.deleteStyle(finalSelectedLocation);
                                Toast.makeText(MainActivity4.this,  "Style "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
                                ShowStylesOnListView(databaseHelper);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

            }
        });

    }

    private String styleChecker(String style) {
//        Toast.makeText(MainActivity.this, style + "", Toast.LENGTH_SHORT).show();

//        Toast.makeText(this,  databaseHelper.getAllStyles().get(0).getStyle() + "", Toast.LENGTH_SHORT).show();
        List<String> styleList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllStyles().size(); i++) {

            styleList.add(databaseHelper.getAllStyles().get(i).getStyle());

        }
        if (!style.isEmpty() && styleList.contains(style)) {
            style = "false";

        }
        if (style.isEmpty()) {
            style = "";
        }
        return style;
    }
    private void ShowStylesOnListView(DAO dataBaseHelper) {

        CustomerAdapterStyle  customerAdapterStyle = new CustomerAdapterStyle(getApplicationContext(), (ArrayList<StyleModel>) dataBaseHelper.getAllStyles());

        lv_styleLista.setAdapter(customerAdapterStyle);
    }
    private void ShowStylesAuto(DAO databaseHelper) {
        styleArrayAdaptera = new ArrayAdapter<>(MainActivity4.this, android.R.layout.simple_list_item_1, allstyle);
        styleAutoCompleteTextView.setAdapter(styleArrayAdaptera);

    }
}