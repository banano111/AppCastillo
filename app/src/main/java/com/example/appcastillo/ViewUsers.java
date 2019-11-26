package com.example.appcastillo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ViewUsers extends AppCompatActivity {

    ListView usuariosBD;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Usuario> listPerson = new ArrayList<>();
    ArrayAdapter<Usuario> arrayAdapterPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        usuariosBD = findViewById(R.id.vistaUsers);

        inicializarFirebase();
        listarDatos();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.icon_home:{
                Intent casa = new Intent(ViewUsers.this , MainActivity.class);
                startActivity(casa);
                Toast.makeText(getApplicationContext(),"Saliendo a Home", Toast.LENGTH_SHORT).show();
                break;
            }

            default:break;
        }
        return true;
    }

    private void listarDatos() {

        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Usuario userView = objSnaptshot.getValue(Usuario.class);
                    listPerson.add(userView);

                    arrayAdapterPersona = new ArrayAdapter<>(ViewUsers.this, R.layout.cambio_list_view, listPerson);
                    usuariosBD.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}
