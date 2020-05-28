package com.example.guanguannfc.model.Helper;

public class HelperApplication {
    String user_name;  //申请人
    String content;    //备注信息
    long time;          //申请时间

    public HelperApplication(String user_name, String content, long time) {
        this.user_name = user_name;
        this.content = content;
        this.time = time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HelperApplication{" +
                "user_name='" + user_name + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
