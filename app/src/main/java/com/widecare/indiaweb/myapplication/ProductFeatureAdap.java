package com.widecare.indiaweb.myapplication;

/**
 * Created by indiaweb on 11/8/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Constant.RowItems;

public class ProductFeatureAdap extends ArrayAdapter<String> {
    // View lookup cache
    private static class ViewHolder {
         TextView tv_featureItem;
     }

    public ProductFeatureAdap(Context context ,     ArrayList<String>  features) {
        super(context, R.layout.product_features, features);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final String rowItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder holder= new ViewHolder(); // view lookup cache stored in tag
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_features, null);
            holder.tv_featureItem = (TextView) convertView.findViewById(R.id.tv_featureItem);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();



        holder.tv_featureItem.setText(rowItem.toString());
         return convertView;
    }
}
