package com.haekal.scheduling.kegiatan_harian.AdapterKegHari;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haekal.scheduling.kegiatan_harian.Kegiatan.KegiatanUpdateActivity;

import com.haekal.scheduling.R;
import com.haekal.scheduling.kegiatan_harian.Kegiatan.KegiatanReq;

import java.util.ArrayList;
import java.util.List;

public class HariReqAdapterRecyclerView extends RecyclerView.Adapter<HariReqAdapterRecyclerView.MyViewHolder> {

    private List<KegiatanReq> LabList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tv_kegiatan,tv_deskripsi,tv_jam,tv_hari,tv_note;

        public MyViewHolder(View view){
            super(view);
            rl_layout = view.findViewById(R.id.lab_layout);
            tv_kegiatan = view.findViewById(R.id.lab_judul);
            tv_deskripsi = view.findViewById(R.id.lab_ruangan);
            tv_jam = view.findViewById(R.id.lab_jam);
            tv_hari = view.findViewById(R.id.lab_hari);
            tv_note = view.findViewById(R.id.lab_note);


        }
    }

    public HariReqAdapterRecyclerView(ArrayList<KegiatanReq> LabList, Activity activity){
        this.mActivity = activity;
        this.LabList = LabList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lab,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final KegiatanReq lab = LabList.get(position);
        holder.tv_kegiatan.setText(lab.getKegiatan());
        holder.tv_deskripsi.setText(lab.getDeskripsi());
        holder.tv_jam.setText(lab.getJam());
        holder.tv_hari.setText(lab.getHari());
        holder.tv_note.setText(lab.getNote());


        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tahan untuk mengedit ", Toast.LENGTH_SHORT).show();
            }
        });

        holder.rl_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent getDetail = new Intent(mActivity, KegiatanUpdateActivity.class);

                getDetail.putExtra("Uid",lab.getUserId());
                getDetail.putExtra("date",lab.getDate());
                getDetail.putExtra("id",lab.getKey());
                getDetail.putExtra("kegiatan",lab.getKegiatan());
                getDetail.putExtra("deskripsi",lab.getDeskripsi());
                getDetail.putExtra("jam",lab.getJam());
                getDetail.putExtra("hari",lab.getHari());
                getDetail.putExtra("note",lab.getNote());

                mActivity.startActivity(getDetail);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return LabList.size();
    }
}
