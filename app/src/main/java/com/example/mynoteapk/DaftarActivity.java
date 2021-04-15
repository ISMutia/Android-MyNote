package com.example.mynoteapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DaftarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private ListAdapter adapter_off;
    private DatabaseHandler db;
    private List<Note>ListNote = new ArrayList<Note>();
    private FloatingActionButton p1;
    private SearchView searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Hi!!!");

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.black));

        db = new DatabaseHandler(this);
        adapter_off = new ListAdapter(this, ListNote);
        mListView = (ListView) findViewById(R.id.list_note);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListNote.clear();
        p1= findViewById(R.id.plus1);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentU = new Intent(DaftarActivity.this,TambahActivity.class);
                startActivity(intentU);
            }
        });

        List<Note> contacts = db.ReadNote();
        for (Note cn : contacts){
            Note judulModel = new Note();
            judulModel.setId(cn.getId());
            judulModel.setTgl(cn.getTgl());
            judulModel.setTitle(cn.getTitle());
            judulModel.setDeskripsi(cn.getDeskripsi());
            ListNote.add(judulModel);

            if ((ListNote.isEmpty()))
                Toast.makeText(DaftarActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
            else {

            }
        }

        searchBar = findViewById(R.id.search_bar2);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Note> data;
                if (newText.equals("")) {
                    data = db.ReadNote();
                } else {
                    data = db.SearchNote(newText);
                }

                adapter_off = new ListAdapter(DaftarActivity.this, data);
                mListView.setAdapter(adapter_off);

                return true;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        Object o = mListView.getItemAtPosition(i);
        Note obj_itemDetails = (Note)o;

        String Sid =  obj_itemDetails.getId();
        String Stgl = obj_itemDetails.getTgl();
        String Stitle = obj_itemDetails.getTitle();
        String Sdescription = obj_itemDetails.getDeskripsi();

        Intent goUpdel = new Intent(DaftarActivity.this, UpdelActivity.class);
        goUpdel.putExtra("Id", Sid);
        goUpdel.putExtra("Tanggal", Stgl);
        goUpdel.putExtra("Title", Stitle);
        goUpdel.putExtra("Description", Sdescription);
        startActivity(goUpdel);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ListNote.clear();
        mListView.setAdapter(adapter_off);

        List<Note> contacts = db.ReadNote();
        for (Note cn : contacts){
            Note judulModel = new Note("1", "Note1","Hehe","15--3-2021");
            judulModel.setId(cn.getId());
            judulModel.setTgl(cn.getTgl());
            judulModel.setTitle(cn.getTitle());
            judulModel.setDeskripsi(cn.getDeskripsi());
            ListNote.add(judulModel);

            if ((ListNote.isEmpty()))
                Toast.makeText(DaftarActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
            else {

            }
        }
    }
}