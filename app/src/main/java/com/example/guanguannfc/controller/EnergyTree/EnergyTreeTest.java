package com.example.guanguannfc.controller.EnergyTree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.EnergyTree.model.BallModel;
import com.example.guanguannfc.controller.EnergyTree.model.TipsModel;
import com.example.guanguannfc.controller.dataVisualization.Allactivity;
import com.example.guanguannfc.controller.timeManagement.GetTime;
import com.example.guanguannfc.model.Dao.DaoActSta;
import com.example.guanguannfc.model.DataBaseTest.FakeData;
import com.example.guanguannfc.model.Initialization;

import java.util.ArrayList;
import java.util.List;

public class EnergyTreeTest extends AppCompatActivity {

    public static final String TAG = "EnergyTree";
    private EnergyTree mWaterFlake;
    private List<BallModel> mBallList;
    private List<TipsModel> mTipsList;
    private String username;

    String [][] Data;
    private Allactivity allactivity = new Allactivity(this);
    private DaoActSta daoActSta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_tree);
        Initialization.initialization(this);
        FakeData fakeData = new FakeData(this);
        fakeData.insert();
        initData();
        mWaterFlake = findViewById(R.id.custom_view);
        Button mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWaterFlake.setModelList(mBallList,mTipsList);
            }
        });
        mBtn.post(new Runnable() {
            @Override
            public void run() {
                mWaterFlake.setModelList(mBallList,mTipsList);
            }
        });

        mWaterFlake.isCollectTips(false);
        mWaterFlake.setOnBallItemListener(new EnergyTree.OnBallItemListener() {
                @Override
            public void onItemClick(BallModel ballModel) {
                Toast.makeText(EnergyTreeTest.this,"获得了"+ballModel.getValue()+"积分",Toast.LENGTH_SHORT).show();

                //需要给活动记录插入一条已使用过的标记
            }
        });

        mWaterFlake.setOnTipsItemListener(new EnergyTree.OnTipsItemListener() {
            @Override
            public void onItemClick(TipsModel tipsModel) {
                Toast.makeText(EnergyTreeTest.this,tipsModel.getContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mBallList = new ArrayList<>();
//        daoActSta.queryByTimeDesc(username,"学习");
//        daoActSta.queryByTimeDesc(username,"工作");
//        daoActSta.queryByTimeDesc(username,"锻炼");

        //需要的是大类Data[][0]，以及时间长度Data[][4]
        Data = allactivity.sortedactivity1("aaa","全部","最新活动在前");

        Log.d(TAG,"本条活动记录为:"+ Data[8][0]);
        Log.d(TAG,"本条活动记录为:"+ Data[9][1]);
        Log.d(TAG,"本条活动记录为:"+ Data[10][2]);
//        Log.d(TAG,"本条活动记录为:"+ Data[1][3]);
//        Log.d(TAG,"本条活动记录为:"+ Integer.toString(StudyPoint("18000000")));
//        Log.d(TAG,"本条活动记录为:"+ Data[1][5]);
//        Log.d(TAG,"本条活动记录为:"+ Data[1][6]);

//        Log.d(TAG, "本条活动记录为: " + daoActSta.queryByTimeDesc(username,"学习"));
        for (int n = 0; n <= 23; n++){
            if (Data[n][0].equals("学习")){
                String point = Integer.toString(StudyPoint(Data[n][4]));
                Log.d(TAG,"本条活动记录为:"+ point);
                mBallList.add(new BallModel("积分",point));
            }else if(Data[n][0].equals("工作")){
                String point = Integer.toString(WEPoint(Data[n][4]));
                Log.d(TAG,"本条活动记录为:"+ point);
                mBallList.add(new BallModel("积分",point));

            }else if(Data[n][0].equals("锻炼")){
                String point = Integer.toString(WEPoint(Data[n][4]));
                Log.d(TAG,"本条活动记录为:"+ point);
                mBallList.add(new BallModel("积分",point));
            }
        }

        //提示：积分规则
        mTipsList = new ArrayList<>();
        mTipsList.add(new TipsModel(" 学习半小时2分！"));
        mTipsList.add(new TipsModel(" 锻炼半小时1分！"));
        mTipsList.add(new TipsModel(" 工作半小时1分！"));
        mTipsList.add(new TipsModel(" 积分球最多攒8个！"));
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
