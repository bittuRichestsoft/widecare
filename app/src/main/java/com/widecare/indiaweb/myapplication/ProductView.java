package com.widecare.indiaweb.myapplication;
/*

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Asynchronus.*;
import Constant.PlayGifV;
import Constant.Shaved_shared_preferences;
import Constant.VariationRecord;
import app.AppController;

public class ProductView extends AppCompatActivity
{
    String catId,cname,cimage,proid,pname,pdesc,pimag;
    String priceitem;
    Shaved_shared_preferences shaved_shared_preferences;
    PlayGifV pGiff;
    WebView pcontent10;
    TextView type,ptitle,pdes,price;
    ImageView pimage;
    String message="Terms and Condition Not Available",variation_id="";
    LinearLayout expanddetails,bottom_view,device_type_linear;
    TextView expandBtn,expandBtn1;
    VariationRecord variationRecord;
    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> mylists = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }

        shaved_shared_preferences = new Shaved_shared_preferences(getApplication());
        pGiff = (PlayGifV) findViewById(R.id.viewGiff);
        pGiff.setImageResource(R.drawable.ringload);


        Intent i = getIntent();
        catId = i.getStringExtra("catid");
        cname = i.getStringExtra("cname");
        cimage = i.getStringExtra("cimage");
        proid = i.getStringExtra("proid");
        pname = i.getStringExtra("pname");
        pdesc = i.getStringExtra("pdes");
        pimag = i.getStringExtra("pimage");

        pcontent10 = (WebView) findViewById(R.id.pcontents10);
        ptitle = (TextView) findViewById(R.id.ptitle);
        type = (TextView) findViewById(R.id.type);
        pdes = (TextView) findViewById(R.id.pdes);
        pimage = (ImageView) findViewById(R.id.pimage);
        price = (TextView) findViewById(R.id.price);

        expandBtn1 = (TextView) findViewById(R.id.expandBtn1);
        expandBtn = (TextView) findViewById(R.id.expandBtn);

        device_type_linear = (LinearLayout) findViewById(R.id.device_type_linear);
        bottom_view = (LinearLayout) findViewById(R.id.bottoms);
        expanddetails = (LinearLayout) findViewById(R.id.expanddetails);

        if(AppController.getInstance().isOnline())
        {
            getData(catId);
          //  registerUser();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
        }

        mylist.clear();
        mylists.clear();

        ptitle.setText(pname);
        pdes.setText(pdesc);
        Picasso.with(getApplicationContext()).load(pimag).into(pimage);

        variationRecord = new VariationRecord(getApplicationContext());
        variationRecord.delall();

        expandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanddetails.setVisibility(View.VISIBLE);
                expandBtn.setVisibility(View.GONE);
                expanddetails.setVisibility(View.VISIBLE);
            }
        });

        expanddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanddetails.setVisibility(View.GONE);
                expanddetails.setVisibility(View.GONE);
                expandBtn.setVisibility(View.VISIBLE);
            }
        });

        if(AppController.getInstance().isOnline())
        {
            receiveData();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
        }

    }

    ////////////////////

    private void receiveData() {
        Log.e("resultttproductiddd", "" + proid);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Asynchronus.Jason_Urls.product_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.e("valuee2", "" + response);

                            JSONObject result = null;
                            try {
                                result = new JSONObject(response);

                                JSONObject jsonObject = result.getJSONObject("product");
                                JSONObject jsonObject1 = jsonObject.getJSONObject("post");

                                priceitem = jsonObject.getString("price");

                                price.setText("₹ " + priceitem);

                                Log.e("priceee123", "" + priceitem);


                                String post_content = jsonObject1.getString("post_content");

                                Log.e("valuee7 ", "" + jsonObject1.getString("post_content"));

                                pcontent10.loadDataWithBaseURL(null, post_content, "text/html", "utf-8", null);


                                String jsonst = result.getString("variations");
                                JSONObject terms = result.getJSONObject("Terms & Conditions");

                                Log.e("valuee3", "" + jsonst);

                                Log.e("valuee3termsterms", "" + terms);

                                JSONObject TERMS = terms.getJSONObject("TERMS");

                                String term = TERMS.getString("TERMS");

                                JSONObject EXCLUSIONS = terms.getJSONObject("EXCLUSIONS");

                                String excul = EXCLUSIONS.getString("EXCLUSIONS");

                                message = term + "\n" + excul;

                                Log.e("valuee3termsterms..", "" + term + "--" + excul);

                                if (jsonst.equals("null")) {
                                    String pricee = jsonObject.getString("price");
                                    Log.e("valuee4", "" + price);

                                    variationRecord.insertRecord(proid, variation_id, pricee, "0");
                                    Log.e("sizee2", "" + variationRecord.getAllUserAdd().size());

                                } else {
                                    JSONArray jsonObjec = result.getJSONArray("variations");

                                    Log.e("valuee6", "" + jsonObjec);

                                    for (int i = 0; i < jsonObjec.length(); i++) {
                                        JSONObject jsonObj = jsonObjec.getJSONObject(i);

                                        variation_id = jsonObj.getString("variation_id");
                                        Log.e("valuee6variation_id", "" + variation_id);
                                        String display_price = jsonObj.getString("display_price");

                                        String json = jsonObj.getJSONObject("attributes").toString();

                                        //  Log.e("tagg ",""+json);

                                        String[] val = json.replace("{", " ").replace("}", " ").split(":");

                                        String vset = val[0].replace("{", "");

                                        Log.e("taggGGGG ", "" + vset);

                                        if (vset.contains("device")) {
                                            type.setText("Invoice Slab Value");
                                            Log.e("tagg1 ", "" + vset);
                                        } else if (vset.contains("plan")) {
                                            type.setText("Plan Type");
                                            Log.e("tagg2 ", "" + vset);
                                        } else if (vset.contains("health")) {
                                            type.setText("Health Protection");
                                            Log.e("tagg3 ", "" + vset);
                                        } else if (vset.contains("protect")) {
                                            type.setText("Protection value");
                                        } else if (vset.contains("policy")) {
                                            type.setText("Policy Value");
                                        }
                                        String v = val[1];

                                        String value1 = v.substring(1, v.length() - 1);
                                        String value = value1.replaceAll("^\"|\"$", "");

                                        Log.e("bbbbbbvalue ", "" + value);


                                            variationRecord.insertRecord(proid, variation_id, display_price, value);
                                            price.setText("₹ " + price);

                                        Log.e("sizee1", "" + variationRecord.getAllUserAdd().size());
                                        bottom_view.setVisibility(View.VISIBLE);
                                        device_type_linear.setVisibility(View.VISIBLE);
                                        pGiff.setVisibility(View.GONE);
                                    }
                                }

                            } catch (JSONException e) {

                            }
                            pGiff.setVisibility(View.GONE);
                        }
                        else {
                            pGiff.setVisibility(View.GONE);
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d("TAG", "Error: " + error.getMessage());

            }
        }) {

            */
/**
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
                params.put("Productid", ""+proid);

                return params;
            }

        };

        // Adding request to request queue

        // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    private void getData(final String CATID) {

        Log.e("select1234", "..." + CATID);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Asynchronus.Jason_Urls.type_model_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("select12345", response.toString());


                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String typeId = jsonObject.getString("typeId");
                                String brandId = jsonObject.getString("brandId");
                                String categoryIds = jsonObject.getString("categoryId");

                                if (categoryIds.equals(catId)) {
                                    JSONArray categorieslevelone = jsonObject.getJSONArray("categorieslevelone");

                                    for (int j = 0; j < categorieslevelone.length(); j++) {

                                        JSONObject jsonObject1 = categorieslevelone.getJSONObject(j);

                                        String attribute_name = jsonObject1.getString("attribute_name");

                                        if(proid.equals("431"))
                                        {
                                            if(attribute_name.equals("Mobile"))
                                            {
                                                if (type_array.contains(attribute_name)) {
                                                    Log.e("device1234567 ", "" + attribute_name);
                                                } else {
                                                    type_array.add(attribute_name);
                                                    Log.e("device1234568 ", "" + attribute_name);

                                                }
                                            }
                                        }
                                        else {
                                            if (type_array.contains(attribute_name)) {
                                                Log.e("device1234567 ", "" + attribute_name);
                                            } else {
                                                type_array.add(attribute_name);
                                                Log.e("device1234568 ", "" + attribute_name);

                                            }
                                        }

                                        JSONArray categoriesleveltwo = jsonObject1.getJSONArray("categoriesleveltwo");

                                        if(categoriesleveltwo.length()==0)
                                        {
                                            Log.e("deviceccccc ", "" + categoriesleveltwo.length());
                                            Log.e("devicecccccc ", "" + attribute_name + "--" + "NULL" + "--" + "NULL");
                                            HashMap<String, String> map = new HashMap<String, String>();

                                            map.put("type", attribute_name);
                                            map.put("product", "");
                                            map.put("model", "");
                                            mylist.add(map);
                                        }
                                        else {
                                            Log.e("devicedddddddd", "" + categoriesleveltwo.length());

                                            for (int k = 0; k < categoriesleveltwo.length(); k++) {
                                                JSONObject jsonObject2 = categoriesleveltwo.getJSONObject(j);

                                                String attribute_name1 = jsonObject2.getString("attribute_name");


                                                JSONArray categorieslevelthree = jsonObject2.getJSONArray("categorieslevelthree");

                                                if(categorieslevelthree.length()==0)
                                                {
                                                    Log.e("deviceaaaaaaaa ", "" + categorieslevelthree.length());
                                                    Log.e("deviceaaaaaaaa ", "" + attribute_name + "--" + attribute_name1 + "--" + "NULL");
                                                    HashMap<String, String> map = new HashMap<String, String>();

                                                    map.put("type", attribute_name);
                                                    map.put("product", attribute_name1);
                                                    map.put("model", "");
                                                    mylist.add(map);
                                                }
                                                else {
                                                    Log.e("devicebbbbbbbb", "" + categorieslevelthree.length());
                                                    for (int l = 0; l < categorieslevelthree.length(); l++) {
                                                        JSONObject jsonObject3 = categorieslevelthree.getJSONObject(l);

                                                        String attribute_name2 = jsonObject3.getString("attribute_name");

                                                        Log.e("devicebbbbbbbb ", "" + attribute_name + "--" + attribute_name1 + "--" + attribute_name2);

                                                        HashMap<String, String> map = new HashMap<String, String>();

                                                        map.put("type", attribute_name);
                                                        map.put("product", attribute_name1);
                                                        map.put("model", attribute_name2);
                                                        mylist.add(map);

                                                    }
                                                }
                                            }
                                        }
                                    }
                                    //	msgResponse.setText(response.toString());
                                }
                            }
                        } catch (JSONException e)
                        {
                            //  progressDialog.hide();
                            e.printStackTrace();
                        }


                        // Creating adapter for spinner
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ProductView.this, R.layout.textview_with_background, type_array) {

                            @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View v = null;
                                v = super.getDropDownView(position, null, parent);
                                // If this is the selected item position
                                if (position == 0) {

                                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                    text.setTextColor(Color.parseColor("#3d406b"));

                                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                                    v.setBackgroundColor(backgroundColor);

                                } else {
                                    // for other views
                                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                    text.setTextColor(Color.parseColor("#3d406b"));
                                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                                    v.setBackground(backgroundColor);

                                }
                                return v;
                            }
                        };


                        // Drop down layout style - list view with radio button
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
                        // attaching data adapter to spinner

                        typee.setAdapter(dataAdapter);
                        // progressDialog.hide();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductView.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Id", ""+CATID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, SubCategory.class);
                intent.putExtra("catId", "" + catId);
                intent.putExtra("cname", "" + cname);
                intent.putExtra("cimage", "" + cimage);
                intent.putExtra("proid", "" + proid);
                intent.putExtra("pname", "" + pname);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed () {
        Intent intent = new Intent(this, SubCategory.class);
        intent.putExtra("catId", "" + catId);
        intent.putExtra("cname", "" + cname);
        intent.putExtra("cimage", "" + cimage);
        intent.putExtra("proid", "" + proid);
        intent.putExtra("pname", "" + pname);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}
*/
