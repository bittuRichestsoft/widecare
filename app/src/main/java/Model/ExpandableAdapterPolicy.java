/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import com.squareup.picasso.Picasso;
import com.widecare.indiaweb.myapplication.PolicyAllDetails;
import com.widecare.indiaweb.myapplication.R;

import java.util.List;

public class ExpandableAdapterPolicy extends BaseExpandableListAdapter {
String TAG="ExpandableAdapterPolicy ";
	private List<CatProducts> catList;
	private int itemLayoutId;
	private int groupLayoutId;
	private Context ctx;
	
	public ExpandableAdapterPolicy(List<CatProducts> catList, Context ctx) {
		this.itemLayoutId = R.layout.item_layouts;
		this.groupLayoutId = R.layout.group_layouts;
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
			v = inflater.inflate(R.layout.item_layouts, parent, false);
		}
		
		TextView itemName = (TextView) v.findViewById(R.id.itemName);
		TextView itemDate = (TextView) v.findViewById(R.id.itemdate);
		TextView itemprice = (TextView) v.findViewById(R.id.itempolicy);
		TextView itemview = (TextView) v.findViewById(R.id.itemview);

		final ItemDetails det = catList.get(groupPosition).getItemList().get(childPosition);
		
		itemName.setText(det.getName());
		itemDate.setText(det.getDescr());
		itemprice.setText(det.getPolicynum());



		itemview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Log.e("valueett ", "" + det.getName() + "==" + det.getPrice());
				Log.e(TAG+" PolicyDetails", "getDob()=" + det.getDob()+ "|" + "getD()="+det.getD()+"|getEnd()="+det.getEnd()+"|getStart()="+det.getStart());

				Intent inq = new Intent(ctx, PolicyAllDetails.class);
				inq.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				inq.putExtra("catid", "" + det.getId());
				inq.putExtra("order", "" + det.getOrder());
				inq.putExtra("price", "" + det.getPrice());
				inq.putExtra("name", "" + det.getName());
				inq.putExtra("date", "" + det.getDescr());
				inq.putExtra("policy", "" + det.getPolicynum());
				inq.putExtra("sdate", "" + det.getStart());
				inq.putExtra("edate", "" + det.getEnd());
				inq.putExtra("proid", "" + det.getPrid());
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
			v = inflater.inflate(R.layout.group_layouts, parent, false);
		}

		TextView groupName = (TextView) v.findViewById(R.id.groupNames);
		ImageView groupDescr = (ImageView) v.findViewById(R.id.groupDescrs);


		CatProducts cat = catList.get(groupPosition);

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
