package com.example.guanguannfc.view.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guanguannfc.R;

import org.w3c.dom.Text;

import java.util.List;

public class FriendRequestAdapter extends ArrayAdapter {
    private final int resourceId;

    public FriendRequestAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<FriendRequestItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FriendRequestItem friendRequestItem = (FriendRequestItem) getItem(position);
        View view =  LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView name = view.findViewById(R.id.tv_friendName);
        TextView remark = view.findViewById(R.id.tv_remark);

        name.setText(friendRequestItem.getName());
        remark.setText(friendRequestItem.getRemark());

        return view;
    }
}
