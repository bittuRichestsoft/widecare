package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.widecare.indiaweb.myapplication.NavigationActivity;
import com.widecare.indiaweb.myapplication.R;

import java.util.ArrayList;

import Constant.MyDataBase;
import Constant.MyDataBases;
import Constant.UserDataItems;

/**
 * Created by indiaweb on 9/29/2016.
 */
public class CartAdapter extends ArrayAdapter<UserDataItems> {
    ArrayList<UserDataItems> lista;
    Context contexts;
    ListView orderlist;
    // View lookup cache
    MyDataBases myDataBase = new MyDataBases(getContext());
    MyDataBase myDataBas = new MyDataBase(getContext());
    private static class ViewHolder {
        TextView name,total,price;
        ImageView cross,imgg;
        LinearLayout all;
        int program_list;

    }

    public CartAdapter(Context context, int cart_single_order,ListView orderlist, ArrayList<UserDataItems> users) {
        super(context, R.layout.cart_single_order, users);
        this.contexts = context;
        this.lista= users;
        this.orderlist= orderlist;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lista.size();
    }

    @Override
    public UserDataItems getItem(int arg0) {
        // TODO Auto-generated method stub
        return lista.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final UserDataItems user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cart_single_order, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.ppname);
            //  viewHolder.quantity = (TextView) convertView.findViewById(R.id.ppquantity);
            viewHolder.price = (TextView) convertView.findViewById(R.id.ppprice);
            // Cache the viewHolder object inside the fresh view
            viewHolder.total = (TextView) convertView.findViewById(R.id.pptotal);
            viewHolder.cross = (ImageView) convertView.findViewById(R.id.cross);
            viewHolder.cross.setTag(position);
            viewHolder.imgg = (ImageView) convertView.findViewById(R.id.imgg);
            viewHolder.all = (LinearLayout) convertView.findViewById(R.id.all);
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(user.getPpname());
        //  viewHolder.quantity.setText(user.getPpquantity());
        viewHolder.price.setText("Rs. "+user.getPprice());
        viewHolder.total.setText("Rs. "+user.getPprice());

        Bitmap bitmap =  user.getPppimage();//BitmapFactory.decodeByteArray(user.getPpimage(), 0, user.getPpimage().length);
        viewHolder.imgg.setImageBitmap(bitmap);
        Log.e("inserteddd123 image", "" + bitmap);

        // Return the completed view to render on screen
        viewHolder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String PRODUCT_ID = user.getPpVId();

                Log.e("pidddddddddddd_PR_del", "" + PRODUCT_ID);

//                orderlist.removeViewAt(position);

                myDataBase.delrow(PRODUCT_ID);
                myDataBas.delrow(PRODUCT_ID);

                Intent i = new Intent(getContext(),NavigationActivity.class);
                i.putExtra("pager", "4");
                getContext().startActivity(i);



              //  parent


          /*      viewHolder.all.setVisibility(View.GONE);
                myDataBase.delrow(user.getPpVId());
                int pos = (int)arg0.getTag();
                lista.remove(pos);
              //  CartAdapter.this.notify();
                CartAdapter.this.notifyDataSetChanged();
                Intent i = new Intent(getContext(),NavigationActivity.class);
                i.putExtra("pager", "4");
                getContext().startActivity(i);*/

            }
        });
        return convertView;
    }
}
