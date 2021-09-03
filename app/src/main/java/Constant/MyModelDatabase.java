package Constant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by indiaweb on 7/29/2017.
 */
public class MyModelDatabase extends SQLiteOpenHelper
{
    public static final String tableName = "CartAdd";
    static String DATABASE_NAME="cart";
    public static final String KEY_ID="id";
    public static String col1 = "categoryid";
    public static String col2 = "productid";
    public static String col3 = "type";
    public static String col4 = "model";
    public static String col5 = "make";
    public static String col6 = "variation";


    public MyModelDatabase(Context _cntxt)
    {

        super(_cntxt,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql="CREATE TABLE "+tableName+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+col1+" TEXT, "+col2+" TEXT, "+col3+" TEXT, "+col4+" TEXT, "+col5+" TEXT, "+col6+" TEXT)";
        db.execSQL(sql);
        System.out.println("Table is CreaTED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {

    }

    public long insertRecord(String Name, String Price,String variation,  String Quantity, String productId, String variationId)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col1, Name);
        cv.put(col2, Price);
        cv.put(col3, variation);
        cv.put(col4, Quantity);
        cv.put(col5, productId);
        cv.put(col6, variationId);


        long cnt = 	db.insert(tableName, null, cv);
        return cnt;
    }

    public ArrayList<String> getAllName()
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
    public ArrayList<String> getAllPrice()
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
    public ArrayList<String> getAllQuantity()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String email []={col4};

        Cursor cursor =db.query(tableName, email, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }
    public ArrayList<String> getAllProId()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String add []={col5};

        Cursor cursor =db.query(tableName, add, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }

    public ArrayList<String> getAllVaration()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String add []={col3};

        Cursor cursor =db.query(tableName, add, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }

    public ArrayList<String> getAllVarId()
    {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db= getReadableDatabase();

        String add []={col6};

        Cursor cursor =db.query(tableName, add, null, null, null, null, null);
        while(cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
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
        String whr = col5+"=?";
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
