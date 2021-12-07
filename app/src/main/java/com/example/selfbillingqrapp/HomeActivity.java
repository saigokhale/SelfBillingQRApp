package com.example.selfbillingqrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button QRButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        QRButton = (Button) findViewById(R.id.login);

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
