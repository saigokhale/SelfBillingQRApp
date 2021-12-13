package com.example.selfbillingqrapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class CartDB extends SQLiteOpenHelper
{
    public static final String DBNAME= "Cart2.db";
    int flag;
    public CartDB(@Nullable Context context)
    {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase CartDB) {

        CartDB.execSQL("CREATE TABLE cart2(username TEXT ,name TEXT primary key, quantity int, price int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase CartDB, int oldVersion, int newVersion) {
        CartDB.execSQL("DROP TABLE IF EXISTS cart2");
    }

    public boolean insertData(String uname, String name, int quantity, int price)
    {
        try {
            SQLiteDatabase CartDB = this.getWritableDatabase();

            if(hasItem(uname,name))
            {
                //CartDB.rawQuery("update cart alter ");
                ContentValues cv = new ContentValues();
                cv.put("quantity", getQuantity(uname,name)+quantity);
                CartDB.update("cart2", cv, uname + "= ?" + name+ "=?", new String[] {uname,name});
                return true;
            }
            else {
                ContentValues cv = new ContentValues();
                cv.put("username", uname);
                cv.put("name", name);
                cv.put("quantity", quantity);
                cv.put("price", price);
                CartDB.insert("cart2", null, cv);
                return true;
            }
        }
        catch(Exception e)
        {
            return false;
        }


    }

    public boolean deletedata(String uname,String name,int quantity)
    {
        try {
            SQLiteDatabase CartDB = this.getWritableDatabase();

            if(hasItem(uname,name))
            {
                //CartDB.rawQuery("update cart alter ");
                if(getQuantity(uname,name)-quantity==0)
                {
                    Cursor cursor=CartDB.rawQuery("delete from cart2 where username=? and name=?",new String[]{uname,name});
                    return true;
                }
                ContentValues cv = new ContentValues();
                cv.put("quantity", getQuantity(uname,name)-quantity);
                CartDB.update("cart2", cv, uname + "= ?" + name+ "=?", new String[] {uname,name});
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }

    }
    public Cursor getdata_byuname(String uname)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart2 where username =?",new String[]{uname});
        return cursor;
    }

    public Cursor getdata(String uname)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart2",null);
        return cursor;
    }

    public int getQuantity(String uname, String name)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart2 where username=? and name=?",new String[]{uname,name});
        if(cursor.getCount()>0)
            return cursor.getInt(2);
        return 0;
    }
    public int getPrice(String uname, String name)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart2 where username=? and name=?",new String[]{uname,name});
        if(cursor.getCount()>0)
            return cursor.getInt(3);
        return 0;
    }
    public boolean hasItem(String uname, String name)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart2 where username=? and name=?",new String[]{uname,name});
        if(cursor.getCount()>0)
            return true;
        return false;
    }

}
