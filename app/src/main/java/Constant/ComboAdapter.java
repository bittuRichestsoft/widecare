package Constant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.widecare.indiaweb.myapplication.Products;
import com.widecare.indiaweb.myapplication.R;

import java.util.ArrayList;

import Model.Combo;

/**
 * Created by indiaweb on 11/25/2016.
 */
public class ComboAdapter extends ArrayAdapter<Combo> {
    Context ctx;
    MyDataBases myDataBase = new MyDataBases(getContext());
    String img,pid;

    private static class ViewHolder {
        TextView text;
        ImageView imageView;
        RelativeLayout relativelayout;

    }

    public ComboAdapter(Context context,  ArrayList<Combo> users) {
        super(context, R.layout.listview_row, users);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Combo combo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder holder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_row, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.textcombo);
            holder.imageView=(ImageView)convertView.findViewById(R.id.imagecombo);
            holder.relativelayout= (RelativeLayout) convertView.findViewById(R.id.relativelayout);

            convertView.setTag(holder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.text.setText(combo.getName());

        Log.e("discription ", "" + position);

        Picasso.with(getContext()).load(combo.getImage()).into(holder.imageView);


//        if(position==0)
//        {
//            Picasso.with(getContext()).load("http://widecare.in/wp-content/uploads/2016/05/Insurance1.jpg").into(holder.imageView);
//            img = "http://widecare.in/wp-content/uploads/2016/05/Insurance1.jpg";
//            pid = "1235";
//        }
//        if(position==1)
//        {
//            Picasso.with(getContext()).load("http://widecare.in/wp-content/uploads/2016/08/gizmo.jpg").into(holder.imageView);
//            img = "http://widecare.in/wp-content/uploads/2016/08/gizmo.jpg";
//            pid = "1237";
//        }
//        if(position==2)
//        {
//            Picasso.with(getContext()).load("http://widecare.in/wp-content/uploads/2016/08/smashed-iphone.jpg").into(holder.imageView);
//            img = "http://widecare.in/wp-content/uploads/2016/08/smashed-iphone.jpg";
//            pid = "1239";
//        }
//        if(position==3)
//        {
//            Picasso.with(getContext()).load("http://widecare.in/wp-content/uploads/2016/08/assure.jpg").into(holder.imageView);
//            img = "http://widecare.in/wp-content/uploads/2016/08/assure.jpg";
//            pid = "1241";
//        }
//        if(position==4)
//        {
//            Picasso.with(getContext()).load("http://widecare.in/wp-content/uploads/2016/05/Insurance3.jpg").into(holder.imageView);
//            img = "http://widecare.in/wp-content/uploads/2016/05/Insurance3.jpg";
//            pid = "1242";
//        }
//        if(position==5)
//        {
//            Picasso.with(getContext()).load("http://widecare.in/wp-content/uploads/2016/05/Insurance2.jpg").into(holder.imageView);
//            img = "http://widecare.in/wp-content/uploads/2016/05/Insurance2.jpg";
//            pid = "1243";
//        }


        holder.relativelayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Products.class);
                i.putExtra("value", "3");
                // value is page of navigation

              //  i.putExtra("proid", ""+pid);
                i.putExtra("proid", combo.getIdd());
                i.putExtra("pname",combo.getName());
                i.putExtra("pdes", "");
                i.putExtra("pimage", combo.getImage());
                getContext().startActivity(i);

                Log.e("product discription ",""+combo.getIdd()+"---"+combo.getName()+"---"+combo.getImage());

            }
        });

        return convertView;
    }


}
