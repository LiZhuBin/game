package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ForumInfoAdapter;
import com.example.administrator.myapplication.my_ui.ActivityForumItem;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;

public class ForumActivity extends BaseActivity {

    private ForumItem one;
    private ActivityForumItem[] forumItems = new ActivityForumItem[]{
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),};
    private List<ActivityForumItem> forumItemList = new ArrayList<>();
    private ForumInfoAdapter adapter;
    private GridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // getSupportActionBar().hide();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initForumContent();
        initForum();
        initRecycle();
    }

    private void initRecycle() {
        final SwipeMenuRecyclerView recyclerView = (SwipeMenuRecyclerView) findViewById(R.id.activity_forum_recycler);
        adapter = new ForumInfoAdapter(forumItemList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initForumContent() {
        Intent intent = getIntent();
        ForumItem obj = (ForumItem) intent.getSerializableExtra("Object_forum");
        one = obj;
//        Glide.with(this).load(one.getForumImage()).into(iconImage);
//        username.setText(one.get);
    }

}
