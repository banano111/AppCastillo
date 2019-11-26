package com.example.appcastillo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.appcastillo.modelos.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ModUsers extends AppCompatActivity {

    ListView usuariosBD;
    EditText nombre, apellidoP,apellidoM,deporteF;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Usuario usuarioSelected;

    private List<Usuario> listPerson = new ArrayList<>();
    ArrayAdapter<Usuario> arrayAdapterPersona;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mod_users);

            nombre = findViewById(R.id.nombre);
            apellidoP = findViewById(R.id.apellidoP);
            apellidoM = findViewById(R.id.apellidoM);
            deporteF = findViewById(R.id.deporteF);

            usuariosBD = findViewById(R.id.vistaUsers);

            inicializarFirebase();
            listarDatos();

            usuariosBD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    usuarioSelected = (Usuario) parent.getItemAtPosition(position);
                    nombre.setText(usuarioSelected.getNombreUser());
                    apellidoP.setText(usuarioSelected.getApellidoPUser());
                    apellidoM.setText(usuarioSelected.getApellidoMUser());
                    deporteF.setText(usuarioSelected.getDeporteUser());
                }
            });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarDatos() {

        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Usuario userView = objSnaptshot.getValue(Usuario.class);
                    listPerson.add(userView);

                    arrayAdapterPersona = new ArrayAdapter<>(ModUsers.this, R.layout.cambio_list_view, listPerson);
                    usuariosBD.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String name = nombre.getText().toString();
        String apeP = apellidoP.getText().toString();
        String apeM = apellidoM.getText().toString();
        String dep = deporteF.getText().toString();


        switch (item.getItemId()){

            case R.id.icon_home:{
                Intent casa = new Intent(ModUsers.this , MainActivity.class);
                startActivity(casa);
            }

            case R.id.icon_save:{
                if (name.equals("") || apeP.equals("") || apeM.equals("") || dep.equals("")) {
                    validacion();
                } else {
                    Usuario user = new Usuario();

                    user.setUid(usuarioSelected.getUid());
                    user.setNombreUser(nombre.getText().toString().trim());
                    user.setApellidoPUser(apellidoP.getText().toString().trim());
                    user.setApellidoMUser(apellidoM.getText().toString().trim());
                    user.setDeporteUser(deporteF.getText().toString().trim());

                    databaseReference.child("Usuario").child(user.getUid()).setValue(user);

                    Toast.makeText(getApplicationContext(), "Usuario Actualizado, Gracias", Toast.LENGTH_SHORT).show();
                    limpiaDatos();
                }

            }
            break;
            case R.id.icon_delete:{
                Usuario user = new Usuario();
                user.setUid(usuarioSelected.getUid());
                databaseReference.child("Usuario").child(user.getUid()).removeValue();
                Toast.makeText(this,"Eliminado", Toast.LENGTH_LONG).show();
                limpiaDatos();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiaDatos() {
        nombre.setText("");
        apellidoP.setText("");
        apellidoM.setText("");
        deporteF.setText("");
    }

    private void validacion() {
        String nameVal = nombre.getText().toString();
        String apePVal = apellidoP.getText().toString();
        String apeMVal = apellidoM.getText().toString();
        String depVal = deporteF.getText().toString();

        if (nameVal.equals("")) {
            nombre.setError("Ingrese Nombre");
        } else if (apePVal.equals("")) {
            apellidoP.setError("Ingrese Apellido Paterno");
        } else if (apeMVal.equals("")) {
            apellidoM.setError("Ingrese Apellido Materno");
        } else if (depVal.equals("")) {
            deporteF.setError("Ingrese Deporte Favorito");
        }
    }
}
