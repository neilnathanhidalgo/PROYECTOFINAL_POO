package com.example.app_sueo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Entidades.Alternativa;
import Entidades.Persona;
import Entidades.Pregunta;

public class FormularioProceso extends AppCompatActivity {

    //URL Obtener preguntas Cuestionario id=1
    String urlPreguntasID1 ="http://trabajopoo.kirudental.net/api/apiPregunta/listarPorIdCuestionario/1";
    String urlAlternativas="http://trabajopoo.kirudental.net/api/apiAlternativa/listarTodos";
    String urlPersona = "http://trabajopoo.kirudental.net/api/apiPersona/listarTodos";
    String urlInsertarPersona = "http://trabajopoo.kirudental.net/api/apiPersona/insertar";
    String urlInsertarRespuesta = "http://trabajopoo.kirudental.net/api/apiRespuesta/insertar";

    private Button btnAtras,btnHome;
    private Button btnSiguiente;
    private RadioButton rbO1, rbO2, rbO3, rbO4,rbO5;

    private TextView txtIndicaciones, txtPreguntas, txtPregunta, txtSigla;

    private ImageView ivFacultad;

    private int p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,id;
    private int posicionPregunta = 0;
    private int auxMostrar = 0;

    private String respuestaU = "";
    private String r0 = "",r1 = "",r2 = "",r3 = "",r4 = "",r5 = "",r6 = "",r7 = "",r8 = "",r9 = "",r10 = "";

    private RadioGroup rbgAlternativas;

    private ArrayList<Pregunta> listaPreguntas;
    private  ArrayList<Alternativa> listaAlternativas;
    private ArrayList<Persona> listaPersonas;
    private String[] arrayRespuestas = {"","","","","","","","","",""};

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_proceso);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btnAtras = findViewById(R.id.btnAtras);
        btnHome = findViewById(R.id.btnHome);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        txtIndicaciones = findViewById(R.id.txtIndicaciones);
        txtPreguntas = findViewById(R.id.txtPreguntas);
        txtPregunta = findViewById(R.id.txtPregunta);
        txtSigla = findViewById(R.id.txtSigla);

        ivFacultad = findViewById(R.id.ivFacultad);

        rbgAlternativas=findViewById(R.id.rbgAlternativas);

        rbO1 = findViewById(R.id.rbO1);
        rbO2 = findViewById(R.id.rbO2);
        rbO3 = findViewById(R.id.rbO3);
        rbO4 = findViewById(R.id.rbO4);
        rbO5 = findViewById(R.id.rbO5);

        cargarPreguntas(this);
        cargarAlternativas(this);
        cargarPersonas(this);


        //Retroceder
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicionPregunta>0){
                    posicionPregunta--;
                    mostarPregunta(posicionPregunta);
                    marcarCheck(posicionPregunta);
                } else{
                    alertHome();
                }
            }
        });
        //color sigla
        iconYSigla();

    }
    //Limpiar alternativas
    public void quitarCheck(){
        if (auxMostrar<posicionPregunta+1){
            rbgAlternativas.clearCheck();
        }
    }
    //Muestra de cada pregunta con sus alternativas
    public void mostarPregunta(int posicionPregunta){
        switch (posicionPregunta){
            case (0):
                txtIndicaciones.setText("Elija la alternativa que mejor describa sus habitos de sueño");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(0).getPregunta());
                rbO1.setText(listaAlternativas.get(0).getAlternativas());
                rbO2.setText(listaAlternativas.get(1).getAlternativas());
                rbO3.setText(listaAlternativas.get(2).getAlternativas());
                rbO4.setText(listaAlternativas.get(3).getAlternativas());
                rbO5.setText(listaAlternativas.get(4).getAlternativas());
                break;
            case (1):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(1).getPregunta());
                rbO1.setText(listaAlternativas.get(5).getAlternativas());
                rbO2.setText(listaAlternativas.get(6).getAlternativas());
                rbO3.setText(listaAlternativas.get(7).getAlternativas());
                rbO4.setText(listaAlternativas.get(8).getAlternativas());
                rbO5.setText(listaAlternativas.get(9).getAlternativas());
                break;
            case (2):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(2).getPregunta());
                rbO1.setText(listaAlternativas.get(10).getAlternativas());
                rbO2.setText(listaAlternativas.get(11).getAlternativas());
                rbO3.setText(listaAlternativas.get(12).getAlternativas());
                rbO4.setText(listaAlternativas.get(13).getAlternativas());
                rbO5.setText(listaAlternativas.get(14).getAlternativas());
                break;
            case (3):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(3).getPregunta());
                rbO1.setText(listaAlternativas.get(15).getAlternativas());
                rbO2.setText(listaAlternativas.get(16).getAlternativas());
                rbO3.setText(listaAlternativas.get(17).getAlternativas());
                rbO4.setText(listaAlternativas.get(18).getAlternativas());
                rbO5.setText(listaAlternativas.get(19).getAlternativas());
                break;
            case (4):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(4).getPregunta());
                rbO1.setText(listaAlternativas.get(20).getAlternativas());
                rbO2.setText(listaAlternativas.get(21).getAlternativas());
                rbO3.setText(listaAlternativas.get(22).getAlternativas());
                rbO4.setText(listaAlternativas.get(23).getAlternativas());
                rbO5.setText(listaAlternativas.get(24).getAlternativas());
                break;
            case (5):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(5).getPregunta());
                rbO1.setText(listaAlternativas.get(25).getAlternativas());
                rbO2.setText(listaAlternativas.get(26).getAlternativas());
                rbO3.setText(listaAlternativas.get(27).getAlternativas());
                rbO4.setText(listaAlternativas.get(28).getAlternativas());
                rbO5.setText(listaAlternativas.get(29).getAlternativas());
                break;
            case (6):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(6).getPregunta());
                rbO1.setText(listaAlternativas.get(30).getAlternativas());
                rbO2.setText(listaAlternativas.get(31).getAlternativas());
                rbO3.setText(listaAlternativas.get(32).getAlternativas());
                rbO4.setText(listaAlternativas.get(33).getAlternativas());
                rbO5.setText(listaAlternativas.get(34).getAlternativas());
                break;
            case (7):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(7).getPregunta());
                rbO1.setText(listaAlternativas.get(35).getAlternativas());
                rbO2.setText(listaAlternativas.get(36).getAlternativas());
                rbO3.setText(listaAlternativas.get(37).getAlternativas());
                rbO4.setText(listaAlternativas.get(38).getAlternativas());
                rbO5.setText(listaAlternativas.get(39).getAlternativas());
                break;
            case (8):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(8).getPregunta());
                rbO1.setText(listaAlternativas.get(40).getAlternativas());
                rbO2.setText(listaAlternativas.get(41).getAlternativas());
                rbO3.setText(listaAlternativas.get(42).getAlternativas());
                rbO4.setText(listaAlternativas.get(43).getAlternativas());
                rbO5.setText(listaAlternativas.get(44).getAlternativas());
                break;
            case (9):
                txtIndicaciones.setText(" \n ");
                txtPreguntas.setText((posicionPregunta+1)+" de "+"10");
                txtPregunta.setText(listaPreguntas.get(9).getPregunta());
                rbO1.setText(listaAlternativas.get(45).getAlternativas());
                rbO2.setText(listaAlternativas.get(46).getAlternativas());
                rbO3.setText(listaAlternativas.get(47).getAlternativas());
                rbO4.setText(listaAlternativas.get(48).getAlternativas());
                rbO5.setText(listaAlternativas.get(49).getAlternativas());
                break;

        }
    }
    //Alternativa elegida
    public String opcionElegida(){

        if (rbO1.isChecked()){
            respuestaU =  rbO1.getText().toString();
        }else {
            if (rbO2.isChecked()){
                respuestaU=rbO2.getText().toString();
            }else {
                if(rbO3.isChecked()){
                    respuestaU=rbO3.getText().toString();
                }else {
                    if (rbO4.isChecked()){
                        respuestaU=rbO4.getText().toString();
                    }else {
                        if (rbO5.isChecked()){
                            respuestaU=rbO5.getText().toString();
                        }
                    }
                }
            }
        }

        return respuestaU;
    }
    //Respuestas para la api
    public void respuestasFinales(){
        //PREGUNTA 1
        switch (arrayRespuestas[0]){
            case "Siempre":
                p1 = listaAlternativas.get(0).getIdAlternativa();
                break;
            case "Frecuentemente":
                p1 = listaAlternativas.get(1).getIdAlternativa();
                break;
            case "A veces":
                p1 = listaAlternativas.get(2).getIdAlternativa();
                break;
            case "Raras veces":
                p1 = listaAlternativas.get(3).getIdAlternativa();
                break;
            case "Nunca":
                p1 = listaAlternativas.get(4).getIdAlternativa();
                break;
        }
        //PREGUNTA 2
        switch (arrayRespuestas[1]) {
            case "Siempre":
                p2 = listaAlternativas.get(5).getIdAlternativa();
                break;
            case "Frecuentemente":
                p2 = listaAlternativas.get(6).getIdAlternativa();
                break;
            case "A veces":
                p2 = listaAlternativas.get(7).getIdAlternativa();
                break;
            case "Raras veces":
                p2 = listaAlternativas.get(8).getIdAlternativa();
                break;
            case "Nunca":
                p2 = listaAlternativas.get(9).getIdAlternativa();
                break;
        }
        //PREGUNTA 3
        switch (arrayRespuestas[2]) {
            case "Siempre":
                p3 = listaAlternativas.get(10).getIdAlternativa();
                break;
            case "Frecuentemente":
                p3 = listaAlternativas.get(11).getIdAlternativa();
                break;
            case "A veces":
                p3 = listaAlternativas.get(12).getIdAlternativa();
                break;
            case "Raras veces":
                p3 = listaAlternativas.get(13).getIdAlternativa();
                break;
            case "Nunca":
                p3 = listaAlternativas.get(14).getIdAlternativa();
                break;
        }
        //PREGUNTA 4
        switch (arrayRespuestas[3]) {
            case "Siempre":
                p4 = listaAlternativas.get(15).getIdAlternativa();
                break;
            case "Frecuentemente":
                p4 = listaAlternativas.get(16).getIdAlternativa();
                break;
            case "A veces":
                p4 = listaAlternativas.get(17).getIdAlternativa();
                break;
            case "Raras veces":
                p4 = listaAlternativas.get(18).getIdAlternativa();
                break;
            case "Nunca":
                p4 = listaAlternativas.get(19).getIdAlternativa();
                break;
        }
        //PREGUNTA 5
        switch (arrayRespuestas[4]) {
            case "Siempre":
                p5 = listaAlternativas.get(20).getIdAlternativa();
                break;
            case "Frecuentemente":
                p5 = listaAlternativas.get(21).getIdAlternativa();
                break;
            case "A veces":
                p5 = listaAlternativas.get(22).getIdAlternativa();
                break;
            case "Raras veces":
                p5 = listaAlternativas.get(23).getIdAlternativa();
                break;
            case "Nunca":
                p5 = listaAlternativas.get(24).getIdAlternativa();
                break;
        }
        //PREGUNTA 6
        switch (arrayRespuestas[5]) {
            case "Siempre":
                p6 = listaAlternativas.get(25).getIdAlternativa();
                break;
            case "Frecuentemente":
                p6 = listaAlternativas.get(26).getIdAlternativa();
                break;
            case "A veces":
                p6 = listaAlternativas.get(27).getIdAlternativa();
                break;
            case "Raras veces":
                p6 = listaAlternativas.get(28).getIdAlternativa();
                break;
            case "Nunca":
                p6 = listaAlternativas.get(29).getIdAlternativa();
                break;
        }
        //PREGUNTA 7
        switch (arrayRespuestas[6]) {
            case "Siempre":
                p7 = listaAlternativas.get(30).getIdAlternativa();
                break;
            case "Frecuentemente":
                p7 = listaAlternativas.get(31).getIdAlternativa();
                break;
            case "A veces":
                p7 = listaAlternativas.get(32).getIdAlternativa();
                break;
            case "Raras veces":
                p7 = listaAlternativas.get(33).getIdAlternativa();
                break;
            case "Nunca":
                p7 = listaAlternativas.get(34).getIdAlternativa();
                break;
        }
        //PREGUNTA 8
        switch (arrayRespuestas[7]) {
            case "Siempre":
                p8 = listaAlternativas.get(35).getIdAlternativa();
                break;
            case "Frecuentemente":
                p8 = listaAlternativas.get(36).getIdAlternativa();
                break;
            case "A veces":
                p8 = listaAlternativas.get(37).getIdAlternativa();
                break;
            case "Raras veces":
                p8 = listaAlternativas.get(38).getIdAlternativa();
                break;
            case "Nunca":
                p8 = listaAlternativas.get(39).getIdAlternativa();
                break;
        }
        //PREGUNTA 9
        switch (arrayRespuestas[8]) {
            case "Siempre":
                p9 = listaAlternativas.get(40).getIdAlternativa();
                break;
            case "Frecuentemente":
                p9 = listaAlternativas.get(41).getIdAlternativa();
                break;
            case "A veces":
                p9 = listaAlternativas.get(42).getIdAlternativa();
                break;
            case "Raras veces":
                p9 = listaAlternativas.get(43).getIdAlternativa();
                break;
            case "Nunca":
                p9 = listaAlternativas.get(44).getIdAlternativa();
                break;
        }
        //PREGUNTA 10
        switch (arrayRespuestas[9]) {
            case "Siempre":
                p10 = listaAlternativas.get(45).getIdAlternativa();
                break;
            case "Frecuentemente":
                p10 = listaAlternativas.get(46).getIdAlternativa();
                break;
            case "A veces":
                p10 = listaAlternativas.get(47).getIdAlternativa();
                break;
            case "Raras veces":
                p10 = listaAlternativas.get(48).getIdAlternativa();
                break;
            case "Nunca":
                p10 = listaAlternativas.get(49).getIdAlternativa();
                break;
        }
    }
    //Para cuando se usa la función retroceder
    public void marcarCheck(int posicionPregunta){
        switch (arrayRespuestas[posicionPregunta]){
            case "Siempre":
                rbO1.setChecked(true);
                break;
            case "Frecuentemente":
                rbO2.setChecked(true);
                break;
            case "A veces":
                rbO3.setChecked(true);
                break;
            case "Raras veces":
                rbO4.setChecked(true);
                break;
            case "Nunca":
                rbO5.setChecked(true);
                break;
            default:
                quitarCheck();
                break;
        }
    }
    //POST DE DATOS
    private void enviarDatos(DialogInterface.OnClickListener onClickListener) {
        //TRY DE PERSONA
        try {

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();

            int idFacultad = getIntent().getExtras().getInt("idFacultad");
            int idTipo = getIntent().getExtras().getInt("idTipo");
            int sexo = getIntent().getExtras().getInt("sexo");
            String fechaNac = getIntent().getExtras().getString("fechaNac");

            jsonBody.put("idFacultad", idFacultad);
            jsonBody.put("idTipo", idTipo);
            jsonBody.put("sexo", sexo);
            jsonBody.put("fechaNac", fechaNac);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsertarPersona,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //TRYs DE RESPUESTAS
        try {
            RequestQueue requestQueue1 = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("idPersona",id);
            jsonBody.put("idAlternativa", p1);

            final String requestBody1 = jsonBody.toString();

            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E1");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody1 == null ? null : requestBody1.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody1, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString1 = "";
                    if (response != null) {
                        responseString1 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString1, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue1.add(stringRequest1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue2 = Volley.newRequestQueue(this);
            JSONObject jsonBody2 = new JSONObject();
            jsonBody2.put("idPersona",id);
            jsonBody2.put("idAlternativa", p2);

            final String requestBody2 = jsonBody2.toString();

            StringRequest stringRequest2 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E2");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody2 == null ? null : requestBody2.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody2, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString2 = "";
                    if (response != null) {
                        responseString2 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString2, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue2.add(stringRequest2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue3 = Volley.newRequestQueue(this);
            JSONObject jsonBody3 = new JSONObject();
            jsonBody3.put("idPersona",id);
            jsonBody3.put("idAlternativa", p3);

            final String requestBody = jsonBody3.toString();

            StringRequest stringRequest3 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E3");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString3 = "";
                    if (response != null) {
                        responseString3 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString3, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue3.add(stringRequest3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue4 = Volley.newRequestQueue(this);
            JSONObject jsonBody4 = new JSONObject();
            jsonBody4.put("idPersona",id);
            jsonBody4.put("idAlternativa", p4);

            final String requestBody4 = jsonBody4.toString();

            StringRequest stringRequest4 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E4");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody4 == null ? null : requestBody4.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody4, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString4 = "";
                    if (response != null) {
                        responseString4 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString4, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue4.add(stringRequest4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue5 = Volley.newRequestQueue(this);
            JSONObject jsonBody5 = new JSONObject();
            jsonBody5.put("idPersona",id);
            jsonBody5.put("idAlternativa", p5);

            final String requestBody5 = jsonBody5.toString();

            StringRequest stringRequest5 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E5");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody5 == null ? null : requestBody5.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody5, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString5 = "";
                    if (response != null) {
                        responseString5 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString5, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue5.add(stringRequest5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue6 = Volley.newRequestQueue(this);
            JSONObject jsonBody6 = new JSONObject();
            jsonBody6.put("idPersona",id);
            jsonBody6.put("idAlternativa", p6);

            final String requestBody6 = jsonBody6.toString();

            StringRequest stringRequest6 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E6");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody6 == null ? null : requestBody6.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody6, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString6 = "";
                    if (response != null) {
                        responseString6 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString6, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue6.add(stringRequest6);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue7 = Volley.newRequestQueue(this);
            JSONObject jsonBody7 = new JSONObject();
            jsonBody7.put("idPersona",id);
            jsonBody7.put("idAlternativa", p7);

            final String requestBody7 = jsonBody7.toString();

            StringRequest stringRequest7 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E7");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody7 == null ? null : requestBody7.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody7, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString7 = "";
                    if (response != null) {
                        responseString7 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString7, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue7.add(stringRequest7);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue8 = Volley.newRequestQueue(this);
            JSONObject jsonBody8 = new JSONObject();
            jsonBody8.put("idPersona",id);
            jsonBody8.put("idAlternativa", p8);

            final String requestBody8 = jsonBody8.toString();

            StringRequest stringRequest8 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E8");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody8== null ? null : requestBody8.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody8, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString8 = "";
                    if (response != null) {
                        responseString8 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString8, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue8.add(stringRequest8);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue9 = Volley.newRequestQueue(this);
            JSONObject jsonBody9 = new JSONObject();
            jsonBody9.put("idPersona",id);
            jsonBody9.put("idAlternativa", p9);

            final String requestBody9 = jsonBody9.toString();

            StringRequest stringRequest9 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E9");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody9 == null ? null : requestBody9.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody9, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString9 = "";
                    if (response != null) {
                        responseString9 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString9, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue9.add(stringRequest9);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestQueue requestQueue10 = Volley.newRequestQueue(this);
            JSONObject jsonBody10 = new JSONObject();
            jsonBody10.put("idPersona",id);
            jsonBody10.put("idAlternativa", p10);

            final String requestBody10 = jsonBody10.toString();

            StringRequest stringRequest10 = new StringRequest(Request.Method.POST, urlInsertarRespuesta,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("VOLLEY", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    System.out.println("E10");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody10 == null ? null : requestBody10.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody10, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString10 = "";
                    if (response != null) {
                        responseString10 = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString10, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue10.add(stringRequest10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Salir Formulario
    public void alertHome(){
        AlertDialog.Builder alert = new AlertDialog.Builder(FormularioProceso.this);

        alert.setMessage("Si regresa al home se eliminaran \n" +
                "todos sus datos").setCancelable(false).setPositiveButton(
                "Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(FormularioProceso.this,IniciarFormulario.class);
                        startActivity(intent);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog title = alert.create();
        title.setTitle("¿Está seguro de volver al home?");
        title.show();

    }
    //API Preguntas
    public void cargarPreguntas(final Context context){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                urlPreguntasID1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            String estado=jsonObject.getString("status");
                            String  code=jsonObject.getString("code");
                            if (estado.equals("success")&& code.equals("200")){
                                JSONArray jsonArray=jsonObject.getJSONArray("data");
                                listaPreguntas = new ArrayList<>();
                                Pregunta auxPregunta;
                                for (int i =0;i<jsonArray.length();i++){
                                    auxPregunta=new Pregunta(jsonArray.getJSONObject(i).optInt("idPregunta"),jsonArray.getJSONObject(i).optString("etiqueta"));
                                    listaPreguntas.add(auxPregunta);
                                }
                            }

                        } catch (JSONException e) {
                            System.out.println(e.toString());;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(stringRequest);


    }
    //API Alternativas
    public void cargarAlternativas(final  Context context){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                urlAlternativas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            String estado=jsonObject.getString("status");
                            String  code=jsonObject.getString("code");
                            if (estado.equals("success")&& code.equals("200")){
                                JSONArray jsonArray=jsonObject.getJSONArray("data");
                                listaAlternativas = new ArrayList<>();
                                Alternativa auxAlternativa;
                                for (int i =0;i<50;i++){
                                    auxAlternativa=new Alternativa(jsonArray.getJSONObject(i).getInt("idAlternativa"),jsonArray.getJSONObject(i).getInt("idPregunta"),jsonArray.getJSONObject(i).getString("etiqueta"));
                                    listaAlternativas.add(auxAlternativa);

                                }

                            }

                        } catch (JSONException e) {
                            System.out.println(e.toString());;
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(stringRequest);

    }
    //API Persona
    public void cargarPersonas(final Context context) {
        RequestQueue request =Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlPersona,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObjectTipo = new JSONObject(response);

                            String estado = jsonObjectTipo.getString("status");
                            String code = jsonObjectTipo.getString("code");

                            if (estado.equals("success") && code.equals("200")) {
                                JSONArray jsonArrayPersona = jsonObjectTipo.getJSONArray("data");

                                listaPersonas = new ArrayList<>();
                                Persona person;
                                for (int i =0;i<jsonArrayPersona.length();i++){
                                    person = new Persona(jsonArrayPersona.getJSONObject(i).optInt(
                                            "idPersona"),jsonArrayPersona.getJSONObject(i).optInt(
                                            "idTipo"),jsonArrayPersona.getJSONObject(i).optInt(
                                            "idFacultad"),jsonArrayPersona.getJSONObject(i).optInt(
                                            "sexo"),jsonArrayPersona.getJSONObject(i).optString("fechaNac"));
                                    listaPersonas.add(person);
                                }
                                id = listaPersonas.toArray().length+1;
                            }
                        }catch (JSONException e) {
                            System.out.println(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(error.getMessage());
                    }
                });
        request.add(stringRequest);
    }
    //Botón siguiente y final
    public void seguirProceso(View view){
        //Si la pregunta está marcada
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si la pregunta está marcada
                if (posicionPregunta >= 0 && posicionPregunta < 9 && (rbO1.isChecked()||rbO2.isChecked()||rbO3.isChecked()||rbO4.isChecked()||rbO5.isChecked())){
                    //Si la siguente pregunta está sin marcar
                    if (arrayRespuestas[posicionPregunta+1].equals("")){
                        arrayRespuestas[posicionPregunta] = opcionElegida();

                        posicionPregunta++;
                        quitarCheck();
                        mostarPregunta(posicionPregunta);
                    }else {
                        posicionPregunta++;
                        arrayRespuestas[posicionPregunta-1]=opcionElegida();

                        marcarCheck(posicionPregunta);
                        mostarPregunta(posicionPregunta);
                    }
                }
                //PREGUNTA FINAL
                else{
                    if (posicionPregunta<9) {
                        Toast error=Toast.makeText(FormularioProceso.this,"Por favor marque una opción",Toast.LENGTH_SHORT);
                        error.show();
                    }else {
                        if (rbO1.isChecked()||rbO2.isChecked()||rbO3.isChecked()||
                                rbO4.isChecked()||rbO5.isChecked()){

                            arrayRespuestas[posicionPregunta]=opcionElegida();

                            posicionPregunta++;

                            respuestasFinales();

                            AlertDialog.Builder alert = new AlertDialog.Builder(FormularioProceso.this);

                            alert.setMessage("Por favor confirme que todas las respuestas \n" +
                                    " seleccionados son correctas antes de enviar").setCancelable(false).setPositiveButton(
                                    "Sí", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent iDatos = new Intent(FormularioProceso.this, FinFormulario.class);
                                            startActivity(iDatos);

                                            enviarDatos(this);

                                        }
                                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            AlertDialog title = alert.create();
                            title.setTitle("¿Continuar?");
                            title.show();
                        }else {
                            Toast error=Toast.makeText(FormularioProceso.this,"Por favor marque una opción",Toast.LENGTH_SHORT);
                            error.show();
                        }
                    }
                }
            }
        });
    }
    //Botón home
    public void home(View view){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertHome();
            }
        });
    }
    //Color sigla
    public void iconYSigla(){
        //SIGLA E IMAGEN
        String sigla = getIntent().getExtras().getString("sigla");
        txtSigla.setText(sigla);
        if (sigla.equals("ING")){
            Drawable ing = getResources().getDrawable(R.drawable.icon_ing);
            ivFacultad.setImageDrawable(ing);
            txtSigla.setTextColor(getResources().getColor(R.color.ing));
        }else {
            if (sigla.equals("EMP")){
                Drawable emp = getResources().getDrawable(R.drawable.icon_emp);
                ivFacultad.setImageDrawable(emp);
                txtSigla.setTextColor(getResources().getColor(R.color.emp));
            }else {
                if (sigla.equals("DER")){
                    Drawable der = getResources().getDrawable(R.drawable.icon_der);
                    ivFacultad.setImageDrawable(der);
                    txtSigla.setTextColor(getResources().getColor(R.color.der));
                }else {
                    if (sigla.equals("COM")){
                        Drawable com = getResources().getDrawable(R.drawable.icon_fcom);
                        ivFacultad.setImageDrawable(com);
                        txtSigla.setTextColor(getResources().getColor(R.color.fcom));
                    }else {
                        if (sigla.equals("EDU")){
                            Drawable edu = getResources().getDrawable(R.drawable.icon_edu);
                            ivFacultad.setImageDrawable(edu);
                            txtSigla.setTextColor(getResources().getColor(R.color.edu));
                        }else {
                            if (sigla.equals("HUM")){
                                Drawable hum = getResources().getDrawable(R.drawable.icon_hum);
                                ivFacultad.setImageDrawable(hum);
                                txtSigla.setTextColor(getResources().getColor(R.color.hum));
                            }
                        }
                    }
                }
            }
        }
    }
}