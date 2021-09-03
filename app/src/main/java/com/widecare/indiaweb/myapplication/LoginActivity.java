package com.widecare.indiaweb.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.Orderdatabase;
import Constant.Shaved_shared_preferences;

public class LoginActivity extends AppCompatActivity implements Asnychronus_notifier ,  GoogleApiClient.OnConnectionFailedListener
{
    CallbackManager callbackManager;
    int connection=0;
  //  LoginButton loginButton;
    Orderdatabase orderdatabase;
    GoogleApiClient mGoogleApiClient;
    ImageView gmaillogin ,  gmaillogout;
    private static final int RC_SIGN_IN = 9001;
    String emailId;
    TextView regbtn, forgetBtn;
    Button loginBtn;
    EditText pass,email;
    ProgressDialog progressDoalog;
  //  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Shaved_shared_preferences shaved_shared_preferences = null;
  FrameLayout fl_fb;
    LoginButton  fbLoginButton;

    Button signOutGooglePlus,btn_revoke_access_google;
    GoogleSignInAccount google_plus_integration;  //com.google.android.gms.common.SignInButton
            TextView tv_gmail;
    ImageView imgvw_google;
LinearLayout ll_gmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orderdatabase = new Orderdatabase(getApplication());

        // instalizing the facebook sdk is method called from here

        facebookSDKInitialize();

        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.signin_logo);
        }

        progressDoalog =  new ProgressDialog(LoginActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Please Wait....");
        progressDoalog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);

        loginBtn = (Button)findViewById(R.id.login_Btn);
        regbtn = (TextView)findViewById(R.id.regBtn);
        forgetBtn = (TextView)findViewById(R.id.forgetBtn);
        pass = (EditText)findViewById(R.id.pass_login);
        email = (EditText)findViewById(R.id.email_login);

        email.setTypeface(null, Typeface.NORMAL);
        pass.setTypeface(null, Typeface.NORMAL);

        fl_fb = (FrameLayout) findViewById(R.id.fl_fb);
        fbLoginButton= (LoginButton) findViewById(R.id.fbLoginButton);
        fbLoginButton.setReadPermissions("email,publish_actions");

        imgvw_google = (ImageView)findViewById(R.id.imgvw_google);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            pass.setOnEditorActionListener(new DoneOnEditorActionListener());
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                        loginBtn.callOnClick();
                    }
                    return false;
                }

            });
        }

        imgvw_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                gmailBtn.setVisibility(View.GONE);*/
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(pass.getText().toString().length()>0 && email.getText().toString().length()>0)
                {
                   // String emailId = email.getText().toString().trim();

//                    if (emailId.matches(emailPattern))
//                    {
                    orderdatabase.delall();
                    shaved_shared_preferences.set_user_email(email.getText().toString());

                    if(isOnline()) {

                        //    progress = ProgressDialog.show(this,null,"Please wait..");

                        sendData();
                    }
                    else {

                        //  progress.dismiss();
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }



//                    }
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
//                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_LONG).show();
                }
//                Intent i = new Intent(LoginActivity.this,NavigationActivity.class);
//                startActivity(i);

            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              Intent i = new Intent(getApplicationContext(), ForgetPsd.class);
                startActivity(i);
                   //     Toast.makeText(getApplicationContext(),"Forget Password", Toast.LENGTH_SHORT).show();

                }
        });

        regbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);

            }
        });

        fl_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbLoginButton.performClick();
                getLoginDetails(fbLoginButton);
            }
        });
    }

    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onConnectionFailed( ConnectionResult connectionResult) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }


    protected void getLoginDetailsOld(LoginButton login_button) {

        // Callback registration
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult login_result) {


                AccessToken accessToken = login_result.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

                // graphic request is made to fetch the data i.e eamil

                GraphRequest request = GraphRequest.newMeRequest(
                        login_result.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                // result is a email as a response


                                try {
                                    emailId = object.getString("email");

                                    Log.e("Email id.. ", " " + email);

                                } catch (Exception ex) {

                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email");
                request.setParameters(parameters);
                request.executeAsync();

                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Login Successfully with facebook ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel() {
                // code for cancellation
            }

            @Override
            public void onError(FacebookException exception) {
                //  code to handle error
            }
        });
    }

    protected void getLoginDetails(LoginButton login_button) {
        // Callback registration
        Log.e("<><>fb", "getLoginDetails" );
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult login_result) {
                Log.e("<><>fb", "onSuccess" );

                getUserInfo(login_result);
            }

            @Override
            public void onCancel() {
                Log.e("<><>fb", "onCancel" );
                // code for cancellation
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("<><>fb", "onError" );
                //  code to handle error
            }
        });
    }

    /*
        To get the facebook user's own profile information via  creating a new request.
        When the request is completed, a callback is called to handle the success condition.
     */
    protected void getUserInfo(final LoginResult login_result) {
        Log.e("<><>fb", "getUserInfo" );

        GraphRequest data_request = GraphRequest.newMeRequest(
                login_result.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        Log.e("<><>json response", "" + response.toString());

                      //  signInWithFacebook(login_result.getAccessToken());

                        try {
                            JSONObject jObj = new JSONObject(response.toString());
                            JSONObject userDetail = jObj.getJSONObject("graphObject");
                            String name = userDetail.getString("name");
                            Log.e("fb user name", "" + name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("fb JSONException ", "" + e.toString());

                        }
                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

/*
    private void signInWithFacebook(AccessToken accessToken) {
        Log.e("signInWithFacebook:", "" + accessToken);

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            alertMessage.cancelDialog();
                            Log.e("signInWithCredential", "" + task.getException());
                            Toast.makeText(LoginScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String socialuid = task.getResult().getUser().getUid();
                            String name = task.getResult().getUser().getDisplayName();
                            String email = task.getResult().getUser().getEmail();
                            String image = task.getResult().getUser().getPhotoUrl().toString();
                            Log.e("--uid---", socialuid);
                            Log.e("--name---", name);
                            // Log.e("--email---", email)
                            addUserwithFacebook(socialuid);

                        }

                    }
                });

    }
*/

    private void sendData()
    {
        progressDoalog.show();
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Password");
            param_values.add(pass.getText().toString());

            params.add("Username");
            param_values.add(email.getText().toString());

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.login_url);
            js.setOnResultsListener(this);

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

        Log.e("login respo ",""+result);
        if (result != null && result.length() > 0) {
            try {
                String value = result.getString("text");


                if(value.equals("1"))
                {
                    String id = result.getString("userId");
                    String displayName = result.getString("displayName");
                    String phone = result.getString("billing_phone");
                    String emails = result.getString("billing_email");
                    String lname = result.getString("billing_last_name");
                    String fname = result.getString("billing_first_name");
                    String state = result.getString("billing_state");
                    String country = result.getString("billing_country");
                    String city = result.getString("billing_city");
                    String company = result.getString("Bday");
                    String postcode = result.getString("billing_postcode");
                    String add1 = result.getString("billing_address_1");
                    String add2 = result.getString("billing_address_2");
                    String gn = result.getString("gender");
                    String refer_code = result.getString("refer_code");

                    Log.e("result_value_id", "" + id);

                    shaved_shared_preferences.set_user_login(1);
                    shaved_shared_preferences.set_userid(id);
                    shaved_shared_preferences.set_user_display(displayName);
                    shaved_shared_preferences.set_user_gender(gn);
//                    shaved_shared_preferences.set_user_email(emails);
                    shaved_shared_preferences.set_user_cname(company);
                    shaved_shared_preferences.set_user_lsname(lname);
                    shaved_shared_preferences.set_user_fname(fname);
                    shaved_shared_preferences.set_user_state(state);
                    shaved_shared_preferences.set_user_country(country);
                    shaved_shared_preferences.set_user_phone(phone);
                    shaved_shared_preferences.set_user_town(city);
                    shaved_shared_preferences.set_user_add(add1);
                    shaved_shared_preferences.set_user_add1(add2);
                    shaved_shared_preferences.set_user_pincode(postcode);
                    shaved_shared_preferences.set_refferCode(""+refer_code);

                   /* email.setText(" ");
                    pass.setText(" ");
*/
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, NavigationActivity.class);
                    finish();
                    startActivity(intent);

                    progressDoalog.hide();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Username and Password not match and Incoreect", Toast.LENGTH_LONG).show();

                }
            }
            catch ( Exception e)
            {
                Log.e("login Exception is", e.toString());
            }

            progressDoalog.hide();
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

    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
