package com.widecare.indiaweb.myapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
 import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.DatabaseMethods;
import Constant.MyDataBase;
import Constant.MyDataBases;
import Constant.PlayGifV;
import Constant.Shaved_shared_preferences;
import Constant.UserDataItems;
import Constant.VariationRecord;
import app.AppController;

/**
 * Created by indiaweb on 9/17/2016.
 */
public class Products extends AppCompatActivity implements Asnychronus_notifier {
    LinearLayout linear_type, linear_make, linear_model, linear_InvoiceNo;
    LinearLayout linear_InvoiceDate, linear_Imei, linear_SerialNo;
    LinearLayout linear_InvoiceVal, linear_Invoice_SlabVal, linear_Dob;
    LinearLayout linear_KycDoc_Type, linear_KycDoc_No, Nomine_name, Nomine_dob, linear_veh_color;
    LinearLayout linear_rel, linear_chas, linear_reg, linear_veh_val, linear_veh_saleDate;
    private String message = "Terms and Condition Not Available", tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    LinearLayout doc1, doc2, doc3, doc4;
    Boolean cancel = false;
    public static final int TAKE_PHOTO_REQUEST = 1888;
    public static final int RESULT_LOAD_IMAGE = 1888;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    int size = 0;
    private Uri mImageUri;
    Button add_product_Images, done1, done2, done3, done4;
    String path = "abc";
    String picturePath = "";
    Boolean all_bol = false;
    ProgressDialog progressDoalog;
    int connection = 0, VAL = 1;
    Boolean bb = true;
    Boolean kyc_bol = false;
    Spinner InvoiceSlabNo, KycDoc, spinner_type;
    DatabaseMethods databaseMethods;
    ImageView pimage;
    TextView price, pdes, ptitle, expandBtn, expandBtn1, Nomine_name_text, Nomine_dob_text, relation_txt;
    WebView pcontent10;
    String proid, pname, pdesc, pimag, value = "0", add_variation = "", add_variation_id = "";
    TextView buy_now, spinner;
    Shaved_shared_preferences shaved_shared_preferences = null;
    MyDataBases myDataBase;
    String priceitem, catid, typename = "", productname = "";
    int openDialog = 0;
    VariationRecord variationRecord;
    ArrayList<String> type_array, deviceMake_array, deviceModel_array, InvoiceSlabNo_array, KycDoc_array;
    List<String> categories;
    String pr = "0", variation_id;
    boolean B1 = true;
    boolean B2 = false;
    boolean DOB_CHECK = false;
    byte[] logoImageByte;
    MyDataBase myDataBas;
    LinearLayout linearLayout, viewGifflayout, bottom, linearScroll, cc, main_id;
    Calendar calendar;
    public Button yes, no;
    PlayGifV pGiff;
    Dialog dialog;
    int year;
    int month;
    ScrollView scrollView;
    int day;
    String filename = "";
    //
    TextView InvoiceDate, spinner_free, type, DOC11, DOC22, DOC33, DOC44, Dobs;
    TextView invoice_numtext, invoice_date_text, invoice_val_txt, initmatecheck_txt;
    TextView KycDoc__txt, InvoiceSlabNo_text, kyc_no_txt, InvoiceVal;

    //
    Boolean BOOL = false;
    int pos;
    Spinner  Make, sp_model;
    EditText kycDoc_num, relation, nominee_name;
    TextView typee,Dobb, SaleDate, nom_dob;
    EditText invoice_date, invoice_num, Imei_val, serial_val, Price, Serial, Chasis, Regno;
    Boolean isInternet;


    String add_type = "", add_model = "", add_make = "", add_kyctype = "";
    String add_invoice_num = "", add_invoice_date = "", add_imei = "", add_serial = "", invoice_val = "";
    String add_upload_image = "", add_upload_invoice = "", add_upload_doc = "";

    TextView type_name, make_name, model_name, serial, Imei;

    String userChoosenTask = "";
    private TextView editTextName;
    CheckBox initmatecheck;
    private Bitmap bitmap;
    private int REQUEST_CODE_DOC = 0;
    private int SELECT_FILE = 1;
    private int REQUEST_CAMERA = 2;
    Inflater inflater;
    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
 //   ArrayList<HashMap<String, String>> mylists = new ArrayList<HashMap<String, String>>();

    ArrayList<HashMap<String, String>> deviceMakeLstData= new ArrayList<HashMap<String, String>>();

    static int TAKE_PIC = 1;
    Uri outPutfileUri;
    String userid = "";

    Bitmap logoImage1, logoImage_f, logoImage_s, logoImage_t;

    Boolean _devicetype = false, _devicemake = false, _devicemodel = false, _kycdocumenttype = false, _nominee = false, _nomineedob = false;
    Boolean _vehicletype = false, _vehiclemake = false, _vehiclemodel = false, _invoiceno = false, _serial = false;
    Boolean _devicevalue = false, _imei = false, _age = false, _relationship = false, _dob = false, _vehiclechasis = false, _vehicleregno = false, _vehiclevalue = false, _devicesaledate = false, _vehiclecolor = false, _pan = false, _vehiclesaledate = false;
    private String TAG = "Products ", choosedAttribute="",choosedSlab="",calculatedAmount="",choosedAttributeType="";
    int socketTimeout = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
     */

        setContentView(R.layout.activity_products);
        Log.e("TAG=" + TAG, "" + TAG);
        spinner_free = (TextView) findViewById(R.id.spinner_free);
        yes = (Button) findViewById(R.id.con);
        no = (Button) findViewById(R.id.cart);
        spinner = (TextView) findViewById(R.id.spinner);
        pimage = (ImageView) findViewById(R.id.pimage);
        price = (TextView) findViewById(R.id.price);

        initmatecheck_txt = (TextView) findViewById(R.id.initmatecheck_txt);
        initmatecheck = (CheckBox) findViewById(R.id.initmatecheck);
        doc1 = (LinearLayout) findViewById(R.id.doc1);
        doc2 = (LinearLayout) findViewById(R.id.doc2);
        doc3 = (LinearLayout) findViewById(R.id.doc3);
        doc4 = (LinearLayout) findViewById(R.id.doc4);

        main_id = (LinearLayout) findViewById(R.id.main_id);

        type_name = (TextView) findViewById(R.id.type_name);
        make_name = (TextView) findViewById(R.id.make_name);
        model_name = (TextView) findViewById(R.id.model_name);
        Dobs = (TextView) findViewById(R.id.Dobs);
        invoice_numtext = (TextView) findViewById(R.id.invoice_numtext);

        Imei_val = (EditText) findViewById(R.id.Imei_val);
        Imei = (TextView) findViewById(R.id.Imeii);
        serial = (TextView) findViewById(R.id.serial);
        serial_val = (EditText) findViewById(R.id.serial_val);


        invoice_date_text = (TextView) findViewById(R.id.invoice_date_text);
        invoice_val_txt = (TextView) findViewById(R.id.invoice_val_txt);
        relation_txt = (TextView) findViewById(R.id.relation_txt);
        KycDoc__txt = (TextView) findViewById(R.id.KycDoc__txt);
        kyc_no_txt = (TextView) findViewById(R.id.kyc_no_txt);
        InvoiceSlabNo_text = (TextView) findViewById(R.id.InvoiceSlabNo_text);

        DOC11 = (TextView) findViewById(R.id.DOC11);
        DOC22 = (TextView) findViewById(R.id.DOC22);
        DOC33 = (TextView) findViewById(R.id.DOC33);
        DOC44 = (TextView) findViewById(R.id.DOC44);

        linear_type = (LinearLayout) findViewById(R.id.linear_type);
        linear_make = (LinearLayout) findViewById(R.id.linear_make);
        linear_model = (LinearLayout) findViewById(R.id.linear_model);
        linear_InvoiceNo = (LinearLayout) findViewById(R.id.linear_InvoiceNo);
        linear_InvoiceDate = (LinearLayout) findViewById(R.id.linear_InvoiceDate);
        linear_Imei = (LinearLayout) findViewById(R.id.linear_Imei);
        linear_SerialNo = (LinearLayout) findViewById(R.id.linear_SerialNo);
        linear_InvoiceVal = (LinearLayout) findViewById(R.id.linear_InvoiceVal);
        linear_Invoice_SlabVal = (LinearLayout) findViewById(R.id.linear_Invoice_SlabVal);
        linear_Dob = (LinearLayout) findViewById(R.id.linear_Dob);
        linear_KycDoc_Type = (LinearLayout) findViewById(R.id.linear_KycDoc_Type);
        linear_KycDoc_No = (LinearLayout) findViewById(R.id.linear_KycDoc_No);
        Nomine_name = (LinearLayout) findViewById(R.id.Nomine_name);
        Nomine_dob = (LinearLayout) findViewById(R.id.Nomine_dob);
        scrollView = (ScrollView) findViewById(R.id.scroll);

        ///qqqqqq

        linear_veh_color = (LinearLayout) findViewById(R.id.linear_veh_color);
        linear_rel = (LinearLayout) findViewById(R.id.linear_rel);
        linear_chas = (LinearLayout) findViewById(R.id.linear_chas);
        linear_reg = (LinearLayout) findViewById(R.id.linear_reg);
        linear_veh_val = (LinearLayout) findViewById(R.id.linear_veh_val);
        linear_veh_saleDate = (LinearLayout) findViewById(R.id.linear_veh_saleDate);

        /// spinnerrrrrrrrrrr

        Make = (Spinner) findViewById(R.id.make);
        sp_model = (Spinner) findViewById(R.id.sp_model);
        typee = (TextView) findViewById(R.id.typee);
        InvoiceSlabNo = (Spinner) findViewById(R.id.InvoiceSlabNo);
        spinner_type = (Spinner) findViewById(R.id.spinner_type);
        KycDoc = (Spinner) findViewById(R.id.KycDoc);


        InvoiceDate = (TextView) findViewById(R.id.Invoice_date);


        invoice_num = (EditText) findViewById(R.id.Invoice_num);
        kycDoc_num = (EditText) findViewById(R.id.kycDoc_num);
        nominee_name = (EditText) findViewById(R.id.nominee_name);

        relation = (EditText) findViewById(R.id.relation);
        //  Regno = (EditText) findViewById(R.id.Regno);


        //  Chasis = (EditText) findViewById(R.id.Chasis);
        //  Vvalue = (EditText) findViewById(R.id.Vvalue);
        SaleDate = (TextView) findViewById(R.id.SaleDate);
        Dobb = (TextView) findViewById(R.id.Dob);
        nom_dob = (TextView) findViewById(R.id.nom_dob);
        // colourr = (EditText) findViewById(R.id.color);

        InvoiceVal = (EditText) findViewById(R.id.InvoiceVal);

        pcontent10 = (WebView) findViewById(R.id.pcontents10);
        ptitle = (TextView) findViewById(R.id.ptitle);
        pdes = (TextView) findViewById(R.id.pdes);
        linearLayout = (LinearLayout) findViewById(R.id.expand);
        viewGifflayout = (LinearLayout) findViewById(R.id.viewGifflayout);
        bottom = (LinearLayout) findViewById(R.id.bottoms);
        cc = (LinearLayout) findViewById(R.id.cc);
        linearScroll = (LinearLayout) findViewById(R.id.vv);
        expandBtn = (TextView) findViewById(R.id.expandBtn);
        expandBtn1 = (TextView) findViewById(R.id.expandBtn1);
        buy_now = (TextView) findViewById(R.id.buy_now);
        type = (TextView) findViewById(R.id.type);
        myDataBase = new MyDataBases(getApplicationContext());
        myDataBas = new MyDataBase(getApplicationContext());
        databaseMethods = new DatabaseMethods(getApplicationContext());
        variationRecord = new VariationRecord(getApplicationContext());
        variationRecord.delall();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.widecare);
        }

        pGiff = (PlayGifV) findViewById(R.id.viewGiff);
        pGiff.setImageResource(R.drawable.ringload);

        shaved_shared_preferences = new Shaved_shared_preferences(getApplication());

        type_array = new ArrayList<>();
        deviceMake_array = new ArrayList<>();
        deviceModel_array = new ArrayList<>();
        InvoiceSlabNo_array = new ArrayList<>();
        KycDoc_array = new ArrayList<>();

        type_array.clear();
        deviceMake_array.clear();
        deviceModel_array.clear();
        InvoiceSlabNo_array.clear();
        KycDoc_array.clear();

        mylist.clear();


        type_array.add("Select");
        deviceMake_array.add("Select");
        deviceModel_array.add("Select");
        InvoiceSlabNo_array.add("Choose an option");
        KycDoc_array.add("Select");
        KycDoc_array.add("Aadhar");
        KycDoc_array.add("PAN Card");
        KycDoc_array.add("Driving Licence");

        Intent i = getIntent();
        catid = i.getStringExtra("catid");
        value = i.getStringExtra("value");
        proid = i.getStringExtra("proid");
        pname = i.getStringExtra("pname");
        pdesc = i.getStringExtra("pdes");
        pdesc = Html.fromHtml(pdesc).toString();
        pimag = i.getStringExtra("pimage");
        Log.e(TAG,"pdesc="+pdesc);
        message = pdesc;
        pcontent10.loadDataWithBaseURL(null, pdesc, "text/html", "utf-8", null);

try{
    choosedAttributeType=i.getStringExtra("pChoosedAttributeType");
    choosedAttribute=                i.getStringExtra("pChoosedAttribute");
    choosedSlab=                i.getStringExtra("pChoosedSlab");
    calculatedAmount=i.getStringExtra("pCalculatedAmount");
    add_type=choosedAttributeType;

Log.e(TAG,"choosedAttribute="+choosedAttribute+"<<>>choosedSlab="+choosedSlab+"<<>>calculatedAmount="+calculatedAmount+"<<>>choosedAttributeType="+choosedAttributeType);

}
catch (Exception exp)
{

}
        userid = shaved_shared_preferences.get_userid();


        progressDoalog = new ProgressDialog(Products.this);
        progressDoalog.setMessage("Adding Product to Cart...");
        progressDoalog.setMax(100);
        progressDoalog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);

        Log.e("select123", "..." + catid);
        Log.e("select123", "..." + proid);
        if (AppController.getInstance().isOnline()) {
         //   getProductData(catid, proid);
            getMakeDataShopByProduct(choosedAttribute);

            getVisibleProducts();
        } else {
            Toast.makeText(getApplicationContext(), "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
        }

        ptitle.setText(pname);


        pdes.setText(pdesc);

        SpannableString content = new SpannableString("Terms and Condition");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        initmatecheck_txt.setText(content);

        Log.e("url ", "" + proid + "--" + pname);

        //  Picasso.with(getApplicationContext()).load(pimag).into(pimage);

        logoImageByte = getLogoImage(pimag);

        logoImage1 = getBitmapFromURL(pimag.toString());
//
        Log.e("codeimage1 ", "" + logoImageByte);
        Log.e("codeimage2 ", "" + logoImage1);

        pimage.setImageBitmap(logoImage1);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        // Image  = new String(logoImage, StandardCharsets.UTF_8);
        variationRecord.insertRecord(proid, variation_id, "0", "0");

        //   Log.e("code ", "" + Image);
        InvoiceDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BOOL = false;
                showDialog(999);//THIS METHOD AUTOMATICALLY CALL override onCreateDialog Method

            }

        });
        Dobb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BOOL = true;
                DOB_CHECK = false;
                showDialog(999);//THIS METHOD AUTOMATICALLY CALL override onCreateDialog Method

            }

        });

        nom_dob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BOOL = true;
                DOB_CHECK = true;
                showDialog(999);//THIS METHOD AUTOMATICALLY CALL override onCreateDialog Method

            }

        });

        initmatecheck_txt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Products.this);
                builder1.setTitle( ptitle.getText());
                builder1.setMessage(pdesc);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "AGREE",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                initmatecheck.setChecked(true);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });


        initmatecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("checkBox ", "CHECKED");


            }
        });


        expandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcontent10.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                expandBtn.setVisibility(View.GONE);
                expandBtn1.setVisibility(View.VISIBLE);
     Log.e(TAG,"expandBtn ");
                scrollView.scrollTo(scrollView.getScrollX()+5 , scrollView.getScrollY()+5 );
                //      scrollView.scrollTo(0,0);
            }
        });

        expandBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"expandBtn1");
                pcontent10.setVisibility(View.GONE);
                 linearLayout.setVisibility(View.GONE);
                expandBtn1.setVisibility(View.GONE);
                expandBtn.setVisibility(View.VISIBLE);
                scrollView.scrollTo(scrollView.getScrollX()+5 , scrollView.getScrollY()+5 );
            }
        });

        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHECK_CONDITION();
            }
        });

        bitmap = BitmapFactory.decodeByteArray(logoImageByte, 0, logoImageByte.length);

        Log.e("codeimage3", "" + bitmap);

        //  pimage.setImageBitmap(bitmap);

        if (AppController.getInstance().isOnline()) {
            receiveData();
        } else {
            Toast.makeText(getApplicationContext(), "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, InvoiceSlabNo_array) {

         //   @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        InvoiceSlabNo.setAdapter(dataAdapter);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterr = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, KycDoc_array) {

          //  @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        KycDoc.setAdapter(dataAdapterr);

        KycDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = String.valueOf(parent.getItemAtPosition(position));

                Log.e("position ", "position " + position + "-" + item);

                if (!item.equals("Select")) {
                    add_kyctype = item;
                } else {
                    add_kyctype = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        invoice_num.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                Log.e(" ", "" + s);

                int counter = 0;

                if (s.equals("")) {
                    invoice_num.setError("EMPTY");
                } else {

                    for (int i = 0; i < s.length(); i++) {

                        counter = s.length();

                        if (counter >= 1) {
                            if (counter < 17) {

                            } else {
                                invoice_num.setError("INVOICE NUMBER MUST NOT BE GREATER THAN 16 DIGITS");
                            }
                        } else {
                            invoice_num.setError("EMPTY FIELD");
                        }
                    }

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        Imei_val.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                Log.e(" ", "" + s);
                int counter = 0;
                if (s.equals("")) {
                    Imei_val.setError("EMPTY");
                } else {
                    for (int i = 0; i < s.length(); i++) {

                        counter = s.length();

                        if (counter >= 15) {
                            if (counter < 17) {

                            } else {
                                Imei_val.setError("IMEI NUMBER MUST NOT BE GREATER THAN 16 DIGITS");
                            }
                        } else {
                            Imei_val.setError("GREATER THAN OR EQUAL TO 15 DIGITS REQUIRED");
                        }
                    }

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        serial_val.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                Log.e(" ", "" + s);

                int counter = 0;

                if (s.equals("")) {
                    serial_val.setError("EMPTY");
                } else {

                    for (int i = 0; i < s.length(); i++) {

                        counter = s.length();

                        if (counter >= 15) {
                            if (counter < 17) {

                            } else {
                                serial_val.setError("SERIAL NUMBER MUST NOT BE GREATER THAN 16 DIGITS");
                            }
                        } else {
                            serial_val.setError("GREATER THAN OR EQUAL TO 15 DIGITS REQUIRED");
                        }
                    }

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        // spinner.setPopupBackgroundResource(R.color.orange);

        InvoiceVal.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here
                Log.e("vaaaaaaaaaa ", "" + s);

                String ss = s.toString();
                int sval = 0;

                if (ss.equals("")) {
                    sval = 0;
                } else {
                    if (ss.length() < 8) {
                        sval = Integer.parseInt(ss);

                        if (sval > size) {
                            InvoiceVal.setError("VALUE EXCEED UPPER LIMIT");
                        } else {
                            for (String cat : categories) {
                                Log.e("vaaaaaaaaaa_val ", "" + cat);

                                if (cat.equals("0")) {

                                } else {
                                    String a[] = cat.split("-");
                                    String val1 = a[0];
                                    String val2 = a[1];

                                    int va1 = Integer.parseInt(val1);
                                    int va2 = Integer.parseInt(val2);

                                    Log.e("vaaaaaaaaaa_val.. ", sval + "..." + va1 + "--" + val2);

                                    if (sval > va1 && sval <= va2) {

                                        pr = variationRecord.getById(cat);
                                        String var_id = variationRecord.getByIdd(cat);
                                        Log.e("positionprice ", "" + pr);

                                 //       price.setText("₹" + pr);
                                 //       spinner.setText(cat);

                                        add_variation = cat;
                                        add_variation_id = var_id;
                                    } else {
                                        //  Toast.makeText(getApplicationContext(), "NOT IN RANGE", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    } else {
                        InvoiceVal.setError("VALUE EXCEED UPPER LIMIT");
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = String.valueOf(parent.getItemAtPosition(position));

                Log.e("position ", "position " + position + "-" + item);

                //  pos = position;

                pr = variationRecord.getById(item);

                Log.e("positionprice ", "" + pr);
                String var_id = variationRecord.getByIdd(item);
                add_variation = item;
                add_variation_id = var_id;

          //      price.setText("₹" + pr);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       /* typee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String TYPE = String.valueOf(parent.getItemAtPosition(position));
                Log.e("position123 ", "TYPE " + position + "-" + TYPE);

                // deviceMake_array.clear();

                if (!TYPE.equals("Select")) {
                    deviceMake_array.clear();
                    deviceMake_array.add("Select");
                    for (int k = 0; k < mylist.size(); k++) {

                        mylist.get(k).get("type");
                        mylist.get(k).get("product");
                        mylist.get(k).get("model");

                        String type = mylist.get(k).get("type");
                        String product = mylist.get(k).get("product");
                        String model = mylist.get(k).get("model");

                        if (type.equals(TYPE)) {
                            add_type = type;
                            typename = type;
                            if (deviceMake_array.contains(product)) {
                                Log.e("device123456777 ", "" + type + "--" + product + "--" + model);
                            } else {
                                deviceMake_array.add(product);
                                Log.e("device123456888 ", "" + type + "--" + product + "--" + model);

                            }


                        } else {
                            // setDefault();
                        }

                    }
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, deviceMake_array) {

                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View v = null;
                            v = super.getDropDownView(position, null, parent);
                            // If this is the selected item position
                            if (position == 0) {

                                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                text.setTextColor(Color.parseColor("#3d406b"));

                                int backgroundColor = getResources().getColor(R.color.greyspinner);
                                ;// ContextCompat.getColor(getContext(), R.color.blue);
                                v.setBackgroundColor(backgroundColor);

                            } else {
                                // for other views
                                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                text.setTextColor(Color.parseColor("#3d406b"));
                                Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                                v.setBackground(backgroundColor);

                            }
                            return v;
                        }
                    };


                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
                    // attaching data adapter to spinner

                    Make.setAdapter(dataAdapter);

                } else {
                    add_type = TYPE;
                    typename = TYPE;
                    setDefault();
                    Log.e("device123456666 ", "........typee");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/

        Make.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String MAKE = String.valueOf(parent.getItemAtPosition(position));
                Log.e("position123 ", "MAKE.setOnItemSelectedListener pos=" + position + "<> make=>" + MAKE);
                add_make = MAKE;
                productname = MAKE;

                if (!MAKE.equals("Select")) {
                    deviceModel_array.clear();
                    deviceModel_array.add("Select");
                   callApiForModel(""+MAKE);

                 /*   for (int k = 0; k < mylist.size(); k++) {
                        mylist.get(k).get("type");
                        mylist.get(k).get("product");
                        mylist.get(k).get("model");

                        String type = mylist.get(k).get("type");
                        String product = mylist.get(k).get("product");
                        String model = mylist.get(k).get("model");

                        if (product.equals(MAKE) && typename.equals(type)) {
                            add_make = product;
                            productname = product;
                            if (deviceModel_array.coanyntains(model)) {
                                Log.e("not_adding_device ", "type=" + type + "--product=" + product + "--model=" + model);
                            } else {
                                deviceModel_array.add(model);
                                Log.e("adding_device ", "type=" + type + "--product=" + product + "--model=" + model);

                            }

                        } else {
                            // setDefaults();
                        }
                    }
                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, deviceModel_array) {

                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            View v = null;
                            v = super.getDropDownView(position, null, parent);
                            // If this is the selected item position
                            if (position == 0) {

                                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                text.setTextColor(Color.parseColor("#3d406b"));

                                int backgroundColor = getResources().getColor(R.color.greyspinner);
                                ;// ContextCompat.getColor(getContext(), R.color.blue);
                                v.setBackgroundColor(backgroundColor);

                            } else {
                                // for other views
                                TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                text.setTextColor(Color.parseColor("#3d406b"));
                                Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                                v.setBackground(backgroundColor);

                            }
                            return v;
                        }
                    };


                    // Drop down layout style - list view with radio button
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
                    // attaching data adapter to spinner

                    Model.setAdapter(dataAdapter);

                    //Model.setSelection(0);
             */   } else {
                   setDefaults();
                    Log.e("else case for setdefault() ", "........PRODUCT");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String MODEL = String.valueOf(parent.getItemAtPosition(position));

                Log.e("Model position123 ", "MODEL " + position + "-" + MODEL);
                if (!MODEL.equals("Select")) {
                    add_model = MODEL;
                } else {
                    add_model = MODEL;
                    // setDefault();
                    Log.e("device123456666 ", "........PRODUCT");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        price.setText("₹" + calculatedAmount);
        spinner.setText(""+choosedSlab);
        typee.setText(""+choosedAttributeType);

    }

    private void callApiForModel(final String strChoosedMake) {
             Log.e(TAG , "callApiForModel="+Jason_Urls.shopByProduct_url+ "?action=get_models&device_id=" + choosedAttribute);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://widecare.in/android/shopByProduct.php?action=get_models&device_id=" + choosedAttribute  ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG,"callApiForModel response="+ response.toString());
                            try {

                                JSONObject jsonObject = new JSONObject(response.toString());


                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject attributeObj = jsonArray.getJSONObject(i);

                                    String  deviceMakeAttribute=attributeObj.getString("attribute_name");
                                     JSONArray jsonModelArry = attributeObj.getJSONArray("model");
if(deviceMakeAttribute.equals(strChoosedMake)) {
    for (int j = 0; j < jsonModelArry.length(); j++) {

        JSONObject jsonModelObj = jsonModelArry.getJSONObject(j);

        String strAttributeId = jsonModelObj.getString("attribute_Id");
        String strAttributeName = jsonModelObj.getString("attribute_name");

        Log.e(TAG, "jsonModelArry strAttributeId=" + strAttributeId + " <> strAttributeName=" + strAttributeName);

        deviceModel_array.add(strAttributeName);
    }
}
                            /* for (int j=0;j<modelArry.length();j++)
                             {
                                deviceModel_array.s
                             } */
                                }
                            }
                            catch (Exception exp)
                            {
Log.e(TAG,"excep="+exp.toString());
                            }

                             setModelAdapterData(deviceModel_array);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    VolleyLog.e(TAG, "Error: " + error.getMessage());
                }
            }) {

                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("action", "fetch_Slabs");
                    params.put("product_id", ""+choosedAttribute);
                    return params;
                }
            };

            // Adding request to request queue
            // 30 seconds. You can change it
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

            stringRequest.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(stringRequest);

        }

    private void setModelAdapterData(ArrayList<String> deviceModel_array) {
        Log.e(TAG,"setModelAdapterData="+deviceModel_array.size());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, deviceModel_array) {

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        sp_model.setAdapter(dataAdapter);


    }


    private void getMakeDataShopByProduct(final String choosedAttribute) {
        Log.e(TAG , "getDataShopByProduct="+Jason_Urls.shopByProduct_url+ "?action=get_models&device_id=" + choosedAttribute);
             StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://widecare.in/android/shopByProduct.php?action=get_models&device_id=" + choosedAttribute  ,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG,"getMakeDataShopByProduct response="+ response.toString());
                            try {

                                JSONObject jsonObject = new JSONObject(response.toString());
                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject attributeObj = jsonArray.getJSONObject(i);

                                    String strAttributeName = attributeObj.getString("attribute_name");
                                    String strAttributeId = attributeObj.getString("attribute_Id");
                                 //   JSONArray modelArry = attributeObj.getJSONArray("model");
Log.e(TAG,"getMakeDataShopByProduct  strAttributeName="+strAttributeName+" <> strAttributeId="+strAttributeId);
                                    deviceMake_array.add(strAttributeName);


                            /* for (int j=0;j<modelArry.length();j++)
                             {
                                deviceModel_array.s
                             } */
                                }
                            }
                            catch (Exception exp)
                            {

                            }

      setMakeAdapterData();

                                }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    VolleyLog.e(TAG, "Error: " + error.getMessage());
                }
            }) {

                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("action", "fetch_Slabs");
                    params.put("product_id", ""+choosedAttribute);
                    return params;
                }
            };

            // Adding request to request queue
            // 30 seconds. You can change it
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

            stringRequest.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(stringRequest);

        }

    private void setMakeAdapterData() {

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, deviceMake_array) {

             @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                        v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


                            // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        Make.setAdapter(dataAdapter);
    }

    private void setDefaults() {
        deviceModel_array.clear();
        deviceModel_array.add("Select");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, deviceModel_array) {

          //  @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        sp_model.setAdapter(dataAdapter);
    }

    private void setDefault() {
        deviceMake_array.clear();
        deviceMake_array.add("Select");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, deviceMake_array) {

             @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        Make.setAdapter(dataAdapter);


    }

    private void getVisibleProducts() {
//        progressDoalog =  new ProgressDialog(MyProducts.this);
//        progressDoalog.setMax(100);
//        progressDoalog.setMessage("Please Wait while Data loading....");
//        progressDoalog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
//        progressDoalog.show();

        Log.e(TAG ,  "getVisibleProducts url="+ Jason_Urls.product_visible_url + "?id=" + proid);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Jason_Urls.product_visible_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG + "getVisible respo", response.toString());
                        progressDoalog.cancel();

                        loadData();
                    try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                Log.e(TAG + "getVisibleProducts", "" + jsonArray.getString(i));

                                JSONObject ob = jsonArray.getJSONObject(i);

                                String naa = ob.getString("name");
                                String tyy = ob.getString("value");
                                String lab = ob.getString("label");
// CHECK_VAR
                                Log.e(TAG + "getVisibleProducts NAME", naa + "---" + lab + "...Type" + tyy + "-bol " + cancel);

                                if (naa.equals("_devicetype")) {
                                    Log.e(TAG + "naa=", naa +  "<<>>tyy="+tyy  );

                                    if (!tyy.equals("") ) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_type.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            type_name.setText(lab);
                                            cancel = true;
                                            all_bol = true;


                                            _devicetype = true;
                                        }
                                    }
                                } else if (naa.equals("_devicemake")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            {
                                                linear_make.setVisibility(View.VISIBLE);
                                                Log.e("value_found", " " + lab);
                                                make_name.setText(lab);
                                                cancel = true;
                                                all_bol = true;

                                                _devicemake = true;
                                            }
                                        }
                                    }
                                } else if (naa.equals("_devicemodel")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_model.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            model_name.setText(lab);
                                            cancel = true;
                                            all_bol = true;

                                            _devicemodel = true;
                                        }
                                    }
                                } else if (naa.equals("_kycdocumenttype")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_KycDoc_Type.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            KycDoc__txt.setText(lab);
                                            cancel = false;
                                            all_bol = true;

                                            _kycdocumenttype = true;

                                            kyc_bol = true;
                                        }
                                    }
                                } else if (naa.equals("_nominee")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            Nomine_name.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            //  model_name.setText(lab);
                                            cancel = false;
                                            all_bol = true;

                                            _nominee = true;
                                        }
                                    }
                                } else if (naa.equals("_nomineedob")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            Nomine_dob.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            //  model_name.setText(lab);
                                            cancel = false;
                                            all_bol = true;

                                            _nomineedob = true;
                                        }
                                    }
                                } else if (naa.equals("_vehicletype")) {

                                    Log.e("print_val", ".." + lab);
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_type.setVisibility(View.VISIBLE);
                                            Log.e("value_foundprint_val", " " + lab);
                                            type_name.setText(lab);
                                            cancel = true;
                                            all_bol = true;

                                            _vehicletype = true;
                                        }
                                    }
                                } else if (naa.equals("_vehiclemake")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_make.setVisibility(View.VISIBLE);

                                            Log.e("value_found", " " + lab);
                                            make_name.setText(lab);
                                            cancel = true;
                                            all_bol = true;

                                            _vehiclemake = true;
                                        }
                                    }
                                } else if (naa.equals("_vehiclemodel")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_model.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            model_name.setText(lab);
                                            cancel = true;
                                            all_bol = true;

                                            _vehiclemodel = true;
                                        }
                                    }
                                } else if (naa.equals("_invoiceno")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_InvoiceNo.setVisibility(View.VISIBLE);

                                            Log.e("value_found", " " + lab);
                                            invoice_numtext.setText(lab);
                                            cancel = true;
                                            all_bol = true;

                                            _invoiceno = true;
                                        }
                                    }
                                } else if (naa.equals("_serial")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_SerialNo.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            serial.setText(lab);
                                            cancel = true;
                                            all_bol = true;

                                            _serial = true;
                                        }
                                    }
                                } else if (naa.equals("_devicevalue")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_InvoiceVal.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            invoice_val_txt.setText(lab);
                                            cancel = true;
                                            all_bol = true;

                                            _devicevalue = true;
                                        }
                                    }
                                } else if (naa.equals("_imei")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox"))
                                            //    linear_Imei.setVisibility(View.VISIBLE);
                                            Imei.setText(lab);
                                        Log.e("value_found", " " + lab);
                                        cancel = true;
                                        all_bol = true;

                                        _imei = true;

                                    }
                                } else if (naa.equals("_age")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox"))
                                            Log.e("value_found", " " + lab);
                                        cancel = true;
                                        all_bol = true;

                                        _age = true;

                                    }
                                } else if (naa.equals("_relationship")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_rel.setVisibility(View.VISIBLE);
                                            relation_txt.setText(lab);
                                            Log.e("value_found", " " + lab);
                                            cancel = false;
                                            all_bol = true;

                                            _relationship = true;

                                        }
                                    }
                                } else if (naa.equals("_dob")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_Dob.setVisibility(View.VISIBLE);
                                            Dobs.setText(lab);
                                            Log.e("value_found", " " + lab);
                                            cancel = false;
                                            all_bol = true;

                                            _dob = true;
                                        }
                                    }
                                } else if (naa.equals("_vehiclechasis")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_chas.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            cancel = true;
                                            all_bol = true;

                                            _vehiclechasis = true;
                                        }
                                    }
                                } else if (naa.equals("_vehicleregno")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox"))
                                        {
                                            linear_reg.setVisibility(View.VISIBLE);
                                        Log.e("value_found", " " + lab);
                                        cancel = true;
                                        all_bol = true;

                                        _vehicleregno = true;

                                    }
                                    }

                                } else if (naa.equals("_vehiclevalue")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_veh_val.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            cancel = true;
                                            all_bol = true;

                                            _vehiclevalue = true;
                                        }
                                    }
                                } else if (naa.equals("_vehiclesaledate")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_veh_saleDate.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            cancel = true;
                                            all_bol = true;

                                            _vehiclesaledate = true;

                                        }
                                    }
                                }

                                else if (naa.equals("_devicesaledate")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_InvoiceDate.setVisibility(View.VISIBLE);
                                            invoice_date_text.setText(lab);
                                            Log.e("value_found", " " + lab);
                                            cancel = true;
                                            all_bol = true;

                                            _devicesaledate = true;

                                        }
                                    }
                                }

                                else if (naa.equals("_vehiclecolor")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_veh_color.setVisibility(View.VISIBLE);
                                            Log.e("value_found", " " + lab);
                                            cancel = true;
                                            all_bol = true;

                                            _vehiclecolor = true;
                                        }
                                    }

                                } else if (naa.equals("_pan")) {
                                    if (!tyy.equals("")) {
                                        if (tyy.equals("selectbox") || tyy.equals("textbox")) {
                                            linear_KycDoc_No.setVisibility(View.VISIBLE);
                                            kyc_no_txt.setText(lab);
                                            Log.e("value_found", " " + lab);
                                            cancel = false;
                                            all_bol = true;

                                            _pan = true;
                                        }
                                    }
                                }
                            }

                            Log.e("fieldsssss ", "--" + _devicesaledate);

                            if (all_bol == true) {
                                if (cancel == true) {
                                    doc2.setVisibility(View.VISIBLE);
/*                                    doc4.setVisibility(View.VISIBLE);
                                    doc3.setVisibility(View.VISIBLE);*/


                                    // linear_Invoice_SlabVal.setVisibility(View.VISIBLE);
                                    //   type.setText("Invoice Slab Value");
                                    main_id.setVisibility(View.VISIBLE);
                                    BOOL = false;

                                    Log.e("ooooooooo 1", "" + cancel);
                                    Log.e("ooooooooo 11", "" + all_bol);

                                } else {
                                    doc1.setVisibility(View.VISIBLE);
                                    //  linear_Invoice_SlabVal.setVisibility(View.VISIBLE);
                                    //  type.setText("Protect value");
                                    main_id.setVisibility(View.VISIBLE);
                                    BOOL = true;
                                    Log.e("ooooooooo 1", "" + cancel);
                                    Log.e("ooooooooo 12", "" + all_bol);
                                }
                            } else {
                                main_id.setVisibility(View.GONE);
                                Log.e("ooooooooo 13", "" + all_bol);
                            }
                        } catch (Exception e) {
                            Log.e(TAG + "Excep ", "" + e.toString());

                            e.printStackTrace();
                        }


                        // progressDoalog.hide();
                        //  Toast.makeText(MyProducts.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG + "getVisible Error", error.toString());
                        progressDoalog.hide();
                        Toast.makeText(Products.this, TAG + " | getVisibleProducts | " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "" + proid);
                return params;
            }

        };

        RetryPolicy policy = new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    private void loadData() {
        linearScroll.setVisibility(View.VISIBLE);
        bottom.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pGiff.setVisibility(View.GONE);
                viewGifflayout.setVisibility(View.GONE);
            }
        }, 3000);
    }

 /*   private void getProductData(final String CATID, final String ProId) {

        Log.e(TAG + "getProductData url", Jason_Urls.getProductDataUrl + "?Id=" + CATID + "&proid=" + ProId);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Jason_Urls.getProductDataUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG + "getProductData respo", response.toString());


                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String typeId = jsonObject.getString("typeId");
                                String brandId = jsonObject.getString("brandId");
                                String categoryIds = jsonObject.getString("categoryId");

                                if (categoryIds.equals(catid)) {
                                    JSONArray categorieslevelone = jsonObject.getJSONArray("categorieslevelone");

                                    for (int j = 0; j < categorieslevelone.length(); j++) {

                                        JSONObject jsonObject1 = categorieslevelone.getJSONObject(j);

                                        String attribute_name = jsonObject1.getString("attribute_name");
                                        if (attribute_name.equalsIgnoreCase("Mobile")) {
                                            doc3.setVisibility(View.VISIBLE);
                                            doc4.setVisibility(View.VISIBLE);
                                            linear_Imei.setVisibility(View.VISIBLE);

                                        }


                                        if (proid.equals("431")) {
                                            if (attribute_name.equals("Mobile")) {
                                                if (type_array.contains(attribute_name)) {
                                                    Log.e("device1234567 ", "" + attribute_name);
                                                } else {
                                                    type_array.add(attribute_name);
                                                    Log.e("device1234568 ", "" + attribute_name);
                                                }
                                            }
                                        } else {
                                            if (type_array.contains(attribute_name)) {
                                                Log.e("device1234567 ", "" + attribute_name);
                                            } else {
                                                type_array.add(attribute_name);
                                                Log.e("device1234568 ", "" + attribute_name);

                                            }
                                        }

                                        JSONArray categoriesleveltwo = jsonObject1.getJSONArray("categoriesleveltwo");

                                        if (categoriesleveltwo.length() == 0) {
                                            Log.e("deviceccccc ", "" + categoriesleveltwo.length());
                                            Log.e("devicecccccc ", "" + attribute_name + "--" + "NULL" + "--" + "NULL");
                                            HashMap<String, String> map = new HashMap<String, String>();

                                            map.put("type", attribute_name);
                                            map.put("product", "");
                                            map.put("model", "");
                                            mylist.add(map);
                                        } else {
                                            Log.e("devicedddddddd", "" + categoriesleveltwo.length());

                                            for (int k = 0; k < categoriesleveltwo.length(); k++) {
                                                JSONObject jsonObject2 = categoriesleveltwo.getJSONObject(j);

                                                String attribute_name1 = jsonObject2.getString("attribute_name");


                                                JSONArray categorieslevelthree = jsonObject2.getJSONArray("categorieslevelthree");

                                                if (categorieslevelthree.length() == 0) {
                                                    Log.e("deviceaaaaaaaa ", "" + categorieslevelthree.length());
                                                    Log.e("deviceaaaaaaaa ", "" + attribute_name + "--" + attribute_name1 + "--" + "NULL");
                                                    HashMap<String, String> map = new HashMap<String, String>();

                                                    map.put("type", attribute_name);
                                                    map.put("product", attribute_name1);
                                                    map.put("model", "");
                                                    mylist.add(map);
                                                } else {
                                                    Log.e("devicebbbbbbbb", "" + categorieslevelthree.length());
                                                    for (int l = 0; l < categorieslevelthree.length(); l++) {
                                                        JSONObject jsonObject3 = categorieslevelthree.getJSONObject(l);

                                                        String attribute_name2 = jsonObject3.getString("attribute_name");

                                                        Log.e("devicebbbbbbbb ", "" + attribute_name + "--" + attribute_name1 + "--" + attribute_name2);

                                                        HashMap<String, String> map = new HashMap<String, String>();

                                                        map.put("type", attribute_name);
                                                        map.put("product", attribute_name1);
                                                        map.put("model", attribute_name2);
                                                        mylist.add(map);

                                                    }
                                                }
                                            }
                                        }
                                    }
                                    //	msgResponse.setText(response.toString());
                                }
                            }
                        } catch (Exception e) {
                            Log.e(TAG + "Excep ", "" + e.toString());

                            progressDoalog.hide();
                            e.printStackTrace();
                        }


                        // Creating adapter for spinner
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, type_array) {

                        //    @TargetApi(Build.VERSION_CODES.M)
                            @Override
                            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                View v = null;
                                v = super.getDropDownView(position, null, parent);
                                // If this is the selected item position
                                if (position == 0) {

                                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                    text.setTextColor(Color.parseColor("#3d406b"));

                                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                                    v.setBackgroundColor(backgroundColor);

                                } else {
                                    // for other views
                                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                                    text.setTextColor(Color.parseColor("#3d406b"));
                                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                                    v.setBackground(backgroundColor);

                                }
                                return v;
                            }
                        };


                        // Drop down layout style - list view with radio button
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
                        // attaching data adapter to spinner
                        loadData();
                        // progressDoalog.hide();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Products.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Id", "" + CATID);
                params.put("proid", "" + ProId);

                return params;
            }
        };
        RetryPolicy policy = new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }*/

    @Override
    public Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            arg0.setMaxDate(calendar.getTimeInMillis());
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {

        Log.e("BOOLBOOL", "..." + BOOL + "-" + day + "-" + month + "-" + year);

        if (BOOL == true) {
            if (DOB_CHECK == false) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH) + 1;
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                int minYear = mYear - 18;

                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

                String dayy = null, monthh = null;

                if (mDay > 9) {
                    dayy = String.valueOf(mDay);
                } else {
                    dayy = "0" + mDay;
                }

                if (mMonth > 9) {
                    monthh = String.valueOf(mMonth);
                } else {
                    monthh = "0" + mMonth;
                }

                Log.e("minyear123_current", "..." + dayy + "-" + monthh + "-" + mYear);
                Log.e("minyear123_past", "..." + dayy + "-" + monthh + "-" + minYear);
                Log.e("minyear123_selected", "..." + day + "-" + month + "-" + year);

                String inputString11 = dayy + " " + monthh + " " + mYear;
                String inputString22 = dayy + " " + monthh + " " + minYear;
                String inputString33 = day + " " + month + " " + year;

                Log.e("days_found_00 ", "__" + inputString11);
                Log.e("days_found_01 ", "__" + inputString22);
                Log.e("days_found_02 ", "__" + inputString33);

                Date date1 = null, date2 = null, date3 = null;
                try {
                    date1 = myFormat.parse(inputString11);
                    date2 = myFormat.parse(inputString22);
                    date3 = myFormat.parse(inputString33);
                } catch (Exception e) {
                    Log.e(TAG + " date parse Excep ", "" + e.toString());

                    e.printStackTrace();
                }

                long difff = date1.getTime() - date2.getTime();
                long difffs = date3.getTime() - date2.getTime();
                long diff = TimeUnit.DAYS.convert(difff, TimeUnit.MILLISECONDS);
                long diffs = TimeUnit.DAYS.convert(difffs, TimeUnit.MILLISECONDS);

                Log.e("days_found_1 ", "" + difff);
                Log.e("days_found_1 s", "" + difffs);
                Log.e("days_found_2 ", "" + diff);      //"diff"  VALUE =  6575 DAYS OR 18 YEARS
                Log.e("days_found_2 s", "" + diffs);  // SELECTIONS DAYS

                if (diffs < 0) {
                    Dobb.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
                } else {
                    Toast.makeText(getApplicationContext(), "Age should be more than 18 Years", Toast.LENGTH_LONG).show();
                }

            } else {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH) + 1;
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                int minYear = mYear - 18;


                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

                String dayy = null, monthh = null;

                if (mDay > 9) {
                    dayy = String.valueOf(mDay);
                } else {
                    dayy = "0" + mDay;
                }

                if (mMonth > 9) {
                    monthh = String.valueOf(mMonth);
                } else {
                    monthh = "0" + mMonth;
                }

                Log.e("minyear123_current", "..." + dayy + "-" + monthh + "-" + mYear);
                Log.e("minyear123_past", "..." + dayy + "-" + monthh + "-" + minYear);
                Log.e("minyear123_selected", "..." + day + "-" + month + "-" + year);

                String inputString11 = dayy + " " + monthh + " " + mYear;
                String inputString22 = dayy + " " + monthh + " " + minYear;
                String inputString33 = day + " " + month + " " + year;

                Log.e("days_found_00 ", "__" + inputString11);
                Log.e("days_found_01 ", "__" + inputString22);
                Log.e("days_found_02 ", "__" + inputString33);

                Date date1 = null, date2 = null, date3 = null;
                try {
                    date1 = myFormat.parse(inputString11);
                    date2 = myFormat.parse(inputString22);
                    date3 = myFormat.parse(inputString33);
                } catch (Exception e) {
                    Log.e(TAG + " date parse Excep ", "" + e.toString());

                    e.printStackTrace();
                }

                long difff = date1.getTime() - date2.getTime();
                long difffs = date3.getTime() - date2.getTime();
                long diff = TimeUnit.DAYS.convert(difff, TimeUnit.MILLISECONDS);
                long diffs = TimeUnit.DAYS.convert(difffs, TimeUnit.MILLISECONDS);

                Log.e("days_found_1 ", "" + difff);
                Log.e("days_found_1 s", "" + difffs);
                Log.e("days_found_2 ", "" + diff);      //"diff"  VALUE =  6575 DAYS OR 18 YEARS
                Log.e("days_found_2 s", "" + diffs);  // SELECTIONS DAYS


                if (diffs < 0) {
                    nom_dob.setText(new StringBuilder().append(day).append("/")
                            .append(month).append("/").append(year));
                } else {
                    Toast.makeText(getApplicationContext(), "Age should be more than 18 Years", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Log.e("BOOLBOOL123", "..." + BOOL + "-" + day + "-" + month + "-" + year);

            DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
            Date date = new Date();

            String dayy = "", monthh = "";

            if (day > 9) {
                dayy = "" + day;
            } else {
                dayy = "0" + day;
            }

            if (month > 9) {
                monthh = "" + month;
            } else {
                monthh = "0" + month;
            }

            String inputString11 = dateFormat.format(date);
            String inputString22 = dayy + " " + monthh + " " + year;

            Log.e("days_found_00 ", "__" + inputString11);
            Log.e("days_found_01 ", "__" + inputString22);

            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
         /*   String inputString1 = "27 03 1997";
            String inputString2 = "27 04 1997";
*/
            try {
                Date date1 = myFormat.parse(inputString11);
                Date date2 = myFormat.parse(inputString22);
                long difff = date1.getTime() - date2.getTime();
                long diff = TimeUnit.DAYS.convert(difff, TimeUnit.MILLISECONDS);

                Log.e("days_found_1 ", "" + difff);
                Log.e("days_found_2 ", "" + diff);

                if (diff > 30) {
                    Toast.makeText(getApplicationContext(), "PURCHASE DATE SHOULD BE WITHIN 30 DAYS", Toast.LENGTH_LONG).show();
                    InvoiceDate.setText("PICK DATE");
                } else if (diff < 0) {
                    Toast.makeText(getApplicationContext(), "SORRY! INVALID DATE SELECTION", Toast.LENGTH_LONG).show();
                    InvoiceDate.setText("PICK DATE");
                } else {
                    String d = "" + day;
                    String m = "" + monthh;

                    if (day < 10) {
                        d = "0" + day;
                    }
                    if (month < 10) {
                        m = "0" + month;
                    }


                    InvoiceDate.setText(new StringBuilder().append(d).append("-")
                            .append(m).append("-").append(year));
                }

                // System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                Log.e(TAG + " date parse Excep ", "" + e.toString());

                e.printStackTrace();
            }

        }
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            Log.e("Excep bitmap", "" + e.toString());

            return null;
        }
    }


    private byte[] getLogoImage(String url) {
        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayBuffer baf = new ByteArrayBuffer(500);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }

            return baf.toByteArray();
        } catch (Exception e) {
            Log.e("ImageManager", "Error: " + e.toString());
        }
        return null;
    }

    private void sendserver() {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Productid");
            param_values.add("" + proid);

            params.add("Variationid");
            param_values.add("" + variation_id);


            Log.e("resulttt product iddd", "" + proid);

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);
            js.execute(Jason_Urls.addcart_url);
            js.setOnResultsListener(Products.this);
        } catch (Exception e) {
            Log.e("Excep sendServer", "" + e.toString());

        }
    }


    private void receiveData() {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Productid");
            param_values.add("" + proid);

            Log.e(TAG + "calling JsonAsy", "" + Jason_Urls.addcart_url + "?Productid=" + proid);

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);
            js.execute(Jason_Urls.product_url);
            js.setOnResultsListener(Products.this);
        } catch (Exception e) {
            Log.e(TAG + "Excep recieve data", "" + e.toString());

        }
    }

    @Override
    public void onResultsSucceeded_Get(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Album_List(JSONObject result) {

    }

    @Override
    public void onResultsSucceeded_Post(JSONObject result) {
        Log.e(TAG + "onResultsSucc ", "onResultsSucc");

        try {
            if (result != null && B1 == true) {
                Log.e(TAG + "onResultsSucc ", "result " + result);

                Log.e("valuee2", "" + result);
                JSONObject jsonObject = result.getJSONObject("product");
                JSONObject jsonObject1 = jsonObject.getJSONObject("post");

                priceitem = jsonObject.getString("price");

                pr = priceitem;

              //  price.setText("₹" + priceitem);

                Log.e("priceee123", "" + priceitem);


                String post_content = jsonObject1.getString("post_content");

                Log.e("valuee7 ", "" + jsonObject1.getString("post_content"));

                pcontent10.loadDataWithBaseURL(null, post_content, "text/html", "utf-8", null);


                String jsonst = result.getString("variations");
                JSONObject terms = result.getJSONObject("Terms & Conditions");

                Log.e("valuee3", "" + jsonst);

                Log.e("valuee3termsterms", "" + terms);

                JSONObject TERMS = terms.getJSONObject("TERMS");

                String term = TERMS.getString("TERMS");

                JSONObject EXCLUSIONS = terms.getJSONObject("EXCLUSIONS");

                String excul = EXCLUSIONS.getString("EXCLUSIONS");

                message = term + "\n" + excul;

                Log.e("valuee3termsterms..", "" + term + "--" + excul);

                if (jsonst.equals("null")) {
                    String pricee = jsonObject.getString("price");
                    Log.e("valuee4", "" + price);
                  /*  spinner_free.setText("FREE");
                    spinner.setText("FREE");*/

                    variationRecord.insertRecord(proid, variation_id, pricee, "0");
                    Log.e("sizee2", "" + variationRecord.getAllUserAdd().size());

                } else {
                    JSONArray jsonObjec = result.getJSONArray("variations");

                    Log.e("valuee6", "" + jsonObjec);

                    for (int i = 0; i < jsonObjec.length(); i++) {
                        JSONObject jsonObj = jsonObjec.getJSONObject(i);

                        variation_id = jsonObj.getString("variation_id");
                        Log.e("valuee6variation_id", "" + variation_id);
                        String display_price = jsonObj.getString("display_price");

                        String json = jsonObj.getJSONObject("attributes").toString();

                        //  Log.e("tagg ",""+json);

                        String[] val = json.replace("{", " ").replace("}", " ").split(":");

                        String vset = val[0].replace("{", "");

                        Log.e("taggGGGG ", "" + vset);

                        if (vset.contains("device")) {
                            type.setText("Invoice Slab Value");
                            Log.e("tagg1 ", "" + vset);
                        } else if (vset.contains("plan")) {
                            bb = false;
                            type.setText("Plan Type");
                            Log.e("tagg2 ", "" + vset);
                        } else if (vset.contains("health")) {
                            bb = false;
                            type.setText("Health Protection");
                            Log.e("tagg3 ", "" + vset);
                        } else if (vset.contains("protect")) {
                            type.setText("Protection value");
                            // Log.e("tagg3 ", "" + vset);
                        } else if (vset.contains("policy")) {
                            type.setText("Policy Value");
                            // Log.e("tagg3 ", "" + vset);
                        }
                        String v = val[1];

                        String value1 = v.substring(1, v.length() - 1);
                        String value = value1.replaceAll("^\"|\"$", "");

                      //  Log.e("bbbbbbvalue ", "" + value);

                        if (bb == true) {
                            variationRecord.insertRecord(proid, variation_id, display_price, value);
                         //   price.setText("₹" + pr);

                        } else {
                            variationRecord.insertRecord(proid, variation_id, display_price, value);
                //            price.setText("₹" + pr);


                        }
                       // Log.e("sizee1", "" + variationRecord.getAllUserAdd().size());
                        cc.setVisibility(View.VISIBLE);
                    }
                }

            } else if (result != null && B2 == true) {
                //Log.e("resultttvaluee 55", "valuee" + result);
            } else {
               // Log.e("resulttt 2 ", "" + result);

                // price.setText("");

            }

        } catch (Exception e) {
            Log.e(TAG + "Exce ", "" + e.toString());

            e.printStackTrace();
        }

        datarecord();
    }

    private void datarecord() {

        if (variationRecord.getAllUserAdd().size() > 0) {
            categories = new ArrayList<String>();

            for (String attribut : variationRecord.getAllUserAdd()) {
        //        Log.e("attributes 111", "" + attribut);
                if (attribut.equals("0")) {
                    Log.e("attributes 222", "" + attribut);

                } else {

                    Log.e("attributes ", "" + "YEEEEEEESSS");

                    if (attribut.contains("-")) {
                        String a[] = attribut.split("-");
                        String val1 = a[0];
                        String val2 = a[1];
                        Log.e("attributes 1", "" + "YEEEEEEESSS");

                        try {
                            int max = Integer.parseInt(val2);


                            if (max > size) {
                                size = max;

                                Log.e("sizeeeeee ", "xxx" + size);
                            }

                            categories.add(attribut);

                            String var = variationRecord.getByIds(pr);
                          //  spinner.setText(var);

                        } catch (Exception EX) {
                            Log.e("Excep sendServer", "" + EX.toString());

                            //DIRECT_ADD(attribut);

                            String var = variationRecord.getByIds(pr);
                         //   spinner.setText(var);

                            EX.getMessage();
                        }
                    } else {
                        Log.e("attributes ", "" + "YEEEEEEESSS123");
                     //   DIRECT_ADD(attribut);

                        String var = variationRecord.getByIds(pr);
                       // spinner.setText(var);

                    }
                }
            }
        }

       /* // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.textview_with_background, categories) {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        spinner.setAdapter(dataAdapter);*/

       /* pGiff.setVisibility(View.GONE);
        viewGifflayout.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        bottom.setVisibility(View.VISIBLE);*/


        doc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("docccccc ", "=1=");

                selectImage();
                VAL = 1;
            }
        });

        doc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("docccccc ", "=4=");


                selectImage();
                VAL = 1;
            }
        });

        doc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("docccccc ", "=2=");

                selectImage();
                VAL = 2;
            }
        });
        doc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("docccccc ", "=3=");

                selectImage();
                VAL = 3;
            }
        });
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
              /*  Intent intent = new Intent(this, NavigationActivity.class);
                intent.putExtra("pager", value);
                startActivity(intent);*/
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


    private void selectImage() {


   /*     final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Attach Pdf",
                "Cancel"};*/
        final Dialog builder = new Dialog(Products.this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setContentView(R.layout.new_dialog);
        builder.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        // set values for custom dialog components - text, image and button
        TextView take_photo = (TextView) builder.findViewById(R.id.take_photo);
        TextView choose_gallery = (TextView) builder.findViewById(R.id.choose_gallery);
        TextView attach_pdf = (TextView) builder.findViewById(R.id.attach_pdf);
        TextView cancel_option = (TextView) builder.findViewById(R.id.cancel_option);

        builder.show();

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog = 0;


                boolean result1 = askForPermission(Manifest.permission.CAMERA, 2);
                if (result1) {
                    cameraIntent();
                }
                builder.dismiss();
                builder.hide();
                builder.cancel();
            }
        });

        choose_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog = 1;

                boolean result2 = askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 1);
                if (result2) {
                    Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhotoIntent, 200);
                }

                builder.dismiss();
                builder.hide();
                builder.cancel();

            }
        });

        attach_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog = 2;


                getDocument();

                Date now = new Date();
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(now);
                filename = timestamp + ".pdf";
                builder.dismiss();
                builder.hide();
                builder.cancel();

            }
        });

        cancel_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.dismiss();
                builder.hide();
                builder.cancel();
            }
        });

    }

    private void cameraIntent() {

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = null;
        try {
            photo = createImageFile();
            //  mImageUri = Uri.fromFile(photo);

            mImageUri = FileProvider.getUriForFile(Products.this,
                    BuildConfig.APPLICATION_ID + ".provider", photo);

            Log.e("valueeeee ", "---cameraIntent pass---" + mImageUri);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            //start camera intent
            startActivityForResult(intent, 100);
        } catch (Exception e) {
            Log.e("Excep s take picture!", "" + e.toString());

            Log.v("TAG", "Can't create file to take picture!");
            Log.v("TAG", "" + e.getMessage());

        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        picturePath = image.getAbsolutePath();

        Log.e("currentPhotoPath___img", "--" + image);
        Log.e("currentPhotoPath", "--" + picturePath);

        return image;
    }

    private void getDocument()

    {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 300);

    }


  /*  public static class Utility {
        public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

     //   @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static boolean checkPermission(final Context context) {
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        alertBuilder.setCancelable(true);
                        alertBuilder.setTitle("Permission necessary");
                        alertBuilder.setMessage("External storage permission is necessary");
                        alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                            }
                        });
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }*/


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("currentPhotoPath ", "--" + requestCode);
        if (requestCode == 200 && data != null
                && data.getData() != null) {

            Log.e("logggggggg gallery ", "--" + requestCode);

            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getApplicationContext().getContentResolver().query(uri, projection, null,
                    null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);

            picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();

            try {

                Bitmap bitmap = compressImage(picturePath);

                String imgg = getStringImage(bitmap);

                Log.e("currentq_gally", "--> " + imgg);
                Log.e("currentq_gally ", "--> " + VAL);

                Log.e("currentPhotoPath_glr ", "" + imgg);

                //    send_image1.setText(picturePath);
                if (VAL == 1) {
                    logoImage_t = bitmap;
                    DOC11.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));
                    DOC44.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));
                } else if (VAL == 2) {
                    logoImage_f = bitmap;
                    DOC22.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));
                } else if (VAL == 3) {
                    logoImage_s = bitmap;
                    DOC33.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));

                }

            } catch (Exception e) {
                Log.e(TAG + "onActivityResult excep", e.toString());
                e.getMessage();
            }
        } else if (requestCode == 100) {

            Log.e("logggggggg camera ", "--" + requestCode + "-- " + mImageUri);

            try {
                Bitmap bitmap = compressImage(picturePath);

                String imgg = getStringImage(bitmap);


                Log.e("currentPhotoPath_co ", "" + imgg);


                //    send_image1.setText(picturePath);
                if (VAL == 1) {
                    logoImage_t = bitmap;
                    DOC11.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));
                    DOC44.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));

                    Log.e("logggggggg camera 1", "--" + logoImage_t);
                } else if (VAL == 2) {
                    logoImage_f = bitmap;
                    DOC22.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));

                    Log.e("logggggggg camera 2", "--" + logoImage_f);
                } else if (VAL == 3) {

                    logoImage_s = bitmap;
                    DOC33.setText(picturePath.substring(picturePath.lastIndexOf("/") + 1));

                    Log.e("logggggggg camera 3", "--" + logoImage_s);

                }

            } catch (Exception e) {
                Log.e(TAG + "onActivityResult excep", e.toString());
                e.getMessage();
            }
        } else if (requestCode == 300) {
            // Get the Uri of the selected file

            Log.e("currentPhotoPath ", "--" + requestCode);

            Uri uri = data.getData();
            String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;

            Log.e("currentPhotoPath dt", "--" + data);

            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                        picturePath = displayName;

                        Log.e("currentPhotoPath ", "--" + picturePath);


                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();

                picturePath = displayName;

                Log.e("currentPhotoPath ", "--" + picturePath);
            }

            if (VAL == 1) {
                logoImage_f = bitmap;
                DOC11.setText(picturePath);
                DOC44.setText(picturePath);
            } else if (VAL == 2) {
                logoImage_s = bitmap;
                DOC22.setText(picturePath);
            } else if (VAL == 3) {
                logoImage_t = bitmap;
                try {
                    logoImage_t = bitmap;
                    DOC33.setText(picturePath);
                } catch (Exception ex) {
                    DOC33.setText("");
                }
            }

            uri = data.getData();

            Log.e("currentPhotoPath dtt", "--" + data);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getApplicationContext().getContentResolver(), uri);

                Log.e("currentPhotoPath ", "--" + uri);
                Log.e("currentPhotoPath ", "--" + bitmap);

                String imgg = getStringImage(bitmap);


                Log.e("currentPhotoPath_co ", "" + imgg);

                //    send_image1.setText(picturePath);


            } catch (Exception e) {
                e.getMessage();
            }
        }
    }


    public static Bitmap resizeAndCompressImageBeforeSend(String filePath) {
        // First decode with inJustDecodeBounds=true to check dimensions of image
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize(First we are going to resize the image to 800x800 image, in order to not have a big but very low quality image.
        //resizing the image will already reduce the file size, but after resizing we will check the file size and start to compress image
        options.inSampleSize = calculateInSampleSize(options, 300, 300);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bmpPic = BitmapFactory.decodeFile(filePath, options);

        return bmpPic;

    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        String debugTag = "MemoryInformation";
        // Image nin islenmeden onceki genislik ve yuksekligi
        final int height = options.outHeight;
        final int width = options.outWidth;
        Log.d(debugTag, "image height: " + height + "---image width: " + width);
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize = inSampleSize + 1;
            }
        }
        Log.d(debugTag, "inSampleSize: " + inSampleSize);
        return inSampleSize;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////


    private String getFileNameByUri(Context context, Uri uri) {
        String filepath = "";//default fileName
        //Uri filePathUri = uri;

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = getOutputMediaFile(1);
        uri = Uri.fromFile(file); // create
        i.putExtra(MediaStore.EXTRA_OUTPUT, uri); // set the image file
        startActivity(i);

        try {
            file = new File(new URI(uri.toString()));
            if (file.exists())
                filepath = file.getAbsolutePath();

        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return filepath;
    }


    private boolean isExternalStorageAvailable() {
        // find out what state external storage is in
        String state = Environment.getExternalStorageState();
        // if external storage is available, return true,
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private Uri getOutputMediaFileUri() {
        // To be safe, you should check that the SD card / external storage is mounted
        // using Environment.getExternalStorageState() before doing this.
        // see method below...
        if (isExternalStorageAvailable()) {
            String appName = Products.this.getString(R.string.app_name);
            // get the Uri

            // Get the external storage directory - we want to return a file object
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), appName);

            // Create our subdirectory
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdir()) {
                    Log.e("TAG", "Failed to create directory");
                    return null;
                }
            }

            // Create a file to hold the image
            File mediaFile;
            // get the current date and time
            Date now = new Date();
            // convert the date and time into a String datetimestamp
            // see http://developer.android.com/guide/topics/media/camera.html#saving-media for the methods used
            // need to append a timestamp to make it unique - otherwise it will overwrite the previous photo
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(now);

            String path = mediaStorageDir.getPath() + File.separator;

            // create a new file using the constructor that takes a name

            mediaFile = new File(path + "IMG_" + timestamp + ".jpg");
            filename = path + "IMG_" + timestamp + ".jpg";
            Log.d("TAG", "File: " + Uri.fromFile(mediaFile));
            Log.d("TAGGGG", "Fileeeeeee: " + path + "IMG_" + timestamp + ".jpg");

            // Return the files URI
            Log.d("TAG", "Returning the files URI");
            return Uri.fromFile(mediaFile);
        } else {
            return null;
        }
    }

    private File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyApplication");

        /**Create the storage directory if it does not exist*/
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        return mediaStorageDir;
    }

    public String getPDFPath(Uri uri) {

        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  imageView.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //  imageView.setImageBitmap(bm);
    }

   /* private void uploadImage()
    {
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(MainActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
             //   String image = getStringImage(bitmap);

                //Getting Image Name
                String name = editTextName.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }*/


    private void DIRECT_ADD(String attribut) {
        spinner.setVisibility(View.GONE);
        spinner_type.setVisibility(View.VISIBLE);

        categories.add(attribut);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Products.this, R.layout.textview_with_background, categories) {

         //   @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);
                    ;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                } else {
                    // for other views
                    TextView text = (TextView) v.findViewById(R.id.textViewalign);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }
                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview_with_background);
        // attaching data adapter to spinner

        spinner_type.setAdapter(dataAdapter);
    }

/////////////// SUBMIT CONDITIONS ////////////////////

    private void CHECK_CONDITION() {

        buy_now.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //  progressDoalog.show();
//
                B1 = false;
                B2 = true;
                Log.e("priceee ", "" + pr);
                if(pr.equals("0"))
                {
                    pr=calculatedAmount;
                }
                //datavalueserver
                Log.e("database77 1", "add_type=" + add_type + "-" + add_make + "-" + add_model);
                Log.e("database77 2", "" + pr + "-" + add_variation + "-" + add_variation_id);
                Log.e("database77 3", "" + InvoiceVal.getText().toString() + "-" + invoice_num.getText().toString() + "-" + InvoiceDate.getText().toString());
                Log.e("database77 4", "" + Imei_val.getText().toString() + "-" + serial_val.getText().toString());

                Log.e("database77 5", "" + add_kyctype + "-" + kycDoc_num.getText().toString());
                Log.e("database77 6", "" + nominee_name.getText().toString() + "-" + nom_dob.getText().toString());
                Log.e("database77 7", "" + relation.getText().toString() + "-" + Dobb.getText().toString());


                //  sendserver();

                Boolean check = false;

                for (int ux = 0; ux < myDataBase.getAllPId().size(); ux++) {
                    String PROID = myDataBase.getAllPId().get(ux);

                    Log.e("pidddddddddddd ssss", "-------" + PROID + "--" + proid);

                    if (proid.equalsIgnoreCase(PROID)) {

                        check = true;
                    }
                }

               /* Boolean _kycdocumenttype=false,_nominee=false,_nomineedob=false;
                Boolean _vehicletype=false,_vehiclemake=false,_vehiclemodel=false;
                Boolean _devicevalue= false,  _imei = false,   _age = false,   _relationship = false,  _dob = false,
                _vehiclechasis = false,  _vehicleregno = false, _vehiclevalue= false,
                        _devicesaledate = false, _vehiclecolor = false,  _pan =false,_vehiclesaledate=false;*/

                if (check == true) {

                    Toast.makeText(getApplicationContext(), "Product already in Cart", Toast.LENGTH_LONG).show();
                    //  showCartPopup();
                } else {
Log.e(TAG,"required_data add_type="+add_type);
                    if (add_type.equalsIgnoreCase("select") || add_type.equalsIgnoreCase("") && _devicetype == true) {
                        Toast.makeText(getApplicationContext(), "SELECT TYPE", Toast.LENGTH_LONG).show();
                    } else {
                        Log.e(TAG,"required_data add_make="+add_make);
                        if (add_make.equalsIgnoreCase("select") || add_make.equalsIgnoreCase("") && _devicemake == true) {
                            Toast.makeText(getApplicationContext(), "SELECT MAKE", Toast.LENGTH_LONG).show();
                        } else {
                            Log.e(TAG,"required_data add_model="+add_model);
                            if (add_model.equalsIgnoreCase("select") || add_model.equalsIgnoreCase("") && _devicemodel == true) {
                                Toast.makeText(getApplicationContext(), "SELECT MODEL", Toast.LENGTH_LONG).show();
                            } else {
                                String inv = invoice_num.getText().toString();
                                Log.e(TAG,"required_data inv="+ inv);
                                if (inv.length() == 0 && _invoiceno == true) {
                                    Toast.makeText(getApplicationContext(), "ENTER INVOICE NUMBER", Toast.LENGTH_LONG).show();
                                    Log.e("fieldssss ", "--" + InvoiceDate.getText().toString());
                                    Log.e("fieldssss ", "--" + Imei_val.getText().toString());
                                } else {
                                    Log.e("fieldssssfff ", "--" + InvoiceDate.getText().toString());
                                    Log.e("fieldssss ", "--" + Imei_val.getText().toString());

                                    String InvDate = InvoiceDate.getText().toString();
                                    if (InvDate.equalsIgnoreCase("PICK DATE") || InvDate.equalsIgnoreCase("") && _devicesaledate == true) {
                                        Toast.makeText(getApplicationContext(), "PICK DATE", Toast.LENGTH_LONG).show();
                                    } else {

                                        Log.e(TAG,"required_data serial_val="+ serial_val.getText().toString());

                                            if (serial_val.getText().toString().equalsIgnoreCase("") && _serial == true) {
                                                Toast.makeText(getApplicationContext(), "ENTER SERIAL NUMBER", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (serial_val.getText().toString().length() < 15 && _serial == true) {
                                                    Toast.makeText(getApplicationContext(), "INVALID SERIAL NUMBER", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Log.e(TAG,"required_data invoice_val_txt="+invoice_val_txt.getText().toString());
                                                    if (invoice_val_txt.getText().toString().equalsIgnoreCase("") && _devicevalue == true) {
                                                        Toast.makeText(getApplicationContext(), "ENTER INVOICE VALUE", Toast.LENGTH_LONG).show();
                                                    } else {
                                                        Log.e(TAG,"required_data nominee_name="+nominee_name.getText().toString());
                                                        if (nominee_name.getText().toString().equalsIgnoreCase("") && _nominee == true) {
                                                            Toast.makeText(getApplicationContext(), "ENTER NOMINEE NAME", Toast.LENGTH_LONG).show();
                                                        } else {
                                                            Log.e(TAG,"required_data nom_dob="+nom_dob.getText().toString());
                                                            if (nom_dob.getText().toString().equalsIgnoreCase("") && _nomineedob == true) {
                                                                Toast.makeText(getApplicationContext(), "ENTER NOMINEE DOB", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                Log.e(TAG,"required_data relation="+relation.getText().toString());
                                                                if (relation.getText().toString().equalsIgnoreCase("") && _relationship == true) {
                                                                    Toast.makeText(getApplicationContext(), "ENTER RELATIONSHIP", Toast.LENGTH_LONG).show();
                                                                } else {
                                                                    Log.e(TAG,"required_data Dobb="+Dobb.getText().toString());
                                                                    if (Dobb.getText().toString().equalsIgnoreCase("") && _dob == true) {
                                                                        Toast.makeText(getApplicationContext(), "ENTER DOB", Toast.LENGTH_LONG).show();
                                                                    } else {
                                                                        Log.e(TAG,"required_data add_kyctype="+add_kyctype);
                                                                        if (add_kyctype.toString().equalsIgnoreCase("") && _kycdocumenttype == true) {
                                                                            Toast.makeText(getApplicationContext(), "SELECT KYC DOC TYPE", Toast.LENGTH_LONG).show();
                                                                        } else {
                                                                            Log.e(TAG,"required_data kycDoc_num="+kycDoc_num.getText().toString());
                                                                            if (kycDoc_num.getText().toString().equalsIgnoreCase("") && _kycdocumenttype == true) {
                                                                                Toast.makeText(getApplicationContext(), "ENTER KYC NUMBER", Toast.LENGTH_LONG).show();
                                                                            } else if (!initmatecheck.isChecked()) {
                                                                                Toast.makeText(getApplicationContext(), "Choose Terms and Conditions", Toast.LENGTH_LONG).show();
                                                                            } else {

                                                                                Log.e("xxxxxxxxxx ", "--" + kyc_bol);

                                                                                if (logoImage_f == null || logoImage_s == null || logoImage_t == null) {
                                                                                    Log.e("xxxxxxxxxxyyyy ", "--" + kyc_bol);

                                                                                    if (!kyc_bol) {
                                                                                        if (doc2.getVisibility() == View.VISIBLE && logoImage_f == null) {
                                                                                            Toast.makeText(getApplicationContext(), "Please Upload Invoice", Toast.LENGTH_LONG).show();
                                                                                        } else if (doc3.getVisibility() == View.VISIBLE && logoImage_s == null) {
                                                                                            Toast.makeText(getApplicationContext(), "Please Upload Device Front Image", Toast.LENGTH_LONG).show();
                                                                                        } else if (doc4.getVisibility() == View.VISIBLE && logoImage_t == null) {
                                                                                            Toast.makeText(getApplicationContext(), "Please Upload Device Back Image", Toast.LENGTH_LONG).show();
                                                                                        } else {
                                                                                            if (linear_Imei.getVisibility() == View.VISIBLE) {
                                                                                                String Imeival = Imei_val.getText().toString();
                                                                                                if (Imeival.length() == 0 && _imei == true) {
                                                                                                    Toast.makeText(getApplicationContext(), "ENTER IMEI NUMBER", Toast.LENGTH_LONG).show();
                                                                                                } else {
                                                                                                    String IM = Imei_val.getText().toString();

                                                                                                    Log.e("lengthlength", "---" + IM.length());

                                                                                                    if (IM.length() < 15 && _imei == true) {
                                                                                                        Toast.makeText(getApplicationContext(), "INVALID IMEI NUMBER", Toast.LENGTH_LONG).show();
                                                                                                    } else {
                                                                                                        addProductToCart();
                                                                                                    }
                                                                                                }

                                                                                            } else {
                                                                                                addProductToCart();

                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        if (logoImage_t == null) {
                                                                                            Toast.makeText(getApplicationContext(), "Please Upload Kyc documents", Toast.LENGTH_LONG).show();
                                                                                        } else {
                                                                                            logoImage_s = logoImage_t;
                                                                                            logoImage_f = logoImage_t;
                                                                                        }

                                                                                    }

                                                                                } else {

                                                                                    // clear cart ////
                                                                                    if (linear_Imei.getVisibility() == View.VISIBLE) {
                                                                                        String Imeival = Imei_val.getText().toString();
                                                                                        if (Imeival.length() == 0 && _imei == true) {
                                                                                            Toast.makeText(getApplicationContext(), "ENTER IMEI NUMBER", Toast.LENGTH_LONG).show();
                                                                                        } else {
                                                                                            String IM = Imei_val.getText().toString();

                                                                                            Log.e("lengthlength", "---" + IM.length());

                                                                                            if (IM.length() < 15 && _imei == true) {
                                                                                                Toast.makeText(getApplicationContext(), "INVALID IMEI NUMBER", Toast.LENGTH_LONG).show();
                                                                                            } else {
                                                                                                addProductToCart();
                                                                                            }
                                                                                        }

                                                                                    } else {
                                                                                        addProductToCart();

                                                                                    }

                                                                                }

                                                                            }
                                                                        }

                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }

            });
        }

        private void addProductToCart () {
            myDataBase.delall();
            myDataBas.delall();

            String b = add_make, m = add_model, t = add_type, im = Imei_val.getText().toString(), sr = serial_val.getText().toString(), d = InvoiceDate.getText().toString(), Doc_num = kycDoc_num.getText().toString(), inv_num = invoice_num.getText().toString(), rel = relation.getText().toString(), db = Dobb.getText().toString(), pn = add_kyctype, cl = "no";
            String Chasis = "no", Regno = "np", Value = "no", SaleDate = "no", nom_name = nominee_name.getText().toString(), nom_db = nom_dob.getText().toString(), kyc_num = "no", extra1 = "no", extra2 = "no", extra3 = "no";


            Log.e("xxxxxxxxxx ", "--" + t);
            Log.e("xxxxxxxxxx ", "--" + b);
            Log.e("xxxxxxxxxx ", "--" + m);

            Log.e("xxxxxxxxxx ", "--" + inv_num);
            Log.e("addingVal", "Imei_val.getText()=" + im);
            Log.e("xxxxxxxxxx ", "--" + sr);
            Log.e("xxxxxxxxxx ", "--" + d);

            Log.e("xxxxxxxxxx ", "--" + pname);
            Log.e("xxxxxxxxxx ", "--" + pr);
            Log.e("xxxxxxxxxx ", "--" + add_variation);
            Log.e("xxxxxxxxxx ", "--" + proid);
            Log.e("xxxxxxxxxx ", "--" + add_variation_id);

            Log.e("Image_logoImage1 ", "--" + logoImage1);
            Log.e("Image_logoImage_f", "--" + logoImage_f);
            Log.e("Image_logoImage_s", "--" + logoImage_s);
            Log.e("Image_logoImage_t", "--" + logoImage_t);

            myDataBase.insertRecord(pname, pr, add_variation, "1", proid, add_variation_id, b, m, im, t, sr, d, Doc_num, rel, db, inv_num, pn, cl, Chasis, Regno, Value, SaleDate, nom_name, nom_db, kyc_num, extra1, extra2, extra3);

            Bitmap.Config conf = Bitmap.Config.ARGB_4444; // see other conf types
            Bitmap bmp = Bitmap.createBitmap(100, 100, conf); // this creates a MUTABLE bitmap

            Bitmap resized1 = bmp, resized2 = bmp, resized3 = bmp, resized4 = bmp;


            // = logoImage1, resized2 = logoImage_f, resized3 = logoImage_s, resized4 = logoImage_t;
            if (logoImage1 != null) {
                resized1 = logoImage1;
            }
            if (logoImage_f != null) {
                resized2 = logoImage_f;
            }
            if (logoImage_s != null) {
                resized3 = logoImage_s;
            }
            if (logoImage_t != null) {
                resized4 = logoImage_t;
            }

            myDataBas.insertBitmap(resized1, resized2, resized3, resized4, proid);

            if (dialog != null) {
                dialog.dismiss();
            }

            Intent i = new Intent(Products.this, NavigationActivity.class);
            i.putExtra("pager", "4");
            startActivity(i);
            finish();

        }

        public Bitmap compressImage (String filePath){

            Bitmap scaledBitmap = null;

            BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
            options.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

            int actualHeight = options.outHeight;
            int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

            float maxHeight = 816.0f;
            float maxWidth = 612.0f;
            float imgRatio = actualWidth / actualHeight;
            float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

            if (actualHeight > maxHeight || actualWidth > maxWidth) {
                if (imgRatio < maxRatio) {
                    imgRatio = maxHeight / actualHeight;
                    actualWidth = (int) (imgRatio * actualWidth);
                    actualHeight = (int) maxHeight;
                } else if (imgRatio > maxRatio) {
                    imgRatio = maxWidth / actualWidth;
                    actualHeight = (int) (imgRatio * actualHeight);
                    actualWidth = (int) maxWidth;
                } else {
                    actualHeight = (int) maxHeight;
                    actualWidth = (int) maxWidth;

                }
            }

//      setting inSampleSize value allows to load a scaled down version of the original image

            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
            options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inTempStorage = new byte[16 * 1024];

            try {
//          load the bitmap from its path
                bmp = BitmapFactory.decodeFile(filePath, options);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();

            }
            try {
                scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError exception) {
                exception.printStackTrace();
            }

            float ratioX = actualWidth / (float) options.outWidth;
            float ratioY = actualHeight / (float) options.outHeight;
            float middleX = actualWidth / 2.0f;
            float middleY = actualHeight / 2.0f;

            Matrix scaleMatrix = new Matrix();
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

            Canvas canvas = new Canvas(scaledBitmap);
            canvas.setMatrix(scaleMatrix);
            canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
            ExifInterface exif;
            try {
                exif = new ExifInterface(filePath);

                int orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, 0);
                Log.d("EXIF", "Exif: " + orientation);
                Matrix matrix = new Matrix();
                if (orientation == 6) {
                    matrix.postRotate(90);
                    Log.d("EXIF", "Exif: " + orientation);
                } else if (orientation == 3) {
                    matrix.postRotate(180);
                    Log.d("EXIF", "Exif: " + orientation);
                } else if (orientation == 8) {
                    matrix.postRotate(270);
                    Log.d("EXIF", "Exif: " + orientation);
                }
                scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                        scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                        true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileOutputStream out = null;
            String filename = getFilename();
            try {
                out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return scaledBitmap;

        }

        public String getFilename () {
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
            if (!file.exists()) {
                file.mkdirs();
            }
            String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
            return uriSting;
        }

        private String getRealPathFromURI (String contentURI){
            Uri contentUri = Uri.parse(contentURI);
            Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
            if (cursor == null) {
                return contentUri.getPath();
            } else {
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                return cursor.getString(index);
            }
        }

        private boolean askForPermission (String permission, Integer requestCode){
            if (ContextCompat.checkSelfPermission(Products.this, permission) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(Products.this, permission)) {

                    //This is called if user has denied the permission before
                    //In this case I am just asking the permission again
                    ActivityCompat.requestPermissions(Products.this, new String[]{permission}, requestCode);

                } else {

                    ActivityCompat.requestPermissions(Products.this, new String[]{permission}, requestCode);
                }
            } else {
                // Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();

                return true;
            }
            return false;
        }


        @Override
        public void onRequestPermissionsResult ( int requestCode, String[] permissions,
        int[] grantResults){
            Log.e("permissionx  ", "--" + permissions[0]);
            Log.e("permissionx  ", "--" + requestCode);
            Log.e("permissionx  ", "--" + grantResults);

            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
                switch (requestCode) {
                    //Location
                    case 1:

                        break;
                    //Read External Storage
                    case 2:
                        Toast.makeText(this, "Storage Permission granted", Toast.LENGTH_SHORT).show();
                        break;
                    //Camera
                    case 3:
                        Toast.makeText(this, "Camera Permission granted", Toast.LENGTH_SHORT).show();
                        break;

                }

            } else {


                if (requestCode == 1) {
                    Toast.makeText(this, "Location Permission denied", Toast.LENGTH_SHORT).show();
                } else if (requestCode == 2) {
                    Toast.makeText(this, "Storage Permission denied", Toast.LENGTH_SHORT).show();

                } else if (requestCode == 3) {
                    Toast.makeText(this, "Camera Permission denied", Toast.LENGTH_SHORT).show();

                }
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Permissions Required")
                        .setMessage("You have forcefully denied some of the required permissions " +
                                "for this action. Please open settings, go to permissions and allow them.")
                        .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();

            }
        }


    }