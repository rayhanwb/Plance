package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button welcome_btn, skip_btn;
        welcome_btn = (Button) findViewById(R.id.start);
        skip_btn = (Button) findViewById(R.id.back);

        welcome_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start_tour = new Intent(Welcome.this, FirstFeature.class);
                startActivity(start_tour);
                finish();
            }
        });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent skip_tour = new Intent(Welcome.this, SignIn.class);
                startActivity(skip_tour);
                finish();
            }
        });
    }
}