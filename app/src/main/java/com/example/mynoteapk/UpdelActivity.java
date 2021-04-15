package com.example.mynoteapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdelActivity extends AppCompatActivity {
    private TextView tgltxt1;
    private DatabaseHandler db;
    private String Sid, Stgl, Stitle, Sdescription;
    private EditText Etitle,Edescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updel);

        db = new DatabaseHandler(this);

        Intent i = this.getIntent();
        Sid = i.getStringExtra("Id");
        Stgl = i.getStringExtra("Tanggal");
        Stitle = i.getStringExtra("Title");
        Sdescription = i.getStringExtra("Description");

        Etitle = (EditText) findViewById(R.id.edit_title);
        Edescription = (EditText) findViewById(R.id.edit_description);
        tgltxt1 = (TextView)findViewById(R.id.edit_tgl);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());
        Stgl = currentDateandTime;
        tgltxt1.setText(currentDateandTime);

        Etitle.setText(Stitle);
        Edescription.setText(Sdescription);

        Button btnUpdate = (Button)findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stgl = String.valueOf(tgltxt1.getText());
                Stitle = String.valueOf(Etitle.getText());
                Sdescription = String.valueOf(Edescription.getText());
                if (Stgl.equals("")){
                    tgltxt1.requestFocus();
                    Toast.makeText(UpdelActivity.this, "Update Tanggal", Toast.LENGTH_SHORT).show();

                } else if (Stitle.equals("")){
                    Etitle.requestFocus();
                    Toast.makeText(UpdelActivity.this, "Update Title", Toast.LENGTH_SHORT).show();

                }else if (Sdescription.equals("")){
                    Edescription.requestFocus();
                    Toast.makeText(UpdelActivity.this, "Update Description", Toast.LENGTH_SHORT).show();
                }else {
                    db.UpadteNote(new Note(Sid, Stitle, Sdescription, Stgl));
                    Toast.makeText(UpdelActivity.this, "Data di Update", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        Button btnDelete = (Button) findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.DeleteNote(new Note(Sid, Stgl,Stitle,Sdescription));
                Toast.makeText(UpdelActivity.this, "Data dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}