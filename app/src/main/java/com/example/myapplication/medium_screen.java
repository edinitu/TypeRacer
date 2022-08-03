package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class medium_screen extends AppCompatActivity {

    private ImageButton Button1;
    private ImageButton Button2;
    private ImageButton Button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_screen);

        // Metoda care creeaza ecranul medium_1
        Button1 = findViewById(R.id.button_m1);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMediumScreen_1();
            }
        });

        // Metoda care creeaza ecranul medium_2
        Button2 = findViewById(R.id.button_m2);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMediumScreen_2();
            }
        });

        // Metoda care creeaza ecranul medium_3
        Button3 = findViewById(R.id.button_m3);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMediumScreen_3();
            }
        });
    }

    // Metoda care deschide ecranul medium_1
    void openMediumScreen_1() {
        if(easy_3.isCheckPrag()) {
            Intent intent = new Intent(this, medium_1.class);
            startActivity(intent);
        }
    }

    // Metoda care deschide ecranul medium_2
    void openMediumScreen_2() {
        if(medium_1.isCheckPrag()) {
            Intent intent = new Intent(this, medium_2.class);
            startActivity(intent);
        }
    }

    // Metoda care deschide ecranul medium_3
    void openMediumScreen_3() {
        if(medium_2.isCheckPrag()) {
            Intent intent = new Intent(this, medium_3.class);
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