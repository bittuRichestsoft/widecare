package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.widecare.indiaweb.myapplication.SubCategory;
import com.widecare.indiaweb.myapplication.R;

import java.util.List;

import Constant.RowItem;

public class CategoryAdapter extends ArrayAdapter<RowItem> {

    Context context;
    String TAG="CategoryAdapter ";
    List<RowItem> items=null;
    public CategoryAdapter(Context context, List<RowItem> items) {
        super(context,0,items);
        this.context = context;
        this.items = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        LinearLayout linearLayout;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final RowItem rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.program_list, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textcat);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imagecat);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linear);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        StringBuilder sb = new StringBuilder(rowItem.getTitle());

        int i = 0;
        while ((i = sb.indexOf(" ", i + 10)) != -1)
        {
            sb.replace(i, i + 1, "\n");
        }
String[] str=sb.toString().split(" ");
       // if(sb.toString().length()>)
String strWithSpace="";
        for(int j=0;j<str.length;j++)
        {
            strWithSpace=strWithSpace+" "+str[j];
try {
    if(j==str.length-2)
    {
        strWithSpace=strWithSpace+"\n";
    }
}
catch (Exception exp)
{

}
        }
        holder.txtTitle.setText(/*sb*/strWithSpace);

        Log.e(TAG,"text="+sb+"<<>>getImage="+rowItem.getImageId());

        // Loader image - will be shown before loading image

      //  int loader = R.drawable.loader;
      //  ImageLoader imgLoader = new ImageLoader(getContext());

       // imgLoader.DisplayImage(rowItem.getImageId(), loader, holder.imageView);

        Picasso.with(getContext()).load(rowItem.getImageId())
                .placeholder(R.drawable.loader)
                .error(R.drawable.loader).into(holder.imageView);
/*
Log.e(TAG,"rowItem.getCatId="+rowItem.getCatId());
if(rowItem.getCatId().equals("1") */
/*position==0*//*
)
{
    Log.e(TAG,"rowItem.getCatId1="+rowItem.getCatId());
    holder.imageView.setImageResource(R.drawable.first_cat);
}
        if(rowItem.getCatId().equals("2")*/
/*position==1*//*
)
        {
            Log.e(TAG,"rowItem.getCatId2="+rowItem.getCatId());
            holder.imageView.setImageResource(R.drawable.second_cat);
        }
        if(rowItem.getCatId().equals("3")*/
/*position==2*//*
)
        {
            holder.imageView.setImageResource(R.drawable.third_cat);
        }
        if(rowItem.getCatId().equals("4")*/
/*position==3*//*
)
        {
            holder.imageView.setImageResource(R.drawable.fourth_cat);
        }
        if(rowItem.getCatId().equals("5")*/
/*position==4*//*
)
        {
            holder.imageView.setImageResource(R.drawable.fifth_cat);
        }
        if(rowItem.getCatId().equals("6")*/
/*position==5*//*
)
        {
            holder.imageView.setImageResource(R.drawable.sixth_cat);
        }
*/
     //   Picasso.with(getContext()).load(rowItem.getImageId()).into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SubCategory.class);
                i.putExtra("catId", "" + rowItem.getCatId());
                i.putExtra("cname", "" + rowItem.getTitle());
                i.putExtra("cimage", "" + rowItem.getImageId());
                getContext().startActivity(i);
            }
        });

        return convertView;
    }
}