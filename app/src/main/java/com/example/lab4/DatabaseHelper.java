package com.example.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="noteDB";
    private static final int DB_VERSION=1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT,note_text TEXT);";
        db.execSQL(sqlStatement);
        ContentValues values = new ContentValues();
        values.put("note_text","Tesiname pirma reiksme");
        db.insert("note",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

