package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signup_screen extends AppCompatActivity {
    private Button signupButton;
    private TextInputEditText signupUsername;
    private TextInputEditText signupPassword;
    private String username;
    private String password;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private TextView checkPassword;
    private CheckBox box;
    private int userEnters, passEnters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        this.box = findViewById(R.id.checkBox);
        this.checkPassword = findViewById(R.id.textView);
        this.signupButton = findViewById(R.id.signupButton);
        this.signupUsername = findViewById(R.id.signupUsername);
        this.signupPassword = findViewById(R.id.signUpPassword);
        this.userEnters = 0;
        this.passEnters = 0;

        db = FirebaseDatabase.getInstance("https://ppa3-4ffe8-default-rtdb.firebaseio.com/");

        this.signupButton.setOnClickListener(v -> {
            if(box.isChecked()) {
                if(passEnters >= 1 && userEnters >= 1) signUp();
                else checkPassword.setText("Apasati enter pentru a introduce\nnumele de utilizator");
            }
        });
        this.signupUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    loadUsername();
                    signup_screen.this.userEnters++;
                    handled = true;
                }
                return handled;
            }
        });
        this.signupPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    sendPassword();
                    loadPassword();
                    signup_screen.this.passEnters++;
                    handled = true;
                }
                return handled;
            }
        });
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                box.setChecked(false);
            }
        });
        changeLanguage();
    }

        // incarca username-ul in atributul String username
    public void loadUsername() {
        String str = signupUsername.getText().toString();
        dbRef = db.getReference("Users");
        Query checkUser = dbRef.orderByChild("username").equalTo(str);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    signup_screen.this.username = str;
                } else if(Utils.LimbaRomana) signup_screen.this.checkPassword.setText("Utilizatorul exista deja");
                else if(Utils.EnglishLanguage) signup_screen.this.checkPassword.setText("Username already exists");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(signupUsername.getWindowToken(), 0);
    }

    public void loadPassword() {
        String str = signupPassword.getText().toString();
        this.password = str;
        if(!this.box.isChecked()) {
            if(Utils.EnglishLanguage) checkPassword.setText("        The password must include minimum\n 7 characters, at least 1 uppercase and 1 digit");
            else if(Utils.LimbaRomana) checkPassword.setText("        Parola trebuie sa contina minim\n 7 caractere, cel putin o litera mare si o cifra");
        }
    }

        // Metoda care verifica daca a fost introdus un username si inregistreaza datele jucatorului in baza de date
    public void signUp() {
        if(!signupUsername.getText().toString().trim().equals("") || this.username == null) {
            Player p = new Player(this.username, this.password);
            dbRef = db.getReference("Users");
            dbRef.child(this.username).setValue(p);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if(Utils.LimbaRomana) checkPassword.setText("Introduceti un nume de utilizator!");
        else if(Utils.EnglishLanguage) checkPassword.setText("Enter a username!");
    }

    // Metoda folosita pentru a schimba limba in care sunt afisate criteriile pentru parola
    public void changeLanguage() {
        if (Utils.EnglishLanguage) {
            checkPassword.setText("        The password must include minimum\n 7 characters, at least 1 uppercase and 1 digit");
            signupUsername.setHint("Username");
            signupPassword.setHint("Password");
            signupButton.setText("SIGN UP");

        } else if (Utils.LimbaRomana) {
            checkPassword.setText("        Parola trebuie sa contina minim\n 7 caractere, cel putin o litera mare si o cifra");
            signupUsername.setHint("Nume de utilizator");
            signupPassword.setHint("Parola");
            signupButton.setText("Inregistrare");
        }
    }

    // Metoda care verifica daca parola indeplineste anumite conditii
    boolean checkPassword(Editable password) {
        char[] pass;
        pass = password.toString().toCharArray();
        boolean ok1 = false;
        boolean ok2 = false;
        boolean ok3 = false;

        if (pass.length >= 7) ok1 = true;
        for (char c : pass) {
            if (Character.isUpperCase(c)) ok2 = true;
            if (Character.isDigit(c)) ok3 = true;
        }
        if (ok1 && ok2 && ok3) return true;
        return false;
    }

    // Metoda care bifeaza Checkbox-ul daca parola indeplineste conditiile
    void sendPassword() {
        Editable e1 = signupPassword.getText();
        if (checkPassword(e1)) box.setChecked(true);
        else box.setChecked(false);
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(signupPassword.getWindowToken(), 0);
    }
}