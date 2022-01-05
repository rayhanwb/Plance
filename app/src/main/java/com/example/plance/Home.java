package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button seeTasks, seeFinances;
        seeTasks = (Button) findViewById(R.id.allTasks);
        seeFinances = (Button) findViewById(R.id.manageFinance);

        seeTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoTasks = new Intent(Home.this, Task.class);
                startActivity(gotoTasks);
            }
        });

        seeFinances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent gotoFinances = new Intent(Home.this, Finances.class);
//                startActivity(gotoFinances);
            }
        });
    }
}