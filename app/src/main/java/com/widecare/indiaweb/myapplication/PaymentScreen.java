package com.widecare.indiaweb.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.MyDataBase;
import Constant.MyDataBases;
import Constant.Orderdatabase;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;

public class PaymentScreen extends AppCompatActivity implements Asnychronus_notifier {
    Button placeorder;
    RelativeLayout paymentdone;
    float total=0;
    Boolean CASE= false;
    String CASE_PROID = "";
    String OrderId="";
    String textView="";
    MyDataBases myDataBase;
    MyDataBase ImageDb;
    Orderdatabase orderdatabase;
    TextView alltotal;
    String TAG="TAGG";
    ArrayList<HashMap<String,String>> arrayList_hashMap;
    ArrayList<UserDataItems> arrayLists = new ArrayList<>();
    ArrayList<String> namee = new ArrayList();
    ArrayList<String> pricee = new ArrayList();
    ScrollView scrollview;
    int val = 0, sum = 0;
    String order;
    String quantity;
    String stringAGE = "";
    String stringPAN = "";
    String stringINVNUM = "";
    String stringDOB = "";
    String stringCLOR= "";
    String Kycdocumenttype= "";
    String Nominee= "";
    String Nomineedob= "";
    String Relationship= "";
    RadioButton test,live;
    String PAYMENTTYPE="LIVE";
    String stringSR = "";
    String stringTY = "";
    String stringB = "";
    String stringM = "";
    String stringIM = "";
    String stringD = "";
    //String stringP = "";
    String stringpid = "";
    String stringvid = "";
    String stringpr = "";
    String stringtl = "";
    String stringvar = "";
    String stringim1 = "";
    String stringim2 = "";
    String stringim3 = "";
    String ttid = "";
    String varid = "";
    String b= "";
    String m ="";
    String im = "";
    String p = "";
    String d = "";
    String ty= "";
    String sr ="";
    String ag = "";
    String sex = "";
    String dob = "";
    String pan = "";
    String colr = "";
    String status = "0";
    String amount = "0";
    WebView webView;
    Shaved_shared_preferences shaved_shared_preferences;
    String dd;
    String userid, fname, lname, company, email, phone, country, add1, add2, city, state, pincode;
    String quotes = "|";
    String[] str = {"January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"};
    private ProgressDialog pDialog;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }
        placeorder = (Button) findViewById(R.id.placeorder);
        alltotal = (TextView) findViewById(R.id.alltotal);

        test = (RadioButton) findViewById(R.id.test);
        live = (RadioButton) findViewById(R.id.live);
         webView = (WebView) findViewById(R.id.webView);

        paymentdone = (RelativeLayout) findViewById(R.id.paymentdone);
        scrollview = (ScrollView) findViewById(R.id.scrollView);

        myDataBase = new MyDataBases(getApplicationContext());
        ImageDb = new MyDataBase(getApplicationContext());

        orderdatabase = new Orderdatabase(getApplicationContext());
        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        arrayList_hashMap = new ArrayList<>();
        arrayList_hashMap.clear();

        scrollview.setVisibility(View.GONE);
        paymentdone.setVisibility(View.VISIBLE);


        Intent i = getIntent();
        order = i.getStringExtra("order");
        status = i.getStringExtra("status");
        amount = i.getStringExtra("amount");

        Log.e("resultSORDER11", "" + order);
        Log.e("resultSSTATUS11", "" + status);
        Log.e("resultSSTATUS11", "" + amount);

        total = Float.parseFloat(amount);
        alltotal.setText(""+total);


        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        dd = "" + str[month] + " " + date + ", " + year + "";

        userid = shaved_shared_preferences.get_userid();
        fname = shaved_shared_preferences.get_user_fname();
        lname = shaved_shared_preferences.get_user_lsname();
        company = shaved_shared_preferences.get_user_cname();
        city = shaved_shared_preferences.get_user_town();
        state = shaved_shared_preferences.get_user_state();
        pincode = shaved_shared_preferences.get_user_pincode();
        email = shaved_shared_preferences.get_user_email();
        phone = shaved_shared_preferences.get_user_phone();
        country = shaved_shared_preferences.get_user_country();
        add1 = shaved_shared_preferences.get_user_add();
        add2 = shaved_shared_preferences.get_user_add();

        live.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {
                    PAYMENTTYPE = "LIVE";
                } else {
                    PAYMENTTYPE = "TEST";
                }
            }
        });

        test.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    PAYMENTTYPE = "TEST";
                } else {
                    PAYMENTTYPE = "LIVE";
                }
            }
        });


        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (shaved_shared_preferences.get_user_town().length() > 0 && shaved_shared_preferences.get_user_town() != null)
                {

                    if(isOnline()) {

                        if(status.equalsIgnoreCase("1")) {

                         //   total = Integer.parseInt(amount);

                            Log.e(TAG+"payment "," track_Pay --"+total);

                           TRAK_PAY();
                        }
                        else {
                            Log.e(TAG+"payment "," method--"+total);
                            method();
                        }
                    }
                    else {

                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }

                } else {
                    deatils();

                    Toast.makeText(getApplicationContext(), "Please Fill all details in your Profile Account", Toast.LENGTH_LONG).show();
                }
            }
        });


        if (myDataBase.getAllName().size() == 0 ||status.equalsIgnoreCase("1")) {
            UserDataItems userDataItems = new UserDataItems();
            userDataItems.setPpname("0");
            userDataItems.setPprice("0");

            arrayLists.add(userDataItems);

            alltotal.setText("Rs." + total);
        } else {


            for (String PRODUCT_ID : myDataBase.getAllProId()) {
                Log.e("namee1 ", "PRODUCT_ID " + PRODUCT_ID);


                //// special case hidden products  ////////////

                if(PRODUCT_ID.equalsIgnoreCase("5664") || PRODUCT_ID.equalsIgnoreCase("5669") ||PRODUCT_ID.equalsIgnoreCase("5684") || PRODUCT_ID.equalsIgnoreCase("5679")  || PRODUCT_ID.equalsIgnoreCase("5674"))
                {

                    CASE =  true;

                    CASE_PROID = PRODUCT_ID;

                    Log.e("namee1234____","---"+CASE_PROID);

                    //  copy same product to all below
                }

            }

               if(CASE==true)
               {
                String VARIATION1="";
                String VARIATION2="";
                if(CASE_PROID.equalsIgnoreCase("5664") )   {
                   VARIATION1 ="25000";
                   VARIATION2 ="300000";
                }
                else
                if(CASE_PROID.equalsIgnoreCase("5669") )   {
                    VARIATION1 ="50000";
                    VARIATION2 ="500000";
                }
                else
                if(CASE_PROID.equalsIgnoreCase("5684") )   {
                    VARIATION1 ="25000";
                    VARIATION2 ="1000000";
                }
                else
                if(CASE_PROID.equalsIgnoreCase("5679") )   {
                    VARIATION1 ="100000";
                    VARIATION2 ="1000000";
                }
                else
                if(CASE_PROID.equalsIgnoreCase("5674") )   {
                    VARIATION1 ="75000";
                    VARIATION2 ="750000";
                }

                myDataBase.insertRecord("Life Policy", "0", ""+VARIATION1, "1", "1339", "1373", b, m, im, "", sr, d, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
                myDataBase.insertRecord("Personal Accidental", "0", ""+VARIATION2, "1", "442", "442", b, m, im, "", sr, d, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

            }

            for (String PRODUCT_ID : myDataBase.getAllProId()) {
                Log.e("namee1 ", "PRODUCT_ID " + PRODUCT_ID);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("PRODUCT_ID", PRODUCT_ID);
                arrayList_hashMap.add(hashMap);

                //// special case hidden products  ////////////

            }


            for (String NAME : myDataBase.getAllName())
            {
                Log.e("namee1 ", "NAME " + NAME);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("NAME", NAME);
                arrayList_hashMap.add(hashMap);

            }

            for (String PRICE : myDataBase.getAllPrice())
            {
                Log.e("namee1 ", "PRICE " + PRICE);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("PRICE", PRICE);
                arrayList_hashMap.add(hashMap);
              //  stringpr = PRICE;

                int  pp  = Integer.parseInt(PRICE);
              //  total = pp+total;    commented

            //   total = 5;    commented

                alltotal.setText("Rs." + total);


              /*  String proprice = Price + quotes + prid;
                prid = proprice;*/

            }

            for (String VARIATION_ID : myDataBase.getAllVarId())
            {
                Log.e("namee1 ", "VARIATION_ID " + VARIATION_ID);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("VARIATION_ID", VARIATION_ID);
                arrayList_hashMap.add(hashMap);


            //   stringvid = VARIATION_ID;


                /*String provid = variationid + quotes + vid;
                vid = provid;*/
            }



            for (String PRODUCT_ID : myDataBase.getAllProId()) {
                Log.e("namee777 ", "PRODUCT_ID_image " + PRODUCT_ID);
                HashMap<String,String> hashMap  = new HashMap<>();


                Bitmap bitmaps1 = ImageDb.getBitmap1(PRODUCT_ID);
                Bitmap bitmaps2 = ImageDb.getBitmap2(PRODUCT_ID);
                Bitmap bitmaps3 = ImageDb.getBitmap3(PRODUCT_ID);

                String IMG1 = getStringImage(bitmaps1);
                String IMG2 = getStringImage(bitmaps2);
                String IMG3 = getStringImage(bitmaps3);

                hashMap.put("Original_dim13_IMG1", IMG1);
                hashMap.put("Original_dim13_IMG2", IMG2);
                hashMap.put("Original_dim13_IMG3", IMG3);

                arrayList_hashMap.add(hashMap);

                Log.e("Original_dim12_byte ", "" + bitmaps1);
                Log.e("Original_dim12_byte ", "" + bitmaps2);
                Log.e("Original_dim12_byte ", "" + bitmaps3);
                Log.e("Original_dim12_byte", "" + PRODUCT_ID);
            }



            for (String VARIATION : myDataBase.getAllVaration()) {
                Log.e("namee1 ", "VARIATION " + VARIATION);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("VARIATION", VARIATION);
                arrayList_hashMap.add(hashMap);
                /*String provar = varation + quotes + varid;
                varid = provar;*/


              //  stringvar = VARIATION;
            }
            for (String TYPE : myDataBase.getAllType()) {
                Log.e("namee1 ", "TYPE " + TYPE);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("TYPE", TYPE);
                arrayList_hashMap.add(hashMap);
              /*  String propr = PRICE + quotes + ty;
                ty = propr;*/

              //  stringTY = TYPE;


            }
            for (String MAKE : myDataBase.getAllBrand()) {
                Log.e("namee1 ", "MAKE " + MAKE);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("MAKE", MAKE);
                arrayList_hashMap.add(hashMap);
               /* String prob = Brand + quotes + b;
                b = prob;*/

              //  stringB = MAKE;



            }
            for (String MODEL : myDataBase.getAllModel()) {
                Log.e("namee1 ", "MODEL " + MODEL);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("MODEL", MODEL);
                arrayList_hashMap.add(hashMap);
              /*  String prom = Model + quotes + m;
                m = prom;*/

               // stringM = MODEL;

            }

            for (String IMEI : myDataBase.getAllImei()) {
                Log.e("namee1 ", "IMEI " + IMEI);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("IMEI", IMEI);
                arrayList_hashMap.add(hashMap);
               /* String proim = IMEI + quotes + im;
                im = proim;*/

             //   stringIM = IMEI;

            }
            for (String SERIAL : myDataBase.getAllSerial())
            {
                Log.e("namee1 ", "SERIAL " + SERIAL);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("SERIAL", SERIAL);
                arrayList_hashMap.add(hashMap);
              /*  String propr = SERIAL + quotes + sr;
                sr = propr;*/


              //  stringSR = SERIAL;
            }
            for (String INVOICE_NUM : myDataBase.getAllInvoice_Num())
            {
                Log.e("namee1 ", "INVOICE_NUM " + INVOICE_NUM);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("INVOICE_NUM", INVOICE_NUM);
                arrayList_hashMap.add(hashMap);
              /*  String propr = SERIAL + quotes + sr;
                sr = propr;*/

               // stringINVNUM = INVOICE_NUM;

            }

            for (String INVOICE_DATE : myDataBase.getAllDate()) {
                Log.e("namee1 ", "INVOICE_DATE " + INVOICE_DATE);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("INVOICE_DATE", INVOICE_DATE);
                arrayList_hashMap.add(hashMap);
              /*  String prod = Date + quotes + d;
                d = prod;*/

              //  stringD = INVOICE_DATE;

            }

            for (String RELATION : myDataBase.getAllRel()) {
                Log.e("namee1 ", "RELATION " + RELATION);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("RELATION", RELATION);
                arrayList_hashMap.add(hashMap);
              /*  String prod = Date + quotes + d;
                d = prod;*/

            //  String  Relationship = RELATION;

            }

            for (String KYCTYPE : myDataBase.getAllKycDocType()) {
                Log.e("namee1 ", "KYCTYPE " + KYCTYPE);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("KYCTYPE", KYCTYPE);
                arrayList_hashMap.add(hashMap);
              /*  String prod = Date + quotes + d;
                d = prod;*/
                //  String  Kycdocumenttype = KYCTYPE;

            }

            for (String NOMINEE : myDataBase.getAllnom_name()) {
                Log.e("namee1 ", "NOMINEE " + NOMINEE);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("NOMINEE", NOMINEE);
                arrayList_hashMap.add(hashMap);
              /*  String prod = Date + quotes + d;
                d = prod;*/
                //  String  Nominee = NOMINEE;

            }

            for (String NOMINEE_DOB : myDataBase.getAllnom_dob()) {
                Log.e("namee1 ", "NOMINEE_DOB " + NOMINEE_DOB);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("NOMINEE_DOB", NOMINEE_DOB);
                arrayList_hashMap.add(hashMap);
              /*  String prod = Date + quotes + d;
                d = prod;*/

                //  String  Nomineedob = NOMINEE_DOB;

            }
            for (String DOB : myDataBase.getAllDob()) {
                Log.e("namee1 ", "DOB " + DOB);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("DOB", DOB);
                arrayList_hashMap.add(hashMap);
             /*   String propr = DOB + quotes + dob;
                dob = propr;*/

            }
            for (String PAN : myDataBase.getKYCTYPE()) {
                Log.e("namee1 ", "PAN " + PAN);
                HashMap<String,String> hashMap  = new HashMap<>();
                hashMap.put("PAN", PAN);
                arrayList_hashMap.add(hashMap);
              /*  String propr = PAN + quotes + pan;
                pan = propr;*/

            }



      //////////////////////////////////////////////////////////////////////////////////////////////////

            for (int j = 0;j<arrayList_hashMap.size();j++)
            {

                String SERIAL  =arrayList_hashMap.get(j).get("SERIAL");
                String IMEI  =arrayList_hashMap.get(j).get("IMEI");
                String MAKE  =arrayList_hashMap.get(j).get("MAKE");
                String MODEL  =arrayList_hashMap.get(j).get("MODEL");
                String TYPE  =arrayList_hashMap.get(j).get("TYPE");

                String INVOICE_NUM  =arrayList_hashMap.get(j).get("INVOICE_NUM");
                String INVOICE_DATE  =arrayList_hashMap.get(j).get("INVOICE_DATE");
               // String INVOICE_PRICE  =arrayList_hashMap.get(j).get("INVOICE_PRICE");

                String PRODUCT_ID  =arrayList_hashMap.get(j).get("PRODUCT_ID");
                String VARIATION_ID  =arrayList_hashMap.get(j).get("VARIATION_ID");
                String VARIATION  =arrayList_hashMap.get(j).get("VARIATION");
                String NAME  =arrayList_hashMap.get(j).get("NAME");
                String PRICE  =arrayList_hashMap.get(j).get("PRICE");


                String RELATION  =arrayList_hashMap.get(j).get("RELATION");
                String KYCTYPE  =arrayList_hashMap.get(j).get("KYCTYPE");
                String NOMINEE  =arrayList_hashMap.get(j).get("NOMINEE");
                String NOMINEE_DOB  =arrayList_hashMap.get(j).get("NOMINEE_DOB");

                String DOB  =arrayList_hashMap.get(j).get("DOB");
                String PAN  =arrayList_hashMap.get(j).get("PAN");

                String IMG1  =arrayList_hashMap.get(j).get("IMG1");
                String IMG2  =arrayList_hashMap.get(j).get("IMG2");
                String IMG3  =arrayList_hashMap.get(j).get("IMG3");


                if(!(IMG1 ==null))
                {
                    String propr = IMG1 + quotes + stringim1;
                    stringim1 = propr;

                }
                if(!(IMG2 ==null))
                {
                    String propr = IMG2 + quotes + stringim2;
                    stringim2 = propr;

                }
                if(!(IMG3 ==null))
                {
                    String propr = IMG3 + quotes + stringim3;
                    stringim3 = propr;

                }


                Log.e("byteimages1", "" + stringim1);
                Log.e("byteimages2 ", "" + stringim2);
                Log.e("byteimages3 ", "" + stringim3);


                if(!(MAKE ==null))
                {
                    String propr = MAKE + quotes + stringB;
                    stringB = propr;

                }
                if(!(MODEL==null))
                {
                    String propr = MODEL + quotes + stringM;
                    stringM = propr;
                }
                if(!(TYPE==null))
                {
                    String propr = TYPE + quotes + stringTY;
                    stringTY = propr;
                }
                if(!(IMEI==null))
                {
                    String propr = IMEI + quotes + stringIM;
                    stringIM = propr;
                }
                if(!(SERIAL==null))
                {

                    String propr = SERIAL + quotes + stringSR;
                    stringSR = propr;
                }
                if(!(INVOICE_NUM==null))
                {

                    String propr = INVOICE_NUM + quotes + stringINVNUM;
                    stringINVNUM = propr;
                }
                if(!(INVOICE_DATE==null))
                {

                    String propr = INVOICE_DATE + quotes + stringD;
                    stringD = propr;
                }
                if(!(DOB==null))
                {

                    String propr = DOB + quotes + stringDOB;
                    stringDOB = propr;
                }
                if(!(PAN==null))
                {

                    String propr = PAN + quotes + stringPAN;
                    stringPAN = propr;
                }
                if(!(PRODUCT_ID==null))
                {

                    String propr = PRODUCT_ID + quotes + stringpid;
                    stringpid = propr;
                }
                if(!(VARIATION_ID==null))
                {

                    String propr = VARIATION_ID + quotes + stringvid;
                    stringvid = propr;
                }
                if(!(VARIATION==null))
                {

                    String propr = VARIATION + quotes + stringvar;
                    stringvar = propr;
                }
                if(!(NAME==null))
                {

                    String propr = NAME + quotes + stringtl;
                    stringtl = propr;
                }
                if(!(PRICE==null))
                {

                    String propr = PRICE + quotes + stringpr;
                    stringpr = propr;
                }
                if(!(RELATION==null))
                {

                    String propr = RELATION + quotes + Relationship;
                    Relationship = propr;
                }
                if(!(KYCTYPE==null))
                {

                    String propr = KYCTYPE + quotes + Kycdocumenttype;
                    Kycdocumenttype = propr;
                }
                if(!(NOMINEE==null))
                {

                    String propr = NOMINEE + quotes + Nominee;
                    Nominee = propr;
                }
                if(!(NOMINEE_DOB==null))
                {
                    String propr = NOMINEE_DOB + quotes + Nomineedob;
                    Nomineedob = propr;
                }



                Log.e("namee123 ", "VALL...1 " + stringTY+"--"+stringB+"--"+stringM);
                Log.e("namee123 ", "VALL...2 " + stringIM+"--"+stringSR);
                Log.e("namee123 ", "VALL...3 " + stringINVNUM+"--"+stringD+"--"+stringPAN+"--"+stringDOB);
                Log.e("namee123 ", "VALL...4 " + stringpid+"--"+stringvid+"--"+stringvar+"--"+stringtl+"--"+stringpr+"--"+sum);
                Log.e("namee123 ", "VALL...5 " + Relationship+"--"+Kycdocumenttype+"--"+Nominee+"--"+Nomineedob);

                // remove this products now to avoid adding in cart again

                myDataBase.delrow("1339");
                myDataBase.delrow("442");

            }
        }
    }

    private void method()
    {
        String a="";

        a = stringim1;
        stringim1 =  method(a);

        a = stringim2;
        stringim2 =  method(a);

        a = stringim3;
        stringim3 =  method(a);

        a = stringB;
        stringB =  method(a);

        a = stringTY;
        stringTY =  method(a);

        a = stringM;
        stringM =  method(a);

        a = stringIM;
        stringIM =  method(a);

        a = stringSR;
        stringSR =  method(a);

        a = stringINVNUM;
        stringINVNUM =  method(a);

        a = stringD;
        stringD =  method(a);

        a = stringPAN;
        stringPAN =  method(a);

        a = stringDOB;
        stringDOB =  method(a);

        a = stringpid;
        stringpid =  method(a);

        a = stringvid;
        stringvid =  method(a);

        a = stringvar;
        stringvar =  method(a);

        a = stringtl;
        stringtl =  method(a);

        a = stringpr;
        stringpr =  method(a);

        a = Relationship;
        Relationship =  method(a);

        a = Kycdocumenttype;
        Kycdocumenttype =  method(a);

        a = Nominee;
        Nominee =  method(a);

        a = Nomineedob;
        Nomineedob =  method(a);

        Log.e("namee1234 ", "stringTY=" + stringTY+" <>  stringB="+stringB+"  <>  stringM="+stringM);
        Log.e("namee1234 ", "  <>  stringIM=" + stringIM+"  <>  stringSR="+stringSR);
        Log.e("namee1234 ", "  <>  stringINVNUM=" + stringINVNUM+"  <>  stringD="+stringD+"  <>  stringPAN="+stringPAN+"  <>  stringDOB="+stringDOB);
        Log.e("namee1234 ", "  <>  stringpid=" + stringpid + "  <>  stringvid=" + stringvid + "  <>  stringvar" + stringvar + "  <>  stringtl =" + stringtl + "  <>  stringpr =" + stringpr + "  <>  total=" + total);
        Log.e("namee1234 ", "  <>  Relationship=" + Relationship + "  <>  Kycdocumenttype=" + Kycdocumenttype + "  <>  Nominee=" + Nominee + "  <>  Nomineedob=" + Nomineedob);
        Log.e("namee12345 ", "  <>  stringim1=" + stringim1 + "  <>  stringim2=" + stringim2 + "  <>  stringim3=" + stringim3);

        Log.e("namee12345__vvvvvv ","--"+stringvar);

        textView = stringim1;




        if(isOnline()) {

         sendcartserver();
        }
        else {

            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }

    private void sendcartserver() {
        showProgressDialog();
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();


            params.add("Userid");
            param_values.add(userid);

            params.add("Type");
            param_values.add(stringTY);

            params.add("Brand");
            param_values.add(stringB);

            params.add("Model");
            param_values.add(stringM);

            params.add("Imei");
            param_values.add(stringIM);

            params.add("Serial");
            param_values.add(stringSR);

            params.add("Kycdocumenttype");
            param_values.add(Kycdocumenttype);

            params.add("Age");
            param_values.add(stringAGE);

            params.add("Nominee");
            param_values.add(Nominee);

            params.add("Nomineedob");
            param_values.add(Nomineedob);

            params.add("Relationship");
            param_values.add(Relationship);

            params.add("Invoicenob");
            param_values.add(stringINVNUM);

            params.add("Sex");
            param_values.add("");

            params.add("Dob");
            param_values.add(stringDOB);

            params.add("Pan");
            param_values.add(stringPAN);

            params.add("Color");
            param_values.add(stringCLOR);

            params.add("Purchasedate");
            param_values.add(stringD);

            params.add("Purchaseprice");
            param_values.add(stringpr);

            params.add("Productid");
            param_values.add(stringpid);

            params.add("Variationid");
            param_values.add(stringvid);

            params.add("Priceid");
            param_values.add(stringpr);

            params.add("Titleid");
            param_values.add(stringtl);

            params.add("Devicevalue");
            param_values.add(stringvar);

            params.add("Firstname");
            param_values.add(fname);

            params.add("Lastname");
            param_values.add(lname);

            params.add("Company");
            param_values.add(company);

            params.add("Email");
            param_values.add(email);

            params.add("Phone");
            param_values.add(phone);

            params.add("Country");
            param_values.add(country);

            params.add("Address1");
            param_values.add(add1);

            params.add("Address2");
            param_values.add(add2);

            params.add("City");
            param_values.add(city);

            params.add("State");
            param_values.add(state);

            params.add("Postcode");
            param_values.add(pincode);

            params.add("image1");
            param_values.add(stringim1);

            params.add("image2");
            param_values.add(stringim2);

            params.add("image3");
            param_values.add(stringim3);

            params.add("Total");
            param_values.add(""+total);

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.save_url);
            js.setOnResultsListener(this);

        } catch (Exception e) {

        }
    }

    private void deatils() {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);

    }
    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='|') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result)
    {
        Log.e("DATA_SEND_INFO_result ", "" + result+"--"+total);
        if(result!=null && result.length()>0)
        {
            try
            {
                order = result.getString("orderId");
                Log.e("resultORDER ", "" + order);

                if(order!=null) {
                  Toast.makeText(getApplicationContext(), "Order Id "+order+" Generated Successfully", Toast.LENGTH_LONG).show();
              /*    Intent i = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(i);
                    finish();*/

                    for (String PRODUCT_ID : myDataBase.getAllProId()) {
                        Log.e("namee1 ", "PRODUCT_ID " + PRODUCT_ID);

                        myDataBase.delrow(PRODUCT_ID);
                        ImageDb.delrow(PRODUCT_ID);

                    }


                        TRAK_PAY();



                     }
            }
            catch (Exception ex)
            {
                hideProgressDialog();
                ex.getMessage();
            }

        }
        hideProgressDialog();
    }

    private void TRAK_PAY()
    {



        OrderId = order;
        Log.e("OrderId_________",""+OrderId+"---"+order+"---"+total);


        String salt = "0e46253955e1ff95a359833e607057a75e0dbbd3";//"f57af2984asdf9a21574e26a9146241896d98a07"; // put your salt
        String api_key = "3feee726-1418-4635-860c-a9cf459dc002";//"611c8127-asdf-46be-a5e5-a14b7f2783df";  // put your api_key
        String return_url = "https://biz.traknpay.in/tnp/return_page_android.php";
        String mode = "LIVE";
        String order_id = order;
        String amount = ""+total;
        String currency = "INR";
        String description = "Payment from Order Placed";
        String name = fname+" "+lname;
        String emal = email;
        String phon = phone;
        String address_line_1 = add1;
        String address_line_2 = add2;
        String cityy = city;
        String statee = state;
        String zip_code = pincode;
        String country = "IND";
        String udf1 = "";
        String udf2 = "";
        String udf3 = "";
        String udf4 = "";
        String udf5 = "";


        // Getting these values from Main activity
    /*    Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mode = extras.getString("mode");
            amount = extras.getString("amount");
            order_id = extras.getString("order_id");
        }*/

        Map<String, String> map = new HashMap<String, String>();
        map.put("api_key", api_key);
        map.put("return_url", return_url);
        map.put("mode", mode);
        map.put("order_id", order_id);
        map.put("amount", amount);
        map.put("currency", currency);
        map.put("description", description);
        map.put("name", name);
        map.put("email", emal);
        map.put("phone", phon);
        map.put("address_line_1", address_line_1);
        map.put("address_line_2", address_line_2);
        map.put("city", cityy);
        map.put("state", statee);
        map.put("zip_code", zip_code);
        map.put("country", country);
        map.put("udf1", udf1);
        map.put("udf2", udf2);
        map.put("udf3", udf3);
        map.put("udf4", udf4);
        map.put("udf5", udf5);

        String hashData = salt;
        String postData = "";


    //    E/DATA_SEND_INFO: --null--abc asm--null--0

        Log.e("DATA_SEND_INFO ","--"+mode+"--"+name+"--"+order_id+"--"+amount);



        // Sort the map by key and create the hashData and postData
        for (String key : new TreeSet<String>(map.keySet())) {

            if(map.get(key).equalsIgnoreCase(""))
            {

            }
            else
            {
        //    if (!map.get(key).equals("")) {
                hashData = hashData + "|" + map.get(key);
                postData = postData + key + "=" + map.get(key) + "&";
            }
        }

        // Generate the hash key using hashdata and append the has to postData query string
        String hash = generateSha512Hash(hashData).toUpperCase();
        postData = postData + "hash=" + hash;

        Log.d(TAG, "HashData: " + hashData);
        Log.d(TAG, "Hash: " + hash);
        Log.d(TAG, "PostData: " + postData);

        scrollview.setVisibility(View.VISIBLE);
        paymentdone.setVisibility(View.GONE);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setDatabasePath("/data/data/" + getPackageName() + "/databases/");
//        webSettings.setAppCacheMaxSize(1024*1024*8);
//        webSettings.setAppCachePath("/data/data/" + getPackageName() + "/cache/");
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setLightTouchEnabled(true);
        webSettings.setBuiltInZoomControls(true);
//        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.postUrl("https://biz.traknpay.in/v1/paymentrequest", postData.getBytes());
        webView.addJavascriptInterface(new MyJavaScriptInterface(this), "Android");

        Log.e("DATA_SEND_INFO3","--"+postData);

    }

    /**
     * Generates the SHA-512 hash (same as PHP) for the given string
     *
     * @param toHash string to be hashed
     * @return return hashed string
     */
    public String generateSha512Hash(String toHash) {
        MessageDigest md = null;
        byte[] hash = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            hash = md.digest(toHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return convertToHex(hash);
    }

    /**
     * Converts the given byte[] to a hex string.
     *
     * @param raw the byte[] to convert
     * @return the string the given byte[] represents
     */
    private String convertToHex(byte[] raw) {
        StringBuilder sb = new StringBuilder();
        for (byte aRaw : raw) {
            sb.append(Integer.toString((aRaw & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /**
     *  Interface to bind Javascript from WebView with Android
     */
    public class MyJavaScriptInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        MyJavaScriptInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void getPaymentResponse(String jsonResponse) {

            Log.e("DATA_SEND_INFO","data_reponse"+jsonResponse);

            try {
                JSONObject resposeData = new JSONObject(jsonResponse);
                Log.d(TAG, "ResponseJson: " + resposeData.toString());
                String transactionId = resposeData.getString("transaction_id");
                String responseCode = resposeData.getString("response_code");
                String responseMessage = resposeData.getString("response_message");

                Log.e("DATA_SEND_INFO","responseCode "+responseCode);
                Log.e("DATA_SEND_INFO","transactionId "+transactionId);
                Log.e("DATA_SEND_INFO","responseMessage "+responseMessage);
                Log.e("DATA_SEND_INFO","OrderId "+OrderId);

                if (responseCode.equals("998")) {
                    Toast.makeText(getApplicationContext(),"The order id field should be unique Or Ammount to low", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(PaymentScreen.this,NavigationActivity.class);
                    i.putExtra("pager","4");
                    startActivity(i);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), TraknpayResponseActivity.class);
                    intent.putExtra("transactionId", transactionId);
                    intent.putExtra("responseCode", responseCode);
                    intent.putExtra("responseMessage", responseMessage);
                    intent.putExtra("OrderId", OrderId);
                    startActivity(intent);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public String getStringImage(Bitmap original)
    {

        try {

            Log.e("Original_dimensions", "" + original);
            Log.e("Original_dimensions", original.getWidth() + " " + original.getHeight());

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // original = BitmapFactory.decodeStream(getAssets().open("1024x768.jpg"));
            original.compress(Bitmap.CompressFormat.PNG, 100, out);
            Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

            Log.e("Original_dimensions", original.getWidth() + " " + original.getHeight());

            byte[] imageBytes = out.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            return encodedImage;
        }
        catch (Exception EX)
        {
            EX.getMessage();
        }

        return null;
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
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
                Intent i = new Intent(PaymentScreen.this,NavigationActivity.class);
                i.putExtra("pager","4");
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(PaymentScreen.this,NavigationActivity.class);
        i.putExtra("pager","4");
        startActivity(i);
        finish();
    }
    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getApplicationContext(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
