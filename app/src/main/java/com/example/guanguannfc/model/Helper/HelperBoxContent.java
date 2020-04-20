package com.example.guanguannfc.model.Helper;

public class HelperBoxContent {
    //物品名称
    private String name;
    //物品数量
    private int num;

    public HelperBoxContent(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "[物品名称:"+name+","+"物品数量:"+num+"]";
    }
}
