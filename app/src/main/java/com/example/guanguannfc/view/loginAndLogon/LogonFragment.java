package com.example.guanguannfc.view.loginAndLogon;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.guanguannfc.controller.userManagement.Register;

import com.example.guanguannfc.R;

public class LogonFragment extends Fragment {

    private EditText edit_username,edit_psw,edit_psw_confirm;
    private String username,pasword,pasword_confirm;
    private CheckBox checkBox;
    private Context ctx;
    private Button btn_logon;
    private Register register;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_logon, container, false);
        ctx=getActivity();
        register=new Register(ctx);

        btn_logon=view.findViewById(R.id.button_logon_confirm);
        edit_username=view.findViewById(R.id.edit_username);
        edit_psw=view.findViewById(R.id.edit_psw);
        edit_psw_confirm=view.findViewById(R.id.edit_psw_confirm);
        checkBox=view.findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    btn_logon.setClickable(true);
                    btn_logon.setEnabled(true);
                }
                else {
                    btn_logon.setClickable(false);
                    btn_logon.setEnabled(false);
                }
            }
        });


        btn_logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=edit_username.getText().toString();
                pasword=edit_psw.getText().toString();
                pasword_confirm=edit_psw_confirm.getText().toString();

                if(username.equals("")){
                    Toast.makeText(ctx,"请设置账号",Toast.LENGTH_LONG).show();
                }
                else if(pasword.equals("")){
                    Toast.makeText(ctx,"请设置密码",Toast.LENGTH_LONG).show();
                }
                else if(pasword_confirm.equals("")){
                    Toast.makeText(ctx,"请确认密码",Toast.LENGTH_LONG).show();
                }

                else {
//                    if (username .equals("GY")){
//                        Toast.makeText(ctx,"账号已存在",Toast.LENGTH_LONG).show();
//                    }
//                    if(register.RisExistUserName(username)){
//                        Toast.makeText(ctx,"账号已存在",Toast.LENGTH_LONG).show();
//                    }

                    if(pasword_confirm.equals(pasword) == false){
                        Toast.makeText(ctx,"两次输入的密码不同",Toast.LENGTH_LONG).show();

                    }
                    else if(pasword.length()<6 | pasword.length()>20){
                        Toast.makeText(ctx,"密码长度为6-20位",Toast.LENGTH_LONG).show();
                    }
                    else {
                        if(register.ISRegisterSuccess(username,pasword)){
                            Toast.makeText(getActivity(),"注册成功",Toast.LENGTH_LONG).show();
                            edit_username.setText("");
                            edit_psw.setText("");
                            edit_psw_confirm.setText("");
                            checkBox.setChecked(false);
                            getFragmentManager().beginTransaction()
                                    .addToBackStack(null)
                                    .replace(R.id.logandsign,new SigninFragment())
                                    .commit();
                        }
                        else{
                            Toast.makeText(ctx,"账号已存在",Toast.LENGTH_LONG).show();
                        }


                    }
                }



            }
        });




        return view;
    }


}
