package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActUpdate extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button btnTambah;
    EditText namaRuang, kapasitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_update);
        database = new Database(this);
        namaRuang = findViewById(R.id.namaRuang);
        kapasitas = findViewById(R.id.kapasitas);
        btnTambah = findViewById(R.id.btnTambah);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor =  db.rawQuery("SELECT* FROM ruang WHERE namaRuang = ' " +
                getIntent().getStringExtra("namaRuang")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            namaRuang.setText(cursor.getString(0).toString());
            kapasitas.setText(cursor.getString(1));
        }
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.btnTambah) {
                    String inputNama = namaRuang.getText().toString().trim();
                    String inputKapasitas = kapasitas.getText().toString().trim();

                    boolean isEmptyFields = false;

                    if (inputNama.isEmpty()) {
                        isEmptyFields = true;
                        namaRuang.setError("Nama Ruang Harus diisi!");
                    }
                    if (inputKapasitas.isEmpty()) {
                        isEmptyFields = true;
                        kapasitas.setError("Kapasitas Ruangan Harus diisi!");
                    }
                    if (!isEmptyFields) {
                        SQLiteDatabase db = database.getWritableDatabase();
                        db.execSQL("update ruang set namaRuang = '" +
                                namaRuang.getText().toString() + "', kapasitas= '" +
                                kapasitas.getText().toString() + "' where namaRuang = '" +
                                getIntent().getStringExtra("namaRuang") + "'");
                        Toast.makeText(ActUpdate.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        MainActivity.ma.RefreshList();
                        finish();
                    }
                }
            }
        });
    }
}