package com.example.guanguannfc.view.management;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.R;
import com.example.guanguannfc.view.management.DisplaygoodsActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoxmanagementActivity extends AppCompatActivity {

    private PopupWindow mPopWindow;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxmanagement);
        Button btn1 =findViewById(R.id.Button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        Button btn3 = findViewById(R.id.Button1);
        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupWindow3();
                return false;
            }
        });
        Button btn4 = findViewById(R.id.btn_addbox);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow4();
            }
        });
    }
    private void showPopupWindow3() {
        //设置contentView
        View contentView = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_changebox, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        //显示PopupWindow
        View rootview = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    private void showPopupWindow4() {
        //设置contentView
        View contentView = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_addbox, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        Button btn2 =contentView.findViewById(R.id.btn_addgoods2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow2();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_displaygoods, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        Button btn2 =contentView.findViewById(R.id.btb_addgoods);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow2();
            }
        });

        ListView listView =contentView.findViewById(R.id.listview);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, this.getData(),
                R.layout.activity_listview1,
                new String[]{"tv_name","btn_c"},
                new int[]{R.id.tv_name,R.id.btn_c});
        listView.setAdapter(mSimpleAdapter);
        //显示PopupWindow
        View rootview = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private List<Map<String,Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map= new HashMap<String, Object>();
        map.put("tv_name","Apple");
        map.put("btn_c", R.id.btn_c);
        list.add(map);
        map.put("tv_name","Appe");
        map.put("btn_c", R.id.btn_c);
        list.add(map);
        map.put("tv_name","Appell");
        map.put("btn_c", R.id.btn_c);
        list.add(map);

        return list;
    }

    private void showPopupWindow2(){
        View contentView = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_addgoods, null);
        mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        View rootview = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }
    public void Searchgoods(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, SearchgoodsActivity.class);
        startActivity(intent);
    }
    public void Displaygoods(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplaygoodsActivity.class);
        startActivity(intent);
    }
    public void Boxtip(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, BoxtipActivity.class);
        startActivity(intent);
    }
    public void Timemanagement(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TimemanagementActivity.class);
        startActivity(intent);
    }
}
