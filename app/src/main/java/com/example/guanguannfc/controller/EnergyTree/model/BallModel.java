package com.example.guanguannfc.controller.EnergyTree.model;

public class BallModel {
    private String content;
    private String value;
    private int num;

    public BallModel(String content, String value, int num) {
        this.content = content;
        this.value = value;
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public String getValue() {
        return value;
    }
    public int getID() {
        return num;
    }
}
