package com.example.guanguannfc.view.management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanguannfc.R;

public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mlayout;
    private String [] boxname;
    public GridviewAdapter(Context context,String [] boxname){
        this.context=context;
        this.boxname=boxname;
        mlayout=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return boxname.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            //填写ListView的图标和标题等控件的来源，来自于layout_list_item这个布局文件
            //把控件所在的布局文件加载到当前类中
            convertView = mlayout.inflate(R.layout.layout_grid_item,null);
            //生成一个ViewHolder的对象
            holder = new ViewHolder();
            //获取控件对象
            holder.Grid_imageview=convertView.findViewById(R.id.grid_IV_Id);
            holder.Grid_textview=convertView.findViewById(R.id.grid_TV_Id);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //修改空间属性
        holder.Grid_textview.setText(boxname[position]);

        //加载第三方网络图片
        return convertView;
    }
    static class ViewHolder{
        public ImageView Grid_imageview;
        public TextView Grid_textview;
    }
}
