package com.example.guanguannfc.controller.userManagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.model.Dao.DaoUserInfo;

import static android.content.Context.MODE_PRIVATE;

public class Login {
    DaoUserInfo DD;
    public Login(Context context){
        this.DD = new DaoUserInfo(context);
    }
    public boolean LIsExistUserName(String userName) {
        boolean hasUserName = DD.registrationQuery(userName);
        return hasUserName;
    }
    public boolean isloginSuccess(String username,String password){
        boolean loginresult =  DD.loadQuery(username,password);
        return loginresult;
    }


}

