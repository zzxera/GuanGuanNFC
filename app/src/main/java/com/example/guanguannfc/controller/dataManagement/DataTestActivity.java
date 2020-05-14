package com.example.guanguannfc.controller.dataManagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.nfcManagement.BaseNfcActivity;
import com.example.guanguannfc.controller.nfcManagement.NFCManage;

public class DataTestActivity extends BaseNfcActivity {

    public static final String TAG = "MyActivity";
    private Button mbtn_test;
    private TextView mtext_test;
    private String mTagText;
    private TextView ifo_NFC;
    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datatest);
        ifo_NFC = (TextView) findViewById(R.id.ifo_NFC);
        // NFC适配器，所有的关于NFC的操作从该适配器进行
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!ifNFCUse()) {
            return;
        }
        mbtn_test = findViewById(R.id.btn_test);
        mtext_test = findViewById(R.id.data_test);

        mbtn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataTestActivity.this, NFCtestActivity.class);
                startActivity(intent);
//                String time_01 = GetTime.getNowTime();
//                mtext_test.setText(time_01);
//                Log.d(TAG, "当前时间 时间戳: " + System.currentTimeMillis());
//                Log.d(TAG, "当前时间 完整时间 : " + GetTime.timeStampToDate(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 year: " + GetTime.getYearByTimeStamp(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 month: " + GetTime.getMonthByTimeStamp(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 day: " + GetTime.getDayByTimeStamp(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 Hour: " + GetTime.getHourByTimeStamp(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 minute: " + GetTime.getMinuteByTimeStamp(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 Second: " + GetTime.getSecondByTimeStamp(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 week: " + GetTime.getWeekByTimeStamp(System.currentTimeMillis()));
//                Log.d(TAG, "当前时间 今日开始时间戳: " + GetTime.getBeginTime("本日"));
//                Log.d(TAG, "当前时间 本周开始时间戳: " + GetTime.getBeginTime("本周"));
//                Log.d(TAG, "当前时间 本月开始时间戳: " + GetTime.getBeginTime("本月"));
////                Log.d(TAG, "当前时间 二维数组时间: " + GetTime.transString(System.currentTimeMillis())[1][1]);
//                //二维数组不能输出不知为何。
//                Log.d(TAG, "当前时间 得到时间戳: " + GetTime.getStringToDate("2020-4-13 22-00-00", "yyyy-MM-dd HH:mm:ss"));
            }
        });
    }


    @SuppressLint("MissingSuperCall")
    public void onNewIntent(Intent intent) {
//        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//        NdefMessage ndefMessage = new NdefMessage(
//                new NdefRecord[]{NFCManage.createTextRecord(NFCManage.setNFCNumberForAct("ooo"))}
//               );
//        boolean result = NFCManage.writeTag(ndefMessage, detectedTag);
//        if (result) {
//            Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "写入失败", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "NFCNumber: " + NFCManage.setNFCNumberForAct("ooo"));
//        }
    }
    protected Boolean ifNFCUse() {
        if (mNfcAdapter == null) {
            ifo_NFC.setText("设备不支持NFC！");
            return false;
        }
        if (mNfcAdapter != null && !mNfcAdapter.isEnabled()) {
            ifo_NFC.setText("请在系统设置中先启用NFC功能！");
            return false;
        }
        return true;
    }
}



