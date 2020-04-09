package com.example.guanguannfc.controller.userManagement;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

public class Register extends AppCompatActivity {
    private boolean RisExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUserName = true;
        }
        return hasUserName;
    }
    private Register(String username, String password){

    }
}
