package com.widecare.indiaweb.myapplication;


import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public class Myadapter extends PagerAdapter {
    private int[] res;
    private Context ctx;

    public Myadapter(FragmentManager fm){
        super(fm);
    }

   /* public Myadapter(Context ctx, int[] res)
    {

        this.ctx=ctx;
        this.res=res;
    }*/
    @Override
    public int getCount() {
        return res.length;
    }

    public Object instantiateItem(View collection, int position) {
        LayoutInflater inflater = (LayoutInflater)collection.getContext
                ().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_list1, null);

        ImageView im=(ImageView) layout.findViewById(R.id.image_gry);
        im.setImageResource(res[position]);

        ((ViewPager) collection).addView(layout, 0);
        return layout;   }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);}

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);}

    @Override
    public Parcelable saveState() {
        return null;
    }
}

