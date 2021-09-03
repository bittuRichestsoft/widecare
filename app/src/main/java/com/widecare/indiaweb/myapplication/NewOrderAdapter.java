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
import Constant.User;

/**
 * Created by indiaweb on 10/6/2016.
 */
public class NewOrderAdapter extends ArrayAdapter<User> {
    Context ctx;
    MyDataBases myDataBase = new MyDataBases(getContext());
    private static class ViewHolder {
        TextView price,num,view,date,policy,name;
        LinearLayout linearLayout;

    }

    public NewOrderAdapter(Context context, int ordered_itemview, ArrayList<User> users) {
        super(context, R.layout.order_detail_list, users);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_detail_list, parent, false);
            viewHolder.num = (TextView) convertView.findViewById(R.id.neworder);
            viewHolder.price = (TextView) convertView.findViewById(R.id.newprice);
            viewHolder.date = (TextView) convertView.findViewById(R.id.newdate);
            viewHolder.policy = (TextView) convertView.findViewById(R.id.newpolicy);
            viewHolder.name = (TextView) convertView.findViewById(R.id.newname);
            viewHolder.view = (TextView) convertView.findViewById(R.id.newview);
            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.lifting);

            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.price.setText("₹ "+user.getProduct());
        viewHolder.num.setText(user.getOrdernum());
        viewHolder.date.setText(user.getDates());
        viewHolder.policy.setText(user.getPolicys());
        viewHolder.name.setText(user.getNamy());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent inq = new Intent (getContext(),PolicyAllDetails.class);
                inq.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inq.putExtra("order", "" + user.getOrdernum());
                inq.putExtra("price",""+user.getProduct());
                inq.putExtra("name",""+user.getNamy());
                getContext().startActivity(inq);
            }
        });
        return convertView;
    }
}
