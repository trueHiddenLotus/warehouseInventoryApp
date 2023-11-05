package com.example.usapparelinventoryapp.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.models.ColorModel;

import java.util.ArrayList;

public class CustomAdapterColor extends ArrayAdapter<ColorModel> {


    public  CustomAdapterColor(Context context, ArrayList<ColorModel> colorModels){

            super(context, 0, colorModels);
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ColorModel colorModel = getItem(position);
            if(convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter_color_cell,parent,false);

            TextView color = convertView.findViewById(R.id.CColA_color);


            color.setText(colorModel.setColor());
            return  convertView;
        }
    }
