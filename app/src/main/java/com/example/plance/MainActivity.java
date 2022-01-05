package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ma = this;
        dbHelper = new DataHelper(this);
//        RefreshList();

        SQLiteDatabase inputBaru = dbHelper.getWritableDatabase();
//        inputBaru.execSQL("CREATE TABLE users(id integer PRIMARY KEY AUTOINCREMENT, name text NOT NULL, comp_name text, is_loggedin integer)");
//        inputBaru.execSQL("INSERT INTO users(id, name, comp_name, is_loggedin) VALUES('1', 'Mahasiswa', 'Toko Maju Jaya', '1')");
//        inputBaru.execSQL("INSERT INTO tasks(user_id, title, date) VALUES('1', 'Menguji', '22/2/2002')");

//        inputBaru.execSQL("UPDATE users SET is_loggedin='0' WHERE is_loggedin='1'");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                cursor = db.rawQuery("SELECT * FROM users WHERE is_loggedin='1'", null);
//
                if (cursor.getCount() > 0) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    cursor.moveToFirst();
                    intent.putExtra("user_id", cursor.getString(0).toString());
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Welcome Back!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, Welcome.class);
                    startActivity(intent);
                    finish();
                }


//                Intent intent = new Intent(MainActivity.this, Welcome.class);
//                startActivity(intent);
//                finish();
            }
        }, 3000);
    }

//    public void RefreshList() {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM users", null);
//    }
}