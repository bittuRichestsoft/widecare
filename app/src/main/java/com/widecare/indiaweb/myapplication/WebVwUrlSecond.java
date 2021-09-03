package com.widecare.indiaweb.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WebVwUrlSecond extends AppCompatActivity {
    WebView webViewData;
    String strHoldUrl="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_vw_url);

        webViewData = (WebView) findViewById(R.id.webVw_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String holdWebLink = b.getString("holdData");

        webVwLinkOpen(holdWebLink);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            if(holdWebLink.contains("how-to-claim"))
            {
                getSupportActionBar().setIcon(R.drawable.how_to_claim);

            }

            if(holdWebLink.contains("faq-app"))
            {
                getSupportActionBar().setIcon(R.drawable.faq_logo);

            }
            if(holdWebLink.contains("why-us-app"))
            {
                getSupportActionBar().setIcon(R.drawable.why_us);

            }
        }

    }

    private void webVwLinkOpen(String strHoldUrl) {


        WebSettings webSettings = webViewData.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webViewData.loadUrl(strHoldUrl);
        webViewData.setBackgroundResource(R.drawable.d);
        webViewData.setBackgroundColor(Color.TRANSPARENT);
        webViewData.getSettings().setJavaScriptEnabled(true);

        webViewData.setWebViewClient(new WebViewClient());

        Log.e("webVwLinkOpen","--"+strHoldUrl);

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
