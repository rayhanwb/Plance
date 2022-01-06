package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    String[] list;
    protected Cursor cursor;
    DataHelper dbcenter, dbHelper;
    public static SignIn signIn;
    EditText name, company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        name = (EditText) findViewById(R.id.nameForm_signUp);
        company = (EditText) findViewById(R.id.companyForm);

        Button signIn_btn, signUp_btn;
        signIn_btn = (Button) findViewById(R.id.signIn);
//        signUp_btn = (Button) findViewById(R.id.signUp);

        signIn = this;
        dbcenter = new DataHelper(this);
        dbHelper = new DataHelper(this);

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbcenter.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM users WHERE name='" + name.getText().toString() +
                        "'", null);

//                if (cursor.getString(1).toString() != null) {
                if (cursor.getCount() > 0) {
                    SQLiteDatabase dbNewData = dbHelper.getWritableDatabase();
                    dbNewData.execSQL("UPDATE users SET is_loggedin='1' WHERE name='" + name.getText().toString() +"'");

                    Intent signIn_success = new Intent(SignIn.this, Home.class);
                    cursor.moveToFirst();
                    signIn_success.putExtra("user_id", cursor.getString(0).toString());
                    startActivity(signIn_success);
                    Toast.makeText(getApplicationContext(), "Welcome Back!", Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    if (company.getText().toString() != null) {
                        SQLiteDatabase dbNewData = dbHelper.getWritableDatabase();
                        dbNewData.execSQL("INSERT INTO users(name, comp_name, is_loggedin) VALUES('"
                                + name.getText().toString() + "', '" + company.getText().toString() +
                                "', '1')");

                        cursor = dbNewData.rawQuery("SELECT * FROM users WHERE name='" + name.getText().toString() +
                                "'", null);
                        Intent signUp_success = new Intent(SignIn.this, Home.class);
                        cursor.moveToFirst();
                        signUp_success.putExtra("user_id", cursor.getString(0).toString());
                        startActivity(signUp_success);
                        Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        SQLiteDatabase dbNewData = dbHelper.getWritableDatabase();
                        dbNewData.execSQL("INSERT INTO users(name, is_loggedin) VALUES('"
                                + name.getText().toString() + "', '1')");

                        cursor = dbNewData.rawQuery("SELECT * FROM users WHERE name='" + name.getText().toString() +
                                "'", null);
                        Intent signUp_success = new Intent(SignIn.this, Home.class);
                        cursor.moveToFirst();
                        signUp_success.putExtra("user_id", cursor.getString(0).toString());
                        startActivity(signUp_success);
                        Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }


            }
        });

//        signUp_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goto_signUp = new Intent(SignIn.this, SignUp.class);
//                startActivity(goto_signUp);
//                finish();
//            }
//        });
    }


}