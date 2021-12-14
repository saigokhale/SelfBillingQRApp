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
public class GroceryDB extends SQLiteOpenHelper
{

        public static final String DBNAME= "Grocery.db";
        public GroceryDB(@Nullable Context context) {
            super(context, DBNAME, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase grocery)
        {
            grocery.execSQL("CREATE TABLE items(code text primary key, name TEXT, mfg TEXT, exp TEXT, price text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase grocery, int i, int i1)
        {
            grocery.execSQL("DROP TABLE IF EXISTS items");


        }

        //public boolean insertData(int srno, String name, String mdate, String edate, int price)
        public boolean insertData(String a, String b, String c , String d, String e)
        {
            try {
                SQLiteDatabase GroceryDB = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("code", "6");
                contentValues.put("name", "Maggi");
                contentValues.put("mfg", "20-10-2021");
                contentValues.put("exp", "10-03-2022");
                contentValues.put("price", "12");
                if(1==GroceryDB.insert("items", null, contentValues))
                    return true;
                return false;
            }
            catch(Exception x)
            {
                return false;
            }
        }

        public Cursor getbycode(String srno)
        {
            try{
                SQLiteDatabase GroceryDB = this.getWritableDatabase();
                Cursor cursor = GroceryDB.rawQuery("Select * from items where code= ?",new String[] {srno});
                return cursor;
            }
            catch(Exception e)
            {
                return null;
            }

            //Toast.makeText(this,"Entered checkUsername",Toast.LENGTH_LONG).show();


        }
    public Cursor getdata()
    {
        SQLiteDatabase GroceryDB = this.getWritableDatabase();

        Cursor cursor=GroceryDB.rawQuery("select * from items",null);
        return cursor;
    }

//        public boolean matchPassword(String username, String password)
//        {
//            try {
//                SQLiteDatabase LoginDB = this.getWritableDatabase();
//                Cursor cursor = LoginDB.rawQuery("Select * from users where username= ? and password= ?", new String[]{username, password});
//                //Toast.makeText("entered match ",this,Toast.LENGTH_LONG).show();
//                return cursor.getCount() > 0 ? true : false;
//            }
//            catch(Exception e)
//            {
//                return false;
//            }
//        }

    }


