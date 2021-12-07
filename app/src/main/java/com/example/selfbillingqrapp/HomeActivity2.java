package com.example.selfbillingqrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity2 extends AppCompatActivity {
    ImageButton QRButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        QRButton = (ImageButton) findViewById(R.id.qrbtn);

        QRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQrActivity();
            }
        });
    }
    public void openQrActivity()
    {
        Intent intent_QR= new Intent(this, QrActivity.class);
        startActivity(intent_QR);
    }
}