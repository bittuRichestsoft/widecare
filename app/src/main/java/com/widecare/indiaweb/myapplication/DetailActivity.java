package com.widecare.indiaweb.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.MyDataBases;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;

public class DetailActivity extends AppCompatActivity implements Asnychronus_notifier
{
    ListView orderviewlist;
    MyDataBases myDataBase;
    LinearLayout paymentdone;
  //  ScrollView scrollView;
    ArrayList<String> namee  = new ArrayList();
    ArrayList<String> pricee  = new ArrayList();
    int val=0,sum=0;
    String TAG="TAG";
    String order;
    String OrderId;
    WebView webView;
    String PAYMENTTYPE="LIVE";
    String status;
    String TOTAL="";
    TextView test;
    ArrayList<UserDataItems> arrayLists = new ArrayList<>();
    OrderViewAdapter orderViewAdapter;
    TextView sub,tot,email,phone,address;
    LinearLayout complete_lin;
    Shaved_shared_preferences shaved_shared_preferences;
    String userid, fname, lname, company, emaill, phonee, country, add1, add2, city, state, pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderplaced);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        orderviewlist = (ListView)findViewById(R.id.orderdonelist);
        sub = (TextView)findViewById(R.id.orderviewsub);
        tot = (TextView)findViewById(R.id.orderviewtotals);
        email = (TextView)findViewById(R.id.orderemail);
        phone = (TextView)findViewById(R.id.orderphone);
        complete_lin = (LinearLayout)findViewById(R.id.comppp);

        address = (TextView)findViewById(R.id.billing);
        test = (TextView)findViewById(R.id.test);


        myDataBase = new MyDataBases(getApplicationContext());
        shaved_shared_preferences = new Shaved_shared_preferences(getApplication());

        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }

        Intent in = getIntent();
        order = in.getStringExtra("order");
        status = in.getStringExtra("status");


        userid = shaved_shared_preferences.get_userid();
        fname = shaved_shared_preferences.get_user_fname();
        lname = shaved_shared_preferences.get_user_lsname();
        company = shaved_shared_preferences.get_user_cname();
        city = shaved_shared_preferences.get_user_town();
        state = shaved_shared_preferences.get_user_state();
        pincode = shaved_shared_preferences.get_user_pincode();
        emaill = shaved_shared_preferences.get_user_email();
        phonee = shaved_shared_preferences.get_user_phone();
        country = shaved_shared_preferences.get_user_country();
        add1 = shaved_shared_preferences.get_user_add();
        add2 = shaved_shared_preferences.get_user_add();

        complete_lin.setVisibility(View.GONE);

        Log.e("SSSSSSSS","---------"+status);


        if(status.equalsIgnoreCase("completed"))
        {
            complete_lin.setVisibility(View.GONE);
        }
        else {
            complete_lin.setVisibility(View.VISIBLE);

        }

        Senddataa();

      Log.e("orderrr ",""+order);

        String pname = shaved_shared_preferences.get_user_fname();
        String lname = shaved_shared_preferences.get_user_lsname();
        String cname = shaved_shared_preferences.get_user_cname();
        String addr = shaved_shared_preferences.get_user_add();
        String town = shaved_shared_preferences.get_user_town();
        String state = shaved_shared_preferences.get_user_state();
        String country = shaved_shared_preferences.get_user_country();
        String pin = shaved_shared_preferences.get_user_pincode();

        address.setText(pname+" "+lname+ "\n" + cname + "\n" +addr+ "\n" + town + "\n" + state + "\n" + country+ "\n" + pin);

        complete_lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isOnline()) {

                    Log.e("bbbbbb", "--" + TOTAL);

               Intent i = new Intent(DetailActivity.this,PaymentScreen.class);
                    i.putExtra("pager", "4");
                    i.putExtra("order", ""+order);
                    i.putExtra("status","1");
                    i.putExtra("amount",""+TOTAL);
                    startActivity(i);
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

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
                Intent intent = new Intent(this, OrderActivity.class);
                intent.putExtra("pager","1");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack
                startActivity(intent);
                finish(); // call this to finish the current activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("pager","1");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear the back stack
        startActivity(intent);
        finish(); // call this to finish the current activity
    }

    private void Senddataa()
    {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Orderid");
            param_values.add(order);

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.userorder_details_url);
            js.setOnResultsListener(this);

        }

        catch (Exception e)
        {

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
        if (result != null && result.length() > 0)
        {
            Log.e("resultorder ",".. "+result);
            try {
                JSONObject jsonObject = result.getJSONObject("Order");
                String total = jsonObject.getString("total");
                String subtotal = jsonObject.getString("subtotal");
                String date  = jsonObject.getString("completed_at");
                String status = jsonObject.getString("status");
                String quantity = jsonObject.getString("total_line_items_quantity");

                JSONObject jsonObject1 = jsonObject.getJSONObject("shipping_address");
                JSONObject jsonObject2 = jsonObject.getJSONObject("billing_address");
                JSONArray jsonObject3 = jsonObject.getJSONArray("line_items");
                Log.e("resulttt ",".. "+jsonObject1);

                String pname = jsonObject1.getString("first_name");
                String pstate = jsonObject1.getString("state");
                String postcode = jsonObject1.getString("postcode");
                String add =  jsonObject1.getString("address_1");
                String emailadd =  jsonObject2.getString("email");
                String phonenum =  jsonObject2.getString("phone");

                Log.e("resulttt ", ".. " + pname + " " + pstate + " " + postcode + " " + add);

                email.setText(emailadd);
                phone.setText(phonenum);
                tot.setText(total);
                sub.setText(subtotal);

                TOTAL =total;


//                address.setText(pname+"\n"+add+"\n"+pstate+"\n"+postcode);

                Log.e("address ",""+pname+"--"+add+"--"+pstate+"--"+postcode);

               // orderviewlist.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, jsonObject3.length()*220));

                for(int i =0;i<jsonObject3.length();i++)
                {
                    UserDataItems userDataItems = new UserDataItems();

                    JSONObject jsonObjects = jsonObject3.getJSONObject(i);
                    String price = jsonObjects.getString("total");
                    String namee = jsonObjects.getString("name");
                    String quan = jsonObjects.getString("quantity");
                    String thum = jsonObjects.getString("thumbnail");

                    Log.e("myresulttt ", ""+thum);
                    test.setText("Order #" + order + " was placed on " + date + " and is currently " + status);


                    userDataItems.setPpname(namee);
                    userDataItems.setPprice(price);
                    userDataItems.setPpquantity(quan);
                    userDataItems.setPpimage(thum);

                    arrayLists.add(userDataItems);

                }

            }
            catch (JSONException e)
            {
                Log.e("Exception is", e.toString());
            }



            orderViewAdapter = new OrderViewAdapter(getApplicationContext(),R.layout.orderdetailview,arrayLists);
            orderviewlist.setAdapter(orderViewAdapter);
        }
    }
    @Override
    public Dialog onCreateDialog(int id) {
        return null;
    }

    ///////////////////////// trakn pay  /////////////////////




    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
