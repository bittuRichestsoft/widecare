package com.widecare.indiaweb.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
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

import java.util.ArrayList;
import java.util.Arrays;
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

/**
 * Created by indiaweb on 9/17/2016.
 */
public class Offers extends Fragment implements Asnychronus_notifier , ExpandableListView.OnGroupExpandListener
{
    PlayGifV pGif;
    Boolean bol=true;
    ExpandableAdapterClaims exAapt;
    int connection=0;
    private List<CatProduct> catList;
    private List<CatProduct> catLists;
    String tableName = "datatable";
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
    public static String col22 = "DateE";
    public static String col23 = "Prce";

    public static String col24 = "_age";
    public static String col25 = "_dob";
    public static String col26 = "_sex";
    public static String col27 = "_pan";
    public static String col28 = "_color";

    String product_id= "";
    String _strtdate= "";
    String _enddate= "";
    String _purchaseprice="";
    String _brand="";
    String _model="";
    String _purchasedate = "";
    String _imei="";
    String _policynumber= "";
    String iddd = "";
    String category = "";
    String categorys = "";
    Shaved_shared_preferences shaved_shared_preferences;
    List<String> eList;
    List<String> eLists;
    CategoryDataBase categoryDataBase;
    ArrayList<String> naming;
    ArrayList<String> idds;
    ArrayList<String> images;

    String _age="";
    String _dob="";
    String _pan="";
    String _sex="";
    String _color="";

    ArrayList<String> idds2;
    ExpandableListView exList ;
    TextView viewTEXT;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_offers, container, false);

        shaved_shared_preferences = new Shaved_shared_preferences(getActivity());
        categoryDataBase = new CategoryDataBase(getActivity());
        categoryDataBase.delall();

        exList = (ExpandableListView)rootView.findViewById(R.id.expandableListView22);
        pGif = (PlayGifV)rootView.findViewById(R.id.viewGifrr);
        viewTEXT = (TextView)rootView.findViewById(R.id.viewTEXTT);
        pGif.setImageResource(R.drawable.ringload);

        catLists = new ArrayList<>();
        naming = new ArrayList<>();
        idds = new ArrayList<>();
        images = new ArrayList<>();
        idds2 = new ArrayList<>();

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

        exList.setOnGroupExpandListener(this);
      //  Log.e("message112233o", "" + "dfdfd");
        if(shaved_shared_preferences.get_userid()=="")
        {
            exList.setVisibility(View.VISIBLE);
            pGif.setVisibility(View.GONE);
          //  Log.e("message11o", "" + "dfdfd");
            viewTEXT.setText("User Need To Login");
        }
        else {
            if(isInternetOn())
            {
                received();
                pGif.setVisibility(View.GONE);
              //  Log.e("message33o", "" + "dfdfd");
                viewTEXT.setText("User Need To Login");
            }
            else
            {
                Toast.makeText(getActivity(), " Internet Not Connected ", Toast.LENGTH_LONG).show();
                exList.setVisibility(View.VISIBLE);
                pGif.setVisibility(View.GONE);
            //    Log.e("message22o", "" + "dfdfd");
            }
        }
     //   Log.e("message44o", "" + "dfdfd");
        return rootView;
    }

    private void received()
    {
      //  Log.e("message", "" + "dfdfd");

        try {
            ArrayList<String> params = new ArrayList<String>();
            ArrayList<String> param_values = new ArrayList<String>();

            params.add("Userid");
            param_values.add(shaved_shared_preferences.get_userid().toString());
            // param_values.add("12");

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);

            js.execute(Jason_Urls.order_details_url);
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
     //   Log.e("message", "" + "dfdfd");
      //  Log.e("messageggg", "" + result);
        if (result != null && result.length() > 0 && bol == true) {
            pGif.setVisibility(View.VISIBLE);
            viewTEXT.setVisibility(View.GONE);
            try {
                Log.e("messages... ", "aaaaaa");
                JSONObject jsonObject = result.getJSONObject("orders");
            //    Log.e("messagelen", "" + jsonObject.length());


                for (int i = 1; i <= jsonObject.length(); i++) {

                    String j = String.valueOf(i);
                    JSONObject jsonObject1 = jsonObject.getJSONObject(j);

                    JSONObject jsonObject11 = jsonObject1.getJSONObject("shipping_address");
                    JSONObject jsonObject22 = jsonObject1.getJSONObject("billing_address");

                  Log.e("resultttcom ", ".. " + jsonObject1);

                    String pname = jsonObject11.getString("first_name");
                    String pstate = jsonObject11.getString("state");
                    String postcode = jsonObject11.getString("postcode");
                    String address_1 = jsonObject11.getString("address_1");
                    String address_2 = jsonObject11.getString("address_2");
                    String city = jsonObject22.getString("city");
                    String country = jsonObject22.getString("country");
                    String emailadd = jsonObject22.getString("email");
                    String phonenum = jsonObject22.getString("phone");
//
//                    ///////////////////////////////////////////////

                    String ordernumber = jsonObject1.getString("order_number");
                    String created = jsonObject1.getString("created_at");
                    String catId = jsonObject1.getString("catId");
               //     String sdate = jsonObject1.getString("startdate");
              //      String edate = jsonObject1.getString("enddate");

                    category = category + catId + ",";
                    categorys = catId + ",";

                    categorys = method(categorys);
                    eLists = Arrays.asList(categorys.split(","));

                    JSONArray jsonArray = jsonObject1.getJSONArray("line_items");


             //       Log.e("messagelength ", "" + jsonArray.length());

                    for (int k = 0; k < jsonArray.length(); k++) {
                        iddd = eLists.get(k);


                        JSONObject jsonObject2 = jsonArray.getJSONObject(k);

                        String NAME = jsonObject2.getString("name");
                        String PRICE = jsonObject2.getString("price");
                      //  String PROID = jsonObject2.getString("product_id");

                        JSONArray jsonArray1 = jsonObject2.getJSONArray("meta");

                   //     Log.e("length  ", "" + jsonArray1.length());
                        for(int jj = 0;jj<jsonArray1.length();jj++)
                        {
//
                            JSONObject jsonObject3 = jsonArray1.getJSONObject(jj);

                       //     Log.e("jsonObject3  ",""+jsonObject3);

                            String label =  jsonObject3.getString("label");

                            if(label.equals("_product_id"))
                            {
                                product_id =  jsonObject3.getString("value");
                            }

                            if(label.equals("_imei"))
                            {
                                _imei =  jsonObject3.getString("value");
                            }
                            if(label.equals("_brand"))
                            {
                                _brand =  jsonObject3.getString("value");
                            }
                            if(label.equals("_model"))
                            {
                                _model =  jsonObject3.getString("value");
                            }
                            if(label.equals("_purchasedate"))
                            {
                                _purchasedate =  jsonObject3.getString("value");
                            }
                            if(label.equals("_purchaseprice"))
                            {
                                _purchaseprice =  jsonObject3.getString("value");
                            }
                            if(label.equals("_policynumber"))
                            {
                                _policynumber =  jsonObject3.getString("value");
                            }
                            if(label.equals("_startdate"))
                            {
                                _strtdate =  jsonObject3.getString("value");
                            }
                            if(label.equals("_enddate"))
                            {
                                _enddate =  jsonObject3.getString("value");
                            }


                            if(label.equals("_age"))
                            {
                                _age =  jsonObject3.getString("value");
                            }
                            if(label.equals("_dob"))
                            {
                                _dob =  jsonObject3.getString("value");
                            }
                            if(label.equals("_pan"))
                            {
                                _pan =  jsonObject3.getString("value");
                            }
                            if(label.equals("_sex"))
                            {
                                _sex =  jsonObject3.getString("value");
                            }

                            if(label.equals("_color"))
                            {
                                _color =  jsonObject3.getString("value");
                            }
                       //     Log.e("jsonObject333  ",""+_brand+"--"+_model+"--"+_imei+"--"+_purchasedate+"--"+_purchaseprice);

                       //     Log.e("messagegot.. ", "" + PROID + "--" + policyNo + "--" + NAME + " " + iddd + " " + PRICE + " " + ordernumber + " " + created + " " + sdate + " " + edate);

                        }
                      //  categoryDataBase.insertCatRecord(NAME, iddd, PRICE, ordernumber, created, pname, pstate, postcode, address_1, address_1, city, country, emailadd, phonenum, _policynumber, _strtdate, _enddate,product_id,_brand,_model,_imei,_purchasedate,_purchaseprice,_age,_dob,_sex,_pan,_color);

                    }

                }
            } catch (JSONException e) {
                Log.e("Exception is", e.toString());
            }
        }
        if (result != null && result.length() > 0 && bol == false) {
            try {
            //    Log.e("messages... ", "bbbb");
                JSONArray jsonArray = result.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (eList.contains(jsonObject.getString("catId"))) {

                        String catId = jsonObject.getString("catId");
                        String imagee = jsonObject.getString("img");
                        String catname = jsonObject.getString("text");

                  //      Log.e("CAT ", "" + catname + " " + catId + " " + imagee);

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
     //   Log.e("idddss5", "" + "yffjhfjh");
        if (bol == true) {
            receiveData();
        }
      //  Log.e("idddss6", "" + "yffjhfjh");

    }

    private void dataadd()
    {
     //   Log.e("idddssrrrrr", "" + "ggggggggg" + naming.size());

        if(naming.size()>0) {
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


                    //    Log.e("order foundedsss " + i, "" + catLists.size());

                        SQLiteDatabase db = categoryDataBase.getReadableDatabase();

                  //      Log.e("databaseid_tochecking", "" + n2);
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
                                String policy = cursor.getString(cursor.getColumnIndex(col15));
                                String sdate = cursor.getString(cursor.getColumnIndex(col16));
                                String edate = cursor.getString(cursor.getColumnIndex(col17));
                                String proid = cursor.getString(cursor.getColumnIndex(col18));

                                String b = cursor.getString(cursor.getColumnIndex(col19));
                                String m = cursor.getString(cursor.getColumnIndex(col20));
                                String im = cursor.getString(cursor.getColumnIndex(col21));
                                String d = cursor.getString(cursor.getColumnIndex(col22));
                                String p = cursor.getString(cursor.getColumnIndex(col23));

                                String ag = cursor.getString(cursor.getColumnIndex(col24));
                                String dobb = cursor.getString(cursor.getColumnIndex(col25));
                                String sx = cursor.getString(cursor.getColumnIndex(col26));
                                String pn = cursor.getString(cursor.getColumnIndex(col27));
                                String clr = cursor.getString(cursor.getColumnIndex(col28));

                                String creates = date.replace(" ", "&");

                                String create = creates.split("&")[0];

                          //      Log.e("founded--datacounting ", NameCat + "--" + String.valueOf(count)+create);


                                List<ItemDetail> result = new ArrayList<ItemDetail>();

                              //  ItemDetail item = new ItemDetail(i, 0, NameCat, create,polecat,order,policy,sdate,edate,proid,b,m,im,d,p,ag,dobb,sx,pn,clr);
//                                result.add(item);
//                                results.addAll(result);
//                                result.clear();

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
        exAapt = new ExpandableAdapterClaims(catLists, getActivity());
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

            params.add("Universal");
            param_values.add("data");

            Json_AsnycTask js = new Json_AsnycTask(getActivity(), "post", params, param_values);
            js.execute(Jason_Urls.categories_url);
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
    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            Log.e("connection1 ", "connected" + connection);
            // if connected with internet
            //  if(connection==0)
            {
//                connection=1;
//                Log.e("connection2 ","connected"+connection);
//
//                //   Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
//                received();
                return true;
//            }
//
//        } else if (
//                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
//                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
//            connection = 0;
//            Log.e("connection3 ","connected"+connection);
//            Toast.makeText(getActivity(), " Internet Not Connected ", Toast.LENGTH_LONG).show();
//            return false;
            }
        }
        return false;
    }
}




