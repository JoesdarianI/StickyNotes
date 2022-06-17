// NIM :  2440012346 , 2440021086 , 2440036365

package com.example.mcs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button log,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        log = findViewById(R.id.Lgnbutton);
        reg = findViewById(R.id.Regbutton);

        log.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        });

        reg.setOnClickListener(view -> {
            Intent intent2 = new Intent(MainActivity.this, Register.class);
            startActivity(intent2);
        });
    }
}