package com.example.mcs2.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mcs2.Model.Notes;
import com.example.mcs2.Model.Users;

import java.util.ArrayList;

public class DBeditorHelper {

    Context context;
    DBhelper dBhelper;
    SQLiteDatabase db;

    public DBeditorHelper(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        dBhelper = new DBhelper(context);
        db = dBhelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        dBhelper.close();
    }

    public void insertUsers(Users users){
        String query = String.format("INSERT INTO users ( userEmail, userUsername, userPassword ) VALUES ('%s','%s','%s')",
                users.getEmail(),users.getUsername(),users.getPassword());
        db.execSQL(query);
    }

    public ArrayList<Users> listUsers(){
        ArrayList<Users> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        Users temp;
        int UserId;
        String UserEmail,UserUsername,UserPassword;
        if (cursor.getCount() > 0) {
            do {
                UserId = cursor.getInt(cursor.getColumnIndexOrThrow("user_Id"));
                UserEmail = cursor.getString(cursor.getColumnIndexOrThrow("userEmail"));
                UserUsername = cursor.getString(cursor.getColumnIndexOrThrow("userUsername"));
                UserPassword = cursor.getString(cursor.getColumnIndexOrThrow("userPassword"));

                temp = new Users(UserId, UserEmail, UserUsername, UserPassword);
                users.add(temp);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }cursor.close();
        return users;
    }

    public void insertNotes(Notes notes){
        String query = String.format("INSERT INTO notes (notesTitle,notesDescription,notesDate) VALUES ('%s','%s','%s')",
                notes.getNotesTitle(),notes.getNotesDescription(),notes.getNotesDate());
        db.execSQL(query);
    }

    public ArrayList<Notes> listNotes(){
        ArrayList<Notes> notes = new ArrayList<>();
        String query = "SELECT * FROM notes";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        Notes temp;
        int notesId;
        String notesName, notesDescription, notesDate;
        if(cursor.getCount() > 0){
            do {
                notesId = cursor.getInt(cursor.getColumnIndexOrThrow("notes_Id"));
                notesName = cursor.getString(cursor.getColumnIndexOrThrow("notesTitle"));
                notesDescription = cursor.getString(cursor.getColumnIndexOrThrow("notesDescription"));
                notesDate = cursor.getString(cursor.getColumnIndexOrThrow("notesDate"));

                temp = new Notes(notesId,notesName,notesDescription,notesDate);
                notes.add(temp);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return notes;
    }

    public void deleteNotes(int notesId){
        String query = String.format("DELETE FROM notes WHERE notes_Id = %d",notesId);
        db.execSQL(query);
    }
}
