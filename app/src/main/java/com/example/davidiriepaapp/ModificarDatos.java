package com.example.davidiriepaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModificarDatos extends AppCompatActivity {

    Button botonanadir,botonvolver,botonmodificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificardatos);

        botonanadir = findViewById(R.id.consulta);
        botonvolver = findViewById(R.id.Volver);
        botonmodificar=findViewById(R.id.Modificar);


        botonanadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonanadir = new Intent(ModificarDatos.this, AnadirDatos.class);
                startActivity(botonanadir);
            }
        });


    }


    public void volver(View view) {
        Intent botonvolver = new Intent(ModificarDatos.this, MainActivity.class);
        startActivity(botonvolver);

    }

    public void Modificar(View view) {
        Intent botonmodificar = new Intent(ModificarDatos.this, cambiardatos.class);
        startActivity(botonmodificar);
    }

    public void borrar(View view) {
        Intent botonborrar = new Intent(ModificarDatos.this, BorrarDatos.class);
        startActivity(botonborrar);

    }
}
