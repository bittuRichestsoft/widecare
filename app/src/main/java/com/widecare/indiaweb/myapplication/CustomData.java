package com.widecare.indiaweb.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.UserDataItems;

public class CustomData extends AppCompatActivity implements Asnychronus_notifier {
    GridView gv;
    public static ArrayList<UserDataItems> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        receiveData();

        gv = (GridView) findViewById(R.id.gridView1);

    }

    private void receiveData() {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Universal");
            param_values.add("data");

            Json_AsnycTask js = new Json_AsnycTask(CustomData.this, "post", params, param_values);
            js.execute(Jason_Urls.categories_url);
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
    public void onResultsSucceeded_Post(JSONObject result) {

        try {

            arrayList=new ArrayList<>();
            JSONArray jsonArray = result.getJSONArray("data");

            Log.e("length ", " " + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++)
            {
                UserDataItems userDataItems = new UserDataItems();


                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String imagee = jsonObject.getString("img");
                String catname = jsonObject.getString("text");

              //  Log.e("imag ",""+imagee);

                Log.e("text ", "" + catname);

                userDataItems.setName(catname);
                userDataItems.setImage(imagee);

                arrayList.add(userDataItems);
            }

            Log.e("sizee ",""+arrayList.size());

           // gv.setAdapter(new CategoryAdapter(this,R.layout.program_list, arrayList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Dialog onCreateDialog(int id) {
        return null;
    }

}