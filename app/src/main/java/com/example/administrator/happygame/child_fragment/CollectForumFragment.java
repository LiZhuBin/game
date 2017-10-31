package com.example.administrator.happygame.child_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.ForumAdapter;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.administrator.happygame.util.GlobalData.mForumDao;

public class CollectForumFragment extends Fragment {

    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    private ForumAdapter adapter;
    private List<ForumItem> forumItemList = new ArrayList<>();
    public static CollectForumFragment newInstance(String param1, String param2) {
        CollectForumFragment fragment = new CollectForumFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_recycle, container, false);
        ButterKnife.bind(this, view);
        adapter = new ForumAdapter(forumItemList);

        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        return view;
    }
    public void initData(){
        String[] strings= StringUtil.httpArray(UserFragment.me.getCollectForum());
        for (String string:strings){
            Forum forum=mForumDao.load(string);
            ClasstoItem.ForumToForumItem(forum, forumItemList);
        }
    }
    public static CollectForumFragment getInstance() {
        CollectForumFragment sf = new CollectForumFragment();
        return sf;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
