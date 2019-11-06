package com.example.appcastillo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class ViewUsers extends AppCompatActivity {

    ListView usuariosBD;
    Button btnCasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        usuariosBD = findViewById(R.id.vistaUsers);

        btnCasa = findViewById(R.id.botonCasa);

        btnCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent casa = new Intent(ViewUsers.this, MainActivity.class);

                startActivity(casa);

            }
        });


    }
}