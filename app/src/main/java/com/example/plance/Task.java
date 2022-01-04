package com.example.plance;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.plance.databinding.ActivityTaskBinding;

public class Task extends AppCompatActivity {

    String[] list;
//    String[] descList;
    ListView ListViewer;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Task ma;

    private AppBarConfiguration appBarConfiguration;
    private ActivityTaskBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        binding = ActivityTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_task);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTask = new Intent(Task.this, CreateTask.class);
                startActivity(createTask);

//                Toast.makeText(getApplicationContext(), "Adding a new Task", Toast.LENGTH_LONG).show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tasks", null);
        list = new String[cursor.getCount()];
//        descList = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            list[i] = cursor.getString(2).toString();
//            descList[i] = cursor.getString(3).toString();
        }
        ListViewer = (ListView) findViewById(R.id.listView1);
        ListViewer.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list));
//        ListViewer.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_2, descList));
        ListViewer.setSelected(true);

        ListViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = list[arg2];
                final CharSequence[] dialogitem = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Task.this);
                builder.setTitle("Pilih");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            // Lihat
                            case 0:
                                Intent taskDetail = new Intent(getApplicationContext(), TaskDetail.class);
                                taskDetail.putExtra("title", selection);
                                startActivity(taskDetail);
                                break;
                            // Update
                            case 1:
//                                Intent update = new Intent(getApplicationContext(), UpdateBiodataActivity.class);
//                                startActivity(update);
//                                update.putExtra("title", selection);
                                break;
                            // Hapus
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("DELETE FROM tasks WHERE title ='" + selection + "'");
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

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_task);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}