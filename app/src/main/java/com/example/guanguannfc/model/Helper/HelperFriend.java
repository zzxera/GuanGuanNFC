package com.example.guanguannfc.model.Helper;

public class HelperFriend {
    private String user_name;
    private int active_day;

    public HelperFriend() {
    }

    public HelperFriend(String user_name, int active_day) {
        this.user_name = user_name;
        this.active_day = active_day;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getActive_day() {
        return active_day;
    }

    public void setActive_day(int active_day) {
        this.active_day = active_day;
    }

    @Override
    public String toString() {
        return "HelperFriend{" +
                "user_name='" + user_name + '\'' +
                ", active_day=" + active_day +
                '}';
    }
}
