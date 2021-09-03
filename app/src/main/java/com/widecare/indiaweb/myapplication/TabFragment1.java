package com.widecare.indiaweb.myapplication;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Constant.Shaved_shared_preferences;

/**
 * Created by indiaweb on 9/15/2016.
 */
public class  TabFragment1 extends Fragment {
    Spinner initmatespinner;
    EditText initmateplace,initmatemobile,initmateemail,initmatedecription;
    TextView  initmatedate,initmatedob,initmatetime;
    LinearLayout initmateSubmit,initmateReset;
    String stateselcted,eml="--",number="--";
    ArrayList<String> arrayList;
    Calendar calendar ;
    int Year, Month, Day ;
    Date dd ;
    Date CNT_date ;
    int mHour=0,mMinute=0 ,mSecond=0;
    String DOB="",DATE="",TIME="";
    int connection=0;
    ArrayAdapter<String> dataAdapter;
    Shaved_shared_preferences shaved_shared_preferences;
    View rootview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_claim, container, false);

        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());


        initmatespinner = (Spinner) rootview.findViewById(R.id.initmatespinner);

        initmateSubmit = (LinearLayout) rootview.findViewById(R.id.initmatenext);
        initmateReset = (LinearLayout) rootview.findViewById(R.id.initmateReseting);

        initmateplace = (EditText) rootview.findViewById(R.id.initmateplace);
        initmatetime = (TextView) rootview.findViewById(R.id.initmatetime);
        initmatemobile = (EditText) rootview.findViewById(R.id.initmatemobile);
        initmateemail = (EditText) rootview.findViewById(R.id.initmateemail);
        initmatedecription = (EditText) rootview.findViewById(R.id.initmatedecription);


        initmatedate = (TextView) rootview.findViewById(R.id.initmatedate);
        initmatedob = (TextView) rootview.findViewById(R.id.initmatedob);


        arrayList = new ArrayList<>();
        arrayList.add("DAMAGE 1");
        arrayList.add("DAMAGE 2");

        eml = initmateemail.getText().toString();
        number = initmatemobile.getText().toString();

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH)+1;
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        String ss="";
        if(Month<10)
        {
            ss = "0"+Month;
        }
        else {
            ss = ""+Month;
        }
     //   current_date = Year + "-" + ss + "-" + Day;

      //  Log.e("vvvvvvvuu ", "==" + current_date);
        // DATE =  parseDateToddMMyyyy(DATE);

        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mSecond = calendar.get(Calendar.SECOND);



        initmateSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (initmateplace.getText().toString().length() > 0 && initmatetime.getText().toString().length() > 0 && initmatedecription.getText().toString().length() > 0) {
                    if (initmatedob.toString().length() > 0 && initmatedate.toString().length() > 0) {
                        //    if (initmatemobile.toString().length() >= 10) {
                        //   if (initmateemail.match(patern) {

                        shaved_shared_preferences.set_spinner(stateselcted);
                        shaved_shared_preferences.set_initmatetime(initmatetime.getText().toString());
                        shaved_shared_preferences.set_initmatedate(initmatedate.getText().toString());
                        shaved_shared_preferences.set_initmatedecription(initmatedecription.getText().toString());
                        shaved_shared_preferences.set_initmatedob(initmatedob.getText().toString());
                        shaved_shared_preferences.set_initmateplace(initmateplace.getText().toString());
                        shaved_shared_preferences.set_initmateemail(eml);
                        shaved_shared_preferences.set_initmatemobile(number);

                        Intent intent = new Intent(getActivity(), IntimateClaimActivity.class);
                        intent.putExtra("pager", "2");
                        startActivity(intent);
                        getActivity().finish();

                        Toast.makeText(getActivity(), "MOVE TO NEXT TAB", Toast.LENGTH_LONG).show();

                        Log.e("aaaaaaaaa ", "" + stateselcted + "---" + initmatetime.getText().toString() + "--" + initmatedate.getText().toString() + "--" + initmatedob.getText().toString());
                        Log.e("bbbbbb ", "" + initmatedecription.getText().toString() + "--" + initmateplace.getText().toString() + "--" + initmatemobile.getText().toString() + "--" + initmateemail.getText().toString());
                        //    } else {
                        //       initmateemail.setError("Invalid EmailId");
                        //   }
                        //   } else {
                        //      initmatemobile.setError("Invalid Number");
                        //   }
                    } else {
                        Toast.makeText(getActivity(), "PICK DATE", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "FILL ALL FIELDS", Toast.LENGTH_LONG).show();
                }
            }
        });

        initmateReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initmatedob.setText(null);
                initmatedate.setText(null);
                initmateplace.setText(null);
                initmatetime.setText(null);
                initmatedecription.setText(null);
                initmateemail.setText(null);
                initmatemobile.setText(null);

                Log.e("RESET ", "DATA");
            }
        });

        initmatetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {

                                int H = hourOfDay;
                                int M = minute;

                                String HH = "";
                                String MM = "";
                                String AM_PM = "";

                                if (H < 10) {
                                    HH = "0" + H;
                                    AM_PM = "AM";
                                } else {
                                    HH = "" + H;

                                    if (H > 12) {
                                        AM_PM = "PM";

                                        int hh = H - 12;
                                        HH = "" + hh;
                                    }
                                }


                                if (M < 10) {
                                    MM = "0" + M;
                                } else {
                                    MM = "" + M;
                                }

                                TIME = HH + ":" + MM + " " + AM_PM;

                                initmatetime.setText(" " + TIME);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });




        initmatedob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
                    {
                        int s = monthOfYear+1;
                        int D = dayOfMonth;
                        String ss = "";

                        if(s<10)
                        {
                            ss = "0"+s;
                        }
                        else {
                            ss = ""+s;
                        }

                        String DD = "";

                        if(D<10)
                        {
                            DD = "0"+D;
                        }
                        else {
                            DD = ""+D;
                        }

                        String a = year + "-" + ss + "-" + DD;
                        DOB =a;

                        shaved_shared_preferences.set_initmatedob(DOB);
                        //  DATE =  parseDateToddMMyyyy(DATE);

                        Log.e("search_datee ", "--" + DOB);

                        initmatedob.setText(" " + parseDateToddMMyyyy(DOB));

                    }
                };
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dd = sdf.parse("2017-01-01");
                    CNT_date = sdf.parse(DOB);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, Year,Month-1, Day);
                //  d.getDatePicker().setMaxDate(System.currentTimeMillis());
              //  d.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                 d.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                // d.getDatePicker().setMinDate(dd.getTime());
                d.show();


            }

        });

        initmatedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
                    {
                        int s = monthOfYear+1;
                        int D = dayOfMonth;
                        String ss = "";

                        if(s<10)
                        {
                            ss = "0"+s;
                        }
                        else {
                            ss = ""+s;
                        }

                        String DD = "";

                        if(D<10)
                        {
                            DD = "0"+D;
                        }
                        else {
                            DD = ""+D;
                        }

                        String a = year + "-" + ss + "-" + DD;
                        DATE =a;

                        shaved_shared_preferences.set_initmatedate(DATE);



                        //  DATE =  parseDateToddMMyyyy(DATE);

                        Log.e("search_datee ", "--" + DATE);

                        initmatedate.setText(" " + parseDateToddMMyyyy(DATE));

                    }
                };
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dd = sdf.parse("2017-01-01");
                    CNT_date = sdf.parse(DATE);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                DatePickerDialog d = new DatePickerDialog(getActivity(), dpd, Year,Month-1, Day);
                //  d.getDatePicker().setMaxDate(System.currentTimeMillis());
               // d.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                  d.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                // d.getDatePicker().setMinDate(dd.getTime());
                d.show();


            }

        });

       dataAdapter  = new ArrayAdapter<String>(getActivity(), R.layout.textview, arrayList) {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;
                v = super.getDropDownView(position, null, parent);
                // If this is the selected item position

                // for other views
                TextView text = (TextView) v.findViewById(R.id.textvw);
                text.setTextColor(Color.parseColor("#3d406b"));
                Drawable backgroundColor = getResources().getDrawable(R.drawable.box);
                v.setBackground(backgroundColor);


                return v;
            }
        };


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.textview);
        initmatespinner.setAdapter(dataAdapter);


        initmatespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                stateselcted = parentView.getItemAtPosition(position).toString();
                Log.e("selected item ",""+stateselcted);

                shaved_shared_preferences.set_spinner(stateselcted);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
     return  rootview;
    }
    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}