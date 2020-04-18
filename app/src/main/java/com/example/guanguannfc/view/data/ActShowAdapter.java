package com.example.guanguannfc.view.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guanguannfc.R;

import java.util.List;

public class ActShowAdapter extends ArrayAdapter {
    private final int resourceId;

    public ActShowAdapter(@NonNull Context context, int TextViewResourceId, @NonNull List objects) {
        super(context, TextViewResourceId, objects);
        resourceId=TextViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ActShow actShow = (ActShow) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView tv_actType = view.findViewById(R.id.text_act1);
        TextView tv_startTime = view.findViewById(R.id.text_starttime);
        TextView tv_endTime = view.findViewById(R.id.text_endttime);
        TextView tv_date = view.findViewById(R.id.text_date);
        TextView tv_len = view.findViewById(R.id.text_long);
        TextView tv_name = view.findViewById(R.id.text_name);

        tv_actType.setText(actShow.getActType());
        tv_startTime.setText(actShow.getStartTime());
        tv_endTime.setText(actShow.getEndTime());
        tv_len.setText(actShow.getLen());
        tv_date.setText(actShow.getDate());
        tv_name.setText(actShow.getName());

        return view;

    }
}
