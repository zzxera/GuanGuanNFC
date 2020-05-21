package com.example.guanguannfc.view.management;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.guanguannfc.R;
import com.example.guanguannfc.view.HomePageActivity;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchgoodsActivity extends AppCompatActivity {
    private ListView lv_search;
    private String username;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchgoods);
        Bundle bundle = this.getIntent().getExtras();
        username=bundle.getString("userName");
        lv_search = (ListView) findViewById(R.id.lv_search);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, this.getData20(),
                R.layout.activity_listview2,
                new String[]{"tvName","tv_shuliang"},
                new int[]{R.id.tvName,R.id.tv_shuliang});
        lv_search.setAdapter(mSimpleAdapter);
        TextView tv_boxmanagement2 = findViewById(R.id.tv_boxmanagement2);


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
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("userName",username);
        intent.putExtras(bundle);
        this.setResult(2,intent);
        this.finish();
    }


}
