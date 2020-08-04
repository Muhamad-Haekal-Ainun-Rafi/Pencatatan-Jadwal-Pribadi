package com.haekal.scheduling.kegiatan_harian.Kegiatan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haekal.scheduling.kegiatan_harian.AdapterKegHari.HariReqAdapterRecyclerView;
import com.haekal.scheduling.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KegiatanListActivity extends AppCompatActivity {

    private DatabaseReference database;

    private ArrayList<KegiatanReq> kegiatanReq;
    private HariReqAdapterRecyclerView hariReqAdapterRecyclerView;
    private String Uid,Date;
    private RecyclerView jadwal_list;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_list);

        database = FirebaseDatabase.getInstance().getReference().child("kegiatan_harian");
        Uid = getIntent().getStringExtra("Uid");
        Date = getIntent().getStringExtra("date");



        DatabaseReference offline = database;
        offline.keepSynced(true);

        jadwal_list = findViewById(R.id.jadwal_lab_list);

        RecyclerView.LayoutManager mLayoutManager  = new LinearLayoutManager(getApplicationContext());
        jadwal_list.setLayoutManager(mLayoutManager);
        jadwal_list.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(KegiatanListActivity.this,
                null,
                "Please wait...",
                true,
                false);

        database.child(Uid).child("Hari").child(Date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kegiatanReq = new ArrayList<>();

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    KegiatanReq kegiatan =noteDataSnapshot.getValue(KegiatanReq.class);
                    kegiatan.setDate(Date);
                    kegiatan.setKey(noteDataSnapshot.getKey());
                    kegiatan.setUserId(Uid);
                    kegiatanReq.add(kegiatan);
                }

                hariReqAdapterRecyclerView = new HariReqAdapterRecyclerView(kegiatanReq, KegiatanListActivity.this);
                jadwal_list.setAdapter(hariReqAdapterRecyclerView);
                loading.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                loading.dismiss();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jadwal Laboratorium");

        findViewById(R.id.fab_add_lab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KegiatanListActivity.this, KegiatanUpdateActivity.class)
                        .putExtra("Uid",Uid)
                        .putExtra("date",Date)
                        .putExtra("id","")
                        .putExtra("kegiatan","")
                        .putExtra("jam","")
                        .putExtra("hari",Date)
                        .putExtra("note",""));
            }
        });
    }


}
