package com.widecare.indiaweb.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
/*import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;*/
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;

import Constant.Shaved_shared_preferences;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView icon1, icon2, icon3, icon4, icon5,icon6, icon7;
    ImageView image1, image2, image3, image4, image5, image6, image7;
    NavigationView navigationView;
    String uri1 = "@drawable/home_icon";
    int imageResource1;
    Drawable res1;
    TextView name;
    String uri2 = "@drawable/categories_icon";
    int imageResource2;
    Drawable res2;
    LinearLayout linearLayout;
    String uri3 = "@drawable/policy_icon";
    int imageResource3;
    Drawable res3;

    String uri4 = "@drawable/offer_icon";
    int imageResource4;
    Drawable res4;

    String uri5 = "@drawable/cart_icon";
    int imageResource5;
    Drawable res5;
/*

    String uri6 = "@drawable/cart_icon";
    int imageResource6;
    Drawable res6;

    String uri5 = "@drawable/cart_icon";
    int imageResource5;
    Drawable res5;
*/


    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private ScreenSlidePagerAdapter /*PagerAdapter*/ mPagerAdapter;
    Shaved_shared_preferences shaved_shared_preferences = null;
    TextView logout;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    Boolean bol = true;
    Boolean bool = true;
    ImageView crossclick;
    int l =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setLogo(R.drawable.wide_care_orange_darkblue);

        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());

        Log.e("data login ", "" + shaved_shared_preferences.get_user_login());

        Log.e("data bol ", "" + bol);

        icon1 = (TextView) findViewById(R.id.icon1title);
        icon2 = (TextView) findViewById(R.id.icon2title);
        icon3 = (TextView) findViewById(R.id.icon3title);
        icon4 = (TextView) findViewById(R.id.icon4title);
        icon5 = (TextView) findViewById(R.id.icon5title);

        image1 = (ImageView) findViewById(R.id.icon1);
        image2 = (ImageView) findViewById(R.id.icon2);
        image3 = (ImageView) findViewById(R.id.icon3);
        image4 = (ImageView) findViewById(R.id.icon4);
        image5 = (ImageView) findViewById(R.id.icon5);

        logout = (TextView)findViewById(R.id.logout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent i = getIntent();
        if(i.getStringExtra("pager")!=null)
        {

            String k = i.getStringExtra("pager");
            l = Integer.parseInt(k);
           int  position = l;
            if (position == 0) {
                getSupportActionBar().setLogo(R.drawable.wide_care_orange_darkblue);
                icon1.setText("Home");
                icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                icon2.setText("Offers");
                icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon3.setText("My Policies");
                icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon4.setText("My Claims");
                icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon5.setText("My Cart");
                icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));

            }
            if (position == 3) {
                getSupportActionBar().setLogo(R.drawable.offers_logo);
                icon1.setText("Home");
                icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon2.setText("Offers");
                icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                icon3.setText("My Policies");
                icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon4.setText("My Claims");
                icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon5.setText("My Cart");
                icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));


            }
            if (position == 1) {
                getSupportActionBar().setLogo(R.drawable.policy_logo);
                getSupportActionBar().setTitle("");
                icon1.setText("Home");
                icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon2.setText("Offers");
                icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon3.setText("My Policies");
                icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                icon4.setText("My Claims");
                icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon5.setText("My Cart");
                icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));

            }
            if (position == 2) {
                getSupportActionBar().setLogo(R.drawable.claim_logo);
                getSupportActionBar().setTitle("");
                icon1.setText("Home");
                icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon2.setText("Offers");
                icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon3.setText("My Policies");
                icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon4.setText("My Claims");
                icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                icon5.setText("My Cart");
                icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));


            }
            if (position == 4) {
                getSupportActionBar().setLogo(R.drawable.cart_logo);
                getSupportActionBar().setTitle("");
                icon1.setText("Home");
                icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon2.setText("Offers");
                icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon3.setText("My Policies");
                icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon4.setText("My Claims");
                icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                icon5.setText("My Cart");
                icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));

            }

//            if(AppController.getInstance().isOnline())
//            {
//                makeJsonArryReq();
//
//            }
//            else {
//                Toast.makeText(getApplicationContext(), "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
//            }
         //   icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
           // icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
            Log.e("kkk", ""+l);
        }

        View header = navigationView.getHeaderView(0);
        linearLayout= (LinearLayout) header.findViewById(R.id.linearprofiles);
        crossclick = (ImageView)header. findViewById(R.id.crossclick);
        name =  (TextView)header.findViewById(R.id.name);
        if(shaved_shared_preferences.get_user_login() ==1 && bool==true)
        {
            logout.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);

            bool = true;
            Log.e("drawer value 1 ", "" + shaved_shared_preferences.get_user_login() + " " + bool);
        }
        else
        {
            logout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);

            bool = false;
            Log.e("drawer value 2 ", "" + shaved_shared_preferences.get_user_login() + " " + bool);

        }

        mTitle = mDrawerTitle = getTitle();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        {

            /* Called when drawer is closed */
            public void onDrawerClosed(View view)
            {
                Log.e("drawer ","close");

                Log.e("drawer value ",""+shaved_shared_preferences.get_user_login()+" "+shaved_shared_preferences.get_user_email());

                if(shaved_shared_preferences.get_user_login()==1 && bol==true) {
                    navigationView.getMenu().clear(); //clear old inflated items.
                    navigationView.inflateMenu(R.menu.navigation_menu_onlogin);
                    logout.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);

                      String email = shaved_shared_preferences.get_user_fname();
                        name.setText("Hello  "+email);

                       bol=false;
                }
                else
                {
//                    logout.setVisibility(View.GONE);
//                    linearLayout.setVisibility(View.GONE);
                }
            }

            /* Called when a drawer is opened */
            public void onDrawerOpened(View drawerView)
            {
                Log.e("drawer ", "open");

                Log.e("drawer value ",""+shaved_shared_preferences.get_user_login()+" "+shaved_shared_preferences.get_user_email());

                if(bool)
                {
                    Log.e("drawerSS ","open");
                    navigationView.getMenu().clear();
                    Log.e("drawerSS value ",""+shaved_shared_preferences.get_user_login()+" "+shaved_shared_preferences.get_user_email());

                    navigationView.inflateMenu(R.menu.navigation_menu_onlogin);
                    logout.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);

                        String email = shaved_shared_preferences.get_user_fname();
                        name.setText("Hello  "+email);
                        bol=false;

                }
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setCurrentItem(l);


        imageResource1 = getResources().getIdentifier(uri1, null, getPackageName());
        res1 = getResources().getDrawable(imageResource1);

        imageResource2 = getResources().getIdentifier(uri2, null, getPackageName());
        res2 = getResources().getDrawable(imageResource2);

        imageResource3 = getResources().getIdentifier(uri3, null, getPackageName());
        res3 = getResources().getDrawable(imageResource3);

        imageResource4 = getResources().getIdentifier(uri4, null, getPackageName());
        res4 = getResources().getDrawable(imageResource4);

        imageResource5 = getResources().getIdentifier(uri5, null, getPackageName());
        res5 = getResources().getDrawable(imageResource5);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {

            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {

                Log.e("viewing page ", "" + position);
                if (position == 0) {
                    getSupportActionBar().setLogo(R.drawable.wide_care_orange_darkblue);
                    icon1.setText("Home");
                    icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                    icon2.setText("Offers");
                    icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon3.setText("My Policies");
                    icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon4.setText("My Claims");
                    icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon5.setText("My Cart");
                    icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));

                    image1.setImageDrawable(res1);
                    image2.setImageDrawable(res2);
                    image3.setImageDrawable(res3);
                    image4.setImageDrawable(res4);
                    image5.setImageDrawable(res5);
                }
                if (position == 3) {
                    getSupportActionBar().setLogo(R.drawable.offers_logo);
                    icon1.setText("Home");
                    icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon2.setText("Offers");
                    icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                    icon3.setText("My Policies");
                    icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon4.setText("My Claims");
                    icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon5.setText("My Cart");
                    icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));

                    image1.setImageDrawable(res1);
                    image2.setImageDrawable(res2);
                    image3.setImageDrawable(res3);
                    image4.setImageDrawable(res4);
                    image5.setImageDrawable(res5);
                }
                if (position == 1) {
                    getSupportActionBar().setLogo(R.drawable.policy_logo);
                    getSupportActionBar().setTitle("");
                    icon1.setText("Home");
                    icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon2.setText("Offers");
                    icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon3.setText("My Policies");
                    icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                    icon4.setText("My Claims");
                    icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon5.setText("My Cart");
                    icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));

                    image1.setImageDrawable(res1);
                    image2.setImageDrawable(res2);
                    image3.setImageDrawable(res3);
                    image4.setImageDrawable(res4);
                    image5.setImageDrawable(res5);
                }
                if (position == 2) {
                    getSupportActionBar().setLogo(R.drawable.claim_logo);
                    getSupportActionBar().setTitle("");
                    icon1.setText("Home");
                    icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon2.setText("Offers");
                    icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon3.setText("My Policies");
                    icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon4.setText("My Claims");
                    icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));
                    icon5.setText("My Cart");
                    icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));

                    image1.setImageDrawable(res1);
                    image2.setImageDrawable(res2);
                    image3.setImageDrawable(res3);
                    image4.setImageDrawable(res4);
                    image5.setImageDrawable(res5);
                }
                if (position == 4) {
                    getSupportActionBar().setLogo(R.drawable.cart_logo);
                    getSupportActionBar().setTitle("");
                    icon1.setText("Home");
                    icon1.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon2.setText("Offers");
                    icon2.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon3.setText("My Policies");
                    icon3.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon4.setText("My Claims");
                    icon4.setTextColor(getApplicationContext().getResources().getColor(R.color.grey));
                    icon5.setText("My Cart");
                    icon5.setTextColor(getApplicationContext().getResources().getColor(R.color.orange));

                    image1.setImageDrawable(res1);
                    image2.setImageDrawable(res2);
                    image3.setImageDrawable(res3);
                    image4.setImageDrawable(res4);
                    image5.setImageDrawable(res5);
                }
            }
        });

        crossclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
           // Toast.makeText(getApplicationContext(),"cross",Toast.LENGTH_LONG).show();
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 1");
                mPager.setCurrentItem(0);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 4");
                mPager.setCurrentItem(3);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 2");
                mPager.setCurrentItem(1);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 3");
                mPager.setCurrentItem(2);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 5");
                mPager.setCurrentItem(4);
            }
        });

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 1");
                mPager.setCurrentItem(0);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 4");
                mPager.setCurrentItem(3);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 2");
                mPager.setCurrentItem(1);
            }
        });

        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 3");
                mPager.setCurrentItem(2);
            }
        });

        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 5");
                mPager.setCurrentItem(4);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " logout");
                bol=true;
                shaved_shared_preferences.set_user_login(0);
                shaved_shared_preferences.set_user_email(null);
                shaved_shared_preferences.set_user_fname(null);
                shaved_shared_preferences.set_userid(null);

                Toast.makeText(getApplicationContext(),"User is logout",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
                startActivity(i);
               // finish();
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent i = new Intent(NavigationActivity.this, NavigationActivity.class);
            startActivity(i);
        } else if (id == R.id.signin_up) {
            Intent i = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(i);
        } else if (id == R.id.noti) {
            Intent i = new Intent(NavigationActivity.this, NotificationActivity.class);
            startActivity(i);
        } else if (id == R.id.about) {
            Intent i = new Intent(NavigationActivity.this, AboutActivity.class);
            startActivity(i);
        } else if (id == R.id.support) {
          /*  Intent i = new Intent(NavigationActivity.this, SupportActivity.class);
            startActivity(i);*/

            Intent i = new Intent(NavigationActivity.this, WebVwUrlSecond.class);
            i.putExtra("holdData","https://widecare.in/shop/faq-app.php");
            // i.putExtra("for","whyUs");
            startActivity(i);
        } else if (id == R.id.contact) {
            Intent i = new Intent(NavigationActivity.this, ContactActivity.class);
            startActivity(i);

                } else if (id == R.id.whyUs) {
            Intent i = new Intent(NavigationActivity.this, WebVwUrlSecond.class);
           i.putExtra("holdData","https://widecare.in/shop/why-us-app.php");
           // i.putExtra("for","whyUs");
             startActivity(i);
                 }
        else if (id == R.id.howToClaim) {
            Intent i = new Intent(NavigationActivity.this, WebVwUrlSecond.class);
             i.putExtra("holdData","https://widecare.in/shop/how-to-claim-app.php");
           // i.putExtra("for","howToclaim");
             startActivity(i);
        }
        else if (id == R.id.rate) {
            /*Intent i = new Intent(NavigationActivity.this, RateActivity.class);
            startActivity(i);*/
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
            }
        }
         else if (id == R.id.profile) {
            Intent i = new Intent(NavigationActivity.this, ProfileActivity.class);
            startActivity(i);
        } else if (id == R.id.claims) {
            Intent i = new Intent(NavigationActivity.this, ClaimsActivity.class);
            startActivity(i);
        } else if (id == R.id.order) {
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("pager","1");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack
            startActivity(intent);
            finish(); // call this to finish the current activity
        }
        else if (id == R.id.refers) {
            Intent i = new Intent(NavigationActivity.this, ReferEarnActivity.class);
            startActivity(i);
        } else if (id == R.id.aboutt) {
            Intent i = new Intent(NavigationActivity.this, AboutActivity.class);
            startActivity(i);
        }else if (id == R.id.supportt) {
            Intent i = new Intent(NavigationActivity.this, SupportActivity.class);
            startActivity(i);
        }
        else if (id == R.id.contactt) {
            Intent i = new Intent(NavigationActivity.this, ContactActivity.class);
            startActivity(i);
        } else if (id == R.id.ratee) {
           /* Intent i = new Intent(NavigationActivity.this, RateActivity.class);
            startActivity(i);*/
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * A simple pager adapter that represents 5 HomeScreen objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
    {
        Fragment fragment;
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch(position){

                case 0: /** Start a new Activity MyCards.java */
                    Log.e("clicked to ", "home");
                    return fragment =  new HomeScreen();
                case 1:
                    Log.e("clicked to ", "policies");
                    return fragment =  new MyPolicies();//policy();

                case 2: /** Start a new Activity MyCards.java */
                    Log.e("clicked to ", "My Claims");
                   // return fragment =  new Offers();
                    return fragment = new Claims();
                case 3:
                    Log.e("clicked to ", "categories");
                    return fragment =  new ComboActivity();
                case 4: /** Start a new Activity MyCards.java */
                    Log.e("clicked to ", "cart");
                    return fragment =  new MyCart();
            }
            return fragment =  new HomeScreen();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


}