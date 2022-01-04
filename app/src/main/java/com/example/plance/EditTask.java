package com.example.plance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditTask extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbhelper;
    Button ton1, ton2;
    EditText text1, text2, text3;
    String datePicked;

    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        dbhelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.titleForm);
        text2 = (EditText) findViewById(R.id.descForm);
        text3 = (EditText) findViewById(R.id.dateForm);
        ton1 = (Button) findViewById(R.id.button);
        ton2 = (Button) findViewById(R.id.button2);

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tasks WHERE title ='"+
                getIntent().getStringExtra("title") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(4).toString());
            if (cursor.getString(3) != null) {
                text2.setText(cursor.getString(3).toString());
            }
        }

        text3.setInputType(InputType.TYPE_NULL);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(EditTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        datePicked = i2 + "/" + (i1+1) + "/" + i;
                        text3.setText(datePicked);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.execSQL("UPDATE tasks SET title='"+
                        text1.getText().toString() + "', desc='" +
                        text2.getText().toString() + "', date='" +
                        text3.getText().toString() + "' WHERE id='" +
                        cursor.getString(0).toString() + "'");
                Toast.makeText(getApplicationContext(), "Task Updated", Toast.LENGTH_LONG).show();
                Task.ma.RefreshList();
                finish();
            }
        });

        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}