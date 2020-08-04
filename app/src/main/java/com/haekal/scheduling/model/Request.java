package com.haekal.scheduling.model;

import java.io.Serializable;

public class Request implements Serializable {

    private String nama;
    private String nim;
    private String kelas;
    private String email;
    private String foto;
    private String key;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



    public Request(){

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Request(String nama, String nim, String kelas, String email, String foto) {
        this.nama = nama;
        this.nim = nim;
        this.kelas = kelas;
        this.email = email;
        this.foto = foto;
    }



    @Override
    public String toString(){
        return ""+nama+"\n"+
                ""+nim+"\n"+
                ""+kelas+"\n"+
                ""+email;
    }


}
