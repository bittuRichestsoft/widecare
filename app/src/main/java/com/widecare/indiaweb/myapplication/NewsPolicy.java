package com.widecare.indiaweb.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import Constant.CategoryDataBase;
import Constant.User;

public class NewsPolicy extends AppCompatActivity
{
    NewOrderAdapter neworderAdapter;
    ArrayList<User> arrayLists =null;
    ArrayList<User> arrayListss = null;
    ListView orderlist;
    String catid;
    CategoryDataBase categoryDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_policy);

        orderlist = (ListView)findViewById(R.id.listview);

        categoryDataBase = new CategoryDataBase(getApplicationContext());
        arrayLists = new ArrayList<>();
        arrayListss = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }

        Intent i = getIntent();
        catid = i.getStringExtra("catId");

        Log.e("catidssrr "," "+catid);

        for(String ctid:categoryDataBase.getIdds())
        {
            String cid =ctid;

            Log.e("catidss "," "+cid);
            if(cid.equals(catid))
            {
                Log.e("catidttt "," "+catid);

                arrayLists = categoryDataBase.getData(catid);

                arrayListss.addAll(arrayLists);

                arrayLists.clear();

                catid=null;

            }

        }

        neworderAdapter = new NewOrderAdapter(getApplicationContext(), R.layout.order_detail_list, arrayListss);
        neworderAdapter.notifyDataSetChanged();
        orderlist.setAdapter(neworderAdapter);

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
    public void onBackPressed() {
        super.onBackPressed();
        //    finish();
    }
}
