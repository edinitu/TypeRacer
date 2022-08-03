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

public class hard_2 extends AppCompatActivity {
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
    private TextView gameResult;
    private int wpm;
    private double acc;
    private static final int prag = 2;
    public static boolean checkPrag;
    private ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_2);

        // Asiguram conexiunea la baza de date
        db = FirebaseDatabase.getInstance("https://ppa3-4ffe8-default-rtdb.firebaseio.com/");

        // Instructiuni care asociaza un widget unui tip de data declarat in clasa
        mTextField = findViewById(R.id.textView2);
        wordShowed = findViewById(R.id.textView3);
        typedWord = findViewById(R.id.inputText);
        correctWord = findViewById(R.id.textCuvinteCorecte);
        cl = findViewById(R.id.ha2);

        // Intructiuni care incarca in vectorul "hardWords" cuvintele din fisierul text cu majuscule intercalate aleator printre minuscule
        this.wordsUsed = new ArrayList<>();
        InputStream in;
        in = getResources().openRawResource(getResources().getIdentifier("hard_lvl2", "raw", getPackageName()));
        Scanner scan = new Scanner(in);
        Utils.hardWords = new ArrayList<>();
        int i = 0;
        while(scan.hasNextLine()) {
            String st = scan.nextLine();
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<st.length(); j++) {
                int k = r.nextInt(2);
                if(k == 0) sb.append(Character.toLowerCase(st.charAt(j)));
                else sb.append(Character.toUpperCase(st.charAt(j)));
            }
            Utils.hardWords.add(sb.toString());
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
                k = rand.nextInt(Utils.hardWords.size());
                c.start();
                wordsUsed.add(Utils.hardWords.get(k));
                wordShowed.setText(Utils.hardWords.get(k));
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

    // Metode folosite pentru a putea atribui o functie butonului back
    @Override
    public void onBackPressed() {
        if(!typedWord.hasFocus()) {
            Intent intent = new Intent(this, hard_screen.class);
            startActivity(intent);
        }
        typedWord.clearFocus();
    }

    // Metoda folosita pentru a verifica corectitudinea cuvintelor introduse
    public boolean verifyWord(CharSequence scris, CharSequence afisat) {
        if(scris.toString().equals(afisat.toString())) return true;
        return false;
    }

    // Metoda folosita pentru a trece la urmatorul cuvant, ales aleator din fisierul text de cuvinte
    public void changeWord() {
        boolean ok = true;
        int contor = 0;
        while(ok) {
            ok = false;
            k = rand.nextInt(Utils.hardWords.size());
            for (CharSequence s : this.wordsUsed) {
                if (Utils.hardWords.get(k)== s) {
                    ok = true;
                    contor ++;
                }
            }
            if(contor == Utils.hardWords.size()) {
                this.noMoreWords = true;
                break;
            }
        }
        if(this.noMoreWords) return;
        wordShowed.setText(Utils.hardWords.get(k));
        this.wordsUsed.add(Utils.hardWords.get(k));
    }

    // Metoda care inchide tastatura la expirarea timpului
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

        // seteam variabila focusable pe true pentru ca fereastra sa nu dispara daca apasam pe ecran din greseala pe langa ea
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        textScore = popupView.findViewById(R.id.textScor);
        textWPM = popupView.findViewById(R.id.textWPM);
        textAccuracy = popupView.findViewById(R.id.textAccuracy);
        gameResult = popupView.findViewById((R.id.textResult));

        nextLevelButton = popupView.findViewById(R.id.buttonNextLv);
        tryAgainButton = popupView.findViewById(R.id.buttonTryAgain);
        seButton = popupView.findViewById(R.id.buttonSaveAndExit);

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

        // Functionalitatea de schimbare a limbii
        if(Utils.EnglishLanguage) {
            textScore.setText("Right words: " + this.noCorrect + " cuvinte");
            textWPM.setText("Words per minute: " + wpm);
            textAccuracy.setText("Accuracy: " + String.format("%.2f", acc) + "%");
            if(noCorrect >= prag) gameResult.setText("Congratulations! You unlocked the next level!");
            else if(noCorrect < prag) gameResult.setText("Bad luck! Try again!");
        }
        else
        if(Utils.LimbaRomana){
            textScore.setText("Ai scris corect: " + this.noCorrect + " cuvinte");
            textWPM.setText("Cuvinte pe minut: " + wpm);
            textAccuracy.setText("Acuratete: " + String.format("%.2f", acc) + "%");
            if(noCorrect >= prag) gameResult.setText("Felicitari! Ai trecut la urmatorul nivel!");
            else if(noCorrect < prag) gameResult.setText("Ghinion! Mai incearca o data!");
        }
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
    public void calcAcc() {
        this.acc = (double) noCorrect / noTotal * 100;
    }
    public void calcWPM() {
        this.wpm = noTotal * 2;
    }

    // La apasarea butonului nextLevel se verifica daca jucatorul a reusit sa scrie corect un anumit numar de cuvinte.
    // Daca da, deblocam urmatorul nivel si salvam aceasta informatie in baza de date, altfel setam variabila booleana
    // care verifica acest lucru pe false
    public void nextLv() {
        if(noCorrect >= prag) {
            checkPrag = true;
            DatabaseReference reference = db.getReference("Users");
            reference.child(Utils.player.getUsername()).child("level").setValue("hard_3");
            Utils.player.setLevel("hard_3");
            Utils.hardWords.clear();
            Intent intent = new Intent(this, hard_3.class);
            startActivity(intent);
        } else checkPrag = false;
    }
    // Metoda care restarteaza nivelul curent
    public void tryAgain() {
        Utils.hardWords.clear();
        Intent intent = new Intent(this, hard_2.class);
        startActivity(intent);
    }
    // Metoda care verifica daca nivelul a fost promovat, in caz afirmativ salveaza informatia in baza de date si
    // ne intoarce in ecranul easy_screen.
    public void saveAndExit() {
        if(noCorrect >= prag) {
            checkPrag = true;
            DatabaseReference reference = db.getReference("Users");
            reference.child(Utils.player.getUsername()).child("level").setValue("hard_3");
            Utils.player.setLevel("hard_3");
            Utils.hardWords.clear();
            Intent intent = new Intent(this, hard_screen.class);
            startActivity(intent);
        } else {
            checkPrag = false;
            Utils.hardWords.clear();
            Intent intent = new Intent(this, hard_screen.class);
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