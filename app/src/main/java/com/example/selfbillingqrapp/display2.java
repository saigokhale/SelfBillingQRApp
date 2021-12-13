package com.example.selfbillingqrapp;
import static com.example.selfbillingqrapp.DisplayDetails.cdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class display2 extends AppCompatActivity
 {
     private RecyclerView wishlistRV;
     // Arraylist for storing data
     //CartDB cdb;
     private ArrayList<ModelCartHandler> wishlistArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);

        wishlistArrayList = new ArrayList<>();
        Toast.makeText(getApplicationContext(), LoginActivity.getuname(), Toast.LENGTH_LONG).show();
        Cursor res= cdb.getdata_byuname(LoginActivity.getuname());

        //    Toast.makeText(getApplicationContext(), "Entered here!", Toast.LENGTH_LONG).show();
        while(res.moveToNext()) {
            String name1 = res.getString(1);
            int price1 = res.getInt(3);

            wishlistArrayList.add(new ModelCartHandler(name1, "1", price1));
        }

        wishlistRV = findViewById(R.id.idRVCourse);

        //wishlistArrayList.add(new ModelCartHandler("Parle", "13/03/22", 20));


        // we are initializing our adapter class and passing our arraylist to it.
        CartAdapter courseAdapter = new CartAdapter(this, wishlistArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        wishlistRV.setLayoutManager(linearLayoutManager);
        wishlistRV.setAdapter(courseAdapter);

    }

     public void putValue(String x,String y,int z)
     {

     }

     private void setValue() {
         CartAdapter courseAdapter = new CartAdapter(this, wishlistArrayList);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
         wishlistRV.setLayoutManager(linearLayoutManager);
         wishlistRV.setAdapter(courseAdapter);
     }

     public ArrayList<ModelCartHandler> returnList()
     {
         return wishlistArrayList;
     }
}