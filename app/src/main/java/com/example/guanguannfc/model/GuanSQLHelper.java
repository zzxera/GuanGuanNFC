package com.example.guanguannfc.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GuanSQLHelper extends SQLiteOpenHelper {

    public GuanSQLHelper(Context context){
        super(context, GuanContract.DATABASE_NAME, null, GuanContract.DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(GuanContract.UserInfo.SQL_CREATE_TABLE);
        db.execSQL(GuanContract.ActivityType.SQL_CREATE_TABLE);
        db.execSQL(GuanContract.Activity.SQL_CREATE_TABLE);
        db.execSQL(GuanContract.ActSta.SQL_CREATE_TABLE);
        db.execSQL(GuanContract.Box.SQL_CREATE_TABLE);
        db.execSQL(GuanContract.BoxContent.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(GuanContract.UserInfo.SQL_DELETE_ENTRIES);
        db.execSQL(GuanContract.ActivityType.SQL_DELETE_ENTRIES);
        db.execSQL(GuanContract.Activity.SQL_DELETE_ENTRIES);
        db.execSQL(GuanContract.ActSta.SQL_DELETE_ENTRIES);
        db.execSQL(GuanContract.Box.SQL_DELETE_ENTRIES);
        db.execSQL(GuanContract.BoxContent.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
