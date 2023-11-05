package com.example.usapparelinventoryapp.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

import com.example.usapparelinventoryapp.customAdapters.CustomAdapterLocation;
import com.example.usapparelinventoryapp.dataBase.DataBaseHelper;
import com.example.usapparelinventoryapp.models.LocationModel;
import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class CreateLocationsScreen extends AppCompatActivity {
    ActivityMainBinding binding;

    Button btn_create, btn_search;
    AutoCompleteTextView locationAutoCompleteTextView ;
    ListView lv_locationLista;
    private ArrayList<String> alllocation;
    private List<LocationModel> alllocationlv;
    ArrayAdapter locationArrayAdaptera;
    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        locationAutoCompleteTextView = findViewById(R.id.locationAutoCompleteTextView);
        btn_create = findViewById(R.id.btn_create_location);
        btn_search = findViewById(R.id.btn_search);
        lv_locationLista = findViewById(R.id.lv_locationLista);
        databaseHelper = new DataBaseHelper( CreateLocationsScreen.this);
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
                Toast.makeText(CreateLocationsScreen.this, "Location '" + locationModel.getLocation() + "' Created", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(CreateLocationsScreen.this, "Error creating location", Toast.LENGTH_SHORT).show();
                locationModel = new LocationModel(-1, "error");
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(CreateLocationsScreen.this);

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
                String text2 = String.valueOf(databaseHelper.getAllLocations().get(position).getLocation());

                Intent i = new Intent(CreateLocationsScreen.this, PalletViewScreen.class);

                i.putExtra("location",text2.toString());

                startActivity(i);
                }

        });

        lv_locationLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LocationModel selectedLocation = (LocationModel) lv_locationLista.getItemAtPosition(position);

                LocationModel finalSelectedLocation = selectedLocation;
                String finalSelectedLocation1 = selectedLocation.getLocation();
                new AlertDialog.Builder(CreateLocationsScreen.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this location")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                databaseHelper.deletelocation(finalSelectedLocation);
                                Toast.makeText(CreateLocationsScreen.this,  "Location "+ finalSelectedLocation1 +" Deleted", Toast.LENGTH_SHORT).show();
                                ShowLocationsOnListView(databaseHelper);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

            }

        });


    }

    private String locationChecker(String location) {

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

    private void ShowLocationsOnListView(DataBaseHelper dataBaseHelper ) {
        CustomAdapterLocation customAdapterLocation = new CustomAdapterLocation(getApplicationContext(), (ArrayList<LocationModel>) dataBaseHelper.getAllLocations());
        lv_locationLista.setAdapter(customAdapterLocation);
    }

    private void ShowLocationAuto(DataBaseHelper databaseHelper) {
        locationArrayAdaptera = new ArrayAdapter<>(CreateLocationsScreen.this, android.R.layout.simple_list_item_1, alllocation);
        locationAutoCompleteTextView.setAdapter(locationArrayAdaptera);

    }
}