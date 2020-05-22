package com.example.guanguannfc.model.Helper;

public class HelperFriend {
    private String user_name;
    private int level;

    public HelperFriend(String user_name, int level) {
        this.user_name = user_name;
        this.level = level;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
