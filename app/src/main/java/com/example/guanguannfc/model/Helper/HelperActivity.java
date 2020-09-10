package com.example.guanguannfc.model.Helper;

public class HelperActivity {
    private int id;
    private String activity_type;
    private String activity_name;
    private long begin_time;
    private long end_time;
    private long len_time;
    private int is_ranked;

    public HelperActivity(String activity_type, String activity_name, long begin_time, long end_time, long len_time, int is_ranked,int id) {
        this.id = id;
        this.activity_type = activity_type;
        this.activity_name = activity_name;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.len_time = len_time;
        this.is_ranked = is_ranked;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
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

    public int getIs_ranked() {
        return is_ranked;
    }

    public void setIs_ranked(int is_ranked) {
        this.is_ranked = is_ranked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
