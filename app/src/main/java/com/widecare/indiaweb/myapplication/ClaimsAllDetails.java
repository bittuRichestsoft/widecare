package com.widecare.indiaweb.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.CategoryDataBase;
import Constant.User;

public class ClaimsAllDetails extends AppCompatActivity implements Asnychronus_notifier {
    ArrayList<User> arrayLists = null;
    TextView AGE, COLOR, DOB, SEX, PAN, Brand, TYPE, SERIAL, MODEL, IMEI, PurchaseDate, PurchasePrice, mypolicypurchase, myemail, myphone, mybillingadr, myname, myprice, myorder, mypolicy, sdate, edate;
    CategoryDataBase categoryDataBase;
    LinearLayout c1, d1, p1, c2, d2, p2;
    int connection = 0;
    TextView llclaim,llpolicy,lldevice,claims, cancel_claim;
    LinearLayout status_dialog, ll_tab2, ll_tab3, a1, a2, a3, a4, a33, a44, a5, a6, a7, a8, a9, a10;
    ImageView imagedetails;
    String brand, model, type, serial, dateE, priceE, imei, proid, namee, sdates, edates, date, order, price, name, state, city, country, postcode, address_1, address_2, policy, age, dob, pan, sex, color;
    MyCountDown timer;
    String idd;
    TextView         textimei ,textserial;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
  //  private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claims_all_details);

        Intent i = getIntent();
        idd = i.getStringExtra("catid");
        date = i.getStringExtra("date");
        sdates = i.getStringExtra("sdate");
        edates = i.getStringExtra("edate");
        order = i.getStringExtra("order");
        price = i.getStringExtra("price");
        namee = i.getStringExtra("name");
        policy = i.getStringExtra("policy");
        proid = i.getStringExtra("proid");
        brand = i.getStringExtra("brand");
        model = i.getStringExtra("model");
        imei = i.getStringExtra("imei");
        type = i.getStringExtra("type");
        serial = i.getStringExtra("serial");
        dateE = i.getStringExtra("priceE");
        priceE = i.getStringExtra("dateE");

        age = i.getStringExtra("age");
        sex = i.getStringExtra("sex");
        pan = i.getStringExtra("pan");
        color = i.getStringExtra("color");
        dob = i.getStringExtra("dob");

        Log.e("sex sex ", "" + age + sex);
        Log.e("prttt id ", "" + proid);

        arrayLists = new ArrayList<>();
        categoryDataBase = new CategoryDataBase(getApplicationContext());

        claims = (TextView) findViewById(R.id.claims);
        cancel_claim = (TextView) findViewById(R.id.cancel_claim);

        myemail = (TextView) findViewById(R.id.mymail);
        myphone = (TextView) findViewById(R.id.myphone);
        myname = (TextView) findViewById(R.id.mynameclaim);
        myprice = (TextView) findViewById(R.id.mypriceclaim);
        mypolicy = (TextView) findViewById(R.id.mypolicyc);
        myorder = (TextView) findViewById(R.id.myorderclaim);
        sdate = (TextView) findViewById(R.id.sdateclaim);
        edate = (TextView) findViewById(R.id.edateclaim);
        mybillingadr = (TextView) findViewById(R.id.mybillingadr);
        mypolicypurchase = (TextView) findViewById(R.id.purchaseclaim);
        c1 = (LinearLayout) findViewById(R.id.lclaim);
        c2 = (LinearLayout) findViewById(R.id.lclaims);
        d1 = (LinearLayout) findViewById(R.id.ldevice);
        d2 = (LinearLayout) findViewById(R.id.ldevices);
        p1 = (LinearLayout) findViewById(R.id.lpolicy);
        p2 = (LinearLayout) findViewById(R.id.lpolicys);

        status_dialog = (LinearLayout) findViewById(R.id.status_dialog);

        llclaim = (TextView) findViewById(R.id.llclaim);
        lldevice = (TextView) findViewById(R.id.lldevice);
        llpolicy = (TextView) findViewById(R.id.llpolicy);

        a1 = (LinearLayout) findViewById(R.id.a1);
        a2 = (LinearLayout) findViewById(R.id.a2);
        a3 = (LinearLayout) findViewById(R.id.a3);
        a4 = (LinearLayout) findViewById(R.id.a4);
        a33 = (LinearLayout) findViewById(R.id.a33);
        a44 = (LinearLayout) findViewById(R.id.a44);
        a5 = (LinearLayout) findViewById(R.id.a5);
        a6 = (LinearLayout) findViewById(R.id.a6);
        a7 = (LinearLayout) findViewById(R.id.a7);
        a8 = (LinearLayout) findViewById(R.id.a8);
        a9 = (LinearLayout) findViewById(R.id.a9);
        a10 = (LinearLayout) findViewById(R.id.a10);

        textimei = (TextView) findViewById(R.id.textimeii);
        textserial = (TextView) findViewById(R.id.textchs);

        ll_tab2 = (LinearLayout) findViewById(R.id.ll_tab2);
        ll_tab3 = (LinearLayout) findViewById(R.id.ll_tab3);

        Brand = (TextView) findViewById(R.id.Brand);
        MODEL = (TextView) findViewById(R.id.Model);
        IMEI = (TextView) findViewById(R.id.IMEI);
        SERIAL = (TextView) findViewById(R.id.SERIAL);
        TYPE = (TextView) findViewById(R.id.TYPE);
        PurchaseDate = (TextView) findViewById(R.id.PurchaseDate);
        PurchasePrice = (TextView) findViewById(R.id.PurchasePrice);

        AGE = (TextView) findViewById(R.id.Age);
        SEX = (TextView) findViewById(R.id.Sex);
        COLOR = (TextView) findViewById(R.id.COLOR);
        PAN = (TextView) findViewById(R.id.PAN);
        DOB = (TextView) findViewById(R.id.DOB);

        imagedetails = (ImageView) findViewById(R.id.imagedetailclaim);




        //device accidental
        if (proid.equals("245") || proid.equals("247") || proid.equals("251") || proid.equals("249")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);

        }
        //device theft
        if (proid.equals("422") || proid.equals("424") || proid.equals("425")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //device warrant repair
        if (proid.equals("427") || proid.equals("428") || proid.equals("429")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //protection and security
        if (proid.equals("431") || proid.equals("433") || proid.equals("434") || proid.equals("773")) {

            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //travel protection
        if (proid.equals("442") || proid.equals("443") || proid.equals("444")) {
            a6.setVisibility(View.VISIBLE);
            a7.setVisibility(View.VISIBLE);
            a8.setVisibility(View.VISIBLE);
            a9.setVisibility(View.VISIBLE);

        }
        //vehicle
        if (proid.equals("665") || proid.equals("667") || proid.equals("668")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
            a10.setVisibility(View.VISIBLE);

            textserial.setText("chassis No");
            textimei.setText("Reg No");
        }
        //Buyback
        if (proid.equals("436") || proid.equals("437")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Refurbishment
        if (proid.equals("439") || proid.equals("440")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Simply care
        if (proid.equals("1235")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Gizmo
        if (proid.equals("1237")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Gimmick
        if (proid.equals("1239")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Always Assure
        if (proid.equals("1241")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Travel Delight
        if (proid.equals("1242")) {
            a6.setVisibility(View.VISIBLE);
            a7.setVisibility(View.VISIBLE);
            a8.setVisibility(View.VISIBLE);
            a9.setVisibility(View.VISIBLE);
        }
        //Health Value
        if (proid.equals("1243")) {
            a6.setVisibility(View.VISIBLE);
            a7.setVisibility(View.VISIBLE);
            a8.setVisibility(View.VISIBLE);
            a9.setVisibility(View.VISIBLE);
        }

        Brand.setText(brand);
        MODEL.setText(model);
        IMEI.setText(imei);
        TYPE.setText(type);
        SERIAL.setText(serial);
        PurchaseDate.setText(dateE);
        PurchasePrice.setText(priceE);

        AGE.setText(age);
        SEX.setText(sex);
        PAN.setText(pan);
        DOB.setText(dob);
        COLOR.setText(color);

        myname.setText(namee);


        onResume();

        c2.setVisibility(View.VISIBLE);


        ll_tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claims.setTextColor(Color.parseColor("#2b2d49"));
                cancel_claim.setTextColor(Color.parseColor("#DCDCDC"));

                timer = new MyCountDown(1000, 100);
            }
        });
        ll_tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_claim.setTextColor(Color.parseColor("#2b2d49"));
                claims.setTextColor(Color.parseColor("#DCDCDC"));

                timer = new MyCountDown(1000, 100);
            }
        });

        status_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(ClaimsAllDetails.this).create();
                alertDialog.setTitle("Status of the claim");
                alertDialog.setMessage("a) InProcess             -   After Claim number has been generated, backend service provider is checking the details\n" +
                        "\nb) Documents Pending     -   After checking service provider finds some document are missing\n" +
                        "\nc) Cancel                -   If Customer himself closes claim due to wrong intimation or if he finds back his sset\n" +
                        "\nd) Closed                -   If Customer has not provided the pending documents for more than 30 days, Service provided Cancels\n" +
                        "\ne) Approved              -   If everything in order, service provider approves the claim with sanctioned amount\n" +
                        "\nf) Settled               -   Details of payment made to customer");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

        cancel_claim.setFocusableInTouchMode(true);

        claims.setFocusableInTouchMode(true);

        cancel_claim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_LONG).show();
            }
        });


        claims.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Toast.makeText(getApplicationContext(), "claims", Toast.LENGTH_LONG).show();
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setVisibility(View.VISIBLE);
                d2.setVisibility(View.GONE);
                p2.setVisibility(View.GONE);

                llclaim.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                llpolicy.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                lldevice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
            }
        });
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d2.setVisibility(View.VISIBLE);
                p2.setVisibility(View.GONE);
                c2.setVisibility(View.GONE);

                lldevice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                llclaim.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                llpolicy.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);

            }
        });
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2.setVisibility(View.VISIBLE);
                d2.setVisibility(View.GONE);
                c2.setVisibility(View.GONE);

                llpolicy.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                llclaim.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                lldevice.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // add back arrow to toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);

            arrayLists = categoryDataBase.getData(order);

            for (User user : arrayLists) {
                myemail.setText(user.getMyemailadd());
                myphone.setText(user.getMyphonenum());

                name = user.getMyname();
                state = user.getMystate();
                postcode = user.getMypostcode();
                address_1 = user.getMyaddress_1();
                address_2 = user.getMyaddress_2();
                city = user.getMycity();
                country = user.getMycountry();

                mybillingadr.setText(name + "\n" + address_1 + "\n" + address_2 + "\n" + city + "\n" + state + "\n" + country + "\n" + postcode);
            }

            myname.setText(namee);
            myorder.setText(order);
            sdate.setText(sdates);
            edate.setText(edates);
            mypolicy.setText(policy);
            myprice.setText(" ₹ " + price);
            mypolicypurchase.setText(date);
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
    protected void onResume() {
        super.onResume();
        isInternetOn();
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {

            Log.e("connection1 ", "connected" + connection);
            // if connected with internet
            if (connection == 0) {
                receiveData();
                connection = 1;
                Log.e("connection2 ", "connected" + connection);

                //   Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            }

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            connection = 0;
            Log.e("connection3 ", "connected" + connection);
            Toast.makeText(this, " Internet Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    private void receiveData() {
        try {

            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Universal");
            param_values.add("data");

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);
            js.execute(Jason_Urls.categories_url);
            js.setOnResultsListener(ClaimsAllDetails.this);
        } catch (Exception e) {

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //    finish();
    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result) {
        Log.e("value1  ", "value " + result);
        try {
            Log.e("value  ", "value ");
            JSONArray jsonArray = result.getJSONArray("data");

            Log.e("length ", " " + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String catid = jsonObject.getString("catId");
                String catname = jsonObject.getString("text");

                JSONArray jsonArrays = jsonObject.getJSONArray("products");

                for (int j = 0; j < jsonArrays.length(); j++) {
                    JSONObject jsonObjects = jsonArrays.getJSONObject(j);

                    String pid = jsonObjects.getString("ID");
                    String pname = jsonObjects.getString("post_title");
                    String pdes = jsonObjects.getString("post_excerpt");
                    String pimageS = jsonObjects.getString("productimage");

                    Log.e("productsiddTTT ", " " + pid + " " + pname + " " + pdes + " " + pimageS);

                    if (namee.equals(pname)) {

                        String pimage = jsonObjects.getString("productimage");

                        Picasso.with(getApplicationContext()).load(pimage).into(imagedetails);

                        Log.e("productsidd ", " " + pid + " " + pname + " " + pdes + " " + pimage);
                    }


                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private class MyCountDown extends CountDownTimer {
        long duration, interval;

        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
            start();
        }

        @Override
        public void onFinish() {

            cancel_claim.setTextColor(Color.parseColor("#DCDCDC"));
            claims.setTextColor(Color.parseColor("#DCDCDC"));
        }

        @Override
        public void onTick(long duration) {
            // could set text for a timer here
        }
    }
    @Override
    public Dialog onCreateDialog(int id) {
        return null;
    }

}
