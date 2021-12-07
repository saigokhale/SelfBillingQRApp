package com.example.selfbillingqrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button sign_up;
    EditText username;
    EditText password;
    EditText repassword;
    DBHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toast t;
        //t=Toast.makeText(this,"welcome!",Toast.LENGTH_LONG);
        //t.show();

        sign_up = (Button) findViewById(R.id.SignUpFinal);
        username = (EditText) findViewById(R.id.signupusername);
        password = (EditText) findViewById(R.id.enterpassword);
        repassword = (EditText) findViewById(R.id.confirmpassword);

        DB = new DBHandler(this);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_to_home();
            }
        });

    }

    public void go_to_home()
    {
        String user= username.getText().toString();
        String pass= password.getText().toString();
        String repass= repassword.getText().toString();

        if(user.equals("")||pass.equals("")||repass.equals("")) {
            Toast.makeText(this,"Please fill all the fields!",Toast.LENGTH_LONG).show();
        }
        else{
            if(pass.equals(repass)){
                boolean checkUsername= DB.checkUsername(user);
                if(checkUsername==false) {
                    boolean insert = DB.insertData(user, pass);
                    if(insert) {
                        Toast.makeText(SignupActivity.this, "Successfully registered!", Toast.LENGTH_LONG).show();
                        Intent intent_signup= new Intent(getApplicationContext(), HomeActivity2.class);
                        startActivity(intent_signup);
                    }
                }
                else{
                    Toast.makeText(SignupActivity.this, "Username exists!", Toast.LENGTH_LONG).show();
                }
            }
        }


    }
}