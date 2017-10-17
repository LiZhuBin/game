package com.example.administrator.happygame.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.FriendsAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.Friends;
import com.example.administrator.happygame.util.ApplicationUtil;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FriendsActivity extends BaseActivity {
    private static List<Friends> friends = new ArrayList<Friends>();
    Toolbar toolbar;
    String title;
    Handler handler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    FriendsAdapter adapter = new FriendsAdapter(friends);
                    RecyclerView listview = (RecyclerView) findViewById(R.id.list_view);

                    listview.setAdapter(adapter);
                    GridLayoutManager manager = new GridLayoutManager(ApplicationUtil.getContext(), 3);
                    listview.setLayoutManager(manager);
            }
        }
    };
    private String[] friendsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // getSupportActionBar().hide();
        title = getIntent().getExtras().getString("title");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initFriends();


    }

    private void initFriends() {     //初始化朋友数据

        friendsId = StringUtil.httpArray(getIntent().getExtras().getString("friends"));

        getFriends(friendsId);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getFriends(String[] friendsId) {
        friends.clear();
        for (int i = 0; i < friendsId.length; i++) {

            HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", friendsId[i], new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    User user = HttpUtil.getSingleUser(response);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    friends.add(new Friends(user.getId(), user.getName(), user.getImage()));

                }
            });
        }
    }
}


