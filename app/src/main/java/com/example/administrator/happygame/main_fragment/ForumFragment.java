package com.example.administrator.happygame.main_fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.activity.CameraFragmentMainActivity;
import com.example.administrator.happygame.activity.GetPhotoActivity;
import com.example.administrator.happygame.adapter.ForumAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.my_ui.GlideRoundTransform;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.thing_class.Images;
import com.example.administrator.happygame.util.ApplicationUtil;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.melnykov.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.TwoOptionsDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ForumFragment extends BaseFragment {
    protected WeakReference<View> mRootView;//缓存fragment数据
    @Bind(R.id.fab_forum)
    FloatingActionButton fabForum;
    @Bind(R.id.activity_add_forum_send_button)
    Button activityAddForumSendButton;
    @Bind(R.id.activity_add_forum_content_left)
    Button activityAddForumContentLeft;

    @Bind(R.id.forum_frame)
    FrameLayout forumFrame;
    @Bind(R.id.forum_spinner)
    Spinner forumSpinner;
    @Bind(R.id.forum_chooseImage)
    RoundedImageView forumChooseImage;

    private List<ForumItem> forumItemList = new ArrayList<>();
    private ForumAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
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
        ButterKnife.bind(this, mRootView.get());
        return mRootView.get();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData() {
        forumItemList = new ArrayList<ForumItem>();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressForum + "php/userData.php", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Forum> appList = HttpUtil.getListForum(response);
                for (final Forum forum : appList) {
                    // GlobalData.httpAddressActivity+activity.getImage(),
                    ClasstoItem.ForumToForumItem(forum, forumItemList);

                }

            }
        });
    }

    public void initRefresh(final View view) {
        forumSpinner.setDrawingCacheBackgroundColor(Color.RED);
        forumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String[] sorts = getResources().getStringArray(R.array.sorts);
                // Toast.makeText(MainActivity.this,sorts[pos],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.forum_refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // onCreate(null);
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

    private void initSwipeRecyclerView(View view) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.forum_recyclerView);
        adapter = new ForumAdapter(forumItemList);

        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        // recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_forum);
        fab.attachToRecyclerView(recyclerView);
        UiUtil.revealatFab(forumFrame, fab, activityAddForumContentLeft);
    }

    @OnClick(R.id.forum_chooseImage)
    public void onViewClicked() {
        final TwoOptionsDialog dialog = SmartisanDialog.createTwoOptionsDialog(getActivity());
        dialog.setTitle("请选择")
                .setOp1Text("拍照")   // 设置第一个选项的文本
                .setOp2Text("从相册中选择")   // 设置第二个选项的文本
                .show();
        dialog.setOnSelectListener(new TwoOptionsDialog.OnSelectListener() {
            @Override
            public void onOp1() {
                dialog.dismiss();
                Intent intent = new Intent(ApplicationUtil.getContext(),CameraFragmentMainActivity.class);
                startActivityForResult(intent,2);
            }

            @Override
            public void onOp2() {
                dialog.dismiss();
                Intent intent = new Intent(ApplicationUtil.getContext(),GetPhotoActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case 2 :
                switch (resultCode)
                {
                    case 1 :
                        Images one = (Images) data.getSerializableExtra("Return_images");
                        setHeadview(one.getPath()); //这里去获得返回来的数据 地址！然后获得图片
                        break;
                }
                break;
        }
    }
    public void setHeadview(String path)
    {
        //在这里去设置框的装饰！！！！！！！！！

        forumChooseImage.setBackground(null);
        forumChooseImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        forumChooseImage.setCornerRadius(20f);
        forumChooseImage.setBorderWidth(1f);
        forumChooseImage.setBorderColor(getResources().getColor(R.color.light_gray));
        Glide.with(this).load(path).transform(new GlideRoundTransform(ApplicationUtil.getContext(),10)).into(forumChooseImage);

    }
}