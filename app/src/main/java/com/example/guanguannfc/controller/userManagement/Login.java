package com.example.guanguannfc.controller.userManagement;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Context.MODE_PRIVATE;

public class Login extends AppCompatActivity {

    private boolean IsExistUserName(String userName) {
        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUserName = true;
        }
        return hasUserName;
    }
    private boolean LisloginSuccess(String username1,String password1,String username2,String password2){
        boolean loginresult =  false;
        if (username1 == username2 && password1==password2){
            loginresult = true;
        }
        else {
            loginresult = false;
        }
        return loginresult;
    }


}

