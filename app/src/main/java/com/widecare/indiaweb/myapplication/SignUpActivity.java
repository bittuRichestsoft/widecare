package com.widecare.indiaweb.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.Shaved_shared_preferences;

public class SignUpActivity extends AppCompatActivity implements Asnychronus_notifier {
    TextView submitBtn,cc;
    int connection=0;
    EditText userf,userl,pass,email,mobile,et_refferBy;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Shaved_shared_preferences shaved_shared_preferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shaved_shared_preferences =  new Shaved_shared_preferences(getApplicationContext());

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.signup_logo);
        }

        submitBtn = (TextView) findViewById(R.id.submit_Btn);
        userf = (EditText)findViewById(R.id.user_signInf);
        userl = (EditText)findViewById(R.id.user_signInl);
        pass = (EditText)findViewById(R.id.pass_signIn);
        email = (EditText)findViewById(R.id.email_signIn);
        mobile = (EditText)findViewById(R.id.mobile_number);
        et_refferBy = (EditText)findViewById(R.id.et_refferBy);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(userf.getText().toString().length()>0 &&userl.getText().toString().length()>0 && pass.getText().toString().length()>0 && email.getText().toString().length()>0)
                {
                    String emailId = email.getText().toString().trim();

                    if (emailId.matches(emailPattern))
                    {
                        if(isOnline()) {

                            //    progress = ProgressDialog.show(this,null,"Please wait..");

                            sendData();
                        }
                        else {

                            //  progress.dismiss();
                            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Fill All Fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendData()
    {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Firstname");
            param_values.add(userf.getText().toString());

            params.add("Lastname");
            param_values.add(userl.getText().toString());

            params.add("Password");
            param_values.add(pass.getText().toString());

            params.add("Email");
            param_values.add(email.getText().toString());

            params.add("Mobile");
            param_values.add(mobile.getText().toString());

            params.add("refer_by");
            param_values.add(et_refferBy.getText().toString());

            Log.e("Firstname",""+userf.getText().toString());
            Log.e("Lastname",""+userl.getText().toString());
            Log.e("Password",""+pass.getText().toString());
            Log.e("Email",""+email.getText().toString());
            Log.e("Mobile",""+mobile.getText().toString());
            Log.e("refer_by",""+et_refferBy.getText().toString());

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.register_url);
            js.setOnResultsListener(this);

        } catch (Exception e)
        {

        }
    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result)
    {
        Log.e("message get",""+result);
    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result)
    {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result)
    {
        Log.e("signup respo",""+result);//
        if (result != null && result.length() > 0) {
            try {
                String value = result.getString("text");
                String id = result.getString("userid");
                String loginerror = result.getString("loginerror");

                    Log.e("result value", "" + value);
                if(value.equalsIgnoreCase("1"))
                {
                    String refer_code= result.getString("refer_code");
                    String refer_By= result.getString("refer_By");

                    shaved_shared_preferences.set_user_login(1);
                    shaved_shared_preferences.set_userid(id);
                    shaved_shared_preferences.set_user_display(userf.getText().toString());
                    shaved_shared_preferences.set_user_fname(userf.getText().toString());
                    shaved_shared_preferences.set_user_lsname(userl.getText().toString());
                    shaved_shared_preferences.set_user_email(email.getText().toString());
                    shaved_shared_preferences.set_user_pincode("");
                    shaved_shared_preferences.set_user_state("");
                    shaved_shared_preferences.set_user_add("");
                    shaved_shared_preferences.set_user_add1("");
                    shaved_shared_preferences.set_user_cname("");
                    shaved_shared_preferences.set_user_country("");
                    shaved_shared_preferences.set_user_phone(mobile.getText().toString());
                    shaved_shared_preferences.set_user_town("");
                    shaved_shared_preferences.set_refferCode(""+refer_code);

                    Log.e("values.. ", "" + shaved_shared_preferences.get_user_email() + " " + shaved_shared_preferences.get_user_login());

                    Toast.makeText(getApplicationContext(), "Registered Successful", Toast.LENGTH_LONG).show();

                    userf.setText(" ");
                    userl.setText(" ");
                    email.setText(" ");
                    pass.setText(" ");
                    mobile.setText(" ");

                    Intent intent = new Intent(this, NavigationActivity.class);
                    finish();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), ""+loginerror, Toast.LENGTH_LONG).show();

                }
            }
            catch (Exception e)
            {
                Log.e("signup Exception is", e.toString());
            }
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
            super.onBackPressed();
            //    finish();
    }
    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
