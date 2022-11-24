package com.example.app_sueo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Pantalla_Informativa extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_informativa);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        progressBar = findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            //progressBar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Intent imostrarApp=new Intent(Pantalla_Informativa.this, DatosFormulario.class);
                startActivity(imostrarApp);
                finish();
            }
        };
        Timer tiempo=new Timer();
        tiempo.schedule(tarea,5000);
        //regresar a 4000 ticks
    }

}