package com.widecare.indiaweb.myapplication;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.CategoryDataBase;
import Constant.PlayGifV;
import Constant.Shaved_shared_preferences;
import Constant.User;
import download.CheckForSDCard;

public class PolicyAllDetails extends AppCompatActivity implements Asnychronus_notifier {

    Boolean bol = false;
    Boolean boll = true;
    Boolean claim = false;
    ProgressDialog pDialog;
    String EcImage, TAG = "PolicyAllDetails ";
    String Userid;
    String Email;
    String Phone;
    ArrayList<User> arrayLists = null;
    TextView toll, email, sms, term, excusive, mypolicypurdate, TYPE, SERIAL, AGE, COLOR, DOB, SEX, PAN, Brand, MODEL, IMEI, PurchaseDate, PurchasePrice, myemail, myphone, mybillingadr, myname, myprice, myorder, mypolicy, sdate, edate;
    CategoryDataBase categoryDataBase;
    Shaved_shared_preferences shaved_shared_preferences;
    int connection = 0;
    ImageView imagedetails;
    LinearLayout intimate_linear, download_linear, ll_tab22, cd, pd, dd;
    LinearLayout a1, a2, a3, a4, a33, a44, a5, a6, a7, a8, a9, a10;
    String catnamee = "";
    LinearLayout pol1, pol2, pol3, pol4, pol5;
    TextView pol11, pol22, pol33, pol44, pol55, initmatedoc;
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotifyManager;

    String catid, brand, model, type, serial, dateE, priceE, imei, proid, sdates, edates, namee, date, order, price, name, state, city, country, postcode, address_1, address_2, policy, age, dob, pan, sex, color;

    String[] titles = new String[]{"Device Accidental Protection", "Device Theft Protection", "Device warranty & repair", "Data Protection and Security", "Travel protect value pack", "vehicle    Assistance", "Buyback and Trade-In", "Refurbishment  Devices"};
    String[] id = new String[]{"9", "10", "11", "12", "16", "30", "14", "15"};
    List<String> wordList, wordLists = null;
    TextView poname, poemail, pophone, poadd, podob, textserial, textimei;
    PlayGifV pGifviewterms, pGifviewexc;


    ProgressBar pb;
    Dialog dialog;
    int downloadedSize = 0;
    int totalSize = 0;
    TextView cur_val;
    String dwnload_file_path = "http://coderzheaven.com/sample_folder/sample_file.png";
    private int apiHit = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);
        pGifviewterms = (PlayGifV) findViewById(R.id.viewterms);
        pGifviewexc = (PlayGifV) findViewById(R.id.viewexc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.policy_logo);
        }

        pGifviewexc.setImageResource(R.drawable.small_giff);
        pGifviewterms.setImageResource(R.drawable.small_giff);

        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());

        Userid = shaved_shared_preferences.get_userid().toString();
        Email = shaved_shared_preferences.get_user_email().toString();
        Phone = shaved_shared_preferences.get_user_phone().toString();

        pDialog = new ProgressDialog(getApplicationContext());
        pDialog.setMessage("Loading...");
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);


        wordList = Arrays.asList(id);
        wordLists = Arrays.asList(titles);

        Intent i = getIntent();
        catid = i.getStringExtra("catid");
        date = i.getStringExtra("date");
        sdates = i.getStringExtra("sdate");
        edates = i.getStringExtra("edate");
        order = i.getStringExtra("order");
        price = i.getStringExtra("price");
        namee = i.getStringExtra("name");
        policy = i.getStringExtra("policy");
        proid = i.getStringExtra("proid");
        brand = i.getStringExtra("brand");
        model = i.getStringExtra("model");
        imei = i.getStringExtra("imei");
        type = i.getStringExtra("type");
        serial = i.getStringExtra("serial");
        priceE = i.getStringExtra("priceE");
        dateE = i.getStringExtra("dateE");

        Log.e(TAG + " getStringExtra ", "priceE-->" + priceE + "<>dateE -->" + dateE + "<>--order-->" + order);

        age = i.getStringExtra("age");
        sex = i.getStringExtra("sex");
        pan = i.getStringExtra("pan");
        color = i.getStringExtra("color");
        dob = i.getStringExtra("dob");

        Log.e("userorder ", "" + order + "--" + proid);

        arrayLists = new ArrayList<>();
        categoryDataBase = new CategoryDataBase(getApplicationContext());

        intimate_linear = (LinearLayout) findViewById(R.id.intimate_linear);
        download_linear = (LinearLayout) findViewById(R.id.download_linear);
        ll_tab22 = (LinearLayout) findViewById(R.id.ll_tab22);

        toll = (TextView) findViewById(R.id.toll);
        sms = (TextView) findViewById(R.id.sms);
        email = (TextView) findViewById(R.id.email);

        term = (TextView) findViewById(R.id.terms);
        excusive = (TextView) findViewById(R.id.exclusive);

        textimei = (TextView) findViewById(R.id.textimei);
        textserial = (TextView) findViewById(R.id.textserial);

        myemail = (TextView) findViewById(R.id.mymail);
        myphone = (TextView) findViewById(R.id.myphone);
        myname = (TextView) findViewById(R.id.mynamees);
        myprice = (TextView) findViewById(R.id.myprices);
        mypolicy = (TextView) findViewById(R.id.mypolicyp);
        myorder = (TextView) findViewById(R.id.myorderpolicy);

        poname = (TextView) findViewById(R.id.poname);
        poemail = (TextView) findViewById(R.id.poemail);
        podob = (TextView) findViewById(R.id.podob);
        poadd = (TextView) findViewById(R.id.poadd);
        pophone = (TextView) findViewById(R.id.pophone);


        Brand = (TextView) findViewById(R.id.Brand);
        MODEL = (TextView) findViewById(R.id.Model);
        IMEI = (TextView) findViewById(R.id.IMEI);
        SERIAL = (TextView) findViewById(R.id.SERIAL);
        TYPE = (TextView) findViewById(R.id.TYPE);
        PurchaseDate = (TextView) findViewById(R.id.PurchaseDate);
        PurchasePrice = (TextView) findViewById(R.id.PurchasePrice);

        AGE = (TextView) findViewById(R.id.Age);
        SEX = (TextView) findViewById(R.id.Sex);
        COLOR = (TextView) findViewById(R.id.COLOR);
        PAN = (TextView) findViewById(R.id.PAN);
        DOB = (TextView) findViewById(R.id.DOB);

        sdate = (TextView) findViewById(R.id.sdates);
        edate = (TextView) findViewById(R.id.edates);
        mypolicypurdate = (TextView) findViewById(R.id.mypolicypurdate);
        mybillingadr = (TextView) findViewById(R.id.mybillingadr);
        imagedetails = (ImageView) findViewById(R.id.imagedetailpolicy);

        cd = (LinearLayout) findViewById(R.id.cd);
        pd = (LinearLayout) findViewById(R.id.pd);
        dd = (LinearLayout) findViewById(R.id.dd);

        a1 = (LinearLayout) findViewById(R.id.a1);
        a2 = (LinearLayout) findViewById(R.id.a2);
        a3 = (LinearLayout) findViewById(R.id.a3);
        a4 = (LinearLayout) findViewById(R.id.a4);
        a33 = (LinearLayout) findViewById(R.id.a33);
        a44 = (LinearLayout) findViewById(R.id.a44);
        a5 = (LinearLayout) findViewById(R.id.a5);
        a6 = (LinearLayout) findViewById(R.id.a6);
        a7 = (LinearLayout) findViewById(R.id.a7);
        a8 = (LinearLayout) findViewById(R.id.a8);
        a9 = (LinearLayout) findViewById(R.id.a9);
        a10 = (LinearLayout) findViewById(R.id.a10);

        //   tdoc = (LinearLayout) findViewById(R.id.tdoc);

        pol1 = (LinearLayout) findViewById(R.id.pol1);
        pol2 = (LinearLayout) findViewById(R.id.pol2);
        pol3 = (LinearLayout) findViewById(R.id.pol3);
        pol4 = (LinearLayout) findViewById(R.id.pol4);
        pol5 = (LinearLayout) findViewById(R.id.pol5);

        pol11 = (TextView) findViewById(R.id.pol11);
        pol22 = (TextView) findViewById(R.id.pol22);
        pol33 = (TextView) findViewById(R.id.pol33);
        pol44 = (TextView) findViewById(R.id.pol44);
        pol55 = (TextView) findViewById(R.id.pol55);

        initmatedoc = (TextView) findViewById(R.id.initmatedoc);

        myname.setText(namee);

        for (int j = 0; j < wordList.size(); j++) {
            if (wordList.contains(catid)) {
                catnamee = wordLists.get(0);
            }
        }

        //device accidental
        if (proid.equals("245") || proid.equals("247") || proid.equals("251") || proid.equals("249")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);

        }
        //device theft
        if (proid.equals("422") || proid.equals("424") || proid.equals("425")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //device warrant repair
        if (proid.equals("427") || proid.equals("428") || proid.equals("429")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //protection and security
        if (proid.equals("431") || proid.equals("433") || proid.equals("434") || proid.equals("773")) {

            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //travel protection
        if (proid.equals("442") || proid.equals("443") || proid.equals("444")) {
            a6.setVisibility(View.VISIBLE);
            a7.setVisibility(View.VISIBLE);
            a8.setVisibility(View.VISIBLE);
            a9.setVisibility(View.VISIBLE);
        }
        //vehicle
        if (proid.equals("665") || proid.equals("667") || proid.equals("668")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
            a10.setVisibility(View.VISIBLE);

            textserial.setText("chassis No");
            textimei.setText("Reg No");
        }
        //Buyback
        if (proid.equals("436") || proid.equals("437")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Refurbishment
        if (proid.equals("439") || proid.equals("440")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Simply care
        if (proid.equals("1235")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Gizmo
        if (proid.equals("1237")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Gimmick
        if (proid.equals("1239")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Always Assure
        if (proid.equals("1241")) {
            a1.setVisibility(View.VISIBLE);
            a2.setVisibility(View.VISIBLE);
            a3.setVisibility(View.VISIBLE);
            a4.setVisibility(View.VISIBLE);
            a33.setVisibility(View.VISIBLE);
            a44.setVisibility(View.VISIBLE);
            a5.setVisibility(View.VISIBLE);
        }
        //Travel Delight
        if (proid.equals("1242")) {
            a6.setVisibility(View.VISIBLE);
            a7.setVisibility(View.VISIBLE);
            a8.setVisibility(View.VISIBLE);
            a9.setVisibility(View.VISIBLE);
        }
        //Health Value
        if (proid.equals("1243")) {
            a6.setVisibility(View.VISIBLE);
            a7.setVisibility(View.VISIBLE);
            a8.setVisibility(View.VISIBLE);
            a9.setVisibility(View.VISIBLE);
        }


        Brand.setText(brand);
        MODEL.setText(model);
        IMEI.setText(imei);
        TYPE.setText(type);
        SERIAL.setText(serial);
        mypolicy.setText(policy);
        myorder.setText(order);
        sdate.setText(sdates);
        edate.setText(edates);
        mypolicypurdate.setText(dateE);
        PurchasePrice.setText(priceE);
        PurchaseDate.setText(dateE);
        myprice.setText(price);

        AGE.setText(age);
        SEX.setText(sex);
        PAN.setText(pan);
        DOB.setText(dob);
        COLOR.setText(color);

        //fname
        if (shaved_shared_preferences.get_user_fname().length() > 0) {
            poname.setText(shaved_shared_preferences.get_user_fname().toString());
            Log.e("user1 ", "user");
        } else {
            poname.setText(" ");
        }

        //email
        if (shaved_shared_preferences.get_user_email().length() > 0) {
            poemail.setText(shaved_shared_preferences.get_user_email().toString());
            Log.e("user4 ", "user");
        } else {
            poemail.setText(" ");
        }
        //phone
        if (shaved_shared_preferences.get_user_phone().length() > 0) {
            pophone.setText(shaved_shared_preferences.get_user_phone().toString());
            Log.e("user5 ", "user" + shaved_shared_preferences.get_user_phone().toString());
        } else {
            pophone.setText(null);
        }

        //address
        if (shaved_shared_preferences.get_user_add().length() > 0) {
            poadd.setText(shaved_shared_preferences.get_user_add().toString());
            Log.e("user10 ", "user");
        } else {
            poadd.setText(null);
        }


        arrayLists = categoryDataBase.getData(order);

        for (User user : arrayLists) {
            myemail.setText(user.getMyemailadd());
            myphone.setText(user.getMyphonenum());

            name = user.getMyname();
            state = user.getMystate();
            postcode = user.getMypostcode();
            address_1 = user.getMyaddress_1();
            address_2 = user.getMyaddress_2();
            city = user.getMycity();
            country = user.getMycountry();

            mybillingadr.setText(name + "\n" + address_1 + "\n" + address_2 + "\n" + city + "\n" + state + "\n" + country + "\n" + postcode);
        }


        download_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(PolicyAllDetails.this);
                mBuilder.setContentTitle("Downloading images...").setContentText("Download in progress").setSmallIcon(R.drawable.ic_launcher);
                // Start a lengthy operation in a background thread
                mBuilder.setProgress(0, 0, true);
                mNotifyManager.notify(1, mBuilder.build());
                mBuilder.setAutoCancel(true);


                DonloadPolicy();


            }

        });

        intimate_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shaved_shared_preferences.set_catid(catid);
                shaved_shared_preferences.set_catname(catnamee);
                shaved_shared_preferences.set_proid(proid);
                shaved_shared_preferences.set_proname(namee);
                shaved_shared_preferences.set_order(order);

                shaved_shared_preferences.set_spinner("");
                shaved_shared_preferences.set_initmatetime("");
                shaved_shared_preferences.set_initmatedate("");
                shaved_shared_preferences.set_initmatedecription("");
                shaved_shared_preferences.set_initmatedob("");
                shaved_shared_preferences.set_initmateplace("");
                shaved_shared_preferences.set_initmateemail("");
                shaved_shared_preferences.set_initmatemobile("");

                shaved_shared_preferences.set_initmate_process(0);

                if (claim == false) {
                    Intent i = new Intent(getApplicationContext(), IntimateClaimActivity.class);
                    startActivity(i);

                    Log.e("aaa ", "" + catnamee + "--" + namee + "--" + catid + "--" + proid + "--" + order);
                } else {
                    Toast.makeText(getApplicationContext(), "Already In Claim", Toast.LENGTH_LONG).show();

                }
            }
        });

        pol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.setVisibility(View.GONE);
                pd.setVisibility(View.VISIBLE);
                dd.setVisibility(View.GONE);
                term.setVisibility(View.GONE);
                excusive.setVisibility(View.GONE);


                pol11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                pol22.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol33.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol44.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol55.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);

            }
        });
        pol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.setVisibility(View.VISIBLE);
                pd.setVisibility(View.GONE);
                dd.setVisibility(View.GONE);
                term.setVisibility(View.GONE);
                excusive.setVisibility(View.GONE);

                pol22.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                pol11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol33.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol44.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol55.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);

            }
        });
        pol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.setVisibility(View.GONE);
                pd.setVisibility(View.GONE);
                dd.setVisibility(View.VISIBLE);
                term.setVisibility(View.GONE);
                excusive.setVisibility(View.GONE);

                pol33.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                pol11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol22.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol44.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol55.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);

            }
        });
        pol4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.setVisibility(View.GONE);
                pd.setVisibility(View.GONE);
                dd.setVisibility(View.GONE);
                excusive.setVisibility(View.GONE);
                pGifviewexc.setVisibility(View.GONE);

                if (boll == true) {

                    term.setVisibility(View.GONE);
                    pGifviewterms.setVisibility(View.VISIBLE);

                } else {
                    term.setVisibility(View.VISIBLE);
                    pGifviewterms.setVisibility(View.GONE);
                }
                pol44.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                pol11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol22.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol33.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol55.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);

            }
        });
        pol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.setVisibility(View.GONE);
                pd.setVisibility(View.GONE);
                dd.setVisibility(View.GONE);

                term.setVisibility(View.GONE);
                pGifviewterms.setVisibility(View.GONE);

                if (boll == true) {

                    excusive.setVisibility(View.GONE);
                    pGifviewexc.setVisibility(View.VISIBLE);

                } else {
                    excusive.setVisibility(View.VISIBLE);
                    pGifviewexc.setVisibility(View.GONE);
                }


                pol55.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);

                pol11.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol22.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol33.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);
                pol44.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_side, 0);

            }
        });

/*
        tdoc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, 100);
                }
        });*/

        isInternetOn();

    }

    private void DonloadPolicy() {


        ArrayList<HashMap<String, String>> hashMapArrayList = new ArrayList<>();

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("pId", "" + proid);
        hashMap.put("oId", "" + order);
        hashMap.put("rId", "" + Userid);
        hashMap.put("mobileno", "" + Email);
        hashMap.put("custemail", "" + Phone);

        hashMapArrayList.add(hashMap);

        Log.e("vvvvvvvvvvvx ", "-pId-" + hashMapArrayList.get(0).get("pId"));
        Log.e("vvvvvvvvvvvx ", "-oId-" + hashMapArrayList.get(0).get("oId"));
        Log.e("vvvvvvvvvvvx ", "-rId-" + hashMapArrayList.get(0).get("rId"));
        Log.e("vvvvvvvvvvvx ", "-mobileno-" + hashMapArrayList.get(0).get("mobileno"));
        Log.e("vvvvvvvvvvvx ", "-custemail-" + hashMapArrayList.get(0).get("custemail"));


        String downloadURL = Jason_Urls.download_pdf + "productId=" + proid + "&orderId=" + order + "&rid=" + Userid;

        new DownloadTask(PolicyAllDetails.this, downloadURL);

        Log.e(TAG + " api_url ", downloadURL);

    }


    private void receiveData() {
        bol = true;
        try {

            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Universal");
            param_values.add("data");
            String apiBase = Jason_Urls.categories_url;
            Log.e(TAG + " api_url ", apiBase + "?Universal=data");
            apiHit = 2;
            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "get", params, param_values);
            js.execute(apiBase);
            js.setOnResultsListener(PolicyAllDetails.this);
        } catch (Exception e) {

        }

    }


    private void received() {
//        Log.e("userId", "" + "--"+shaved_shared_preferences.get_userid().toString());
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("userId");
            param_values.add(Userid);
            String apiBase = Jason_Urls.user_claims_url;
            Log.e(TAG + " api_url ", apiBase + "?userId=" + Userid);
            apiHit = 1;
            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);

            js.execute(apiBase);
            js.setOnResultsListener(this);

        } catch (Exception e) {
            e.getMessage();

        }
    }

    private void received_terms_exclusions() {
        //  Log.e("message", "" + "dfdfd");

        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();
            params.add("Userid");
            param_values.add(shaved_shared_preferences.get_userid().toString());
            // param_values.add("12");
            Log.e(TAG + " api_url ", Jason_Urls.order_details_url + "?Userid=" + shaved_shared_preferences.get_userid().toString());

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);

            js.execute(Jason_Urls.order_details_url);
            js.setOnResultsListener(this);

        } catch (Exception e) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //    finish();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null
                && data.getData() != null) {

            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(uri, projection, null,
                    null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();
            Log.e("pathhhh ", "" + picturePath);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Log.e("pathh2 ", "" + bitmap);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                EcImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                Log.e("Image gallery", "" + EcImage);


                picturePath = EcImage;
                Log.e("Image picturePath1", "" + EcImage);
                initmatedoc.setText(picturePath);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject jsonResult) {
        String result = String.valueOf(Html.fromHtml(Html.fromHtml(jsonResult.toString()).toString()));
        if (result != null && result.length() > 0 && apiHit == 1) {

            try {
                String jsonStr = result.toString();

                Log.e(TAG + " claims ", "" + jsonStr);

                JSONObject jsonObj = new JSONObject(jsonStr);

                JSONArray jsonArray = jsonObj.getJSONArray("Claims");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String userId = jsonObject.getString("userId");

                    if (userId.equals(shaved_shared_preferences.get_userid())) {
                        String categoryId = jsonObject.getString("categoryId");
                        String productId = jsonObject.getString("productId");
                        String mobileno = jsonObject.getString("mobileno");
                        String email = jsonObject.getString("email");
                        String place = jsonObject.getString("place");
                        String orderId = jsonObject.getString("orderId");

                        if (categoryId.equals(catid) && productId.equals(proid) && orderId.equals(order)) {
                            Log.e("catid ", "" + catid + "--" + categoryId + "proid " + proid + "--" + productId + "order " + order + "--" + orderId);

                            claim = true;
                            Log.e("CLAIM FOUND", "");
                        } else {
                            if (claim == true) {
                                claim = true;
                                Log.e("CLAIM FOUND NOW", "");
                            } else {
                                Log.e("CLAIM NOT FOUND", "");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(TAG + "claims Excep", e.toString());
            }
        }

        if (result != null && result.length() > 0 && bol == true && apiHit == 2) {

            try {
                Log.e(TAG + "orders ", result.toString());

                JSONObject jsonObject = jsonResult.getJSONObject("orders");

                for (int i = 1; i <= jsonObject.length(); i++) {

                    String j = String.valueOf(i);
                    JSONObject jsonObject1 = jsonObject.getJSONObject(j);

                    JSONObject jsonArrayy = jsonObject1.getJSONObject("TERMS");
                    JSONObject jsonArrayyy = jsonObject1.getJSONObject("EXCLUSIONS");

                    try {

                        String TERMS = jsonArrayy.getString(proid.toString());
                        String EXCLUSIONS = jsonArrayyy.getString(proid.toString());

                        if (boll == true) {
                            Log.e("qqqqqq TERMS", "" + TERMS);
                            Log.e("qqqqqq EXCLUSIONS", "" + EXCLUSIONS);
                            pGifviewterms.setVisibility(View.GONE);
                            pGifviewexc.setVisibility(View.GONE);
                            term.setText(TERMS);
                            excusive.setText(EXCLUSIONS);

                            boll = false;

                        }
                    } catch (Exception ex) {
                        Log.e(TAG + " TERMS/EXCLUSIONS ", "Exception " + ex);
                    }

                }
            } catch (Exception e) {
                Log.e(TAG + " orders ", "Exception " + e);

            }
        }

        if (bol == false) {
            receiveData();

        }
    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result) {
        Log.e("value1  ", "value " + result);
        pDialog.hide();
        try {
            Log.e("value  ", "value ");
            JSONArray jsonArray = result.getJSONArray("data");

            Log.e("length ", " " + jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String catid = jsonObject.getString("catId");
                String catname = jsonObject.getString("text");

                JSONArray jsonArrays = jsonObject.getJSONArray("products");

                for (int j = 0; j < jsonArrays.length(); j++) {
                    JSONObject jsonObjects = jsonArrays.getJSONObject(j);

                    String pid = jsonObjects.getString("ID");
                    String pname = jsonObjects.getString("post_title");
                    String pdes = jsonObjects.getString("post_excerpt");
                    String pimageS = jsonObjects.getString("productimage");

                    Log.e("productsiddTTT ", " " + pid + " " + pname + " " + pdes + " " + pimageS);

                    if (namee.equals(pname)) {

                        String pimage = jsonObjects.getString("productimage");

                        Picasso.with(getApplicationContext()).load(pimage).into(imagedetails);

                        Log.e("productsidd ", " " + pid + " " + pname + " " + pdes + " " + pimage);
                    }


                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        received_terms_exclusions();

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


    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {

            Log.e("connection1 ", "connected" + connection);
            // if connected with internet
            if (connection == 0) {
                received();

                connection = 1;
                Log.e("connection2 ", "connected" + connection);

                //   Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            }
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            connection = 0;
            pDialog.hide();
            Log.e("connection3 ", "connected" + connection);
            Toast.makeText(this, " Internet Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }


}

class DownloadTask {

    private static final String TAG = "Download Task";
    private Context context;
/*    android.support.v4.app.NotificationCompat.*/ NotificationCompat.Builder mBuilder;
    NotificationManager mNotifyManager;
    int percentage;

    private String downloadUrl = "", downloadFileName = "", downloadViewFile = "";
    private ProgressDialog progressDialog;

    public DownloadTask(Context context, String downloadUrl) {
        this.context = context;

        this.downloadUrl = downloadUrl;


        downloadFileName = "Widecare_order_" + downloadUrl.substring(downloadUrl.lastIndexOf('=') + 1, downloadUrl.length()) + ".pdf";//Create file name by picking download file name from URL
        //  downloadViewFile = "Widecare_order_" + downloadUrl.substring(downloadUrl.lastIndexOf('=') + 1, downloadUrl.length());//Create file name by picking download file name from URL
        Log.e(TAG, downloadFileName);

        //Start Downloading Task
        new DownloadingTask().execute();
    }

    private class DownloadingTask extends AsyncTask<Void, Integer, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Starting downloading...");
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void result) {

            Log.e("vaaaaaa ", "---" + result);


            try {
                if (outputFile != null) {

                    Toast.makeText(context, "Downloaded Successfully", Toast.LENGTH_SHORT).show();

                    mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    mBuilder = new NotificationCompat.Builder(context);
                    mBuilder.setContentTitle("Downloading images...").setContentText("Download completed").setSmallIcon(R.drawable.ic_launcher);

                    mBuilder.setProgress(100, 100, false);
                    // Displays the progress bar for the first time.
                    mNotifyManager.notify(1, mBuilder.build());
                    mBuilder.setAutoCancel(true);

                    progressDialog.setMessage("Downloading Completed...");
                    progressDialog.dismiss();


                    File file = new File(Environment.getExternalStorageDirectory() + "/WIDECARE POLICY PDF FILES/" + downloadViewFile);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                    intent = Intent.createChooser(intent, "Open File");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    // intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    context.startActivity(intent);

                } else {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    }, 3000);

                    Log.e(TAG, "Download Failed");

                }
            } catch (Exception e) {
                e.printStackTrace();

                //Change button text if exception occurs

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }


            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection

                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }

                int fileLength = c.getContentLength();

                //Get File if SD card is present
                if (new CheckForSDCard().isSDCardPresent()) {

                    apkStorage = new File(
                            Environment.getExternalStorageDirectory() + "/"
                                    + "WIDECARE POLICY PDF FILES");
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File

                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[2048];//Set buffer type
                long total = 0;
                int count = 0;//init length

                Log.e("vaaaaaa ", "" + is.read(buffer));

                //  while ((count = is.read(buffer)) != -1) {
                while ((count = is.read(buffer)) > 0) {
                    total += count;

                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    fos.write(buffer, 0, count);//Write new file

                    Log.e("vaaaaaa ", fileLength + "---" + total + "---" + count);


                }




           /*   byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                    */


                //Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            /*    mProgressDialog.setIndeterminate(false);
                mProgressDialog.setMax(100);
                mProgressDialog.setProgress(progress[0]);*/

            percentage = progress[0];

            progressDialog.setMessage("Downloading..." + percentage);
            progressDialog.show();

            Log.e("vaaaaaa____per", "---" + progress[0]);

/*
            float len = urlsToDownload.length;
            // When the loop is finished, updates the notification
            if (counter >= len - 1) {
                mBuilder.setContentTitle("Done.");
                mBuilder.setContentText("Download complete")
                        // Removes the progress bar
                        .setProgress(0, 0, false);
                mNotifyManager.notify(id, mBuilder.build());
            } else {
                int per = (int) (((counter + 1) / len) * 100f);
                Log.i("Counter", "Counter : " + counter + ", per : " + per);
                mBuilder.setContentText("Downloaded (" + per + "/100");
                mBuilder.setProgress(100, per, false);
                // Displays the progress bar for the first time.
                mNotifyManager.notify(id, mBuilder.build());
            }
            counter++;
            }*/


        }
    }
}

class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String event = intent.getExtras().getString(NLService.NOT_EVENT_KEY);
        Log.i("NotificationReceiver", "NotificationReceiver onReceive : " + event);
        if (event.trim().contentEquals(NLService.NOT_REMOVED)) {

        }
    }


}