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
    //向表中插入一整条数据
    public void insert(Integer user_ID,String nfc,Integer type_ID,String act_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.Activity.TABLE_NAME+ "(user_ID,nfc,type_ID,act_name,created_time,updated_time) values(?,?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_ID,nfc,type_ID,act_name,currentTime,currentTime});
        db.close();

    }
    //根据活动名称删除整个活动：给定用户ID、要删除的活动名称
    public void delete(Integer user_ID,String act_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.Activity.TABLE_NAME + " where act_name=? and user_ID=?";
        db.execSQL(sql,new Object[]{user_ID,act_name});
        db.close();

    }
    //更新活动名字：需要给定用户ID，原来的活动名字、更新后的活动名字
    public void update(Integer user_ID,String act_oldName,String act_newName){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.Activity.TABLE_NAME + " set act_name=? , updated_time=? where act_name=? and user_ID=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_newName,currentTime,act_oldName,user_ID});
        db.close();

    }
    //活动名查重，已包含活动名返回true,反之返回false:给定用户ID和活动名称
    public boolean query(Integer user_ID,String act_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + GuanContract.Activity.TABLE_NAME + " where user_ID=? and act_name=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_ID),act_name});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    //查询表中是否包含给定nfc，包含返回true，不包含返回false：需要给定nfc
    public boolean query(String nfc){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + GuanContract.Activity.TABLE_NAME + " where nfc=?";
        Cursor cursor=db.rawQuery(sql,new String[]{nfc});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }


}
