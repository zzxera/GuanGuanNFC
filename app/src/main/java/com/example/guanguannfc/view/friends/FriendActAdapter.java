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

import java.util.List;

public class FriendActAdapter extends ArrayAdapter {

    private final int resourceId;

    public FriendActAdapter(Context context, int textViewResourceId, List<FriendActItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FriendActItem friendActItem = (FriendActItem) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView friendHead=view.findViewById(R.id.img_friendHead);
        TextView friendName = view.findViewById(R.id.tv_friendName);
        TextView tv_level = view.findViewById(R.id.tv_level);
        TextView tv_shareContent = view.findViewById(R.id.tv_shareContent);
        TextView tv_actType = view.findViewById(R.id.text_act1);
        TextView tv_startTime = view.findViewById(R.id.text_starttime);
        TextView tv_endTime = view.findViewById(R.id.text_endttime);
        TextView tv_date = view.findViewById(R.id.text_date);
        TextView tv_len = view.findViewById(R.id.text_long);
        TextView tv_shareDate = view.findViewById(R.id.tv_shareDate);

        tv_level.setText(friendActItem.getLevel());
        tv_shareContent.setText(friendActItem.getShareContent());
        tv_actType.setText(friendActItem.getActType());
        tv_startTime.setText(friendActItem.getStartTime());
        tv_endTime.setText(friendActItem.getEndTime());
        tv_len.setText(friendActItem.getLen());
        tv_date.setText(friendActItem.getDate());
        friendHead.setImageResource(friendActItem.getImageId());
        friendName.setText(friendActItem.getFriendName());
        tv_shareDate.setText(friendActItem.getShareDate());
        return view;
    }
}
