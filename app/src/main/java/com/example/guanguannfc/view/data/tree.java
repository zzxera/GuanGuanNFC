package com.example.guanguannfc.view.data;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.EnergyTree.EnergyTree;
import com.example.guanguannfc.controller.EnergyTree.EnergyTreeTest;
import com.example.guanguannfc.controller.EnergyTree.model.BallModel;
import com.example.guanguannfc.controller.EnergyTree.model.TipsModel;
import com.example.guanguannfc.controller.dataVisualization.Allactivity;

import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.DataBaseTest.FakeData;
import com.example.guanguannfc.model.Helper.HelperActivity;

import com.example.guanguannfc.model.Initialization;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class tree extends AppCompatActivity {
    private String userName;
    private View view;
    public static final String TAG = "EnergyTree";
    int color =  0xffF3A5C2;
    private List<TreeView> tl = new ArrayList<>();
    private Calendar c=Calendar.getInstance();
    String [][] Data;
    private Allactivity allactivity = new Allactivity(this);

    private EnergyTree mWaterFlake;
    private List<BallModel> mBallList;
    private List<TipsModel> mTipsList;
    private Context context;
    DaoActSta daoActSta = new  DaoActSta(this);

    protected void onCreate(@Nullable Bundle savedInstanceState){
        view = this.getWindow().getDecorView();
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);

        setContentView(R.layout.tree);

        TreeAdapter adapter = new TreeAdapter(tree.this,R.layout.tree_view,tl);

        ListView listView = findViewById(R.id.treeview);
        listView.setAdapter(adapter);
        allactivity = new Allactivity(this);

        int s=c.get(Calendar.HOUR_OF_DAY);
//提取他的时钟值，int型
        if(s>= 12 && s<=18){
            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.night));
        }
        else {
            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.day));
            color = 0xff2D4D3C;
        }
        String str1="早上好";

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TreeView treeView1 = tl.get(position);
                //Toast.makeText(Notebook.this, group.getGroup_name(), Toast.LENGTH_SHORT).show();
                int positon1 = position+1;
                String pid = String.valueOf(positon1);
                //Toast.makeText(tree.this, String.valueOf(s), Toast.LENGTH_SHORT).show();

            }
            });

//        Initialization.initialization(this);
//        FakeData fakeData = new FakeData(this);
//        fakeData.insert();
        initView();

        mWaterFlake = findViewById(R.id.custom_view);

        view.post(new Runnable() {
            @Override
            public void run() {
                mWaterFlake.setModelList(mBallList,mTipsList);
            }
        });

        mWaterFlake.isCollectTips(false);
        mWaterFlake.setOnBallItemListener(new EnergyTree.OnBallItemListener() {
            @Override
            public void onItemClick(BallModel ballModel) {
                Toast.makeText(tree.this,"获得了"+ballModel.getValue()+"积分",Toast.LENGTH_SHORT).show();
                daoActSta.update(ballModel.getID(),1);
                //需要给活动记录插入一条已使用过的标记
            }
        });

        mWaterFlake.setOnTipsItemListener(new EnergyTree.OnTipsItemListener() {
            @Override
            public void onItemClick(TipsModel tipsModel) {
                Toast.makeText(tree.this,tipsModel.getContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView(){
        userName = getIntent().getStringExtra("userName");
        Initialization.initialization(this);
        FakeData fakeData = new FakeData(this);
        fakeData.insert();
        Data = allactivity.sortedactivity1(userName,"全部","最新活动在前");
        if (Data != null) {
            for (int a= 0; a<Math.min(10,Data.length); a++){
                //double f = Math.ceil(Integer.parseInt(Data[a][4])/50000);
                if(Data[a][0] .equals("学习")|| Data[a][0].equals("工作")){
                    if(Data[a][7].equals("0")){
                        TreeView t = new TreeView(Data[a][0],Data[a][1],Data[a][2],String.valueOf(2*Integer.parseInt(Data[a][4])/(30*60*10)),"未被收取");
                        tl.add(t);
                    }
                    else {
                        TreeView t2 = new TreeView(Data[a][0],Data[a][1],Data[a][2],String.valueOf(2*Integer.parseInt(Data[a][4])/(30*60*10)),"已被收取");
                        tl.add(t2);
                    }
                }
            }
        }

        mBallList = new ArrayList<>();
        ArrayList<HelperActivity> helperActivities = daoActSta.queryByLengthDesc(userName);
        if (helperActivities != null) {
            for (HelperActivity activity : helperActivities) {
                int ranked = activity.getIs_ranked();
                long time = activity.getLen_time();
                int id = activity.getId();
                Log.d(TAG, "当前活动ID为: " + Integer.toString(id));
                if (ranked == 0 && WEPoint(time+"") > 0){
                    String type = activity.getActivity_type();
                    if (type.equals("学习")) {
                        String point = Integer.toString(StudyPoint(time+""));
                        mBallList.add(new BallModel("积分",point,id,color+100));
                    }else if (type.equals("工作")){
                        String point = Integer.toString(WEPoint(time+""));
                        mBallList.add(new BallModel("积分",point,id,color-200));
                    }else if(type.equals("锻炼")){
                        String point = Integer.toString(WEPoint(time+""));
                        mBallList.add(new BallModel("积分",point,id,color+200));
                    }
                }
            }
        }
        //提示：积分规则
        mTipsList = new ArrayList<>();
        mTipsList.add(new TipsModel(" 学习半小时2分！"));
        mTipsList.add(new TipsModel(" 锻炼半小时1分！"));
        mTipsList.add(new TipsModel(" 工作半小时1分！"));
        mTipsList.add(new TipsModel(" 养成良好的习惯！"));
    }

    private int StudyPoint(String time){
        int Time = 2 * Integer.parseInt(time) / (30*60*10);
        return Time;
    }

    private int WEPoint(String time){
        int Time = Integer.parseInt(time) / (30*60*10);
        return Time;
    }

}
