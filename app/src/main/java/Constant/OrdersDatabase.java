package Constant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by indiaweb on 10/28/2016.
 */
public class OrdersDatabase extends SQLiteOpenHelper {
    public static final String tableName = "Products";
    static String DATABASE_NAME = "productdatas";
    public static final String KEY_ID = "id";
    public static String col1 = "Name";

    public OrdersDatabase(Context _cntxt) {

        super(_cntxt, "DataRecords", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + tableName + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + col1 + " TEXT)";
        db.execSQL(sql);
        System.out.println("Table is CreaTED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public long insertRecord(String userName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col1, userName);

        long cnt = db.insert(tableName, null, cv);
        return cnt;
    }

    public ArrayList<String> getAllName() {
        ArrayList<String> temp = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();

        String nam[] = {col1};

        Cursor cursor = db.query(tableName, nam, null, null, null, null, null);
        while (cursor.moveToNext())//row wise row
        {
            temp.add(cursor.getString(0));

        }
        return temp;
    }
    public void update(String nam, String n, String mob, String m, String email, String e, String add, String a) {
        String whr1 = col1 + "=?";

        String[] str1 = {nam};

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(col1, n);

        db.update(tableName, cv, whr1, str1);

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