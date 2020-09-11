package com.example.guanguannfc.controller.userManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.audiofx.DynamicsProcessing;
import android.os.Message;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.model.Dao.DaoUserInfo;
import com.example.guanguannfc.model.GuanContract;

import static android.content.Context.MODE_PRIVATE;

public class Login {
    DaoUserInfo DD;
    Message message;
    //public Activity activity;
    public Login(Context context, Message message){
        this.DD = new DaoUserInfo(context);
        this.message = message;
        //this.message = message;
    }

    public boolean LIsExistUserName(String userName) {
        boolean hasUserName = DD.registrationQuery(userName);
        return hasUserName;
    }
    public boolean isloginSuccess(String username,String password){
        boolean loginresult =  DD.loadQuery(username,password);
        return loginresult;
    }

    public void login1(final String username, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean hasusername = DD.registrationQuery(username);
                if(hasusername){
                    boolean login = DD.loadQuery(username,password);
                    if(login){
                        String str = "登陆成功";
                        message.getLoadMessage(str);
                    }else {
                        message.getLoadMessage("登陆失败");
                    }
                }
                else {
                    String str="账号不存在";
                    message.getLoadMessage(str);
                }
            }
        }).start();
    }

    interface Message{
        void getLoadMessage(String str);
    }


}

