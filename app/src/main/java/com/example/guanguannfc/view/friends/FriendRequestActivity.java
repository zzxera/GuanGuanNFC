package com.example.guanguannfc.view.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guanguannfc.R;
import com.example.guanguannfc.controller.userManagement.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity {
    private String userName;

    private List<FriendRequestItem> friendRequestItemsList = new ArrayList<FriendRequestItem>();
    private FriendRequestAdapter friendRequestAdapter ;
    private ListView lv_friendRequest;

    private Friend friend;
    private String[][] arr_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        //      获取传入数值
        Intent mainIntent=getIntent();
        userName=mainIntent.getStringExtra("username");

        lv_friendRequest = findViewById(R.id.lv_friendRequest);

        getRequestList();
        initList();
        friendRequestAdapter = new FriendRequestAdapter(FriendRequestActivity.this,R.layout.item_friendrequest,friendRequestItemsList);
        lv_friendRequest.setAdapter(friendRequestAdapter);
    }


    private void initList(){
        friendRequestItemsList.clear();
//        for (int i = 0;i<6;i++){
//            String[] info = {"用户"+i,"你好，我是用户"+i};
//            FriendRequestItem friendRequestItem = new FriendRequestItem(info);
//            friendRequestItemsList.add(friendRequestItem);
//        }

        if (arr_request!=null){
            for (int i=0;i<arr_request.length;i++){
                FriendRequestItem friendRequestItem = new FriendRequestItem(arr_request[i]);
                friendRequestItemsList.add(friendRequestItem);
            }
        }

        else {
            Toast.makeText(FriendRequestActivity.this,"无好友请求",Toast.LENGTH_LONG).show();
        }

    }

    private void getRequestList() {
        friend = new Friend(this);
        arr_request = friend.friendapply("aaa");
        if (arr_request!=null){
            Toast.makeText(FriendRequestActivity.this,arr_request[0][0],Toast.LENGTH_LONG).show();

        }
    }

    private void checkClick(){
        lv_friendRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String friendName = arr_request[i][0];

            }
        });
    }
}
