package Constant;

/**
 * Created by indiaweb on 11/23/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by indiaweb on 11/8/2016.
 */
public class CategoryDataBases extends SQLiteOpenHelper {
    public static final String tableName = "cools";
    static String DATABASE_NAME = "categorydata";
    public static final String KEY_ID = "id";
    public static String col1 = "NameCat";
    public static String col2 = "idd";
    public static String col3 = "prefect";
    public static String col4 = "ordered";
    public static String col5 = "date";
    public static String col6 = "first_name";
    public static String col7 = "state";
    public static String col8 = "postcode";
    public static String col9 = "address_1";
    public static String col10 = "address_2";
    public static String col11 = "city";
    public static String col12 = "country";
    public static String col13 = "email";
    public static String col14 = "phone";
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
    public static String col25 = "Prce";

    public static String col26 = "_age";
    public static String col27 = "_dob";
    public static String col28 = "_sex";
    public static String col29 = "_pan";
    public static String col30 = "_color";
    String TAG="CategoryDataBases ";

    public CategoryDataBases(Context _cntxt) {

        super(_cntxt, "Catss", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + tableName + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + col1 + " TEXT, " + col2 + " TEXT, " + col3 + " TEXT, " + col4 + " TEXT, " + col5 + " TEXT, " + col6 + " TEXT, " + col7 + " TEXT, " + col8 + " TEXT, " + col9 + " TEXT, " + col10 + " TEXT, " + col11 + " TEXT, " + col12 + " TEXT, " + col13 + " TEXT, " + col14 + " TEXT, " + col15 + " TEXT, " + col16 + " TEXT, " + col17 + " TEXT, " + col18 + " TEXT, " + col19 + " TEXT, " + col20 + " TEXT, " + col21 + " TEXT, " + col22 + " TEXT, " + col23 + " TEXT, " + col24 + " TEXT, " + col25 + " TEXT, " + col26 + " TEXT, " + col27 + " TEXT, " + col28 + " TEXT, " + col29 + " TEXT, " + col30 + " TEXT)";
        db.execSQL(sql);
        System.out.println("Table is CreaTED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public long insertCatRecord(String str_NAME, String str_iddd, String str_price, String str_ordernum, String str_created_date,
                                String str_pName, String str_pstate, String str_postCode, String str_address1, String str_address2,
                                String str_city, String str_country, String str_emailadd, String str_phoneNum, String str_policyNo,
                                String str_startdate, String str_enddate, String str_prodId, String str_brand, String str_model, String str_imei,
                                String str_type, String str_serial, String str_purchasedDate, String str_purchasePrice, String str_age, String str_dob,
                                String str_sex, String str_pan, String str_color) {
        /*NAME, iddd, PRICE, ordernumber, created, pname, pstate, postcode, address_1, address_1,
         city, country, emailadd, phonenum, _policynumber,
          _strtdate, _enddate, product_id, _brand, _model, _imei,
          _type, _serial, _purchasedate, _purchaseprice, _age, _dob,
          _sex, _pan, _color);*/

        Log.e(TAG+ "db_InsertData","name="+str_NAME +"<>id="+str_iddd+"<>price"+str_price+"<>ordernum="+str_ordernum+"<>str_created_date"+str_created_date
                +"<>place="+str_pName+"<>str_pstate="+str_pstate+ "<>str_postCode="+str_postCode+"<>str_address1="+str_address1+"<>str_address2="+str_address2+"<>str_city="+str_city
                +"<>str_country="+str_country+"<>str_emailadd="+str_emailadd+"<>str_phoneNum="+str_phoneNum+"<>str_policyNo="+str_policyNo+"<>str_type="+str_type+"<>str_serial="+str_serial+"<>str_purchasedDate="+str_purchasedDate+"<>str_purchasePrice="+str_purchasePrice+"<>str_age="+str_age+"<>str_dob="+str_dob+"<>str_sex="+str_sex+"<>str_pan="+str_pan+"<>str_color="+str_color);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(col1, str_NAME);
        cv.put(col2, str_iddd);
        cv.put(col3, str_price);
        cv.put(col4, str_ordernum);
        cv.put(col5, str_created_date);
        cv.put(col6, str_pName);
        cv.put(col7, str_pstate);
        cv.put(col8, str_postCode);
        cv.put(col9, str_address1);
        cv.put(col10, str_address2);
        cv.put(col11, str_city);
        cv.put(col12, str_country);
        cv.put(col13, str_emailadd);
        cv.put(col14, str_phoneNum);
        cv.put(col15, str_policyNo);
        cv.put(col16, str_startdate);
        cv.put(col17, str_enddate);
        cv.put(col18, str_prodId);
        cv.put(col19, str_brand);
        cv.put(col20, str_model);
        cv.put(col21, str_imei);
        cv.put(col22, str_type);
        cv.put(col23, str_serial);
        cv.put(col24, str_purchasedDate);
        cv.put(col25, str_purchasePrice);
        cv.put(col26, str_age);
        cv.put(col27, str_dob);
        cv.put(col28, str_sex);
        cv.put(col29, str_pan);
        cv.put(col30, str_color);
        long cnt = db.insert(tableName, null, cv);

        return cnt;
    }

    //    public ArrayList<String> getNames()
//{
//    ArrayList<String> temp = new ArrayList<String>();
//
//    SQLiteDatabase db= getReadableDatabase();
//
//    String nam []={col1};
//
//    Cursor cursor =db.query(tableName, nam, null, null, null, null, null);
//    while(cursor.moveToNext())//row wise row
//    {
//        temp.add(cursor.getString(0));
//
//    }
//    return temp;
//}
    public ArrayList<String> getIdds() {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();

        String mob[] = {col2};

        Cursor cursor = db.query(tableName, mob, null, null, null, null, null);
        while (cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }

    public ArrayList<User> getData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Log.e("databaseid_tocheck", "" + id);

        ArrayList<User> arrayLists = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + tableName + " WHERE idd =" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Convert blob data to byte array
                User user = new User();

                String NameCat = cursor.getString(cursor.getColumnIndex(col1));
                String ida = cursor.getString(cursor.getColumnIndex(col2));
                String polecat = cursor.getString(cursor.getColumnIndex(col3));
                String order = cursor.getString(cursor.getColumnIndex(col4));
                String date = cursor.getString(cursor.getColumnIndex(col5));
                String policy = cursor.getString(cursor.getColumnIndex(col15));

                Log.e("database ", " " + NameCat + " " + ida + " " + polecat + " " + order + " " + date);

                user.setProduct(polecat);
                user.setNamy(NameCat);
                user.setDates(date);
                user.setOrdernum(order);
                user.setPolicys(ida);
                user.setPolicynumber(policy);
                arrayLists.add(user);
            }

        }

        return arrayLists;

    }

    public ArrayList<User> getDataByOrder(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Log.e("databaseorder_tocheck", "" + id);

        ArrayList<User> arrayLists = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + tableName + " WHERE ordered =" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Convert blob data to byte array
                User user = new User();

                String first_name = cursor.getString(cursor.getColumnIndex(col6));
                String state = cursor.getString(cursor.getColumnIndex(col7));
                String postcode = cursor.getString(cursor.getColumnIndex(col8));
                String address_1 = cursor.getString(cursor.getColumnIndex(col9));
                String address_2 = cursor.getString(cursor.getColumnIndex(col10));
                String city = cursor.getString(cursor.getColumnIndex(col11));
                String country = cursor.getString(cursor.getColumnIndex(col12));
                String email = cursor.getString(cursor.getColumnIndex(col13));
                String phony = cursor.getString(cursor.getColumnIndex(col14));


//                user.setMyname(first_name);
//                user.setMystate(state);
//                user.setMypostcode(postcode);
//                user.setMyaddress_1(address_1);
//                user.setMyaddress_2(address_2);
//                user.setMycity(city);
//                user.setMycountry(country);
//                user.setMyemailadd(email);
//                user.setMyphonenum(phony);


                arrayLists.add(user);
            }

        }

        return arrayLists;

    }

    public void update(String nam, String n, String mob, String m, String email, String e, String add, String a, String dt, String t) {
        String whr1 = col1 + "=?";
        String whr2 = col2 + "=?";
        String whr3 = col3 + "=?";
        String whr4 = col4 + "=?";
        String whr5 = col5 + "=?";

        String[] str1 = {nam};
        String[] str2 = {mob};
        String[] str3 = {email};
        String[] str4 = {add};
        String[] str5 = {dt};
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(col1, n);
        cv.put(col2, m);
        cv.put(col3, e);
        cv.put(col4, a);
        cv.put(col5, t);

        db.update(tableName, cv, whr1, str1);
        db.update(tableName, cv, whr2, str2);
        db.update(tableName, cv, whr3, str3);
        db.update(tableName, cv, whr4, str4);
        db.update(tableName, cv, whr5, str5);
    }

    public void delrow(String nam) {
        String whr = col1 + "=?";
        String[] str = {nam};
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, whr, str);
    }

    public void delall() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, null, null);
    }
}
