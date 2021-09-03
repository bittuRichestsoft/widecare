package com.widecare.indiaweb.myapplication;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.UserDataItems;

/**
 * Created by indiaweb on 9/17/2016.
 */
public class Categories extends Fragment implements Asnychronus_notifier{
    GridView gv;
    public static ArrayList<UserDataItems> arrayList=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_screen, container, false);

        receiveData();

        gv = (GridView)rootView.findViewById(R.id.gridView1);
        return rootView;
    }

    private void receiveData() {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Universal");
            param_values.add("data");

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);
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

         //   Log.e("lengthh ", " " + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++)
            {
                UserDataItems userDataItems = new UserDataItems();


                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String imagee = jsonObject.getString("img");
                String catname = jsonObject.getString("text");

                //  Log.e("imag ",""+imagee);

            //    Log.e("text ", "" + catname);

                userDataItems.setName(catname);
                userDataItems.setImage(imagee);

                arrayList.add(userDataItems);
            }

       //     Log.e("sizee ",""+arrayList.size());

          //  gv.setAdapter(new CategoryAdapter(getActivity(), R.layout.program_list, arrayList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}