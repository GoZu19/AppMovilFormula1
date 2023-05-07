package com.example.formula1.conexionBBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminBBDD extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE pilotos (id TEXT PRIMARY KEY,numeropiloto INTEGER,codigo TEXT,nombre TEXT,apellido TEXT,nacimiento DATE,nacionalidad TEXT);";
    public AdminBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
