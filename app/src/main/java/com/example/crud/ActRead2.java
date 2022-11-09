package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActRead2 extends AppCompatActivity {

    Button btnBack;
    TextView dataruang, datakapasitas;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_read2);


        Bundle extras = getIntent().getExtras();
        String namaRuang = extras.getString("namaRuang");
        String kapasitas= extras.getString("kapasitas");


        dataruang= findViewById(R.id.vnamaRuang);
        datakapasitas = findViewById(R.id.vkapasitas);
        btnBack = findViewById(R.id.btnBack);

        dataruang.setText(namaRuang);
        datakapasitas.setText(kapasitas);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActRead2.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}