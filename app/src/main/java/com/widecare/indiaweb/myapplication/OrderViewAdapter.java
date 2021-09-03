package com.widecare.indiaweb.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Constant.MyDataBases;
import Constant.UserDataItems;

/**
 * Created by indiaweb on 10/6/2016.
 */
public class OrderViewAdapter extends ArrayAdapter<UserDataItems> {
    // View lookup cache
    MyDataBases myDataBase = new MyDataBases(getContext());
    private static class ViewHolder
    {
        TextView name,quan,price;
        ImageView imageView2;

    }

    public OrderViewAdapter(Context context, int orderdetailview, ArrayList<UserDataItems> users) {
        super(context, R.layout.orderdetailview, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final UserDataItems user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.orderdetailview, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.itemname);
            viewHolder.quan = (TextView) convertView.findViewById(R.id.itemquantity);
            viewHolder.price = (TextView) convertView.findViewById(R.id.itemprice);
            viewHolder.imageView2 = (ImageView) convertView.findViewById(R.id.imagedetails);

            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(user.getPpname());
        viewHolder.quan.setText(user.getPpquantity());
        viewHolder.price.setText(user.getPprice());

        Log.e("myresultttss ", "" + user.getPpimage());
        Picasso.with(getContext()).load(user.getPpimage()).into(viewHolder.imageView2);

        return convertView;
    }
}