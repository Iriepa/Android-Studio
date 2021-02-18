package com.example.davidiriepaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.davidiriepaapp.Model.Persona;
import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class consultas extends AppCompatActivity {
    Button botonvolver;
    ListView lvIP;

    private List<Persona> listaPersona = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultas);

        botonvolver = findViewById(R.id.Modificar);
        lvIP =findViewById(R.id.lv_LisPers);

        inicializarFirebase();
        listarDatos();


    }

    public void volver(View view) {
        Intent botonvolver = new Intent(consultas.this, MainActivity.class);
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

                    arrayAdapterPersona = new ArrayAdapter<Persona>(consultas.this , R.layout.row, listaPersona);
                    lvIP.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}
