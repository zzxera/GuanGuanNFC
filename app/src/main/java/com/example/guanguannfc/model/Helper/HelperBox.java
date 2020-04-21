package com.example.guanguannfc.model.Helper;

public class HelperBox {
    //盒子名称
    private String name;

    //盒子位置描述
    private String position;

    public HelperBox(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "[盒子名称:"+name+","+"盒子位置描述:"+position+"]";
    }
}
