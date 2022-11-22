package com.example.agendatrucha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.SharedPreferences;
import android.os.Bundle;

public class CrearContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        EditText etNombre = findViewById(R.id.etNombreAC);
        EditText etApellidos = findViewById(R.id.etApellidosAC);
        EditText etTelefono = findViewById(R.id.etTelefonoAC);
        EditText etDireccion = findViewById(R.id.etDireccionAC);
        Button bGuardarContacto = findViewById(R.id.bAnadirContacto);
        Button btMostrarListaContactos = findViewById(R.id.btMostrarLista);


        bGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences myShared = getSharedPreferences("SharedPreferencesAgenda",MODE_PRIVATE);
                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = myShared.edit();
                // Storing the key and its value as the data fetched from edittext

                //ESTA LINEA ES PARA RECUPERAR EL INT ID DEL SHARED PREFERENCES Y SEGUIR USANDOLO COMO ITERADOR
                int idContador = myShared.getInt("idContacto",0);

                idContador++;
                myEdit.putInt("idContacto",idContador);
                myEdit.commit();

                String nombre = etNombre.getText().toString();
                String apellido = etApellidos.getText().toString();
                String telefono = etTelefono.getText().toString();
                String direccion = etDireccion.getText().toString();

                Contacto contacto = new Contacto(idContador,nombre,apellido,telefono,direccion);

                try {
                    String separator = System.getProperty("line.separator");
                    OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("contactosAnadidos.txt", Context.MODE_APPEND));
                    fout.write(contacto.getId()+", "+contacto.getNombre().toString()+", "+contacto.getApellidos().toString()+", "+contacto.getTelefono().toString()+", "+contacto.getDireccion().toString());
                    fout.append(separator);
                    fout.close();

                    Log.d("nombre", contacto.getNombre().toString());
                    Log.d("apellidos", contacto.getApellidos().toString());
                    Log.d("telefono", contacto.getTelefono().toString());
                    Log.d("direccion",contacto.getDireccion().toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(CrearContacto.this,contacto.getNombre().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(CrearContacto.this,contacto.getApellidos().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(CrearContacto.this,contacto.getTelefono().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(CrearContacto.this,contacto.getDireccion().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void volver(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}