package com.example.usapparelinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class CustomerAdapterStyle extends ArrayAdapter<StyleModel> {


    public  CustomerAdapterStyle(Context context, ArrayList<StyleModel> styleModels){

            super(context, 0, styleModels);
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            StyleModel styleModels = getItem(position);
            if(convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_style_cell,parent,false);

            TextView style = convertView.findViewById(R.id.CStyA_style);


            style.setText(styleModels.setStyle());
            return  convertView;
        }
    }

