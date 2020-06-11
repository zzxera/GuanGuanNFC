package com.example.guanguannfc.model.Helper;

public class HelperFriendAct {
    private String friend_name;
    private int level;
    private String activity_type;
    private long begin_time;
    private long end_time;
    private long len_time;
    private String moment_text;
    private long shared_time;

    public HelperFriendAct(String friend_name, int level, String activity_type, long begin_time, long end_time, long len_time, String moment_text, long shared_time) {
        this.friend_name = friend_name;
        this.level = level;
        this.activity_type = activity_type;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.len_time = len_time;
        this.moment_text = moment_text;
        this.shared_time = shared_time;
    }

    public long getShared_time() {
        return shared_time;
    }

    public void setShared_time(long shared_time) {
        this.shared_time = shared_time;
    }

    public String getFriend_name() {
        return friend_name;
    }

    public void setFriend_name(String friend_name) {
        this.friend_name = friend_name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public long getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(long begin_time) {
        this.begin_time = begin_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public long getLen_time() {
        return len_time;
    }

    public void setLen_time(long len_time) {
        this.len_time = len_time;
    }

    public String getMoment_text() {
        return moment_text;
    }

    public void setMoment_text(String moment_text) {
        this.moment_text = moment_text;
    }

    @Override
    public String toString() {
        return "HelperFriendAct{" +
                "friend_name='" + friend_name + '\'' +
                ", level=" + level +
                ", activity_type='" + activity_type + '\'' +
                ", begin_time=" + begin_time +
                ", end_time=" + end_time +
                ", len_time=" + len_time +
                ", moment_text='" + moment_text + '\'' +
                '}';
    }
}
