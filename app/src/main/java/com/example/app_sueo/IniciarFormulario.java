package com.example.app_sueo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IniciarFormulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_formulario);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    public void iniciarFormulario(View view){
        Intent iIniciarFormulario=new Intent(IniciarFormulario.this, Pantalla_Informativa.class);
        startActivity(iIniciarFormulario);
        finish();
    }
}