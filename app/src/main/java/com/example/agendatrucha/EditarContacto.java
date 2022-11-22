package com.example.agendatrucha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EditarContacto extends AppCompatActivity {
    int id;
    ArrayList<Integer> idContactos = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<String> apellidos = new ArrayList<>();
    ArrayList<String> telefonos = new ArrayList<>();
    ArrayList<String> direcciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button guard = findViewById(R.id.guardar);

        SharedPreferences myPrefs = getSharedPreferences("SharedPreferencesAgenda", MODE_PRIVATE);

        try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("contactosAnadidos.txt")));
            String texto;

            while ((texto = fin.readLine()) != null) {
                String[] textoSplit = texto.split(",");
                idContactos.add(Integer.parseInt(textoSplit[0]));
                nombres.add(textoSplit[1]);
                apellidos.add(textoSplit[2]);
                telefonos.add(textoSplit[3]);
                direcciones.add(textoSplit[4]);
            }

            for (int i = 0; i < idContactos.size(); i++) {
                Log.d("Contactos", idContactos.get(i) + nombres.get(i) + apellidos.get(i) + telefonos.get(i) + direcciones.get(i));
            }
        } catch (Exception e) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

        Bundle parametros = this.getIntent().getExtras();

        if(parametros !=null){
            id = parametros.getInt("ID");
            String nombre = parametros.getString("NOMBRE");
            String apellido = parametros.getString("APELLIDOS");
            String telefono = parametros.getString("TEL");
            String direccion = parametros.getString("DIRE");

            Log.d("Monstart", nombre);

            EditText name = findViewById(R.id.etNombreAC);
            EditText ellipsoidal = findViewById(R.id.etApellidosAC);
            EditText notelet = findViewById(R.id.etTelefonoAC);
            EditText direction = findViewById(R.id.etDireccionAC);

            name.setText(nombre);
            ellipsoidal.setText(apellido);
            notelet.setText(telefono);
            direction.setText(direccion);

            guard.setOnClickListener(view -> {
                String nom = name.getText().toString();
                String ape = ellipsoidal.getText().toString();
                String tel = notelet.getText().toString();
                String dire = direction.getText().toString();

                nombres.set((id-1), nom);
                apellidos.set((id-1), ape);
                telefonos.set((id-1), tel);
                direcciones.set((id-1), dire);

                String separator = System.getProperty("line.separator");

                try {
                    OutputStreamWriter fout = null;
                    fout = new OutputStreamWriter(openFileOutput("contactosAnadidos.txt", Context.MODE_PRIVATE));

                    for (int i = 0; i < idContactos.size(); i++){
                        fout.write(idContactos.get(i)+", "+nombres.get(i)+", "+apellidos.get(i)+", "+telefonos.get(i)+", "+direcciones.get(i));
                        fout.append(separator);
                    }

                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Los datos han sido guardados con exito.")
                        .setTitle("Datos guardados")
                        .setPositiveButton("Vale", (dialog, id) -> {
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        })
                        .setNegativeButton("", null);
                // Create the AlertDialog object and return it
                builder.show();
            });
        }
    }

    public void volver(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}