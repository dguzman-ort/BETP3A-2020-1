package com.example.dbsqliteejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class detalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle);

        DBHandler db = new DBHandler(this);

        ArrayList<HashMap<String,String>> listadoCarros = db.getCarros();

        ListView lv = (ListView) findViewById(R.id.carro_list);
        ListAdapter adapter = new SimpleAdapter(
        this,
        listadoCarros,
        R.layout.listado_carros,
        new String []{
                "marca",
                "modelo"
        },
        new int[] {
                R.id.mi_marca,
                R.id.mi_modelo
        });


        lv.setAdapter(adapter);

        Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detalleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
