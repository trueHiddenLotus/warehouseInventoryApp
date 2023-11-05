package com.example.usapparelinventoryapp.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.models.SizeModel;

import java.util.ArrayList;

public class CustomAdapterSize extends ArrayAdapter<SizeModel> {

    public  CustomAdapterSize(Context context, ArrayList<SizeModel> sizeModels){

        super(context, 0, sizeModels);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        SizeModel sizeModels = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_size_cell,parent,false);

        TextView size = convertView.findViewById(R.id.CSizA_size);


        size.setText(sizeModels.setSize());
        return  convertView;
    }
}
