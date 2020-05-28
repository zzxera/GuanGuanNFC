package com.example.guanguannfc.view.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guanguannfc.R;

import org.w3c.dom.Text;

import java.util.List;

public class FriendRequestAdapter extends ArrayAdapter {
    private final int resourceId;
    Button refuse,agree;

    public FriendRequestAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<FriendRequestItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FriendRequestItem friendRequestItem = (FriendRequestItem) getItem(position);
        View view =  LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView name = view.findViewById(R.id.tv_friendName);
        final TextView remark = view.findViewById(R.id.tv_remark);
        refuse = view.findViewById(R.id.btn_refuse);
        agree = view.findViewById(R.id.btn_agree);


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
