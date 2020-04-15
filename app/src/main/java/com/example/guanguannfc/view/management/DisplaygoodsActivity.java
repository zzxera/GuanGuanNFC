package com.example.guanguannfc.view.management;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplaygoodsActivity extends AppCompatActivity {
    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaygoods);
        listView = (ListView) findViewById(R.id.listview);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, this.getData(),
                R.layout.activity_listview1,
                new String[]{"tv_Name","btn_delet"},
                new int[]{R.id.tv_name});
        listView.setAdapter(mSimpleAdapter);
    }

    private List<? extends Map<String,?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("tv_name", "Apple");
        list.add(map);
        map.put("tv_name", "Appe");
        list.add(map);
        return list;

    }

}
