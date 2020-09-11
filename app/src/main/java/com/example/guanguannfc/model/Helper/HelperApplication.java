package com.example.guanguannfc.model.Helper;

public class HelperApplication {
    String user_name;  //申请人
    String content;    //备注信息
    long created_time;          //申请时间

    public HelperApplication() {
    }

    public HelperApplication(String user_name, String content, long created_time) {
        this.user_name = user_name;
        this.content = content;
        this.created_time = created_time;
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

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "HelperApplication{" +
                "user_name='" + user_name + '\'' +
                ", content='" + content + '\'' +
                ", created_time=" + created_time +
                '}';
    }
}
