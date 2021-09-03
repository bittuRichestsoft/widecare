package Constant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class MyDataBases extends SQLiteOpenHelper
{
	public static final String tableName = "CartAdd";
	static String DATABASE_NAME="cart";
	public static final String KEY_ID="id";
	public static String col1 = "Name";
	public static String col2 = "Price";
	public static String col3 = "variation";
	public static String col4 = "Quantity";
	public static String col5 = "productId";
	public static String col6 = "variationId";

	public static String col7 = "Brand";
	public static String col8 = "Model";
	public static String col9 = "Imei";
	public static String col10 = "Type";
	public static String col11 = "Serial";

	public static String col12 = "Date";
	public static String col13 = "kycDoc_num";

	public static String col14 = "_rel";
	public static String col15 = "_dob";
	public static String col16 = "_sex";
	public static String col17 = "_pan";
	public static String col18 = "_color";
	public static String col19 = "Chasis";
	public static String col20 = "Regno";
	public static String col21 = "Value";
	public static String col22= "SaleDate";
	public static String col23 = "nom_name";
	public static String col24 = "nom_dob";
	public static String col25 = "kyc_num";
	public static String col26 = "extra1";
	public static String col27 = "extra2";
	public static String col28 = "extra3";
	public MyDataBases(Context _cntxt)
	{

		super(_cntxt,DATABASE_NAME , null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql="CREATE TABLE "+tableName+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+col1+" TEXT, "+col2+" TEXT, "+col3+" TEXT, "+col4+" TEXT, "+col5+" TEXT, "+col6+" TEXT, "+col7+" TEXT, "+col8+" TEXT, "+col9+" TEXT, "+col10+" TEXT, "+col11+" TEXT, "+col12+" TEXT, "+col13+" TEXT, "+col14+" TEXT, "+col15+" TEXT, "+col16+" TEXT, "+col17+" TEXT, "+col18+" TEXT, "+col19+" TEXT, "+col20+" TEXT, "+col21+" TEXT, "+col22+" TEXT, "+col23+" TEXT, "+col24+" TEXT, "+col25+" TEXT, "+col26+" TEXT, "+col27+" TEXT, "+col28+" TEXT)";
		db.execSQL(sql);
		System.out.println("Table is CreaTED");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{

	}

	public long insertRecord(String Name, String Price,String variation,  String Quantity, String productId, String variationId,String b,String m,String im,String t,String s,String dt,String Doc_num,String rel,String dob,String sex,String pan,String clr,
	String Chasis,String Regno,String Value,String SaleDate,String nom_name,String nom_dob,
							 String kyc_num,String extra1,String extra2,String extra3)
	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(col1, Name);
		cv.put(col2, Price);
		cv.put(col3, variation);
		cv.put(col4, Quantity);
		cv.put(col5, productId);
		cv.put(col6, variationId);
		cv.put(col7, b);
		cv.put(col8, m);
		cv.put(col9, im);
		cv.put(col10, t);
		cv.put(col11, s);
		cv.put(col12, dt);

		cv.put(col13, Doc_num);
		cv.put(col14, rel);
		cv.put(col15, dob);
		cv.put(col16, sex);
		cv.put(col17, pan);
		cv.put(col18, clr);

		cv.put(col19, Chasis);
		cv.put(col20, Regno);
		cv.put(col21, Value);
		cv.put(col22, SaleDate);

		cv.put(col23, nom_name);
		cv.put(col24, nom_dob);
		cv.put(col25, kyc_num);
		cv.put(col26, extra1);
		cv.put(col27, extra2);
		cv.put(col28, extra3);

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
	public ArrayList<String> getAllPId()
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

	public ArrayList<String> getAllBrand()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col7};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllModel()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col8};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllImei()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col9};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllType()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col10};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllSerial()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col11};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllDate()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col12};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getKYCTYPE()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col13};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllRel()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col14};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllDob()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col15};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllInvoice_Num()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col16};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllKycDocType()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col17};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}
	public ArrayList<String> getAllColor()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col18};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllChasis()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col19};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}


	public ArrayList<String> getAllRegno()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col20};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllValue()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col21};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllSaleDate()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col22};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllnom_name()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col23};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllnom_dob()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col24};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllkyc_num()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col25};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllextra1()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col26};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllextra2()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col27};

		Cursor cursor =db.query(tableName, add, null, null, null, null, null);
		while(cursor.moveToNext())//row wise row
		{
			temp.add(cursor.getString(0));

		}
		return temp;
	}

	public ArrayList<String> getAllextra3()
	{
		ArrayList<String> temp = new ArrayList<String>();

		SQLiteDatabase db= getReadableDatabase();

		String add []={col28};

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