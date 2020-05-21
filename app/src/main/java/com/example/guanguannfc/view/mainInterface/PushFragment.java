package com.example.guanguannfc.view.mainInterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guanguannfc.R;

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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_push, container, false);

//        Toast.makeText(getActivity(),"用户名"+userName,Toast.LENGTH_LONG).show();
        lv_pushs= view.findViewById(R.id.lv_pushs);

        initPushs(pushs1);
        pushAdapter = new PushAdapter(getActivity(),R.layout.item_push,pushItemList);
        lv_pushs.setAdapter(pushAdapter);
        return view;
    }
    private void initPushs(String[][] array){
        pushItemList.clear();
        String[] push1 ={"时间管理的重要性","爱吃肉","当下社会的生活节奏越来越快，掌握好的事件间管理方式能够为大家带来以下好处...",Integer.toString(R.drawable.img_time1)};
        PushItem pushItem1 = new PushItem(push1);
        pushItemList.add(pushItem1);
        String[] push2 ={"如何有效的管理时间","不想起床","上期栏目介绍了时间管理的重要性，本期小编将会带领大家学习十条简单有效的时间管理方式，我们的口号是“摆脱拖延症！”",Integer.toString(R.drawable.img_time2)};
        PushItem pushItem2 = new PushItem(push2);
        pushItemList.add(pushItem2);
    }
}
