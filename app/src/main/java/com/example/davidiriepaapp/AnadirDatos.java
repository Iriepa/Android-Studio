package com.example.davidiriepaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidiriepaapp.Model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class AnadirDatos extends AppCompatActivity {

    Button botonvolver,botonaceptar;
    EditText nomP, appP, correoP;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_datos);

        nomP = findViewById(R.id.nombre);
        appP = findViewById(R.id.Apellido);
        correoP = findViewById(R.id.Email);

        inicializarFirebase();

        botonvolver = findViewById(R.id.Volver);
        botonaceptar = findViewById(R.id.Aceptar);

        botonvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonanadir = new Intent(AnadirDatos.this, ModificarDatos.class);
                startActivity(botonanadir);
            }
        });


        botonaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nomP.getText().toString();
                String apellido = appP.getText().toString();
                String correo = correoP.getText().toString();
                
                if (nombre.equals("")|apellido.equals("")|correo.equals("")){
                    validacion();
                }else{
                    Persona p = new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellidos(apellido);
                    p.setCorreo(correo);
                    databaseReference.child("Clientes").child(p.getUid()).setValue(p);
                    Toast.makeText(AnadirDatos.this,"Cliente Añadido",Toast.LENGTH_SHORT).show();
                    limpiarcajas();
                }
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void limpiarcajas() {
        nomP.setText("");
        appP.setText("");
        correoP.setText("");
    }

    private void validacion() {
        String nombre = nomP.getText().toString();
        String apellido = appP.getText().toString();
        String correo = correoP.getText().toString();
        if (nombre.equals("")){
            nomP.setError("Requerido");
        }
        if (apellido.equals("")){
            appP.setError("Requerido");
        }
        if (correo.equals("")){
            correoP.setError("Requerido");
        }

    }
}
