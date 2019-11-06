package com.example.appcastillo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAlta, btnModUser, btnViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlta = findViewById(R.id.botonAltaUser);

        btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent alta = new Intent(MainActivity.this , NewUser.class);
                startActivity(alta);

            }
        });

        btnModUser = findViewById(R.id.botonModUsers);

        btnModUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mod = new Intent(MainActivity.this , ModUsers.class);
                startActivity(mod);

            }
        });

        btnViewUser = findViewById(R.id.botonViewUser);

        btnViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ver = new Intent(MainActivity.this , ViewUsers.class);
                startActivity(ver);

            }
        });
    }
}
