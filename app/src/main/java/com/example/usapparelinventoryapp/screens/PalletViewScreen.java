package com.example.usapparelinventoryapp.screens;

import static com.example.usapparelinventoryapp.dataBase.DataBaseHelper.location_id;

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

import com.example.usapparelinventoryapp.customAdapters.CustomAdapterSearchByLocation;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.models.PalletStylesModel;
import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.dto.StyleSearchByLocationDTO;

import java.util.ArrayList;
import java.util.List;

public class PalletViewScreen extends AppCompatActivity {

    Button btn_add, btn_search;
    EditText quantity1, quantity2, quantity3, quantity4,stylePo1, stylePo2, stylePo3, stylePo4;
    AutoCompleteTextView style1, style2, style3, style4, size1, size2, size3, size4, color1, color2, color3, color4, et_palletLocation;
    ListView lv_palletListByLocation;
    private ArrayList<String> allsize;
    private List<StyleSearchByLocationDTO> allstyle;
    private List<StyleSearchByLocationDTO> allStylesBylocationlv;


    ArrayAdapter locationSearchArrayAdapter;
    ArrayAdapter sizeArrayAdaptera, styleArrayAdaptera, colorArrayAdaptera;

    DataBaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        stylePo1 = findViewById(R.id.stylePo1);
        stylePo2 = findViewById(R.id.stylePo2);
        stylePo3 = findViewById(R.id.stylePo3);
        stylePo4 = findViewById(R.id.stylePo4);
        btn_add = findViewById(R.id.btn_add);
        btn_search = findViewById(R.id.btn_search);
        style1 = findViewById(R.id.stylea1);
        style2 = findViewById(R.id.stylea2);
        style3 = findViewById(R.id.stylea3);
        style4 = findViewById(R.id.stylea4);
        size1 = findViewById(R.id.sizea1);
        size2 = findViewById(R.id.sizea2);
        size3 = findViewById(R.id.sizea3);
        size4 = findViewById(R.id.sizea4);
        color1 = findViewById(R.id.colora1);
        color2 = findViewById(R.id.colora2);
        color3 = findViewById(R.id.colora3);
        color4 = findViewById(R.id.colora4);
        quantity1 = findViewById(R.id.quantitya1);
        quantity2 = findViewById(R.id.quantitya2);
        quantity3 = findViewById(R.id.quantitya3);
        quantity4 = findViewById(R.id.quantitya4);
        et_palletLocation = findViewById(R.id.et_addPalletLocation);
        lv_palletListByLocation = findViewById(R.id.lv_palletListByLocation);
        registerForContextMenu(lv_palletListByLocation);

        databaseHelper = new DataBaseHelper(PalletViewScreen.this);
        allsize = databaseHelper.getAllSizesA();
        allstyle = databaseHelper.getPalletStylesByLocation(location_id);
//        allStylesBylocationlv = databaseHelper.getPalletStylesByLocation();
//        ShowPalletsOnListView(databaseHelper);



        ShowStylesAuto(databaseHelper);
        ShowColorsAuto(databaseHelper);
        ShowSizesAuto(databaseHelper);

        Intent intent = this.getIntent();
        String location = null;
        String asn = null;

        if (intent != null) {

            location = intent.getStringExtra("location");
            asn = intent.getStringExtra("asn");
//            Toast.makeText(PalletViewScreen.this, location + "", Toast.LENGTH_SHORT).show();

            allStylesBylocationlv = databaseHelper.getPalletStylesByLocation(location);
            ShowSearchOnListView(databaseHelper,location);



        }
        if (allStylesBylocationlv.size() > 0) {
            int palletId = allStylesBylocationlv.get(0).style_pallet_id;
//            Toast.makeText(PalletViewScreen.this, palletId + "", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(PalletViewScreen.this, "You can NOT edit styles in locations that have no assigned pallet.", Toast.LENGTH_LONG).show();
            Toast.makeText(PalletViewScreen.this, "Please CREATE a pallet for this location using 'CREATE PALLET' option", Toast.LENGTH_LONG).show();


        }
        String finalLocation = location;
        btn_add.setOnClickListener(v -> {
            if (allStylesBylocationlv.size() > 0) {
                int palletId = allStylesBylocationlv.get(0).style_pallet_id;
            }else{

                Toast.makeText(PalletViewScreen.this, "You can NOT edit styles in locations that have no assigned pallet.", Toast.LENGTH_LONG).show();
                Toast.makeText(PalletViewScreen.this, "Please CREATE a pallet for this location using 'CREATE PALLET' option", Toast.LENGTH_LONG).show();


                return;

            }
            int palletId = allStylesBylocationlv.get(0).style_pallet_id;

            PalletStylesModel palletStylesModel = null;

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

            try {
                if (style1.getText().toString().equals("") || color1.getText().toString().equals("") || size1.getText().toString().equals("") || quantity1.getText().toString().equals("")) {
                    Toast.makeText(PalletViewScreen.this,"item1 not ready", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, palletId, style1.getText().toString(), color1.getText().toString(), size1.getText().toString(), Integer.parseInt(quantity1.getText().toString()), stylePo1.getText().toString().isEmpty() ? null : Integer.valueOf(stylePo1.getText().toString()));
                        Toast.makeText(PalletViewScreen.this, palletStylesModel.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(PalletViewScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowSearchOnListView(databaseHelper, finalLocation);

                }
            }
            catch (Exception e) {
                Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null );
            }

            try {
                if (style2.getText().toString().equals("") || color2.getText().toString().equals("") || size2.getText().toString().equals("") || quantity2.getText().toString().equals("")) {
                    Toast.makeText(PalletViewScreen.this,"item2 not ready", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, palletId, style2.getText().toString(), color2.getText().toString(), size2.getText().toString(), Integer.parseInt(quantity2.getText().toString()), stylePo2.getText().toString().isEmpty() ? null : Integer.valueOf(stylePo2.getText().toString()));
                        Toast.makeText(PalletViewScreen.this, palletStylesModel.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(PalletViewScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowSearchOnListView(databaseHelper, finalLocation);

                }
            }
            catch (Exception e) {
                Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null );
            }

            try {
                if (style3.getText().toString().equals("") || color3.getText().toString().equals("") || size3.getText().toString().equals("") || quantity3.getText().toString().equals("")) {
                    Toast.makeText(PalletViewScreen.this,"item3 not ready", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, palletId, style3.getText().toString(), color3.getText().toString(), size3.getText().toString(), Integer.parseInt(quantity3.getText().toString()), stylePo3.getText().toString().isEmpty() ? null : Integer.valueOf(stylePo3.getText().toString()));
                        Toast.makeText(PalletViewScreen.this, palletStylesModel.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null);
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(PalletViewScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowSearchOnListView(databaseHelper, finalLocation);

                }
            }
            catch (Exception e) {
                Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null );
            }

            try {
                if (style4.getText().toString().equals("") || color4.getText().toString().equals("") || size4.getText().toString().equals("") || quantity4.getText().toString().equals("")) {
                    Toast.makeText(PalletViewScreen.this,"item4 not ready", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        palletStylesModel = new PalletStylesModel(-1, palletId, style4.getText().toString(), color4.getText().toString(), size4.getText().toString(), Integer.parseInt(quantity4.getText().toString()), stylePo4.getText().toString().isEmpty() ? null : Integer.valueOf(stylePo4.getText().toString()));
                        Toast.makeText(PalletViewScreen.this, palletStylesModel.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                        palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null );
                    }
                    DataBaseHelper dataBaseHelper1 = new DataBaseHelper(PalletViewScreen.this);

                    boolean success1 = dataBaseHelper1.addPalletStyle(palletStylesModel);
                    ShowSearchOnListView(databaseHelper, finalLocation);

                }
            }
            catch (Exception e) {
                Toast.makeText(PalletViewScreen.this, "Error creating pallet", Toast.LENGTH_SHORT).show();
                palletStylesModel = new PalletStylesModel(-1, palletId, "error", "error2", "error3", 0, null );
            }
        });


//        lv_palletListByLocation.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                StyleSearchByLocationDTO selectedLocation = (StyleSearchByLocationDTO) lv_palletListByLocation.getItemAtPosition(position);
//                StyleSearchByLocationDTO finalSelectedLocation = selectedLocation;
//                int finalSelectedLocation1 = selectedLocation.getPallet_styles_id();
//                String finalSelectedLocation2 = selectedLocation.getPallet_location();
//                new AlertDialog.Builder(PalletViewScreen.this)
//                        .setIcon(android.R.drawable.ic_delete)
//                        .setTitle("Are you sure ?")
//                        .setMessage("Do you want to delete this Style")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int which) {
//                                databaseHelper.deleteStyleFromPallet(finalSelectedLocation);
//                                Toast.makeText(PalletViewScreen.this,  "Style "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
//                                ShowSearchOnListView(databaseHelper,finalSelectedLocation2);
//
//                            }
//
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//
//                return true;
//
//            }
//        });
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
//        Toast.makeText(this, style + "", Toast.LENGTH_SHORT).show();

//        Toast.makeText(this,  databaseHelper.getAllSizes().get(0).getSize() + "", Toast.LENGTH_SHORT).show();

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


//
    private void ShowSearchOnListView(DataBaseHelper dataBaseHelper, String locationId) {
        CustomAdapterSearchByLocation customAdapterSearchByLocation = new CustomAdapterSearchByLocation(getApplicationContext(), (ArrayList<StyleSearchByLocationDTO>) dataBaseHelper.getPalletStylesByLocation(locationId));

        lv_palletListByLocation.setAdapter(customAdapterSearchByLocation);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.palletstyle_context_menu, menu);
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        StyleSearchByLocationDTO selectedStyle = (StyleSearchByLocationDTO) lv_palletListByLocation.getItemAtPosition(info.position);
//
//        if (item.getItemId() == R.id.edit_pallet_style) {
//            showEditStyleDialog(selectedStyle);
//            return true;
//        }
//        return super.onContextItemSelected(item);
//    }

    private void showEditStyleDialog(StyleSearchByLocationDTO style) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Pallet Style");

        View view = getLayoutInflater().inflate(R.layout.dialog_edit_pallet_style, null);

        EditText etStyleCode = view.findViewById(R.id.et_style_code);
        EditText etStyleColor = view.findViewById(R.id.et_style_color);
        EditText etStyleSize = view.findViewById(R.id.et_style_size);
        EditText etQuantity = view.findViewById(R.id.et_quantity);
        EditText etStylePo = view.findViewById(R.id.et_style_po);

        etStyleCode.setText(style.getStyle_code());
        etStyleColor.setText(style.getStyle_color());
        etStyleSize.setText(style.getStyle_size());
        etQuantity.setText(String.valueOf(style.getQuantity()));
        etStylePo.setText(style.getStyle_po() == 0 ? "" : String.valueOf(style.getStyle_po()));

        builder.setView(view);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newCode = etStyleCode.getText().toString();
            String newColor = etStyleColor.getText().toString();
            String newSize = etStyleSize.getText().toString();
            int newQty = Integer.parseInt(etQuantity.getText().toString());
            String newPoText = etStylePo.getText().toString();
            Integer newPo = newPoText.isEmpty() ? null : Integer.parseInt(newPoText);

            if (!style.getStyle_code().equals(newCode)) {
                databaseHelper.updatePalletStyleField(style.getPallet_styles_id(), "style_code", newCode);
            }
            if (!style.getStyle_color().equals(newColor)) {
                databaseHelper.updatePalletStyleField(style.getPallet_styles_id(), "style_color", newColor);
            }
            if (!style.getStyle_size().equals(newSize)) {
                databaseHelper.updatePalletStyleField(style.getPallet_styles_id(), "style_size", newSize);
            }
            if (style.getQuantity() != newQty) {
                databaseHelper.updatePalletStyleField(style.getPallet_styles_id(), "quantity", String.valueOf(newQty));
            }
            if (style.getStyle_po() != newPo) {
                databaseHelper.updatePalletStyleField(style.getPallet_styles_id(), "style_po", newPo == null ? null : String.valueOf(newPo));
            }

            ShowSearchOnListView(databaseHelper, style.getPallet_location());
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        StyleSearchByLocationDTO selectedStyle = (StyleSearchByLocationDTO) lv_palletListByLocation.getItemAtPosition(info.position);

        if (item.getItemId() == R.id.edit_pallet_style) {
            showEditStyleDialog(selectedStyle);
            return true;
        }

        if (item.getItemId() == R.id.delete_pallet_style) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_delete)
                    .setTitle("Delete Style")
                    .setMessage("Do you want to delete this Style?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        databaseHelper.deleteStyleFromPallet(selectedStyle);
                        Toast.makeText(PalletViewScreen.this, "Style Deleted", Toast.LENGTH_SHORT).show();
                        ShowSearchOnListView(databaseHelper, selectedStyle.getPallet_location());
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }

        return super.onContextItemSelected(item);
    }
    private void ShowStylesAuto(DataBaseHelper databaseHelper) {
        styleArrayAdaptera = new ArrayAdapter<>(PalletViewScreen.this, android.R.layout.simple_list_item_1, databaseHelper.getAllStylesA());
        style1.setAdapter(styleArrayAdaptera);
        style2.setAdapter(styleArrayAdaptera);
        style3.setAdapter(styleArrayAdaptera);
        style4.setAdapter(styleArrayAdaptera);    }

    private void ShowColorsAuto(DataBaseHelper databaseHelper) {
        colorArrayAdaptera = new ArrayAdapter<>(PalletViewScreen.this, android.R.layout.simple_list_item_1, databaseHelper.getAllColorsA());
        color1.setAdapter(colorArrayAdaptera);
        color2.setAdapter(colorArrayAdaptera);
        color3.setAdapter(colorArrayAdaptera);
        color4.setAdapter(colorArrayAdaptera);
    }

    private void ShowSizesAuto(DataBaseHelper databaseHelper) {
        sizeArrayAdaptera = new ArrayAdapter<>(PalletViewScreen.this, android.R.layout.simple_list_item_1, allsize);
        size1.setAdapter(sizeArrayAdaptera);
        size2.setAdapter(sizeArrayAdaptera);
        size3.setAdapter(sizeArrayAdaptera);
        size4.setAdapter(sizeArrayAdaptera);
    }

}