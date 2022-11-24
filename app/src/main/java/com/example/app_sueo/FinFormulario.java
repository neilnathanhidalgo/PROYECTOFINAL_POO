package com.example.app_sueo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

public class FinFormulario extends AppCompatActivity {
    private Button btnVolverIncicio;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_formulario);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btnVolverIncicio = findViewById(R.id.btnVolverInicio);
        btnSalir = findViewById(R.id.btnSalir);

        System.out.println("Mención especial a Perla Albines por ayudar en el desarrollo del diseño de esta aplicación");

    }
    public void btnVolverInicio(View view){
        Intent iVolverInicio=new Intent(FinFormulario.this,IniciarFormulario.class);
        startActivity(iVolverInicio);
        finish();

    }
    public void btnSalir(View view){
        finishAffinity();

    }

}