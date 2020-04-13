package com.example.guanguannfc.controller.userManagement;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

public class Register extends AppCompatActivity {
    public boolean RisExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUserName = true;
        }
        return hasUserName;
    }
    public String ISRegisterSuccess(String username, String password){
        String newUsername = username;
        String Userpassword = password;//调用数据库方法，返回布尔类型的值
        String result = "successful";
        return result;
    }
}
