package com.example.guanguannfc.model.Helper;

public class HelperUserInfo {
    private int id;
    private String user_name;
    private String password;
    private int active_day;
    private String last_act;
    private int rank;
    private int is_studied;
    private long created_time;
    private long updated_time;

    public HelperUserInfo() {
    }

    public HelperUserInfo(int id, String user_name, String password, int active_day, String last_act, int rank, int is_studied, long created_time, long updateed_time) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.active_day = active_day;
        this.last_act = last_act;
        this.rank = rank;
        this.is_studied = is_studied;
        this.created_time = created_time;
        this.updated_time = updateed_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive_day() {
        return active_day;
    }

    public void setActive_day(int active_day) {
        this.active_day = active_day;
    }

    public String getLast_act() {
        return last_act;
    }

    public void setLast_act(String last_act) {
        this.last_act = last_act;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getIs_studied() {
        return is_studied;
    }

    public void setIs_studied(int is_studied) {
        this.is_studied = is_studied;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public long getUpdateed_time() {
        return updated_time;
    }

    public void setUpdateed_time(long updateed_time) {
        this.updated_time = updateed_time;
    }

    @Override
    public String toString() {
        return "HelperUserInfo{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", active_day=" + active_day +
                ", last_act='" + last_act + '\'' +
                ", rank=" + rank +
                ", is_studied=" + is_studied +
                ", created_time=" + created_time +
                ", updated_time=" + updated_time +
                '}';
    }
}
