package com.example.usapparelinventoryapp.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.models.PalletModel;

import java.util.ArrayList;

public class CustomAdapterPallet extends ArrayAdapter<PalletModel> {

    public  CustomAdapterPallet(Context context, ArrayList<PalletModel> palletModels){

        super(context, 0, palletModels);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PalletModel palletModel = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_pallet_cell,parent,false);

        TextView pallet_Id = convertView.findViewById(R.id.CPalA_pallet_Id);
        TextView palletlocation = convertView.findViewById(R.id.CPalA_location);


        pallet_Id.setText(Integer.toString(palletModel.setPallet_id()));
        palletlocation.setText(palletModel.setLocation());
        return  convertView;
    }
}
