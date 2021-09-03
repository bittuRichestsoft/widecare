package com.widecare.indiaweb.myapplication;

import android.content.Intent;
import android.os.Bundle;
/*import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;*/
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import Constant.Shaved_shared_preferences;

public class IntimateClaimActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
        ScreenSlidePagerAdapter /*PagerAdapter*/ mPagerAdapter;
        ViewPager mPager;
        View v0, v1,v2,v3;
        TextView on0,on1,on2,on3,off0,off1,off2,off3;
    TextView inimatecatname,inimateproname;
    String catnamee ,proname;
    Shaved_shared_preferences shaved_shared_preferences;
    int l=0;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setIcon(R.drawable.widecare);
        }
    shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());

    catnamee = shaved_shared_preferences.get_catname();
    proname = shaved_shared_preferences.get_proname();

    v0 = (View) findViewById(R.id.v0);
    v1 = (View) findViewById(R.id.v1);
    v2 = (View) findViewById(R.id.v2);
    v3 = (View) findViewById(R.id.v3);

    on0 = (TextView) findViewById(R.id.on0);
    on1 = (TextView) findViewById(R.id.on1);
    on2 = (TextView) findViewById(R.id.on2);
    on3 = (TextView) findViewById(R.id.on3);

    off0 = (TextView) findViewById(R.id.off0);
    off1 = (TextView) findViewById(R.id.off1);
    off2 = (TextView) findViewById(R.id.off2);
    off3 = (TextView) findViewById(R.id.off3);

    inimatecatname = (TextView)findViewById(R.id.inimatecatname);
    inimateproname = (TextView)findViewById(R.id.inimateproname);

        mPager = (ViewPager) findViewById(R.id.pagers);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);



    Intent i = getIntent();
    if(i.getStringExtra("pager")!=null)
    {

        String k = i.getStringExtra("pager");
        l = Integer.parseInt(k);
        Log.e("kkk", "" + l);


        if(k.equals("1"))
        {
            v0.setVisibility(View.GONE);
            v1.setVisibility(View.GONE);
            v2.setVisibility(View.GONE);

            off0.setVisibility(View.VISIBLE);
            off1.setVisibility(View.GONE);
            off2.setVisibility(View.VISIBLE);
            off3.setVisibility(View.VISIBLE);

            on0.setVisibility(View.GONE);
            on1.setVisibility(View.VISIBLE);
            on2.setVisibility(View.GONE);
            on3.setVisibility(View.GONE);

            mPager.setCurrentItem(1);
        }
        if(k.equals("2"))
    {
        v0.setVisibility(View.VISIBLE);
        v1.setVisibility(View.GONE);
        v2.setVisibility(View.GONE);

        off0.setVisibility(View.VISIBLE);
        off1.setVisibility(View.VISIBLE);
        off2.setVisibility(View.GONE);
        off3.setVisibility(View.VISIBLE);

        on0.setVisibility(View.GONE);
        on1.setVisibility(View.GONE);
        on2.setVisibility(View.VISIBLE);
        on3.setVisibility(View.GONE);

        mPager.setCurrentItem(2);
    }
            if (k.equals("3")) {
                v0.setVisibility(View.VISIBLE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);

                off0.setVisibility(View.VISIBLE);
                off1.setVisibility(View.VISIBLE);
                off2.setVisibility(View.VISIBLE);
                off3.setVisibility(View.GONE);

                on0.setVisibility(View.GONE);
                on1.setVisibility(View.GONE);
                on2.setVisibility(View.GONE);
                on3.setVisibility(View.VISIBLE);

                mPager.setCurrentItem(3);
            }

    }

        mPager.setCurrentItem(l);


    off0.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(0);

            v0.setVisibility(View.GONE);
            v1.setVisibility(View.VISIBLE);
            v2.setVisibility(View.VISIBLE);

            off0.setVisibility(View.GONE);
            off1.setVisibility(View.VISIBLE);
            off2.setVisibility(View.VISIBLE);
            off3.setVisibility(View.VISIBLE);

            on0.setVisibility(View.VISIBLE);
            on1.setVisibility(View.GONE);
            on2.setVisibility(View.GONE);
            on3.setVisibility(View.GONE);

            mPager.setCurrentItem(0);
        }
    });

    inimateproname.setText(proname);
    inimatecatname.setText(catnamee);

    mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        public void onPageScrollStateChanged(int state) {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {


            if(position==0)
            {
                v0.setVisibility(View.GONE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.VISIBLE);

                off0.setVisibility(View.GONE);
                off1.setVisibility(View.VISIBLE);
                off2.setVisibility(View.VISIBLE);
                off3.setVisibility(View.VISIBLE);

                on0.setVisibility(View.VISIBLE);
                on1.setVisibility(View.GONE);
                on2.setVisibility(View.GONE);
                on3.setVisibility(View.GONE);

                mPager.setCurrentItem(1);
            }
            else  if(position==1)
            {
                v0.setVisibility(View.VISIBLE);
                v1.setVisibility(View.GONE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.VISIBLE);

                off0.setVisibility(View.VISIBLE);
                off1.setVisibility(View.GONE);
                off2.setVisibility(View.VISIBLE);
                off3.setVisibility(View.VISIBLE);

                on0.setVisibility(View.GONE);
                on1.setVisibility(View.VISIBLE);
                on2.setVisibility(View.GONE);
                on3.setVisibility(View.GONE);

                mPager.setCurrentItem(1);
            }
            else  if(position==2)
            {
                v0.setVisibility(View.VISIBLE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.VISIBLE);

                off0.setVisibility(View.VISIBLE);
                off1.setVisibility(View.VISIBLE);
                off2.setVisibility(View.GONE);
                off3.setVisibility(View.VISIBLE);

                on0.setVisibility(View.GONE);
                on1.setVisibility(View.GONE);
                on2.setVisibility(View.VISIBLE);
                on3.setVisibility(View.GONE);

                mPager.setCurrentItem(2);
            }
            else   if(position==3 )
            {
                v0.setVisibility(View.VISIBLE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.GONE);

                off0.setVisibility(View.VISIBLE);
                off1.setVisibility(View.VISIBLE);
                off2.setVisibility(View.VISIBLE);
                off3.setVisibility(View.GONE);

                on0.setVisibility(View.GONE);
                on1.setVisibility(View.GONE);
                on2.setVisibility(View.GONE);
                on3.setVisibility(View.VISIBLE);

                mPager.setCurrentItem(3);
            }
        }
    });

}



@Override
public void onTabSelected(TabLayout.Tab tab) {
        int pos = tab.getPosition();

        Log.e("position Selected", "" + pos);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setCurrentItem(pos);

        }

@Override
public void onTabUnselected(TabLayout.Tab tab) {
        int pos = tab.getPosition();

        Log.e("position Unselected", "" + pos);

        }

@Override
public void onTabReselected(TabLayout.Tab tab) {
        int pos = tab.getPosition();

  //  tabLayout.getTabAt(pos).getCustomView().setBackgroundDrawable(getResources().getDrawable(R.drawable.arrow));
        }




private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    Fragment fragment;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Log.e("clicked to ", "TabFragment0");
                return fragment = new TabFragment0();
            case 1:
                Log.e("clicked to ", "TabFragment1");
                return fragment = new TabFragment1();
            case 2:
                Log.e("clicked to ", "TabFragment2");
                return fragment = new TabFragment2();
            case 3:
                Log.e("clicked to ", "TabFragment3");
                return fragment = new TabFragment3();
        }
        return fragment = new TabFragment0();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //    finish();
    }
}