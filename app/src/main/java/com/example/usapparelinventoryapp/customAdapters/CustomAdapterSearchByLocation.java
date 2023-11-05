package com.example.usapparelinventoryapp.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.dto.StyleSearchByLocationDTO;

import java.util.ArrayList;

public class CustomAdapterSearchByLocation extends ArrayAdapter<StyleSearchByLocationDTO> {

    public  CustomAdapterSearchByLocation(Context context, ArrayList<StyleSearchByLocationDTO> styleSearchByLocationDTOS){

        super(context, 0, styleSearchByLocationDTOS);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        StyleSearchByLocationDTO styleSearchByLocationDTO = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_search_by_location_cell,parent,false);

        TextView styleCode = convertView.findViewById(R.id.CAStyAByLoc_code);
        TextView styleColor = convertView.findViewById(R.id.CAStyAByLoc_color);
        TextView styleSize = convertView.findViewById(R.id.CAStyAByLoc_size);
        TextView Quantity = convertView.findViewById(R.id.CAStyAByLoc_quantity);
        TextView Location = convertView.findViewById(R.id.CAStyAByLoc_location);
        TextView palletId = convertView.findViewById(R.id.CAStyAByLoc_pallet_id);




        styleCode.setText(styleSearchByLocationDTO.setStyle_code());
        styleColor.setText(styleSearchByLocationDTO.setStyle_color());
        styleSize.setText(styleSearchByLocationDTO.setStyle_size());
        Quantity.setText((Integer.toString(styleSearchByLocationDTO.getQuantity())));
        Location.setText(styleSearchByLocationDTO.setPallet_location());
        palletId.setText((Integer.toString(styleSearchByLocationDTO.getStyle_pallet_id())));




        return  convertView;
    }
}
