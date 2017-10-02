package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.FriendsAdapter;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.thing_class.Friends;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.LogUtil;
import com.example.administrator.myapplication.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FriendsActivity extends BaseActivity {
    private String[] friendsId;
    private List<User>  friendsList = new ArrayList<User>();
private  List<Friends> friends=new ArrayList<Friends>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // getSupportActionBar().hide();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initFriends();
       FriendsAdapter adapter = new FriendsAdapter(ApplicationUtil.getContext(), R.layout.friends_item, friends);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Friends friend = friends.get(position);
                Intent intent=new Intent(FriendsActivity.this,PersonActivity.class);
                intent.putExtra("id",friend.getId());
               startActivity(intent);
            }
        });

    }

    private void initFriends() {     //初始化朋友数据
        friendsId= StringUtil.httpArray(getIntent().getExtras().getString("friends"));
  getFriends(friendsId);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
private void getFriends(String[] friendsId) {
    for (int i = 0; i < friendsId.length; i++) {
        RequestBody body = new FormBody.Builder()
                .add("id", friendsId[i])
                .build();

        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                User user = HttpUtil.getSingleUser(response);
                friendsList.add(user);
                friends.add(new Friends(user.getId(),user.getName(), user.getImage()));
                LogUtil.d( user.getImage() + "<<<<<<<<<<<<<<");
            }
        });
    }
}
}


