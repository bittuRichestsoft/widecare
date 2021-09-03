package com.widecare.indiaweb.myapplication;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.Shaved_shared_preferences;

public class ProfileActivity extends AppCompatActivity implements Asnychronus_notifier{
    Button saveBtn;
    Boolean monitorState = true;
    Calendar calendar;
    String idd;
    int year;
    int pos=0;
    int month;
    ArrayAdapter<String> dataAdapter = null;
    ArrayList<String> arrayList;
    Boolean bol = true;
    int day;
    String  [] st = new String[]{};
    RadioButton male, female;
    String gender;
    Spinner spinner;
    private static int SPLASH_TIME_OUT = 7000;
    String stateselcted;
    Shaved_shared_preferences shaved_shared_preferences;
    TextView firstname,lastnames,companyname,email_address,phone,town,country,state,address,address1,postcode;
  LinearLayout ll_refferBy,ll_refferCode;
    TextView tv_refferBy,tv_refferCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setIcon(R.drawable.userp_logo);
        }

        saveBtn = (Button)findViewById(R.id.saveBtnn);

        firstname = (TextView)findViewById(R.id.firstname);
        lastnames = (TextView)findViewById(R.id.lastnamesname);
        companyname = (TextView)findViewById(R.id.companyname);
        email_address = (TextView)findViewById(R.id.email_address);
        phone = (TextView)findViewById(R.id.phone);
        town = (TextView)findViewById(R.id.town);
        country = (TextView)findViewById(R.id.country);
        state = (TextView)findViewById(R.id.state);
        address = (TextView)findViewById(R.id.address);
        address1 = (TextView)findViewById(R.id.address1);
        postcode = (TextView)findViewById(R.id.pincode);
        spinner = (Spinner)findViewById(R.id.statespinner);
        male =(RadioButton)findViewById(R.id.male);
        female =(RadioButton)findViewById(R.id.female);
        tv_refferBy=(TextView)findViewById(R.id.tv_refferBy);
        tv_refferCode=(TextView)findViewById(R.id.tv_refferCode);
        ll_refferBy=(LinearLayout)findViewById(R.id.ll_refferBy);
        ll_refferCode=(LinearLayout) findViewById(R.id.ll_refferCode);
        ll_refferBy.setVisibility(View.GONE);
        ll_refferCode.setVisibility(View.GONE);



        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        arrayList = new ArrayList<>();
        arrayList.add("SELECT STATE");

        receivedata();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.text_back, arrayList){

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position
                if (position == 0) {

                    TextView text = (TextView)v.findViewById(R.id.textViewalignspinner);
                    text.setTextColor(Color.parseColor("#3d406b"));

                    int backgroundColor = getResources().getColor(R.color.greyspinner);;// ContextCompat.getColor(getContext(), R.color.blue);
                    v.setBackgroundColor(backgroundColor);

                }
                else {
                    // for other views
                    TextView text = (TextView)v.findViewById(R.id.textViewalignspinner);
                    text.setTextColor(Color.parseColor("#3d406b"));
                    Drawable backgroundColor = getResources().getDrawable(R.drawable.bkg);
                    v.setBackground(backgroundColor);

                }

                return v;
            }
        };

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.text_back);
        // attaching data adapter to spinner

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                stateselcted = parentView.getItemAtPosition(position).toString();
                Log.e("selected_item ",""+stateselcted);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        companyname.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(999);//THIS METHOD AUTOMATICALLY CALL override onCreateDialog Method

            }

        });



        shaved_shared_preferences = new Shaved_shared_preferences(getApplicationContext());

        idd = shaved_shared_preferences.get_userid();

        Log.e("user id ",""+idd);

        //fname
        if(shaved_shared_preferences.get_user_fname().length()>0)
        {
            firstname.setText(shaved_shared_preferences.get_user_fname().toString());
            Log.e("user1 ", "user");
        }
        else
        {
            firstname.setText(" ");
        }
        //lname
        if(shaved_shared_preferences.get_user_lsname().length()>0)
        {
            lastnames.setText(shaved_shared_preferences.get_user_lsname().toString());
            Log.e("user2 ", "user");
        }
        else
        {
            lastnames.setText("");
        }
        //cname
        if(shaved_shared_preferences.get_user_cname().length()>0)
        {
            companyname.setText(shaved_shared_preferences.get_user_cname().toString());
            Log.e("user3 ", "user");
        }
        else
        {
            companyname.setText(null);
        }
        //email
        if(shaved_shared_preferences.get_user_email().length()>0)
        {
            email_address.setText(shaved_shared_preferences.get_user_email().toString());
            Log.e("user4 ", "user");
        }
        else
        {
            email_address.setText(" ");
        }
        //phone
        if(shaved_shared_preferences.get_user_phone().length()>0)
        {
            phone.setText(shaved_shared_preferences.get_user_phone().toString());
            Log.e("user5 ", "user");
        }
        else
        {
            phone.setText(null);
        }
        //country
        if(shaved_shared_preferences.get_user_country().length()>0)
        {
            country.setText(shaved_shared_preferences.get_user_country().toString());
            Log.e("user6 ", "user");
        }
        else
        {
            country.setText(null);
        }
        try {

            if (shaved_shared_preferences.get_user_gender().length() > 0) {
                if (shaved_shared_preferences.get_user_gender().equals("Male")) {
                    male.setChecked(true);
                    female.setChecked(false);

                    gender = "Male";
                } else {
                    female.setChecked(true);
                    male.setChecked(false);

                    gender = "Female";
                }
                Log.e("genderyes ", "user" + shaved_shared_preferences.get_user_gender());
            } else {
                Log.e("genderno ", "userno");
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
        //town
        if(shaved_shared_preferences.get_user_town().length()>0)
        {
            town.setText(shaved_shared_preferences.get_user_town().toString());
            Log.e("user7 ", "user");
        }
        else
        {
            town.setText(null);
        }
        //state
        if(shaved_shared_preferences.get_user_state().length()>0)
        {
            state.setText(shaved_shared_preferences.get_user_state().toString());
            Log.e("user8 ", "user");

            stateselcted =  shaved_shared_preferences.get_user_state().toString();
        }
        else
        {
            state.setText(null);
        }
        //address
        if(shaved_shared_preferences.get_user_add().length()>0)
        {
            address.setText(shaved_shared_preferences.get_user_add().toString());
            Log.e("user9 ", "user");
        }
        else
        {
            address.setText(null);
        }
        //address
        if(shaved_shared_preferences.get_user_add().length()>0)
        {
            address1.setText(shaved_shared_preferences.get_user_add().toString());
            Log.e("user10 ", "user");
        }
        else
        {
            address1.setText(null);
        }
        //postcode
        if(shaved_shared_preferences.get_user_pincode().length()>0)
        {
            postcode.setText(shaved_shared_preferences.get_user_pincode().toString());
            Log.e("user10 ", "user");
        }
        else
        {
            postcode.setText(null);
        }

        // referCode
        if(shaved_shared_preferences.get_refferBy().length()>0)

        {
            ll_refferBy.setVisibility(View.VISIBLE);
            tv_refferBy.setText("Refer By :- "+shaved_shared_preferences.get_refferBy().toString());
            Log.e("user10 ", "refferBy="+shaved_shared_preferences.get_refferBy().toString());
        }
        if(shaved_shared_preferences.get_refferCode().length()>0)

        {
            ll_refferCode.setVisibility(View.VISIBLE);
            tv_refferCode.setText("Referral Code :- "+shaved_shared_preferences.get_refferCode().toString());
            Log.e("user10 ", "refferCode="+shaved_shared_preferences.get_refferCode().toString());
        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bol = false;
                Senddataa();

                Log.e("user11 ", "user");
            }
        });

        RadioGroup group = (RadioGroup) findViewById(R.id.gender);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (male.isChecked()) {
                    gender = "Male";
                } else if (female.isChecked()) {
                    gender = "Female";
                }
            }
        });
    }

    private void receivedata()
    {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.state_url);
            js.setOnResultsListener(this);

        } catch (Exception e)
        {

        }
    }


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
// arg1 = year
// arg2 = month
// arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        companyname.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));


    }

    private void Senddataa()
    {
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Userid");
            param_values.add(idd.toString());

            params.add("Firstname");
            param_values.add(firstname.getText().toString());

            params.add("Lastname");
            param_values.add(lastnames.getText().toString());

            params.add("Gender");
            param_values.add(gender);

            params.add("Bday");
            param_values.add(companyname.getText().toString());

            params.add("Email");
            param_values.add(email_address.getText().toString());


            params.add("Phone");
            param_values.add(phone.getText().toString());

            params.add("Address1");
            param_values.add(address.getText().toString());

            params.add("Address2");
            param_values.add(address1.getText().toString());

            params.add("City");
            param_values.add(town.getText().toString());

            params.add("State");
            param_values.add(stateselcted);

            params.add("Country");
            param_values.add(country.getText().toString());

            params.add("Postcode");
            param_values.add(postcode.getText().toString());

            Json_AsnycTask js = new Json_AsnycTask(getApplicationContext(), "post", params, param_values);


            js.execute(Jason_Urls.addupdate_url);
            js.setOnResultsListener(this);

        } catch (Exception e)
        {

        }
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
        Log.e("resulttt ","login person "+result);
        if (result != null && result.length() > 0 && bol==false) {
            try {
                String value = result.getString("text");
                //  String id = result.getString("userId");
                String gn = result.getString("gender");
                String phone = result.getString("billing_phone");
                String emails = result.getString("billing_email");
                String lssname = result.getString("billing_last_name");
                String fssname = result.getString("billing_first_name");
                String state = result.getString("billing_state");
                String country = result.getString("billing_country");
                String city = result.getString("billing_city");
                String birthday = result.getString("Bday");
                String postcode = result.getString("billing_postcode");
                String add1 = result.getString("billing_address_1");
                String add2 = result.getString("billing_address_2");

                Log.e("birthday ", "" + birthday);
                Log.e("birthday ", "" + gn);

                shaved_shared_preferences.set_user_login(1);
                //   shaved_shared_preferences.set_userid(id);
                shaved_shared_preferences.set_user_email(emails);
                shaved_shared_preferences.set_user_cname(birthday);
                shaved_shared_preferences.set_user_lsname(lssname);
                shaved_shared_preferences.set_user_fname(fssname);
                shaved_shared_preferences.set_user_state(state);
                shaved_shared_preferences.set_user_country(country);
                shaved_shared_preferences.set_user_phone(phone);
                shaved_shared_preferences.set_user_town(city);
                shaved_shared_preferences.set_user_add(add1);
                shaved_shared_preferences.set_user_add1(add2);
                shaved_shared_preferences.set_user_pincode(postcode);
                shaved_shared_preferences.set_user_gender(gn);


                Toast.makeText(getApplicationContext(), "User data Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, NavigationActivity.class);
                finish();
                startActivity(intent);

            } catch (JSONException e) {
                Log.e("Exception is", e.toString());
            }
        }
            if (result != null && result.length() > 0 && bol==true)
            {
                Log.e("resulttt ","state.. "+result);
                try
                {

                    final JSONArray keyArray = result.getJSONArray("state");
                    String[] keyAttributes = new String[keyArray.length()];
                    for(int i = 0; i < keyArray.length(); i++)
                    {
                        keyAttributes[i] = keyArray.getString(i);
                        Log.e("resulttt ", "attribute " + keyArray.getString(i));

                        arrayList.add(keyArray.getString(i));

                        if(shaved_shared_preferences.get_user_state().equals(keyArray.getString(i)))
                        {
                            Log.e("resulttt ", "attribute position "+i+"--" + keyArray.getString(i));

                            spinner.setSelection(i+1);
                        }

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                bol=false;
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
}
