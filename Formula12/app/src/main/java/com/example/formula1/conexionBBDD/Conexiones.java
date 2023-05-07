package com.example.formula1.conexionBBDD;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.formula1.guardarDatosApi.Piloto;

import java.util.ArrayList;

public class Conexiones {
    ArrayList<Piloto> arrayPilotos;
    private SQLiteDatabase db;
    public Conexiones(SQLiteDatabase db) {
        this.db = db;
        arrayPilotos = new ArrayList<Piloto>();
    }
    public void guardarPilotoFavorito(Piloto piloto){
        if (db != null){
            String id,codigo,nombre,apellido,nacimiento,nacionalidad,numeropiloto;

            id = piloto.getId();
            codigo = piloto.getCodigo();
            nombre = piloto.getNombre();
            apellido = piloto.getApellido();
            nacimiento = piloto.getFechaNacimiento();
            nacionalidad = piloto.getNacionalidad();
            numeropiloto = piloto.getNumero();
            String sql = "INSERT INTO pilotos (id, numeropiloto, codigo, nombre, apellido, nacimiento, nacionalidad) VALUES ('"+id+"',"+ numeropiloto+", '"+codigo+"', '"+nombre+"', '"+apellido+"', '"+nacimiento+"', '"+nacionalidad+"');";
            db.execSQL(sql);
        }

    }
    public void eliminarPilotoFavorito(Piloto piloto){
        String id = piloto.getId();
        String sql = "DELETE FROM pilotos WHERE id = '"+id+"';";
        db.execSQL(sql);
    }
    public ArrayList<Piloto> obtenerPilotosFavoritos(){
        Cursor fila = db.rawQuery("select * from pilotos;",null);
        while(fila.moveToNext()){
            Piloto piloto = new Piloto();
            piloto.setId(fila.getString(0));
            piloto.setNumero(fila.getString(1));
            piloto.setCodigo(fila.getString(2));
            piloto.setNombre(fila.getString(3));
            piloto.setApellido(fila.getString(4));
            piloto.setFechaNacimiento(fila.getString(5));
            piloto.setNacionalidad(fila.getString(6));

            arrayPilotos.add(piloto);
        }
        return arrayPilotos;
    }
}
