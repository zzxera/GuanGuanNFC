package com.example.guanguannfc.model.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;

import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.GuanSQLHelper;
import com.example.guanguannfc.model.Helper.HelperBox;
import com.example.guanguannfc.model.Helper.HelperBoxContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 盒子表操作类
 */
public class DaoBox {
    private final GuanSQLHelper mDataBaseHelper;


    public DaoBox(Context context){
        mDataBaseHelper=new GuanSQLHelper(context);

    }
    //插入一整条数据：需给定用户ID、nfc、盒子名称、盒子位置
    public boolean insert(long user_ID,String nfc,String box_name,String box_pos){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into " + GuanContract.Box.TABLE_NAME+ "(user_ID,nfc,box_name,box_pos,created_time,updated_time) values(?,?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_ID,nfc,box_name,box_pos,currentTime,currentTime});
        db.close();
        return true;
    }
    //删除盒子：需给定用户ID、盒子名称
    public boolean delete(long user_ID,String box_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from " + GuanContract.Box.TABLE_NAME + " where user_ID=? and box_name=?";
        db.execSQL(sql,new Object[]{user_ID,box_name});
        db.close();
        return true;
    }
    //更新盒子名称：需给定用户ID、盒子原名称、盒子现在的名称
    public boolean updateName(long user_ID,String box_oldName,String box_newName){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.Box.TABLE_NAME + " set box_name=?,updated_time=? where user_ID=? and box_name=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{box_newName,currentTime,user_ID,box_oldName});
        db.close();
        return true;

    }
    //更新盒子描述：需给定用户ID、盒子原描述、盒子现在的描述
    public boolean updatePos(long user_ID,String box_oldPos,String box_newPos){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update " + GuanContract.Box.TABLE_NAME + " set box_name=?,updated_time=? where user_ID=? and box_name=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{box_newPos,currentTime,user_ID,box_oldPos});
        db.close();
        return true;

    }
    //盒子查重，已包含盒子名返回true,反之返回false:给定用户ID和盒子名称
    public boolean query(long user_ID,String box_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + GuanContract.Box.TABLE_NAME + " where user_ID=? and box_name=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_ID),box_name});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    //查询表中是否包含给定nfc，包含返回true，不包含返回false：需要给定nfc
    public boolean query(String nfc){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from " + GuanContract.Box.TABLE_NAME + " where nfc=?";
        Cursor cursor=db.rawQuery(sql,new String[]{nfc});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }

    //返回指定用户所有盒子，和盒子位置描述
    public ArrayList<HelperBox> queryAllBox(String user_name){
        ArrayList<HelperBox> arrayList = new ArrayList<HelperBox>();
        HelperBox helperBox = null;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select box_name,box_pos from Box where user_ID=(select _id from User_Info where user_name=?)";
        Cursor cursor=db.rawQuery(sql,new String[]{user_name});
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                helperBox = new HelperBox(cursor.getString(0),cursor.getString(1));
                arrayList.add(helperBox);

            }
        }
        return arrayList;
    }

    /*
    返回所有一个HashMap<String,ArrayList<HelperBoxContent>>,其中key=盒子名称,value=和盒子对应的一个ArrayList<HelperBoxContent>集合
    集合里面每一个元素是一个HelperBoxContext对象，包括物品名称和物品数量两个属性
    方法需给定用户名
     */

    public HashMap<String,ArrayList<HelperBoxContent>> queryBoxAndContext(String user_name){
        HashMap<String,ArrayList<HelperBoxContent>> hashMap = new HashMap<String,ArrayList<HelperBoxContent>>();
        HelperBoxContent helperBoxContent = null;
        ArrayList<HelperBox> arrayList1 =this.queryAllBox(user_name);
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        for(HelperBox helperBox:arrayList1){
            String boxName = helperBox.getName();
            String sql=" select thing_name,thing_num from Box_Content" +
                    " inner join Box on Box._id=Box_Content.box_ID " +
                    " where Box.user_ID=(select _id from User_Info where user_name=?) and Box.box_name=?";
            Cursor cursor=db.rawQuery(sql,new String[]{user_name,boxName});
            if(cursor.getCount()!=0){
                ArrayList<HelperBoxContent> arrayList = new ArrayList<HelperBoxContent>();
                while(cursor.moveToNext()){
                    helperBoxContent = new HelperBoxContent(cursor.getString(0),cursor.getInt(1));
                    arrayList.add(helperBoxContent);
                }
                hashMap.put(boxName,arrayList);
            }
        }


        return hashMap;
    }


}
