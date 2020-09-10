package com.example.guanguannfc.view.friends;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.guanguannfc.R;
import com.example.guanguannfc.model.GuanContract;
import com.example.guanguannfc.model.util.HttpUtil;
import com.example.guanguannfc.model.util.ImgNameUtil;
import com.example.guanguannfc.view.data.DataShow;

import org.w3c.dom.Text;

import java.util.List;

public class FriendAdapter extends ArrayAdapter {
    private final int resourceId;
    private String username;
    private Drawable drawable;
    private Fragment fragment;


    public FriendAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<FriendItem> objects, Fragment fragment) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.username = username;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final FriendItem friendItem=(FriendItem)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId, null);
        final ImageView friendHead=view.findViewById(R.id.img_friendHead);

        TextView friendName = view.findViewById(R.id.tv_friendName);
        TextView friendLevel = view.findViewById(R.id.tv_friendLevel);
        friendHead.setImageResource(friendItem.getImageId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                drawable = HttpUtil.getImg(ImgNameUtil.getImgHeadName(friendItem.getFriendName()));
                if(drawable != null){
                    fragment.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            friendHead.setImageDrawable(drawable);
                        }
                    });
                }



            }
        }).start();
        friendName.setText(friendItem.getFriendName());
        friendLevel.setText(friendItem.getFriendLevel());
        return view;
    }


}
