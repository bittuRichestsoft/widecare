package Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.widecare.indiaweb.myapplication.R;


/**
 * Created by indiaweb on 11/22/2016.
 */
public class LazyImageLoadAdapter extends BaseAdapter {

    private Activity activity;
    private String[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyImageLoadAdapter(Activity a, String[] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView text;
        public ImageView image;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.listview_row, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.textcombo);
            holder.image=(ImageView)vi.findViewById(R.id.imagecombo);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();


        if(position==0)
        {
            holder.text.setText("Simplify Care");
        }
        if(position==1)
        {
            holder.text.setText("Gizmo Secure (1 Year)");
        }
        if(position==2)
        {
            holder.text.setText("Gimmich Care");
        }
        if(position==3)
        {
            holder.text.setText("Always Assure");
        }
        if(position==4)
        {
            holder.text.setText("Travel Delight Pack");
        }
        if(position==5)
        {
            holder.text.setText("Health Value Pack");
        }


        ImageView image = holder.image;

        //DisplayImage function from ImageLoader Class
   //     imageLoader.DisplayImage(data[position], loader, image);

        /******** Set Item Click Listner for LayoutInflater for each row ***********/

        return vi;
    }

}