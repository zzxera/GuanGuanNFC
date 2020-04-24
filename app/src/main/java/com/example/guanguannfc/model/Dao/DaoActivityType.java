package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 活动一级种类表操作类
 */
public class DaoActivityType {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoActivityType(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一个活动大类:需给定活动大类名称
    public boolean insert(String act_type){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.ActivityType.TABLE_NAME+ "(act_type,created_time,updated_time) values(?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_type,currentTime,currentTime});
        db.close();
        return true;

    }
    //删除一个活动大类:需给定活动大类名称
    public boolean delete(String act_type){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.ActivityType.TABLE_NAME + " where act_type=?";
        db.execSQL(sql,new Object[]{act_type});
        db.close();
        return true;

    }
    //更新一个活动大类：需给定原来的大类名称、现在的大类名称
    public boolean update(String act_oldType,String act_newType){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.ActivityType.TABLE_NAME + " set act_type=? , updated_time=? where act_type=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_newType,currentTime,act_oldType});
        db.close();
        return true;

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


    //返回所有大类活动集合
    public ArrayList<String> queryAllType(){
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type from Activity_Type";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
                arrayList.add(cursor.getString(0));
            }
        }
        return arrayList;
    }

    //返回所有一个HashMap<String,ArrayList<String>>,其中key=大类名称,value=和大类对应的所有活动。需给定用户名
    public HashMap<String,ArrayList<String>> queryTypeAndActivity(String user_name){
        HashMap<String,ArrayList<String>> hashMap = new HashMap<String,ArrayList<String>>();
        ArrayList<String> arrayList1 =this.queryAllType();
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        if (arrayList1.size()!=0){
            for(String type:arrayList1){
                String sql=" select act_name from Activity" +
                        " inner join Activity_Type on Activity.type_ID=Activity_Type._id" +
                        " where Activity_Type.act_type=? and Activity.user_ID=" +
                        " (select _id from User_Info where user_name=?)";
                Cursor cursor=db.rawQuery(sql,new String[]{type,user_name});
                if(cursor.getCount()!=0){
                    ArrayList<String> arrayList = new ArrayList<String>();
                    while(cursor.moveToNext()){
                        arrayList.add(cursor.getString(0));
                    }
                    hashMap.put(type,arrayList);

                }


            }
        }
        return hashMap;
    }


}
