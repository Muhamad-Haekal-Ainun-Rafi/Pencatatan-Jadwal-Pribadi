package com.haekal.scheduling.menu_utama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.haekal.scheduling.R;
import com.haekal.scheduling.jadwal_schedule.JadwalActivity;
import com.haekal.scheduling.jadwal_schedule.Semester.SemesterListActivity;
import com.haekal.scheduling.kegiatan_harian.AdapterKegHari.HariReqAdapterRecyclerView;
import com.haekal.scheduling.kegiatan_harian.Hari.HariListActivity;
import com.haekal.scheduling.login.LoginActivity;
import com.haekal.scheduling.menu_profile.ProfileActivity;
import com.haekal.scheduling.menu_profile.ProfileSetting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuActivity extends AppCompatActivity {
    private TextView HomeName;
    private String Uid;
    TextView Day,txtJam,Jam;
    ImageView BTN_SETTING;

    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        loading = ProgressDialog.show(MenuActivity.this,
                "",
                "Please wait...",
                true,
                false);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Uid = getIntent().getStringExtra("Uid");

        HomeName = findViewById(R.id.home_nama);
        Day = findViewById(R.id.hari);
        txtJam = findViewById(R.id.txtJam);
        Jam = findViewById(R.id.JamKe);
        BTN_SETTING = findViewById(R.id.btn_profil);
        registerForContextMenu(BTN_SETTING);

        DatabaseReference offline = FirebaseDatabase.getInstance().getReference().child("Users");
        offline.keepSynced(true);


        setUcapan();
        jamKuliah();
        menu();


        database.child("Users").child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loading.dismiss();
                String Snama = dataSnapshot.child("nama").getValue(String.class);
                HomeName.setText(Snama.toUpperCase());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_setting,menu);

    }
    @Override
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId())
        {
            case R.id.option_1:
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
                return true;

            case R.id.option_2:
                Intent intent1 = new Intent(MenuActivity.this, ProfileSetting.class);
                intent1.putExtra("Uid", Uid);
                startActivity(intent1);
                return true;

            case R.id.option_3:
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();

                return true;
            default:
                return super.onContextItemSelected(item);



        }

    }

    void menu() {

        BTN_SETTING.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tahan Button Untuk ke Menu Profile", Toast.LENGTH_SHORT).show();

            }
        });


        findViewById(R.id.btn_jadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SemesterListActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_tugas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HariListActivity.class);
                intent.putExtra("Uid", Uid);
                startActivity(intent);
            }
        });
    }



    private void setUcapan(){

        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String s = sdf.format(new Date());
        int jam = Integer.parseInt(s);

        if (jam >= 15 && jam <= 17){
            Day.setText("SORE");
        }else if (jam > 17 && jam < 18){
            Day.setText("PETANG");
        }else if (jam >= 18 ){
            Day.setText("MALAM");
        }else if (jam >= 00 || jam >= 1 || jam >= 01){
            Day.setText("PAGI");
        }else if (jam >= 11){
            Day.setText("SIANG");
        }
        else{
            Day.setText("ERROR");
        }
    }

    private void jamKuliah(){

        //format jam 7.30
        SimpleDateFormat sdf = new SimpleDateFormat("hh.mm");
        String s = sdf.format(new Date());
        Double jam = Double.parseDouble(s);

        if (jam >= 7.30 && jam <= 8.30){
            Jam.setText("1");
        }
        if (jam >= 8.30 && jam <= 9.30){
            Jam.setText("2");
        }
        if (jam >= 9.30 && jam <= 10.30){
            Jam.setText("3");
        }
        if (jam >= 10.30 && jam <= 11.30){
            Jam.setText("4");
        }
        if (jam >= 11.30 && jam <= 12.30){
            Jam.setText("5");
        }
        if (jam >= 12.30 && jam <= 13.30){
            Jam.setText("6");
        }
        if (jam >= 13.30 && jam <= 14.30){
            Jam.setText("7");
        }
        if (jam >= 14.30 && jam <= 15.30){
            Jam.setText("8");
        }
        if (jam >= 15.30 && jam <= 16.30){
            Jam.setText("9");
        }
        if (jam >= 16.30  && jam <= 17.30){
            Jam.setText("10");
        }
        else{
            Jam.setText("");
            txtJam.setText("Tidak ada Kuliah");
        }
    }
    }

