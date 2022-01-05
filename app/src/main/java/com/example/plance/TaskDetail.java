package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button back_btn;
    TextView text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.date);
        text2 = (TextView) findViewById(R.id.title);
        text3 = (TextView) findViewById(R.id.desc);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tasks WHERE title = '" +
                getIntent().getStringExtra("title") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(4).toString());
            text2.setText(cursor.getString(2).toString());
            if (cursor.getString(3) != null ) {
                text3.setText(cursor.getString(3).toString());
            }
        }

        back_btn = (Button) findViewById(R.id.btn_kembali);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}