package Constant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class MyDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "imgdb";
    public static final String TABLE_NAME = "tbl_img";
    public static final String KEY_ID = "ID";
  //  public static final String KEY_   PID = "PID";
    public static final String KEY_USERID = "KEY_USERID";
    public static final String KEY_IMAGE1 = "KEY_IMAGE1";
    public static final String KEY_IMAGE2 = "KEY_IMAGE2";
    public static final String KEY_IMAGE3 = "KEY_IMAGE3";
    public static final String KEY_IMAGE4 = "KEY_IMAGE4";
    public static final int DATABASE_VERSION = 1;
   public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, img BLOB NOT NULL, img1 BLOB NOT NULL, img2 BLOB NOT NULL, img3 BLOB NOT NULL, description TEXT NULL)";
    public static final String DELETE_TABLE="DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDataBase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db)  {
     //   String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("

        //        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERID + " TEXT,"+ KEY_IMAGE1 + " BLOB," + KEY_IMAGE2 + " BLOB,"+ KEY_IMAGE3 + " BLOB," + KEY_IMAGE4 + " BLOB" + ")";
        db.execSQL(CREATE_TABLE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL(DELETE_TABLE);
        //Create tables again
        onCreate(db);
    }

    public void insertBitmap(Bitmap bm,Bitmap bmf,Bitmap bms,Bitmap bmt,String pid)  {

        // Convert the image into byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        ByteArrayOutputStream out3 = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.PNG, 100, out);
        bmf.compress(Bitmap.CompressFormat.PNG, 100, out1);
        bms.compress(Bitmap.CompressFormat.PNG, 100, out2);
        bmt.compress(Bitmap.CompressFormat.PNG, 100, out3);

        byte[] buffer=out.toByteArray();
        byte[] buffer1=out1.toByteArray();
        byte[] buffer2=out2.toByteArray();
        byte[] buffer3=out3.toByteArray();
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put("img", buffer);
            values.put("img1", buffer1);
            values.put("img2", buffer2);
            values.put("img3", buffer3);
            values.put("description", pid);
         //   values.put(KEY_USERID, userid);
            // Insert Row
            long i = db.insert(TABLE_NAME, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
    }

    public Bitmap getBitmap(String id){
        Bitmap bitmap = null;
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+ TABLE_NAME+" WHERE description = " + id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Convert blob data to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex("img"));
                    // Convert the byte array to Bitmap
                    bitmap=BitmapFactory.decodeByteArray(blob, 0, blob.length);

                }

            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
        return bitmap;

    }
    public Bitmap getBitmap1(String id){
        Bitmap bitmap = null;
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+ TABLE_NAME+" WHERE description = " + id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Convert blob data to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex("img1"));
                    // Convert the byte array to Bitmap
                    bitmap=BitmapFactory.decodeByteArray(blob, 0, blob.length);

                }

            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
        return bitmap;

    }

    public Bitmap getBitmap2(String id){
        Bitmap bitmap = null;
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+ TABLE_NAME+" WHERE description = " + id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Convert blob data to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex("img2"));
                    // Convert the byte array to Bitmap
                    bitmap=BitmapFactory.decodeByteArray(blob, 0, blob.length);

                }

            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
        return bitmap;

    }

    public Bitmap getBitmap3(String id){
        Bitmap bitmap = null;
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();

        try
        {
            String selectQuery = "SELECT * FROM "+ TABLE_NAME+" WHERE description = " + id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    // Convert blob data to byte array
                    byte[] blob = cursor.getBlob(cursor.getColumnIndex("img3"));
                    // Convert the byte array to Bitmap
                    bitmap=BitmapFactory.decodeByteArray(blob, 0, blob.length);

                }

            }
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }
        return bitmap;

    }

    public void delrow( String nam)
    {
        String whr = "description=?";
        String [] str= {nam};
        SQLiteDatabase db= getWritableDatabase();
        db.delete(TABLE_NAME, whr, str);
    }

    public void delall()
	{
		SQLiteDatabase db =getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
	}
}
