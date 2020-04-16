package com.example.guanguannfc.model.Helper;

public class HelperActivityType {
    private String activity_type;
    private long len_time;

    public HelperActivityType(String activity_type, long len_time) {
        this.activity_type = activity_type;
        this.len_time = len_time;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public long getLen_time() {
        return len_time;
    }

    public void setLen_time(long len_time) {
        this.len_time = len_time;
    }

    @Override
    public String toString() {
        return "activity_type"+this.activity_type+"\n"+
                "len_time"+this.len_time;
    }
}
