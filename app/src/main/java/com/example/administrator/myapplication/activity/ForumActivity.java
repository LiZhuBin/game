package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ForumInfoAdapter;
import com.example.administrator.myapplication.my_ui.ActivityForumItem;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForumActivity extends BaseActivity {
    private ActivityForumItem[] forumItems= new ActivityForumItem[]{new ActivityForumItem(R.id.image_view,"dddd","fff","ffff","ffff"),
            new ActivityForumItem(R.id.image_view,"dddd","fff","ffff","ffff"),
            new ActivityForumItem(R.id.image_view,"dddd","fff","ffff","ffff"),
            new ActivityForumItem(R.id.image_view,"dddd","fff","ffff","ffff"),};
    private List<ActivityForumItem> forumItemList=new ArrayList<>();
    private ForumInfoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // getSupportActionBar().hide();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initForum();
        initRecycle();
    }
    private void initRecycle(){
        final SwipeMenuRecyclerView recyclerView=(SwipeMenuRecyclerView)findViewById(R.id.activity_forum_recycler);
        adapter=new ForumInfoAdapter(forumItemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
    private void initForum() {
        forumItemList.clear();
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int index = random.nextInt(forumItems.length);
            forumItemList.add(forumItems[index]);
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
