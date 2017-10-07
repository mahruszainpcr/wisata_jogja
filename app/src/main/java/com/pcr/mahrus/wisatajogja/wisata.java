package com.pcr.mahrus.wisatajogja;

/**
 * Created by PC-01-320 on 8/11/2017.
 */

public class wisata {
    private String nama_pariwisata;
    private String alamat_pariwisata;
    private String detail_pariwisata;
    private String gambar_pariwisata;

    public wisata(String nama_pariwisata, String alamat_pariwisata, String detail_pariwisata, String gambar_pariwisata) {
        this.nama_pariwisata = nama_pariwisata;
        this.alamat_pariwisata = alamat_pariwisata;
        this.detail_pariwisata = detail_pariwisata;
        this.gambar_pariwisata = gambar_pariwisata;
    }
    public wisata(){

    }

    public String getNama_pariwisata() {
        return nama_pariwisata;
    }

    public void setNama_pariwisata(String nama_pariwisata) {
        this.nama_pariwisata = nama_pariwisata;
    }

    public String getAlamat_pariwisata() {
        return alamat_pariwisata;
    }

    public void setAlamat_pariwisata(String alamat_pariwisata) {
        this.alamat_pariwisata = alamat_pariwisata;
    }

    public String getGambar_pariwisata() {
        return gambar_pariwisata;
    }

    public void setGambar_pariwisata(String gambar_pariwisata) {
        this.gambar_pariwisata = gambar_pariwisata;
    }

    public String getDetail_pariwisata() {
        return detail_pariwisata;
    }

    public void setDetail_pariwisata(String detail_pariwisata) {
        this.detail_pariwisata = detail_pariwisata;
    }
}

