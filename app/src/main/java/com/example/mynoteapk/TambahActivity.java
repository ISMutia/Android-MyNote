package com.example.mynoteapk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TambahActivity extends AppCompatActivity {
    private TextView tgltxt;
    private EditText Etitle, Edescription;
    Button btnInput;
    private String tgl;
    private String Stitle, Sdescripton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        db = new DatabaseHandler(this);

        tgltxt = findViewById(R.id.tgl);
        Etitle = findViewById(R.id.add_title);
        Edescription = findViewById(R.id.add_description);

        btnInput = findViewById(R.id.btn_submit);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        tgl = currentDateandTime;
        tgltxt.setText(currentDateandTime);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tgl = String.valueOf(tgltxt.getText());
                Stitle = String.valueOf(Etitle.getText());
                Sdescripton = String.valueOf(Edescription.getText());
                if (tgl.equals("")){
                    tgltxt.requestFocus();
                    Toast.makeText(TambahActivity.this, "Tanggal Hari Ini", Toast.LENGTH_SHORT).show();
                }
                else if (Stitle.equals("")){
                    Etitle.requestFocus();
                    Toast.makeText(TambahActivity.this, "Masukkan title", Toast.LENGTH_SHORT).show();
                }
                else if (Sdescripton.equals("")){
                    Edescription.requestFocus();
                    Toast.makeText(TambahActivity.this, "Masukkan description", Toast.LENGTH_SHORT).show();
                }
                else {
                    tgltxt.setText("");
                    Etitle.setText("");
                    Edescription.setText("");
                    Toast.makeText(TambahActivity.this, "Data Telah Ditambah", Toast.LENGTH_SHORT).show();
                    db.CreateNote(new Note(null, Stitle, Sdescripton, tgl));
                    Intent intent = new Intent(TambahActivity.this,DaftarActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}