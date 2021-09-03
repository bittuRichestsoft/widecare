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
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.PlayGifV;

public class ForgetPsd extends AppCompatActivity implements View.OnClickListener, Asnychronus_notifier , GoogleApiClient.OnConnectionFailedListener{
    private WebView wv1;
    PlayGifV pGif;
    TextView tv_forgetPsd;
    EditText et_emailPsd;
    private String strEmail="" ,TAG="ForgetPsd ";
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget);
        et_emailPsd=(EditText) findViewById(R.id.et_emailPsd) ;
        tv_forgetPsd=(TextView)findViewById(R.id.tv_forgetPsd) ;

        progressDoalog =  new ProgressDialog(ForgetPsd.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Please Wait....");
        progressDoalog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);


//        wv1=(WebView)findViewById(R.id.webView);
//        wv1.setWebViewClient(new MyBrowser());
//        wv1.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress) {
//
//                ForgetPsd.this.setProgress(progress * 100);
//
//                if (progress == 100)
//                    Log.e("mapping8 ", "map");
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }
//
//        pGif = (PlayGifV)findViewById(R.id.viewGifff);
//        pGif.setImageResource(R.drawable.loadbig);
//        Log.e("mapping1 ", "map");
//        wv1.getSettings().setLoadsImagesAutomatically(true);
//        Log.e("mapping2 ", "map");
//        wv1.getSettings().setJavaScriptEnabled(true);
//        Log.e("mapping3 ", "map");
//        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        Log.e("mapping4 ", "map");
//        wv1.loadUrl(Jason_Urls.user_pass_url);
//        Log.e("mapping5 ", "map");
        tv_forgetPsd.setOnClickListener(this);
    }
//    private class MyBrowser extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            Log.e("mapping6 ", "map");
//            pGif.setVisibility(View.GONE);
//            wv1.setVisibility(View.VISIBLE);
//            Log.e("mapping7 ", "map");
//            return true;
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_forgetPsd:
                getETvalue();
                break;
        }
    }


    private void getETvalue() {
        String stringVal = et_emailPsd.getText().toString();
        boolean atleastOneAlpha = stringVal.matches(".*[a-zA-Z]+.*");
        if (atleastOneAlpha == true ) {
            if(!validateEmailId(stringVal))
                Toast.makeText(ForgetPsd.this,   "Please enter valid Email or Phone number",Toast.LENGTH_LONG).show();
             else
            {
                if(stringVal.contains("@"))
                {
                    strEmail=stringVal;
                }
                else{
                    strEmail=stringVal;
                }
                if(isOnline()) {
  //                  progressDoalog = ProgressDialog.show(this,null,"Please wait..");
          callForgetApi();
                }

            }
        } else if (stringVal.length() != 10) {
            Toast.makeText(ForgetPsd.this,   "Please enter valid Email or Phone number",Toast.LENGTH_LONG).show();
        } else {
            if(stringVal.contains("@"))
            {
                strEmail=stringVal;
            }
            else{
                strEmail=stringVal;
            }
            if(isOnline()) {
               callForgetApi();
            }
        }
    }

    private void callForgetApi()
    {
        progressDoalog.show();
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("number");
            param_values.add(strEmail);
/*

            params.add("Username");
            param_values.add(email.getText().toString());
*/

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);
String forgetPsd_Url=Jason_Urls.forgetPsd_Url;
Log.e(TAG+"forgetPsd_Url",""+forgetPsd_Url+"?number="+strEmail);
            js.execute(forgetPsd_Url);
            js.setOnResultsListener((Asnychronus_notifier) this);

        } catch (Exception e)
        {

        }
    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result)
    {
        Log.e("message get", "" + result);
    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result)
    {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result)
    {
        progressDoalog.hide();
        Log.e("login respo ",""+result);
        if (result != null && result.length() > 0) {
            try {
                String value = result.getString("text");
                String message= result.getString("message");
      if(value.equals("1"))
                {
              Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_LONG).show();

                }
            }
            catch ( Exception e)
            {
                Log.e(TAG+"forgetPsd Exception is", e.toString());
            }

            progressDoalog.hide();
        }
    }


    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }



    private boolean validateEmailId(String email) {
// TODO Auto-generated method stub
        final Pattern EMAIL_ADDRESS_PATTERN = Pattern
                .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                        + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "("
                        + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
                        + ")+");

        boolean validEmail = EMAIL_ADDRESS_PATTERN.matcher(email)
                .matches();
        // validate email address
        if (!validEmail) {
            return false;
            // return false;
        }
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
