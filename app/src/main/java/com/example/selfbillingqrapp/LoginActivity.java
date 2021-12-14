package com.example.selfbillingqrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button log_in;
    Button sign_up;
    EditText username;
    EditText password;
    static String send_uname;   //make the current username available in all classes
    DBHandler DB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_in = (Button) findViewById(R.id.login);

        sign_up = (Button) findViewById(R.id.SignUp);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        DB = new DBHandler(this);


        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                trylogin();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openSignup();
            }
        });
    }

    //activity opens signup
    public void openSignup()
    {
        Intent intent_signup= new Intent(this, SignupActivity.class);
        startActivity(intent_signup);
    }

    //checks login credentials
    public void trylogin()
    {
        String user= username.getText().toString();
        String pass= password.getText().toString();

        //function to set static username user
        setuname(user);

        if(user.equals("")||pass.equals(""))
            Toast.makeText(LoginActivity.this,"please enter all fields",Toast.LENGTH_SHORT).show();
        else {
            Boolean checkuserpass = DB.matchPassword(user, pass);
            if(checkuserpass)
            {
                Toast.makeText(LoginActivity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                Intent intent_login= new Intent(getApplicationContext(), HomeActivity2.class) ;
                startActivity(intent_login);
            }
            else
            {
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public static void setuname(String username)
    {
        send_uname=username;
    }

    //function to retrieve username in any class
    public static String getuname()
    {
        return send_uname;
    }


}