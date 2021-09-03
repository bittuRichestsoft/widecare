package com.widecare.indiaweb.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.provider.CalendarContract;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Asynchronus.Jason_Urls;
import Constant.PlayGifV;
import Constant.UserDataItems;
import app.AppController;

public class CalculatePriceScreen extends AppCompatActivity implements View.OnClickListener {
    PlayGifV pGif;
    ListView lv_catFeatures;
    TextView btnBuy,tvChoosedProdName,tvProdctDesctiption,tvChoosedProdNameForCart,tvCalculatedPrice,tvOutsideCity,tvWithinCity;
    ArrayList<String> arrLstFeatures,attributeStrArrLst,slabStrArrLst,planStrArrLst,citiesStrArrLst = new ArrayList<>();
    ProductFeatureAdap arrayAdapter;
    String holdProid, holdPname, holdPdesc, holdPimag, holdPvalue = "0", holdPcatid,holdProdCat="" ;
    String TAG="CalculatePriceScreen ",calculatedAmount="";
    int socketTimeout = 30000;
    ArrayList<UserDataItems> attributeArrLst = new ArrayList<>();
    ArrayList<UserDataItems> slabArrLst = new ArrayList<>();

    ArrayList<UserDataItems> planArrLst = new ArrayList<>();


    ImageView ivChoosedCatImage;
Spinner spDeviceSlab,spDeviceArrtribute,spPlanValue,spChooseCity;
String choosedSlab="",choosedAttribute="",choosedAttributeType="",choosedPlan="",choosedCity="";

WebView webVw_description;
LinearLayout llChooseItemPrice,llChooseDevice,llDevicePlanOption,llChooseCity,llCitiesInSideOutsideBtnVisiblilit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_price_screen);

        lv_catFeatures = (ListView) findViewById(R.id.lv_catFeatures);
        btnBuy = (TextView) findViewById(R.id.btnBuy);
        spDeviceArrtribute= (Spinner) findViewById(R.id.sp_deviceArrtribute);

        spDeviceSlab = (Spinner) findViewById(R.id.sp_deviceSlab);
        spPlanValue = (Spinner) findViewById(R.id.sp_planValue);
        spChooseCity = (Spinner) findViewById(R.id.sp_chooseCity);
        tvProdctDesctiption= (TextView) findViewById(R.id.tv_prodctDesctiption);
        webVw_description= (WebView) findViewById(R.id.webVw_prodctDesctiption);
                tvChoosedProdName= (TextView) findViewById(R.id.tv_choosedProdName);
        tvChoosedProdNameForCart= (TextView) findViewById(R.id.tv_choosedProdNameForCart);
        tvCalculatedPrice= (TextView) findViewById(R.id.tv_calculatedPrice);
        tvOutsideCity= (TextView) findViewById(R.id.tvOutsideCity);
        tvWithinCity= (TextView) findViewById(R.id.tvWithinCity);

        ivChoosedCatImage=(ImageView) findViewById(R.id.iv_choosedCatImage);
        llChooseItemPrice=(LinearLayout) findViewById(R.id.ll_chooseItemPrice);
        llChooseDevice=(LinearLayout) findViewById(R.id.ll_chooseDevice);
        llDevicePlanOption=(LinearLayout) findViewById(R.id.ll_DevicePlanOption);
        llChooseCity=(LinearLayout) findViewById(R.id.ll_chooseCity);
        llCitiesInSideOutsideBtnVisiblilit=(LinearLayout) findViewById(R.id.llCities_inSideOutsideBtnVisiblilit);
                pGif = (PlayGifV)findViewById(R.id.viewGif);
        pGif.setImageResource(R.drawable.ringload);
     /*   arrLstFeatures.add("this is first record");
        arrLstFeatures.add("this is second record");
        arrLstFeatures.add("this is third record");
        arrLstFeatures.add("this is fourth record");
        arrayAdapter = new ProductFeatureAdap(getBaseContext(),  arrLstFeatures);
        arrayAdapter.notifyDataSetChanged();
        lv_catFeatures.setAdapter(arrayAdapter);
*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.widecare);
        }

        load();
        Intent i = getIntent();
        holdPcatid = i.getStringExtra("catid");
        holdPvalue = i.getStringExtra("value");
        holdProid = i.getStringExtra("proid");
        holdPname = i.getStringExtra("pname");
        holdPdesc = i.getStringExtra("pdes");
     //   holdPdesc = Html.fromHtml(holdPdesc).toString();
        holdPimag = i.getStringExtra("pimage");
        holdProdCat= i.getStringExtra("cname");

        tvProdctDesctiption.setText(holdPdesc);
        webVw_description. loadData(holdPdesc, "text/html", "UTF-8");
        getSingleProductDetail();
        btnBuy.setOnClickListener(this);
        tvChoosedProdName.setText(""+holdPname);
        tvChoosedProdNameForCart.setText(""+holdPname);

        Log.e(TAG,"holdPimag="+holdPimag);
        Picasso.with(CalculatePriceScreen.this).load(holdPimag)
                .placeholder(R.drawable.loader)
                .error(R.drawable.loader).into(ivChoosedCatImage);

        tvOutsideCity.setOnClickListener(this);
        tvWithinCity.setOnClickListener(this);
    }

    private void getSingleProductDetail() {
             Log.e(TAG ,"getSingleProductDetail="+ Jason_Urls.shopByProduct_url+"?action=fetch_Slabs&product_id="+holdProid);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://widecare.in/android/shopByProduct.php?action=fetch_Slabs&product_id="+holdProid  ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            llChooseItemPrice.setVisibility(View.GONE);
                            llChooseDevice.setVisibility(View.GONE);
                            llDevicePlanOption.setVisibility(View.GONE);
                            llChooseCity.setVisibility(View.GONE);
                            try{
                                Log.e(TAG,"getSingleProductDetail response="+ response.toString());
                                JSONObject jsonObj =  new JSONObject(response);
                                JSONObject dataObj = jsonObj.getJSONObject("data");
                          try {
                              JSONArray deviceArr = dataObj.getJSONArray("Device");
                              attributeStrArrLst=new ArrayList<>();
                              for (int devicsLoop = 0;devicsLoop < deviceArr.length();devicsLoop++)
                              {
                                  UserDataItems userDataItems = new UserDataItems();
                                  JSONObject jsonObjects = deviceArr.getJSONObject(devicsLoop);
                                  String strAttributeId= jsonObjects.getString("attribute_id");
                                  String strAttributeName = jsonObjects.getString("attribute_name");
                                  userDataItems.setAttributeId(strAttributeId);
                                  userDataItems.setAttributeName(strAttributeName);
                                  attributeArrLst.add(userDataItems);
                                  attributeStrArrLst.add(strAttributeName);
                              }

                              setAttributeAdapInSpinner();

                              JSONArray slabsArr = dataObj.getJSONArray("Slabs");
                               slabStrArrLst=new ArrayList<>();
                              slabArrLst=new ArrayList<>();
                              llChooseItemPrice.setVisibility(View.VISIBLE);
                              llChooseDevice.setVisibility(View.VISIBLE);
                               for (int slabLoop = 0;slabLoop < slabsArr.length();slabLoop++)
                              {
                                  UserDataItems userDataItems = new UserDataItems();
                                  JSONObject jsonObjects = slabsArr.getJSONObject(slabLoop);
                                   String strSlabId= jsonObjects.getString("slab_id");
                                  String strSlabValue = jsonObjects.getString("slab_value");
                                  userDataItems.setSlabId(strSlabId);
                                  userDataItems.setSlabValue(strSlabValue);  ;
                                  slabArrLst.add(userDataItems);
                                  slabStrArrLst.add(strSlabValue);
                              Log.e(TAG,"slabLoop");
                              }
                              setSlabAdapInSpinner();
                          }
                          catch (Exception exp){
                              Log.e(TAG+" exception","exp="+exp.toString());
                          }
                            } catch (Exception exp2) {
                                 Log.e(TAG+" exception","exp2="+exp2.toString());
                            }

                            //for get only data string
                            try{
                                Log.e(TAG,"getSingleProductDetail response="+ response.toString());
                                JSONObject jsonObj =  new JSONObject(response);
                                String statusStr = jsonObj.getString("status");
                                if(statusStr.equals("2"))
                                {

//btnBuy.callOnClick();
                                     callFetchPriceApi();

                                }
                               // String dataStr = jsonObj.getString("data");
                             Log.e(TAG,"handlingStatus="+response.toString());
                            }
                            catch (Exception expDataStr){
                                Log.e(TAG+" exception","expDataStr="+expDataStr.toString());
                            }

                            //for get plan
                            try{
                                Log.e(TAG,"getSingleProductDetail response="+ response.toString());
                                JSONObject jsonObj =  new JSONObject(response);
                                 JSONObject dataObj= jsonObj.getJSONObject("data");
                                JSONArray planArry= dataObj.getJSONArray("Plans");
                                planArrLst=new ArrayList<>();
                                planStrArrLst=new ArrayList<>();
                                 llDevicePlanOption.setVisibility(View.VISIBLE);

                                for (int slabLoop = 0;slabLoop < planArry.length();slabLoop++)
                                {
                                    UserDataItems userDataItems = new UserDataItems();
                                    JSONObject jsonObjects =planArry.getJSONObject(slabLoop);
                                    String strPlanId= jsonObjects.getString("plan_id");
                                    String strPlanName = jsonObjects.getString("plan_name");
                                    /*plan_id":"44877","plan_name*/
                                    userDataItems.setSlabId(strPlanId);
                                    userDataItems.setSlabValue(strPlanName);  ;
                                     planArrLst.add(userDataItems);
                                    planStrArrLst.add(strPlanName);
                                    Log.e(TAG,"planArry<<>>>strPlanId="+strPlanId+"<<>>strPlanName="+strPlanName );
                                     }
                                tvWithinCity.callOnClick();
                                setPlanAdapInSpinner();
//                                Log.e(TAG,"PlanArry="+strPlanName);
                            try {
                                JSONArray cityJsonArry= dataObj.getJSONArray("Cities");
                                citiesStrArrLst=new ArrayList<>();
                                llChooseCity.setVisibility(View.VISIBLE);

                            for (int cityLoop= 0;cityLoop < cityJsonArry.length();cityLoop++)
                                {
                                    UserDataItems userDataItems = new UserDataItems();
                                    JSONObject jsonObjects =cityJsonArry.getJSONObject(cityLoop);
                                    String strCityName= jsonObjects.getString("city_name");

                                    citiesStrArrLst.add(strCityName);
                                    Log.e(TAG,"citiesStrArrLst<<>>>strName="+strCityName);
                                }
                                setCityAdapInSpinner();

                            }
                            catch (Exception exp)
                            {

                            }

                            }
                            catch (Exception expDataStr){
                                Log.e(TAG+" exception","expDataStr="+expDataStr.toString());
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
                    params.put("action", "fetch_Slabs");
                    params.put("product_id", ""+holdProid);
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

    private void setCityAdapInSpinner() {
        Log.e(TAG,"setCityAdapInSpinner citiesStrArrLst size="+citiesStrArrLst.size());

         ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CalculatePriceScreen.this, R.layout.textview_with_background, citiesStrArrLst) {
           @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                text.setTextColor(Color.parseColor("#3d406b"));
                Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                v.setBackground(backgroundColor);
                return v;
            }
        };

        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        spChooseCity.setAdapter(dataAdapter);

        spChooseCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosedCity = citiesStrArrLst.get(position);
                Log.e(TAG , "choosedCity position=" + position + "<>city=" + choosedCity);
                //tvChoosedProdNameForCart.setText(""+ planArrLst.get(position).getSlabValue());
             }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
 llCitiesInSideOutsideBtnVisiblilit.setVisibility(View.VISIBLE);
 llDevicePlanOption.setVisibility(View.VISIBLE);
        spPlanValue.setVisibility(View.GONE);
    }

    private void setPlanAdapInSpinner() {
Log.e(TAG,"setPlanAdapInSpinner="+planStrArrLst.size());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CalculatePriceScreen.this, R.layout.textview_with_background, planStrArrLst) {

             @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                text.setTextColor(Color.parseColor("#3d406b"));
                Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                v.setBackground(backgroundColor);
                return v;
            }
        };

        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        spPlanValue.setAdapter(dataAdapter);
        Log.e(TAG,"planArrLst size="+planArrLst.size());

        spPlanValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosedPlan = planArrLst.get(position).getSlabId();
                Log.e(TAG , "choosedPlan=" + position + "-" + choosedPlan);
                tvChoosedProdNameForCart.setText(""+ planArrLst.get(position).getSlabValue());
             if(holdProid=="128")
             {
                 choosedAttribute= choosedPlan;
             }
                callFetchPriceApi();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(planArrLst.size()>0)
        {
            callFetchPriceApi();
        }

    }

    private void setSlabAdapInSpinner() {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CalculatePriceScreen.this, R.layout.textview_with_background, slabStrArrLst) {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                text.setTextColor(Color.parseColor("#3d406b"));
                Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                v.setBackground(backgroundColor);
                return v;
            }
        };

         dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        spDeviceSlab.setAdapter(dataAdapter);
        Log.e(TAG,"slabStrArrLst size="+slabStrArrLst.size());

        spDeviceSlab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosedSlab = slabArrLst.get(position).getSlabId();

                Log.e(TAG , "spDeviceSlab=" + position + "-" + choosedSlab);
                callFetchPriceApi();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(slabArrLst.size()>0)
        {
            callFetchPriceApi();
        }
    }

    private void callFetchPriceApi() {
        String slabParam="", productIdParam="",attributeIdParam="";

        if(choosedSlab!="")
        {
            slabParam="&slab_id="+choosedSlab;
        }

        if(holdProid!="")
        {
            productIdParam="&product_id="+holdProid;
        }


         if(choosedAttribute!="")
        {
            attributeIdParam="&device_id="+choosedAttribute;
        }

String strUrl="https://widecare.in/android/shopByProduct.php?action=fetch_price"+slabParam+productIdParam+attributeIdParam;
         Log.e(TAG ,"callFetchPriceApi="+ strUrl);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strUrl  ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            Log.e(TAG,"callFetchPriceApi response="+ response.toString());
                            JSONObject jsonObj =  new JSONObject(response);
                            String dataStr = jsonObj.getString("data");
                            Log.e(TAG,"---"+dataStr);
                        calculatedAmount=dataStr.replace("[","");
                            calculatedAmount=calculatedAmount.replace("]","");
                            tvCalculatedPrice.setText("Rs. "+calculatedAmount +" only");
                        }
                        catch (Exception expDataStr){
                            Log.e(TAG+" exception","expDataStr="+expDataStr.toString());
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

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "fetch_Slabs");
                params.put("product_id", ""+holdProid);

                 /*
                params.put("action", "fetch_price");
                params.put("slab_id", ""+choosedSlab);
                params.put("product_id", ""+holdProid);
                 params.put("device_id", ""+choosedAttribute);
 */

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

    private void setAttributeAdapInSpinner() {


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(CalculatePriceScreen.this, R.layout.textview_with_background, attributeStrArrLst) {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                text.setTextColor(Color.parseColor("#3d406b"));
                Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                v.setBackground(backgroundColor);
                return v;
            }
        };


        // Drop down layout style - list view with radio button
       // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        spDeviceArrtribute.setAdapter(dataAdapter);

        spDeviceArrtribute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choosedAttribute = String.valueOf( attributeArrLst.get(position).getAttributeId());
        choosedAttributeType=String.valueOf( attributeArrLst.get(position).getAttributeName());
        Log.e(TAG , "spDeviceArrtribute= " + position + "-" + choosedAttribute);
callFetchPriceApi();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});

    }


    private void load()
    {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pGif.setVisibility(View.GONE);
            //    listView.setVisibility(View.VISIBLE);
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

                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case  R.id.btnBuy:
            {

if(calculatedAmount.equals(""))
{
    Toast.makeText(this,"Please select any another choice",Toast.LENGTH_LONG).show();
}
else {
    Intent i = new Intent(CalculatePriceScreen.this, Products.class);
    i.putExtra("catid", "" + holdPcatid);
    i.putExtra("cname", "" + holdProdCat);
    i.putExtra("cimage", "" + holdPimag);
    i.putExtra("proid", "" + holdProid);
    i.putExtra("pname", "" + holdPname);
    i.putExtra("pdes", "" + holdPdesc);
    i.putExtra("pimage", "" + holdPimag);

    i.putExtra("pChoosedAttribute", "" + choosedAttribute);
    i.putExtra("pChoosedAttributeType", "" + choosedAttributeType);

    i.putExtra("pChoosedSlab", "" + choosedSlab);
    i.putExtra("pCalculatedAmount", "" + calculatedAmount);


    Log.e(TAG + " gotoNext ", "proid=" + holdProid + "| pname=" + holdPname + "| pdes=" + holdPdesc + "| pimage=" + holdPimag);
    startActivity(i);
}
            }
break;
            case R.id.tvOutsideCity: {
                Log.e(TAG, "tvOutsideCity clicked = " + planArrLst.get(1).getSlabId());
                choosedAttribute = planArrLst.get(1).getSlabId();
                callFetchPriceApi();
                /*if(BuildConfig >= 23)
                {
                    tvWithinCity.setTextColor(ContextCompat.getColor(context, R.color.<name_of_color>));

                }
                (For API< 23)
                {
                    mTextView.setTextColor(getResources().getColor(R.color.<name_of_color>));

                }*/
                tvOutsideCity.setTextColor(getResources().getColor(R.color.white));
                tvOutsideCity.setBackgroundColor(getResources().getColor(R.color.orange));

                tvWithinCity.setTextColor(getResources().getColor(R.color.white));
                tvWithinCity.setBackgroundColor(getResources().getColor(R.color.grey));
            }

            break;
            case R.id.tvWithinCity:
            {
                Log.e(TAG,"tvWithinCity clicked = "+planArrLst.get(0).getSlabId());
                choosedAttribute=planArrLst.get(0).getSlabId();
                callFetchPriceApi();
                tvWithinCity.setTextColor(getResources().getColor(R.color.white));
                tvWithinCity.setBackgroundColor(getResources().getColor(R.color.orange));

                tvOutsideCity.setTextColor(getResources().getColor(R.color.white));
                tvOutsideCity.setBackgroundColor(getResources().getColor(R.color.grey));

             }
            break;


        }
    }
}