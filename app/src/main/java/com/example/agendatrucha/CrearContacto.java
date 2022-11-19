package com.example.agendatrucha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class CrearContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        SharedPreferences myShared = = getSharedPreferences("SharedPreferenesAgenda",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("nombre", etNombre.getText().toString());
        myEdit.putString("apellidos", etApellidos.getText().toString());
        myEdit.putString("nombre", etNombre.getText().toString());
        myEdit.putString("apellidos", etApellidos.getText().toString());
        myEdit.putString("apellidos", etApellidos.getText().toString());
    }
}