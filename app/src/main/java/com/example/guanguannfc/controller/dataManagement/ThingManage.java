package com.example.guanguannfc.controller.dataManagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.guanguannfc.model.Dao.DaoBox;
import com.example.guanguannfc.model.Dao.DaoActivityType;
import com.example.guanguannfc.model.Helper.HelperBox;
import com.example.guanguannfc.model.Helper.HelperBoxContent;

import java.util.ArrayList;
import java.util.HashMap;

public class ThingManage {
    private String username;
    private Context context;

    DaoBox daoBox = new DaoBox(context);

    public ThingManage(String username, Context context){
        this.username = username;
        this.context = context;
    }

    //张浦鑫需要的
    public String[][] thingAndNumberInBox(String boxName){
        HashMap<String, ArrayList<HelperBoxContent>> hashMap = new HashMap<String, ArrayList<HelperBoxContent>>();
        hashMap = daoBox.queryBoxAndContext(username);
        ArrayList<HelperBoxContent> list = hashMap.get(boxName);
        String[][] array = new String[2][list.size()];
        for (int i = 0; i< list.size(); i++){
            array[0][i] = list.get(i).getName();
        }for (int j = 0; j< list.size(); j++){
            array[1][j] = list.get(j).getNum() + "";
        }
        return array;
    }

    public String[][] boxAndPosition(){
        ArrayList<HelperBox> list = new ArrayList<HelperBox>();
        list = daoBox.queryAllBox(username);
        String[][] array = new String[2][list.size()];
        for (int i = 0; i< list.size(); i++){
            array[0][i] = list.get(i).getName();
        }for (int j = 0; j< list.size(); j++){
            array[1][j] = list.get(j).getPosition();
        }
        return array;
    }


    public boolean isBoxExist(String name){
        boolean boxExist = false;
        // TODO Auto-generated catch block
        if (true){//需要表进行存在判断
            boxExist = true;
        }
        return boxExist;
    }

    public boolean isActivityNameExist(String name){
        boolean ActivityNameExist = false;
        // TODO Auto-generated catch block
        if (true){//需要表进行存在判断
            ActivityNameExist = true;
        }
        return ActivityNameExist;
    }

    public void deleteBox(String name, String location){
        if (isBoxExist(name)){
            // TODO Auto-generated catch block
            //将数据传给数据库
        }else{
            Log.i("deleteBox","Box is not existed");
        }
    }

    public void addThings(String name, int number){
        // TODO Auto-generated catch block
        //将名称与数量信息传给数据库
    }

}
