package com.widecare.indiaweb.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import Constant.Shaved_shared_preferences;

/**
 * Created by indiaweb on 1/27/2017.
 */
public class TabFragment0 extends Fragment
{
    View rootview;
    Button initmateonline;
    TextView toll, email, sms;
    Shaved_shared_preferences shaved_shared_preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.tab_fragment_0, container, false);

        toll = (TextView)rootview. findViewById(R.id.call_icon);
        sms = (TextView)rootview. findViewById(R.id.msg_icon);
        email = (TextView)rootview. findViewById(R.id.email_icon);

        initmateonline = (Button)rootview. findViewById(R.id.initmateonline);


        toll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:123456789"));
                startActivity(callIntent);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "support@widecare.in"});
                email.putExtra(Intent.EXTRA_SUBJECT, "QUERY");
                email.putExtra(Intent.EXTRA_TEXT, "MESSAGE..");

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        initmateonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        Intent intent = new Intent(getActivity(), IntimateClaimActivity.class);
                        intent.putExtra("pager", "1");
                        startActivity(intent);
                        getActivity().finish();

            }
        });

        return  rootview;
    }
}