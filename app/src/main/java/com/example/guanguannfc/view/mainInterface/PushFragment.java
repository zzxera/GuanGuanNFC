package com.example.guanguannfc.view.mainInterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guanguannfc.R;


public class PushFragment extends Fragment {

    private View view;
    private String userName;
    private TextView tv_show;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_push, container, false);

//        Toast.makeText(getActivity(),"用户名"+userName,Toast.LENGTH_LONG).show();

        return view;
    }
}
