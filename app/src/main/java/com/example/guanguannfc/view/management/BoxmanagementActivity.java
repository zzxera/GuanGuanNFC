package com.example.guanguannfc.view.management;

import android.app.ActionBar;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.dataManagement.ThingManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoxmanagementActivity extends AppCompatActivity {

    private PopupWindow mPopWindow;
    private String username;
    private ThingManage boxget;
    private String[][] box;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxmanagement);
        Bundle bundle = this.getIntent().getExtras();
        username=bundle.getString("userName");
        boxget =new ThingManage(username,this);
        box=boxget.boxAndPosition();
        TextView tv_timemanagement =findViewById(R.id.tv_timemanagement);
        tv_timemanagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoxmanagementActivity.this, TimemanagementActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("userName",username);
                startActivity(intent);
            }
        });
        ImageView iv_box1 =findViewById(R.id.iv_box1);
        iv_box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box[0][0]!=null){
                    showPopupWindow();
                }
                else {
                    showPopupWindow4();
                }
            }
        });
        ImageView iv_box_1= findViewById(R.id.iv_box1);
        iv_box_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupWindow3();
                return false;
            }
        });

        ImageView iv_box2 =findViewById(R.id.iv_box2);
        iv_box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box[0][1]!=null){
                    showPopupWindow5();
                }
                else {
                    showPopupWindow4();
                }
            }
        });
        ImageView iv_box3 =findViewById(R.id.iv_box3);
        iv_box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box[0][2]!=null){
                    showPopupWindow();
                }
                else {
                    showPopupWindow4();
                }
            }
        });

        ImageView iv_box4 =findViewById(R.id.iv_box4);
        iv_box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box[0][3]!=null){
                    showPopupWindow();
                }
                else {
                    showPopupWindow4();
                }
            }
        });

        ImageView iv_box5 =findViewById(R.id.iv_box5);
        iv_box5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box[0][4]!=null){
                    showPopupWindow();
                }
                else {
                    showPopupWindow4();
                }
            }
        });

        ImageView iv_box6 =findViewById(R.id.iv_box6);
        iv_box6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(box[0][5]!=null){
                    showPopupWindow();
                }
                else {
                    showPopupWindow4();
                }
            }
        });






        ImageView addbox = findViewById(R.id.iv_addbox);
        addbox.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_boxname=contentView.findViewById(R.id.tv_boxname);
        tv_boxname.setText(box[0][0]);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, this.getData1(),
                R.layout.activity_listview3,
                new String[]{"tv_goods_name","tv_goods_shuliang","btn_change_num"},
                new int[]{R.id.tv_goods_name,R.id.tv_goods_shuliang,R.id.btn_change_num});
        listView.setAdapter(mSimpleAdapter);
        //显示PopupWindow
        View rootview = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void showPopupWindow5() {
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
        TextView tv_boxname=contentView.findViewById(R.id.tv_boxname);
        tv_boxname.setText(box[0][1]);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, this.getData2(),
                R.layout.activity_listview3,
                new String[]{"tv_goods_name","tv_goods_shuliang","btn_change_num"},
                new int[]{R.id.tv_goods_name,R.id.tv_goods_shuliang,R.id.btn_change_num});
        listView.setAdapter(mSimpleAdapter);
        //显示PopupWindow
        View rootview = LayoutInflater.from(BoxmanagementActivity.this).inflate(R.layout.activity_boxmanagement, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }












    private List<Map<String,Object>> getData1() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String [] name;
        String [] num;
        String [][] thing=boxget.thingAndNumberInBox(box[0][0]);
        name=thing[0];
        num=thing[1];
        for (int j=0;j<name.length;j++)
        {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("tv_goods_name",name[j]);
            map.put("tv_goods_shuliang",num[j]);
            map.put("btn_change_num",R.id.btn_change_num);
            list.add(map);
        }
        return list;
    }

    private List<Map<String,Object>> getData2() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String [] name;
        String [] num;
        String [][] thing=boxget.thingAndNumberInBox(box[0][1]);
        name=thing[0];
        num=thing[1];
        for (int j=0;j<name.length;j++)
        {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("tv_goods_name",name[j]);
            map.put("tv_goods_shuliang",num[j]);
            map.put("btn_change_num",R.id.btn_change_num);
            list.add(map);
        }
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

}
