package com.example.mcs2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.mcs2.Database.DBeditorHelper;
import com.example.mcs2.Model.Notes;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ListView listViewN;
    private DBeditorHelper dbEditorHelper = new DBeditorHelper(this);
    ArrayList<Notes> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listViewN = findViewById(R.id.listViews);
        try {
            dbEditorHelper.open();
            notes = dbEditorHelper.listNotes();
            dbEditorHelper.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        NotesAdapter notesAdapter = new NotesAdapter(this,R.layout.activity_notesdetail,notes);
        listViewN.setAdapter(notesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this,editNotes.class);
                startActivity(intent);
                break;
            case R.id.delete:
                Intent intent1 = new Intent(this,editNotes.class);
                startActivity(intent1);
                break;
            case R.id.logoutM:
                Intent intent2 = new Intent(this,Login.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
