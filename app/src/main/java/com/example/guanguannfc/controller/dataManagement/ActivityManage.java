package com.example.guanguannfc.controller.dataManagement;

import android.content.Context;
import com.example.guanguannfc.model.Dao.DaoActivityType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityManage {
//张浦鑫的方法
    private String username;
    private Context context;

    DaoActivityType daoActivityType;

    public ActivityManage(String username, Context context){
        this.username = username;
        this.context = context;
        daoActivityType = new DaoActivityType(context);
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
        String array[] = new String[list.size()];
        for (int i = 0; i< list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }
}
