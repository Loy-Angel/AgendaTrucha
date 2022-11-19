package com.example.agendatrucha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.*;

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
                Intent sc2 = new Intent(this, EditarContacto.class);
                startActivity(sc2);
                break;
            case 3:
                Intent sc3 = new Intent(this, BorrarContacto.class);
                startActivity(sc3);
                break;
            case 4:
                Intent sc4 = new Intent(this, BuscarContacto.class);
                startActivity(sc4);
                break;
            case 5:
                Intent sc5 = new Intent(this, MostrarContactos.class);
                startActivity(sc5);
                break;
        }
    }
}