package com.example.plance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    String[] list, desc;
    ListView ListViewer;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Home ma;

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

        ImageView userImage;
        userImage = (ImageView) findViewById(R.id.userImage);

        TextView username, comp_name;
        username = (TextView) findViewById(R.id.username);
        comp_name = (TextView) findViewById(R.id.compName);


        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

        SQLiteDatabase readName = dbcenter.getWritableDatabase();
        cursor = readName.rawQuery("SELECT * FROM users WHERE id='" + getIntent().getStringExtra("user_id") +
                "'", null);
        cursor.moveToFirst();
        username.setText(cursor.getString(1).toString());
        comp_name.setText(cursor.getString(2).toString());

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

                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("UPDATE users SET is_loggedin='0' WHERE id='"
                        + getIntent().getStringExtra("user_id") + "'");

                Intent logout = new Intent(Home.this, SignIn.class);
                startActivity(logout);
                Toast.makeText(getApplicationContext(),
                        "You're Logged Out", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] dialogitem = {"Log Out"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Profile");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                final CharSequence[] comfirmItem = {"Yes", "No"};
                                AlertDialog.Builder logoutConfirm = new AlertDialog.Builder(Home.this);
                                logoutConfirm.setTitle("Are you sure " + getIntent().getStringExtra("user_id") + "?");

                                logoutConfirm.setItems(comfirmItem, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        switch (i) {
                                            case 0:
                                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                                db.execSQL("UPDATE users SET is_loggedin='0' WHERE id='"
                                                        + getIntent().getStringExtra("user_id") + "'");

                                                Intent logout = new Intent(Home.this, SignIn.class);
                                                startActivity(logout);
                                                Toast.makeText(getApplicationContext(),
                                                        "You're Logged Out", Toast.LENGTH_LONG).show();
                                                finish();
                                                break;
                                            case 1:
                                                break;
                                        }
                                    }
                                });
                                logoutConfirm.create().show();
                                break;
                        }
                    }
                });
            }
        });

    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tasks LIMIT 2", null);
        list = new String[cursor.getCount()];
        desc = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            list[i] = cursor.getString(2).toString();
            if (cursor.getString(3) != null) {
                desc[i] = cursor.getString(3).toString();
            } else {
                desc[i] = cursor.getString(2).toString();
            }
        }

        ListViewer = (ListView) findViewById(R.id.listView1);
        ListAdapter listAdapter = new ListAdapter(this, list, desc);
        ListViewer.setAdapter(listAdapter);
//        ListViewer.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
        ListViewer.setSelected(true);

        ListViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = list[arg2];
//                Intent showTask = new Intent(getApplicationContext(), TaskDetail.class);
//                showTask.putExtra("title", selection);
//                startActivity(showTask);
                final CharSequence[] dialogitem = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Pilih");

                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Intent showTask = new Intent(getApplicationContext(), TaskDetail.class);
                        showTask.putExtra("title", selection);
                        startActivity(showTask);

                        switch (item) {
                            // Lihat
                            case 0:
//                                Intent lihat = new Intent(getApplicationContext(), LihatBiodataActivity.class);
//                                lihat.putExtra("nama", selection);
//                                startActivity(lihat);
                                break;
                            // Update
                            case 1:
//                                Intent update = new Intent(getApplicationContext(), UpdateBiodataActivity.class);
//                                update.putExtra("nama", selection);
//                                startActivity(update);
                                break;
                            // Hapus
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("DELETE FROM biodata WHERE nama ='" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListViewer.getAdapter()).notifyDataSetInvalidated();
    }
}