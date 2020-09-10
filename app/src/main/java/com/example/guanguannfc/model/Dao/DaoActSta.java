package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;
import com.example.guanguannfc.model.Helper.HelperActivity;
import com.example.guanguannfc.model.Helper.HelperActivityType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 活动统计表操作类
 */
public class DaoActSta {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoActSta(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一条活动记录：需要给定用户名、活动名称、开始活动时间、结束活动时间
    public boolean insert(String user_name,String act_name,long start_time,long end_time){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into Act_Sta (act_ID,start_time,end_time,created_time,updated_time) values((select _id from Activity where act_name=? and user_ID=(select _id from User_Info where user_name=?)),?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_name,user_name,start_time,end_time,currentTime,currentTime});
        db.close();
        return true;
    }
    //插入一条活动记录：需要给定活动id、开始活动时间、结束活动时间
    public boolean insert(int act_ID,long start_time,long end_time){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into Act_Sta (act_ID,start_time,end_time,created_time,updated_time) values(?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_ID,start_time,end_time,currentTime,currentTime});
        db.close();
        return true;
    }
    //插入一条活动记录：需要给定活动id、开始活动时间、结束活动时间、评价、分享状态
    public boolean insert(int act_ID,long start_time,long end_time,String moment_text,int is_shared){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into Act_Sta (act_ID,start_time,end_time,moment_text,is_shared,created_time,updated_time) values(?,?,?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{act_ID,start_time,end_time,moment_text,is_shared,currentTime,currentTime});
        db.close();
        return true;
    }
    //更新一条活动记录（用于分享活动，非计时界面）：需给定用户名、活动开始时间戳、分享文本
    public boolean update(String username,long start_time,String moment_text ){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update Act_Sta set moment_text=?,is_shared=1,updated_time=?,shared_time=? where start_time=? and act_id in (select _id from activity where user_id=(select _id from user_info where user_name=?))";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{moment_text,currentTime,currentTime,start_time,username});
        db.close();
        return true;
    }
    //更新一条活动记录（用于分享活动，计时界面分享需要执行）：需给定用户名、分享文本
    public boolean update(String username,String moment_text ){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update Act_Sta set moment_text=?,is_shared=1,updated_time=? ,shared_time=?" +
                " where end_time=(select max(end_time) from act_sta where act_id in (select _id from activity where user_id=(select _id from user_info where user_name=?)) group by end_time)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{moment_text,currentTime,currentTime,username});
        db.close();
        return true;
    }

    //根据活动ID删除和该活动所有时间记录：需给定活动ID
    public boolean delete(long act_ID){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.ActSta.TABLE_NAME + " where act_ID=?";
        db.execSQL(sql,new Object[]{act_ID});
        db.close();
        return true;

    }


    //查询自定义范围所有大类活动总时间,返回一个ArrayList<HelperActivityType>: 用户名、活动大类名、开始日期时间戳、结束日期时间戳
    public ArrayList<HelperActivityType> queryActType(String user_name,  long begin, long end ){
        List<HelperActivityType> list = new ArrayList<HelperActivityType>();
        HelperActivityType helperActivityType;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select act_type,sum(end_time-start_time) from  Activity_Type,Act_Sta inner join Activity" +
                " on Activity._id=Act_Sta.act_ID " +
                " where Activity_type._id=Activity.type_ID and Activity.user_ID=(select _id from User_Info where user_name=?) and start_time>? and end_time<? " +
                " group by act_type";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name, String.valueOf(begin), String.valueOf(end)});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivityType = new HelperActivityType(cursor.getString(0),cursor.getLong(1));
                list.add(helperActivityType);
            }
            return (ArrayList<HelperActivityType>) list;
        }else {
            return null;
        }
    }

    //查询自定义范围单个大类活动总时间,返回一个ArrayList<HelperActivityType>: 用户名、活动大类名、开始日期时间戳、结束日期时间戳
    public ArrayList<HelperActivityType> queryActType(String user_name,  long begin, long end ,String act_type){
        List<HelperActivityType> list = new ArrayList<HelperActivityType>();
        HelperActivityType helperActivityType;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select act_type,sum(end_time-start_time) from  Activity_Type,Act_Sta inner join Activity" +
                " on Activity._id=Act_Sta.act_ID " +
                " where Activity_type._id=Activity.type_ID and Activity.user_ID=(select _id from User_Info where user_name=?) and start_time>? and end_time<? and Activity_Type.act_type=?" +
                " group by act_type";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name, String.valueOf(begin), String.valueOf(end),act_type});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivityType = new HelperActivityType(cursor.getString(0),cursor.getLong(1));
                list.add(helperActivityType);
            }
            return (ArrayList<HelperActivityType>) list;
        }else {
            return null;
        }
    }






    //返回一个活动集合（时间从长到短），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByLengthDesc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_ranked,act_sta._id" +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time-start_time desc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（时间从短到长），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByLengthAsc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_rankes,act_sta._id " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time-start_time asc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（最新活动在前），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByTimeDesc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_ranked,act_sta._id " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time desc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（最新活动在后），一个ArrayList<HelperActivity>
    public ArrayList<HelperActivity> queryByTimeAsc(String user_name){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_ranked,act_sta._id " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " order by end_time asc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }






    //返回一个活动集合（时间从长到短），一个ArrayList<HelperActivity>(指定大类筛选)
    public ArrayList<HelperActivity> queryByLengthDesc(String user_name,String act_type){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_ranked,act_sta._id " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " and act_type=?" +
                " order by end_time-start_time desc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name,act_type});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（时间从短到长），一个ArrayList<HelperActivity>(指定大类筛选)
    public ArrayList<HelperActivity> queryByLengthAsc(String user_name,String act_type){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_ranked,act_sta._id " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " and act_type=?" +
                " order by end_time-start_time asc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name,act_type});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（最新活动在前），一个ArrayList<HelperActivity>,(指定大类筛选)
    public ArrayList<HelperActivity> queryByTimeDesc(String user_name,String act_type){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_ranked,act_sta._id " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " and act_type=?" +
                " order by end_time desc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name,act_type});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }

    //返回一个活动集合（最新活动在后），一个ArrayList<HelperActivity>,(指定大类筛选)
    public ArrayList<HelperActivity> queryByTimeAsc(String user_name,String act_type){
        List<HelperActivity> list = new ArrayList<HelperActivity>();
        HelperActivity helperActivity;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql=" select act_type,act_name,start_time,end_time,end_time-start_time,is_ranked,act_sta._id " +
                " from Act_Sta inner join Activity on Activity._id=Act_Sta.act_ID " +
                " inner join Activity_Type on Activity_Type._id=Activity.type_ID " +
                " where user_ID=(select _id from User_Info where user_name=?) " +
                " and act_type=?" +
                " order by end_time asc";

        Cursor cursor=db.rawQuery(sql,new String[]{user_name,act_type});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperActivity = new HelperActivity(cursor.getString(0),
                        cursor.getString(1),cursor.getLong(2),
                        cursor.getLong(3),cursor.getLong(4),cursor.getInt(5),cursor.getInt(6));
                list.add(helperActivity);
            }
            return (ArrayList<HelperActivity>) list;
        }else {
            return null;
        }
    }






}
