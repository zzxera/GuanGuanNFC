package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanSQLHelper;
import com.example.guanguannfc.model.Helper.HelperFriend;
import com.example.guanguannfc.model.Helper.HelperPush;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 好友关系表
 */
public class DaoPush {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoPush(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一条数据
    public boolean insert (int author_id,String title,String summary,String contents){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into push_note(author_id,title,summary,contents,created_time,updated_time) values(?,?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{author_id,title,summary,contents,currentTime,currentTime});
        db.close();
        return true;
    }

    //删除一条数据
    public boolean delete (int id){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="delete from push_note where _id=?";
        db.execSQL(sql,new Object[]{id});
        db.close();
        return true;
    }

    //返回所有推送，一个List，每个元素代表一条推送
    public List<HelperPush> query(){
        List<HelperPush> list = new ArrayList<HelperPush>();
        HelperPush helperPush;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select user_name,title,summary,contents,created_time from push_note inner join user_info on push_note.author_id=user_info._id";
        Cursor cursor=db.rawQuery(sql,new String[]{});
        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
                helperPush = new HelperPush(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getLong(4));
                list.add(helperPush);
            }
            return list;
        }else {
            return list;
        }
    }




}
