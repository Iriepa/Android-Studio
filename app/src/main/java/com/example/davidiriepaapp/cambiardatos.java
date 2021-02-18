package com.example.davidiriepaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.davidiriepaapp.Model.Persona;
import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class cambiardatos extends AppCompatActivity {
    Button botonvolver,botonaceptar;
    ListView lvIP;
    EditText nomP, appP, correoP;

    private List<Persona> listaPersona = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambiardatos);

        nomP = findViewById(R.id.nombre);
        appP = findViewById(R.id.Apellido);
        correoP = findViewById(R.id.Email);

        botonvolver = findViewById(R.id.Volver);
        botonaceptar=findViewById(R.id.Aceptar);
        lvIP =findViewById(R.id.lv_LisPers);

        inicializarFirebase();
        listarDatos();

        lvIP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSeleccionada = (Persona) parent.getItemAtPosition(position);
                nomP.setText(personaSeleccionada.getNombre());
                appP.setText(personaSeleccionada.getApellidos());
                correoP.setText(personaSeleccionada.getCorreo());
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
                    p.setUid(personaSeleccionada.getUid());
                    p.setNombre(nomP.getText().toString().trim());
                    p.setApellidos(appP.getText().toString().trim());
                    p.setCorreo(correoP.getText().toString().trim());
                    databaseReference.child("Clientes").child(p.getUid()).setValue(p);
                    Toast.makeText(cambiardatos.this,"Cliente Actualizado",Toast.LENGTH_SHORT).show();
                    limpiarcajas();
                }
            }
        });


    }

    public void volver(View view) {
        Intent botonvolver = new Intent(cambiardatos.this, ModificarDatos.class);
        startActivity(botonvolver);
    }

    public void inicializarFirebase (){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    public void listarDatos(){
        databaseReference.child("Clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaPersona.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Persona p = objSnapshot.getValue(Persona.class);
                    listaPersona.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(cambiardatos.this , R.layout.row, listaPersona);
                    lvIP.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
