package com.example.mynoteapk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_note";

    private static final String tb_note = "tb_note";

    private static final String tb_note_id = "id";
    private static final String tb_note_tgl = "tgl";
    private static final String tb_note_title = "title";
    private static final String tb_note_description = "description";

    private static final String CREATE_TABLE_MYNOTE = "CREATE TABLE " + tb_note + " ("
            + tb_note_id + " INTEGER PRIMARY KEY, "
            + tb_note_tgl + " TEXT, "
            + tb_note_title + " TEXT, "
            + tb_note_description + " TEXT" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MYNOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateNote(Note nNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_note_id, nNotif.getId());
        values.put(tb_note_tgl, nNotif.getTgl());
        values.put(tb_note_title, nNotif.getTitle());
        values.put(tb_note_description, nNotif.getDeskripsi());
        db.insert(tb_note, null, values);
        db.close();
    }

    public List<Note> ReadNote() {
        List<Note> judulModelList = new ArrayList<>();
        String selecQuery = " SELECT * FROM " + tb_note;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selecQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note nKontak = new Note();
                nKontak.setId(cursor.getString(0));
                nKontak.setTgl(cursor.getString(1));
                nKontak.setTitle(cursor.getString(2));
                nKontak.setDeskripsi(cursor.getString(3));

                judulModelList.add(nKontak);
            } while (cursor.moveToNext());
        }
        db.close();
        return judulModelList;
    }

    public List<Note> SearchNote(String title) {
        List<Note> judulModelList = new ArrayList<>();
        String selecQuery = " SELECT * FROM " + tb_note + " WHERE " + tb_note_title + " LIKE '%" + title + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selecQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Note nKontak = new Note();
                nKontak.setId(cursor.getString(0));
                nKontak.setTgl(cursor.getString(1));
                nKontak.setTitle(cursor.getString(2));
                nKontak.setDeskripsi(cursor.getString(3));

                judulModelList.add(nKontak);
            } while (cursor.moveToNext());
        }
        db.close();
        return judulModelList;
    }

    public int UpadteNote(Note nNotif) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tb_note_tgl, nNotif.getTgl());
        values.put(tb_note_title, nNotif.getTitle());
        values.put(tb_note_description, nNotif.getDeskripsi());

        return db.update(tb_note, values, tb_note_id + " = ? ",
                new String[]{String.valueOf(nNotif.getId())});

    }

    public void DeleteNote(Note nNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_note, tb_note_id + " = ?",
                new String[]{String.valueOf(nNotif.getId())});
        db.close();
    }
}
