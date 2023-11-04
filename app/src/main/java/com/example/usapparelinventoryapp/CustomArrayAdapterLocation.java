package com.example.usapparelinventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomArrayAdapterLocation extends ArrayAdapter<LocationModel>{

    public  CustomArrayAdapterLocation(Context context, ArrayList<LocationModel> locationModels){

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
//    private ArrayList<LocationModel> dataSet;
//    Context mContext;
//    Activity mActivity;
//
//    public CustomArrayAdapterLocation(@NonNull Context context, List<LocationModel> alllocationlv) {
//        super(context, R.layout.custom_location_adapter, alllocationlv);
//    }

//    // View lookup cache
//    private static class ViewHolder {
//        TextView txtlocation_id;
//        TextView txtlocation;
//
//    }
//
//    public CustomArrayAdapterLocation(ArrayList<LocationModel> data, Context context) {
//        super(context, R.layout.custom_location_adapter, data);
//        this.dataSet = data;
//        this.mContext=context;
//
//    }



//    private int lastPosition = -1;

//    @NonNull
//    @Override
//    View oneLIne;
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        // Get the data item for this position
//        LocationModel locationModel = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//        RecyclerView.ViewHolder viewHolder; // view lookup cache stored in tag
//
//
//
//        if (convertView == null) {
//
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_location_adapter,parent,false);
//
////            viewHolder = new ViewHolder();
////            LayoutInflater inflater = LayoutInflater.from(getContext());
////            convertView = inflater.inflate(R.layout.custom_location_adapter, parent, false);
////            viewHolder.txtlocation_id = (TextView) convertView.findViewById(R.id.CLA_location_Id);
////            viewHolder.txtlocation = (TextView) convertView.findViewById(R.id.CLA_location);
//////
////
////            convertView.setTag(viewHolder);
//        }
//
//         LayoutInflater inflater = (LayoutInflater) mAct R.layout.custom_location_adapter,parent,false);
////        TextView location_Id = convertView.findViewById(R.id.CLA_location_Id);
////        TextView location = convertView.findViewById(R.id.CLA_location);
//
//
//
//       location_Id.setText(Integer.toString(locationModel.setLocation_id()));
//       location.setText(locationModel.setLocation().toString());
////        viewHolder.info.setTag(position);
//        // Return the completed view to render on screen
//        return super.getView(position, convertView, parent);
//    }
}


