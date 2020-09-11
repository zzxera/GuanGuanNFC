package com.example.guanguannfc.controller.userManagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.model.Dao.DaoUserInfo;

import static android.content.Context.MODE_PRIVATE;

public class Register {
    DaoUserInfo DD;
    Message message;
    public Register(Context context, Message message){
        this.DD = new DaoUserInfo(context);
        this.message = message;
    }
    public void register1(final String username,final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                message.getLoadMessage(DD.insert(username,password));
            }
        }).start();
    }


    interface Message{
        void getLoadMessage(String str);
    }
}