package com.example.davidiriepaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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


public class BorrarDatos extends AppCompatActivity {
    Button botonvolver, botonborrar;
    ListView lvIP;
    TextView nomP, appP, correoP,pulsa;

    private List<Persona> listaPersona = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrardatos);

        nomP = findViewById(R.id.nombre);
        appP = findViewById(R.id.Apellido);
        correoP = findViewById(R.id.Email);
        pulsa=findViewById(R.id.pulsa);
        botonvolver = findViewById(R.id.Volver);
        botonborrar =findViewById(R.id.borrar);
        lvIP =findViewById(R.id.lv_LisPers);

        inicializarFirebase();
        listarDatos();

        lvIP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pulsa.setVisibility(View.INVISIBLE);
                personaSeleccionada = (Persona) parent.getItemAtPosition(position);
                nomP.setText(personaSeleccionada.getNombre());
                appP.setText(personaSeleccionada.getApellidos());
                correoP.setText(personaSeleccionada.getCorreo());
            }
        });

        botonborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(BorrarDatos.this);
                alerta.setMessage("¿Desera Borrar este Cliente?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String nombre = nomP.getText().toString();
                                String apellido = appP.getText().toString();
                                String correo = correoP.getText().toString();

                                if (nombre.equals("")|apellido.equals("")|correo.equals("")){
                                    validacion();
                                }else{
                                    Persona p = new Persona();
                                    p.setUid(personaSeleccionada.getUid());
                                    databaseReference.child("Clientes").child(p.getUid()).removeValue();
                                    Toast.makeText(BorrarDatos.this,"Cliente Borrado",Toast.LENGTH_SHORT).show();
                                    limpiarcajas();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Borrar");
                titulo.show();

            }
        });


    }

    public void volver(View view) {
        Intent botonvolver = new Intent(BorrarDatos.this, ModificarDatos.class);
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

                    arrayAdapterPersona = new ArrayAdapter<Persona>(BorrarDatos.this , R.layout.row, listaPersona);
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
        pulsa.setVisibility(View.VISIBLE);
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
