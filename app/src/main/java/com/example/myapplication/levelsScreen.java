package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class levelsScreen extends AppCompatActivity {

    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button yesButton;
    private Button noButton;
    private TextView textExit;
    private ImageButton settingsButton;

    // Metoda care creeaza ecranul levelsScreen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran2);

        // Dupa o logare reusita, deblocam nivelele unde utilizatorul a trecut pragul de cuvinte
        Utils.unlockLevels();

        easyButton = findViewById(R.id.button3);
        easyButton.setBackgroundColor(0x86E7F3);
        easyButton.setTextColor(Color.BLACK);

        mediumButton = findViewById(R.id.button2);
        mediumButton.setBackgroundColor(0x86E7F3);
        mediumButton.setTextColor(Color.BLACK);

        hardButton = findViewById(R.id.button4);
        hardButton.setBackgroundColor(0x86E7F3);
        hardButton.setTextColor(Color.BLACK);

        settingsButton = findViewById(R.id.imageButton3);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsScreen();
            }
        });

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEasyScreen();
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMediumScreen();
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHardScreen();
            }
        });
        changeLanguage();
        Utils.notAppStart = true;
    }

    // Metoda care deschide ecranul de setari
    void openSettingsScreen() {
        Intent intent = new Intent(this, Settings_screen.class);
        startActivity(intent);
    }

    // Metoda care deschide ecranul easy_screen
    void openEasyScreen() {
        Intent intent = new Intent(this, easy_screen.class);
        startActivity(intent);
    }

    // Metoda care deschide ecranul medium_screen
    void openMediumScreen() {
        Intent intent = new Intent(this, medium_screen.class);
        startActivity(intent);
    }

    // Metoda care deschide ecranul hard_screen
    void openHardScreen() {
        Intent intent = new Intent(this, hard_screen.class);
        startActivity(intent);
    }

    // Metoda care atribuie o functionalitate butonului de Back
    @Override
    public void onBackPressed() {
        displayPopUp();
    }

    public void changeLanguage() {
        if(Utils.EnglishLanguage) {
            easyButton.setText("EASY");
            mediumButton.setTextSize(25);
            mediumButton.setText("MEDIUM");
            hardButton.setText("HARD");
        } else if(Utils.LimbaRomana) {
            easyButton.setText("USOR");
            mediumButton.setText("MEDIU");
            hardButton.setText("GREU");
        }
    }
    // Afisam o fereastra pop-up pentru a nu iesi din joc din greseala la apasarea butonului back
    public void displayPopUp() {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_exit, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        textExit = popupView.findViewById(R.id.textExit);
        noButton = popupView.findViewById(R.id.buttonNo);
        yesButton = popupView.findViewById(R.id.buttonYes);

        // functionalitatea de schimbare a limbii
        if(Utils.EnglishLanguage) {
           textExit.setText("Are you sure you want to exit?");
           noButton.setText("No");
           yesButton.setText("Yes");
        } else if(Utils.LimbaRomana) {
            textExit.setText("Esti sigur ca vrei sa iesi?");
            noButton.setText("Nu");
            yesButton.setText("Da");
        }

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        // In caz ca utilizatorul nu doreste sa iasa din joc, fereastra pop-up dispare
        // si se revine la ecranul de nivele
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        // Daca utilizatorul doreste sa iasa din joc, se realizeaza o delogare: toate nivelele se blocheaza
        // si asteptam in ecranul MainActivity sa fie introduse din nou datele unui utilizator
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.BackFromSettings) Utils.BackFromSettings2 = true;
                Utils.lockLevels();
                Intent intent = new Intent(levelsScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}