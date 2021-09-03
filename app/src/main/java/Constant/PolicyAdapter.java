package Constant;

/**
 * Created by indiaweb on 10/28/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.widecare.indiaweb.myapplication.R;

import java.util.ArrayList;

public class PolicyAdapter extends ArrayAdapter<UserDataItems> {
    // View lookup cache
    MyDataBases myDataBase = new MyDataBases(getContext());
    private static class ViewHolder
    {
        TextView name,quan,price,imageView2;

    }

    public PolicyAdapter(Context context, int orderdetailview, ArrayList<UserDataItems> users) {
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
            convertView = inflater.inflate(R.layout.allpolicy, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.t1);
            viewHolder.quan = (TextView) convertView.findViewById(R.id.t2);
            viewHolder.price = (TextView) convertView.findViewById(R.id.t3);
            viewHolder.imageView2 = (TextView) convertView.findViewById(R.id.t4);

            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(user.getPolicyname());
        viewHolder.quan.setText(user.getPolicyorder());
        viewHolder.price.setText(user.getPolicydate());

        return convertView;
    }
}
