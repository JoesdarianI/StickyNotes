package com.example.mcs2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mcs2.Database.DBeditorHelper;
import com.example.mcs2.Model.Notes;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class editNotes extends AppCompatActivity {

    EditText title,description,id;
    Button add,delete;
    DBeditorHelper dbEditorHelper = new DBeditorHelper(this);

    public Calendar calendar;
    public SimpleDateFormat simpleDateFormat;
    public String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        date = simpleDateFormat.format(calendar.getTime());


        init();

        add.setOnClickListener(view -> {
            String titleTxt = title.getText().toString();
            String descTxt = description.getText().toString();

            ArrayList<Notes> notes = new ArrayList<>();

            try{
                dbEditorHelper.open();
                dbEditorHelper.insertNotes(new Notes(-1,titleTxt,descTxt,date));
                dbEditorHelper.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            Toast.makeText(this, "Add Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        });

        delete.setOnClickListener(view -> {
            String idTxt = id.getText().toString();

            int notesID = Integer.parseInt(idTxt);

            try {
                dbEditorHelper.open();
                dbEditorHelper.deleteNotes(notesID);
                dbEditorHelper.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show();
            Intent intent2= new Intent(this,Home.class);
            startActivity(intent2);
        });
    }

    private void init(){
        title = findViewById(R.id.addTitle);
        description = findViewById(R.id.addDescription);
        id =findViewById(R.id.ID);
        add = findViewById(R.id.addbtn);
        delete = findViewById(R.id.deleteBtn);
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
        }
        return super.onOptionsItemSelected(item);
    }
}