package com.example.app_sueo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.example.app_sueo.IniciarFormulario;
import com.example.app_sueo.R;

import java.util.Timer;
import java.util.TimerTask;

public class PantallaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Intent imostrarApp=new Intent(PantallaInicio.this, IniciarFormulario.class);
                startActivity(imostrarApp);
                finish();
            }
        };
        Timer tiempo=new Timer();
        tiempo.schedule(tarea,5000);
    }

}