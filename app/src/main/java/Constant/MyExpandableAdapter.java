package Constant;

/**
 * Created by indiaweb on 11/15/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.widecare.indiaweb.myapplication.MyPolicies;
import com.widecare.indiaweb.myapplication.R;

import java.util.ArrayList;


public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private MyPolicies activity;
    private ArrayList<Object> childtems;
    private LayoutInflater inflater;
    private ArrayList<String> parentItems;
    private ArrayList<Object> child;
    Context context;

    public MyExpandableAdapter(ArrayList<String> parents, ArrayList<Object> childern) {
        this.parentItems = parents;
        this.childtems = childern;
    }

    public void setInflater(LayoutInflater inflater, MyPolicies activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        LinearLayout linearLayout;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        child = (ArrayList<Object>) childtems.get(groupPosition);


        TextView textView = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group, null);
        }

        textView = (TextView) convertView.findViewById(R.id.textView1);
        //   textView.setText(child.get(childPosition));

        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
//                Toast.makeText(activity, child.get(childPosition),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.row, null);
//        }
//
//       // ((CheckedTextView) convertView).setText(parentItems.get(groupPosition));
//        ((CheckedTextView) convertView).setChecked(isExpanded);
//
//        return convertView;

        RowItem rowItem = (RowItem) childtems.get(groupPosition);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder holder = new ViewHolder(); // view lookup cache stored in tag
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.buycategory, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.buytextcat);
            holder.imageView = (ImageView) convertView.findViewById(R.id.buyimagecat);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.buylinear);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.txtTitle.setText(rowItem.getTitle());
        Picasso.with(context).load(rowItem.getImageId()).into(holder.imageView);
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return  convertView;
    }


    @Override
    public RowItem getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<Object>) childtems.get(groupPosition)).size();
    }

    @Override
    public RowItems getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
