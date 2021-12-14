package com.example.selfbillingqrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity2 extends AppCompatActivity {
    ImageButton QRButton;
    ImageButton WishButton;
    ImageButton CartButton;
    int PERM_CODE = 11;
    String[] permissions = {
            Manifest.permission.CAMERA
    };
    public static CartDB cdb;
    public static WishDB wdb;
    public static GroceryDB gdb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        QRButton = (ImageButton) findViewById(R.id.qrbtn);
        WishButton=(ImageButton) findViewById(R.id.wishbtn);
        CartButton=(ImageButton) findViewById(R.id.cartbtn);
        cdb= new CartDB(this);
        wdb=new WishDB(this);
        gdb = new GroceryDB(this);

        CartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), display2.class));
            }
        });

        WishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), displaywish.class));
            }
        });



        QRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), QrActivity.class));
            }
        });
    }
    public void openQrActivity()
    {
        Intent intent_QR= new Intent(this, QrActivity.class);
        startActivity(intent_QR);
    }

    private boolean checkpermissions(){
        List<String> listofpermisssions = new ArrayList<>();
        for (String perm: permissions){
            if (ContextCompat.checkSelfPermission(getApplicationContext(), perm) != PackageManager.PERMISSION_GRANTED){
                listofpermisssions.add(perm);
            }
        }
        if (!listofpermisssions.isEmpty()){
            ActivityCompat.requestPermissions(this, listofpermisssions.toArray(new String[listofpermisssions.size()]), PERM_CODE);
            return false;
        }
        return true;
    }

}



//    Button btnScan;

//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnScan = (Button) findViewById(R.id.button);
//        btnScan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), ScannerActivity.class));
//            }
//        });
//
//    }

