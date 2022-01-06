package com.example.plance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "plance.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE biodata(no integer PRIMARY KEY, nama text NULL, tgl text NULL, jk text NULL, alamat text NULL)";
        String sql;
//        sql = "DROP TABLE user";
//        Log.d("Data", "onCreate"+sql);
//        db.execSQL(sql);


        sql = "CREATE TABLE tasks(id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL," +
                " title text NOT NULL, desc text, date text NOT NULL, isdone integer DEFAULT 0 NOT NULL)";
        Log.d("Data", "onCreate"+sql);
        db.execSQL(sql);

        sql = "CREATE TABLE finances(id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL, mainbal integer NOT NULL," +
                " name_subbal1 text, subbal1 integer, name_subbal2 text, subbal2 integer," +
                " name_subbal3 text, subbal3 integer, name_subbal4 text, subbal4 integer," +
                " name_subbal5 text, subbal5 integer)";
        Log.d("Data", "onCreate"+sql);
        db.execSQL(sql);

//        sql = "INSERT INTO tasks(user_id, title, date) VALUES('1', 'Percobaan', datetime('now'))";
//        Log.d("Data", "onCreate"+sql);
//        db.execSQL(sql);

//        sql = "INSERT INTO user(id, name, comp_name, is_loggedin) VALUES('1', 'Mahasiswa', 'Toko Maju Jaya', '1')";
//        Log.d("Data", "onCreate"+sql);
//        db.execSQL(sql);

        sql = "CREATE TABLE users(id integer PRIMARY KEY AUTOINCREMENT, name text NOT NULL, comp_name" +
                " text, is_loggedin integer)";
        Log.d("Data", "onCreate"+sql);
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }
}
