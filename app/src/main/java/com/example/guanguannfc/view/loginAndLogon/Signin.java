package com.example.guanguannfc.view.loginAndLogon;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.guanguannfc.R;
import com.example.guanguannfc.view.data.Data;

public class Signin extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin, container, false);

//        View rootView = inflater.inflate(R.layout.signin, null); // 先解析file.xml布局，得到一个view
        Button button_signin = (Button) view.findViewById(R.id.button_signin_confirm);
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"登录",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), Data.class);
                startActivity(intent);
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


}