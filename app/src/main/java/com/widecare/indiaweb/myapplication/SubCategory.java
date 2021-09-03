package com.widecare.indiaweb.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Asynchronus.Jason_Urls;
import Constant.PlayGifV;
import Constant.UserDataItems;
import adapter.SubCategoryAdapter;
import app.AppController;

/**
 * Created by indiaweb on 9/17/2016.
 */
public class SubCategory extends AppCompatActivity {
    ListView listView;
    String TAG="SubCategory ";
    int connection=0;
    int socketTimeout = 30000;
    TextView catname;
    public static ArrayList<UserDataItems> arrayList = null;
    String catId="";
    String cname;
    String img;
    ImageView imgicon;
    ProgressDialog progressDialog;
    PlayGifV pGif;
    Boolean isInternet;
    TextView icon1, icon2, icon3, icon4, icon5;
    ImageView image1, image2, image3, image4, image5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        listView = (ListView) findViewById(R.id.listview);
        catname = (TextView)findViewById(R.id.catname);
        imgicon = (ImageView)findViewById(R.id.imgicon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.widecare);
        }

        Intent i = getIntent();
        catId = i.getStringExtra("catId");
        cname = i.getStringExtra("cname");
        img = i.getStringExtra("cimage");

        Log.e(TAG+"choosed cat", "catId=" + catId+" | catname="+cname+" | img="+img);

        catname.setText(cname);
         Picasso.with(getApplicationContext()).load(img).into(imgicon);

/*

        if(catId.equals("1"))
        {

            imgicon.setBackgroundResource(R.drawable.first_cat);
        }
        if(catId.equals("2"))
        {
            imgicon.setBackgroundResource(R.drawable.second_cat);
        }
        if(catId.equals("3"))
        {
            imgicon.setImageResource(R.drawable.third_cat);
        }
        if(catId.equals("4"))
        {
            imgicon.setBackgroundResource(R.drawable.fourth_cat);
        }
        if(catId.equals("5"))
        {
            imgicon.setBackgroundResource(R.drawable.fifth_cat);
        }
        if(catId.equals("6"))
        {
            imgicon.setBackgroundResource(R.drawable.sixth_cat);
        }
*/

        pGif = (PlayGifV)findViewById(R.id.viewGif);
        pGif.setImageResource(R.drawable.ringload);


        icon1 = (TextView) findViewById(R.id.icon11);
        icon2 = (TextView) findViewById(R.id.icon22);
        icon3 = (TextView) findViewById(R.id.icon33);
        icon4 = (TextView) findViewById(R.id.icon44);
        icon5 = (TextView) findViewById(R.id.icon55);

        image1 = (ImageView) findViewById(R.id.icon11m);
        image2 = (ImageView) findViewById(R.id.icon22m);
        image3 = (ImageView) findViewById(R.id.icon33m);
        image4 = (ImageView) findViewById(R.id.icon44m);
        image5 = (ImageView) findViewById(R.id.icon55m);

        if(isOnline()) {

            //    progress = ProgressDialog.show(this,null,"Please wait..");

//            Fetch_SubCategory();
            //Fetch_SubCategoryNew();
            FetchCategoryFromNewApi2();
        }
        else {

            //  progress.dismiss();
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 1");
                setCurrentItem(0);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 4");
                setCurrentItem(3);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 2");
                setCurrentItem(1);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 3");
                setCurrentItem(2);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 5");
                setCurrentItem(4);
            }
        });

        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 1");
                setCurrentItem(0);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 4");
                setCurrentItem(3);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 2");
                setCurrentItem(1);
            }
        });

        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 3");
                setCurrentItem(2);
            }
        });

        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked ", " 5");
                setCurrentItem(4);
            }
        });


    }



    private void FetchCategoryFromNewApi2() {

        Log.e(TAG ,"FetchCategoryFromNewApi2="+Jason_Urls.shopByProduct_url+"?action=fetch_Subcategory&cat_id=" + catId  );
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://widecare.in/android/shopByProduct.php?action=fetch_Subcategory&cat_id=" + catId  ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                         arrayList=new ArrayList<>();

                        try{
                            Log.e(TAG,"FetchCategoryFromNewApi2 response="+ response.toString());
                            JSONObject jsonObj =  new JSONObject(response);
                            JSONArray  jsonArrays = jsonObj.getJSONArray("data");

                            Log.e(TAG, "jsonArrays.length()= "+jsonArrays.length());

                            for (int j = 0; j < jsonArrays.length(); j++)
                            {
                                UserDataItems userDataItems = new UserDataItems();

                                JSONObject jsonObjects = jsonArrays.getJSONObject(j);

                                String pid = jsonObjects.getString("productId");
                                String pname = jsonObjects.getString("ProdName");
                                String pdes = jsonObjects.getString("prodDesc");
                                String pHtmlDes = jsonObjects.getString("prodhtmlDesc");
                                String pimage = jsonObjects.getString("prodImage");

                                Log.e("all productsidd ", " " + pid + "| " + pname + "| " + pdes + "| " + pimage);

                                try{
                                 String[]    pdes1= pdes.split("\\.");
                                pdes=pdes1[0];
                                Log.e(TAG,"pdes="+pdes);
                                }
                                catch (Exception excep)
                                {
                                    Log.e(TAG,""+excep.toString());
                                }

                                userDataItems.setCatId(catId);
                                userDataItems.setName(cname);
                                userDataItems.setImage(img);
                                userDataItems.setpId(pid);
                                userDataItems.setPname(pname);
                                userDataItems.setPdes(pdes);
                                userDataItems.setHtmlPdes(pHtmlDes);
                                userDataItems.setPimage(pimage);
                                arrayList.add(userDataItems);
                            }

                          //  Collections.reverse(arrayList);
                            listView.setAdapter(new SubCategoryAdapter(SubCategory.this, R.layout.products, arrayList));

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(TAG+" exception",""+e.toString());
                        }

                        load();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.e(TAG, "Error: " + error.getMessage());

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
                params.put("action", "fetch_Subcategory");
                params.put("cat_id", ""+catId);

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



    private void setCurrentItem(int i)
    {

        Intent ij = new Intent(SubCategory.this, NavigationActivity.class);
        ij.putExtra("pager", ""+i);
        startActivity(ij);
        finish();
    }


    private void Fetch_SubCategory() {
Log.e(TAG,"callApi "+Jason_Urls.categories_url+"?Universal=data");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Jason_Urls.categories_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response.toString());

                        arrayList=new ArrayList<>();
                        try{
                            JSONObject jsonObj =  new JSONObject(response);
                        JSONArray jsonArray = jsonObj.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++)
                        {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String catid  = jsonObject.getString("catId");
                            String catname = jsonObject.getString("text");

                            if(catId.equals(catid))
                            {
                                JSONArray jsonArrays = jsonObject.getJSONArray("products");

                                for (int j = 0; j < jsonArrays.length(); j++)
                                {
                                    UserDataItems userDataItems = new UserDataItems();

                                    JSONObject jsonObjects = jsonArrays.getJSONObject(j);

                                    String pid = jsonObjects.getString("ID");
                                    String pname = jsonObjects.getString("post_title");
                                    String pdes = jsonObjects.getString("post_excerpt");
                                    String pimage = jsonObjects.getString("productimage");

                                   Log.e("all productsidd ", " " + pid + "| " + pname + "| " + pdes + "| " + pimage);

                                    userDataItems.setCatId(catid);
                                    userDataItems.setName(cname);
                                    userDataItems.setImage(img);
                                    userDataItems.setpId(pid);
                                    userDataItems.setPname(pname);
                                    userDataItems.setPdes(pdes);
                                    userDataItems.setPimage(pimage);

                                    if(pid.equals("5492") || pid.equals("1242")  || pid.equals("1243") || pid.equals("427")||pid.equals("5684"))
                                    {

                                    }
                                    else
                                    {
                                  //      Log.e("productsiddXX ", " " + pid + " " + pname + " " + pdes + " " + pimage);
                                        arrayList.add(userDataItems);
                                    }


                                }

                            }

                        }

                         Collections.reverse(arrayList);
                        listView.setAdapter(new SubCategoryAdapter(SubCategory.this, R.layout.products, arrayList));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG+" exception",""+e.toString());
                    }

                    load();
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
                params.put("Universal", "data");

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

    private void load()
    {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pGif.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                //     Log.e("valueload", "value ");
            }
        }, 5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
             /*   Intent intent = new Intent(this, NavigationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
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
    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
    @Override
    public Dialog onCreateDialog(int id) {
        return null;
    }

}