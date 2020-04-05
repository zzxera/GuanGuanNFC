package com.example.guanguannfc.model;

import android.content.Context;

/**
 * 这是一个用于初始化数据库的类
 */
public class Initialization {
    public static void initialization(Context context){
        GuanSQLHelper mDataBaseHelper=new GuanSQLHelper(context);
        mDataBaseHelper.getWritableDatabase();
    }
}
