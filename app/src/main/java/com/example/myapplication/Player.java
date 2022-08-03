package com.example.myapplication;

// clasa Player contine campurile si metodele necesare pentru a gestiona numele, parola si nivelul la care a ajuns un jucator
public class Player {
    private String username;
    private String password;
    private String level;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.level = "easy_1";
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}