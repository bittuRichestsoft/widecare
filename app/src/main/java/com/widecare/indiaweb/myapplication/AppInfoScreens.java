package com.widecare.indiaweb.myapplication;

import android.content.Intent;
import android.media.Image;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppInfoScreens extends AppCompatActivity {
TextView tv_nextAppInfoImage,tv_skipAppInfoImage;
ImageView iv_info_img;
int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info_screens);
        tv_nextAppInfoImage=(TextView)findViewById(R.id.tv_nextAppInfoImage);
        tv_skipAppInfoImage=(TextView)findViewById(R.id.tv_skipAppInfoImage);

        iv_info_img=(ImageView) findViewById(R.id.iv_info_img);
        tv_skipAppInfoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppInfoScreens.this, NavigationActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        });
        tv_nextAppInfoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(page==1)
                {
                    page++;

                    iv_info_img.setImageResource(R.drawable.app_info_slider_two);
                }
else                 if(page==2)
                {
                    iv_info_img.setImageResource(R.drawable.app_info_slider_three);
                    page++;
                }
                else                 if(page==3)
                {
                    Intent i = new Intent(AppInfoScreens.this, NavigationActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }
        });

    }
}
