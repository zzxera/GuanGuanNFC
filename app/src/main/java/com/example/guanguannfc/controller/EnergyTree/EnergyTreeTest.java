package com.example.guanguannfc.controller.EnergyTree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.example.guanguannfc.model.Dao.DaoBox;
import com.example.guanguannfc.model.Dao.DaoBoxContent;
import com.example.guanguannfc.model.DataBaseTest.FakeData;
import com.example.guanguannfc.model.Helper.HelperActivity;
import com.example.guanguannfc.model.Helper.HelperActivityType;
import com.example.guanguannfc.model.Initialization;

import java.util.ArrayList;
import java.util.List;

public class EnergyTreeTest extends AppCompatActivity {

    public static final String TAG = "EnergyTree";
    private EnergyTree mWaterFlake;
    private List<BallModel> mBallList;
    private List<TipsModel> mTipsList;

    private String username;
    private Context context;

    DaoActSta daoActSta = new  DaoActSta(this);



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
                //Toast.makeText(EnergyTreeTest.this,"获得了"+ballModel.getValue()+"积分",Toast.LENGTH_SHORT).show();
                Toast.makeText(EnergyTreeTest.this,"获得了"+ballModel.getValue()+"积分",Toast.LENGTH_SHORT).show();

                daoActSta.update(ballModel.getID(),1);
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
        ArrayList<HelperActivity> helperActivities = daoActSta.queryByLengthDesc("bbb");
        for (HelperActivity activity : helperActivities) {
            int ranked = activity.getIs_ranked();
            long time = activity.getLen_time();
            int id = activity.getId();
            if (ranked == 0 && WEPoint(time+"") > 0){
                String type = activity.getActivity_type();
                if (type.equals("学习")) {
                    String point = Integer.toString(StudyPoint(time+""));
                    mBallList.add(new BallModel("积分",point,id));
                }else if (type.equals("工作")){
                    String point = Integer.toString(WEPoint(time+""));
                    mBallList.add(new BallModel("积分",point,id));
                }else if(type.equals("锻炼")){
                    String point = Integer.toString(WEPoint(time+""));
                    mBallList.add(new BallModel("积分",point,id));
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
