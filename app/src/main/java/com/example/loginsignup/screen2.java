package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public  class screen2 extends AppCompatActivity {
    private Button btn;
    private Button btn1;
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);




        btn=findViewById(R.id.btnlogin1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginEmail();
            }
        });
        btn1=findViewById(R.id.btnlogin2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOtp();
            }
        });
        btn2=findViewById(R.id.btnlogin0);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignupEmail();
            }
        });



    }

    private void openLoginEmail() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
    private void openSignupEmail() {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    private void openOtp() {
        Intent intent = new Intent(this, otp.class);
        startActivity(intent);
    }





}