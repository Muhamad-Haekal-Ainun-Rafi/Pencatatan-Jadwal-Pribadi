package com.haekal.scheduling.jadwal_schedule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.haekal.scheduling.R;
import com.haekal.scheduling.jadwal_schedule.Semester.SemesterListActivity;
import com.haekal.scheduling.menu_profile.ProfileActivity;
import com.haekal.scheduling.menu_utama.MenuActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JadwalActivity extends AppCompatActivity {
    private String Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Uid = getIntent().getStringExtra("Uid");


        menu();

    }



    void menu() {
        findViewById(R.id.btn_kuliah).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JadwalActivity.this, SemesterListActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });

    }
}
