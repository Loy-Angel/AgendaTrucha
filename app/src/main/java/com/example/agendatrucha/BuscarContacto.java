package com.example.agendatrucha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class BuscarContacto extends AppCompatActivity {
    TextInputLayout nombre;
    TextInputLayout apellido;
    ArrayList<Integer> idContactos = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<String> apellidos = new ArrayList<>();
    ArrayList<String> telefono = new ArrayList<>();
    ArrayList<String> direccion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_contacto);

        SharedPreferences myPrefs = getSharedPreferences("SharedPreferencesAgenda",MODE_PRIVATE);

        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("contactosAnadidos.txt")));
            String texto;

            while ((texto = fin.readLine()) != null) {
                String[] textoSplit = texto.split(",");
                idContactos.add(Integer.parseInt(textoSplit[0]));
                nombres.add(textoSplit[1]);
                apellidos.add(textoSplit[2]);
                telefono.add(textoSplit[3]);
                direccion.add(textoSplit[4]);
            }

            for(int i = 0; i < idContactos.size(); i++){
                Log.d("Contactos", idContactos.get(i)+nombres.get(i)+apellidos.get(i)+telefono.get(i)+direccion.get(i));
            }
        } catch (Exception e) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

        nombre = findViewById(R.id.textInputLayout);
        apellido = findViewById(R.id.textInputLayoutApe);
    }

    public void buscar(View v){
        Log.d("Buscar", "has been presed");
        Log.d("Buscar", nombre.getEditText().getText().toString());
        String nom = nombre.getEditText().getText().toString();
        Log.d("Buscar", apellido.getEditText().getText().toString());
        String ape = apellido.getEditText().getText().toString();
        int found = -1;
        for (int i = 0; i < nombres.size(); i++){
            Log.d("Vuelta", ""+i);
            if(nombres.get(i).toUpperCase().contains(nom.toUpperCase())){
                Log.d("Found contact", nombres.get(i));
                if(apellidos.get(i).toUpperCase().contains(ape.toUpperCase())){
                    Log.d("Found contact", apellidos.get(i));
                    found = i;
                }
            }
        }

        if(found > -1){
            Intent sc1 = new Intent(this, MostrarContacto.class);
            sc1.putExtra("ID", idContactos.get(found));
            sc1.putExtra("NOMBRE", nombres.get(found));
            sc1.putExtra("APELLIDOS", apellidos.get(found));
            sc1.putExtra("TEL", telefono.get(found));
            sc1.putExtra("DIRE", direccion.get(found));
            startActivity(sc1);
        }
        else {

        }
    }

    public void volver(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}