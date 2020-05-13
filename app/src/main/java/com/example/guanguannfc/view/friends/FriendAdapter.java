package com.example.guanguannfc.view.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guanguannfc.R;
import com.example.guanguannfc.view.data.DataShow;

import java.util.List;

public class FriendAdapter extends ArrayAdapter {
    private final int resourceId;

    public FriendAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<FriendItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FriendItem friendItem=(FriendItem)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView friendHead=view.findViewById(R.id.img_friendHead);
        TextView friendName = view.findViewById(R.id.tv_friendName);
        friendHead.setImageResource(friendItem.getImageId());
        friendName.setText(friendItem.getFriendName());
        return view;
    }
}
