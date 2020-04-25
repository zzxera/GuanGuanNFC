package com.example.guanguannfc.view.management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.guanguannfc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchgoodsActivity extends AppCompatActivity {
    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchgoods);
        listView = (ListView) findViewById(R.id.listview);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, this.getData(),
                R.layout.activity_listview2,
                new String[]{"tvName","tv_shuliang"},
                new int[]{R.id.tvName,R.id.tv_shuliang});
        listView.setAdapter(mSimpleAdapter);
    }
    private List<Map<String,Object>> getData() {
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
    public void Timemanagement(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TimemanagementActivity.class);
        startActivity(intent);
    }
    public void Boxmanagement2(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, BoxmanagementActivity.class);
        startActivity(intent);
    }
}
