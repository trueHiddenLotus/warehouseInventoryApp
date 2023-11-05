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

import com.example.usapparelinventoryapp.models.ColorModel;
import com.example.usapparelinventoryapp.customAdapters.CustomAdapterColor;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class CreateColorsScreen extends AppCompatActivity {

    Button btn_create_color;
    AutoCompleteTextView colorAutoCompleteTextView ;
    ListView lv_colorLista;
    private ArrayList<String> allcolor;
    ArrayAdapter colorArrayAdapter;
    ArrayAdapter colorArrayAdaptera;
    DataBaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        colorAutoCompleteTextView = findViewById(R.id.colorAutoCompleteTextView);
        btn_create_color = findViewById(R.id.btn_create_color);
//        btn_search = findViewById(R.id.btn_search);
        lv_colorLista = findViewById(R.id.lv_colorLista);
        databaseHelper = new DataBaseHelper( CreateColorsScreen.this);
        allcolor = databaseHelper.getAllColorsA();
        ShowColorsOnListView(databaseHelper);
        ShowColorAuto(databaseHelper);


        btn_create_color.setOnClickListener(v -> {

            ColorModel colorModel;

            if(colorChecker(colorAutoCompleteTextView.getText().toString()).equals("false")){

                Toast.makeText(this, "Color already exists", Toast.LENGTH_SHORT).show();

                return;
            } else if (colorChecker(colorAutoCompleteTextView.getText().toString()).equals("")){

                Toast.makeText(this, "Can not be blank", Toast.LENGTH_SHORT).show();
                return;

            }

            try {
                colorModel = new ColorModel(lv_colorLista.getCount() + 1 , colorAutoCompleteTextView.getText().toString());
                Toast.makeText(CreateColorsScreen.this, colorModel.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(CreateColorsScreen.this, "Error creating color", Toast.LENGTH_SHORT).show();
                colorModel = new ColorModel(-1, "error");
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(CreateColorsScreen.this);

            boolean success = dataBaseHelper.addColor(colorModel);

            ShowColorsOnListView(dataBaseHelper);
        });

        lv_colorLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ColorModel selectedLocation = (ColorModel) lv_colorLista.getItemAtPosition(position);

                ColorModel finalSelectedLocation = selectedLocation;
                String finalSelectedLocation1 = selectedLocation.getColor();
                new AlertDialog.Builder(CreateColorsScreen.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this color")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                databaseHelper.deleteColor(finalSelectedLocation);
                                Toast.makeText(CreateColorsScreen.this,  "Color "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
                                ShowColorsOnListView(databaseHelper);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

            }
        });

    }

    private String colorChecker(String color) {
//        Toast.makeText(this, style + "", Toast.LENGTH_SHORT).show();

//        Toast.makeText(this,  databaseHelper.getAllColors().get(0).getColor() + "", Toast.LENGTH_SHORT).show();

        List <String> colorList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllColors().size(); i++) {
//
            colorList.add(databaseHelper.getAllColors().get(i).getColor());
//
        }
        if (!color.isEmpty() && colorList.contains(color)) {
            color = "false";

        }
        if (color.isEmpty()) {
            color = "";
        }
//        Toast.makeText(this, color + "", Toast.LENGTH_SHORT).show();

        return color;
    }

    private void ShowColorsOnListView(DataBaseHelper dataBaseHelper) {

        CustomAdapterColor customAdapterColor = new CustomAdapterColor(getApplicationContext(), (ArrayList<ColorModel>) dataBaseHelper.getAllColors());
        lv_colorLista.setAdapter(customAdapterColor);
    }
    private void ShowColorAuto(DataBaseHelper databaseHelper) {
        colorArrayAdaptera = new ArrayAdapter<>(CreateColorsScreen.this, android.R.layout.simple_list_item_1, allcolor);
        colorAutoCompleteTextView.setAdapter(colorArrayAdaptera);

    }
}