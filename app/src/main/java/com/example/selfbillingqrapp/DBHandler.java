package com.example.selfbillingqrapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//
public class DBHandler extends SQLiteOpenHelper
{

    public static final String DBNAME= "Login.db";
    public DBHandler(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase LoginDB)
    {
        LoginDB.execSQL("CREATE TABLE users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase LoginDB, int i, int i1)
    {
        LoginDB.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean insertData(String uname, String passw)
    {
        try {
            SQLiteDatabase LoginDB = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("username", uname);
            contentValues.put("password", passw);
            LoginDB.insert("users", null, contentValues);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }


    }

    //check for username in database
    public boolean checkUsername(String username)
    {
        try{
            SQLiteDatabase LoginDB = this.getWritableDatabase();
            Cursor cursor = LoginDB.rawQuery("Select * from users where username= ?",new String[] {username});
            return cursor.getCount()>0?true:false;
        }
        catch(Exception e)
        {
            return false;
        }

    }

    //match password in the database while login
    public boolean matchPassword(String username, String password)
    {
        try {
            SQLiteDatabase LoginDB = this.getWritableDatabase();
            Cursor cursor = LoginDB.rawQuery("Select * from users where username= ? and password= ?", new String[]{username, password});
            //Toast.makeText("entered match ",this,Toast.LENGTH_LONG).show();
            return cursor.getCount() > 0 ? true : false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

}
