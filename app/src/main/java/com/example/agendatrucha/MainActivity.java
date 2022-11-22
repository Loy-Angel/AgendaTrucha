package com.example.agendatrucha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void boton(View v){
        switch (Integer.parseInt(v.getTag().toString())){
            case 1:
                Intent sc1 = new Intent(this, CrearContacto.class);
                startActivity(sc1);
                break;
            case 2:
                Intent sc2 = new Intent(this, BuscarContacto.class);
                sc2.putExtra("Buscar", 1);
                startActivity(sc2);
                break;
            case 4:
                Intent sc4 = new Intent(this, BuscarContacto.class);
                sc4.putExtra("Buscar", 0);
                startActivity(sc4);
                break;
            case 5:
                Intent sc5 = new Intent(this, MostrarContactos.class);
                startActivity(sc5);
                break;
        }
    }
}