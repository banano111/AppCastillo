package com.example.appcastillo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcastillo.modelos.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class NewUser extends AppCompatActivity {

    EditText nombre, apellidoP, apellidoM, deporte;
    Button btnCasa, btnAltaUs;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_users);

        nombre = findViewById(R.id.nombre);
        apellidoP = findViewById(R.id.apellidoP);
        apellidoM = findViewById(R.id.apellidoM);
        deporte = findViewById(R.id.deporteF);

        inicializarFirebase();

        btnCasa = findViewById(R.id.botonCasa);

        btnCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent casa = new Intent(NewUser.this , MainActivity.class);

                startActivity(casa);

            }
        });

        btnAltaUs = findViewById(R.id.botonSaveUser);

        btnAltaUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = nombre.getText().toString();
                String apeP = apellidoP.getText().toString();
                String apeM = apellidoM.getText().toString();
                String dep = deporte.getText().toString();
                
                if (name.equals("") || apeP.equals("") || apeM.equals("") || dep.equals(""))
                {
                    validacion();
                }

                else
                {
                    Usuario user = new Usuario();

                    user.setUid(UUID.randomUUID().toString());
                    user.setNombreUser(name);
                    user.setApellidoPUser(apeP);
                    user.setApellidoMUser(apeM);
                    user.setDeporteUser(dep);

                    databaseReference.child("Usuario").child(user.getUid()).setValue(user);

                    Toast.makeText(getApplicationContext(), "Usuario Guardado, Gracias", Toast.LENGTH_SHORT).show();
                    limpiaDatos();
                }

            }

            private void limpiaDatos() {

                nombre.setText("");
                apellidoP.setText("");
                apellidoM.setText("");
                deporte.setText("");
            }

            private void validacion() {

                String nameVal = nombre.getText().toString();
                String apePVal = apellidoP.getText().toString();
                String apeMVal = apellidoM.getText().toString();
                String depVal = deporte.getText().toString();

                if (nameVal.equals("")){
                    nombre.setError("Ingrese Nombre");
                }
                else if (apePVal.equals("")){
                    apellidoP.setError("Ingrese Apellido Paterno");
                }
                else if (apeMVal.equals("")){
                    apellidoM.setError("Ingrese Apellido Materno");
                }
                else if (depVal.equals("")){
                    deporte.setError("Ingrese Deporte Favorito");
                }
                
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
