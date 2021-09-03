package Model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.widecare.indiaweb.myapplication.ClaimsAllDetails;
import com.widecare.indiaweb.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by indiaweb on 11/21/2016.
 */
public class ExpandableAdapterClaims extends BaseExpandableListAdapter {

    private List<CatProduct> catList;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context ctx;

    public ExpandableAdapterClaims(List<CatProduct> catList, Context ctx) {

        this.itemLayoutId = R.layout.item_layout;
        this.groupLayoutId = R.layout.group_layout;
        this.catList = catList;
        this.ctx = ctx;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getItemList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getItemList().get(childPosition).hashCode();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_layout, parent, false);
        }

        TextView itemName = (TextView) v.findViewById(R.id.itemNames);
        TextView itemDate = (TextView) v.findViewById(R.id.itemdates);
        TextView itemprice = (TextView) v.findViewById(R.id.itempolicys);
        TextView itemview = (TextView) v.findViewById(R.id.itemviews);

        final ItemDetail det = catList.get(groupPosition).getItemList().get(childPosition);

        itemName.setText(det.getName());
        itemDate.setText(det.getDescr());
        itemprice.setText(det.getPolicynum());

        itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inq = new Intent(ctx, ClaimsAllDetails.class);
                inq.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inq.putExtra("catid",""+det.getId());
                inq.putExtra("order", "" + det.getOrder());
                inq.putExtra("price", "" + det.getPrice());
                inq.putExtra("name", "" + det.getName());
                inq.putExtra("date", "" + det.getDescr());
                inq.putExtra("sdate", "" + det.getStart());
                inq.putExtra("edate", "" + det.getEnd());
                inq.putExtra("policy", "" + det.getPolicynum());
                inq.putExtra("proid", "" + det.getProid());
                inq.putExtra("brand", "" + det.getB());
                inq.putExtra("model", "" + det.getM());
                inq.putExtra("imei", "" + det.getIM());
                inq.putExtra("type", "" + det.getType());
                inq.putExtra("serial", "" + det.getSerial());
                inq.putExtra("priceE", "" + det.getP());
                inq.putExtra("dateE", "" + det.getD());

                inq.putExtra("age", "" + det.getAge());
                inq.putExtra("sex", "" + det.getSex());
                inq.putExtra("dob", "" + det.getDob());
                inq.putExtra("pan", "" + det.getPan());
                inq.putExtra("color", "" + det.getColor());

                Log.e("prooooo id ",""+det.getProid());

                ctx.startActivity(inq);
            }
        });

        return v;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = catList.get(groupPosition).getItemList().size();
        System.out.println("Child for group ["+groupPosition+"] is ["+size+"]");
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return catList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return catList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return catList.get(groupPosition).hashCode();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.group_layout, parent, false);
        }

        TextView groupName = (TextView) v.findViewById(R.id.groupName);
        ImageView groupDescr = (ImageView) v.findViewById(R.id.groupDescr);


        CatProduct cat = catList.get(groupPosition);

        groupName.setText(cat.getName());

        Picasso.with(ctx).load(cat.getDescr()).into(groupDescr);

        return v;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

