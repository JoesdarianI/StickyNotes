package com.example.mcs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mcs2.Database.DBeditorHelper;
import com.example.mcs2.Model.Users;

import java.util.ArrayList;
import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText email,password;
    private Button login,register;
    SharedPreferences sp;
    private DBeditorHelper helper = new DBeditorHelper(this);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);
        sp = getApplicationContext().getSharedPreferences("user_Id",MODE_PRIVATE);
        init();

        login.setOnClickListener(v -> {
            String emailTxt = email.getText().toString();
            String passwordTxt = password.getText().toString();
            ArrayList<Users> users = new ArrayList<>();

            if (emailTxt.isEmpty()) {
                email.setError("Email cannot be Empty");
                return;
            }
            try {
                helper.open();
                users = helper.listUsers();
                for (Users user : users) {
                    if (user.getEmail().equals(emailTxt) && user.getPassword().equals(passwordTxt)) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("user_Id", user.getUserID());
                        editor.apply();
                    }
                }
                helper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (sp.getInt("user_Id", -1) == -1) {
                Toast.makeText(Login.this, "Email or Password is Wrong", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            Intent intent2 = new Intent(Login.this, Register.class);
            startActivity(intent2);
        });
    }
    private void init(){
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.LoginBtn);
        register = findViewById(R.id.RegisterBtn);
    }

}