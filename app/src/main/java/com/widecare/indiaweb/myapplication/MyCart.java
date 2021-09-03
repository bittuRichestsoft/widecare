package com.widecare.indiaweb.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

import Asynchronus.Jason_Urls;
import Constant.MyDataBase;
import Constant.MyDataBases;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;
import adapter.CartAdapter;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by indiaweb on 9/17/2016.
 */
public class MyCart extends Fragment {
    ListView orderlist;
    LinearLayout carttotal;
    MyDataBases myDataBase;
    MyDataBase myDataBas;
    EditText et_promoCode;
    TextView sub,tot,proceed,itemcart,tv_promoCode;
    CartAdapter cartAdapter;
    Boolean amount = false;
    ArrayList<UserDataItems> arrayLists = new ArrayList<>();
    CartAdapter arrayAdapter;
    ArrayList<String> namee  = new ArrayList();
    ArrayList<String> pricee  = new ArrayList();
    ArrayList<String> imagee = new ArrayList<String>();
    ArrayList<String> varidd = new ArrayList<String>();

    int val=0,sum=0;
    String alltotal;

/*
    Log.e("xxxxxxxxxx ","--"+t);
    Log.e("xxxxxxxxxx ","--"+b);
    Log.e("xxxxxxxxxx ","--"+m);

    Log.e("xxxxxxxxxx ", "--" + inv_num);
    Log.e("xxxxxxxxxx ", "--" + im);
    Log.e("xxxxxxxxxx ", "--" + sr);
    Log.e("xxxxxxxxxx ", "--" + d);

    Log.e("xxxxxxxxxx ", "--" + pname);
    Log.e("xxxxxxxxxx ", "--" + pr);
    Log.e("xxxxxxxxxx ", "--" + add_variation);
    Log.e("xxxxxxxxxx ", "--" + proid);
    Log.e("xxxxxxxxxx ", "--" + add_variation_id);


    myDataBase.insertRecord(pname, pr, add_variation, "1", proid, add_variation_id, b, m, im, t, sr, d, Doc_num, rel, db, inv_num, pn, cl, Chasis, Regno, Value, SaleDate, nom_name, nom_db, kyc_num, extra1, extra2, extra3);


*/

    Shaved_shared_preferences shaved_shared_preferences = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.carts, container, false);

        orderlist=(ListView)rootView.findViewById(R.id.listViews);
        myDataBase = new MyDataBases(getActivity());
        myDataBas = new MyDataBase(getActivity());
        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());
        et_promoCode= (EditText) rootView.findViewById(R.id.et_promoCode);
        tv_promoCode= (TextView)rootView.findViewById(R.id.tv_promoCode);

        proceed = (TextView)rootView.findViewById(R.id.proceed);
        carttotal = (LinearLayout)rootView.findViewById(R.id.carttotal);
        sub = (TextView)rootView.findViewById(R.id.subtotal);
        tot = (TextView)rootView.findViewById(R.id.total);
        itemcart = (TextView)rootView.findViewById(R.id.itemcart);

   /*     if(myDataBas.getPid().size()>0)
        {
            for(String PID: myDataBas.getPid())
            {
                Log.e("pidddddddddddd xx","-------"+PID);
            }
        }*/

    /*    if(myDataBase.getAllPId().size()>0)
        {
            for(String PID: myDataBase.getAllPId())
            {
                Log.e("pidddddddddddd pp","-------"+PID);
            }
        }*/


//        if(myDataBas.getPImage().size()>0)
//        {
//            for(Bitmap PID: myDataBas.getPImage())
//            {
//                Log.e("pidddddddddddd img","-------"+PID);
//            }
//        }

        shaved_shared_preferences.set_claimId("");

        proceed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(shaved_shared_preferences.get_user_login()==1)
                {
                    if(amount==true) {
//Log.e("carttotal",""+tot.getText().toString());
                        Intent i = new Intent(getActivity(), PaymentScreen.class);
                        i.putExtra("order", "0");
                        i.putExtra("status","0");
                        i.putExtra("amount",""+sum);

                        startActivity(i);
                        getActivity().finish();
                    }
                    else
                    {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("PAYMENT ALERT")
                                .setContentText("For Payment user must have products in cart list !")
                                .show();
                    }
                 }
                else
                {
                Intent i = new Intent(getActivity(),LoginActivity.class);
                    startActivity(i);

                }
            }
        });




        if(myDataBase.getAllName().size()==0)
        {
            amount = false;
            UserDataItems userDataItems = new UserDataItems();
            userDataItems.setPpname("0");
            userDataItems.setPprice("0");

            arrayLists.add(userDataItems);

            itemcart.setText("You have 0 Items In Your Cart");
            orderlist.setVisibility(View.GONE);
           carttotal.setVisibility(View.GONE);
            proceed.setVisibility(View.GONE);
        }
        else {
            itemcart.setText("You have " + myDataBase.getAllName().size() + " Items In Your Cart");

           carttotal.setVisibility(View.VISIBLE);
          //  proceed.setVisibility(View.VISIBLE);
            orderlist.setVisibility(View.VISIBLE);
            for (String name : myDataBase.getAllName()) {

                amount = true;
                orderlist.setVisibility(View.VISIBLE);
                Log.e("namee1 ", "namee " + name);
                namee.add(name);

              //  int size = myDataBase.getAllName().size() * 600;

              //  orderlist.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, size));
            }


            for (String Price : myDataBase.getAllPrice()) {

                Log.e("namee2 ", "Price " + Price);
                pricee.add(Price);
            }

            for (String varid : myDataBase.getAllProId()) {

                Log.e("namee2 ", "varid " + varid);
                varidd.add(varid);
            }

            for (int i = 0; i < pricee.size(); i++) {

                UserDataItems userDataItems = new UserDataItems();
                Bitmap bitmaps=null;
                try {

                    bitmaps  = myDataBas.getBitmap(varidd.get(i));
                }
                catch (Exception ex)
                {
                    myDataBase.delall();
                    myDataBas.delall();

                    Intent intent = new Intent(getActivity(),NavigationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                Log.e("insertedddimage", "" + bitmaps);

                String name = namee.get(i);
                String price = pricee.get(i);
                String var = varidd.get(i);
                val = Integer.parseInt(price);

                sum = val + sum;

                sub.setText("Rs. " + String.valueOf(sum));
                tot.setText("Rs. " + String.valueOf(sum));

                alltotal = String.valueOf(sum);

                userDataItems.setPpname(name);
                userDataItems.setPprice(price);
                userDataItems.setPpVId(var);
                userDataItems.setPppimage(bitmaps);
                arrayLists.add(userDataItems);


            }

            arrayAdapter = new CartAdapter(getActivity(), R.layout.cart_single_order,orderlist, arrayLists);
            arrayAdapter.notifyDataSetChanged();
            orderlist.setAdapter(arrayAdapter);
        }

        return rootView;
    }

    private void registerUser(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Jason_Urls.categories_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAGGGGGG", response.toString());

                        try {
                            JSONObject result = new JSONObject(response);

                            JSONArray jsonArray = result.getJSONArray("data");

                            //   Log.e("length ", " " + jsonArray.length());

                            for (int i = 0; i < jsonArray.length(); i++)
                            {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String catid  = jsonObject.getString("catId");
                                String catname = jsonObject.getString("text");

                              //  if(catId.equals(catid))
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

                                    }

                                }

                            }
                            } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //  Toast.makeText(Products.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
              //  params.put("id",""+proid);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
