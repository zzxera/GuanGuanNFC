package com.example.guanguannfc.controller.dataManagement;

import android.content.Intent;
import android.util.Log;

public class ThingManage {

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
