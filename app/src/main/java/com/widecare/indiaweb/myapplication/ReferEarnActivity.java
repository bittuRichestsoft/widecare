package com.widecare.indiaweb.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import Constant.Shaved_shared_preferences;

public class ReferEarnActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_referCode;
    ImageView iv_shareReferCode;
    Button btn_inviteFriends;
    Shaved_shared_preferences shaved_shared_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initalView();
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.refer_logo);
        }
        iv_shareReferCode.setOnClickListener(this);
        btn_inviteFriends.setOnClickListener(this);
    }

    private void initalView() {

        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());
        tv_referCode = (TextView) findViewById(R.id.tv_referCode);
        iv_shareReferCode = (ImageView) findViewById(R.id.iv_shareReferCode);
        btn_inviteFriends = (Button) findViewById(R.id.btn_inviteFriends);
        tv_referCode.setText("" + shaved_shared_preferences.get_refferCode());

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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_shareReferCode:
socialShare();
                break;

            case R.id.btn_inviteFriends:
                socialShare();
                break;

        }


    }

    private void socialShare() {

        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, "WideCare B2C");
        share.putExtra(Intent.EXTRA_TEXT, "Hey, Buy WIDECARE protection plans with discount of 30% on listed price. To accept, use referral code "+ shaved_shared_preferences.get_refferCode()+" to sign up. Enjoy!");
/*Hey, Buy WIDECARE protection plans with discount of 30% on listed price. To accept, use referral code 'navs785ue' to sign up. Enjoy!*/
        startActivity(Intent.createChooser(share, "Share to Friends !"));

    }
}
