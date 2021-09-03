package com.widecare.indiaweb.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Asynchronus.Asnychronus_notifier;
import Asynchronus.Jason_Urls;
import Asynchronus.Json_AsnycTask;
import Constant.CategoryDataBases;
import Constant.PlayGifV;
import Constant.Shaved_shared_preferences;
import Model.CatProducts;
import Model.ExpandableAdapterPolicy;
import Model.ItemDetails;

public class MyPolicies extends Fragment implements Asnychronus_notifier , ExpandableListView.OnGroupExpandListener
{

    PlayGifV pGif;
    Boolean bol=true;
    int test = 0;
    ExpandableAdapterPolicy exAapt;
    private List<CatProducts> catList;
    private List<CatProducts> catLists;
    String tableName = "cools",TAG="MyPolicies ";
    public static String col1 = "NameCat";
    public static String col2 = "idd";
    public static String col3 = "prefect";
    public static String col4 = "ordered";
    public static String col5 = "date";
    public static String col15 = "policynumber";
    public static String col16 = "start";
    public static String col17 = "end";
    public static String col18 = "proid";
    public static String col19 = "Brand";
    public static String col20 = "Model";
    public static String col21 = "Imei";
    public static String col22 = "Type";
    public static String col23 = "Serial";
    public static String col24 = "DateE";
    public static String col25 = "Prce"; //Prce
    public static String col26 = "_age";
    public static String col27 = "_dob";
    public static String col28 = "_sex";
    public static String col29 = "_pan";
    public static String col30 = "_color";



    ArrayList<HashMap<String,String>> arrayList  = null;
    String product_id= "";
    String _policynumber= "";
    String _strtdate= "";
    String _enddate= "";
    String _purchaseprice="";
    String _brand="";
    String _model="";
    String _type="";
    String _serial="";
    String _purchasedate = "";
    String _imei="";
    String iddd = "";
   String category = "";
    String categorys = "";

    String _age="";
    String _dob="";
    String _pan="";
    String _sex="";
    String _color="";

    Shaved_shared_preferences shaved_shared_preferences;
    List<String> eList;
    List<String> eLists;
    CategoryDataBases categoryDataBase;
    ArrayList<String> naming;
    ArrayList<String> idds;
    ArrayList<String> images;
    TextView viewTEXT,tv_policyStaus;
    int connection=0;
    ArrayList<String> idds2;
    ExpandableListView exList ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_policy, container, false);

        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());
        categoryDataBase = new CategoryDataBases(getActivity());
        categoryDataBase.delall();

        exList = (ExpandableListView) rootView.findViewById(R.id.expandableListView1);
        pGif = (PlayGifV) rootView.findViewById(R.id.viewGifr);
        viewTEXT = (TextView) rootView.findViewById(R.id.viewTEXT);
        tv_policyStaus   = (TextView) rootView.findViewById(R.id.tv_policyStaus);
        pGif.setImageResource(R.drawable.ringload);

        catLists = new ArrayList<CatProducts>();
        naming = new ArrayList<>();
        idds = new ArrayList<>();
        images = new ArrayList<>();
        idds2 = new ArrayList<>();

         arrayList  = new ArrayList<>();

        idds2.add("9");
        idds2.add("10");
        idds2.add("11");
        idds2.add("12");
        idds2.add("16");
        idds2.add("30");
        idds2.add("14");
        idds2.add("15");
        idds2.add("21");
        idds2.add("45");

        naming.clear();
        idds.clear();
        images.clear();

        exList.setOnGroupExpandListener(this);


        if(isOnline())
        {
            Log.e("message112233", "" + "received");
            //    progress = ProgressDialog.show(this,null,"Please wait..");

            String UserId =  shaved_shared_preferences.get_userid();

            Log.e("message112233", "" + "received " + shaved_shared_preferences.get_userid());
            try {

                if (UserId.equalsIgnoreCase("")) {
                    viewTEXT.setText("User Need To Login");
                    pGif.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "USER MUST NEED TO LOGIN", Toast.LENGTH_LONG).show();
                } else {
                    received();
                }
            }
            catch (Exception ex)
            {
                viewTEXT.setText("User Need To Login");
                pGif.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "USER MUST NEED TO LOGIN", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            //  progress.dismiss();
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

     return  rootView;
    }





    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void received()
    {
        Log.e("message112233_userid", "" + "received..." + shaved_shared_preferences.get_userid().toString());

        //Log.e("jsonObject333444_idd", "" + shaved_shared_preferences.get_userid().toString());
        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();
            String strApiUrl=Jason_Urls.order_details_url;

            Log.e(TAG+"API_Hitting",strApiUrl+"?Userid="+shaved_shared_preferences.get_userid().toString());

            params.add("Userid");
            param_values.add(shaved_shared_preferences.get_userid().toString());


            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);

            js.execute(strApiUrl);
            js.setOnResultsListener(this);

        } catch (Exception e)
        {
            pGif.setVisibility(View.GONE);

         //   Log.e("message33", "" + "dfdfd");
            viewTEXT.setText("User Need To Login");
                pGif.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "USER MUST NEED TO LOGIN", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
     //  isInternetOn();
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


     Log.e(TAG+"messageggg_policy", "" + result);
        category = "";
        if (result != null && result.length() > 0 && bol == true) {

            viewTEXT.setVisibility(View.GONE);

            try {


                JSONObject jsonObject = result.getJSONObject("orders");

                for (int i = 1; i <= jsonObject.length(); i++) {

                    String j = String.valueOf(i);
                    JSONObject jsonObject1 = jsonObject.getJSONObject(j);

                    JSONObject jsonObject11 = jsonObject1.getJSONObject("shipping_address");
                    JSONObject jsonObject22 = jsonObject1.getJSONObject("billing_address");

                    String pname = jsonObject11.getString("first_name");
                    String pstate = jsonObject11.getString("state");
                    String postcode = jsonObject11.getString("postcode");
                    String address_1 = jsonObject11.getString("address_1");
                    String address_2 = jsonObject11.getString("address_2");
                    String city = jsonObject22.getString("city");
                    String country = jsonObject22.getString("country");
                    String emailadd = jsonObject22.getString("email");
                    String phonenum = jsonObject22.getString("phone");

                    String ordernumber = jsonObject1.getString("order_number");
                    String created = jsonObject1.getString("created_at");
                    String catId = jsonObject1.getString("catId");
                    String customer_id = jsonObject1.getString("customer_id");
                    String status = jsonObject1.getString("status");
                   if(status.equalsIgnoreCase("completed"))
                    {
                        Log.e("messageggg_policyyy",ordernumber+"---"+status);

                    if(catId.equalsIgnoreCase("0"))
                    {
                        catId = "9";
                    }

                    category = category + catId + ",";
                    categorys = catId + ",";

                    Log.e("messagescatt ", "categoryid " + catId);
                    Log.e("messagescatts ", "categoryid " + category);
                    Log.e("messagescattss ", "categoryid " + categorys);

                    categorys = method(categorys);
                    eLists = Arrays.asList(categorys.split(","));

                    JSONArray jsonArray = jsonObject1.getJSONArray("line_items");

                    for (int k = 0; k < jsonArray.length(); k++) {
                        iddd = eLists.get(k);


                        JSONObject jsonObject2 = jsonArray.getJSONObject(k);

                        String NAME = jsonObject2.getString("name");
                        String PRICE = jsonObject2.getString("price");

                        JSONArray jsonArray1 = jsonObject2.getJSONArray("meta");
                        for (int jj = 0; jj < jsonArray1.length(); jj++) {
                            JSONObject jsonObject3 = jsonArray1.getJSONObject(jj);
                            String label = jsonObject3.getString("label");
String val=jsonObject3.getString("value");
                           Log.e(TAG+"LABEL ="+jj, "" + label+":"+val);

                            if (label.equals("_product_id")) {
                                product_id = jsonObject3.getString("value");
                            }
                            if (label.equals("_startdate")) {
                                _strtdate = jsonObject3.getString("value");
                            }
                            if (label.equals("_enddate")) {
                                _enddate = jsonObject3.getString("value");
                            }
                            if (label.equals("_policynumber")) {
                                _policynumber = jsonObject3.getString("value");
                            }
                            if (label.equals("_devicemake")) {
                                _brand = jsonObject3.getString("value");

                            }
                            if (label.equals("_devicemodel")) {
                                _model = jsonObject3.getString("value");
                            }
                            if (label.equals("_devicetype")) {
                                _type = jsonObject3.getString("value");
                            }
                            if (label.equals("_brand")) {
                                _brand = jsonObject3.getString("value");
                            }
                            if (label.equals("_model")) {
                                _model = jsonObject3.getString("value");
                            }
                            if (label.equals("_type")) {
                                _type = jsonObject3.getString("value");
                            }
                            if (label.equals("_imei")) {
                                _imei = jsonObject3.getString("value");
                            }

                            if (label.equals("_serial")) {
                                _serial = jsonObject3.getString("value");
                            }

                            if (label.equals("_purchasedate")) {
                                _purchasedate = jsonObject3.getString("value");
                            }
                            if (label.equals("_purchaseprice")) {
                                _purchaseprice = jsonObject3.getString("value");
                            }

                             _purchaseprice = _strtdate;


                            if (label.equals("_age")) {
                                _age = jsonObject3.getString("value");
                            }
                            if (label.equals("_dob")) {
                                _dob = jsonObject3.getString("value");
                            }
                            if (label.equals("_pan")) {
                                _pan = jsonObject3.getString("value");
                            }
                            if (label.equals("_sex")) {
                                _sex = jsonObject3.getString("value");
                            }

                            if (label.equals("_vehiclecolor")) {
                                _color = jsonObject3.getString("value");
                            }

                            Log.e("jsonObject_data  ", "" + _brand + "--" + _model + "--" + _imei + "--" + _purchasedate + "--" + _purchaseprice);
                        }

                        Log.e("ggggggg", "--" + _strtdate);
                        Log.e("ggggggg", "--" + _enddate);

                 /*       ArrayList<HashMap<String,String>> arrayLisst  = new ArrayList<>();

                        arrayLisst.clear();

                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
                        Date date_s = null,date_e = null;

                        arrayLisst = FORMAT(dt,_strtdate,_enddate);

                        if(arrayLisst.size()==0 && test==0 || arrayLisst==null)
                        {

                                SimpleDateFormat DT1 = new SimpleDateFormat("dd-MMM-yy");

                                FORMAT(DT1,_strtdate,_enddate);

                                Log.e("gggggggg ", "-----" + arrayList.size());
                                test = 1;

                        }

                         _strtdate = arrayLisst.get(0).get("_strtdate");
                         _enddate = arrayLisst.get(0).get("_enddate");*/

                        Log.e("ggggggg", "-d-" + _strtdate);
                        Log.e("ggggggg", "-d-" + _enddate);

                        Log.e("ffffff123 ", "" + NAME + "-" + iddd + "-" + PRICE + "-" + ordernumber + "-" + created + "-" + pname + "-" + pstate + "-" + postcode + "-" + address_1 + "-" + address_1 + "-" + city + "-" + country + "-" + emailadd + "-" + phonenum);
                        Log.e("ffffff1234 ", "" + _policynumber + "-" + _strtdate + "-" + _enddate + "-" + product_id);
                      //  Log.e("ffffff12345 ", "_brand="+_brand + "<>_model=" + _model + "<>_imei=" + _imei + "<>_type=" + _type + "<>_serial=" + _serial + "<>_purchasedate=" + _purchasedate + "<>_purchaseprice=" + _purchaseprice + "<>_age=" + _age + "<>_dob=" + _dob + "<>_sex=" + _sex + "<>_pan=" + _pan + "<>_color=" + _color);

                        categoryDataBase.insertCatRecord(NAME, iddd, PRICE, ordernumber, created, pname, pstate, postcode, address_1, address_1, city, country, emailadd, phonenum, _policynumber, _strtdate, _enddate, product_id, _brand, _model, _imei, _type, _serial, _purchasedate, _purchaseprice, _age, _dob, _sex, _pan, _color);
                    }
                    }

                }
            } catch (JSONException e) {
                Log.e("Exception is", e.toString());
                pGif.setVisibility(View.GONE);
            }
            bol=true;
            pGif.setVisibility(View.GONE);
        }
        if (result != null && result.length() > 0 && bol == false) {
            try {

                JSONArray jsonArray = result.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Log.e("messages765888 ", "bbbb  "+jsonObject);
                    Log.e("messages7658899 ", "bbbb  "+jsonObject.getString("catId"));

                    if (eList.contains(jsonObject.getString("catId"))) {

                        String catId = jsonObject.getString("catId");
                        String imagee = jsonObject.getString("img");
                        String cattname = jsonObject.getString("text");

                        String catname = cattname.substring(0, 1).toUpperCase() + cattname.substring(1);

                        Log.e("CATttttttt ", "" + catname + " " + catId + " " + imagee);

                        naming.add(catname);
                        idds.add(catId);
                        images.add(imagee);
                    }}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            pGif.setVisibility(View.GONE);
            dataadd();
        }
        if (bol == true) {
            pGif.setVisibility(View.VISIBLE);
            receiveData();
        }
    }

    private ArrayList<HashMap<String,String>> FORMAT(SimpleDateFormat dt,String strtdate,String enddate)
    {

        arrayList.clear();

        Date date_s = null,date_e = null;
        try {
            date_s = dt.parse(strtdate);
            date_e = dt.parse(enddate);

            SimpleDateFormat dt1 = new SimpleDateFormat("dd-mm-yyyy");

            _strtdate = dt1.format(date_s);
            _enddate = dt1.format(date_e);

            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("_strtdate",""+_strtdate);
            hashMap.put("_enddate",""+_enddate);

            arrayList.add(hashMap);
        }
        catch (ParseException e)
        {

            e.printStackTrace();
        }


        return  arrayList;
    }

    private void dataadd()
    {
       Log.e("rrrrrrrrrrr", "" + "ggggggggg" + naming.size());

        if(naming.size()>0) {
            for (String tempid : idds2) {

                for (int i = 0; i < naming.size(); i++) {

                    String n1 = naming.get(i);
                    String n2 = idds.get(i);
                    String n3 = images.get(i);

                    Log.e("order_founding ", "" + tempid + "--" + n2);

                    if (n2.equals(tempid)) {
                    //    Log.e("order founded " + i, n1 + "--" + tempid + "--" + n2);
                        catList = new ArrayList<>();

                        CatProducts cat1 = createCategory(n1, n3, 1);


                      //  Log.e("order foundedsss " + i, "" + catLists.size());

                        SQLiteDatabase db = categoryDataBase.getReadableDatabase();

                     //   Log.e("databaseid_tochecking", "" + n2);
                        List<ItemDetails> results = new ArrayList<ItemDetails>();

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
                                String policy = cursor.getString(cursor.getColumnIndex(col15));
                                String sdate = cursor.getString(cursor.getColumnIndex(col16));
                                String edate = cursor.getString(cursor.getColumnIndex(col17));
                                String proid = cursor.getString(cursor.getColumnIndex(col18));

                                String b = cursor.getString(cursor.getColumnIndex(col19));
                                String m = cursor.getString(cursor.getColumnIndex(col20));
                                String im = cursor.getString(cursor.getColumnIndex(col21));
                                String ty = cursor.getString(cursor.getColumnIndex(col22));
                                String sr = cursor.getString(cursor.getColumnIndex(col23));
                                String str_purchasedDate= cursor.getString(cursor.getColumnIndex(col24));
                                String str_purchasedPrice= cursor.getString(cursor.getColumnIndex(col25));


                                String ag = cursor.getString(cursor.getColumnIndex(col26));
                                String dobb = cursor.getString(cursor.getColumnIndex(col27));
                                String sx = cursor.getString(cursor.getColumnIndex(col28));
                                String pn = cursor.getString(cursor.getColumnIndex(col29));
                                String clr = cursor.getString(cursor.getColumnIndex(col30));

                                String creates = date.replace(" ", "&");

                                String createe = creates.split("&")[0];

                               Log.e("founded--datacounting ", NameCat + "--" + String.valueOf(count)+createe);

                                Log.e("founded_data ", NameCat + "--" +createe+"--"+polecat+"--"+order+"--"+policy+"--"+sdate+"--"+edate+"--"+proid+"--"+b+"--"+m+"--"+im+"--"+ty+"--"+sr+"--pDATE="+str_purchasedDate+"--pPrice="+str_purchasedPrice+"--"+ag+"--"+dobb+"--"+sx+"--"+pn+"--"+clr);

                                String create = parseDateToddMMyyyy(createe);

                                List<ItemDetails> result = new ArrayList<ItemDetails>();

                                ItemDetails item = new ItemDetails(ida, 0, NameCat, create,polecat,order,policy,sdate,edate,proid,b,m,im,ty,sr,str_purchasedDate,str_purchasedPrice,ag,dobb,sx,pn,clr);
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
            tv_policyStaus.setVisibility(View.VISIBLE);
        }

        Log.e("length ",""+catLists.size());

            exAapt = new ExpandableAdapterPolicy(catLists, getActivity());
            exList.setIndicatorBounds(0, 20);
            exList.setAdapter(exAapt);

        exList.setVisibility(View.VISIBLE);
        pGif.setVisibility(View.GONE);
    }

    private void receiveData()
    {
        bol=false;
        category = method(category);
        eList = Arrays.asList(category.split(","));
        eList.toArray();

        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();
String strApiUrl=Jason_Urls.categories_url;
            Log.e(TAG+"API_Hitting categories",strApiUrl+"?Universal=data");
            params.add("Universal");
            param_values.add("data");

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);
            js.execute(strApiUrl);
            js.setOnResultsListener(this);
        } catch (Exception e) {

        }
    }
    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)==',') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    private CatProducts createCategory(String name, String descr, long id) {
        return new CatProducts(id, name, descr);
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

    public boolean isOnline() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}

