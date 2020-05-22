package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanSQLHelper;
import com.example.guanguannfc.model.Helper.HelperActivity;
import com.example.guanguannfc.model.Helper.HelperApplication;
import com.example.guanguannfc.model.Helper.HelperFriend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 好友关系表
 */
public class DaoMoment {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoMoment(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一条数据
    public boolean insert (int from_id,int to_id,String content,int is_processed){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into moment_list(from_id,to_id,content,is_processed,created_time,updated_time) values(?,?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{from_id,to_id,content,is_processed,currentTime,currentTime});
        db.close();
        return true;
    }
    //删除一条数据（拒绝添加好友）:需给定用户名、和申请来源用户名
    public boolean delete (String user_name,String friend_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="delete from moment_list where from_id=(select _id from user_info where user_name=?) and to_id=(select _id from user_info where user_name=?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{friend_name,user_name});
        db.close();
        return true;
    }

    //返回所有的好友申请列表：需要给定用户名
    public List<HelperApplication> query(String user_name){
        List<HelperApplication> list = new ArrayList<HelperApplication>();
        HelperApplication helperApplication;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select user_name,content,created_time from moment_list inner join user_info on user_info._id=moment_list.from_id where to_id=(select _id from user_info where user_name=?)";
        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
                helperApplication = new HelperApplication(cursor.getString(0),cursor.getString(1),cursor.getLong(2));
                list.add(helperApplication);
            }
            return list;
        }else {
            return list;
        }
    }




}
