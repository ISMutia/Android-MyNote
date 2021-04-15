package com.example.mynoteapk;

public class Note {
    private String id;
    private String title;
    private String tgl;
    private String deskripsi;

    public Note( String id,String title, String deskripsi,String tgl) {
        this.setId(id);
        this.setTitle(title);
        this.setTgl(tgl);
        this.setDeskripsi(deskripsi);
    }

    public Note(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
