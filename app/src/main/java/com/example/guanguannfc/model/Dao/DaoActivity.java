package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 活动表操作类
 */
public class DaoActivity {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoActivity(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //添加一个活动。需要：用户名、nfc、大类名称、活动名称
    public boolean insert(String user_name,String nfc,String act_type,String act_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into Activity (user_ID,nfc,type_ID,act_name,created_time,updated_time) values((select _id from User_Info where user_name=?),?,(select _id from Activity_Type where act_type=?),?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_name,nfc,act_type,act_name,currentTime,currentTime});
        db.close();
        return true;

    }

    //添加一个活动。需要：用户名、nfc、大类名称、活动名称
    public boolean insert(int user_ID,String nfc,int type_ID,String act_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into Activity (user_ID,nfc,type_ID,act_name,created_time,updated_time) values(?,?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_ID,nfc,type_ID,act_name,currentTime,currentTime});
        db.close();
        return true;

    }
    //根据活动名称删除整个活动：给定用户名、要删除的活动名称
    public boolean delete(String user_name,String act_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from Activity where act_name=? and user_ID=(select _id from User_Info where user_name=?)";
        db.execSQL(sql,new Object[]{act_name,user_name});
        String sql1 = "delete from act_sta where act_id=(select _id from activity where act_name=? and user_ID=(select _id from User_Info where user_name=?))";
        db.execSQL(sql1,new Object[]{act_name,user_name});
        db.close();
        return true;

    }
    //更新活动名字：需要给定用户ID，原来的活动名字、更新后的活动名字
    public boolean update(String username,String act_oldName,String act_newName){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.Activity.TABLE_NAME + " set act_name=? , updated_time=? where act_name=? and user_ID=(select _id from User_Info where user_name=?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_newName,currentTime,act_oldName,username});
        db.close();
        return true;
    }
    //活动名查重，已包含活动名返回true,反之返回false:给定用户名和活动名称
    public boolean query(String user_name,String act_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from Activity where user_ID=(select _id from User_Info where user_name=?) and act_name=?";
        Cursor cursor=db.rawQuery(sql,new String[]{user_name,act_name});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    //查询表中是否包含给定nfc，包含返回true，不包含返回false：需要给定nfc
    public boolean queryNFC(String nfc,String username){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + GuanContract.Activity.TABLE_NAME + " where nfc=? and user_id=(select _id from user_info where user_name=?";
        Cursor cursor=db.rawQuery(sql,new String[]{nfc,username});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }

    //根据nfc查询活动名称
    public String[] queryActivityByNFC(String nfc,String username){
        String[] activity = new String[3];
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select act_name from  Activity where nfc=? and user_id=(select _id from user_info where user_name=?)";
        Cursor cursor=db.rawQuery(sql,new String[]{nfc,username});
        while(cursor.moveToNext()){
            activity[0] = cursor.getString(0);
        }
        String sql1="select _id,act_type from Activity_type where _id=(select type_id from activity where nfc=? and user_id=(select _id from user_info where user_name=?))";
        Cursor cursor1=db.rawQuery(sql1,new String[]{nfc,username});
        while(cursor1.moveToNext()){
            activity[1] = cursor1.getInt(0)+"";
            activity[2] = cursor1.getString(1);
        }
        return activity;
    }


}
