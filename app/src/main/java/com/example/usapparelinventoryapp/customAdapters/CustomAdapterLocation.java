package com.example.usapparelinventoryapp.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.models.LocationModel;

import java.util.ArrayList;

public class CustomAdapterLocation extends ArrayAdapter<LocationModel>{

    public CustomAdapterLocation(Context context, ArrayList<LocationModel> locationModels){

        super(context, 0, locationModels);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LocationModel locationModel = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_location_adapter,parent,false);

        TextView location_Id = convertView.findViewById(R.id.CLA_location_Id);
        TextView location = convertView.findViewById(R.id.CLA_location);

        location_Id.setText(Integer.toString(locationModel.setLocation_id()));
        location.setText(locationModel.setLocation());
        return  convertView;
    }
}


