package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 活动一级种类表操作类
 */
public class DaoActivityType {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoActivityType(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一个活动大类:需给定活动大类名称
    public void insert(String user_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.ActivityType.TABLE_NAME+ "(user_name,created_time,updated_time) values(?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_name,currentTime,currentTime});
        db.close();

    }
    //删除一个活动大类:需给定活动大类名称
    public void delete(String user_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.ActivityType.TABLE_NAME + " where user_name=?";
        db.execSQL(sql,new Object[]{user_name});
        db.close();

    }
    //更新一个活动大类：需给定原来的大类名称、现在的大类名称
    public void update(String user_oldName,String user_newName){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.ActivityType.TABLE_NAME + " set user_name=? , updated_time=? where user_name=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_newName,currentTime,user_oldName});
        db.close();

    }
    //根据大类名称查询id：需给定大类活动名称
    public int query(String user_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select _id from " + GuanContract.ActivityType.TABLE_NAME + " where user_name=?";
        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            return cursor.getInt(0);
        }else {
            return 0;
        }
    }

}
