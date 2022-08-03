package com.example.myapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class DBConnection {
    private static DBConnection dbc;
    private static FirebaseDatabase db;
    private static DatabaseReference myRef;
    private static DataSnapshot ds;
    public static final String TAG = null;
    private String text;

    private DBConnection() {
        db = FirebaseDatabase.getInstance("https://ppa3-4ffe8-default-rtdb.firebaseio.com/");
    }
    public static DBConnection newInstance() {
        if(dbc == null) {
            dbc = new DBConnection();
        }
        return dbc;
    }
    public void getValue(Player p) {
        //DatabaseReference myRef = null;
        String refName = p.getUsername();
        myRef = db.getReference(refName);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
              //  MainActivity.incercare.setText(myRef.getKey() + " " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public void setMyRef(String key) {
        myRef = db.getReference(key);
    }
    public void setRefValue(String txt) {
        myRef.setValue(txt);
    }
}
