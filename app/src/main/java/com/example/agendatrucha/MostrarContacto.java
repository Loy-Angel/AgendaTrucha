package com.example.agendatrucha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MostrarContacto extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_contacto);

        Bundle parametros = this.getIntent().getExtras();

        if(parametros !=null){
            int id = parametros.getInt("ID");
            String nombre = parametros.getString("NOMBRE");
            String apellidos = parametros.getString("APELLIDOS");
            String telefono = parametros.getString("TEL");
            String direccion = parametros.getString("DIRE");

            Log.d("Monstart", nombre);

            TextView name = findViewById(R.id.NAME);
            TextView ellipsoidal = findViewById(R.id.APELLIDOS);
            TextView notelet = findViewById(R.id.telefono);
            TextView direction = findViewById(R.id.direccion);

            name.setText(nombre);
            ellipsoidal.setText(apellidos);
            notelet.setText(telefono);
            direction.setText(direccion);
        }
    }

    public void volver(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}