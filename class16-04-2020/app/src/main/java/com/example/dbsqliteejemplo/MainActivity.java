package com.example.dbsqliteejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText marca, modelo;
    Button saveButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // vinculacion con los elemntos
        marca = (EditText) findViewById(R.id.txtMarca);
        modelo = (EditText) findViewById(R.id.txtModelo);
        saveButton = (Button) findViewById(R.id.btnSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carro_marca = marca.getText().toString() + "\n ";
                String carro_modelo = modelo.getText().toString() + "\n ";

                // Aqui instancia la clase handler de la DB
                DBHandler dbHandler = new DBHandler(MainActivity.this);

                dbHandler.insertarDetalleCarro(carro_marca, carro_modelo);

                intent = new Intent(MainActivity.this, detalleActivity.class);

                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Se cargo correctamente", Toast.LENGTH_LONG);
            }
        });


    }


    public void clickButton(){
        // hacer lo que yo quiera
    }
}
