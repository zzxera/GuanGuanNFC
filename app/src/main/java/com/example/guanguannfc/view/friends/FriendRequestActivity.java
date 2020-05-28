package com.example.guanguannfc.view.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.guanguannfc.R;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity {
    private String userName;

    private List<FriendRequestItem> friendRequestItemsList = new ArrayList<FriendRequestItem>();
    private FriendRequestAdapter friendRequestAdapter ;
    private ListView lv_friendRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        //      获取传入数值
        Intent mainIntent=getIntent();
        userName=mainIntent.getStringExtra("username");


        lv_friendRequest = findViewById(R.id.lv_friendRequest);
        initList();
        friendRequestAdapter = new FriendRequestAdapter(FriendRequestActivity.this,R.layout.item_friendrequest,friendRequestItemsList);
        lv_friendRequest.setAdapter(friendRequestAdapter);
    }

    private void initList(){
        friendRequestItemsList.clear();
        for (int i = 0;i<6;i++){
            String[] info = {"用户"+i,"你好，我是用户"+i};
            FriendRequestItem friendRequestItem = new FriendRequestItem(info);
            friendRequestItemsList.add(friendRequestItem);
        }

    }
}
