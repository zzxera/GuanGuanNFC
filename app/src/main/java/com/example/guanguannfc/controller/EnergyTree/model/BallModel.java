package com.example.guanguannfc.controller.EnergyTree.model;

public class BallModel {
    private String content;
    private String value;
    private int num;
    private int color;

    public BallModel(String content, String value, int num, int color) {
        this.content = content;
        this.value = value;
        this.num = num;
        this.color = color;
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

    public int getColor(){
        return color;
    }
}
