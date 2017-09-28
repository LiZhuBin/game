package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.AddAdapter;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.thing_class.AddItem;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class PersonActivity extends BaseActivity {
    public static AddItem[] addItemses = {

    };
    @Bind(R.id.add_as_friend)
    Button addAsFriend;
    private List<AddItem> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    private GridLayoutManager manager;
    protected ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        // getSupportActionBar().hide();

        setSupportActionBar(toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addAsFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(PersonActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("您的好友请求已发送")
                        .setContentText("请等待对方同意")
                        .setConfirmText("好的")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();
            }
        });
        initAdd();
        initSwipeRecyclerView();
    }

    private void initSwipeRecyclerView() {
        final SwipeMenuRecyclerView recyclerView = (SwipeMenuRecyclerView) findViewById(R.id.person_activity_recycle);
        addAdapter = new AddAdapter(addList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApplicationUtil.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addAdapter);
        recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ActivityUtils.startActivity(PersonActivity.this, AddActivity.class);
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
                Collections.swap(addList, fromPosition, toPosition);
                addAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int position = srcHolder.getAdapterPosition();
                // Item被侧滑删除时，删除数据，并更新adapter。
                addList.remove(position);
                addAdapter.notifyItemRemoved(position);
            }
        };
        recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
    }

    private void initAdd() {
        addList.clear();
        for (int i = 0; i < addItemses.length; i++) {

            addList.add(addItemses[i]);
        }
    }


}
