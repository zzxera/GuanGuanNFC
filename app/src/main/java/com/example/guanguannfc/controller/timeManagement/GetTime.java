package com.example.guanguannfc.controller.timeManagement;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;
//获取本地时间、系统时间为基准
public class GetTime {

    //获取当前时间并显示为字符串形式 12：24：23
    public static String getNowTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date curData = new Date(System.currentTimeMillis());
        String string = format.format(curData);
        return string;
    }

    //获取起始时间并返回长整型（long）
    public static long getStartTime(){
        long startTime = System.currentTimeMillis();
        return startTime;
    }

    //将获取的长整型起始时间与当前的时间进行计算并返回字符串型持续时间 12：24：23
    public static String countTime(long startTime){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        long endTime = System.currentTimeMillis();
        String contineTime = format.format(startTime - endTime);
        return contineTime;
    }

    //将字符串转换为时间戳，pattern为日期格式
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    //Calendar应用实例
//    public static void main(String[] args) {
//        // 其日历字段已由当前日期和时间初始化：
//        Calendar rightNow = Calendar.getInstance(); // 子类对象
//        // 获取年
//        int year = rightNow.get(Calendar.YEAR);
//        // 获取月
//        int month = rightNow.get(Calendar.MONTH);
//        // 获取日
//        int date = rightNow.get(Calendar.DATE);
//        // 获取几点
//        int hour=rightNow.get(Calendar.HOUR_OF_DAY);
//        // 获取上午下午
//        int moa=rightNow.get(Calendar.AM_PM);
//        if(moa==1)
//            System.out.println("下午");
//        else
//            System.out.println("上午");
//        System.out.println(year + "年" + (month + 1) + "月" + date + "日"+hour+"时");
//        rightNow.add(Calendar.YEAR,5);
//        rightNow.add(Calendar.DATE, -10);
//        int year1 = rightNow.get(Calendar.YEAR);
//        int date1 = rightNow.get(Calendar.DATE);
//        System.out.println(year1 + "年" + (month + 1) + "月" + date1 + "日"+hour+"时");
//    }
}



