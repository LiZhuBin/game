package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.FriendsAdapter;
import com.example.administrator.myapplication.thing_class.Friends;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;


public class FriendsActivity extends BaseActivity {

    private List<Friends> friendsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // getSupportActionBar().hide();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initFriends();
        FriendsAdapter adapter = new FriendsAdapter(ApplicationUtil.getContext(), R.layout.friends_item, friendsList);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Friends friend = friendsList.get(position);
                Bundle bundle=new Bundle();
                bundle.putSerializable("frends",friend);
                ActivityUtils.startActivity(bundle,FriendsActivity.this,PersonActivity.class);
            }
        });

    }

    private void initFriends() {     //初始化朋友数据

        for (int i = 0; i < 2; i++) {
            Friends aa = new Friends("aa", R.drawable.icon_reading);
            friendsList.add(aa);
            Friends bb = new Friends("bb", R.drawable.ic_menu_slideshow);
            friendsList.add(bb);
            Friends cc = new Friends("cc", R.drawable.icon_username);
            friendsList.add(cc);

        }
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

}
