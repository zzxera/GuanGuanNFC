package com.example.guanguannfc.controller.dataManagement;

import android.content.Context;

import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.Dao.DaoActivity;
import com.example.guanguannfc.model.Dao.DaoActivityType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityManage {
//张浦鑫的方法
    private String username;
    private Context context;

    DaoActivityType daoActivityType;
    DaoActivity daoActivity;
    DaoActSta daoActSta;

    public ActivityManage(String username, Context context){
        this.username = username;
        this.context = context;
        daoActivityType = new DaoActivityType(context);
        daoActivity = new DaoActivity(context);
    }

    public String[] getBigActivity(Context context){
        ArrayList<String> list = daoActivityType.queryAllType();
        String array[] = new String[list.size()];
        for (int i = 0; i< list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }

    public String[] getSmallActivity(String bigActivity){
        HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
        hashMap = daoActivityType.queryTypeAndActivity(username);
        ArrayList<String> list = hashMap.get(bigActivity);
        if(list == null) return null;

        String array[] = new String[list.size()];//更新小类活动名称string[list.size()];

        for (int i = 0; i< list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }


    //根据NFC标签字符串查询活动
    public String queryActivityByNFC(String nfcNumber){
        return daoActivity.queryActivityByNFC(nfcNumber);
    }


    //判断小类活动名称是否存在
    public boolean isSmallActivityExist(String smallActivityName){
        return daoActivity.query(username, smallActivityName);
    }

    //添加小类活动
    public boolean addSmallActivity(String NFCNumber, String bigActivityName, String smallActivityName){
        return daoActivity.insert(username, NFCNumber, bigActivityName, smallActivityName);
    }

    //删除小类活动
    public boolean deletSmallActivity(String smallActivityName){
        if (isSmallActivityExist(smallActivityName)){
            return daoActivity.delete(username, smallActivityName);
        }else {
            return false;
        }
    }

    //插入（添加）一条活动记录
    public boolean addActivityMessage(String smallActivityName, long start_time, long end_time){
        return daoActSta.insert(username, smallActivityName, start_time, end_time);
    }

    public void updataSmallActivity(String oldName,String newName){
        daoActivity.update(username, oldName, newName);
    }
}
