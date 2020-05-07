package com.example.guanguannfc.view.management;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.dataManagement.ThingManage;

public class MsimpleAdapter extends BaseAdapter {
    private PopupWindow mPopWindow;
    private Context context;
    private String [] goodsname;
    private String [] goodsnum;
    private String thingName;
    private String boxName;
    private ThingManage boxget;
    public MsimpleAdapter(Context context,String[] goodsname,String[] goodsnum){
        this.context=context;
        this.goodsname=goodsname;
        this.goodsnum=goodsnum;
    }
    @Override
    public int getCount() {
        return goodsname.length;
    }

    @Override
    public Object getItem(int i) {
        return goodsname[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final ViewHolder ViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview3,parent,false);
            ViewHolder = new ViewHolder();
            ViewHolder.tv_goods_name = (TextView)convertView.findViewById(R.id.tv_goods_name);
            ViewHolder.btn_change_num=convertView.findViewById(R.id.btn_change_num);
            ViewHolder.tv_goods_shuliang =convertView.findViewById(R.id.tv_goods_shuliang);
            convertView.setTag(ViewHolder);

        }else {
            ViewHolder = (ViewHolder) convertView.getTag();
        }
        ViewHolder.tv_goods_name.setText(goodsname[position]);
        ViewHolder.tv_goods_shuliang.setText(goodsnum[position]);
        ViewHolder.btn_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thingName=ViewHolder.tv_goods_name.getText().toString();
                boxget.deleteThings(boxName,thingName);
            }
        });
        ViewHolder.btn_change_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow14();
            }
            private void showPopupWindow14() {
                View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview4,null);
                mPopWindow = new PopupWindow(contentView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                //设置各个控件的点击响应
                //显示PopupWindow
                View rootview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_boxmanagement,null);
                mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView tv_goods_name;
        TextView tv_goods_shuliang;
        Button btn_change_num;
        Button btn_delet;
        TextView tv_boxname;
    }

}
