package com.example.guanguannfc.view.loginAndLogon;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.guanguannfc.R;

public class Logon extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.logon, container, false);

        Button button_logon=view.findViewById(R.id.button_logon_confirm);
        button_logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"注册成功",Toast.LENGTH_LONG).show();
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.logandsign,new Signin())
                        .commit();
            }
        });




        return view;
    }
}
