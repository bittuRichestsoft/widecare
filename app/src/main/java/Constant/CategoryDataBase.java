package Constant;

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
public class CategoryDataBase extends SQLiteOpenHelper
{
    public static final String tableName = "datatable";
    static String DATABASE_NAME="categorydata";
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
String  TAG="CategoryDataBase ";
    public CategoryDataBase(Context _cntxt)
    {

        super(_cntxt, "Cats", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql="CREATE TABLE "+tableName+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+col1+" TEXT, "+col2+" TEXT, "+col3+" TEXT, "+col4+" TEXT, "+col5+" TEXT, "+col6+" TEXT, "+col7+" TEXT, "+col8+" TEXT, "+col9+" TEXT, "+col10+" TEXT, "+col11+" TEXT, "+col12+" TEXT, "+col13+" TEXT, "+col14+" TEXT, "+col15+" TEXT, "+col16+" TEXT, "+col17+" TEXT, "+col18+" TEXT, "+col19+" TEXT, "+col20+" TEXT, "+col21+" TEXT, "+col22+" TEXT, "+col23+" TEXT, "+col24+" TEXT)";
        db.execSQL(sql);
        System.out.println("Table is CreaTED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {

    }

    public long insertCatRecord(String name, String id, String price, String ordernum, String date, String place, String email, String mobile, String policyNo, String startdate, String enddate, String proid, String BRAND, String MODEL , String IMEI, String TYPE, String sr, String d, String p, String _age, String _dob, String _sex, String _pan, String _color)
    {
        /*ENDDATE, productId, BRAND, MODEL, IMEI, TYPE, SERIAL, PURCHASEDATE,
                                    PURCHASEPRICE, AGE, DOB, SEX, PAN, COLOR*/
Log.e(TAG+ "db_InsertData","name="+name +"<>id="+id+"<>price"+price+"<>ordernum="+ordernum+"<>date"+date
        +"<>place="+place+"<>email="+email+ "<>mobile="+mobile+"<>policyNo="+policyNo+"<>startdate="+startdate+"<>enddate="+enddate
        +"<>proid="+proid+"<>BRAND="+BRAND+"<>MODEL="+MODEL+"<>IMEI="+IMEI+"<>TYPE="+TYPE +"<>sr="+sr+"<>d="+d +"<>p="+p +"<>_age="+_age +"<>_dob="+_dob+"<>_sex="+_sex+"<>_pan="+_pan +"<>_color="+_color);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(col1, name);
        cv.put(col2, id);
        cv.put(col3, price);
        cv.put(col4, ordernum);
        cv.put(col5, date);
        cv.put(col6, place);
        cv.put(col7, email);
        cv.put(col8, mobile);
        cv.put(col9, policyNo);
        cv.put(col10, startdate);
        cv.put(col11, enddate);
        cv.put(col12, proid);
        cv.put(col13, BRAND);
        cv.put(col14, MODEL);
        cv.put(col15, IMEI);
        cv.put(col16, TYPE);
        cv.put(col17, sr);
        cv.put(col18, d);
        cv.put(col19, p);
        cv.put(col20, _age);
        cv.put(col21, _dob);
        cv.put(col22, _sex);
        cv.put(col23, _pan);
        cv.put(col24, _color);
        long cnt = 	db.insert(tableName, null, cv);

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
    public ArrayList<String> getIdds()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String mob []={col2};

        Cursor cursor = db.query(tableName, mob, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }
    public ArrayList<User> getData(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Log.e("databaseid_tocheck",""+id);

        ArrayList<User> arrayLists = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+ tableName+" WHERE idd ="+id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount() >0)
        {
            while (cursor.moveToNext()) {
                // Convert blob data to byte array
                User user = new User();

                String NameCat = cursor.getString(cursor.getColumnIndex(col1));
                String ida = cursor.getString(cursor.getColumnIndex(col2));
                String polecat = cursor.getString(cursor.getColumnIndex(col3));
                String order = cursor.getString(cursor.getColumnIndex(col4));
                String date = cursor.getString(cursor.getColumnIndex(col5));
                String policy = cursor.getString(cursor.getColumnIndex(col15));

                Log.e("database "," "+NameCat+" "+ida+" "+polecat+" "+order+" "+date);

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

    public ArrayList<User> getDataByOrder(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Log.e("databaseorder_tocheck",""+id);

        ArrayList<User> arrayLists = new ArrayList<>();

            String selectQuery = "SELECT * FROM "+ tableName+" WHERE ordered ="+id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)
            {
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


                    user.setMyname(first_name);
                    user.setMystate(state);
                    user.setMypostcode(postcode);
                    user.setMyaddress_1(address_1);
                    user.setMyaddress_2(address_2);
                    user.setMycity(city);
                    user.setMycountry(country);
                    user.setMyemailadd(email);
                    user.setMyphonenum(phony);


                    arrayLists.add(user);
                }

            }

        return arrayLists;

    }

    public void update(String nam, String n,String mob,String m,String email,String e,String add, String a,String dt, String t)
    {
        String whr1 = col1+"=?";
        String whr2 = col2+"=?";
        String whr3 = col3+"=?";
        String whr4= col4+"=?";
        String whr5= col5+"=?";

        String [] str1= {nam};
        String [] str2= {mob};
        String [] str3= {email};
        String [] str4= {add};
        String [] str5= {dt};
        SQLiteDatabase db= getWritableDatabase();

        ContentValues cv= new ContentValues();
        cv.put(col1,n);
        cv.put(col2,m);
        cv.put(col3, e);
        cv.put(col4, a);
        cv.put(col5, t);

        db.update(tableName, cv, whr1, str1);
        db.update(tableName, cv, whr2, str2);
        db.update(tableName, cv, whr3, str3);
        db.update(tableName, cv, whr4, str4);
        db.update(tableName, cv, whr5, str5);
    }
    public void delrow( String nam)
    {
        String whr = col1+"=?";
        String [] str= {nam};
        SQLiteDatabase db= getWritableDatabase();
        db.delete(tableName, whr, str);
    }
    public void delall()
    {
        SQLiteDatabase db =getWritableDatabase();
        db.delete(tableName, null, null);
    }
}