package com.example.administrator.happygame.childfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aopa.greendao.UserDao;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.FriendsAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.Friends;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.LogUtil;
import com.example.administrator.happygame.util.MyApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CollectUserFragment extends BaseFragment {

    private  List<Friends> friends = new ArrayList<Friends>();
    FriendsAdapter adapter;

    @Bind(R.id.recycler)
    RecyclerView listview;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_recycle, container, false);
        ButterKnife.bind(this, view);
        initRecycle();
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(String currentQuery) {
        List<User> joes = GlobalData.mUserDao.queryBuilder()
                .where(UserDao.Properties.Name.like("%"+currentQuery+"%"))
                .orderAsc(UserDao.Properties.Name)
                .list();
        for(User user:joes){
            LogUtil.d(user.getName()+"   dd");
            friends.add(new Friends(user.getId(), user.getName(), user.getImage()));
        }

    }

    public static CollectUserFragment getInstance() {
        CollectUserFragment sf = new CollectUserFragment();
        return sf;
    }

    public void initRecycle() {
        adapter = new FriendsAdapter(friends);
        listview.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(MyApplication.getContext(), 3);
        listview.setLayoutManager(manager);
    }
public void initData(){

}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        friends.clear();
    }
}
