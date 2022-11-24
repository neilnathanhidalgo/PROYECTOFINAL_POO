package com.example.app_sueo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import Entidades.Facultad;
import Entidades.Tipo;


public class DatosFormulario extends AppCompatActivity implements View.OnClickListener {

    private Chip cHombre,cMujer,cIngenieria,cEmpresas,cDerecho,cComunicacion;
    private Chip cEducacion,cHumanidades,cFechaNacimiento,cAlumno,cDocente,cAdministrativo;

    private ChipGroup cgFacultad1,cgFacultad2, cgSexo, cgTipo;

    private int year, month, day, idFacultad, idTipo, sexo;
    private String fechaNac, sigla, sexoN, facultad, tipo;

    private Button btnDatos, btnExit;

    private ArrayList<Facultad> listaFacultades;
    private ArrayList<Tipo> listaTipo;

    String urlFacultad = "http://trabajopoo.kirudental.net/api/apiFacultad/listarTodos";
    String urlTipo = "http://trabajopoo.kirudental.net/api/apiTipo/listarTodos";

    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_formulario);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        cHombre = findViewById(R.id.cHombre);
        cMujer = findViewById(R.id.cMujer);
        cFechaNacimiento = findViewById(R.id.cFechaNacimiento);
        cIngenieria = findViewById(R.id.cIngenieria);
        cEmpresas = findViewById(R.id.cEmpresas);
        cDerecho = findViewById(R.id.cDerecho);
        cComunicacion = findViewById(R.id.cComunicacion);
        cEducacion = findViewById(R.id.cEducacion);
        cHumanidades = findViewById(R.id.cHumanidades);
        cAlumno = findViewById(R.id.cAlumno);
        cDocente = findViewById(R.id.cDocente);
        cAdministrativo = findViewById(R.id.cAdministrativo);

        cgFacultad1 = findViewById(R.id.cgFacultad1);
        cgFacultad2 = findViewById(R.id.cgFacultad2);
        cgSexo = findViewById(R.id.cgSexo);
        cgTipo = findViewById(R.id.cgTipo);

        btnDatos = findViewById(R.id.btnDatos);
        btnExit = findViewById(R.id.btnExit);

        requestQueue=Volley.newRequestQueue(this);

        cFechaNacimiento.setOnClickListener(this);
        cargarFacultades(this);
        cargarTipo(this);

    }
    //API Facultades
    private void cargarFacultades(final Context context) {
        RequestQueue request =Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlFacultad,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObjectFacultad = new JSONObject(response);

                            String estado = jsonObjectFacultad.getString("status");
                            String code = jsonObjectFacultad.getString("code");

                            if (estado.equals("success") && code.equals("200")) {
                                JSONArray jsonArrayFalcultad = jsonObjectFacultad.getJSONArray("data");

                                cIngenieria.setText(jsonArrayFalcultad.getJSONObject(0).getString("nombre"));
                                cEmpresas.setText(jsonArrayFalcultad.getJSONObject(1).getString("nombre"));
                                cDerecho.setText(jsonArrayFalcultad.getJSONObject(2).getString("nombre"));
                                cComunicacion.setText(jsonArrayFalcultad.getJSONObject(3).getString("nombre"));
                                cEducacion.setText(jsonArrayFalcultad.getJSONObject(4).getString("nombre"));
                                cHumanidades.setText(jsonArrayFalcultad.getJSONObject(5).getString("nombre"));

                                listaFacultades = new ArrayList<>();
                                Facultad f;
                                for (int i =0;i<jsonArrayFalcultad.length();i++){
                                    f = new Facultad(jsonArrayFalcultad.getJSONObject(i).optInt(
                                            "idFacultad"),jsonArrayFalcultad.getJSONObject(i).optString("sigla"),
                                            jsonArrayFalcultad.getJSONObject(i).optString("nombre"));
                                    listaFacultades.add(f);
                                }

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
    //API Tipo
    private void cargarTipo(final Context context) {
        RequestQueue request =Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlTipo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObjectTipo = new JSONObject(response);

                            String estado = jsonObjectTipo.getString("status");
                            String code = jsonObjectTipo.getString("code");

                            if (estado.equals("success") && code.equals("200")) {
                                JSONArray jsonArrayTipo = jsonObjectTipo.getJSONArray("data");

                                cAlumno.setText(jsonArrayTipo.getJSONObject(0).getString("nombre"));
                                cDocente.setText(jsonArrayTipo.getJSONObject(1).getString("nombre"));
                                cAdministrativo.setText(jsonArrayTipo.getJSONObject(2).getString("nombre"));

                                listaTipo = new ArrayList<>();
                                Tipo t;
                                for (int i =0;i<jsonArrayTipo.length();i++){
                                    t = new Tipo(jsonArrayTipo.getJSONObject(i).optInt(
                                            "idTipo"),jsonArrayTipo.getJSONObject(i).optString("nombre"));
                                    listaTipo.add(t);
                                }
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
    //Calendario de fecha
    @Override
    public void onClick(View v) {
        if(v==cFechaNacimiento) {
            final Calendar c = Calendar.getInstance();

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        if (monthOfYear<9){
                            cFechaNacimiento.setText(year+"-0"+(monthOfYear+1)+"-"+dayOfMonth);
                        } else {
                            cFechaNacimiento.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        }
                    }
                }
                ,day, month, year);
                datePickerDialog.show();
            }
        }

    }
    //Juntar ChipGroups
    public void desactivarCheck(View view) {
        boolean checked = ((Chip) view).isChecked();

        switch (view.getId()) {
            case R.id.cIngenieria:
                if (checked) {
                    cgFacultad2.clearCheck();
                    //cIngenieria.setChipBackgroundColor(ColorStateList.valueOf(Color));
                }
                break;
            case R.id.cEmpresas:
                if (checked) {
                    cgFacultad2.clearCheck();
                }
                break;
            case R.id.cDerecho:
                if (checked) {
                    cgFacultad2.clearCheck();
                }
                break;
            case R.id.cComunicacion:
                if (checked) {
                    cgFacultad1.clearCheck();
                }
                break;
            case R.id.cEducacion:
                if (checked) {
                    cgFacultad1.clearCheck();
                }
                break;
            case R.id.cHumanidades:
                if (checked) {
                    cgFacultad1.clearCheck();
                }
                break;
        }
    }
    //All checked, aLterta de confirmación y guardado de datos
    public void alertDialog(View view) {
        //Comprobación de datos y alerta
        if (cHombre.isChecked() || cMujer.isChecked()) {
            if (cIngenieria.isChecked() || cEmpresas.isChecked() || cDerecho.isChecked() ||
                    cComunicacion.isChecked() || cEducacion.isChecked() || cHumanidades.isChecked()) {
                if (cAlumno.isChecked() || cDocente.isChecked() || cAdministrativo.isChecked()) {
                    if ((cFechaNacimiento.getText().toString()).equals("aaaa/mm/dd") ) {
                        Toast.makeText(this,
                                "Por favor coloque su fecha de nacimiento", Toast.LENGTH_SHORT).show();
                    } else {
                        //sexo elegido
                        switch (cgSexo.getCheckedChipId()){
                            case R.id.cHombre:
                                sexo = 1;
                                sexoN = "Hombre";
                                break;
                            case R.id.cMujer:
                                sexo = 0;
                                sexoN = "Mujer";
                        }
                        //facultad elegida
                        switch (cgFacultad1.getCheckedChipId()){
                            case R.id.cIngenieria:
                                idFacultad = listaFacultades.get(0).getIdFacultad();
                                sigla = listaFacultades.get(0).getSigla();
                                facultad = listaFacultades.get(0).getNombre();
                                break;
                            case R.id.cEmpresas:
                                idFacultad = listaFacultades.get(1).getIdFacultad();
                                sigla = listaFacultades.get(1).getSigla();
                                facultad = listaFacultades.get(1).getNombre();
                                break;
                            case R.id.cDerecho:
                                idFacultad = listaFacultades.get(2).getIdFacultad();
                                sigla = listaFacultades.get(2).getSigla();
                                facultad = listaFacultades.get(2).getNombre();
                        }
                        switch (cgFacultad2.getCheckedChipId()){
                            case R.id.cComunicacion:
                                idFacultad = listaFacultades.get(3).getIdFacultad();
                                sigla = listaFacultades.get(3).getSigla();
                                facultad = listaFacultades.get(3).getNombre();
                                break;
                            case R.id.cEducacion:
                                idFacultad = listaFacultades.get(4).getIdFacultad();
                                sigla = listaFacultades.get(4).getSigla();
                                facultad = listaFacultades.get(4).getNombre();
                                break;
                            case R.id.cHumanidades:
                                idFacultad = listaFacultades.get(5).getIdFacultad();
                                sigla = listaFacultades.get(5).getSigla();
                                break;
                        }
                        //tipo elegido
                        switch (cgTipo.getCheckedChipId()) {
                            case R.id.cAlumno:
                                idTipo = listaTipo.get(0).getIdTipo();
                                tipo = listaTipo.get(0).getNombre();
                                break;
                            case R.id.cDocente:
                                idTipo = listaTipo.get(1).getIdTipo();
                                tipo = listaTipo.get(1).getNombre();
                                break;
                            case R.id.cAdministrativo:
                                idTipo = listaTipo.get(2).getIdTipo();
                                tipo = listaTipo.get(2).getNombre();
                                break;
                        }
                        //fecha elegida
                        fechaNac = cFechaNacimiento.getText().toString();

                        alertConfrimation();
                    }
                } else {
                    Toast.makeText(this, "Por favor complete sus datos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Por favor seleccione la facultad a la que pertenece", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor complete sus datos", Toast.LENGTH_SHORT).show();
        }

    }
    //Volver al inicio+alerta
    public void volverHome(View view) {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(DatosFormulario.this);

                alert.setMessage("Los datos que halla elegido no se guardaran").setCancelable(false).setPositiveButton(
                        "Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent iDatos = new Intent(DatosFormulario.this, IniciarFormulario.class);
                                startActivity(iDatos);
                                finish();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog title = alert.create();
                title.setTitle("¿Está seguro?");
                title.show();
            }
        });
    }
    //Alerta continuar
    public void alertConfrimation(){
        AlertDialog.Builder mensaje_alerta=new AlertDialog.Builder(DatosFormulario.this);
        mensaje_alerta.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.pantalla_confirmacion,null);
        mensaje_alerta.setView(view);

        AlertDialog dialog=mensaje_alerta.create();
        dialog.show();

        TextView txtSexoElegido = view.findViewById(R.id.txtSexoElegido);
        txtSexoElegido.setText(sexoN);
        TextView txtFacultadElegida=view.findViewById(R.id.txtFacultadElegida);
        txtFacultadElegida.setText(facultad);
        TextView txtTipoPersonaElegida=view.findViewById(R.id.txtTipoPersonaElegida);
        txtTipoPersonaElegida.setText(tipo);
        TextView txtFechaNac=view.findViewById(R.id.txtFechaNac);
        txtFechaNac.setText(fechaNac);

        Button btnConfirmar=view.findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatosFormulario.this,FormularioProceso.class);
                intent.putExtra("sexo",sexo);
                intent.putExtra("idFacultad",idFacultad);
                intent.putExtra("idTipo",idTipo);
                intent.putExtra("fechaNac",fechaNac);
                intent.putExtra("sigla",sigla);
                startActivity(intent);
            }
        });


        Button btnRegresar=view.findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}