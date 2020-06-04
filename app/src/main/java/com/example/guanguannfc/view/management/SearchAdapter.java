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
import android.widget.TextView;

import com.example.guanguannfc.R;

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private String [] boxnames;
    private PopupWindow mPopwindow;
    private String [] svboxnames;
    private int z;
    public SearchAdapter(Context context ,String[] svboxnames,String [] boxnames){
        this.context=context;
        this.svboxnames=svboxnames;
        this.boxnames=boxnames;
    }
    @Override
    public int getCount() {
        return svboxnames.length;
    }

    @Override
    public Object getItem(int i) {
        return svboxnames[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder ViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchlist,parent,false);
            ViewHolder = new ViewHolder();
            ViewHolder.tv_sv_boxname = (TextView)convertView.findViewById(R.id.tv_sv_boxname);
            convertView.setTag(ViewHolder);

        }else {
            ViewHolder = (ViewHolder) convertView.getTag();
        }
        ViewHolder.tv_sv_boxname.setText(svboxnames[position]);
        for(int s=0;s<boxnames.length;s++){
            if(boxnames[s]==svboxnames[position]){
                z=s;
            }
        }
        ViewHolder.tv_sv_boxname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
    static class ViewHolder {
        TextView tv_sv_boxname;
    }

}
