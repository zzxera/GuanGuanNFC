package com.example.guanguannfc.view.loginAndLogon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class LogAndSign extends AppCompatActivity {

    private Signin frag_signin = new Signin();
    private Logon frag_logon = new Logon();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_las);

        getFragmentManager().beginTransaction().replace(R.id.logandsign,frag_signin).commit();

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
