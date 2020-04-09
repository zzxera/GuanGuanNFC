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

public class DataShowAdapter extends ArrayAdapter {
    private final int resourceId;

    public DataShowAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<DataShow> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       DataShow dataShow= (DataShow) getItem(position);
       View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
       TextView dataType= view.findViewById(R.id.datashow_type);
       TextView dataTime = view.findViewById(R.id.datashow_time);
       dataType.setText(dataShow.getType());
       dataTime.setText(dataShow.getTime());
       return view;

    }
}
