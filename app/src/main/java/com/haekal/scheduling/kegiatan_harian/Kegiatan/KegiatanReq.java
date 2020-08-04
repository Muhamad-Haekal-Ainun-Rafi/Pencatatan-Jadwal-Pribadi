package com.haekal.scheduling.kegiatan_harian.Kegiatan;

import java.io.Serializable;

public class KegiatanReq implements Serializable {

    private String kegiatan;
    private String deskripsi;
    private String jam;
    private String hari;
    private String note;
    private String key;
    private String userId;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) { this.date = date; }

    public String getKegiatan() { return kegiatan; }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public KegiatanReq(){}

    public KegiatanReq(String kegiatan, String deskripsi, String jam, String hari, String note) {
        this.kegiatan = kegiatan;
        this.deskripsi = deskripsi;
        this.jam = jam;
        this.hari = hari;
        this.note = note;
    }
}
