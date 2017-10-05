package com.example.administrator.myapplication.main_fragment;

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
import com.example.administrator.myapplication.adapter.ForumAdapter;
import com.example.administrator.myapplication.been.Forum;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.StringUtil;
import com.melnykov.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ForumFragment extends Fragment {
    protected WeakReference<View> mRootView;//缓存fragment数据
    private List<ForumItem> forumItemList=new ArrayList<>();
    private ForumAdapter adapter;
    private GridLayoutManager manager;
    private static final String TAG = "ForumFragment";
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
            ButterKnife.bind(this, view);
        //initFlowLayout(view);
            initRefresh(view);
        initSwipeRecyclerView(view);

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
    private void initData(){

        forumItemList = new ArrayList<ForumItem>();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressForum+"php/userData.php",  new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Forum> appList = HttpUtil.getListForum(response);
                for (final Forum forum : appList) {
                    // GlobalData.httpAddressActivity+activity.getImage(),


                    ForumItem forumItem=new ForumItem(
                            forum.getTitle(), StringUtil.httpArrayStringLength(forum.getComment())+1,
                            forum.getLike(),forum.getImage(),forum.getId()
                    );
                    forumItemList.add(forumItem);
                }

            }
        });
    }

    public void initRefresh(final View view){
        initData();
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.forum_refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initRefresh(view);
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
private  void initSwipeRecyclerView(View view){
    final RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.forum_recyclerView);
    adapter=new ForumAdapter(forumItemList);
    recyclerView.setAdapter(adapter);
  //  recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
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
   // recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
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
