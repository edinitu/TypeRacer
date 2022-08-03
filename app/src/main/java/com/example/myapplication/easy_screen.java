package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class easy_screen extends AppCompatActivity {

    private ImageButton Button1;
    private ImageButton Button2;
    private ImageButton Button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_screen);

        // Metoda care creeaza ecranul easy_1
        Button1 = findViewById(R.id.button_e1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEasyScreen_1();
            }
        });

        // Metoda care creeaza ecranul easy_2
        Button2 = findViewById(R.id.button_e2);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEasyScreen_2();
            }
        });

        // Metoda care creeaza ecranul easy_3
        Button3 = findViewById(R.id.button_e3);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEasyScreen_3();
            }
        });
    }

    // Metoda care deschide ecranul easy_1
    void openEasyScreen_1() {
        Intent intent = new Intent(this, easy_1.class);
        startActivity(intent);
    }

    // Metoda care deschide ecranul easy_2
    void openEasyScreen_2() {
        if(easy_1.isCheckPrag()) {
            Intent intent = new Intent(this, easy_2.class);
            startActivity(intent);
        }
    }

    // Metoda care deschide ecranul easy_3
    void openEasyScreen_3() {
        if(easy_2.isCheckPrag()) {
            Intent intent = new Intent(this, easy_3.class);
            startActivity(intent);
        }
    }

    // Metoda care atribuie o functionalitate butonului de Back
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, levelsScreen.class);
        startActivity(intent);
    }
}
