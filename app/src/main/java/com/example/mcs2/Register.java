package com.example.mcs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mcs2.Database.DBeditorHelper;
import com.example.mcs2.Model.Users;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText email,username,password;
    Button login,register;
    private DBeditorHelper editorHelper = new DBeditorHelper(this);


    public static final String passwordPattern = "(?=.*[0-9])"+".{3,20}";
    public static final Pattern pattern =Pattern.compile(passwordPattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);

        init();

        register.setOnClickListener(v-> {
            String EmailTxt = email.getText().toString();
            String UsernameTxt = username.getText().toString();
            String PasswordTxt = password.getText().toString();

            boolean error = false;
            ArrayList<Users> users = new ArrayList<>();
            try {
                editorHelper.open();
                users = editorHelper.listUsers();
                editorHelper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (EmailTxt.isEmpty()) {
                error = true;
                email.setError("Email address cannot be Empty");
            } else if (!EmailTxt.endsWith(".com")) {
                error = true;
                email.setError("Invalid Email Address");
            }else{
                for (Users users1:users){
                    if(email.equals(users1.getEmail())){
                        error = true;
                        email.setError("Email already registered");
                        break;
                    }
                }
            }

            if (UsernameTxt.length() < 3 || UsernameTxt.length() > 20) {
                error = true;
                username.setError("Invalid Username");
            } else if (UsernameTxt.isEmpty()) {
                error = true;
                username.setError("Username cannot be Empty");
            }

            else if (PasswordTxt.isEmpty()) {
                error = true;
                password.setError("Password cannot be Empty");
            } else if (!pattern.matcher(PasswordTxt).matches()) {
                error = true;
                password.setError("invalid Password");
            }

            if(error){
                return;
            }

            try{
                editorHelper.open();
                editorHelper.insertUsers(new Users(-1, EmailTxt, UsernameTxt, PasswordTxt));
                editorHelper.close();
                Toast.makeText(Register.this, "REGISTER SUCCESS", Toast.LENGTH_SHORT).show();

            }catch (SQLException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);

        });
    }

    private void init(){
        email = findViewById(R.id.editTextEmailAddressR);
        username = findViewById(R.id.editTextUsernameR);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.LoginrBtn);
        register = findViewById(R.id.RegisterrBtn);
    }
}