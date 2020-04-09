package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 盒子内容表操作类
 */
public class DaoBoxContent {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoBoxContent(Context context) {
        mDataBaseHelper = new GuanSQLHelper(context);

    }

    //插入一条盒子物品数据：需要给定盒子ID、物品名称、物品数量
    public void insert(String box_ID, String thing_name, String thing_num) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        String sql = "insert into " + GuanContract.BoxContent.TABLE_NAME + "(box_ID,thing_name,thing_num,created_time,updated_time) values(?,?,?,?,?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        db.execSQL(sql, new Object[]{box_ID, thing_name, thing_num, currentTime, currentTime});
        db.close();

    }

    //删除某个盒子的所有物品：需要给定盒子ID
    public void delete(String box_ID) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.BoxContent.TABLE_NAME + " where box_ID=?";
        db.execSQL(sql, new Object[]{box_ID});
        db.close();
    }

    //删除某个盒子中某个物品：需要给定盒子ID、物品名称
    public void delete(String box_ID, String thing_name) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.BoxContent.TABLE_NAME + " where box_ID=? and thing_name=?";
        db.execSQL(sql, new Object[]{box_ID, thing_name});
        db.close();
    }

    //更新某个盒子中某个物品的数量：需要给定盒子的ID、物品的名称、更新后物品的数量
    public void update(String box_ID, String thing_name, String thing_num) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        String sql = "update " + GuanContract.BoxContent.TABLE_NAME + " set thing_num=? , updated_time=? where box_ID=? and thing_name=?";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        db.execSQL(sql, new Object[]{thing_num, currentTime, box_ID, thing_name});
        db.close();

    }

    //指定盒子内物品名称查重，存在返回true，反之false：需给定盒子ID、查重物品名称
    public boolean loadQuery(String box_ID, String thing_name) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        String sql = "select * from " + GuanContract.BoxContent.TABLE_NAME + " where box_ID=? and thing_name=?";
        Cursor cursor = db.rawQuery(sql, new String[]{box_ID, thing_name});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }
}






