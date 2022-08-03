package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

public class Settings_screen extends AppCompatActivity {
    private Switch sw;
    private Button languageButton;
    private Button language1, language2;
    private Button musicButton;
    private TextView modeTex;
    private ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        sw = findViewById(R.id.switch1);
        languageButton = findViewById(R.id.button6);
        language1 = findViewById(R.id.limba1);
        language2 = findViewById(R.id.limba2);
        musicButton = findViewById(R.id.button_muzica);
        modeTex = findViewById(R.id.modeText);
        cl = findViewById(R.id.pa);

        if(Utils.LimbaRomana) {
            languageButton.setText("SETARE LIMBA");
            musicButton.setText("SETARE MUZICA FUNDAL");
            modeTex.setText("Mod intunecat");
        } else {
            languageButton.setText("LANGUAGE SETTINGS");
            musicButton.setText("BACKROUND MUSIC SETTINGS");
            modeTex.setText("Dark Mode");
        }
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMusicScreen();
            }
        });


        // Implementarea unui buton switch care schimba tema aplicatiei si ramane activ chiar si la navigarea intre ecrane
        sw.setChecked(Utils.MainActivityTheme);
        if(sw.isChecked()) darkSettings();
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    Utils.MainActivityTheme = true;
                    isChecked = true;
                    darkSettings();
                } else {
                    Utils.MainActivityTheme = false;
                    isChecked = false;
                    lightSettings();
                }
                buttonView.setChecked(isChecked);
            }
        });
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopUp(v);
            }
        });
        }

    @Override
    public void onBackPressed() {
        Utils.BackFromSettings = true;
        Intent intent = new Intent(this, levelsScreen.class);
        startActivity(intent);
    }

    // Metode care schimba fundalul si culorile textelor pentru optiunea de mod intunecat
    public void darkSettings() {
        cl.setBackgroundResource(R.drawable.settings_image_dm);
        modeTex.setTextColor(0xFFFFFFFF);
        musicButton.setTextColor(0xFFFFFFFF);
        languageButton.setTextColor(0xFFFFFFFF);
    }
    public void lightSettings() {
        modeTex.setTextColor(this.getResources().getColor(R.color.black));
        musicButton.setTextColor(this.getResources().getColor(R.color.black));
        languageButton.setTextColor(this.getResources().getColor(R.color.black));
        cl.setBackgroundResource(R.drawable.settings_image);
    }
    public void changeToRomana() {
        Utils.setRomana();
        languageButton.setText("SETARE LIMBA");
        musicButton.setText("SETARE MUZICA FUNDAL");
        modeTex.setText("Mod intunecat");
    }
    public void changeToEngleza() {
        Utils.setEngleza();
        languageButton.setText("LANGUAGE SETTINGS");
        musicButton.setText("BACKROUND MUSIC SETTINGS");
        modeTex.setText("Dark Mode");
    }

    // Afisarea unei ferestre popup care permite schimbarea limbii aplicatiei
    public void displayPopUp(View v) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_limbi, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        language1 = popupView.findViewById(R.id.limba1);
        language2 = popupView.findViewById(R.id.limba2);
        if (Utils.LimbaRomana) {
            languageButton.setText("SETARE LIMBA");
            musicButton.setText("SETARE MUZICA FUNDAL");
            modeTex.setText("Mod intunecat");
        } else {
            languageButton.setText("LANGUAGE SETTINGS");
            musicButton.setText("BACKROUND MUSIC SETTINGS");
            modeTex.setText("Dark Mode");
        }
        language1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToRomana();
                language1.setText("ROMANA");
                language2.setText("ENGLEZA");
            }
        });
        language2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToEngleza();
                language1.setText("ROMANIAN");
                language2.setText("ENGLISH");
            }
        });
    }
    void openMusicScreen() {
        Intent intent = new Intent(this, music_screen.class);
        startActivity(intent);
    }
}