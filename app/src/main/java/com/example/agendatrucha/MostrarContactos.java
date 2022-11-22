package com.example.agendatrucha;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MostrarContactos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_contactos);

        ListView listaInterfaz;

        SharedPreferences myPrefs = getSharedPreferences("SharedPreferencesAgenda",MODE_PRIVATE);
        int idContador = myPrefs.getInt("idContacto",1);
        listaInterfaz = (ListView)findViewById(R.id.lvListaContactos);
        ArrayAdapter adaptador;
        //CREAMOS EL ARRAY DE STRING DEL CONTACTO QUE MOSTRAREMOS EN EL LISTVIEW.
        ArrayList<String> listaContactos = new ArrayList<>();
        adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaContactos);

        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("contactosAnadidos.txt")));
            String texto;
            //Log.d("TxtRecuperado","*************"+texto);

            while((texto = fin.readLine())!=null){
                String [] textoSplit = texto.split(",");
                int idContacto = Integer.parseInt(textoSplit[0]);
                String nombre = textoSplit[1];
                String apellidos = textoSplit[2];
                String telefono = textoSplit[3];
                String direccion = textoSplit[4];
                //ESTO ES UNA PRUEBA PARA VER SI SE HACE BIEN EL SPLIT
                String linea = idContacto+" "+nombre+" "+apellidos+" "+telefono+" "+direccion;

                listaContactos.add(String.valueOf(idContacto)+nombre+apellidos+telefono+direccion);
                //SETEAMOS LOS VALORES AL OBJETO PARA ANADIR A ARRAYLIST????????? NO SE PARA QUE
                /*contacto.setId(idContacto);
                contacto.setNombre(nombre);
                contacto.setApellidos(apellidos);
                contacto.setTelefono(telefono);
                contacto.setDireccion(direccion);*/

                //MOSTRAMOS EL LISTVIEW CON EL ARRAYLIST DE STRING CARGADO
                listaInterfaz.setAdapter(adaptador);
            }

            //Log.d("TxtRecuperado","*************"+contacto.getId()+" "+contacto.getNombre()+" "+contacto.getApellidos()+" "+contacto.getTelefono()+" "+contacto.getDireccion());
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

        //EVENTO LONGCLICK PARA LLAMAR AL METODO DE BORRAR
        listaInterfaz.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                new AlertDialog.Builder(MostrarContactos.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle(".:BORRAR??:.")
                        .setMessage("Seguro que quieres el borrar el usuario: "+adaptador.getItem(i)+" ?")
                        .setPositiveButton("Zi, poh claro", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listaContactos.remove(String.valueOf((i)));
                                adaptador.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No, por diooh",null)
                        .show();
                return true;
            }
        });
    }
}