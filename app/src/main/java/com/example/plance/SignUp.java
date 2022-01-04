package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signIn_btn, signUp_btn;
        signUp_btn = (Button) findViewById(R.id.signUp);
        signIn_btn = (Button) findViewById(R.id.signIn);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp_success = new Intent(SignUp.this, SignIn.class);
                startActivity(signUp_success);
                Toast.makeText(getApplicationContext(), "Sign Up Success", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goto_signIn = new Intent(SignUp.this, SignIn.class);
                startActivity(goto_signIn);
                finish();
            }
        });
    }
}