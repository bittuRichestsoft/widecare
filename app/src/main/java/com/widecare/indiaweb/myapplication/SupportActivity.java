package com.widecare.indiaweb.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.CustomExpandableListAdapter;
import Model.ExpandableListDataPump;

public class SupportActivity extends AppCompatActivity {
    ExpandableListView exList ;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        exList = (ExpandableListView)findViewById(R.id.faqlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

           // getSupportActionBar().setTitle("FAQ SUPPORT");
            getSupportActionBar().setIcon(R.drawable.faq_logo);
        }


    expandableListView = (ExpandableListView) findViewById(R.id.faqlist);
    expandableListDetail = ExpandableListDataPump.getData();
    expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
    expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
    expandableListView.setAdapter(expandableListAdapter);
    expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

        @Override
        public void onGroupExpand(int groupPosition) {
//            Toast.makeText(getApplicationContext(),
//                    expandableListTitle.get(groupPosition) + " List Expanded.",
//                    Toast.LENGTH_SHORT).show();
        }
    });

    expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

        @Override
        public void onGroupCollapse(int groupPosition) {
//            Toast.makeText(getApplicationContext(),
//                    expandableListTitle.get(groupPosition) + " List Collapsed.",
//                    Toast.LENGTH_SHORT).show();

        }
    });

    expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
        int groupPosition, int childPosition, long id) {
            Toast.makeText(
                    getApplicationContext(),
                    expandableListTitle.get(groupPosition)
                            + " -> "
                            + expandableListDetail.get(
                            expandableListTitle.get(groupPosition)).get(
                            childPosition), Toast.LENGTH_SHORT
            ).show();
            return false;
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
