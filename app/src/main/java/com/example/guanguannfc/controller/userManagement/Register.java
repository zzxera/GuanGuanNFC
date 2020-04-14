package com.example.guanguannfc.controller.userManagement;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.model.Dao.DaoUserInfo;

import static android.content.Context.MODE_PRIVATE;

public class Register extends AppCompatActivity {
    DaoUserInfo DD = new DaoUserInfo(this);
    public boolean RisExistUserName(String userName) {
        boolean hasUserName = DD.registrationQuery(userName);
        return hasUserName;
    }
    public boolean ISRegisterSuccess(String username, String password){
        DD.insert(username,password);
         boolean result = true;
        return result;
    }
}
