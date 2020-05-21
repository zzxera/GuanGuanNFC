package com.example.guanguannfc.view.friends;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanguannfc.R;

import java.util.ArrayList;
import java.util.List;

public class FrendFragment extends Fragment {

    private View view;
    private ConstraintLayout cl_friend,cl_friendAct;
    private TextView tv_friend,tv_friendAct;
    private List<FriendItem> friendItemsList=new ArrayList<FriendItem>();
    private List<FriendActItem> friendActItemList = new ArrayList<FriendActItem>();
    private ListView lv_friends,lv_friendAct;
    private FriendAdapter friendAdapter;
    private FriendActAdapter friendActAdapter;
    private String[][] act2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_frend, container, false);

        checkClick();
//        Toast.makeText(getActivity(),"用户名"+userName,Toast.LENGTH_LONG).show();
        initView();
        initFriends();
        initFriendAct(act2);
        friendAdapter = new FriendAdapter(getActivity(),R.layout.item_friend,friendItemsList);
        lv_friends.setAdapter(friendAdapter);
        friendActAdapter = new FriendActAdapter(getActivity(),R.layout.item_friendact,friendActItemList);
        lv_friendAct.setAdapter(friendActAdapter);
        return view;

    }
    private void initView(){
        cl_friend=view.findViewById(R.id.cl_frinds);
        cl_friendAct=view.findViewById(R.id.cl_frindAct);
        tv_friend=view.findViewById(R.id.text_frinds);
        tv_friendAct=view.findViewById(R.id.text_frindAct);

        lv_friends = view.findViewById(R.id.lv_friendList);
        lv_friendAct=view.findViewById(R.id.lv_friendActList);

    }

    private void initFriends(){
        friendItemsList.clear();
        for (int i = 0;i<20;i++){
            FriendItem friendItem=new FriendItem("好朋友"+(i+1),R.drawable.img_head);
            friendItemsList.add(friendItem);
        }

    }

    private void initFriendAct(String[][] array){
        friendActItemList.clear();
//        for(int i=0;i<array.length;i++){
//            FriendActItem friendActItem = new FriendActItem(array[i]);
//            friendActItemList.add(friendActItem);
//        }

        for (int i = 0;i<10;i++){
            String[][] act1={{"好朋友"+(i+1),5+"","记录生活的点点滴滴","工作","2020-5-11","15时47分3秒","16时47分3秒","1时0分0秒","2020-05-21",Integer.toString(R.drawable.img_head)}};
            FriendActItem friendActItem = new FriendActItem(act1[0]);
            friendActItemList.add(friendActItem);
        }


    }

    private void checkClick(){
        view.findViewById(R.id.text_frinds).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                cl_friend.setVisibility(View.VISIBLE);
                cl_friendAct.setVisibility(View.INVISIBLE);
                tv_friend.setTextColor(Color.RED);
                tv_friendAct.setTextColor(R.color.colorgray);
            }
        });
        view.findViewById(R.id.text_frindAct).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                cl_friend.setVisibility(View.INVISIBLE);
                cl_friendAct.setVisibility(View.VISIBLE);
                tv_friendAct.setTextColor(Color.RED);
                tv_friend.setTextColor(R.color.colorgray);
            }
        });
    }
}
