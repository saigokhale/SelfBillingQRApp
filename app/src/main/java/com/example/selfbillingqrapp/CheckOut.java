package com.example.selfbillingqrapp;

import static com.example.selfbillingqrapp.HomeActivity2.cdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckOut extends AppCompatActivity {

    Button gohome;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);



        cdb.cleardata(LoginActivity.getuname());
        gohome=findViewById(R.id.button_home_last);

        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity2.class));
            }
        });


    }
}