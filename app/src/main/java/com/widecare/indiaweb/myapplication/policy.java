package com.widecare.indiaweb.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Struct;
import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.MyDataBases;
import Constant.Orderdatabase;
import Constant.PlayGifV;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;

/**
 * Created by indiaweb on 9/17/2016.
 */
public class policy  extends Fragment implements Asnychronus_notifier {
    ListView orderlist;
    com.widecare.indiaweb.myapplication.OrderAdapter orderAdapter;
    ArrayList<UserDataItems> arrayLists = new ArrayList<>();
    Constant.Orderdatabase Orderdatabase;
    MyDataBases myDataBase ;
    public Button yes, no;
    PlayGifV pGiff;
    Dialog dialog;
    int connection=0;
    TextView t1,t2;
    Shaved_shared_preferences shaved_shared_preferences;
    ArrayList<String> ordernum  = new ArrayList();
    ArrayList<String> orderprice  = new ArrayList();
    ArrayList<String> orderquant  = new ArrayList();
    ArrayList<String> orderdate  = new ArrayList();
    ConnectivityManager connec;
    String TAG="policy ";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.policy, container, false);
        orderlist = (ListView)rootView.findViewById(R.id.orderlists);
        t1=(TextView)rootView.findViewById(R.id.textView5);
        t2=(TextView)rootView.findViewById(R.id.textView6);
        Orderdatabase = new Orderdatabase(getActivity());
        myDataBase = new MyDataBases(getActivity());
        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        connec = (ConnectivityManager)getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);

        if(shaved_shared_preferences.get_userid()=="" || shaved_shared_preferences.get_userid()==null )
        {
            Log.e("message112233o2", "" + shaved_shared_preferences.get_userid());

            //  Log.e("message11o", "" + "dfdfd");

        }
        else {
            if(isOnline())
            {
                Log.e("message112233o1",""+shaved_shared_preferences.get_userid());
                getalluserorders();
            }
            else
            {
                Toast.makeText(getActivity(), " Internet Not Connected ", Toast.LENGTH_LONG).show();

            }
        }


        //

      return rootView;
    }

    private void cart()
    {
        dialog.setContentView(R.layout.cart_dialog);
        TextView dialogButtonCancel = (TextView) dialog.findViewById(R.id.connn);
        TextView dialogButtonOk = (TextView) dialog.findViewById(R.id.carttt);
        // Click cancel to dismiss android custom dialog box
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Your android custom dialog ok action
        // Action for custom dialog ok button click
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        dialog.show();

    }

    private void getalluserorders()
    {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();
String apiUrl=Jason_Urls.userorder_url;
            params.add("Userid");
            param_values.add(shaved_shared_preferences.get_userid());


            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);

Log.e(TAG+"userorder_url",""+apiUrl+"?Userid="+shaved_shared_preferences.get_userid());
            js.execute(apiUrl);
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
        Log.e("resultss ", "" + result);

        if(result!=null && result.length()>0)
        {
            orderlist.setVisibility(View.VISIBLE);
            t1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            try {
                JSONArray jsonArray = result.getJSONArray("Order");

                for(int i = 0 ; i<jsonArray.length();i++)
                {
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

                    arrayLists.add(userDataItems);

                }
                orderlist.setVisibility(View.VISIBLE);
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                orderAdapter = new com.widecare.indiaweb.myapplication.OrderAdapter(getActivity(), R.layout.ordered_itemview, arrayLists);
                orderlist.setAdapter(orderAdapter);

            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        else
        {
            Log.e("result ","pagee") ;
            orderlist.setVisibility(View.GONE);
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.VISIBLE);

        }
    }
    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

}
