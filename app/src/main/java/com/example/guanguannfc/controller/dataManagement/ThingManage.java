package com.example.guanguannfc.controller.dataManagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.guanguannfc.model.Dao.DaoBox;
import com.example.guanguannfc.model.Dao.DaoActivityType;
import com.example.guanguannfc.model.Dao.DaoBoxContent;
import com.example.guanguannfc.model.Helper.HelperBox;
import com.example.guanguannfc.model.Helper.HelperBoxContent;

import java.util.ArrayList;
import java.util.HashMap;

public class ThingManage {
    private String username;
    private Context context;

    DaoBox daoBox;
    DaoBoxContent mDaoBoxContent;

    public ThingManage(String username, Context context){
        this.username = username;
        this.context = context;
        daoBox = new DaoBox(context);
        mDaoBoxContent = new DaoBoxContent(context);
    }

    //张浦鑫需要的
    public String[][] thingAndNumberInBox(String boxName){
        HashMap<String, ArrayList<HelperBoxContent>> hashMap = new HashMap<String, ArrayList<HelperBoxContent>>();
        hashMap = daoBox.queryBoxAndContext(username);
        ArrayList<HelperBoxContent> list = hashMap.get(boxName);
        if(list==null){
            return null;
        }
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
        if (list.size() == 0) return null;
        String[][] array = new String[2][list.size()];
        for (int i = 0; i< list.size(); i++){
            array[0][i] = list.get(i).getName();
        }for (int j = 0; j< list.size(); j++){
            array[1][j] = list.get(j).getPosition();
        }
        return array;
    }


    public boolean isBoxExist(String boxName){
        return daoBox.query(username, boxName);
    }

    public boolean isThingExist(String box_name, String thing_name){
        return mDaoBoxContent.loadQuery(username, box_name, thing_name);
    }

    public boolean addBox(String box_name, String thing_name, int thing_number){
        return mDaoBoxContent.insert(username, box_name, thing_name, thing_number);
    }

    public boolean deleteBox(String boxName){
        if (isBoxExist(boxName)){
            return daoBox.delete(username,boxName);
        }else{
            return false;
        }
    }

    public void addThings(String boxName, String thingName, int number){
        //将名称与数量信息传给数据库
        mDaoBoxContent.insert(username,boxName,thingName,number);
    }

    public void deleteThings(String boxName, String thingName){
        //将名称与数量信息传给数据库（删除物品）
        mDaoBoxContent.delete(username,boxName,thingName);
    }

    //更新物品数量
    public void updataThings(String box_name, String thing_name, Integer thing_num){
        mDaoBoxContent.update(username, box_name, thing_name, thing_num);
    }

}
