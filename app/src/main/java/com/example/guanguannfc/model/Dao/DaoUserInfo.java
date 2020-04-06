package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

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
    public void insert(String username,String password){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.UserInfo.TABLE_NAME+ "(user_name,password,created_time,updated_time) values(?,?,?,?)";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        db.execSQL(sql,new Object[]{username,md5(password),currentTime,currentTime});
        db.close();

    }
    public void delete(String username){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.UserInfo.TABLE_NAME + " where user_name=?";
        db.execSQL(sql,new Object[]{username});
        db.close();

    }
    public void update(String username,String newPassword){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.UserInfo.TABLE_NAME + " set password=? , updated_time=? where user_name=?";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date date = new Date();
        String currentTime = simpleDateFormat.format(date);
        db.execSQL(sql,new Object[]{md5(newPassword),currentTime,username});
        db.close();

    }
    //登录查询
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
    //注册查询
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
