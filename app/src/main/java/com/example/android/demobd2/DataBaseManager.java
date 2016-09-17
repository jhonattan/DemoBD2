package com.example.android.demobd2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ANDROID on 10/09/2016.
 */
public class DataBaseManager {

    public static final String CREATE_TABLE =
            "CREATE TABLE amigos("
                    + "_id integer primary key autoincrement," // obligatorio _id
                    + "nombre text not null,"
                    + "correo text not null,"
                    + "telefono text not null);";


    public static final  String INSERT =
            "INSERT INTO amigos(nombre, correo, telefono) VALUES"
                    + "('Víctor Balta','jked@gmail.com','4857542'),"
                    + "('Jose Benito','jaked@hotmail.com','44557542'),"
                    + "('Carlos Gonzales','dsdsed@hotmail.com','44523422');";


    // operaciones CRUD
    SQLiteDatabase database;

    public DataBaseManager(Context context) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
    }
    //

    public String frasesQry() {
        StringBuilder result = new StringBuilder();

        String[] cols = {"_id", "nombre", "correo","telefono"};
        Cursor cursor = database.query("amigos", cols, null, null, null, null, null);

        while(cursor.moveToNext()) {
            Integer id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String correo = cursor.getString(2);
            String telefono = cursor.getString(3);

            String fil = String.format("%02d %-20s\r\n %-40s %-40s", id, nombre, correo, telefono);
            result.append(fil).append("\r\n\r\n");
        }

        return result.toString();
    }

    public void frasesIns(String nombre, String correo, String telefono) {
        ContentValues values = new ContentValues();

        values.put("nombre", nombre);
        values.put("correo", correo);
        values.put("telefono", telefono);

        long ok = database.insert("amigos", null, values);
        // en el segundo parámetro va el nombre del campo que tiene valor null
        // si se pone null significa ningún campo
        // si ok == -1 no pudo hacer la inserción

        /*
        String sql = "INSERT INTO frases VALUES(" +
                "null," +
                "'" + autor + "'," +
                "'" + frase + "');";
        database.execSQL(sql);
        */
    }

    public void frasesDel(Integer id) {
        String[] param = {id.toString()};
        database.delete("amigos", "_id = ?", param);

        /*
        String sql = "DELETE FROM frases WHERE _id=" + id;
        database.execSQL(sql);
        */
    }

    public void frasesUpd(String nombre, String correo, String telefono, Integer id) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("correo", correo);
        values.put("telefono", telefono);

        String[] param = {id.toString()};

        database.update("amigos", values, "_id = ?", param);

        /*
        String sql = "UPDATE frases SET frase='" + frase + "' WHERE _id=" + id;
        database.execSQL(sql);
        */
    }

}
