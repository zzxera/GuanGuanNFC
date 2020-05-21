package com.example.guanguannfc.view.homepage;

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

public class GoodAdapter extends ArrayAdapter {

    private final int resourceId;

    public GoodAdapter(@NonNull Context context, int TextViewResourceId, @NonNull List objects) {
        super(context, TextViewResourceId, objects);
        resourceId=TextViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        GoodItem goodItem = (GoodItem) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView name = view.findViewById(R.id.datashow_type);
        TextView num = view.findViewById(R.id.datashow_time);

        name.setText(goodItem.getGoodName());
        num.setText(goodItem.getGoodNum());

        return view;
    }
}
