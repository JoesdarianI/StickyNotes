package com.example.mcs2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public DBhelper(@Nullable Context context){
        super(context,"Notes",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE users ( " +
                "user_Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "userEmail TEXT," + "userUsername TEXT," +
                "userPassword TEXT)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE notes ( " +
                "notes_Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "notesTitle String," + "notesDescription String," +
                "notesDate String" + ")";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");
    }
}
