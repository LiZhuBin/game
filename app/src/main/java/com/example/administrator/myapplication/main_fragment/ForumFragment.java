package com.example.administrator.myapplication.main_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.AddForumActivity;
import com.example.administrator.myapplication.activity.ForumActivity;
import com.example.administrator.myapplication.adapter.ForumAdapter;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.melnykov.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;


public class ForumFragment extends Fragment {

private ForumItem[] forumItems={
        new ForumItem("一起来玩吧","5","2",1,R.drawable.image_scrolling_head),

       };
    protected WeakReference<View> mRootView;//缓存fragment数据
    private List<ForumItem> forumItemList=new ArrayList<>();
    private ForumAdapter adapter;
    private GridLayoutManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
        View view = inflater.inflate(R.layout.forum_info, container, false);
            mRootView = new WeakReference<View>(view);
        //initFlowLayout(view);
        initForum();
        initSwipeRecyclerView(view,inflater,container);
        initRefresh(view);
        ButterKnife.bind(this, view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        return mRootView.get();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void initForum() {
        forumItemList.clear();
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int index = random.nextInt(forumItems.length);
            forumItemList.add(forumItems[index]);
        }
    }
    public void initRefresh(View view){
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.forum_refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }
private  void initSwipeRecyclerView(View view,LayoutInflater inflater, ViewGroup container){
    final SwipeMenuRecyclerView recyclerView=(SwipeMenuRecyclerView)view.findViewById(R.id.forum_recyclerView);
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
    recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
    FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_forum);
    fab.attachToRecyclerView(recyclerView);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityUtils.startActivity(getActivity(), AddForumActivity.class, R.anim.enter_anim, R.anim.slide_out_right);
        }
    });
}
}
