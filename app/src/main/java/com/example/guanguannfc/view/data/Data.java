package com.example.guanguannfc.view.data;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.guanguannfc.R;

import com.example.guanguannfc.view.loginAndLogon.LoginActivity;
import com.example.guanguannfc.view.management.Boxmanagement;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Data extends AppCompatActivity {



//    private Datashow frag_datashow = new Datashow();
//    private DataShow frag_datashow = new DataShow();

    private List<DataShow> dataShowList = new ArrayList<DataShow>();
    private ListView actlist;
    private WebView webView;
    private Spinner spinner_times,spinner_types;
    private ConstraintLayout lay_datashow,lay_actshow,lay_time,lay_personset;
    private Button bt_starttime,bt_endtime,bt_acttype,bt_confirmtime,bt_person,bt_manage,bt_quit;
    private TextView txt_prompt;
    private String[] dataType={"工作","学习","睡眠","娱乐","吃饭"};
    private String[] dataTime={"2h","3h","2h","0h","1h"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        initDataShow();
        DataShowAdapter dataShowAdapter = new DataShowAdapter(Data.this,R.layout.datashow_item,dataShowList);


        actlist=findViewById(R.id.listview_actlist);
        webView=findViewById(R.id.webview_acts);
        spinner_times=(Spinner)findViewById(R.id.spinner_time);
        spinner_types=(Spinner)findViewById(R.id.spinner_type);
        lay_datashow=findViewById(R.id.layout_show);
        lay_actshow=findViewById(R.id.layout_allact);
        lay_actshow.setVisibility(View.INVISIBLE);
        lay_time=findViewById(R.id.layout_chosetime);
        lay_time.setVisibility(View.INVISIBLE);
        lay_personset=findViewById(R.id.layout_person);
        lay_personset.setVisibility(View.INVISIBLE);
        bt_starttime=findViewById(R.id.button_starttime);
        bt_endtime=findViewById(R.id.button_endtime);
        bt_acttype=findViewById(R.id.button_acttype);
        bt_confirmtime=findViewById(R.id.button_time_confirm);
        bt_person=findViewById(R.id.button_personset);
        bt_manage=findViewById(R.id.button_manage);
        bt_quit=findViewById(R.id.button_quit);
        txt_prompt=findViewById(R.id.text_prompt);

        actlist.setAdapter(dataShowAdapter);


        spinner_times.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cardNumber = Data.this.getResources().getStringArray(R.array.times)[position];
                Toast.makeText(Data.this, "" + cardNumber, Toast.LENGTH_SHORT).show();
                if (position==3){
                    lay_time.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cardNumber = Data.this.getResources().getStringArray(R.array.types)[position];
                Toast.makeText(Data.this, "" + cardNumber, Toast.LENGTH_SHORT).show();
                if (position==0){
                    webView.setVisibility(View.INVISIBLE);
                    actlist.setVisibility(View.VISIBLE);
                }
                else{
                    webView.setVisibility(View.VISIBLE);
                    actlist.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initDataShow(){
        for(int i=0;i<dataType.length;i++){
            DataShow dataShow = new DataShow(dataType[i],dataTime[i]);
            dataShowList.add(dataShow);
        }
    }

    public void click(View v){
        int id=v.getId();
        switch (id) {
            case R.id.text_show:
                lay_datashow.setVisibility(View.VISIBLE);
                lay_actshow.setVisibility(View.INVISIBLE);
                break;
            case R.id.text_allact:
                lay_datashow.setVisibility(View.INVISIBLE);
                lay_actshow.setVisibility(View.VISIBLE);
                break;
            case R.id.button_starttime:
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String starttime =  year + "-" + (month + 1) + "-" + dayOfMonth ;
                        Toast.makeText(Data.this, starttime, Toast.LENGTH_SHORT).show();
                        bt_starttime.setText(starttime);
                    }
                }
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_endtime:
                Calendar calendar1 = Calendar.getInstance();
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String endtime =  year + "-" + (month + 1) + "-" + dayOfMonth ;
                        Toast.makeText(Data.this, endtime, Toast.LENGTH_SHORT).show();
                        bt_endtime.setText(endtime);
                    }
                }
                        , calendar1.get(Calendar.YEAR)
                        , calendar1.get(Calendar.MONTH)
                        , calendar1.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button_acttype:
                PopupMenu actmenu=new PopupMenu(Data.this,v);
                actmenu.inflate(R.menu.actmenu);
                actmenu.show();
                actmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String acttype=(String)menuItem.getTitle();
                        bt_acttype.setText(acttype);
//                        switch (menuItem.getItemId()){
//                            case R.id.all:
//                                bt_acttype.setText("全部");
//                                break;
//                            case R.id.work:
//                                bt_acttype.setText("工作");
//                                break;
//                        }
                        return false;
                    }
                });
                break;
            case R.id.button_time_confirm:
                lay_time.setVisibility(View.INVISIBLE);
                break;
            case R.id.button_personset:
                if(lay_personset.getVisibility()==View.VISIBLE){
                    lay_personset.setVisibility(View.INVISIBLE);
                }
                else{
                    lay_personset.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.button_manage:

                Intent intent1 = new Intent();
                intent1.setClass(Data.this, Boxmanagement.class);
                startActivity(intent1);
                break;
            case R.id.button_quit:
                Intent intent2 = new Intent();
                intent2.setClass(Data.this, LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.text_prompt:
                Intent intent3 = new Intent();
                intent3.setClass(Data.this, ClockActivity.class);
                startActivity(intent3);

                break;

        }

        }


    }



