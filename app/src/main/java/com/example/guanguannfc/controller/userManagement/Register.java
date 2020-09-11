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
    public boolean RisExistUserName(String userName) {
        boolean hasUserName = DD.registrationQuery(userName);
        return hasUserName;
    }
    public boolean ISRegisterSuccess(String username, String password){
        return DD.insert(username,password);
    }

    public void register1(final String username,final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean hs = DD.registrationQuery(username);
                if(hs){
                    message.getLoadMessage("此用户名已存在");
                }else {
                    boolean irs = DD.insert(username,password);
                    if(irs){
                        message.getLoadMessage("注册成功");
                    }else {
                        message.getLoadMessage("注册失败");
                    }
                }
            }
        }).start();
    }


    public interface Message{
        void getLoadMessage(String str);
    }
}