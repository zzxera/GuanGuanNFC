package com.example.guanguannfc.view.management;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.dataManagement.ActivityManage;

public class TimemanagementActivity extends AppCompatActivity {

    private PopupWindow mPopWindow;
    private ExpandableListView expand_list_id;
    private String username;
    private Context context;
    private ActivityManage getact=new ActivityManage(username,this);
    private String[] groups;
    //Model：定义的数据


    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private String[][] childs;
    private String[] child;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timemanagement);
        initView();
        Button btn_changeact = findViewById(R.id.btn_changeact);
        View contentView = LayoutInflater.from(TimemanagementActivity.this).inflate(R.layout.expand_chidren_item, null);
        final Button btn_change_actname = contentView.findViewById(R.id.btn_change_actname);
        btn_changeact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_change_actname.setVisibility(View.INVISIBLE);
            }
        });
        ImageView addact =findViewById(R.id.iv_addact);
        addact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow2();
            }
        });

    }


    private void showPopupWindow2() {
        //设置contentView
        View contentView = LayoutInflater.from(TimemanagementActivity.this).inflate(R.layout.activity_addact, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        //显示PopupWindow
        View rootview = LayoutInflater.from(TimemanagementActivity.this).inflate(R.layout.activity_changeact, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void initView() {
        groups=getact.getBigActivity(this);
        for (int i =0;i<groups.length;i++){
            child=getact.getSmallActivity(groups[i]);
            for(int j=0;j<child.length;j++){
                childs[i][j]=child[j];
            }
        }
        expand_list_id=findViewById(R.id.expand_list_id);
        ExpandableListviewAdapter adapter=new ExpandableListviewAdapter(this,groups,childs);
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




    public void Changeactivity(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChangeactActivity.class);
        startActivity(intent);
    }
    public void Boxmanagement(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, BoxmanagementActivity.class);
        startActivity(intent);
    }
}
