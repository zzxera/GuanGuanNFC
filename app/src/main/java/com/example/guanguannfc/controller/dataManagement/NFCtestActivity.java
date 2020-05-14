package com.example.guanguannfc.controller.dataManagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.nfcManagement.BaseNfcActivity;
import com.example.guanguannfc.controller.nfcManagement.NFCManage;

public class NFCtestActivity extends BaseNfcActivity {
    private String mTagText;
    public static final String TAG = "NFCTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfctest);
    }

    @SuppressLint("MissingSuperCall")
    public void onNewIntent(Intent intent){
        mTagText = NFCManage.readNfcTag(intent);
        if (mTagText.equals("activity600001")){
            Intent intent1 = new Intent(NFCtestActivity.this, DataTestActivity.class);
            startActivity(intent1);
        }else {
            Toast.makeText(this, "扫描失败", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "内部字符串为: " + NFCManage.readNfcTag(intent));
        }
    }
}

