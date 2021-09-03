package com.widecare.indiaweb.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.MyDataBases;
import Constant.Orderdatabase;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;
import app.AppController;

public class Pay extends AppCompatActivity implements Asnychronus_notifier {
    Button placeorder;
    String total;
    MyDataBases myDataBase;
    Orderdatabase orderdatabase;
    TextView alltotal;
    ArrayList<UserDataItems> arrayLists = new ArrayList<>();
    ArrayList<String> namee = new ArrayList();
    ArrayList<String> pricee = new ArrayList();
    int val = 0, sum = 0;
    String order;
    String quantity;
    String stringAGE = "";
    String stringPAN = "";
    String stringSEX = "";
    String stringDOB = "";
    String stringCLOR= "";

    String stringSR = "";
    String stringTY = "";
    String stringB = "";
    String stringM = "";
    String stringIM = "";
    String stringD = "";
    String stringP = "";
    String stringpid = "";
    String stringvid = "";
    String stringpr = "";
    String stringtl = "";
    String stringvar = "";
    String pid = "";
    String vid = "";
    String prid = "";
    String ttid = "";
    String varid = "";
    String b= "";
    String m ="";
    String im = "";
    String p = "";
    String d = "";
    String ty= "";
    String sr ="";
    String ag = "";
    String sex = "";
    String dob = "";
    String pan = "";
    String colr = "";
    Shaved_shared_preferences shaved_shared_preferences;
    String dd;
    String userid, fname, lname, company, email, phone, country, add1, add2, city, state, pincode;
    String quotes = "|";
    String[] str = {"January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"};
    private ProgressDialog pDialog;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }
        placeorder = (Button) findViewById(R.id.placeorder);
        alltotal = (TextView) findViewById(R.id.alltotal);
        myDataBase = new MyDataBases(getApplicationContext());
        orderdatabase = new Orderdatabase(getApplicationContext());
        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        Intent i = getIntent();
        total = i.getStringExtra("total");
        order = i.getStringExtra("order");

        Log.e("resultORDER 11", "" + order);

        alltotal.setText(total);

        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        dd = "" + str[month] + " " + date + ", " + year + "";

        userid = shaved_shared_preferences.get_userid();
        fname = shaved_shared_preferences.get_user_fname();
        lname = shaved_shared_preferences.get_user_lsname();
        company = shaved_shared_preferences.get_user_cname();
        city = shaved_shared_preferences.get_user_town();
        state = shaved_shared_preferences.get_user_state();
        pincode = shaved_shared_preferences.get_user_pincode();
        email = shaved_shared_preferences.get_user_email();
        phone = shaved_shared_preferences.get_user_phone();
        country = shaved_shared_preferences.get_user_country();
        add1 = shaved_shared_preferences.get_user_add();
        add2 = shaved_shared_preferences.get_user_add();


        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (shaved_shared_preferences.get_user_town().length() > 0 && shaved_shared_preferences.get_user_town() != null) {
//                                                    quantity = String.valueOf(pricee.size());
//
//                                                    orderdatabase.insertOrderRecord(order, total, dd, quantity);

                    sendcartserver();
                    //  makeJsonObjReq();

                } else {
                    deatils();

                    Toast.makeText(getApplicationContext(), "Please Fill all details in your Profile Account", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (myDataBase.getAllName().size() == 0) {
            UserDataItems userDataItems = new UserDataItems();
            userDataItems.setPpname("0");
            userDataItems.setPprice("0");

            arrayLists.add(userDataItems);
        }

        for (String name : myDataBase.getAllName()) {
            Log.e("namee1 ", "NAME " + name);
            namee.add(name);

            String proname = name + quotes + ttid;
            ttid = proname;
        }

        for (String Price : myDataBase.getAllPrice()) {
            Log.e("namee1 ", "PRICE " + Price);
            pricee.add(Price);

            String proprice = Price + quotes + prid;
            prid = proprice;

        }

        for (String variationid : myDataBase.getAllVarId()) {

            Log.e("namee1 ", "variationid " + variationid);
            String provid = variationid + quotes + vid;
            vid = provid;
        }

        for (String productid : myDataBase.getAllProId()) {
            Log.e("namee1 ", "productid " + productid);
            String propid = productid + quotes + pid;
            pid = propid;
        }

        for (String varation : myDataBase.getAllVaration()) {
            Log.e("namee1 ", "varation " + varation);
            String provar = varation + quotes + varid;
            varid = provar;
        }

        for (String Brand : myDataBase.getAllBrand()) {
            Log.e("namee1 ", "Brand " + Brand);
            String prob = Brand + quotes + b;
            b = prob;
        }
        for (String Model : myDataBase.getAllModel()) {
            Log.e("namee1 ", "MODEL " + Model);
            String prom= Model + quotes + m;
            m = prom;
        }

        for (String Imei : myDataBase.getAllImei()) {
            Log.e("namee1 ", "IMEI " + Imei);
            String proim = Imei + quotes + im;
            im = proim;
        }
        for (String Date : myDataBase.getAllDate()) {
            Log.e("namee1 ", "DATE_PUR " + Date);
            String prod = Date + quotes + d;
            d = prod;
        }
        for (String PRICE : myDataBase.getAllInvoice_Num()) {
            Log.e("namee1 ", "PRICE_PUR " + PRICE);
            String propr = PRICE + quotes + p;
            p = propr;
        }
        for (String PRICE : myDataBase.getAllType()) {
            Log.e("namee1 ", "TYPE " + PRICE);
            String propr = PRICE + quotes + ty;
            ty = propr;
        }
        for (String PRICE : myDataBase.getAllSerial()) {
            Log.e("namee1 ", "SERIAL " + PRICE);
            String propr = PRICE + quotes + sr;
            sr = propr;
        }
        for (String PRICE : myDataBase.getAllInvoice_Num()) {
            Log.e("namee1 ", "AGE " + PRICE);
            String propr = PRICE + quotes + ag;
            ag = propr;
        }
        for (String PRICE : myDataBase.getAllInvoice_Num()) {
            Log.e("namee1 ", "SEX " + PRICE);
            String propr = PRICE + quotes + sex;
            sex = propr;
        }
//        for (String PRICE : myDataBase.getAllDob()) {
//            Log.e("namee1 ", "DOB " + PRICE);
//            String propr = PRICE + quotes + dob;
//            dob = propr;
//        }
//        for (String PRICE : myDataBase.getAllInvoice_Num()) {
//            Log.e("namee1 ", "PAN " + PRICE);
//            String propr = PRICE + quotes + pan;
//            pan = propr;
//        }
        for (String PRICE : myDataBase.getAllColor()) {
            Log.e("namee1 ", "COLOR " + PRICE);
            String propr = PRICE + quotes + colr;
            colr = propr;
        }


        for (int j = 0; j < pricee.size(); j++) {
            UserDataItems userDataItems = new UserDataItems();

            String name = namee.get(j);
            String price = pricee.get(j);

            val = Integer.parseInt(price);

            sum = val + sum;


            userDataItems.setPpname(name);
            userDataItems.setPprice(price);

            arrayLists.add(userDataItems);
        }

        stringtl = method(ttid);
        stringpid = method(pid);
        stringpr = method(prid);
        stringvar = method(varid);
        stringvid = method(vid);

        stringB = method(b);
        stringM = method(m);
        stringIM = method(im);
        stringD = method(d);
        stringP = method(p);
        stringTY = method(ty);
        stringSR = method(sr);

        stringAGE = method(ag);
        stringDOB = method(dob);
        stringPAN = method(pan);
        stringCLOR = method(colr);
        stringSEX = method(sex);

        Log.e("dddddd ", " " +stringtl);
        Log.e("dddddd ", " " +stringpid);
        Log.e("dddddd ", " " +stringpr);
        Log.e("dddddd ", " " +stringvar);
        Log.e("dddddd ", " " +stringvid);

        Log.e("dddddd ", " " +stringB);
        Log.e("dddddd ", " " +stringM);
        Log.e("dddddd ", " " +stringIM);
        Log.e("dddddd ", " " +stringD);
        Log.e("dddddd ", " " +stringP);
        Log.e("dddddd ", " " +stringTY);
        Log.e("dddddd ", " " +stringSR);

        Log.e("dddddd ", " " +stringAGE);
        Log.e("dddddd ", " " +stringDOB);
        Log.e("dddddd ", " " +stringSEX);
        Log.e("dddddd ", " " +stringPAN);
        Log.e("dddddd ", " " +stringCLOR);

    }


    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='|') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    private void deatils() {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);

    }

    private void makeJsonObjReq() {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Jason_Urls.save_url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG" + "asm data", response.toString());

                        Log.e("resultORDER ", "" + response);
                        if(response!=null && response.length()>0)
                        {
                            try
                            {
                                order = response.getString("orderId");
                                Log.e("resultORDER ", "" + order);

                                if(order!=null) {
                                    Toast.makeText(getApplicationContext(), "Order "+order+" is Placed successfully", Toast.LENGTH_LONG).show();

                                    Intent i = new Intent(getApplicationContext(), OrderActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                            catch (Exception ex)
                            {
                                ex.getMessage();
                            }

                        }

                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                hideProgressDialog();
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

                params.put("Userid", userid);
                params.put("Brand", stringB);
                params.put("Model", stringM);
                params.put("Imei", stringIM);
                params.put("Type", stringTY);

                params.put("Serial", stringSR);
                params.put("Age", stringAGE);
                params.put("Sex", stringSEX);
                params.put("Dob", stringDOB);
                params.put("Pan", stringPAN);

                params.put("Color", stringCLOR);
                params.put("Purchasedate", stringD);
                params.put("Purchaseprice", stringP);
                params.put("Productid", stringpid);
                params.put("Variationid", stringvid);

                params.put("Priceid", stringpr);
                params.put("Titleid", stringtl);
                params.put("Devicevalue", stringvar.substring(1));
                params.put("Firstname", fname);
                params.put("Lastname", lname);

                params.put("Company", company);
                params.put("Email", email);
                params.put("Phone", phone);
                params.put("Country", country);
                params.put("Address1", add1);

                params.put("Address2", add2);
                params.put("City", city);
                params.put("State", state);
                params.put("Postcode", pincode);
                params.put("Total", String.valueOf(sum));

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    private void sendcartserver() {
        showProgressDialog();
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Userid");
            param_values.add(userid);

            params.add("Brand");
            param_values.add(stringB);

            params.add("Model");
            param_values.add(stringM);

            params.add("Imei");
            param_values.add(stringIM);

            params.add("Type");
            param_values.add(stringTY);

            params.add("Serial");
            param_values.add(stringSR);

            params.add("Age");
            param_values.add(stringAGE);

            params.add("Sex");
            param_values.add(stringSEX);

            params.add("Dob");
            param_values.add(stringDOB);

            params.add("Pan");
            param_values.add(stringPAN);

            params.add("Color");
            param_values.add(stringCLOR);

            params.add("Purchasedate");
            param_values.add(stringD);

            params.add("Purchaseprice");
            param_values.add(stringP);

            params.add("Productid");
            param_values.add(stringpid);

            params.add("Variationid");
            param_values.add(stringvid);

            params.add("Priceid");
            param_values.add(stringpr);

            params.add("Titleid");
            param_values.add(stringtl);

            params.add("Devicevalue");
            param_values.add(stringvar.substring(1));

            params.add("Firstname");
            param_values.add(fname);

            params.add("Lastname");
            param_values.add(lname);

            params.add("Company");
            param_values.add(company);

            params.add("Email");
            param_values.add(email);

            params.add("Phone");
            param_values.add(phone);

            params.add("Country");
            param_values.add(country);

            params.add("Address1");
            param_values.add(add1);

            params.add("Address2");
            param_values.add(add2);

            params.add("City");
            param_values.add(city);

            params.add("State");
            param_values.add(state);

            params.add("Postcode");
            param_values.add(pincode);

            params.add("Total");
            param_values.add(String.valueOf(sum));

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.save_url);
            js.setOnResultsListener(this);

        } catch (Exception e) {

        }
    }


    @Override
    public void onResultsSucceeded_Get(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result)
    {
        Log.e("resultORDER ", "" + result);
        if(result!=null && result.length()>0)
        {
            try
            {
                order = result.getString("orderId");
                Log.e("resultORDER ", "" + order);

                if(order!=null) {
                    Toast.makeText(getApplicationContext(), "Order "+order+" is Placed successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), OrderActivity.class);
                    startActivity(i);
                    finish();
                }
            }
            catch (Exception ex)
            {
                ex.getMessage();
            }

        }
        hideProgressDialog();
    }
    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
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
                Intent i = new Intent(Pay.this,NavigationActivity.class);
                i.putExtra("pager","4");
                startActivity(i);
                finish();
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
