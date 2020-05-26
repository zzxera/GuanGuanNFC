package com.example.guanguannfc.model.Helper;

public class HelperPush {
    private String autthor_name;
    private String title;
    private String summary;
    private String contents;
    private long created_time;

    public HelperPush(String autthor_name, String title, String summary, String contents, long created_time) {
        this.autthor_name = autthor_name;
        this.title = title;
        this.summary = summary;
        this.contents = contents;
        this.created_time = created_time;
    }

    public String getAutthor_name() {
        return autthor_name;
    }

    public void setAutthor_name(String autthor_name) {
        this.autthor_name = autthor_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }
}
