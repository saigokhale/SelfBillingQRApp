package com.example.selfbillingqrapp;

import static com.example.selfbillingqrapp.HomeActivity2.cdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayDetails extends AppCompatActivity
{
    int price;
    String name;
    String mfg;
    Button /*binsert, bdisplay,*/ bcart, bwish;
    //EditText code,mfg,exp,price,name;
    TextView tv1,tv2,tv3,tv4;//,tv2;
    GroceryDB gdb ;

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

//        code = (EditText) findViewById(R.id.etcode);
//        mfg = (EditText) findViewById(R.id.etmfg);
//        exp = (EditText) findViewById(R.id.etexp);
//        price = (EditText) findViewById(R.id.etprice);
//        name = (EditText) findViewById(R.id.etname);

        tv1= (TextView) findViewById(R.id.textView1);
        tv2= (TextView) findViewById(R.id.textView2);
        tv3= (TextView) findViewById(R.id.textView3);
        tv4= (TextView) findViewById(R.id.textView4);

        gdb = new GroceryDB(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            code_in= extras.getString("code");
            //The key argument here must match that used in the other activity
        }

        Cursor res= gdb.getbycode(code_in);

        if(res.getCount()==0){
            Toast.makeText(getApplicationContext(), "No data in Table", Toast.LENGTH_LONG);
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
            tv4.setText("Rs: "+Integer.toString(price));
            //sb.append("\n");
        }





        bwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean x=cdb.insertData(LoginActivity.getuname(),name,1,price);
                //tempList= wishobj.returnList();
//                wishobj.putValue(name, mfg,price);
                //Intent intent_display2= new Intent(getApplicationContext(), display2.class);
                //startActivity(intent_display2);
                Toast.makeText(DisplayDetails.this, x+"", Toast.LENGTH_SHORT).show();
            }
        });
//        binsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int code_s= Integer.parseInt(code.getText().toString());
////                String mfg_s= mfg.getText().toString();
////                String exp_s= exp.getText().toString();
////                int price_s= Integer.parseInt(mfg.getText().toString());
////                String name_s= name.getText().toString();
//
////                Toast.makeText(DisplayDetails.this, ""+name_s+code+mfg_s, Toast.LENGTH_LONG);
//                //boolean checkinsert= gdb.insertData();
//                //Toast.makeText(DisplayDetails.this, ""+checkinsert, Toast.LENGTH_LONG);
//                //if(checkinsert)
//                //    Toast.makeText(DisplayDetails.this, "Insertion successful!", Toast.LENGTH_LONG);
//            }
//        });



//        bdisplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int code_s= Integer.parseInt(code.getText().toString());
////                String mfg_s= mfg.getText().toString();
////                String exp_s= exp.getText().toString();
////                int price_s= Integer.parseInt(mfg.getText().toString());
////                String name_s= name.getText().toString();
//
//                Cursor res= gdb.getdata();
//
//                if(res.getCount()==0){
//                    Toast.makeText(getApplicationContext(), "No data in Table", Toast.LENGTH_LONG);
//                    return;
//                }
//                StringBuffer sb= new StringBuffer();
//
//                while(res.moveToNext())
//                {
//                    sb.append("Code: "+res.getString(0)+"\t");
//                    sb.append("Name   : "+res.getString(1)+"\t");
//                    sb.append("Mfg   : "+res.getString(2)+"\t");
//                    sb.append("Exp   : "+res.getString(3)+"\t");
//                    sb.append("Price   : "+res.getString(4)+"\t");
//                    sb.append("\n");
//                }
//
//                tv2.setText(sb.toString());
//            }
//        });

    }
}