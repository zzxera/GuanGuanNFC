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
    public void insert(String act_type){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.ActivityType.TABLE_NAME+ "(act_type,created_time,updated_time) values(?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_type,currentTime,currentTime});
        db.close();

    }
    //删除一个活动大类:需给定活动大类名称
    public void delete(String act_type){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.ActivityType.TABLE_NAME + " where act_type=?";
        db.execSQL(sql,new Object[]{act_type});
        db.close();

    }
    //更新一个活动大类：需给定原来的大类名称、现在的大类名称
    public void update(String act_oldType,String act_newType){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.ActivityType.TABLE_NAME + " set act_type=? , updated_time=? where act_type=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_newType,currentTime,act_oldType});
        db.close();

    }
    //根据大类名称查询id：需给定大类活动名称
    public int query(String act_type){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select _id from " + GuanContract.ActivityType.TABLE_NAME + " where act_type=?";
        Cursor cursor=db.rawQuery(sql,new String[]{act_type});
        if(cursor.getCount()!=0){
            return cursor.getInt(0);
        }else {
            return 0;
        }
    }

}
