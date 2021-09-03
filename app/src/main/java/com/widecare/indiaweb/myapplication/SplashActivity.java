package com.widecare.indiaweb.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Constant.Shaved_shared_preferences;
import Constant.PlayGifV;


public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    ImageView slide_image,imgVwSplashLogo;
     Animation zoomin,zoomout;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    String REGISTRATION_SUCCESS = "RegistrationSuccess";
    String REGISTRATION_ERROR = "RegistrationError";

    String  TAG="TAG SplashActivity ";
    Shaved_shared_preferences shaved_shared_preferences;
     @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());
      //  printHashKey(SplashActivity.this);
        imgVwSplashLogo = (ImageView) findViewById(R.id.imgVw_splashLogo);
        zoomin = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        zoomout = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        imgVwSplashLogo.setAnimation(zoomin);



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(shaved_shared_preferences.get_user_login() ==1 )
                {
                    Intent i = new Intent(SplashActivity.this, NavigationActivity.class);
                    startActivity(i);
   finish();
                }
                else
                {
                    Intent i = new Intent(SplashActivity.this,  NavigationActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);

//  initalFirebase();
  collectToken();
    }


    private void collectToken()
        {

//            String token = FirebaseInstanceId.getInstance().getToken();
            String token =            FirebaseInstanceId.getInstance().getId();
            Log.d("MYTAG", "Firebase_token " + token);
        }



    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.e(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }
}