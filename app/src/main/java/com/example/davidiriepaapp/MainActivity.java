package com.example.davidiriepaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonmodificar,botonconsultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonmodificar = findViewById(R.id.Modificar);
        botonconsultas = findViewById(R.id.consulta);

        botonmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonmodificar = new Intent(MainActivity.this, ModificarDatos.class);
                startActivity(botonmodificar);
            }
        });
    }

    public void consultas(View view) {
        Intent botonconsulta = new Intent(MainActivity.this, consultas.class);
        startActivity(botonconsulta);

    }
}
