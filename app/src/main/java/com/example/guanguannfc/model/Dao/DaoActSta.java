package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;
import com.example.guanguannfc.model.Helper.HelperActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 活动统计表操作类
 */
public class DaoActSta {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoActSta(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入整条数据：需要给定活动ID、开始活动时间、结束活动时间
    public void insert(long act_ID,long start_time,long end_time){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.ActSta.TABLE_NAME+ "(act_ID,start_time,end_time,created_time,updated_time) values(?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_ID,start_time,end_time,currentTime,currentTime});
        db.close();

    }
    //根据活动ID删除和该活动所有时间记录：需给定活动ID
    public void delete(long act_ID){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.ActSta.TABLE_NAME + " where act_ID=?";
        db.execSQL(sql,new Object[]{act_ID});
        db.close();

    }

    //查询某用户任意大类活动总时间: 用户名、活动大类名、开始日期时间戳、结束日期时间戳
    public long queryActType(String user_name,String act_type,long begin,long end ){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select sum(end_time-start_time) from  Act_Sta inner join Activity" +
                " on Activity._id=Act_Sta.act_ID " +
                " inner join User_Info " +
                " on User_Info._id=Activity.user_ID " +
                " where Activity.type_ID=( select _id from Activity_Type where act_type=?) and user_name=? and start_time>? and end_time<?";

        Cursor cursor=db.rawQuery(sql,new String[]{act_type,user_name, String.valueOf(begin), String.valueOf(end)});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                return cursor.getLong(cursor.getColumnIndex("sum(end_time-start_time)"));
            }
            return -1;
        }else {
            return -1;
        }
    }

    //返回一个活动集合（时间从长到短），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByLengthDesc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time-start_time desc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（时间从短到长），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByLengthAsc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time-start_time asc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（最新活动在前），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByTimeDesc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time desc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（最新活动在后），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByTimeAsc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time asc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }







}
