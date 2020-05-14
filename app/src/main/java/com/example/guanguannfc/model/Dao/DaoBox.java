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
    //添加一个盒子：需给定用户名、nfc、盒子名称、盒子位置
    public boolean insert(String user_name,String nfc,String box_name,String box_pos){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into Box(user_ID,nfc,box_name,box_pos,created_time,updated_time) values((select _id from User_Info where user_name=?),?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_name,nfc,box_name,box_pos,currentTime,currentTime});
        db.close();
        return true;
    }
    //添加一个盒子：需给定用户id、nfc、盒子名称、盒子位置
    public boolean insert(int user_ID,String nfc,String box_name,String box_pos){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="insert into Box(user_ID,nfc,box_name,box_pos,created_time,updated_time) values(?,?,?,?,?,?)";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{user_ID,nfc,box_name,box_pos,currentTime,currentTime});
        db.close();
        return true;
    }
    //删除盒子：需给定用户名、盒子名称
    public boolean delete(String user_name,String box_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql = "delete from Box where user_ID=(select _id from User_Info where user_name=?) and box_name=?";
        db.execSQL(sql,new Object[]{user_name,box_name});
        db.close();
        return true;
    }
    //修改盒子名称：需给定用户ID、盒子原名称、盒子现在的名称
    public boolean updateName(String user_name,String box_oldName,String box_newName){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update Box set box_name=?,updated_time=? where user_ID=(select _id from User_Info where user_name=?) and box_name=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{box_newName,currentTime,user_name,box_oldName});
        db.close();
        return true;

    }
    //更新盒子描述：需给定用户ID、盒子原描述、盒子现在的描述
    public boolean updatePos(String user_name,String box_oldPos,String box_newPos){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="update Box set box_name=?,updated_time=? where user_ID=(select _id from User_Info where user_name=?) and box_name=?";
        Date date = new Date();
        long currentTime = date.getTime();
        db.execSQL(sql,new Object[]{box_newPos,currentTime,user_name,box_oldPos});
        db.close();
        return true;

    }
    //盒子查重，已包含盒子名返回true,反之返回false:给定用户ID和盒子名称
    public boolean query(String user_name,String box_name){
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select * from Box where user_ID=(select _id from User_Info where user_name=?) and box_name=?";
        Cursor cursor=db.rawQuery(sql,new String[]{user_name,box_name});
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
    //根据nfc查询盒子名称
    public String queryBoxByNFC(String nfc){
        String boxName = null;
        SQLiteDatabase db=mDataBaseHelper.getWritableDatabase();
        String sql="select box_name from  Box where nfc=?";
        Cursor cursor=db.rawQuery(sql,new String[]{nfc});
        while(cursor.moveToNext()){
            boxName = cursor.getString(0);
        }
        return boxName;
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
