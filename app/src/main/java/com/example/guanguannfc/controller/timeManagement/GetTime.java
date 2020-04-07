package com.example.guanguannfc.controller.timeManagement;

import com.example.guanguannfc.view.data.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

//获取本地时间
public class GetTime {
    java.util.Date currentdata = new java.util.Date();//当前时间

    Timestamp now = new Timestamp(System.currentTimeMillis());//系统当前时间

    public static String getnowDataTime(){
        SimpleDateFormat s_format = new SimpleDateFormat("yyyyMMddhhmmss");//年月日小时分秒
        return s_format.format(new Data());
    }

    public static  String getNowTime(){
        SimpleDateFormat s_format = new SimpleDateFormat("HH:mm:ss");//小时：分：秒
        return s_format.format(new Data());
    }
}



