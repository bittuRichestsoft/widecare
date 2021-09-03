package com.widecare.indiaweb.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import Asynchronus.Jason_Urls;
import Constant.PlayGifV;
import Constant.RowItem;
import Constant.Shaved_shared_preferences;
import adapter.CategoryAdapter;
import app.AppController;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by indiaweb on 9/17/2016.
 */
public class HomeScreen extends Fragment {
    public static String[] id = null;
    public static String[] titles = null;
    public  String TAG = "HomeScreen ";
    public static  int[] images = null;
    CategoryAdapter adapter =null;
    GridView listView;
    List<RowItem> rowItems;
    ArrayList<String> a_name,a_id,a_image;
    static TextView mDotsText[];
 //   Myadapter adapter2;
   // ViewPager viewPager;
    int[] flag;
    int socketTimeout = 30000;
    int heights;
    Runnable update;
    RelativeLayout relativeLayout;
    int NUM_PAGES;
    int currentPage;
    Handler handler;
    private int mDotsCount;
    private LinearLayout mDotsLayout,viewGifflayout_home;
    PlayGifV pGiff;

    Shaved_shared_preferences shaved_shared_preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_screen, container, false);
        rowItems = new ArrayList<RowItem>();
        listView = (GridView)rootView.findViewById(R.id.gridView1);
      //  viewPager = (ViewPager)rootView.findViewById(R.id.vp);
        relativeLayout = (RelativeLayout)rootView.findViewById(R.id.relative);

        a_name = new ArrayList<>();
        a_id = new ArrayList<>();
        a_image = new ArrayList<>();

        a_name.clear();
        a_id.clear();
        a_image.clear();

     /*   a_id.add("9");
        a_id.add("10");
        a_id.add("11");
      //  a_id.add("12");

     //   a_id.add("70");
    //    a_id.add("45");
      //  a_id.add("30");

      //  a_id.add("16");
        a_id.add("21");
      //  a_id.add("110");

     //   a_id.add("17");
     //   a_id.add("14");

*/
        pGiff = (PlayGifV)rootView. findViewById(R.id.viewGiff_home);
        pGiff.setImageResource(R.drawable.ringload);

      //  titles= new String[]{"Device Accidental Protection","Device Theft Protection","Device warranty & repair","Data Protection and Security","Travel protect value pack","vehicle\nAssistance","Buyback and Trade-In","Life \nProtection"};
     //   id = new String[]{"9","10","11","12","16","30","14","45"};
        mDotsLayout = (LinearLayout)rootView.findViewById(R.id.image_count);
        viewGifflayout_home = (LinearLayout)rootView.findViewById(R.id.viewGifflayout_home);

        Display display = getActivity().getWindowManager().getDefaultDisplay();

        int width = display.getWidth(); // ((display.getWidth()*20)/100)
        int height = display.getHeight();
        // ((display.getHeight()*30)/100)

        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int screen_height = outMetrics.heightPixels;
        int screen_width = outMetrics.widthPixels;

//        Log.e("display ","display "+display);
//        Log.e("display ","width "+width+"----" + screen_width);
//        Log.e("display ", "width "+height+"----"+screen_height);


        float smallestWidth = screen_width ;
 //       Log.e("displayss ", "" + smallestWidth);

        if (smallestWidth > 720) {

     //       Log.e("displays3 ", "" + smallestWidth);

        }
        else if (smallestWidth > 600) {

       //     Log.e("displays2 ", "" + smallestWidth);
        }
        else
        {
        //    Log.e("displays1 ", "" + smallestWidth);
        }

        flag = new int[]{R.drawable.b, R.drawable.a, R.drawable.d, R.drawable.c, R.drawable.f, R.drawable.e,R.drawable.e};

       // images = new int[]{R.drawable.device,R.drawable.protection,R.drawable.repair,R.drawable.data,R.drawable.combo,R.drawable.veh,R.drawable.buz,R.drawable.life_protection};

       /* adapter2 = new Myadapter(getActivity(), flag);
        viewPager.setAdapter(adapter2);
*/
         handler = new Handler();
        update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       currentPage = 0;
                   }
               },1000);
                }
            //    viewPager.setCurrentItem(currentPage++, true);
            }
        };
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 1000, 1200);

     //   mDotsCount = viewPager.getAdapter().getCount();
        mDotsText = new TextView[mDotsCount];

        for (int i = 0; i < mDotsCount; i++) {
            mDotsText[i] = new TextView(getActivity());
            mDotsText[i].setText(".");
            mDotsText[i].setTextSize(45);
            mDotsText[i].setTypeface(null, Typeface.BOLD);
            mDotsLayout.addView(mDotsText[i]);
        }
      /*  viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < mDotsCount; i++) {
                    mDotsText[i].setTextColor(Color.WHITE);
                }
                try {
                    mDotsText[position].setTextColor(getResources().getColor(R.color.black));
                } catch (Exception e) {
                    e.getMessage();
                    //   mDotsText[1].setTextColor(getResources().getColor(R.color.black));

                }
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (currentPage == viewPager.getAdapter().getCount() - 1) {
                        viewPager.setCurrentItem(0, false);
                    } else if (currentPage == 0) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount(), false);
                    }

                }

            }
        });
*/
        if(AppController.getInstance().isOnline())
        {
            viewGifflayout_home.setVisibility(View.VISIBLE);
            pGiff.setVisibility(View.VISIBLE);
        //     Fetch_Category();  --- old api
           FetchCategoryFromNewApi();  //--new api
         }
        else {
            Toast.makeText(getActivity(), "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
        }

     getToken();

        return rootView;
    }

    private void getToken() {
       String token="";
        if(token==null || token=="" ) {
            token = FirebaseInstanceId.getInstance().getToken();
            Log.e(TAG, "" + token);
        }
        Log.e(TAG, "2nd  <<>" + token);

    }


    private void FetchCategoryFromNewApi() {

        Log.e(TAG ,"Fetch_CategoryFromNewApi="+Jason_Urls.shopByProduct_url+"?action=fetch_category" );
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, "https://widecare.in/android/shopByProduct.php?action=fetch_category",null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Log.e(TAG,"FetchCategoryFromNewApi response="+ response.toString());
                        try {
//                            JSONObject ob = new JSONObject(response);
                             JSONArray jsonArray = response.getJSONArray("data");
                           for (int i = 0; i < jsonArray.length(); i++) {
                                 JSONObject jsonObject = jsonArray.getJSONObject(i);
                                 String catId = jsonObject.getString("catId");
                                String imagee = jsonObject.getString("catImage");
                                String catname = jsonObject.getString("catName");
                        Log.e(TAG , "FetchCategoryFromNewApi catId=" + catId + "<<>>>catname=" + catname + "<<>>catimg=" + imagee);
                                HashMap<String,String> hash = new HashMap<>();
                                hash.put("catid", catId);
                                hash.put("image", imagee);
                                 hash.put("catname", catname);
                                 RowItem item = new RowItem(imagee, catname,catId);
                                rowItems.add(item);
                             }
                        }
                        catch (JSONException e)
                        {
                         Log.e(TAG,"exception="+e.toString());
                        }

                        Log.e(TAG,"rowItems.size()="+rowItems.size());

                        if (getActivity()!=null) {
                            adapter = new CategoryAdapter(getContext(), rowItems);
                            // adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                        }
                        viewGifflayout_home.setVisibility(View.GONE);
                        pGiff.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
               //  params.put("action", "fetch_category");

                return params;
            }

        };

        // Adding request to request queue

        // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
    ////////////////////

  /*  private void Fetch_Category() {

Log.e(TAG+"Fetch_Category","categories="+Jason_Urls.categories_url );
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Jason_Urls.categories_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response.toString());

                        try {
                            JSONObject ob = new JSONObject(response);

                            Log.e("Fetch_Category", response.toString());


                                JSONArray jsonArray = ob.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {


                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    String catId = jsonObject.getString("catId");
                                    String imagee = jsonObject.getString("img");
                                    String catname = jsonObject.getString("text");

                                    Log.e("Fetch_Category", "..." + catId + "--" + catname + "--" + imagee);

                                    HashMap<String,String> hash = new HashMap<>();

                                    if(catId.equals("16"))
                                    {
                                        catname= "Travel Protection" ;
                                    }

                                    hash.put("catid", catId);
                                    hash.put("image", imagee);
                                   *//* if(catname.contains("Complete Protection"))
                                        catname=catname+" "+catname;*//*
                                        hash.put("catname", catname);

                                    arrayList.add(hash);

                                 a_id.add(catId);
                                a_name.add(catname);
                                a_image.add(imagee);
                                }
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }

                            for(String are : a_id)
                            {
                                for(HashMap<String, String> ar:arrayList)
                                {
                                    String catid =  ar.get("catid");

                                    if(catid.equals(are))
                                    {
                                        String image =  ar.get("image");
                                        String catname =  ar.get("catname");

                                        RowItem item = new RowItem(image, catname, catid);
                                        rowItems.add(item);

                                        a_name.add(catid);
                                    }
                                }
                            }

                            if (getActivity()!=null) {
                                adapter = new CategoryAdapter(getContext(), rowItems);
                                // adapter.notifyDataSetChanged();
                                listView.setAdapter(adapter);
                            }
                            viewGifflayout_home.setVisibility(View.GONE);
                            pGiff.setVisibility(View.GONE);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());

                        }
                    }) {

                        *//**
                         * Passing some request headers
                         * *//*
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", "Androidhive");
                            params.put("email", "abc@androidhive.info");
                            params.put("pass", "password123");

                            return params;
                        }

                    };

                    // Adding request to request queue

                    // 30 seconds. You can change it
                    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }*/



    //////////////////

    
}