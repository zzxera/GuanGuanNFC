package com.example.guanguannfc.view.friends;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guanguannfc.R;

public class FrendFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_frend, container, false);

//        Toast.makeText(getActivity(),"用户名"+userName,Toast.LENGTH_LONG).show();

        return view;

    }
}
