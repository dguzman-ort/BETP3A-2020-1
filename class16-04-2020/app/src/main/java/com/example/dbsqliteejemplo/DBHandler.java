package com.example.dbsqliteejemplo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "carrosdb";
    public static final String TABLE_Carros= "detalle_carros";
    public static final String KEY_ID= "id";
    public static final String KEY_MARCA= "marca";
    public static final String KEY_MODELO= "modelo";


    public DBHandler(Context contexto){
        super(contexto, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+ TABLE_Carros + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                KEY_MARCA +" TEXT, " +
                KEY_MODELO + " TEXT )";


        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_Carros);
        onCreate(db);
    }


    //** CRUD para Carros **/

    /**
     * Insercion de datos para la tabla Carro
     *
     * @belongsTO DBHandler
     *
     * @param marca
     * @param modelo
     */
    public void insertarDetalleCarro(String marca, String modelo){
        // Obtener data model para escribir en DB
        SQLiteDatabase db = this.getWritableDatabase();

        // Defino content values para el modelado de datos.
        ContentValues values = new ContentValues();
        values.put(KEY_MARCA, marca);
        values.put(KEY_MODELO, modelo);

        /*
            INSERT INTO detalle_carros VALUES (marca, modelo)
         */
        long car_id = db.insert(TABLE_Carros, null, values);

        db.close();

    }

    /**
     * Obtiene el listado de carros almacenados en DB
     * @return
     */
    public ArrayList<HashMap<String,String>> getCarros(){

        // Obtener la conexion de la BD para la tabla carros
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> listadoCarros = new ArrayList<>();

        // Construyo la query cruda.
        String query = "SELECT id, marca, modelo FROM " + TABLE_Carros;

        // obtengo un cursor con el resultado de la ejecucion de la query creada.
        Cursor cursor = db.rawQuery(query, null);

        //Me muevo en el cursor con el fin de formatear la salida.
        while (cursor.moveToNext()){

            HashMap<String,String> carro = new HashMap<>();
            //Carro carro = new Carro();

            // Agrego el resultado de la misma al auxiliar carro
            carro.put("marca",cursor.getString(cursor.getColumnIndex(KEY_MARCA)));
            carro.put("modelo",cursor.getString(cursor.getColumnIndex(KEY_MODELO)));

            listadoCarros.add(carro);
        }

        return listadoCarros;
    }

    public Carro getCarroById(int id){
        // Obtener la conexion de la BD para la tabla carros
        SQLiteDatabase db = this.getWritableDatabase();
        Carro carro = new Carro();

        String query = "SELECT marca, modelo FROM " + TABLE_Carros;

        Cursor cursor = db.query(TABLE_Carros,
                // SELECT *
                new String[] {
                KEY_MARCA,
                KEY_MODELO
        },
                // WHERE
                KEY_ID + " = ?", new String[] {
                String.valueOf(id),
        }, null, null, null);

        // Como en teoria me tre un solo elemento (estoy filtrando por KEY_ID), pregunto una sola vez
        // por el movimiento del cursor.
        if (cursor.moveToNext()){
            carro.setMarca(cursor.getString(cursor.getColumnIndex(KEY_MARCA)));
            carro.setModelo(cursor.getString(cursor.getColumnIndex(KEY_MODELO)));
        }

        db.close();

        return carro;

    }

    public void borrarCarro (int id){
        // Obtener la conexion de la BD para la tabla carros
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_Carros,
        KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });
        db.close();
    }

    public int actualizarCarro (int id, String modelo, String marca){
        // Obtener la conexion de la BD para la tabla carros
        SQLiteDatabase db = this.getWritableDatabase();

        // Defino content values para el modelado de datos.
        ContentValues values = new ContentValues();
        values.put(KEY_MARCA, marca);
        values.put(KEY_MODELO, modelo);

        int count = db.update(TABLE_Carros, values,
        KEY_ID + " = ?", new String[] {
                String.valueOf(id)
        });

        return count;
    }




}
