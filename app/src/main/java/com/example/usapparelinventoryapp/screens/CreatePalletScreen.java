package com.example.usapparelinventoryapp.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usapparelinventoryapp.customAdapters.CustomAdapterPallet;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.models.ColorModel;
import com.example.usapparelinventoryapp.models.LocationModel;
import com.example.usapparelinventoryapp.models.PalletModel;
import com.example.usapparelinventoryapp.models.PalletStylesModel;
import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.models.SizeModel;
import com.example.usapparelinventoryapp.models.StyleModel;

import java.util.ArrayList;
import java.util.List;

public class CreatePalletScreen extends AppCompatActivity {

    Button btn_create, btn_search, btnPo1, btnPo2, btnPo3, btnPo4;
    EditText quantity1, quantity2, quantity3, quantity4, et_asn;
    AutoCompleteTextView style1, style2, style3, style4, size1, size2, size3, size4, color1, color2, color3, color4, et_palletLocation, newlocation;
    ListView lv_palletList;
    private ArrayList<String> allsize, alllocation;
    private List<PalletModel> allpallet;
    ArrayAdapter sizeArrayAdaptera, styleArrayAdaptera, colorArrayAdaptera, locationArrayAdaptera;

    CheckBox rocketCheckbox, oACheckbox;

    DataBaseHelper databaseHelper;

    Integer stylePo1 = null;
    Integer stylePo2 = null;
    Integer stylePo3 = null;
    Integer stylePo4 = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_create = findViewById(R.id.btn_create);
        btn_search = findViewById(R.id.btn_search);
        btnPo1 = findViewById(R.id.btn_po1);
        btnPo2 = findViewById(R.id.btn_po2);
        btnPo3 = findViewById(R.id.btn_po3);
        btnPo4 = findViewById(R.id.btn_po4);
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
        rocketCheckbox = findViewById(R.id.rocket_checkbox);
        oACheckbox = findViewById(R.id.OA_checkbox);
        rocketCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                int locID = getNextLocationId(databaseHelper);
                et_palletLocation.setText("ROCKET" + " " + locID);
            } else {
                et_palletLocation.setText("");
            }
        });
        oACheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
        if (isChecked) {
            int locID = getNextLocationId(databaseHelper);
            et_palletLocation.setText("OA" + " " + locID);
        } else {
            et_palletLocation.setText("");
        }
    });
        et_asn = findViewById(R.id.et_asn);
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

        btnPo1.setOnClickListener(v -> showPoDialog(1));
        btnPo2.setOnClickListener(v -> showPoDialog(2));
        btnPo3.setOnClickListener(v -> showPoDialog(3));
        btnPo4.setOnClickListener(v -> showPoDialog(4));
//
        btn_search.setOnClickListener(v -> {
            showSearchDialog();
        });


        btn_create.setOnClickListener(v -> {

            PalletModel palletModel = null;
            boolean newPallet = false;

            String styleVal1 = style1.getText().toString();
            String styleVal2 = style2.getText().toString();
            String styleVal3 = style3.getText().toString();
            String styleVal4 = style4.getText().toString();

            if (styleChecker(styleVal1).equals("false")) {
                showAddConfirmationDialog("Style", styleVal1);
                return;
            }

            if (styleChecker(styleVal2).equals("false")) {
                showAddConfirmationDialog("Style", styleVal2);  // fixed: previously used styleVal1 again
                return;
            }

            if (styleChecker(styleVal3).equals("false")) {
                showAddConfirmationDialog("Style", styleVal3);
                return;
            }

            if (styleChecker(styleVal4).equals("false")) {
                showAddConfirmationDialog("Style", styleVal4);
                return;
            }

            else if (
                    styleChecker(styleVal1).equals("") ||
                            styleChecker(styleVal2).equals("") ||
                            styleChecker(styleVal3).equals("") ||
                            styleChecker(styleVal4).equals("")
            ) {
                // Optional: handle empty inputs here if needed
            }



            String colorVal1 = color1.getText().toString();
            String colorVal2 = color2.getText().toString();
            String colorVal3 = color3.getText().toString();
            String colorVal4 = color4.getText().toString();

            if (colorChecker(colorVal1).equals("false")) {
                showAddConfirmationDialog("Color", colorVal1);
                return;
            }
            if (colorChecker(colorVal2).equals("false")) {
                showAddConfirmationDialog("Color", colorVal2);
                return;
            }
            if (colorChecker(colorVal3).equals("false")) {
                showAddConfirmationDialog("Color", colorVal3);
                return;
            }
            if (colorChecker(colorVal4).equals("false")) {
                showAddConfirmationDialog("Color", colorVal4);
                return;
            }
            else if (
                    colorChecker(colorVal1).equals("") ||
                            colorChecker(colorVal2).equals("") ||
                            colorChecker(colorVal3).equals("") ||
                            colorChecker(colorVal4).equals("")
            ) {
                // Optional: handle empty input warning
            }



            String sizeVal1 = size1.getText().toString();
            String sizeVal2 = size2.getText().toString();
            String sizeVal3 = size3.getText().toString();
            String sizeVal4 = size4.getText().toString();

            if (sizeChecker(sizeVal1).equals("false")) {
                showAddConfirmationDialog("Size", sizeVal1);
                return;
            }
            if (sizeChecker(sizeVal2).equals("false")) {
                showAddConfirmationDialog("Size", sizeVal2);
                return;
            }
            if (sizeChecker(sizeVal3).equals("false")) {
                showAddConfirmationDialog("Size", sizeVal3);
                return;
            }
            if (sizeChecker(sizeVal4).equals("false")) {
                showAddConfirmationDialog("Size", sizeVal4);
                return;
            }
            else if (
                    sizeChecker(sizeVal1).equals("") ||
                            sizeChecker(sizeVal2).equals("") ||
                            sizeChecker(sizeVal3).equals("") ||
                            sizeChecker(sizeVal4).equals("")
            ) {
                // Optional: handle empty input warning
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
                    String locationVal = et_palletLocation.getText().toString();

                    if (locationChecker(et_palletLocation.getText().toString()).equals("false")) {
                        showAddConfirmationDialog("Location", locationVal);
                        return;
                    }
                    else if (et_palletLocation.getText().toString().equals("")) {
                        Toast.makeText(CreatePalletScreen.this, "No location was entered", Toast.LENGTH_SHORT).show();
                        return;
                    }
//
//                    if((et_palletLocation.getText().toString()).equals("false")){
//
//                        Toast.makeText(this, "Invalid Location", Toast.LENGTH_SHORT).show();
//
//                        return;
//                    } else if (et_palletLocation.getText().toString().equals("")){
//                        Toast.makeText(this, "No location was entered", Toast.LENGTH_SHORT).show();
//                        return;
//                    }

                    try {
                        String locationText = et_palletLocation.getText().toString();
                        String asnValue = et_asn.getText().toString().trim();


                        if (rocketCheckbox.isChecked()) {
                            int newId = getNextLocationId(databaseHelper);
                            locationText = "ROCKET " + newId;

                            // Add the location to the DB
                            databaseHelper.addLocation(new LocationModel(newId, locationText));

                            if((et_palletLocation.getText().toString()).equals("false")){

                                Toast.makeText(this, "Invalid Location", Toast.LENGTH_SHORT).show();

                                return;
                            } else if (et_palletLocation.getText().toString().equals("")){
                                Toast.makeText(this, "No location was entered", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        if (asnValue.isEmpty()) {
                            Toast.makeText(CreatePalletScreen.this, "ASN cannot be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (databaseHelper.isDuplicateASN(asnValue)) {
                            Toast.makeText(CreatePalletScreen.this, "That ASN already exists.", Toast.LENGTH_SHORT).show();
                            return;
                        }




// Use final locationText for pallet model
                        palletModel = new PalletModel(-1, locationText, et_asn.getText().toString());
                        Toast.makeText(CreatePalletScreen.this, "New pallet created at location " + palletModel.getLocation(), Toast.LENGTH_SHORT).show();
                        newPallet = true;

                    }
                    catch (Exception e) {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletModel = new PalletModel(-1, "error", "error");

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

                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID() , style1.getText().toString(), color1.getText().toString(), size1.getText().toString(), Integer.parseInt(quantity1.getText().toString()), stylePo1);
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
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), style2.getText().toString(), color2.getText().toString(), size2.getText().toString(), Integer.parseInt(quantity2.getText().toString()), stylePo2);
                        Toast.makeText(CreatePalletScreen.this, palletStylesModel.getStyleCode() + " Added", Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0, null );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowPalletsOnListView(dataBaseHelper1);
                }
            }
            catch (Exception e) {

                Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0, null );
            }

            try {

                if (style3.getText().toString().equals("") || color3.getText().toString().equals("") || size3.getText().toString().equals("") || quantity3.getText().toString().equals("")) {
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), style3.getText().toString(), color3.getText().toString(), size3.getText().toString(), Integer.parseInt(quantity3.getText().toString()), stylePo3);
                        Toast.makeText(CreatePalletScreen.this,palletStylesModel.getStyleCode() + " Added", Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0, null );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowPalletsOnListView(dataBaseHelper1);
                }
            }
            catch (Exception e) {

                Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0, null );
            }

            try {

                if (style4.getText().toString().equals("") || color4.getText().toString().equals("") || size4.getText().toString().equals("") || quantity4.getText().toString().equals("")) {
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), style4.getText().toString(), color4.getText().toString(), size4.getText().toString(), Integer.parseInt(quantity4.getText().toString()), stylePo4);
                        Toast.makeText(CreatePalletScreen.this, palletStylesModel.getStyleCode() + " Added", Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0, null );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(CreatePalletScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowPalletsOnListView(dataBaseHelper1);
                }
            }
            catch (Exception e) {

                Toast.makeText(CreatePalletScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, databaseHelper.getSavedPalletID(), "error", "error2", "error3", 0, null );
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
                String text = String.valueOf(databaseHelper.getAllPallets().get(position).getAsn());
                String text2 = String.valueOf(databaseHelper.getAllPallets().get(position).getLocation());

                Intent i = new Intent(CreatePalletScreen.this, PalletViewScreen.class);

                i.putExtra("location",text2.toString());
                i.putExtra("asn",text.toString());

                startActivity(i);
            }

        });


    }

    private void showPoDialog(int row) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter PO number for Style " + row);

        final EditText input = new EditText(this);
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String inputVal = input.getText().toString().trim();
            Integer poVal = inputVal.isEmpty() ? null : Integer.valueOf(inputVal);

            switch (row) {
                case 1: stylePo1 = poVal; break;
                case 2: stylePo2 = poVal; break;
                case 3: stylePo3 = poVal; break;
                case 4: stylePo4 = poVal; break;
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
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
//                                    if(locationChecker(newlocation.getText().toString()).equals("false")){
//                                        Toast.makeText(CreatePalletScreen.this, "Invalid Location", Toast.LENGTH_SHORT).show();
//                                        return;
//                                    } else if (newlocation.getText().toString().equals("")){
//                                        Toast.makeText(CreatePalletScreen.this, "No location was entered", Toast.LENGTH_SHORT).show();
//                                        return;
//                                    }

                                    String locationVal = et_palletLocation.getText().toString();

                                    if (locationChecker(newlocation.getText().toString()).equals("false")) {
                                        showAddConfirmationDialog("Location", locationVal);
                                        return;
                                    }
                                    else if (newlocation.getText().toString().equals("")) {
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

            case R.id.update_asn:
                try {
                    PalletModel selectedPallet = (PalletModel) lv_palletList.getItemAtPosition(info.position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(CreatePalletScreen.this);
                    builder.setTitle("Update ASN");
                    builder.setMessage("Enter the new ASN for this pallet:");

                    final EditText inputAsn = new EditText(this);
                    inputAsn.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
                    inputAsn.setText(selectedPallet.getAsn());
                    builder.setView(inputAsn);

                    builder.setPositiveButton("Update", (dialog, which) -> {
                        String newAsn = inputAsn.getText().toString().trim();

                        if (newAsn.isEmpty()) {
                            Toast.makeText(CreatePalletScreen.this, "ASN cannot be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (databaseHelper.isDuplicateASN(newAsn)) {
                            Toast.makeText(CreatePalletScreen.this, "That ASN already exists.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Update the ASN in the database
                        selectedPallet.setAsn(newAsn);
                        databaseHelper.updatePallet(selectedPallet);

                        Toast.makeText(CreatePalletScreen.this, "ASN Updated", Toast.LENGTH_SHORT).show();
                        ShowPalletsOnListView(databaseHelper);
                    });

                    builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

                    builder.show();
                } catch (Exception e) {
                    Toast.makeText(this, "Error updating ASN", Toast.LENGTH_SHORT).show();
                }
                break;
        }


        return super.onContextItemSelected(item);
    }

    public int getNextLocationId(DataBaseHelper db) {
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT MAX(" + DataBaseHelper.location_id + ") FROM " + DataBaseHelper.location_table, null);
        int nextId = 1;
        if (cursor.moveToFirst()) {
            int maxId = cursor.getInt(0);
            if (!cursor.isNull(0)) {
                nextId = maxId + 1;
            }
        }
        cursor.close();
        return nextId;
    }

    private void setDropDownWidthToLongestItem(AutoCompleteTextView textView, List<String> items) {
        // Measure the longest string
        String longestItem = "";
        for (String item : items) {
            if (item.length() > longestItem.length()) {
                longestItem = item;
            }
        }

        // Use Paint to measure how wide the string is
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize()); // Match text size
        float textWidth = paint.measureText(longestItem);

        // Add padding (in pixels)
        int paddingPx = (int) (16 * textView.getResources().getDisplayMetrics().density); // ~16dp
        int totalWidth = (int) (textWidth + paddingPx);

        textView.setDropDownWidth(totalWidth);
    }

    private void autoResizeTextToFit(AutoCompleteTextView textView, String text) {
        textView.setText(text);

        float maxSize = 12f; // Slightly reduced from 14f
        float minSize = 6f;  // Avoid going too small

        Paint paint = new Paint();
        paint.set(textView.getPaint());

        int viewWidth = textView.getMeasuredWidth() > 0 ? textView.getMeasuredWidth() : textView.getWidth();
        float availableWidth = viewWidth - textView.getPaddingLeft() - textView.getPaddingRight();

        float textSize = maxSize;
        paint.setTextSize(textSize);

        // Decrease in 0.3f steps for smoother tuning
        while (paint.measureText(text) > availableWidth && textSize > minSize) {
            textSize -= 0.3f;
            paint.setTextSize(textSize);
        }

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    private void ShowLocationsAuto(DataBaseHelper dataBaseHelper) {
        locationArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, dataBaseHelper.getAllLocationsA());
        et_palletLocation.setAdapter(locationArrayAdaptera);
    }

    private void ShowStylesAuto(DataBaseHelper databaseHelper) {
        styleArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, databaseHelper.getAllStylesA());
        style1.setAdapter(styleArrayAdaptera);
        setDropDownWidthToLongestItem(style1, databaseHelper.getAllStylesA());
        style1.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(style1, selectedText);
        });
        style2.setAdapter(styleArrayAdaptera);
        setDropDownWidthToLongestItem(style2, databaseHelper.getAllStylesA());
        style2.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(style2, selectedText);
        });
        style3.setAdapter(styleArrayAdaptera);
        setDropDownWidthToLongestItem(style3, databaseHelper.getAllStylesA());
        style3.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(style3, selectedText);
        });
        style4.setAdapter(styleArrayAdaptera);
        setDropDownWidthToLongestItem(style4, databaseHelper.getAllStylesA());
        style4.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(style4, selectedText);
        });
    }

    private void ShowColorsAuto(DataBaseHelper databaseHelper) {
        colorArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, databaseHelper.getAllColorsA());
        color1.setAdapter(colorArrayAdaptera);
        setDropDownWidthToLongestItem(color1, databaseHelper.getAllColorsA());
        color1.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(color1, selectedText);
        });
        color2.setAdapter(colorArrayAdaptera);
        setDropDownWidthToLongestItem(color2, databaseHelper.getAllColorsA());
        color2.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(color2, selectedText);
        });
        color3.setAdapter(colorArrayAdaptera);
        setDropDownWidthToLongestItem(color3, databaseHelper.getAllColorsA());
        color3.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(color3, selectedText);
        });
        color4.setAdapter(colorArrayAdaptera);
        setDropDownWidthToLongestItem(color4, databaseHelper.getAllColorsA());
        color4.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(color4, selectedText);
        });
    }

    private void ShowSizesAuto(DataBaseHelper databaseHelper) {
        sizeArrayAdaptera = new ArrayAdapter<>(CreatePalletScreen.this, android.R.layout.simple_list_item_1, allsize);
        size1.setAdapter(sizeArrayAdaptera);
        setDropDownWidthToLongestItem(size1, allsize);
        size1.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(size1, selectedText);
        });
        size2.setAdapter(sizeArrayAdaptera);
        setDropDownWidthToLongestItem(size2, allsize);
        size2.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(size2, selectedText);
        });
        size3.setAdapter(sizeArrayAdaptera);
        setDropDownWidthToLongestItem(size3, allsize);
        size3.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(size3, selectedText);
        });
        size4.setAdapter(sizeArrayAdaptera);
        setDropDownWidthToLongestItem(size4, allsize);
        size4.setOnItemClickListener((parent, view, position, id) -> {
            String selectedText = (String) parent.getItemAtPosition(position);
            autoResizeTextToFit(size4, selectedText);
        });
    }

    private void showAddConfirmationDialog(String type, String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New " + type);
        builder.setMessage("The " + type.toLowerCase() + " \"" + value + "\" does not exist. Would you like to add it?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            switch (type) {
                case "Style":
                    StyleModel newStyle = new StyleModel(databaseHelper.getAllStyles().size() + 1, value);
                    databaseHelper.addStyle(newStyle);
                    break;
                case "Color":
                    ColorModel newColor = new ColorModel(databaseHelper.getAllColors().size() + 1, value);
                    databaseHelper.addColor(newColor);
                    break;
                case "Size":
                    SizeModel newSize = new SizeModel(databaseHelper.getAllSizes().size() + 1, value);
                    databaseHelper.addSize(newSize);
                    break;
                case "Location":
                    LocationModel newLocation = new LocationModel(databaseHelper.getAllLocations().size() + 1, value);
                    databaseHelper.addLocation(newLocation);
                    break;
            }
            Toast.makeText(this, type + " \"" + value + "\" added.", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreatePalletScreen.this);
        builder.setTitle("Search Options");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_search, null);
        builder.setView(dialogView);

        AutoCompleteTextView searchInput = dialogView.findViewById(R.id.searchInput);
        Button toggleButton = dialogView.findViewById(R.id.toggleButton);
        toggleButton.setTag("Pallet ID");

        toggleButton.setOnClickListener(v -> {
            String currentOption = toggleButton.getTag().toString();
            switch (currentOption) {
                case "Pallet ID":
                    toggleButton.setText("Search by ASN");
                    toggleButton.setTag("ASN");
                    break;
                case "ASN":
                    toggleButton.setText("Search by Location");
                    toggleButton.setTag("Location");
                    break;
                case "Location":
                    toggleButton.setText("Search by Pallet ID");
                    toggleButton.setTag("Pallet ID");
                    break;
            }
        });

        builder.setPositiveButton("Search", (dialog, which) -> {
            String searchOption = toggleButton.getTag().toString();
            String searchQuery = searchInput.getText().toString().trim();

            if (searchQuery.isEmpty()) {
                Toast.makeText(CreatePalletScreen.this, "Search field cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            searchListView(searchOption, searchQuery);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    private void searchListView(String searchOption, String searchQuery) {
        List<PalletModel> allPallets = databaseHelper.getAllPallets();
        boolean found = false;

        for (int i = 0; i < allPallets.size(); i++) {
            PalletModel pallet = allPallets.get(i);
            String valueToCheck = "";

            switch (searchOption) {
                case "Pallet ID":
                    valueToCheck = String.valueOf(pallet.getPallet_id());
                    break;
                case "ASN":
                    valueToCheck = pallet.getAsn();
                    break;
                case "Location":
                    valueToCheck = pallet.getLocation();
                    break;
            }

            if (valueToCheck.equalsIgnoreCase(searchQuery)) {
                lv_palletList.smoothScrollToPosition(i);
                found = true;
                break;
            }
        }

        if (!found) {
            Toast.makeText(CreatePalletScreen.this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }
    }

}