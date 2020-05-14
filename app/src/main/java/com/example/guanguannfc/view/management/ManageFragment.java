package com.example.guanguannfc.view.management;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.dataManagement.ActivityManage;
import com.example.guanguannfc.controller.dataManagement.ThingManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageFragment extends Fragment {

    private PopupWindow mPopWindow;
    private String username;
    private ThingManage boxget;
    private String[][] box;
    private ConstraintLayout lay_box,lay_time,lay_search;

    private ExpandableListView expand_list_id;
    private Context context;
    private ActivityManage getact;
    private String[] groups;
    private ListView lv_search;
    private String name;
    private int num;
    private GridView gridView1;
    //Model：定义的数据


    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private String[][] childs;
    private String[] child;
    private String[] goodsname;
    private String[] goodsnum;
    private String boxName;
    private String [] boxnames;
    List<Act> childsq = new ArrayList<Act>();


    private View view;
    Context ctx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_boxmanagement, container, false);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            username = bundle.getString("username");
        }
        ctx = getActivity();
        checkClick();
//        Toast.makeText(getActivity(),"用户名"+username,Toast.LENGTH_LONG).show();
        boxget =new ThingManage(username,ctx);
        box=boxget.boxAndPosition();


        getact=new ActivityManage(username,ctx);
        initView();
        ImageView btn_changeact = view.findViewById(R.id.btn_changeact);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.expand_chidren_item, null);
        final Button btn_change_actname = contentView.findViewById(R.id.btn_change_actname);
        btn_changeact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_change_actname.setVisibility(View.INVISIBLE);
            }
        });
        ImageView addact = view.findViewById(R.id.iv_addact);
        addact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showaddact();
            }
        });


        gridView1= view.findViewById(R.id.box);
        boxnames= box[0];
        GridviewAdapter gridviewAdapter=new GridviewAdapter(getActivity(),boxnames);
        gridView1.setAdapter(gridviewAdapter);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showbox(position);
            }
        });


        lay_box=view.findViewById(R.id.layout_boxmanagement);
//      lay_box.setVisibility(View.VISIBLE);
        lay_time=view.findViewById(R.id.layout_timemanagement);
        lay_time.setVisibility(View.INVISIBLE);
        lay_search=view.findViewById(R.id.layout_search);
        lay_search.setVisibility(View.INVISIBLE);
        ListView lv_search = (ListView) view.findViewById(R.id.lv_search);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(getActivity(), this.getData20(),
                R.layout.activity_listview2,
                new String[]{"tvName","tv_shuliang"},
                new int[]{R.id.tvName,R.id.tv_shuliang});
        lv_search.setAdapter(mSimpleAdapter);





        ImageView addbox = view.findViewById(R.id.iv_addbox);
        addbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showaddbox();
            }
        });
        return view;





    }

    private void showchangebox() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_changebox, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    private void showaddbox() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_addbox, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        Button btn2 =contentView.findViewById(R.id.btn_addgoods2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showaddgood_box();
            }
        });
        ListView lv_goods=contentView.findViewById(R.id.lv_goods);
        SimpleAdapter ms=new SimpleAdapter(getActivity(),getData2(),
                R.layout.activity_lv_goods,
                new String[]{"tv_goods_name","tv_goods_shuliang"},
                new int[]{R.id.tv_goods_name,R.id.tv_goods_shuliang});
        lv_goods.setAdapter(ms);
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    private void showbox(final int num) {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_displaygoods, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        ListView listView =contentView.findViewById(R.id.listview);
        final TextView tv_boxname=contentView.findViewById(R.id.tv_boxname);
        tv_boxname.setText(box[0][num]);
        final String boxname=tv_boxname.getText().toString();
        String [][] thing=boxget.thingAndNumberInBox(box[0][num]);
        goodsname=thing[0];
        goodsnum=thing[1];
        MsimpleAdapter mSimpleAdapter = new MsimpleAdapter(getActivity(),goodsname,goodsnum);
        listView.setAdapter(mSimpleAdapter);
        Button btn2 =contentView.findViewById(R.id.btb_addgoods);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("boxname",boxname);
                showaddgoods(boxname);
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }


    private void showaddgoods(final String boxname){
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_addgoods, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        Button btn_add_goods=contentView.findViewById(R.id.btn_add_goods);
        btn_add_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed_name=contentView.findViewById(R.id.ed_name);
                EditText ed_num=contentView.findViewById(R.id.ed_num);
                name=ed_name.getText().toString();
                String i=ed_num.getText().toString();
                boxName=boxname;
                num=Integer.valueOf(i).intValue();
                boxget.addThings(boxName,name,num);
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    private void showaddgood_box(){
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_add_goods_box, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void showaddact() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_addact, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        //显示PopupWindow
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_changeact, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void initView() {
        groups=getact.getBigActivity(getActivity());
        for (int i =0;i<groups.length;i++){
            if (groups.length>1){
                child = getact.getSmallActivity(groups[i]);
                childsq.add(new Act(groups[i], child));
            }
        }
        expand_list_id=view.findViewById(R.id.expand_list_id);
        ExpandableListviewAdapter adapter=new ExpandableListviewAdapter(getActivity(),groups,childsq);
        expand_list_id.setAdapter(adapter);
        //默认展开第一个数组
        expand_list_id.expandGroup(0);
        //关闭数组某个数组，可以通过该属性来实现全部展开和只展开一个列表功能
        //expand_list_id.collapseGroup(0);
        expand_list_id.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                showToastShort(groups[groupPosition]);
                return false;
            }
        });
        //子视图的点击事件
        expand_list_id.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                showToastShort(childs[groupPosition][childPosition]);
                return true;
            }
        });


        //用于当组项折叠时的通知。
        expand_list_id.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                showToastShort("折叠了数据___"+groups[groupPosition]);
            }
        });
        //
        //用于当组项折叠时的通知。
        expand_list_id.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                showToastShort("展开了数据___"+groups[groupPosition]);
            }
        });

    }

    private void showToastShort(String s) {

    }


    public void checkClick(){
        view.findViewById(R.id.tv_boxmanage).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                lay_box.setVisibility(View.VISIBLE);
                lay_time.setVisibility(View.INVISIBLE);
                lay_search.setVisibility(View.INVISIBLE);
            }
        });
        view.findViewById(R.id.tv_timemanagement).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                lay_box.setVisibility(View.INVISIBLE);
                lay_time.setVisibility(View.VISIBLE);
                lay_search.setVisibility(View.INVISIBLE);
            }
        });
        view.findViewById(R.id.sv_goods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay_box.setVisibility(View.INVISIBLE);
                lay_time.setVisibility(View.INVISIBLE);
                lay_search.setVisibility(View.VISIBLE);
            }
        });

    }

    private List<Map<String,Object>> getData20() {
        List<Map<String, Object>> list = new ArrayList<Map<String ,Object>>();
        String [] name=new String[]{"化妆品","球类","笔","书"};
        String [] num = new String[]{"2","5","4","7"};
        for (int i=0;i<name.length;i++)
        {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("tvName",name[i]);
            map.put("tv_shuliang",num[i]);
            list.add(map);
        }
        return list;
    }
    private List<Map<String,Object>> getData2() {
        List<Map<String, Object>> list = new ArrayList<Map<String ,Object>>();
        String [] name=new String[]{"化妆品","球类","笔","书"};
        String [] num = new String[]{"2","5","4","7"};
        for (int i=0;i<name.length;i++)
        {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("tv_goods_name",name[i]);
            map.put("tv_goods_shuliang",num[i]);
            list.add(map);
        }
        return list;
    }









}
