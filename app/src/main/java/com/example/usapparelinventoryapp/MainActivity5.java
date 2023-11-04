package com.example.usapparelinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usapparelinventoryapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    ActivityMainBinding binding;

    Button btn_create, btn_search;
    AutoCompleteTextView locationAutoCompleteTextView ;
    ListView lv_locationLista;
    private ArrayList<String> alllocation;
    private List<LocationModel> alllocationlv;
//    private static  CustomArrayAdapterLocation<LocationModel> CLA_adapter;
    ArrayAdapter locationArrayAdapter;
    ArrayAdapter locationArrayAdaptera;
    DAO databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        locationAutoCompleteTextView = findViewById(R.id.locationAutoCompleteTextView);
        btn_create = findViewById(R.id.btn_create_location);
        btn_search = findViewById(R.id.btn_search);
        lv_locationLista = findViewById(R.id.lv_locationLista);
        databaseHelper = new DAO( MainActivity5.this);
        alllocation = databaseHelper.getAllLocationsA();
        alllocationlv = databaseHelper.getAllLocations();
        ShowLocationsOnListView(databaseHelper);
        ShowLocationAuto(databaseHelper);

        ArrayList<LocationModel>  ListViewIdByLocation= new ArrayList<>();
        for (int i = 0; i< databaseHelper.getAllLocations().size(); i++){
            int line1 = i ;

            String location = databaseHelper.getAllLocations().get(i).getLocation();

            String line2 = location;

            LocationModel fullLine1 = new LocationModel(line1, line2);


            ListViewIdByLocation.add(fullLine1);

        }

        btn_search.setOnClickListener(v -> {
            int duration = 100;
            int offset = 0;
            int position = 0;
            for (int i =0; i< ListViewIdByLocation.size(); i++){
                if (ListViewIdByLocation.get(i).getLocation().equals(locationAutoCompleteTextView.getText().toString())) {
                    position = ListViewIdByLocation.get(i).getLocation_id();
                    break;
                }
            }

            lv_locationLista.smoothScrollToPositionFromTop(position,offset,duration);
        } );

        btn_create.setOnClickListener(v -> {

            LocationModel locationModel;

            if(locationChecker(locationAutoCompleteTextView.getText().toString()).equals("false")){

                Toast.makeText(this, "Location already exists", Toast.LENGTH_SHORT).show();

                return;
            } else if (locationChecker(locationAutoCompleteTextView.getText().toString()).equals("")){

                Toast.makeText(this, "Can not be blank", Toast.LENGTH_SHORT).show();
                return;

            }

            try {
                locationModel = new LocationModel(lv_locationLista.getCount() + 1 , locationAutoCompleteTextView.getText().toString());
                Toast.makeText(MainActivity5.this, "Location '" + locationModel.getLocation() + "' Created", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(MainActivity5.this, "Error creating location", Toast.LENGTH_SHORT).show();
                locationModel = new LocationModel(-1, "error");
            }

            DAO dataBaseHelper = new DAO(MainActivity5.this);

            boolean success = dataBaseHelper.addLocation(locationModel);

            ShowLocationsOnListView(dataBaseHelper);

            LocationModel fullLine1 = new LocationModel(lv_locationLista.getCount(), locationModel.getLocation());

            ListViewIdByLocation.add(fullLine1);

        });



        lv_locationLista.setClickable(true);
     lv_locationLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onClick(View view) {

            }

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String text = String.valueOf(alllocationlv.get(position).getLocation_id());
                String text2 = String.valueOf(databaseHelper.getAllLocations().get(position).getLocation());

                Intent i = new Intent(MainActivity5.this,MainActivity9.class);

                i.putExtra("location",text2.toString());

                startActivity(i);
                }

        });

        lv_locationLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LocationModel selectedLocation = (LocationModel) lv_locationLista.getItemAtPosition(position);
//                Toast.makeText(MainActivity5.this, selectedLocation + "", Toast.LENGTH_SHORT).show();
//
//                StringBuilder sb = new StringBuilder(selectedLocation);
//                sb.delete(10, sb.length()-1);
//                Toast.makeText(MainActivity5.this, sb.length() + "", Toast.LENGTH_SHORT).show();
////                sb.deleteCharAt(9);
//
//                selectedLocation = sb.toString();
//                Toast.makeText(MainActivity5.this, selectedLocation + "", Toast.LENGTH_SHORT).show();



                LocationModel finalSelectedLocation = selectedLocation;
                String finalSelectedLocation1 = selectedLocation.getLocation();
                new AlertDialog.Builder(MainActivity5.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this location")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                databaseHelper.deletelocation(finalSelectedLocation);
                                Toast.makeText(MainActivity5.this,  "Location "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
                                ShowLocationsOnListView(databaseHelper);
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

    private String locationChecker(String location) {
//        Toast.makeText(this, style + "", Toast.LENGTH_SHORT).show();

//        Toast.makeText(this,  databaseHelper.getAllColors().get(0).getColor() + "", Toast.LENGTH_SHORT).show();

        List <String> locationList = new ArrayList<>();

        for (int i = 0; i < databaseHelper.getAllLocations().size(); i++) {
//
            locationList.add(databaseHelper.getAllLocations().get(i).getLocation());
//
        }
        if (!location.isEmpty() && locationList.contains(location)) {
            location = "false";

        }
        if (location.isEmpty()) {
            location = "";
        }
//        Toast.makeText(this, color + "", Toast.LENGTH_SHORT).show();

        return location;
    }

    private void LocationScrollList(){
        ArrayList<LocationModel>  ListViewIdByLocation= new ArrayList<>();
        for (int i = 0; i< databaseHelper.getAllLocations().size(); i++){
            int line1 = i ;

            String location = databaseHelper.getAllLocations().get(i).getLocation();

            String line2 = location;

            LocationModel fullLine1 = new LocationModel(line1, line2);


            ListViewIdByLocation.add(fullLine1);

        }
    }

    private void ShowLocationsOnListView(DAO dataBaseHelper ) {

//        ArrayList  ListViewConverter = new ArrayList<>();
//        for (int i = 0; i< dataBaseHelper.getAllLocations().size(); i++){
//            int location_id = alllocationlv.get(i).getLocation_id();
//
//            String location = dataBaseHelper.getAllLocations().get(i).getLocation().toString();
//            int line1 = Integer.parseInt(String.valueOf(location_id));
//
//            String line2 = location;
//
//            LocationModel fullLine1 = new LocationModel(line1, line2);
//
//            String fullLine = "    ID: " + line1 + " - LOCATION: " + line2;
////            String fullLine = line1 + " - " + " LOCATION: " + line2;
//
//            ListViewConverter.add(fullLine1.getLocation());
//
//        }
        CustomArrayAdapterLocation  customArrayAdapterLocation = new CustomArrayAdapterLocation(getApplicationContext(), (ArrayList<LocationModel>) dataBaseHelper.getAllLocations());





//        CustomArrayAdapterLocation customArrayAdapterLocation = new CustomArrayAdapterLocation<LocationModel>(MainActivity5.this,ret);
        lv_locationLista.setAdapter(customArrayAdapterLocation);
    }
    private void ShowLocationAuto(DAO databaseHelper) {
        locationArrayAdaptera = new ArrayAdapter<>(MainActivity5.this, android.R.layout.simple_list_item_1, alllocation);
        locationAutoCompleteTextView.setAdapter(locationArrayAdaptera);

    }
}