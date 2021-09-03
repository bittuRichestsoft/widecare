package com.widecare.indiaweb.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by indiaweb on 9/15/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public PagerAdapter(FragmentManager manager) {
        super(manager);
        //...
    }
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            case 1:
                TabFragment1 tab2 = new TabFragment1();
                return tab2;
            case 2:
                TabFragment1 tab3 = new TabFragment1();
                return tab3;
            case 3:
                TabFragment1 tab4 = new TabFragment1();
                return tab4;
            case 4:
                TabFragment1 tab5 = new TabFragment1();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}