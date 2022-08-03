package com.example.myapplication;

import android.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

// Clasa utilitara care contine doar campuri si metode statice, vizibile din tot restul aplicatiei
// fara a creea o instanta a acesteia, necesara pentru salvarea informatiilor la navigarea dintr-un ecran in altul
public class Utils {
    public static MediaPlayer mediaPlayer;
    public static boolean MainActivityTheme;
    public static boolean BackFromSettings;
    public static boolean BackFromSettings2;
    public static boolean EnglishLanguage;
    public static boolean LimbaRomana;
    // Colectiile de tip lista unde sunt salvate cuvintele pentru fiecare nivel
    public static ArrayList<CharSequence> easyWords;
    public static ArrayList<CharSequence> mediumWords;
    public static ArrayList<CharSequence> hardWords;
    public static boolean push_stop;
    public static boolean soundtrack;
    public static boolean notAppStart;
    // Instanta statica de Player care ramane aceeasi pe tot parcursul jocului, pana la delogare
    public static Player player;
    public static void setRomana() {
        Utils.LimbaRomana = true;
        Utils.EnglishLanguage = false;
    }
    public static void setEngleza() {
        Utils.LimbaRomana = false;
        Utils.EnglishLanguage = true;
    }
    // Metoda care verifica nivelul jucatorului curent si il deblocheaza pe acesta si pe cele inferioare lui
    public static void unlockLevels() {
        switch(player.getLevel()) {
            case "easy_1" : break;
            case "easy_2" : easy_1.checkPrag = true;
                            break;
            case "easy_3" : easy_1.checkPrag = true;
                            easy_2.checkPrag = true;
                            break;
            case "medium_1" : easy_1.checkPrag = true;
                              easy_2.checkPrag = true;
                              easy_3.checkPrag = true;
                              break;
            case "medium_2" : easy_1.checkPrag = true;
                              easy_2.checkPrag = true;
                              easy_3.checkPrag = true;
                              medium_1.checkPrag = true;
                              break;
            case "medium_3" : easy_1.checkPrag = true;
                              easy_2.checkPrag = true;
                              easy_3.checkPrag = true;
                              medium_1.checkPrag = true;
                              medium_2.checkPrag = true;
                              break;
            case "hard_1" :   easy_1.checkPrag = true;
                              easy_2.checkPrag = true;
                              easy_3.checkPrag = true;
                              medium_1.checkPrag = true;
                              medium_2.checkPrag = true;
                              medium_3.checkPrag = true;
                              break;
            case "hard_2" :  easy_1.checkPrag = true;
                             easy_2.checkPrag = true;
                             easy_3.checkPrag = true;
                             medium_1.checkPrag = true;
                             medium_2.checkPrag = true;
                             medium_3.checkPrag = true;
                             hard_1.checkPrag = true;
                             break;
            case "hard_3" : easy_1.checkPrag = true;
                            easy_2.checkPrag = true;
                            easy_3.checkPrag = true;
                            medium_1.checkPrag = true;
                            medium_2.checkPrag = true;
                            medium_3.checkPrag = true;
                            hard_1.checkPrag = true;
                            hard_2.checkPrag = true;
                            break;
            case "champion" : easy_1.checkPrag = true;
                              easy_2.checkPrag = true;
                              easy_3.checkPrag = true;
                              medium_1.checkPrag = true;
                              medium_2.checkPrag = true;
                              medium_3.checkPrag = true;
                              hard_1.checkPrag = true;
                              hard_2.checkPrag = true;
                              hard_3.checkPrag = true;
                              break;

        }
    }
    // Metoda care blocheaza toate nivelele
    public static void lockLevels() {
        easy_2.checkPrag = false;
        easy_3.checkPrag = false;
        medium_1.checkPrag = false;
        medium_2.checkPrag = false;
        medium_3.checkPrag = false;
        hard_1.checkPrag = false;
        hard_2.checkPrag = false;
        hard_3.checkPrag = false;
    }
}
