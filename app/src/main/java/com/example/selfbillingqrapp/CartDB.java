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
import android.widget.Toast;

import androidx.annotation.Nullable;
public class CartDB extends SQLiteOpenHelper
{
    public static final String DBNAME= "Cart3.db";
    int flag;
    public CartDB(@Nullable Context context)
    {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase CartDB) {

        CartDB.execSQL("CREATE TABLE cart3(id int primary key ,username TEXT ,name TEXT, quantity int, price int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase CartDB, int oldVersion, int newVersion) {
        CartDB.execSQL("DROP TABLE IF EXISTS cart3");
    }

    public boolean insertData(String uname, String name, int quantity, int price)
    {
        try {
            SQLiteDatabase CartDB = this.getWritableDatabase();

            if(hasItem(uname,name))
            {
                //CartDB.rawQuery("update cart alter ");
                int new_qty=getQuantity(uname,name)+1;
                ContentValues cv = new ContentValues();
                cv.put("quantity",Integer.toString(new_qty));
//              CartDB.execSQL("update cart3 set quantity="+new_qty+" where username= ? and name=?",new String[]{uname,name});
                long affect= CartDB.update("cart3", cv, "username=? " + "and name=?", new String[] {uname, name});
                return affect==-1?false:true;
            }
            else {
                ContentValues cv = new ContentValues();
                cv.put("id",Math.random());
                cv.put("username", uname);
                cv.put("name", name);
                cv.put("quantity", quantity);
                cv.put("price", price);
                CartDB.insert("cart3", null, cv);
                return true;
            }
        }
        catch(Exception e)
        {
            return false;
        }


    }

    public boolean deletedata(String uname,String name)
    {
        try {
            SQLiteDatabase CartDB = this.getWritableDatabase();

            if(hasItem(uname,name))
            {
                //CartDB.rawQuery("update cart alter ");
                 Cursor cursor=CartDB.rawQuery("delete from cart3 where username=? and name=?",new String[]{uname,name});
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
        Cursor cursor=CartDB.rawQuery("select * from cart3 where username =?",new String[]{uname});
        return cursor;
    }

    public Cursor getdata(String uname)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart3",null);
        return cursor;
    }

    public int getQuantity(String uname, String name)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select quantity from cart3 where username=? and name=?",new String[]{uname,name});
        if(cursor.getCount()>0)
            return cursor.getInt(0);
        else
            return 0;
    }
    public int getPrice(String uname, String name)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart3 where username=? and name=?",new String[]{uname,name});
        if(cursor.getCount()>0)
            return cursor.getInt(4);
        return 0;
    }
    public boolean hasItem(String uname, String name)
    {
        SQLiteDatabase CartDB = this.getWritableDatabase();
        Cursor cursor=CartDB.rawQuery("select * from cart3 where username=? and name=?",new String[]{uname,name});
        if(cursor.getCount()>0)
            return true;
        return false;
    }


    public boolean deletedata(String name) {
        try {
            SQLiteDatabase CartDB = this.getWritableDatabase();
            String uname=LoginActivity.getuname();
            if(hasItem(uname,name))
            {
                //Cursor cursor=CartDB.rawQuery("delete from cart3 where username=? and name=?",new String[]{uname,name});
                CartDB.delete("cart3", "username=?" + "and name=?", new String[]{uname,name});
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

    public void resetdata(){

    }
}
