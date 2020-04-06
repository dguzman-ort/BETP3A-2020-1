package com.example.clase3listadoadapters;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Array que asociaremos al adaptador

        String[] cars = ListOfCars.getListOfCars();
        //Creación del adaptador, vamos a escoger el layout simple_list_item_1, que los mostrara
        ListAdapter adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cars);

        //Asociamos el adaptador a la vista.
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setAdapter(adaptador);

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(
                MainActivity.this, // Contexto en donde va a ser mostrado el toast
                l.getItemAtPosition(position).toString(), // l hace referencia a la lista,
                Toast.LENGTH_LONG // la cantidad de tiempo que va a mostrarse
        ) .show();
        // Muestra un mensaje al presionar sobre un item de la lista
    }


    /** Called when the user touches the button */

    public void clickBotonPresioname(View view)
    {
        Toast.makeText(MainActivity.this, getString(R.string.mensage_click), Toast.LENGTH_LONG).show();
    }


}
