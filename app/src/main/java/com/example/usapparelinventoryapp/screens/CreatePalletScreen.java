package com.example.usapparelinventoryapp.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usapparelinventoryapp.customAdapters.CustomAdapterPallet;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.models.PalletModel;
import com.example.usapparelinventoryapp.models.PalletStylesModel;
import com.example.usapparelinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class CreatePalletScreen extends AppCompatActivity {

    Button btn_create, btn_search;
    EditText quantity1, quantity2, quantity3, quantity4;
    AutoCompleteTextView style1, style2, style3, style4, size1, size2, size3, size4, color1, color2, color3, color4, et_palletLocation, newlocation;
    ListView lv_palletList;
    private ArrayList<String> allsize, alllocation;
    private List<PalletModel> allpallet;
    ArrayAdapter sizeArrayAdaptera, styleArrayAdaptera, colorArrayAdaptera, locationArrayAdaptera;

    DataBaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_create = findViewById(R.id.btn_create);
        btn_search = findViewById(R.id.btn_search);
        style1 =findViewById(R.id.style1);
        style2 =findViewById(R.id.style2);
        style3 =findViewById(R.id.style3);
        style4 =findViewById(R.id.style4);
        size1 =findViewById(R.id.size1);
        size2 =findViewById(R.id.size2);
        size3 =findViewById(R.id.size3);
        size4 =findViewById(R.id.size4);
        color1 =findViewById(R.id.color1);
        color2 =findViewById(R.id.color2);
        color3 =findViewById(R.id.color3);
        color4 =findViewById(R.id.color4);
        quantity1 =findViewById(R.id.quantity1);
        quantity2 =findViewById(R.id.quantity2);
        quantity3 =findViewById(R.id.quantity3);
        quantity4 =findViewById(R.id.quantity4);
        et_palletLocation =findViewById(R.id.et_palletLocation);
        lv_palletList = findViewById(R.id.lv_palletList);
        databaseHelper = new DataBaseHelper( CreatePalletScreen.this);
        allsize = databaseHelper.getAllSizesA();
        allpallet = databaseHelper.getAllPallets();
        alllocation = databaseHelper.getAllLocationsA();

        ShowPalletsOnListView(databaseHelper);
        ShowStylesAuto(databaseHelper);
        ShowColorsAuto(databaseHelper);
        ShowSizesAuto(databaseHelper);
        ShowLocationsAuto(databaseHelper);



        if (PalletIdChecker()){
            Toast.makeText(this, databaseHelper.getSavedPalletID() + "", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, databaseHelper.getSavedPalletID() + "", Toast.LENGTH_SHORT).show();
        }
//
        btn_search.setOnClickListener(v -> {
            lv_palletList.smoothScrollToPosition((Integer) lv_palletList.getItemAtPosition(0));
        } );


        btn_create.setOnClickListener(v -> {

            PalletModel palletModel = null;
            boolean newPallet = false;


            if(styleChecker(style1.getText().toString()).equals("false") || styleChecker(style2.getText().toString()).equals("false") || styleChecker(style3.getText().toString()).equals("false")|| styleChecker(style4.getText().toString()).equals("false")){

                Toast.makeText(this, "Check Styles", Toast.LENGTH_SHORT).show();

                return;
            } else if (styleChecker(style1.getText().toString()).equals("") || styleChecker(style2.getText().toString()).equals("") || styleChecker(style3.getText().toString()).equals("")|| styleChecker(style4.getText().toString()).equals("")) {

            }



            if(colorChecker(color1.getText().toString()).equals("false") || colorChecker(color2.getText().toString()).equals("false") || colorChecker(color3.getText().toString()).equals("false")|| colorChecker(color4.getText().toString()).equals("false")){

                Toast.makeText(this, "Check Colors", Toast.LENGTH_SHORT).show();

                return;
            } else if (colorChecker(color1.getText().toString()).equals("") || colorChecker(color2.getText().toString()).equals("") || colorChecker(color3.getText().toString()).equals("")|| colorChecker(color4.getText().toString()).equals("")){

            }



            if(sizeChecker(size1.getText().toString()).equals("false") || sizeChecker(size2.getText().toString()).equals("false") || sizeChecker(size3.getText().toString()).equals("false")|| sizeChecker(size4.getText().toString()).equals("false")){

                Toast.makeText(this, "Check Sizes", Toast.LENGTH_SHORT).show();

                return;
            } else if (sizeChecker(size1.getText().toString()).equals("") || sizeChecker(size2.getText().toString()).equals("") || sizeChecker(size3.getText().toString()).equals("")|| sizeChecker(size4.getText().toString()).equals("")){

            }



            if(locationChecker(et_palletLocation.getText().toString()).equals("false")){

                Toast.makeText(this, "Invalid Location", Toast.LENGTH_SHORT).show();

                return;
            } else if (et_palletLocation.getText().toString().equals("")){
                Toast.makeText(this, "No location was entered", Toast.LENGTH_SHORT).show();
                return;
            }




            for(int i = 0; i<databaseHelper.getAllPallets().size();i++){
                if(databaseHelper.getAllPallets().get(i).getLocation().contains(et_palletLocation.getText().toString())){
                    Toast.makeText(CreatePalletScreen.this,"Location already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            try{

                if (style1.getText().toString().equals("") || color1.getText().toString().equals("") || size1.getText().toString().equals("") || quantity1.getText().toString().equals("")) {
                    Toast.makeText(CreatePalletScreen.this,"item1 not ready", Toast.LENGTH_SHORT).show();


                }else {
                    try {
                        palletModel = new PalletModel(-1, et_palletLocation.getText().toString());
                        Toast.makeText(CreatePalletScreen.this, "New pallet created at location " + palletModel.getLocation(), Toast.LENGTH_SHORT).show();
                        newPallet = true;

                    }
                    catch (Exception e) {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletModel = new PalletModel(-1, "error");

                    }

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success = dataBaseHelper.addPallet(palletModel);
                    ShowPalletsOnListView(dataBaseHelper);

                }
            } catch (Exception e) {
                Toast.makeText(CreatePalletScreen.this, "Error creating pallet yall", Toast.LENGTH_SHORT).show();
            }


            PalletStylesModel palletStylesModel = null;

            try {

                if (style1.getText().toString().equals("") || color1.getText().toString().equals("") || size1.getText().toString().equals("") || quantity1.getText().toString().equals("")) {
                    Toast.makeText(CreatePalletScreen.this,"item1 not ready", Toast.LENGTH_SHORT).show();
                }else {
                    try {

                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID() , style1.getText().toString(), color1.getText().toString(), size1.getText().toString(), Integer.parseInt(quantity1.getText().toString()));
                        Toast.makeText(CreatePalletScreen.this, palletStylesModel.getStyleCode() + " Added", Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(CreatePalletScreen.this, "Style 1 Data persistence error", Toast.LENGTH_SHORT).show();
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowPalletsOnListView(dataBaseHelper1);
                }
            }
            catch (Exception e) {

                Toast.makeText(CreatePalletScreen.this, "Style 1 error", Toast.LENGTH_SHORT).show();
            }

            try {

                if (style2.getText().toString().equals("") || color2.getText().toString().equals("") || size2.getText().toString().equals("") || quantity2.getText().toString().equals("")) {
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), style2.getText().toString(), color2.getText().toString(), size2.getText().toString(), Integer.parseInt(quantity2.getText().toString()));
                        Toast.makeText(CreatePalletScreen.this, palletStylesModel.getStyleCode() + " Added", Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0 );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowPalletsOnListView(dataBaseHelper1);
                }
            }
            catch (Exception e) {

                Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0 );
            }

            try {

                if (style3.getText().toString().equals("") || color3.getText().toString().equals("") || size3.getText().toString().equals("") || quantity3.getText().toString().equals("")) {
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), style3.getText().toString(), color3.getText().toString(), size3.getText().toString(), Integer.parseInt(quantity3.getText().toString()));
                        Toast.makeText(CreatePalletScreen.this,palletStylesModel.getStyleCode() + " Added", Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0 );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowPalletsOnListView(dataBaseHelper1);
                }
            }
            catch (Exception e) {

                Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0 );
            }

            try {

                if (style4.getText().toString().equals("") || color4.getText().toString().equals("") || size4.getText().toString().equals("") || quantity4.getText().toString().equals("")) {
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), style4.getText().toString(), color4.getText().toString(), size4.getText().toString(), Integer.parseInt(quantity4.getText().toString()));
                        Toast.makeText(CreatePalletScreen.this, palletStylesModel.getStyleCode() + " Added", Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0 );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowPalletsOnListView(dataBaseHelper1);
                }
            }
            catch (Exception e) {

                Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0 );
            }

            try {
                if (newPallet == true) {
                    Toast.makeText(this, databaseHelper.getSavedPalletID() + "", Toast.LENGTH_SHORT).show();
                    if (PalletIdChecker() == true && IdGreaterThanOne() == false) {
                        databaseHelper.updatePalletId(databaseHelper.getSavedPalletID());
                        Toast.makeText(CreatePalletScreen.this, databaseHelper.getSavedPalletID() + "a", Toast.LENGTH_SHORT).show();
                    } else if (PalletIdChecker() == true && IdGreaterThanOne() == true) {
                        databaseHelper.updatePalletId(databaseHelper.getSavedPalletID() + 1);
                        Toast.makeText(this, databaseHelper.getSavedPalletID() + "b", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseHelper.updatePalletId(databaseHelper.getSavedPalletID() + 1);
                        Toast.makeText(CreatePalletScreen.this, databaseHelper.getSavedPalletID() + "c", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    return;
                }
            }
            catch (Exception e) {

            }




        });

        lv_palletList.setClickable(true);
        lv_palletList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onClick(View view) {

            }

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = String.valueOf(databaseHelper.getAllPallets().get(position).getPallet_id());
                String text2 = String.valueOf(databaseHelper.getAllPallets().get(position).getLocation());

                Intent i = new Intent(CreatePalletScreen.this, PalletViewScreen.class);

                i.putExtra("location",text2.toString());

                startActivity(i);
            }

        });


    }

    private String styleChecker(String style) {

        List <String> styleList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllStyles().size(); i++) {
            styleList.add(databaseHelper.getAllStyles().get(i).getStyle());
        }
        if (!style.isEmpty() && !styleList.contains(style)) {
            style = "false";

        }
        if (style.isEmpty()) {
            style = "";
        }
        return style;
    }







    private String sizeChecker(String size) {

        List <String> sizeList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllSizes().size(); i++) {

            sizeList.add(databaseHelper.getAllSizes().get(i).getSize());

        }
        if (!size.isEmpty() && !sizeList.contains(size)) {
            size = "false";

        }
        if (size.isEmpty()) {
            size = "";
        }

        return size;
    }

    private String colorChecker(String color) {


        List <String> colorList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllColors().size(); i++) {
//
            colorList.add(databaseHelper.getAllColors().get(i).getColor());
//
        }
        if (!color.isEmpty() && !colorList.contains(color)) {
            color = "false";

        }
        if (color.isEmpty()) {
            color = "";
        }

        return color;
    }

    private String locationChecker(String location) {

        List <String> locationList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllLocations().size(); i++) {
//
            locationList.add(databaseHelper.getAllLocations().get(i).getLocation());
//
        }
        if (!location.isEmpty() && !locationList.contains(location)) {
            location = "false";

        }
        if (location.isEmpty()) {
            location = "";
        }

        return location;
    }

    private boolean PalletIdChecker() {
        boolean p = false;
        if (databaseHelper.getAllPallets().size() == 0){

            p = true;
        }

        return p;
    }

    private boolean IdGreaterThanOne() {
        boolean p = false;
        if (databaseHelper.getSavedPalletID() >= 1){

            p = true;
        }

        return p;
    }


    private void ShowPalletsOnListView(DataBaseHelper dataBaseHelper) {

        CustomAdapterPallet customAdapterPallet = new CustomAdapterPallet(getApplicationContext(), (ArrayList<PalletModel>) dataBaseHelper.getAllPallets());
        lv_palletList.setAdapter(customAdapterPallet);
        registerForContextMenu(lv_palletList);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.palletlistview_context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId())
        {
            case R.id.remove_pallet:
                databaseHelper.removePallet((PalletModel) lv_palletList.getItemAtPosition(info.position));
                ShowPalletsOnListView(databaseHelper);
                break;

            case R.id.reassign_id:
                try{
                    locationArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, databaseHelper.getAllLocationsA());
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreatePalletScreen.this);
                    builder.setIcon(android.R.drawable.ic_menu_edit);
                    builder.setTitle("Reassign pallet?");
                    builder.setMessage("Enter new location for this pallet.");
                    newlocation = new AutoCompleteTextView(this);
                    newlocation.setAdapter(locationArrayAdaptera);
                    builder.setView(newlocation);

                    builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    for(int i = 0; i<databaseHelper.getAllPallets().size();i++){
                                        if(databaseHelper.getAllPallets().get(i).getLocation().contains(newlocation.getText().toString())){
                                            Toast.makeText(CreatePalletScreen.this,"Location already in use", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                    if(locationChecker(newlocation.getText().toString()).equals("false")){
                                        Toast.makeText(CreatePalletScreen.this, "Invalid Location", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else if (newlocation.getText().toString().equals("")){
                                        Toast.makeText(CreatePalletScreen.this, "No location was entered", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    databaseHelper.reassignLocation((PalletModel) lv_palletList.getItemAtPosition(info.position), String.valueOf(newlocation.getText()));
                                    Toast.makeText(CreatePalletScreen.this, "Pallet " + ((PalletModel) lv_palletList.getItemAtPosition(info.position)).getPallet_id() + " reassigned to "  + newlocation.getText().toString(), Toast.LENGTH_SHORT).show();
                                    ShowPalletsOnListView(databaseHelper);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }catch (Exception e){
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.delete_pallet:

                try{ PalletModel selectedLocation = (PalletModel) lv_palletList.getItemAtPosition(info.position);

                    PalletModel finalSelectedLocation = selectedLocation;
                    int finalSelectedLocation1 = selectedLocation.getPallet_id();
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreatePalletScreen.this);
                    builder.setIcon(android.R.drawable.ic_delete);
                    builder.setTitle("Are you sure ?");
                    builder.setMessage("Do you want to delete this pallet?\n(Associated styles will be deleted as well.)");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    databaseHelper.deletePallet(finalSelectedLocation);
                                    Toast.makeText(CreatePalletScreen.this,  "Pallet "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
                                    ShowPalletsOnListView(databaseHelper);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                } catch(Exception e){

                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                break;
        }


        return super.onContextItemSelected(item);
    }

    private void ShowLocationsAuto(DataBaseHelper dataBaseHelper) {
        locationArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllLocationsA());
        et_palletLocation.setAdapter(locationArrayAdaptera);
    }

    private void ShowStylesAuto(DataBaseHelper databaseHelper) {
        styleArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, databaseHelper.getAllStylesA());
        style1.setAdapter(styleArrayAdaptera);
        style2.setAdapter(styleArrayAdaptera);
        style3.setAdapter(styleArrayAdaptera);
        style4.setAdapter(styleArrayAdaptera);    }

    private void ShowColorsAuto(DataBaseHelper databaseHelper) {
        colorArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, databaseHelper.getAllColorsA());
        color1.setAdapter(colorArrayAdaptera);
        color2.setAdapter(colorArrayAdaptera);
        color3.setAdapter(colorArrayAdaptera);
        color4.setAdapter(colorArrayAdaptera);
    }

    private void ShowSizesAuto(DataBaseHelper databaseHelper) {
        sizeArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, allsize);
        size1.setAdapter(sizeArrayAdaptera);
        size2.setAdapter(sizeArrayAdaptera);
        size3.setAdapter(sizeArrayAdaptera);
        size4.setAdapter(sizeArrayAdaptera);
    }


}