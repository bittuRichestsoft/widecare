package com.widecare.indiaweb.myapplication;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.OrdersDatabase;
import Constant.PolicyAdapter;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;

public class AllPolicyActivity extends Fragment implements Asnychronus_notifier {
    ListView allpolicylist;
    OrdersDatabase orderdatabase;
    String oordernumber;
    ArrayList<UserDataItems> arrayLists = new ArrayList<>();
    Boolean bol=true;
    PolicyAdapter  orderViewAdapter;
    Shaved_shared_preferences shaved_shared_preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.activity_products, container, false);
        allpolicylist = (ListView)rootView.findViewById(R.id.allpolicylist);
        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());

        orderdatabase = new OrdersDatabase(getActivity());
        orderdatabase.delall();
        getalluserorders();

        return rootView;
    }

    private void getalluserorders()
    {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Userid");
            param_values.add(shaved_shared_preferences.get_userid());


            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);


            js.execute(Jason_Urls.userorder_url);
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
     //   Log.e("resultss ", "" + result);

        if(result!=null && result.length()>0 && bol==true)
        {
            try {
                JSONArray jsonArray = result.getJSONArray("Order");

                for(int i = 0 ; i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String ordernum = jsonObject.getString("order_number");


                //    Log.e("resultORDER ", "" + ordernum);

                    orderdatabase.insertRecord(ordernum);

                    data();
                }

            } catch (Exception ex) {
                ex.getMessage();
            }

        }

        if (result != null && result.length() > 0)
        {
          //  Log.e("resultorder ",".. "+result);
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
             //   Log.e("resulttt ",".. "+jsonObject1);

                String pname = jsonObject1.getString("first_name");
                String pstate = jsonObject1.getString("state");
                String postcode = jsonObject1.getString("postcode");
                String add =  jsonObject1.getString("address_1");
                String emailadd =  jsonObject2.getString("email");
                String phonenum =  jsonObject2.getString("phone");

            //    Log.e("resulttt ", ".. " + pname + " " + pstate + " " + postcode + " " + add);

                for(int i =0;i<jsonObject3.length();i++)
                {
                    UserDataItems userDataItems = new UserDataItems();

                    JSONObject jsonObjects = jsonObject3.getJSONObject(i);
                    String price = jsonObjects.getString("total");
                    String namee = jsonObjects.getString("name");
                    String quan = jsonObjects.getString("quantity");
                    String thum = jsonObjects.getString("thumbnail");

                    userDataItems.setPolicyname(namee);
                    userDataItems.setPolicydate(date);
                    userDataItems.setPolicyorder(oordernumber);

              //      Log.e("bbbbbb ", "" + namee+"  "+date+"  "+oordernumber);

                    arrayLists.add(userDataItems);

                }

            }
            catch (JSONException e)
            {
                Log.e("Exception is", e.toString());
            }

//            orderViewAdapter = new PolicyAdapter(getActivity(),R.layout.policy,arrayLists);
//            allpolicylist.setAdapter(orderViewAdapter);
        }
    }

    private void data()
    {
     orderdatabase.getAllName();
        for(String order: orderdatabase.getAllName())
        {
            oordernumber = order;

            datacall(oordernumber);
            bol=false;
          //  Log.e("orders ","getorder "+oordernumber);
        }
    }

    private void datacall(String oordernumber)
    {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Orderid");
            param_values.add(oordernumber);

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);


            js.execute(Jason_Urls.userorder_details_url);
            js.setOnResultsListener(this);

        }

        catch (Exception e)
        {

        }
    }
}
