package com.example.davidiriepaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class PantallaCarga extends Activity {

    private final int DURACION_SPLASH = 5000;
    ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pantalla_carga);

        Animation animacion = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);
        imagen = findViewById(R.id.imagen);

        imagen.setAnimation(animacion);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(PantallaCarga.this,MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}