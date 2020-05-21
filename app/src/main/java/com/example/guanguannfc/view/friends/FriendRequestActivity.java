package com.example.guanguannfc.view.friends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.guanguannfc.R;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity {

    private List<FriendRequestItem> friendRequestItemsList = new ArrayList<FriendRequestItem>();
    private FriendRequestAdapter friendRequestAdapter ;
    private ListView lv_friendRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
        initList();
        friendRequestAdapter = new FriendRequestAdapter(FriendRequestActivity.this,R.layout.item_friendrequest,friendRequestItemsList);

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
