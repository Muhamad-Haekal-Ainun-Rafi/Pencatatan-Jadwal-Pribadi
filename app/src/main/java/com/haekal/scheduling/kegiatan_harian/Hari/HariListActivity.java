package com.haekal.scheduling.kegiatan_harian.Hari;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.haekal.scheduling.kegiatan_harian.Kegiatan.KegiatanListActivity;
import com.haekal.scheduling.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HariListActivity extends AppCompatActivity {

    private DatabaseReference database;
    private String Uid;


    ListView HarilistView;
    String namaHari [] ={"Senin","Selasa","Rabu","Kamis","Jum'at","Sabtu","Lainnya"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hari_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pilih Hari");

        database = FirebaseDatabase.getInstance().getReference();
        Uid = getIntent().getStringExtra("Uid");

        HarilistView = findViewById(R.id.hari_listview);

        MyAdapter adapter= new MyAdapter(this, namaHari);
        HarilistView.setAdapter(adapter);


        HarilistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(getApplicationContext(), KegiatanListActivity.class);
                    intent.putExtra("Uid", Uid);
                    intent.putExtra("date",namaHari[0]);
                    startActivity(intent);
                }
                if (position == 1){
                    Intent intent = new Intent(getApplicationContext(), KegiatanListActivity.class);
                    intent.putExtra("Uid", Uid);
                    intent.putExtra("date",namaHari[1]);
                    startActivity(intent);
                }
                if (position == 2){
                    Intent intent = new Intent(getApplicationContext(), KegiatanListActivity.class);
                    intent.putExtra("Uid", Uid);
                    intent.putExtra("date",namaHari[2]);
                    startActivity(intent);
                }
                if (position == 3){
                    Intent intent = new Intent(getApplicationContext(), KegiatanListActivity.class);
                    intent.putExtra("Uid", Uid);
                    intent.putExtra("date",namaHari[3]);
                    startActivity(intent);
                }
                if (position == 4){
                    Intent intent = new Intent(getApplicationContext(), KegiatanListActivity.class);
                    intent.putExtra("Uid", Uid);
                    intent.putExtra("date",namaHari[4]);
                    startActivity(intent);
                }
                if (position == 5){
                    Intent intent = new Intent(getApplicationContext(), KegiatanListActivity.class);
                    intent.putExtra("Uid", Uid);
                    intent.putExtra("date",namaHari[5]);
                    startActivity(intent);
                }
                if (position == 6){
                    Intent intent = new Intent(getApplicationContext(), KegiatanListActivity.class);
                    intent.putExtra("Uid", Uid);
                    intent.putExtra("date",namaHari[6]);
                    startActivity(intent);
                }
            }
        });



    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String[] namaHari;

        MyAdapter (Context c, String[] Namahari){
            super(c,R.layout.item_hari, R.id.txtHari, Namahari);
            this.context = c;
            this.namaHari = Namahari;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.item_hari, parent, false);

            TextView NamaHari = row.findViewById(R.id.txtHari);
            NamaHari.setText(namaHari[position]);

            return row;
        }
    }
}
