package com.widecare.indiaweb.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.MyDataBase;
import Constant.MyDataBases;
import Constant.Orderdatabase;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;

public class OrderActivity extends AppCompatActivity implements Asnychronus_notifier{
    ListView orderlist;
    OrderAdapter orderAdapter;
    ArrayList<UserDataItems> arrayLists = new ArrayList<>();
    Orderdatabase Orderdatabase;
    MyDataBases myDataBase ;
    MyDataBase myDataBas;
    Shaved_shared_preferences shaved_shared_preferences;
    ArrayList<String> ordernum  = new ArrayList();
    ArrayList<String> orderprice  = new ArrayList();
    ArrayList<String> orderquant  = new ArrayList();
    ArrayList<String> orderdate  = new ArrayList();
    private ProgressDialog pDialog;
    TextView pending,complete,tv_emptyRecord;
    String STATUS = "processing";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderlist = (ListView)findViewById(R.id.orderlists);

        pending = (TextView)findViewById(R.id.pending);
        complete = (TextView)findViewById(R.id.complete);
        tv_emptyRecord= (TextView)findViewById(R.id.tv_emptyRecord);
        Orderdatabase = new Orderdatabase(getApplicationContext());
        myDataBase = new MyDataBases(getApplicationContext());
        myDataBas = new MyDataBase(getApplicationContext());
        shaved_shared_preferences = new Shaved_shared_preferences(getApplication());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.order_history);
        }

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Fetching Orders...");
        pDialog.setCancelable(false);

        myDataBase.delall();
        myDataBas.delall();

        if(isOnline()) {

            STATUS = "processing";

            getalluserorders();
        }
        else {


            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

        pending.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isOnline()) {

                    STATUS = "processing";

                    getalluserorders();
                }
                else {


                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }

                pending.setTextColor(getResources().getColor(R.color.orange));
                pending.setBackgroundColor(getResources().getColor(R.color.white));
                complete.setTextColor(getResources().getColor(R.color.border_color));
                complete.setBackground(getResources().getDrawable(R.drawable.box));

            }
        });

        complete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(isOnline()) {

                    STATUS = "completed";

                    getalluserorders();
                }
                else {


                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
                complete.setTextColor(getResources().getColor(R.color.orange));
                complete.setBackgroundColor(getResources().getColor(R.color.white));
                pending.setTextColor(getResources().getColor(R.color.border_color));
                pending.setBackground(getResources().getDrawable(R.drawable.boxx));
            }
        });

    }

    private void getalluserorders()
    {

        tv_emptyRecord.setText("No " + STATUS + " Record");

        pDialog.show();

        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Userid");
            param_values.add(shaved_shared_preferences.get_userid());

            Log.e("resultss","---------"+shaved_shared_preferences.get_userid());


            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.userorder_url);
            js.setOnResultsListener(this);

        } catch (Exception e) {

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
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, NavigationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
        Log.e("resultss ",""+result);
        arrayLists.clear();
orderlist.setVisibility(View.GONE);
        tv_emptyRecord.setVisibility(View.GONE);

        pDialog.hide();

        if(result!=null && result.length()>0) {
            try {
                String strOrder=result.getString("Order");
                if(strOrder.equalsIgnoreCase("null"))
                {
                   tv_emptyRecord.setVisibility(View.VISIBLE);
                }
                else {
                    JSONArray jsonArray = result.getJSONArray("Order");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        UserDataItems userDataItems = new UserDataItems();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String total = jsonObject.getString("total");
                        String quantity = jsonObject.getString("total_line_items_quantity");
                        String ordernum = jsonObject.getString("order_number");
                        String date = jsonObject.getString("created_at");
                        String status = jsonObject.getString("status");

                        Log.e("resultORDER ", "" + total + " " + quantity + " " + ordernum + " " + date);

                        userDataItems.setDate(date);
                        userDataItems.setOpricer(total);
                        userDataItems.setOitem(quantity);
                        userDataItems.setOnum(ordernum);
                        userDataItems.setStatus(status);

                        if (status.equalsIgnoreCase(STATUS)) {
                            arrayLists.add(userDataItems);
                        }


                    }
                    orderAdapter = new OrderAdapter(getApplicationContext(), R.layout.ordered_itemview, arrayLists);
                    orderlist.setAdapter(orderAdapter);
                    if (arrayLists.size() >= 1) {
                        orderlist.setVisibility(View.VISIBLE);
                        tv_emptyRecord.setVisibility(View.GONE);

                    } else {
                        tv_emptyRecord.setVisibility(View.VISIBLE);
                        orderlist.setVisibility(View.GONE);

                    }

                }
            } catch (Exception ex) {
                ex.getMessage();

            }
        }



        }
    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}

