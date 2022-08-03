package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class medium_2 extends AppCompatActivity {
    private FirebaseDatabase db;
    private TextView mTextField;
    private Button startButton;
    private Button tryAgainButton;
    private Button nextLevelButton;
    private Button seButton;
    private TextView wordShowed;
    private TextView correctWord;
    private TextInputEditText typedWord;
    private int noCorrect;
    private int noTotal;
    private int k = 0;
    private boolean noMoreWords = false;
    private List<CharSequence> wordsUsed;
    private Random rand = new Random();
    private boolean timeUp;
    private TextView textScore;
    private TextView textWPM;
    private TextView textAccuracy;
    private int wpm;
    private double acc;
    private static final int prag = 11;
    public static boolean checkPrag;
    public ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_2);

        db = FirebaseDatabase.getInstance("https://ppa3-4ffe8-default-rtdb.firebaseio.com/");

        // Instructiuni care asociaza un widget unui tip de data declarat in clasa
        mTextField = findViewById(R.id.textView2);
        wordShowed = findViewById(R.id.textView3);
        typedWord = findViewById(R.id.inputText);
        correctWord = findViewById(R.id.textCuvinteCorecte);
        cl = findViewById(R.id.me2);

        // Intructiuni care incarca in vectorul "wordsUsed" cuvintele din fisierul text
        this.wordsUsed = new ArrayList<>();
        InputStream in;
        in = getResources().openRawResource(getResources().getIdentifier("medium_lvl2", "raw", getPackageName()));
        Scanner scan = new Scanner(in);
        Utils.mediumWords = new ArrayList<>();
        int i = 0;
        while(scan.hasNextLine()) {
            String st = scan.nextLine();
            st = st.toLowerCase();
            Utils.mediumWords.add(st);
            i++;
        }

        noCorrect = 0;

        // Intructiuni folosite pentru a realiza un cronometru cu durata de 30 secunde
        CountDownTimer c = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                if(Utils.EnglishLanguage)
                    mTextField.setText("Seconds left: " + millisUntilFinished / 1000);
                else
                if(Utils.LimbaRomana)
                    mTextField.setText("Secunde ramase: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if(Utils.EnglishLanguage)
                    mTextField.setText("Time is up!!");
                else
                if(Utils.LimbaRomana)
                    mTextField.setText("Timpul s-a scurs!!");
                timeUp = true;
                finishLevel();
                calcAcc();
                calcWPM();
                correctWord.setText("");
                displayPopUp();
            }
        };

        // Metode folosite pentru a putea atribui o functie butonului de start
        startButton = findViewById(R.id.button5);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noMoreWords = false;
                wordsUsed.clear();
                k = rand.nextInt(Utils.mediumWords.size());
                c.start();
                wordsUsed.add(Utils.mediumWords.get(k));
                wordShowed.setText(Utils.mediumWords.get(k));
                noCorrect = 0;
                wpm = 0;
                noTotal = 0;
                if(Utils.EnglishLanguage)
                    correctWord.setText("Right words: " + noCorrect);
                else
                if(Utils.LimbaRomana)
                    correctWord.setText("Ai nimerit: " + noCorrect);
            }
        });

        // Metoda care permite introducerea textului
        typedWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                boolean wordOK = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT && !timeUp) {
                    wordOK = verifyWord(v.getText(), wordShowed.getText());
                    if(wordOK) {
                        noCorrect++;
                    }
                    v.setText("");
                    changeWord();
                    noTotal++;
                    if(noMoreWords) {
                        if (Utils.EnglishLanguage)
                            correctWord.setText("No more words!");
                        else
                        if (Utils.LimbaRomana)
                            correctWord.setText("Nu mai sunt cuvinte!");
                        handled = true;
                    }
                    else {
                        if (Utils.EnglishLanguage)
                            correctWord.setText("Right words: " + noCorrect);
                        else
                        if(Utils.LimbaRomana)
                            correctWord.setText("Ai nimerit: " + noCorrect);
                    }
                }
                return handled;
            }
        });
        changeLanguage();
        if(Utils.MainActivityTheme) darkTheme();
        else lightTheme();
    }

    // Metode folosite pentru a putea atribui o functie butonului
    @Override
    public void onBackPressed() {
        if(!typedWord.hasFocus()) {
            Intent intent = new Intent(this, medium_screen.class);
            startActivity(intent);
        }
        typedWord.clearFocus();
    }

    // Metoda folosita pentru a verifica corectitudinea cuvintelor introduse
    public boolean verifyWord(CharSequence scris, CharSequence afisat) {
        if(scris.toString().equals(afisat.toString())) return true;
        return false;
    }

    // Metoda folosita pentru a trece la urmatorul cuvant ales aleator din vectorul medium1_words
    public void changeWord() {
        boolean ok = true;
        int contor = 0;
        while(ok) {
            ok = false;
            k = rand.nextInt(Utils.mediumWords.size());
            for (CharSequence s : this.wordsUsed) {
                if (Utils.mediumWords.get(k)== s) {
                    ok = true;
                    contor ++;
                }
            }
            if(contor == Utils.mediumWords.size()) {
                this.noMoreWords = true;
                break;
            }
        }
        if(this.noMoreWords) return;
        wordShowed.setText(Utils.mediumWords.get(k));
        this.wordsUsed.add(Utils.mediumWords.get(k));
    }

    // Metoda care inchide tastatura
    public void finishLevel() {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(typedWord.getWindowToken(), 0);
        typedWord.clearFocus();
    }

    // Metoda care afiseaza o fereastra pop-up ce contine rezultatele obtinute
    public void displayPopUp() {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_results, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        textScore = popupView.findViewById(R.id.textScor);
        textWPM = popupView.findViewById(R.id.textWPM);
        textAccuracy = popupView.findViewById(R.id.textAccuracy);

        nextLevelButton = popupView.findViewById(R.id.buttonNextLv);
        tryAgainButton = popupView.findViewById(R.id.buttonTryAgain);
        seButton = popupView.findViewById(R.id.buttonSaveAndExit);

        // Butoane implementate in fereastra popUp care ajuta la navigarea intre nivele
        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextLv();
            }
        });
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAgain();
            }
        });
        seButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndExit();
            }
        });

        if(Utils.EnglishLanguage) {
            textScore.setText("Right words: " + this.noCorrect + " cuvinte");
            textWPM.setText("Words per minute: " + wpm);
            textAccuracy.setText("Accuracy: " + String.format("%.2f", acc) + "%");
        }
        else
        if(Utils.LimbaRomana){
            textScore.setText("Ai scris corect: " + this.noCorrect + " cuvinte");
            textWPM.setText("Cuvinte pe minut: " + wpm);
            textAccuracy.setText("Acuratete: " + String.format("%.2f", acc) + "%");

        }
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
    public void calcAcc() {
        this.acc = (double) noCorrect / noTotal * 100;
    }
    public void calcWPM() {
        this.wpm = noTotal * 2;
    }
    public void nextLv() {
        if(noCorrect >= prag) {
            Intent intent = new Intent(this, medium_3.class);
            Utils.mediumWords.clear();
            checkPrag = true;
            DatabaseReference reference = db.getReference("Users");
            reference.child(Utils.player.getUsername()).child("level").setValue("medium_3");
            Utils.player.setLevel("medium_3");
            startActivity(intent);
        } else checkPrag = false;
    }
    public void tryAgain() {
        Utils.mediumWords.clear();
        Intent intent = new Intent(this, medium_2.class);
        startActivity(intent);
    }
    public void saveAndExit() {
        if(noCorrect >= prag) {
            checkPrag = true;
            DatabaseReference reference = db.getReference("Users");
            reference.child(Utils.player.getUsername()).child("level").setValue("medium_3");
            Utils.player.setLevel("medium_3");
            Utils.mediumWords.clear();
            Intent intent = new Intent(this, medium_screen.class);
            startActivity(intent);
        } else {
            checkPrag = false;
            Utils.mediumWords.clear();
            Intent intent = new Intent(this, medium_screen.class);
            startActivity(intent);
        }
    }
    public static boolean isCheckPrag() {
        return checkPrag;
    }
    public void changeLanguage(){
        if(Utils.EnglishLanguage){
            // startButton.setText("Start");
            typedWord.setHint("Type here");
        }
        else
        if(Utils.LimbaRomana){
            // startButton.setText("Start");
            typedWord.setHint("Scrie aici");
        }
    }

    // Metode care schimba fundalul si culorile corespunzator temei curente
    public void darkTheme() {
        cl.setBackgroundResource(R.drawable.game_image_dm);
        wordShowed.setTextColor(this.getResources().getColor(R.color.white));
        correctWord.setTextColor(this.getResources().getColor(R.color.black));
        mTextField.setTextColor(this.getResources().getColor(R.color.white));
    }
    public void lightTheme() {
        cl.setBackgroundResource(R.drawable.game_image);
        wordShowed.setTextColor(this.getResources().getColor(R.color.purple_500));
        correctWord.setTextColor(this.getResources().getColor(R.color.black));
        mTextField.setTextColor(this.getResources().getColor(R.color.purple_500));
    }
}