package com.example.administrator.myapplication.child_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ForumInfoAdapter;
import com.example.administrator.myapplication.my_ui.ActivityForumItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ForumCommentFragment extends Fragment {
    private ActivityForumItem[] forumItems = new ActivityForumItem[]{
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),
            new ActivityForumItem(R.drawable.image_scrolling_head, "dddd", "fff", "ffff", "ffff"),};
    private List<ActivityForumItem> forumItemList = new ArrayList<>();
    private ForumInfoAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_forum_comment, container, false);
        initForum();
        initRecycle(view);

        return view;
    }
    private void initRecycle(View view) {

        final SwipeMenuRecyclerView recyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.activity_forum_recycler);
        adapter = new ForumInfoAdapter(forumItemList);
        LinearLayoutManager manager = new LinearLayoutManager(ApplicationUtil.getContext());
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
    public static ForumCommentFragment getInstance(String title) {
        ForumCommentFragment sf = new ForumCommentFragment();
        return sf;
    }

}
