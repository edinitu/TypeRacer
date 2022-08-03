package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

public class music_screen extends AppCompatActivity {

    private Button song1;
    private Button song2;
    private Button song3;
    private Button song4;
    private Button song5;
    private Button song6;
    private Button song7;
    private Button song8;
    private Button song9;
    private Button song10;
    private Button song11;
    private Button song12;
    private Button song13;
    private Button song14;
    private Button song15;
    private Button song16;
    private Button song17;
    private Button song18;
    private Button song19;
    private Button song20;
    private Button song21;
    private Button song22;
    private Button song23;
    private RelativeLayout cl;
    private TextView listTxt;

    private Switch soundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_screen);

        song1 = findViewById(R.id.buton_muzica);
        song2 = findViewById(R.id.butonmuzica2);
        song3 = findViewById(R.id.butonmuzica3);
        song4 = findViewById(R.id.butonmuzica4);
        song5 = findViewById(R.id.butonmuzica5);
        song6 = findViewById(R.id.butonmuzica6);
        song7 = findViewById(R.id.butonmuzica7);
        song8 = findViewById(R.id.butonmuzica8);
        song9 = findViewById(R.id.butonmuzica9);
        song10 = findViewById(R.id.butonmuzica10);
        song11 = findViewById(R.id.butonmuzica11);
        song12 = findViewById(R.id.butonmuzica12);
        song13 = findViewById(R.id.butonmuzica13);
        song14 = findViewById(R.id.butonmuzica14);
        song15 = findViewById(R.id.butonmuzica15);
        song16 = findViewById(R.id.butonmuzica16);
        song17 = findViewById(R.id.butonmuzica17);
        song18 = findViewById(R.id.butonmuzica18);
        song19 = findViewById(R.id.butonmuzica19);
        song20 = findViewById(R.id.butonmuzica20);
        song21 = findViewById(R.id.butonmuzica21);
        song22 = findViewById(R.id.butonmuzica22);
        song23 = findViewById(R.id.butonmuzica23);
        cl = findViewById(R.id.music);
        listTxt = findViewById(R.id.textLista);

        // Intructiuni care realizeaza un switch on/off pentru muzica
        soundSwitch = findViewById(R.id.switch2);
        if(Utils.soundtrack) soundSwitch.setChecked(true);

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    melodie1();
                    Utils.soundtrack = true;
                    isChecked = true;
                } else {
                    Utils.soundtrack = false;
                    isChecked = false;
                    stop();
                }
                buttonView.setChecked(isChecked);
            }
        });

            song1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(soundSwitch.isChecked()) melodie1();
                }
            });
            song2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(soundSwitch.isChecked()) melodie2();
                }
            });
            song3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(soundSwitch.isChecked()) melodie3();
                }
             });
        song4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie4();
            }
        });
        song5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie5();
            }
        });
        song6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie6();
            }
        });
        song7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie7();
            }
        });
        song8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie8();
            }
        });
        song9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie9();
            }
        });
        song10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie10();
            }
        });
        song11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie11();
            }
        });
        song12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie12();
            }
        });
        song13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie13();
            }
        });
        song14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie14();
            }
        });
        song15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie15();
            }
        });
        song16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie16();
            }
        });
        song17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie17();
            }
        });
        song18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie18();
            }
        });
        song19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie19();
            }
        });
        song20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie20();
            }
        });
        song21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie21();
            }
        });
        song22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie22();
            }
        });
        song23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soundSwitch.isChecked()) melodie23();
            }
        });
        changeLanguage();
        if(Utils.MainActivityTheme) darkTheme();
        else lightTheme();
    }
    public void changeLanguage(){
        if(Utils.EnglishLanguage){
            soundSwitch.setText("Background Music");
            listTxt.setText("Songs list:");
        }
        else
        if(Utils.LimbaRomana){
            soundSwitch.setText("Muzica de fundal");
            listTxt.setText("Lista melodii:");
        }
    }
    // Metode care schimba fundalul si culorile textelor pentru optiunea de mod intunecat
    public void lightTheme() {
        cl.setBackgroundColor(0xFFFFFFFF);
        soundSwitch.setTextColor(this.getResources().getColor(R.color.black));
        listTxt.setTextColor(this.getResources().getColor(R.color.black));
    }
    public void darkTheme() {
        soundSwitch.setTextColor(this.getResources().getColor(R.color.white));
        listTxt.setTextColor(this.getResources().getColor(R.color.white));
        cl.setBackgroundColor(this.getResources().getColor(R.color.black));
    }

    // Intructiuni folosite pentru a putea asculta melodiile
    void melodie1() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie2() {
        if(Utils.push_stop)  Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this,R.raw.melodie2);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie3() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.melodie3);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie4() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie4);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie5() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie5);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie6() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie6);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie7() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie7);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie8() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie8);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie9() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie9);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie10() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie10);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie11() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie11);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie12() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie12);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie13() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie13);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }void melodie14() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie14);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie15() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie15);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie16() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie16);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie17() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie17);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie18() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie18);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie19() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie19);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie20() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie20);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie21() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie21);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie22() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie22);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }
    void melodie23() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
        Utils.mediaPlayer = MediaPlayer.create(this, R.raw.melodie23);
        Utils.mediaPlayer.start();
        Utils.push_stop = true;
    }

    void stop() {
        if(Utils.push_stop) Utils.mediaPlayer.stop();
    }
}