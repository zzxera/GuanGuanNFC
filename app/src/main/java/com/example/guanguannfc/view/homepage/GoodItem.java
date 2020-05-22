package com.example.guanguannfc.view.homepage;

public class GoodItem {
    private String goodName,goodNum;


    public GoodItem(String[] str){
        this.goodName = str[0];
        this.goodNum=str[1];

    }


    public String getGoodName(){
        return goodName;
    }

    public String getGoodNum(){
        return goodNum;
    }
}
