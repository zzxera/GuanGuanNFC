package com.example.guanguannfc.controller.userManagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.model.Dao.DaoUserInfo;

import static android.content.Context.MODE_PRIVATE;

public class Register {
    DaoUserInfo DD;
    public Register(Context context){
        this.DD = new DaoUserInfo(context);
    }
    public boolean RisExistUserName(String userName) {
        boolean hasUserName = DD.registrationQuery(userName);
        return hasUserName;
    }
    public boolean ISRegisterSuccess(String username, String password){
        return DD.insert(username,password);
    }
}