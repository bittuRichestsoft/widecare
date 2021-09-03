package adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.widecare.indiaweb.myapplication.CalculatePriceScreen;
import com.widecare.indiaweb.myapplication.Products;
import com.widecare.indiaweb.myapplication.R;

import java.util.ArrayList;

import Constant.UserDataItems;

public class SubCategoryAdapter extends ArrayAdapter<UserDataItems> {
    // View lookup cache
    String  TAG="SubCategoryAdapter ";
    private static class ViewHolder {
        TextView product_title,product_des;
        ImageView product_image;
        LinearLayout lean1;
    }

    public SubCategoryAdapter(Context context, int products, ArrayList<UserDataItems> users) {
        super(context, R.layout.products, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final UserDataItems user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.products, parent, false);
            viewHolder.product_title = (TextView) convertView.findViewById(R.id.product_title);
            viewHolder.product_des = (TextView) convertView.findViewById(R.id.product_des);
            viewHolder.product_image = (ImageView) convertView.findViewById(R.id.product_image);
            viewHolder.lean1 = (LinearLayout) convertView.findViewById(R.id.lean1);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object

       String desc =  Html.fromHtml(user.getPdes()).toString();

        viewHolder.product_title.setText(user.getPname());
        viewHolder.product_des.setText(desc);

        Picasso.with(getContext()).load(user.getPimage())
                .placeholder(R.drawable.loader)
                .error(R.drawable.loader).into(viewHolder.product_image);

    //    Picasso.with(getContext()).load(user.getPimage()).into(viewHolder.product_image);

        viewHolder.lean1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

   //      Intent i = new Intent(getContext(), Products.class);
             Intent i = new Intent(getContext(), CalculatePriceScreen.class);
                i.putExtra("catid", "" + user.getCatId());
                i.putExtra("cname", "" + user.getName());
                i.putExtra("cimage", "" + user.getImage());
                i.putExtra("proid", "" + user.getpId());
                i.putExtra("pname", "" + user.getPname());
                i.putExtra("pdes", "" + user.getHtmlPdes());
                i.putExtra("pimage", "" + user.getPimage());

                Log.e(TAG+" choosedSubCat ", "proid=" + user.getpId() + "| pname=" + user.getPname() + "| pdes=" + user.getHtmlPdes() + "| pimage=" + user.getPimage());
                getContext().startActivity(i);

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
