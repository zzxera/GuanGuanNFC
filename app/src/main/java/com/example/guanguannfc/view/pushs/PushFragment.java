package com.example.guanguannfc.view.pushs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.userManagement.UserInfo;

import java.util.ArrayList;
import java.util.List;


public class PushFragment extends Fragment {

    private View view;
    private String userName;
    private TextView tv_show;
    private List<PushItem> pushItemList = new ArrayList<PushItem>();
    private ListView lv_pushs;
    private String[][] pushs1;
    private PushAdapter pushAdapter;
    private UserInfo userInfo;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_push, container, false);

//        Toast.makeText(getActivity(),"用户名"+userName,Toast.LENGTH_LONG).show();

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            userName = bundle.getString("username");
//            isCount = bundle.getBoolean("isCount");
        }

        lv_pushs= view.findViewById(R.id.lv_pushs);
        getPushs();
        initPushs(pushs1);

        pushAdapter = new PushAdapter(getActivity(),R.layout.item_push,pushItemList);
        lv_pushs.setAdapter(pushAdapter);
        return view;
    }
    private void initPushs(String[][] array){
        pushItemList.clear();
//        String[] push1 ={"时间管理的重要性","爱吃肉","当下社会的生活节奏越来越快，掌握好的事件间管理方式能够为大家带来以下好处...",Integer.toString(R.drawable.img_time1)};
        for (int i = 0;i< array.length;i++){
            PushItem pushItem1 = new PushItem(array[i],R.drawable.img_time1);
            pushItemList.add(pushItem1);
        }


    }
    private void getPushs(){
        if (userInfo.Push()!=null){
            pushs1 = userInfo.Push();
        }
    }
}
