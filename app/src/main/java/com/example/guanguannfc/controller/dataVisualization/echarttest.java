package com.example.guanguannfc.controller.dataVisualization;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guanguannfc.R;

public class echarttest extends AppCompatActivity {
    private EchartView EChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EChart = findViewById(R.id.EChart);
        EChart.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                refreshEChart();
            }
        });
    }

    private void refreshEChart(){
        Object[] x = new Object[]{
                "Mon", "Tue", "Wed", "Thu", "Fri"
        };
        Object[] y = new Object[]{
                820, 932, 901, 934, 1290
        };
        EChart.refreshEchartsWithOption(EchartOptionUtil.getBarChartOptions(x, y));
    }

}
