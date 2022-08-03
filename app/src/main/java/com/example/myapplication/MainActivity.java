package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import java.lang.*;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//import static com.example.myapplication.Utils.notify;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;
    private TextView checkWarnings;
    private Button toSignUp;
    private FirebaseDatabase db;
    private String enteredUsername, enteredPassword;
    private int userEnters, passEnters;
    public boolean ok = false;

    // Metoda care creeaza ecranul MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.button);
        usernameInput = findViewById(R.id.input);
        passwordInput = findViewById(R.id.inputPassword);
        toSignUp = findViewById(R.id.toSignUp);
        checkWarnings = findViewById(R.id.textWarnings);

        userEnters = 0;
        passEnters = 0;

        // Asiguram conexiunea cu baza de date Firebase, de tip Real-time database
        db = FirebaseDatabase.getInstance("https://ppa3-4ffe8-default-rtdb.firebaseio.com/");

        // La deschiderea aplicatiei, limba initiala va fi romana
        if(!Utils.notAppStart) Utils.setRomana();

        // Metode folosite pentru a putea atribui o functie butonului
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEnters >= 1 && passEnters >= 1) verifyUserAndLogin();
                else {
                    checkWarnings.setTextSize(11);
                    checkWarnings.setText("Apasati enter pentru\na introduce\nnumele de utilizator");
                }
            }
        });

        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        // Metode folosite pentru a putea introduce text
        usernameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    sendUser();
                    MainActivity.this.userEnters++;
                    ok = true;
                    handled = true;
                }
                return handled;
            }
        });

        passwordInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handlePassword = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    sendPassword();
                    MainActivity.this.passEnters++;
                    handlePassword = true;
                }
                return handlePassword;
            }
        });

        if(Utils.MainActivityTheme && Utils.BackFromSettings2) setDarkTheme();
        else setLightTheme();
        changeLanguage();
    }

    // Metoda care atribuie o functionalitate butonului de Back
    @Override
    public void onBackPressed() {
        if(usernameInput.hasFocus()) usernameInput.clearFocus();
        if(passwordInput.hasFocus()) passwordInput.clearFocus();
    }

    // Urmatoare 2 metode incarca datele introduse in atributele enteredUsername si enteredPassword
    public void sendUser() {
        usernameInput.clearFocus();
        this.enteredUsername = usernameInput.getText().toString().trim();
        assert enteredUsername != null;
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(usernameInput.getWindowToken(), 0);
    }

    public void sendPassword(){
        passwordInput.clearFocus();
        this.enteredPassword = passwordInput.getText().toString().trim();
        assert enteredPassword != null;
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(usernameInput.getWindowToken(), 0);
    }

    // Metoda care acceseaza baza de date, verifica daca utilizatorul exista
    // si trece la ecranul de nivele daca datele acestuia sunt valide
    public void verifyUserAndLogin() {
        DatabaseReference dbRef = db.getReference("Users");
        Query checkUser = dbRef.orderByChild("username").equalTo(this.enteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String passFromDB = dataSnapshot.child(MainActivity.this.enteredUsername).child("password").getValue(String.class);
                    if(passFromDB.equals(MainActivity.this.enteredPassword)) {
                        Utils.player = new Player(MainActivity.this.enteredUsername, passFromDB);
                        Utils.player.setLevel(dataSnapshot.child(MainActivity.this.enteredUsername).child("level").getValue(String.class));
                        openEcran2();
                    }
                    else {
                        setWrongPasswordText();
                    }
                } else setWrongUsernameText();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    // Metode unde tratam posibilitatea introducerii gresite a numelui de utilizator sau parola
    public void setWrongPasswordText() {
        checkWarnings.setText("Parola gresita");
    }
    public void setWrongUsernameText() {
        checkWarnings.setText("User inexistent");
    }

    public void openEcran2(){
        Intent intent = new Intent(this, levelsScreen.class);
        startActivity(intent);
    }
    public void openSignUp() {
        Intent intent = new Intent(this, signup_screen.class);
        startActivity(intent);
    }

    // Metoda folosita pentru a seta o tema intunecata/deschisa
    public void setDarkTheme(){
        usernameInput.setHintTextColor(0xffffffff);
        passwordInput.setHintTextColor(0xffffffff);
    }

    public void setLightTheme() {
        usernameInput.setHintTextColor(0xFF755858);
        passwordInput.setHintTextColor(0xFF755858);
    }

    // Metoda folosita pentru a schimba limba in care sunt afisate criteriile de login
    public void changeLanguage() {
        if(Utils.EnglishLanguage) {
            loginButton.setText("LOGIN");
            usernameInput.setHint("Username");
            passwordInput.setHint("Password");

        } else if(Utils.LimbaRomana) {
            loginButton.setText("LOGARE");
            usernameInput.setHint("Nume de utilizator");
            passwordInput.setHint("Parola");
        }
    }
}