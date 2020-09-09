package com.example.guanguannfc.view.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.guanguannfc.R;

import java.util.List;

public class TreeAdapter  extends ArrayAdapter<TreeView> {
    private int resourceId;
    public TreeAdapter(Context context, int textViewResourceId, List<TreeView> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position , View convertView, ViewGroup parent){
        TreeView treeView =getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView actType= view.findViewById(R.id.text_acta);
        TextView starttime= view.findViewById(R.id.text_starttime1);
        TextView endtime= view.findViewById(R.id.text_endttime1);
        TextView len= view.findViewById(R.id.text_long1);
        actType.setText(treeView.getActType());
        starttime.setText(treeView.getStartTime());
        endtime.setText(treeView.getEndTime());
        len.setText(treeView.getLen());
        return view;

    }

}
