package com.example.administrator.happygame.child_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.AddAdapter;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.thing_class.AddItem;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

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
        addAdapter = new AddAdapter(addList);
        recyclerView.setAdapter(addAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
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