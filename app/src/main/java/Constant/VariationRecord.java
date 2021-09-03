package Constant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class VariationRecord extends SQLiteOpenHelper
{
    public static final String tableName = "Variation";
    static String DATABASE_NAME="data";
    public static final String KEY_ID="id";
    public static String col1 = "productId";
    public static String col2 = "variation_id";
    public static String col3 = "display_price";
    public static String col4 = "attribute";



    public VariationRecord(Context _cntxt)
    {

        super(_cntxt, "RecordData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql="CREATE TABLE "+tableName+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+col1+" TEXT, "+col2+" TEXT, "+col3+" TEXT, "+col4+" TEXT)";
        db.execSQL(sql);
        System.out.println("Table is CreaTED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {

    }

    public long insertRecord(String userName, String phn,String email,String add)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col1, userName);
        cv.put(col2, phn);
        cv.put(col3, email);
        cv.put(col4, add);

        long cnt = 	db.insert(tableName, null, cv);
        return cnt;
    }

    public ArrayList<String> getAllUserName()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String nam []={col1};

        Cursor cursor =db.query(tableName, nam, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }
    public ArrayList<String> getAllUserVar()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String mob []={col2};

        Cursor cursor =db.query(tableName, mob, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }
    public ArrayList<String> getAllUserPrice()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String email []={col3};

        Cursor cursor =db.query(tableName, email, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(2));

        }
        return temp;
    }



    public ArrayList<String> getAllUserAdd()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String add []={col4};

        Cursor cursor =db.query(tableName, add, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }

    public String getById(String bankId)
    {

        Cursor cursor=null;
        String name = null;
        SQLiteDatabase db= getReadableDatabase();
        cursor =  db.rawQuery("select * from " + tableName + " where " + col4 + "='" + bankId + "'" , null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                name = cursor.getString(cursor.getColumnIndex(col3));

            }
            cursor.close();
        }
        return name;

    }
    public String getByIdd(String bankId)
    {

        Cursor cursor=null;
        String name = null;
        SQLiteDatabase db= getReadableDatabase();
        cursor =  db.rawQuery("select * from " + tableName + " where " + col4 + "='" + bankId + "'" , null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                name = cursor.getString(cursor.getColumnIndex(col2));

            }
            cursor.close();
        }
        return name;

    }


    public String getByIds(String bankId)
    {

        Cursor cursor=null;
        String name = null;
        SQLiteDatabase db= getReadableDatabase();
        cursor =  db.rawQuery("select * from " + tableName + " where " + col3 + "='" + bankId + "'" , null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                name = cursor.getString(cursor.getColumnIndex(col4));

            }
            cursor.close();
        }
        return name;

    }


    public void update(String nam, String n,String mob,String m,String email,String e,String add, String a)
    {
        String whr1 = col1+"=?";
        String whr2 = col2+"=?";
        String whr3 = col3+"=?";
        String whr4 = col4+"=?";

        String [] str1= {nam};
        String [] str2= {mob};
        String [] str3= {email};
        String [] str4= {add};

        SQLiteDatabase db= getWritableDatabase();

        ContentValues cv= new ContentValues();
        cv.put(col1,n);
        cv.put(col2,m);
        cv.put(col3,e);
        cv.put(col4,a);

        db.update(tableName, cv, whr1, str1);
        db.update(tableName, cv, whr2, str2);
        db.update(tableName, cv, whr3, str3);
        db.update(tableName, cv, whr4, str4);

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