package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户信息表操作类
 */
public class DaoUserInfo {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoUserInfo(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一个用户：需要给定用户名和密码
    public boolean insert(String username,String password){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.UserInfo.TABLE_NAME+ "(user_name,password,created_time,updated_time) values(?,?,?,?)";

        Date date = new Date();
        long currentTime = date.getTime();
        try{
            db.execSQL(sql,new Object[]{username,md5(password),currentTime,currentTime});
        }catch (SQLiteConstraintException e){
            Log.v("tag","插入失败");

            return false;

        }finally {
            db.close();
        }
        return true;

    }
    //删除一个用户：需要给定用户名
    public boolean delete(String username){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.UserInfo.TABLE_NAME + " where user_name=?";
        db.execSQL(sql,new Object[]{username});
        db.close();
        return true;

    }
    //更新密码：需要给定用户名和密码
    public boolean update(String username,String newPassword){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.UserInfo.TABLE_NAME + " set password=? , updated_time=? where user_name=?";

        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{md5(newPassword),currentTime,username});
        db.close();
        return true;

    }
    //登录查询：需要给定用户名和密码
    public boolean loadQuery(String username,String password){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + GuanContract.UserInfo.TABLE_NAME + " where user_name=? and password=?";
        Cursor cursor=db.rawQuery(sql,new String[]{username,md5(password)});
        //Cursor cursor=db.query(Constants.TABLE_NAME,new String[]{"username","password"},"username=? and password=?",new String[]{username,password},null,null,null);
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    //注册查询用户名是否存在：需要给定用户名
    public boolean registrationQuery(String username){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + GuanContract.UserInfo.TABLE_NAME + " where user_name=?";
        Cursor cursor=db.rawQuery(sql,new String[]{username});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    //md5加密
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result="";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length()==1) {
                    temp = "0" + temp;
                }
                result+=temp;
            }
            return result;

        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    };
}
