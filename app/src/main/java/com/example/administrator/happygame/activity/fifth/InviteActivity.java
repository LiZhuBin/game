package com.example.administrator.happygame.activity.fifth;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.InviteAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.thing_class.Friends;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import lib.homhomlib.design.SlidingLayout;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;

public class InviteActivity extends BaseActivity {

    @Bind(R.id.toolbar_text)
    TextView toolbarText;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.toolbar_edit)
    Toolbar toolbarEdit;
    @Bind(R.id.layout_recycle_view)
    RecyclerView layoutRecycleView;
    @Bind(R.id.slidingLayout)
    SlidingLayout slidingLayout;
    private String[] friendsId;
    private static List<Friends> friends = new ArrayList<Friends>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        ButterKnife.bind(this);
        btnAdd.setText("确定");
        toolbarText.setText("邀请好友");

        setSupportActionBar(toolbarEdit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        InviteAdapter adapter = new InviteAdapter(friends);

getFriends(StringUtil.httpArray(UserFragment.me.getFriends()));
        layoutRecycleView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(MyApplication.getContext(), 3);
        layoutRecycleView.setLayoutManager(manager);
    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {

        new SweetAlertDialog(InviteActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                .setContentText("等待对方加入")
                .setTitleText("邀请成功")
                .setConfirmText("确定")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        sDialog.cancel()      ;

                    }
                })
                .show();

    }
    private void getFriends(String[] friendsId) {
        friends.clear();
        for (int i = 0; i < friendsId.length; i++) {
            User user = mUserDao.load(friendsId[i]);
            friends.add(new Friends(user.getId(), user.getName(), user.getImage()));

        }
    }
}
