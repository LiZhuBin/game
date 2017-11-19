package com.example.administrator.happygame.childfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aopa.greendao.ActivityDao;
import com.aopa.greendao.UserDao;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.AddAdapter;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.thing_class.AddItem;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.administrator.happygame.util.GlobalData.mActivityDao;

public class CollectActivityFragment extends Fragment {

    private List<AddItem> addList = new ArrayList<>();
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    AddAdapter addAdapter;
    Boolean isSeek=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isSeek==false) {
            initData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_recycle, container, false);
        ButterKnife.bind(this, view);
        addAdapter = new AddAdapter(addList);
        recyclerView.setAdapter(addAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(String currentQuery) {
        isSeek=true;
        List<Activity> joes = GlobalData.mActivityDao.queryBuilder()
                .where(ActivityDao.Properties.Type.eq(currentQuery),
                        ActivityDao.Properties.Title.eq(currentQuery),
                        ActivityDao.Properties.Add_id.eq(UserDao.Properties.Name.eq((currentQuery))))
                .orderAsc(ActivityDao.Properties.Praise_num)
                .list();
        for (Activity activity:joes){

            ClasstoItem.ActivityToAddItem(activity,addList);
        }

    }
    public void initData(){

        String[] strings= StringUtil.httpArray(UserFragment.me.getCollectActivities());
        for (String string:strings){
            Activity activity=mActivityDao.load(string);
            ClasstoItem.ActivityToAddItem(activity,addList);
        }
    }
    public static CollectActivityFragment getInstance() {
        CollectActivityFragment sf = new CollectActivityFragment();
        return sf;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}