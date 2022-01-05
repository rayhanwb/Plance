package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button signIn_btn, signUp_btn;
        signIn_btn = (Button) findViewById(R.id.signIn);
        signUp_btn = (Button) findViewById(R.id.signUp);

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn_success = new Intent(SignIn.this, Home.class);
                startActivity(signIn_success);
                finish();
            }
        });

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goto_signUp = new Intent(SignIn.this, SignUp.class);
                startActivity(goto_signUp);
                finish();
            }
        });
    }
}