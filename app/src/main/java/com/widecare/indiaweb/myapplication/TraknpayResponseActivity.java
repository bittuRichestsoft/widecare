package com.widecare.indiaweb.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.Shaved_shared_preferences;
import app.AppController;

public class TraknpayResponseActivity extends AppCompatActivity implements Asnychronus_notifier {
    String transactionId = "";
    String responseCode;
    String OrderId = "";
    String responseMessage = "";
    String UserId = "";
    private ProgressDialog pDialog;
    Shaved_shared_preferences shaved_shared_preferences;
    TextView responseMessageView,transactionIdView,res,create_new;
    LinearLayout download_link;

    String product_id= "";
    String _policynumber= "";
    String _strtdate= "";
    String _enddate= "";
    String _purchaseprice="";
    String _brand="";
    String _model="";
    String _type="";
    String _serial="";
    String _purchasedate = "";
    String _imei="";
    String iddd = "";
    String category = "";
    String categorys = "";

    String _age="";
    String _dob="";
    String _pan="";
    String _sex="";
    String _color="";

    String NAME="";
    String PRICE="";
    String ORDER="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traknpay_response);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Checking process...");
        pDialog.setCancelable(false);

        Bundle extras = getIntent().getExtras();

        shaved_shared_preferences =  new Shaved_shared_preferences(getApplicationContext());

        UserId =  shaved_shared_preferences.get_userid().toString();

        create_new = (TextView) findViewById(R.id.create_new);
        responseMessageView = (TextView) findViewById(R.id.responseMessageView);
        transactionIdView = (TextView) findViewById(R.id.transactionIdView);
        res = (TextView) findViewById(R.id.res);
        download_link = (LinearLayout) findViewById(R.id.download_link);

        if (extras != null) {
            transactionId = extras.getString("transactionId");
            responseCode = extras.getString("responseCode");
            responseMessage = extras.getString("responseMessage");
            OrderId = extras.getString("OrderId");
        }

        download_link.setVisibility(View.GONE);

        responseMessageView.setText(responseMessage);

        if (responseMessage.contains("failed")) {
            res.setText("SORRY!!");
            transactionIdView.setText("Transaction ID: " + transactionId + "\n" + "Your Order is Failed");//\nOrder ID: " + OrderId + "\n");

        } else if (responseMessage.contains("successful"))
        {
            sendData();
    }
    else {
            Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();
        }


        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent intent = new Intent(TraknpayResponseActivity.this,NavigationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        download_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent inq = new Intent(getApplicationContext(), PolicyAllDetails.class);
                inq.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                inq.putExtra("catid", "" + category);
                inq.putExtra("order", "" + ORDER);
                inq.putExtra("price", "" + PRICE);
                inq.putExtra("name", "" + NAME);
                inq.putExtra("date", "" );
                inq.putExtra("policy", "" + _policynumber);
                inq.putExtra("sdate", "" + _strtdate);
                inq.putExtra("edate", "" + _enddate);
                inq.putExtra("proid", "" + product_id);
                inq.putExtra("brand", "" + _brand);
                inq.putExtra("model", "" + _model);
                inq.putExtra("imei", "" + _imei);
                inq.putExtra("type", "" + _type);
                inq.putExtra("serial", "" + _serial);
                inq.putExtra("priceE", "" + _purchaseprice);
                inq.putExtra("dateE", "" + _purchasedate);

                inq.putExtra("age", "" + _age);
                inq.putExtra("sex", "" + _sex);
                inq.putExtra("dob", "" + _dob);
                inq.putExtra("pan", "" + _pan);
                inq.putExtra("color", "" + _color);

                startActivity(inq);
            }
        });
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra("pager","1");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack
        startActivity(intent);
        finish();

        // call this to finish the current activity
   /*     Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("pager","1");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack
        startActivity(intent);
        finish(); // call this to finish the current activity

*/
}

    private void sendData()
    {
        pDialog.show();
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("OrderId");
            param_values.add(""+OrderId);

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.type_Traknpay_complete);
            js.setOnResultsListener(this);

        } catch (Exception e)
        {

        }
    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result)
    {
        Log.e("message get", "" + result);
    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result)
    {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result)
    {
        Log.e("message_proces_complet", "" + result);
        pDialog.hide();
        if (result != null && result.length() > 0) {
            try {

                JSONObject jsonObject = result.getJSONObject("data");

                String value = jsonObject.getString("text");

                if(value.equals("1"))
                {
                    res.setText("THANKS YOU!!");
                    transactionIdView.setText("Transaction ID: " + transactionId + "\n" + "Your Order is Successfully Placed");//\nOrder ID: " + OrderId + "\n");

                    if (AppController.getInstance().isOnline()) {

                        FETCH(OrderId);


                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();

                }
            }
            catch (JSONException e)
            {
                Log.e("Exception is", e.toString());
            }

            pDialog.hide();
        }



        pDialog.hide();
    }

    private void FETCH(final String order)
    {
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Jason_Urls.order_details_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TAGGGGGG_PROBLEM", response.toString());
                        String userId = "";
                        String restaurantId = "";
                        try {
                            JSONObject result =  new JSONObject(response);

                            JSONObject jsonObject = result.getJSONObject("orders");

                            for (int i = 1; i <= jsonObject.length(); i++) {

                                String j = String.valueOf(i);
                                JSONObject jsonObject1 = jsonObject.getJSONObject(j);

                                JSONObject jsonObject11 = jsonObject1.getJSONObject("shipping_address");
                                JSONObject jsonObject22 = jsonObject1.getJSONObject("billing_address");

                                String pname = jsonObject11.getString("first_name");
                                String pstate = jsonObject11.getString("state");
                                String postcode = jsonObject11.getString("postcode");
                                String address_1 = jsonObject11.getString("address_1");
                                String address_2 = jsonObject11.getString("address_2");
                                String city = jsonObject22.getString("city");
                                String country = jsonObject22.getString("country");
                                String emailadd = jsonObject22.getString("email");
                                String phonenum = jsonObject22.getString("phone");

                                String ordernumber = jsonObject1.getString("order_number");
                                String created = jsonObject1.getString("created_at");
                                String catId = jsonObject1.getString("catId");
                                String customer_id = jsonObject1.getString("customer_id");


                                if(catId.equalsIgnoreCase("0"))
                                {
                                    catId = "9";
                                }


                                JSONArray jsonArray = jsonObject1.getJSONArray("line_items");

                                for (int k = 0; k < jsonArray.length(); k++) {

                                    JSONObject jsonObject2 = jsonArray.getJSONObject(k);

                                    if(order.equalsIgnoreCase(ordernumber)) {
                                        NAME = jsonObject2.getString("name");
                                        PRICE = jsonObject2.getString("price");
                                        ORDER = ordernumber;

                                        JSONArray jsonArray1 = jsonObject2.getJSONArray("meta");
                                        for (int jj = 0; jj < jsonArray1.length(); jj++) {
                                            JSONObject jsonObject3 = jsonArray1.getJSONObject(jj);
                                            String label = jsonObject3.getString("label");

                                            Log.e("LABEL ", "" + label);

                                            category = catId;

                                            if (label.equals("_product_id")) {
                                                product_id = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_startdate")) {
                                                _strtdate = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_enddate")) {
                                                _enddate = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_policynumber")) {
                                                _policynumber = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_devicemake")) {
                                                _brand = jsonObject3.getString("value");

                                            }
                                            if (label.equals("_devicemodel")) {
                                                _model = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_devicetype")) {
                                                _type = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_brand")) {
                                                _brand = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_model")) {
                                                _model = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_type")) {
                                                _type = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_imei")) {
                                                _imei = jsonObject3.getString("value");
                                            }

                                            if (label.equals("_serial")) {
                                                _serial = jsonObject3.getString("value");
                                            }

                                            if (label.equals("_purchasedate")) {
                                                _purchasedate = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_purchaseprice")) {
                                                _purchaseprice = jsonObject3.getString("value");
                                            }

                                            _purchaseprice = _strtdate;


                                            if (label.equals("_age")) {
                                                _age = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_dob")) {
                                                _dob = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_pan")) {
                                                _pan = jsonObject3.getString("value");
                                            }
                                            if (label.equals("_sex")) {
                                                _sex = jsonObject3.getString("value");
                                            }

                                            if (label.equals("_vehiclecolor")) {
                                                _color = jsonObject3.getString("value");
                                            }

                                            Log.e("jsonObject_data  ", "" + _brand + "--" + _model + "--" + _imei + "--" + _purchasedate + "--" + _purchaseprice);

                                            download_link.setVisibility(View.VISIBLE);
                                        }

                                    }
                                }

                            }
                        } catch (JSONException e) {
                            Log.e("Exception is", e.toString());

                        }

                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Slow Internet", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Userid", "" + UserId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
                Intent intent = new Intent(TraknpayResponseActivity.this,NavigationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TraknpayResponseActivity.this,NavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
