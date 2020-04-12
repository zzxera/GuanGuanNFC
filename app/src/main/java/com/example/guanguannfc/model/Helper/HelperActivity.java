package com.example.guanguannfc.model.Helper;

public class HelperActivity {
        private String activity_name;
        private long begin_time;
        private long end_time;
    public HelperActivity(String activity_name, long begin_time, long end_time, long len_time) {
        this.activity_name = activity_name;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.len_time = len_time;
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

    private long len_time;
}
