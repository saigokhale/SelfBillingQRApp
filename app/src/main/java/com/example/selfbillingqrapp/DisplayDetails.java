package com.example.selfbillingqrapp;

import static com.example.selfbillingqrapp.HomeActivity2.cdb;
import static com.example.selfbillingqrapp.HomeActivity2.gdb;
import static com.example.selfbillingqrapp.HomeActivity2.wdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayDetails extends AppCompatActivity
{
    int price;
    String name;
    String mfg;
    Button  bcart, bwish;
    ImageButton bhome;
    //EditText code,mfg,exp,price,name;
    TextView tv1,tv2,tv3,tv4;//,tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydetails);

        //wishobj=

        String code_in="1";

//        binsert =(Button) findViewById(R.id.btninsert);
//        bdisplay= (Button) findViewById(R.id.btnshow);
        bcart = (Button) findViewById(R.id.add_cart);
        bwish = (Button) findViewById(R.id.add_wishlist);
        bhome = (ImageButton) findViewById(R.id.homeButtondetails);

//        code = (EditText) findViewById(R.id.etcode);
//        mfg = (EditText) findViewById(R.id.etmfg);
//        exp = (EditText) findViewById(R.id.etexp);
//        price = (EditText) findViewById(R.id.etprice);
//        name = (EditText) findViewById(R.id.etname);

        tv1= (TextView) findViewById(R.id.textView1);
        tv2= (TextView) findViewById(R.id.textView2);
        tv3= (TextView) findViewById(R.id.textView3);
        tv4= (TextView) findViewById(R.id.textView4);




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            code_in= extras.getString("code");
            //The key argument here must match that used in the other activity
        }

        Cursor res= gdb.getbycode(code_in);

        if(res.getCount()==0){
            //Toast to display index of row in grocery database
            //Toast.makeText(getApplicationContext(), "No data in Table", Toast.LENGTH_LONG);
            return;
        }

        StringBuffer sb= new StringBuffer();

        while(res.moveToNext())
        {
            name=res.getString(1);
            tv1.setText(name);

            mfg=res.getString(2);
            tv2.setText("Mfg: "+mfg);

            String exp=res.getString(3);
            tv3.setText("Exp: "+exp);

            price=Integer.valueOf(res.getString(4));
            tv4.setText("₹ "+Integer.toString(price));
            //sb.append("\n");
        }

        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity2.class));
            }
        });

        bcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean x=cdb.insertData(LoginActivity.getuname(),name,1,price);
                //tempList= wishobj.returnList();
//                wishobj.putValue(name, mfg,price);
                //Intent intent_display2= new Intent(getApplicationContext(), display2.class);
                //startActivity(intent_display2);
                if(x)
                    Toast.makeText(DisplayDetails.this, "Added to cart", Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(DisplayDetails.this, "Already in cart", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean x=wdb.insertData(LoginActivity.getuname(),name,1,price);
                if(x)
                    Toast.makeText(DisplayDetails.this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
            }
        });

    }
}