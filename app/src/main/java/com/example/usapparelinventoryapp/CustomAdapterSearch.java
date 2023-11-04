package com.example.usapparelinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapterSearch extends ArrayAdapter<StyleSearchDTO> {

    public  CustomAdapterSearch(Context context, ArrayList<StyleSearchDTO> styleSearchDTOS){

        super(context, 0, styleSearchDTOS);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        StyleSearchDTO styleSearchDTO = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_search_cell,parent,false);

        TextView styleCode = convertView.findViewById(R.id.CAStyA_code);
        TextView styleColor = convertView.findViewById(R.id.CAStyA_color);
        TextView styleSize = convertView.findViewById(R.id.CAStyA_size);
        TextView Quantity = convertView.findViewById(R.id.CAStyA_quantity);
        TextView Location = convertView.findViewById(R.id.CAStyA_location);



        styleCode.setText(styleSearchDTO.setStyle_code());
        styleColor.setText(styleSearchDTO.setStyle_color());
        styleSize.setText(styleSearchDTO.setStyle_size());
        Quantity.setText((Integer.toString(styleSearchDTO.getQuantity())));
        Location.setText(styleSearchDTO.setPallet_location());



        return  convertView;
    }
}
