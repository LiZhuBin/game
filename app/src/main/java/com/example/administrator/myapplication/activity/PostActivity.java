package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ForumAdapter;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PostActivity extends BaseActivity {
    private ForumItem[] forumItems={

    };
    private List<ForumItem> forumItemList=new ArrayList<>();
    private ForumAdapter adapter;
    private GridLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // getSupportActionBar().hide();

        setSupportActionBar(toolbar);

        initForum();
        initSwipeRecyclerView();
    }
    private  void initSwipeRecyclerView(){
        final SwipeMenuRecyclerView recyclerView=(SwipeMenuRecyclerView)findViewById(R.id.person_post_recycle);
        adapter=new ForumAdapter(forumItemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(ApplicationUtil.getContext(), ForumActivity.class));
            }
        });
        recyclerView.setLongPressDragEnabled(true); // 开启拖拽。
        //recyclerView.setItemViewSwipeEnabled(true); // 开启滑动删除。f
        OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                int fromPosition = srcHolder.getAdapterPosition();
                int toPosition = targetHolder.getAdapterPosition();

                // Item被拖拽时，交换数据，并更新adapter。
                Collections.swap(forumItemList, fromPosition, toPosition);
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int position = srcHolder.getAdapterPosition();
                // Item被侧滑删除时，删除数据，并更新adapter。
                forumItemList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
        // recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.VERTICAL, false));
        manager = new GridLayoutManager(ApplicationUtil.getContext(), 2);
        // 设置布局管理一条数据占用几行，如果是头布局则头布局自己占用一行
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                if (postion%5== 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        recyclerView.setLayoutManager(manager);
    }
    private void initForum() {
        forumItemList.clear();
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int index = random.nextInt(forumItems.length);
            forumItemList.add(forumItems[index]);
        }
    }
}
