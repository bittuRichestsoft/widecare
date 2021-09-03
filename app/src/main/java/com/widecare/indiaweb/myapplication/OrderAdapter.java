package com.widecare.indiaweb.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import Constant.MyDataBases;
import Constant.UserDataItems;

/**
 * Created by indiaweb on 10/6/2016.
 */
public class OrderAdapter extends ArrayAdapter<UserDataItems> {
    Context ctx;
    MyDataBases myDataBase = new MyDataBases(getContext());
    private static class ViewHolder {
        TextView price,num,view,date,quant,status;
        LinearLayout linearLayout;

    }

    public OrderAdapter(Context context, int ordered_itemview, ArrayList<UserDataItems> users) {
        super(context, R.layout.ordered_itemview, users);
    }

    @SuppressLint("WrongViewCast")
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
            convertView = inflater.inflate(R.layout.ordered_itemview, parent, false);
            viewHolder.num = (TextView) convertView.findViewById(R.id.ordernum);
            viewHolder.price = (TextView) convertView.findViewById(R.id.orderprice);
            viewHolder.date = (TextView) convertView.findViewById(R.id.orderdate);
            viewHolder.quant = (TextView) convertView.findViewById(R.id.orderquant);
            viewHolder.view = (TextView) convertView.findViewById(R.id.viewbtn);
            viewHolder.status = (TextView)convertView.findViewById(R.id.status);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.lift);

            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.price.setText("Rs. "+user.getOpricer());
        viewHolder.num.setText("Order No #"+user.getOnum());
        viewHolder.date.setText(user.getDate());
        viewHolder.quant.setText("for "+user.getOitem()+" item");
        viewHolder.status.setText("Order is "+user.getStatus());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
            Intent inq = new Intent (getContext(),DetailActivity.class);
                inq.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 inq.putExtra("order", "" + user.getOnum());
                 inq.putExtra("status",""+user.getStatus());
                getContext().startActivity(inq);
            }
        });
        return convertView;
    }
}
