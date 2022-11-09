package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActRead extends AppCompatActivity {
    protected Cursor cursor;
    Database database;

    TextView namaRuang, kapasitas, tnamaRuang, tkapasitas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_read);
        database = new Database(this);
        namaRuang = findViewById(R.id.namaRuang);
        kapasitas = findViewById(R.id.kapasitas);


        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM ruang WHERE namaRuang = '" +
                getIntent().getStringExtra("namaRuang")+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            namaRuang.setText(cursor.getString(0).toString());
            kapasitas.setText(cursor.getString(1).toString());
        }



    }
}