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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Storing the key and its value as the data fetched from edittext
        editor.putString("nombre", etNombre.getText().toString());
        editor.putString("apellidos", etApellidos.getText().toString());
        editor.putString("nombre", etNombre.getText().toString());
        editor.putString("apellidos", etApellidos.getText().toString());
        editor.putString("apellidos", etApellidos.getText().toString());
    }
}