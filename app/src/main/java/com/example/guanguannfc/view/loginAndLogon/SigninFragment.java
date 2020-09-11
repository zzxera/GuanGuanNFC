package com.example.guanguannfc.view.loginAndLogon;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.userManagement.UserInfo;
import com.example.guanguannfc.model.Helper.HelperUserInfo;
import com.example.guanguannfc.view.homepage.HomePageActivity;
import com.example.guanguannfc.controller.userManagement.Login;
import com.example.guanguannfc.view.lead.LeadActivity;

import java.util.HashMap;


public class SigninFragment extends Fragment {

    private EditText edit_username,edit_psw;
    private String username,psw;
    private Login login ;
    private UserInfo leadupdate;
    private Boolean lead;
    private HelperUserInfo leads;
    private int id;
    private SoundPool mSoundPool = null;
    private HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();//声明HashMap来存放声音文件
    Button button_signin;
    Context ctx;

    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;
    private void initSoundPool() throws Exception{//初始化声音池
        SoundPool.Builder spb = new SoundPool.Builder();
        spb.setMaxStreams(10);
        //spb.setAudioAttributes(null);    //转换音频格式
        mSoundPool = spb.build();
        //加载声音文件，并且设置为1号声音放入hm中
        hm.put(1, mSoundPool.load(getActivity(), R.raw.button_guan, 1));
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        sprfMain= PreferenceManager.getDefaultSharedPreferences(getActivity());
        editorMain=sprfMain.edit();
        ctx=getActivity();
        edit_username=view.findViewById(R.id.edit_username);
        edit_psw=view.findViewById(R.id.edit_psw);
        button_signin = (Button) view.findViewById(R.id.button_signin_confirm);

        username = sprfMain.getString("userName","");
        psw = sprfMain.getString("psw","");
        edit_username.setText(username);
        edit_psw.setText(psw);
        login=new Login(ctx);
        leadupdate = new UserInfo(ctx);
        leads =leadupdate.getlead(username);
        id=leads.getIs_studied();
        try {
            initSoundPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        View rootView = inflater.inflate(R.layout.signin, null); // 先解析file.xml布局，得到一个view
        button_signin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"登录",Toast.LENGTH_LONG).show();
                username = edit_username.getText().toString();
                psw = edit_psw.getText().toString();

                if (username.equals("")){
                    Toast.makeText(ctx,"请输入账号",Toast.LENGTH_LONG).show();
                }
                else if(psw.equals("")){
                    Toast.makeText(ctx,"请输入密码",Toast.LENGTH_LONG).show();
                }
                else {
                    if(psw.length()<6 | psw.length()>20){
                    Toast.makeText(ctx,"密码长度为6-20位",Toast.LENGTH_LONG).show();
                }
                    else if(!login.LIsExistUserName(username)){
                        Toast.makeText(ctx,"账号不存在",Toast.LENGTH_LONG).show();
                    }
//                    else if (username.equals("GGYY") == false){
//                        Toast.makeText(ctx,"账号不存在",Toast.LENGTH_LONG).show();
//                    }
                    else if (login.isloginSuccess(username,psw)==false){
                        Toast.makeText(ctx,"密码错误",Toast.LENGTH_LONG).show();
                    }

//                    else if (psw.equals("123456") == false){
//                        Toast.makeText(ctx,"密码错误",Toast.LENGTH_LONG).show();
//                    }
                    else{
//                        Intent intent = new Intent(getActivity(), Data.class);
////                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        Bundle bundle=new Bundle();
//                        bundle.putString("userName",username);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
                        if(id == 0) {
                            mSoundPool.play(hm.get(1), 1, 1, 0, 0, 1);
                            Intent intent = new Intent(getActivity(), LeadActivity.class);
                            editorMain.putBoolean("main", true);
                            editorMain.putString("userName", username);
                            editorMain.putString("psw", psw);
                            editorMain.commit();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putString("userName", username);
                            intent.putExtras(bundle);
                            lead=leadupdate.leadupdate(username,1);
                            startActivity(intent);
                        }
                        else {
                            mSoundPool.play(hm.get(1), 1, 1, 0, 0, 1);
                            Intent intent = new Intent(getActivity(), HomePageActivity.class);
                            editorMain.putBoolean("main", true);
                            editorMain.putString("userName", username);
                            editorMain.putString("psw", psw);
                            editorMain.commit();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Bundle bundle = new Bundle();
                            bundle.putString("userName", username);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }

                }

            }
        });

        TextView forgetkey=view.findViewById(R.id.text_forgetkey);
        forgetkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Forgetkey.class);
                startActivity(intent);
            }
        });



        return view;


    }


//    public void getContex(Context context){
//        ctx = context;
//    }


}