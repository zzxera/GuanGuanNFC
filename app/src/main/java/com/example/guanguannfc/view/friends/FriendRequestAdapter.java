package com.example.guanguannfc.view.friends;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guanguannfc.R;
import com.example.guanguannfc.model.util.HttpUtil;
import com.example.guanguannfc.model.util.ImgNameUtil;

import org.w3c.dom.Text;

import java.util.List;

public class FriendRequestAdapter extends ArrayAdapter {
    private final int resourceId;
    Button refuse,agree;
    private Activity activity;
    private Drawable drawable;

    public FriendRequestAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<FriendRequestItem> objects, Activity activity) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final FriendRequestItem friendRequestItem = (FriendRequestItem) getItem(position);
        View view =  LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView name = view.findViewById(R.id.tv_friendName);
        final TextView remark = view.findViewById(R.id.tv_remark);
        final ImageView imageView = view.findViewById(R.id.img_head);
        refuse = view.findViewById(R.id.btn_refuse);
        agree = view.findViewById(R.id.btn_agree);

        new Thread(new Runnable() {
            @Override
            public void run() {
                drawable = HttpUtil.getImg(ImgNameUtil.getImgHeadName(friendRequestItem.getName()));
                if (drawable != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageDrawable(drawable);
                        }
                    });
                }
            }
        }).start();
        imageView.setImageResource(R.drawable.img_head);
        name.setText(friendRequestItem.getName());
        remark.setText(friendRequestItem.getRemark());
        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnRefuseListener.onRefuseClick(position);
                refuse = view.findViewById(R.id.btn_refuse);
                refuse.setEnabled(false);
            }
        });

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnAgreeListener.onAgreeClick(position);
                agree = view.findViewById(R.id.btn_agree);
                agree.setEnabled(false);
            }
        });


        return view;
    }

    public Button getRefuse(){
        return refuse;
    }

    public Button getAgree(){
        return agree;
    }

//    拒绝按钮的借口
    public interface onRefuseListener{
        void onRefuseClick(int i);
    }

    private onRefuseListener mOnRefuseListener;

    public void setOnRefuseListener(onRefuseListener mOnRefuseListener){
        this.mOnRefuseListener = mOnRefuseListener;
    }

//    同意按钮的接口
    public interface onAgreeListener{
        void onAgreeClick(int i);
    }

    private onAgreeListener mOnAgreeListener ;

    public void setOnAgreeListener(onAgreeListener mOnAgreeListener){
        this.mOnAgreeListener = mOnAgreeListener;
    }

}
