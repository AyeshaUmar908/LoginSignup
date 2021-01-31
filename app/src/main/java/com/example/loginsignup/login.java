package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {
    private TextView register, forgotpasssword ;
    private EditText etemail, etpassword;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login = (Button) findViewById(R.id.btnlogin);
        login.setOnClickListener(this);

        etemail = (EditText)findViewById(R.id.etemail);
        etpassword = (EditText)findViewById(R.id.etpassword);

        mAuth = FirebaseAuth.getInstance();
        forgotpasssword = (TextView)findViewById(R.id.tvforgotpassord);
        forgotpasssword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnlogin:
                userlogin();
                break;


            case R.id.tvforgotpassord:
                startActivity(new Intent(this, forgotpassword.class ));
                break;
        }

    }

    private void userlogin() {
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if(email.isEmpty()){
            etemail.setError("Email is required");
        }
        else if(password.isEmpty()){
            etpassword.setError("Enter Password");
        }
        if(email.equals("")){
            etemail.setError("Enter Email");
        }
        else if(password.length()< 6){
            etpassword.setError("Min Password length is 6 character");
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        //redirect to user profile
                        startActivity(new Intent(login.this, profile.class));

                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(login.this, "check your email to verify your account !", Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    Toast.makeText(login.this, "Failed to login!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

