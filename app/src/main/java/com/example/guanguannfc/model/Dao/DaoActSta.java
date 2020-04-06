package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 活动统计表操作类
 */
public class DaoActSta {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoActSta(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入整条数据：需要给定活动ID、开始活动时间、结束活动时间
    public void insert(String act_ID,String start_time,String end_time){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.ActSta.TABLE_NAME+ "(act_ID,start_time,end_time,created_time,updated_time) values(?,?,?,?,?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        db.execSQL(sql,new Object[]{act_ID,start_time,end_time,currentTime,currentTime});
        db.close();

    }
    //根据活动ID删除和该活动所有时间记录：需给定活动ID
    public void delete(String act_ID){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.ActSta.TABLE_NAME + " where act_ID=?";
        db.execSQL(sql,new Object[]{act_ID});
        db.close();

    }


}
