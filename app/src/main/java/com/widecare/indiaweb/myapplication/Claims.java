package com.widecare.indiaweb.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.CategoryDataBase;
import Constant.PlayGifV;
import Constant.Shaved_shared_preferences;
import Model.CatProduct;
import Model.ExpandableAdapterClaims;
import Model.ItemDetail;

public class Claims extends Fragment implements Asnychronus_notifier , ExpandableListView.OnGroupExpandListener
{
    Shaved_shared_preferences shaved_shared_preferences;
    int connection=0;
    Boolean bol=true;
    PlayGifV pGif;
    TextView viewTEXT,tv_claimStaus;
    ExpandableListView exList ;
    ArrayList<String> idds2;
    private List<CatProduct> catList;
    private List<CatProduct> catLists;
    String tableName = "datatable";
    public static final String KEY_ID="id";
    public static String col1 = "NameCat";
    public static String col2 = "idd";
    public static String col3 = "Price";
    public static String col4 = "ordered";
    public static String col5 = "date";
    public static String col6 = "place";
    public static String col7 = "email";
    public static String col8 = "phone";
    public static String col9 = "policynumber";
    public static String col10 = "start";
    public static String col11 = "end";
    public static String col12 = "proid";
    public static String col13 = "Brand";
    public static String col14 = "Model";
    public static String col15 = "Imei";
    public static String col16 = "Type";
    public static String col17 = "Serial";
    public static String col18 = "DateE";
    public static String col19 = "Prce";

    public static String col20 = "_age";
    public static String col21 = "_dob";
    public static String col22 = "_sex";
    public static String col23 = "_pan";
    public static String col24 = "_color";

    ExpandableAdapterClaims exAapt;
    CategoryDataBase categoryDataBase;

    ArrayList<String> mycat;

    ArrayList<String> naming;
    ArrayList<String> idds;
    ArrayList<String> images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_claims2, container, false);
        categoryDataBase = new CategoryDataBase(getActivity());
        categoryDataBase.delall();
        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());
        catList = new ArrayList<>();
        exList = (ExpandableListView)rootView.findViewById(R.id.expandableListView2);
        pGif = (PlayGifV)rootView.findViewById(R.id.viewGifrr);
        viewTEXT = (TextView)rootView.findViewById(R.id.viewTEXTT);
        tv_claimStaus= (TextView)rootView.findViewById(R.id.tv_claimStaus);
        pGif.setImageResource(R.drawable.ringload);

        catLists = new ArrayList<>();
        naming = new ArrayList<>();
        idds = new ArrayList<>();
        images = new ArrayList<>();
        idds2 = new ArrayList<>();

        exList.setOnGroupExpandListener(this);

        Log.e("message112233o",""+shaved_shared_preferences.get_userid());

        //  Log.e("message112233o", "" + "dfdfd");

        idds2 = new ArrayList<>();
        if(shaved_shared_preferences.get_userid()=="" || shaved_shared_preferences.get_userid()==null )
        {
            Log.e("message112233o2",""+shaved_shared_preferences.get_userid());

            exList.setVisibility(View.VISIBLE);
            pGif.setVisibility(View.GONE);
            //  Log.e("message11o", "" + "dfdfd");
            viewTEXT.setText("User Need To Login");
        }
        else {
            if(isOnline())
            {
                Log.e("message112233o1",""+shaved_shared_preferences.get_userid());
                   received();
                //  pGif.setVisibility(View.GONE);

                //   viewTEXT.setText("User Need To Login");
            }
            else
            {
                Toast.makeText(getActivity(), " Internet Not Connected ", Toast.LENGTH_LONG).show();
                exList.setVisibility(View.VISIBLE);
                pGif.setVisibility(View.GONE);

                viewTEXT.setText("Internet Not Connected");

            }
        }

        mycat = new ArrayList<>();

        idds2.add("9");
        idds2.add("10");
        idds2.add("11");
        idds2.add("12");
        idds2.add("16");
        idds2.add("30");
        idds2.add("14");
        idds2.add("15");
        idds2.add("21");

        naming.clear();
        idds.clear();
        images.clear();


        return rootView;
    }
    private void received()
    {
        Log.e("userId", "" + "--"+shaved_shared_preferences.get_userid().toString());

        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("userId");
            param_values.add(shaved_shared_preferences.get_userid().toString());

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);

            js.execute(Jason_Urls.user_claims_url);
            js.setOnResultsListener(this);

        } catch (Exception e)
        {
            pGif.setVisibility(View.GONE);

            //      Log.e("message33", "" + "dfdfd");
            viewTEXT.setText("User Need To Login");
            pGif.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "USER MUST NEED TO LOGIN", Toast.LENGTH_LONG).show();


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
        if (result != null && result.length() > 0  && bol == true)
        {
            Log.e("zzzzzyyyy",""+result);

            viewTEXT.setVisibility(View.GONE);

            try {
                String jsonStr = result.toString();

                JSONObject jsonObj = new JSONObject(jsonStr);

                String str = jsonObj.getString("Claims");
                if (str.equalsIgnoreCase("null")) {
                //    tv_claimStaus.setVisibility(View.VISIBLE);
                } else {
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

                            String IMEI = jsonObject.getString("IMEI");
                            String BRAND = jsonObject.getString("BRAND");
                            String MODEL = jsonObject.getString("MODEL");
                            String PURCHASEPRICE = jsonObject.getString("PURCHASEPRICE");
                            String PURCHASEDATE = jsonObject.getString("PURCHASEDATE");
                            String POLICYNUMBER = jsonObject.getString("POLICYNUMBER");
                            String STARTDATE = jsonObject.getString("STARTDATE");
                            String ENDDATE = jsonObject.getString("ENDDATE");
                            String TYPE = jsonObject.getString("TYPE");
                            String SERIAL = jsonObject.getString("SERIAL");
                            String COLOR = jsonObject.getString("COLOR");
                            String SEX = jsonObject.getString("SEX");
                            String AGE = jsonObject.getString("AGE");
                            String DOB = jsonObject.getString("DOB");
                            String PAN = jsonObject.getString("PAN");
                            String PRICE = jsonObject.getString("PRICE");

                            JSONArray jsonArray1 = jsonObject.getJSONArray("PRODUCTS");

                            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);

                            JSONObject jsonObject2 = jsonObject1.getJSONObject("post");

                            // String order_date = jsonObject1.getString("order_date");

                            String post_title = jsonObject2.getString("post_title");


                            JSONArray jsonArray11 = jsonObject.getJSONArray("PRODUCTS");

                            JSONObject jsonObject11 = jsonArray11.getJSONObject(0);

                            JSONObject jsonObject22 = jsonObject11.getJSONObject("post");

                            // String order_date = jsonObject1.getString("order_date");

                            String post_date = jsonObject22.getString("post_date");


                            Log.e("jsonObject... ", "NAME " + post_title);
                            Log.e("jsonObject... ", "categoryId " + categoryId);
                            Log.e("jsonObject... ", "POLICYNUMBER " + POLICYNUMBER);
                            Log.e("jsonObject123... ", "xxxxxxxxxxx " + TYPE + "-" + BRAND + "-" + MODEL);
                            //  Log.e("jsonObject... ", "order_date " + order_date);

                            mycat.add(categoryId);

                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
                            Date date_s = null, date_e = null;
                            try {
                                date_s = dt.parse(STARTDATE.toString());
                                date_e = dt.parse(ENDDATE.toString());

                                SimpleDateFormat dt1 = new SimpleDateFormat("dd-mm-yyyy");

                                STARTDATE = dt1.format(date_s);
                                ENDDATE = dt1.format(date_e);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            categoryDataBase.insertCatRecord(post_title, categoryId, PRICE, orderId,
                                    post_date, place, email, mobileno, POLICYNUMBER, STARTDATE,
                                    ENDDATE, productId, BRAND, MODEL, IMEI, TYPE, SERIAL, PURCHASEDATE,
                                    PURCHASEPRICE, AGE, DOB, SEX, PAN, COLOR);

                        }


                    }
                }
            }
         catch (JSONException e) {
            Log.e("Exception is", e.toString());
        }

    }
        if (result != null && result.length() > 0 && bol == false) {
            try {
                //    Log.e("messages... ", "bbbb");
                JSONArray jsonArray = result.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (idds2.contains(jsonObject.getString("catId")))
                    {
                        String catId = jsonObject.getString("catId");
                        String imagee = jsonObject.getString("img");
                        String cattname = jsonObject.getString("text");

                        String catname = cattname.substring(0,1).toUpperCase() + cattname.substring(1);


                              Log.e("CATTTTT ", "" + catname + " " + catId + " " + imagee);

                        naming.add(catname);
                        idds.add(catId);
                        images.add(imagee);

                        //    Log.e("iddddd ", "" + naming.get(i) + " " + naming.size());

                        //    Log.e("idddss1", "" + "yffjhfjh");
                    }

                    //   Log.e("idddss2", "" + "yffjhfjh");
                }
                //  Log.e("idddss3", "" + "yffjhfjh");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //   Log.e("idddss4", "" + "yffjhfjh");
            dataadd();
        }
        if (bol == true) {
            receiveData();
        }
    }

    private void dataadd()
    {
           Log.e("idddssrrrrr", "" + "ffffff" + naming.size()+"-- "+idds.size()+"--"+idds2.size()+"--"+mycat.size());
        pGif.setVisibility(View.GONE);
        if(mycat.size()>0) {
            for (String tempid : idds2) {

                for (int i = 0; i < naming.size(); i++) {

                    String n1 = naming.get(i);
                    String n2 = idds.get(i);
                    String n3 = images.get(i);

                    //     Log.e("order founding ", "" + tempid + "--" + n2);

                    if (n2.equals(tempid)) {
                        //   Log.e("order founded " + i, n1 + "--" + tempid + "--" + n2);
                        catList = new ArrayList<>();

                        CatProduct cat1 = createCategory(n1, n3, 1);


                            Log.e("order foundedsss " + i, "" + catLists.size());

                        SQLiteDatabase db = categoryDataBase.getReadableDatabase();

                              Log.e("databaseid_tochecking", "" + n2);

                        List<ItemDetail> results = new ArrayList<ItemDetail>();

                        String selectQuery = "SELECT * FROM " + tableName + " WHERE idd =" + n2;
                        Cursor cursor = db.rawQuery(selectQuery, null);
                        long count = 0;
                        if (cursor.getCount() > 0) {
                            while (cursor.moveToNext()) {
                                // Convert blob data to byte array

                                String NameCat = cursor.getString(cursor.getColumnIndex(col1));
                                String ida = cursor.getString(cursor.getColumnIndex(col2));
                                String polecat = cursor.getString(cursor.getColumnIndex(col3));
                                String order = cursor.getString(cursor.getColumnIndex(col4));
                                String date = cursor.getString(cursor.getColumnIndex(col5));
                                String policy = cursor.getString(cursor.getColumnIndex(col9));
                                String sdate = cursor.getString(cursor.getColumnIndex(col10));
                                String edate = cursor.getString(cursor.getColumnIndex(col11));
                                String proid = cursor.getString(cursor.getColumnIndex(col12));

                                String b = cursor.getString(cursor.getColumnIndex(col13));
                                String m = cursor.getString(cursor.getColumnIndex(col14));
                                String im = cursor.getString(cursor.getColumnIndex(col15));
                                String tyy = cursor.getString(cursor.getColumnIndex(col16));
                                String srr = cursor.getString(cursor.getColumnIndex(col17));
                                String d = cursor.getString(cursor.getColumnIndex(col18));
                                String p = cursor.getString(cursor.getColumnIndex(col19));

                                String ag = cursor.getString(cursor.getColumnIndex(col20));
                                String dobb = cursor.getString(cursor.getColumnIndex(col21));
                                String sx = cursor.getString(cursor.getColumnIndex(col22));
                                String pn = cursor.getString(cursor.getColumnIndex(col23));
                                String clr = cursor.getString(cursor.getColumnIndex(col24));

//                                String creates = date.replace(" ", "&");
//
//                                String create = creates.split("&")[0];

                                //      Log.e("founded--datacounting ", NameCat + "--" + String.valueOf(count)+create);
                                      Log.e("founded--datacounting ", NameCat + "--" +date);

                                String create = parseDateToddMMyyyy(date);

                                Log.e("founded--create ",create);

                                List<ItemDetail> result = new ArrayList<ItemDetail>();

                                ItemDetail item = new ItemDetail(ida, 0, NameCat, create,polecat,order,policy,sdate,edate,proid,b,m,im,tyy,srr,d,p,ag,dobb,sx,pn,clr);
                                result.add(item);
                                results.addAll(result);
                                result.clear();

                                cat1.setItemList(results);

                            }
                            catList.add(cat1);
                            //   Log.e("order foundedss " + i, "" + catList.size());

                            catLists.addAll(catList);
                            catList.clear();
                        }
                 }
               }
            }
        }
        else {
            tv_claimStaus.setVisibility(View.VISIBLE);

        }
        exAapt = new ExpandableAdapterClaims(catLists, getActivity());
        exList.setIndicatorBounds(0, 20);
        exList.setAdapter(exAapt);

        exList.setVisibility(View.VISIBLE);
        pGif.setVisibility(View.GONE);
    }


    private CatProduct createCategory(String name, String descr, long id) {
        return new CatProduct(id, name, descr);
    }

    @Override
    public void onGroupExpand(int groupPosition)
    {
        int len = exAapt.getGroupCount();

        for (int i = 0; i < len; i++) {
            if (i != groupPosition) {
                exList.collapseGroup(i);
            }
        }
    }

    private void receiveData()
    {

        bol=false;

        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Universal");
            param_values.add("data");

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);
            js.execute(Jason_Urls.categories_url);
            js.setOnResultsListener(this);
        } catch (Exception e) {

        }
    }



    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
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
