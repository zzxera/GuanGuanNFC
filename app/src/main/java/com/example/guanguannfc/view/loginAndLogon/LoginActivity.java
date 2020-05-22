package com.example.guanguannfc.view.loginAndLogon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.userManagement.Login;
import com.example.guanguannfc.model.DataBaseTest.FakeData;
import com.example.guanguannfc.model.Initialization;
import com.example.guanguannfc.view.homepage.HomePageActivity;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;
    private SigninFragment frag_signin = new SigninFragment();
    private LogonFragment frag_logon = new LogonFragment();
    Login login = new Login(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sprfMain= PreferenceManager.getDefaultSharedPreferences(this);
        editorMain=sprfMain.edit();
        if(sprfMain.getBoolean("main",false)){
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            LoginActivity.this.finish();
        }

        setContentView(R.layout.activity_las);
//        frag_logon.getContex(this);
        getFragmentManager().beginTransaction().replace(R.id.logandsign,frag_signin).commit();

        Initialization.initialization(this);
        if (!login.LIsExistUserName("aaa")){
            FakeData fakeData = new FakeData(this);
            fakeData.insert();
        }

    }

    public void click(View v){
        int id=v.getId();

        switch(id){
            case R.id.button_signin:
                getFragmentManager().beginTransaction().replace(R.id.logandsign,frag_signin).commit();
                break;
            case R.id.button_logon:
                getFragmentManager().beginTransaction().replace(R.id.logandsign,frag_logon).commit();
                break;

            case R.id.text_agree:
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);

                builder.setTitle("协议");
                builder.setMessage(R.string.no_mean);
                builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                final AlertDialog b=builder.create();
                b.show();
                break;




        }

    }

//    public void init(){
//        button_signin=findViewById(R.id.button_signin_confirm);
//        button_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Homepage.class);
//                startActivity(intent);
//            }
//        });
//    }


}
