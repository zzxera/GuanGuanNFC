package com.example.guanguannfc.model;

import android.provider.BaseColumns;

public class GuanContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "guan.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = " ,";

    private GuanContract(){}

    public static class UserInfo implements BaseColumns{
        public static final String TABLE_NAME = "User_Info";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_CREATED_TIME = "created_time";
        public static final String COLUMN_UPDATED_TIME = "updated_time";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + UserInfo.TABLE_NAME + " (" +
                UserInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                UserInfo.COLUMN_USER_NAME + TEXT_TYPE + COMMA_SEP +
                UserInfo.COLUMN_PASSWORD + TEXT_TYPE + COMMA_SEP +
                UserInfo.COLUMN_CREATED_TIME + TEXT_TYPE + COMMA_SEP +
                UserInfo.COLUMN_UPDATED_TIME + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserInfo.TABLE_NAME;
    }

    public static class ActivityType implements BaseColumns{
        public static final String TABLE_NAME = "Activity_Type";
        public static final String COLUMN_ACT_TYPE = "user_name";
        public static final String COLUMN_CREATED_TIME = "created_time";
        public static final String COLUMN_UPDATED_TIME = "updated_time";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + ActivityType.TABLE_NAME + " (" +
                ActivityType._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                ActivityType.COLUMN_ACT_TYPE + TEXT_TYPE + COMMA_SEP +
                ActivityType.COLUMN_CREATED_TIME + TEXT_TYPE + COMMA_SEP +
                ActivityType.COLUMN_UPDATED_TIME + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + ActivityType.TABLE_NAME;
    }

    public static class Activity implements BaseColumns{
        public static final String TABLE_NAME = "Activity";
        public static final String COLUMN_USER_ID = "user_ID";
        public static final String COLUMN_NFC = "nfc";
        public static final String COLUMN_TYPE_ID = "type_ID";
        public static final String COLUMN_ACT_NAME = "act_name";
        public static final String COLUMN_CREATED_TIME = "created_time";
        public static final String COLUMN_UPDATED_TIME = "updated_time";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + Activity.TABLE_NAME + " (" +
                Activity._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                Activity.COLUMN_USER_ID + TEXT_TYPE + COMMA_SEP +
                Activity.COLUMN_NFC + TEXT_TYPE + COMMA_SEP +
                Activity.COLUMN_TYPE_ID + TEXT_TYPE + COMMA_SEP +
                Activity.COLUMN_ACT_NAME + TEXT_TYPE + COMMA_SEP +
                Activity.COLUMN_CREATED_TIME + TEXT_TYPE + COMMA_SEP +
                Activity.COLUMN_UPDATED_TIME + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Activity.TABLE_NAME;
    }

    public static class ActSta implements BaseColumns{
        public static final String TABLE_NAME = "Act_Sta";
        public static final String COLUMN_ACT_ID = "act_ID";
        public static final String COLUMN_START_TIME = "start_time";
        public static final String COLUMN_END_TIME = "end_time";
        public static final String COLUMN_CREATED_TIME = "created_time";
        public static final String COLUMN_UPDATED_TIME = "updated_time";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + ActSta.TABLE_NAME + " (" +
                ActSta._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                ActSta.COLUMN_ACT_ID + TEXT_TYPE + COMMA_SEP +
                ActSta.COLUMN_START_TIME + TEXT_TYPE + COMMA_SEP +
                ActSta.COLUMN_END_TIME + TEXT_TYPE + COMMA_SEP +
                ActSta.COLUMN_CREATED_TIME + TEXT_TYPE + COMMA_SEP +
                ActSta.COLUMN_UPDATED_TIME + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + ActSta.TABLE_NAME;
    }

    public static class Box implements BaseColumns{
        public static final String TABLE_NAME = "Box";
        public static final String COLUMN_USER_ID = "user_ID";
        public static final String COLUMN_NFC = "nfc";
        public static final String COLUMN_BOX_NAME = "box_name";
        public static final String COLUMN_BOX_POS = "box_pos";
        public static final String COLUMN_CREATED_TIME = "created_time";
        public static final String COLUMN_UPDATED_TIME = "updated_time";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + Box.TABLE_NAME + " (" +
                Box._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                Box.COLUMN_USER_ID + TEXT_TYPE + COMMA_SEP +
                Box.COLUMN_NFC + TEXT_TYPE + COMMA_SEP +
                Box.COLUMN_BOX_NAME + TEXT_TYPE + COMMA_SEP +
                Box.COLUMN_BOX_POS + TEXT_TYPE + COMMA_SEP +
                Box.COLUMN_CREATED_TIME + TEXT_TYPE + COMMA_SEP +
                Box.COLUMN_UPDATED_TIME + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Box.TABLE_NAME;
    }

    public static class BoxContent implements BaseColumns{
        public static final String TABLE_NAME = "Box_Content";
        public static final String COLUMN_BOX_ID = "box_ID";
        public static final String COLUMN_THING_NAME = "thing_name";
        public static final String COLUMN_THING_NUM = "thing_num";
        public static final String COLUMN_CREATED_TIME = "created_time";
        public static final String COLUMN_UPDATED_TIME = "updated_time";
        public static final String SQL_CREATE_TABLE = "CREATE TABLE "
                + BoxContent.TABLE_NAME + " (" +
                BoxContent._ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                BoxContent.COLUMN_BOX_ID + TEXT_TYPE + COMMA_SEP +
                BoxContent.COLUMN_THING_NAME + TEXT_TYPE + COMMA_SEP +
                BoxContent.COLUMN_THING_NUM + TEXT_TYPE + COMMA_SEP +
                BoxContent.COLUMN_CREATED_TIME + TEXT_TYPE + COMMA_SEP +
                BoxContent.COLUMN_UPDATED_TIME + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + BoxContent.TABLE_NAME;
    }


}
