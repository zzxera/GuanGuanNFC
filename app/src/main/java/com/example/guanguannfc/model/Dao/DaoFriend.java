package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;
import com.example.guanguannfc.model.Helper.HelperFriend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 好友关系表
 */
public class DaoFriend {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoFriend(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一条数据
    public boolean insert (int user_id,int friend_id){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into friend_list(user_id,friend_id,created_time,updated_time) values(?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_id,friend_id,currentTime,currentTime});
        db.close();
        return true;
    }
    //插入一条数据(添加一个好友)：需要给定用户名和好友名字
    public boolean insert (String user_name,String friend_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into friend_list(user_id,friend_id,created_time,updated_time) values((select _id from user_info where user_name=?),(select _id from user_info where user_name=?),?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_name,friend_name,currentTime,currentTime});
        db.close();
        return true;
    }
    //删除一条数据(删除一个好友)：需要给定用户名和好友名字
    public boolean delete (String user_name,String friend_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="delete from friend_list where user_id=(select _id from user_info where user_name=?) and friend_id=(select _id from user_info where user_name=?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_name,friend_name});
        db.close();
        return true;
    }

    //返回所有好友的用户名和等级：需要给定用户名
    public List<HelperFriend> query(String user_name){
        List<HelperFriend> list = new ArrayList<HelperFriend>();
        HelperFriend helperFriend;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select user_name,active_day from User_Info where User_Info._id in (select friend_id from friend_list where user_id=(select _id from user_info where user_name=?))";
        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
                helperFriend = new HelperFriend(cursor.getString(0),cursor.getInt(1));
                list.add(helperFriend);
            }
            return list;
        }else {
            return list;
        }
    }




}
