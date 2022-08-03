package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class hard_screen extends AppCompatActivity {

    private ImageButton Button;
    private ImageButton Button2;
    private ImageButton Button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_screen);

        // Metoda care creeaza ecranul hard_1
        Button = findViewById(R.id.button_h1);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHardScreen_1();
            }
        });

        // Metoda care creeaza ecranul hard_2
        Button2 = findViewById(R.id.button_h2);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHardScreen_2();
            }
        });

        // Metoda care creeaza ecranul hard_3
        Button3 = findViewById(R.id.button_h3);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHardScreen_3();
            }
        });
    }

    // Metoda care deschide ecranul hard_1
    void openHardScreen_1() {
        if(medium_3.isCheckPrag()) {
            Intent intent = new Intent(this, hard_1.class);
            startActivity(intent);
        }
    }

    // Metoda care deschide ecranul hard_2
    void openHardScreen_2() {
        if(hard_1.isCheckPrag()) {
            Intent intent = new Intent(this, hard_2.class);
            startActivity(intent);
        }
    }

    // Metoda care deschide ecranul hard_3
    void openHardScreen_3() {
        if(hard_2.isCheckPrag()) {
            Intent intent = new Intent(this, hard_3.class);
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