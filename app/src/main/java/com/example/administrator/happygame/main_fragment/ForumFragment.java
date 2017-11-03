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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.activity.GetPhotoActivity;
import com.example.administrator.happygame.activity.fifth.CameraFragmentMainActivity;
import com.example.administrator.happygame.adapter.ForumAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.my_ui.GlideRoundTransform;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.thing_class.Images;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.UiUtil;
import com.jaouan.revealator.Revealator;
import com.makeramen.roundedimageview.RoundedImageView;
import com.melnykov.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.TwoOptionsDialog;

import static com.example.administrator.happygame.util.GlobalData.mForumDao;


public class ForumFragment extends BaseFragment {
    protected WeakReference<View> mRootView;//缓存fragment数据
    @Bind(R.id.fab_forum)
    FloatingActionButton fabForum;

    @Bind(R.id.activity_add_forum_content_left)
    Button activityAddForumContentLeft;

    @Bind(R.id.forum_frame)
    FrameLayout forumFrame;
    @Bind(R.id.forum_spinner)
    Spinner forumSpinner;
    @Bind(R.id.forum_chooseImage)
    RoundedImageView forumChooseImage;
    @Bind(R.id.activity_add_forum_send_button)
    Button activityAddForumSendButton;

View view;
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
            view = inflater.inflate(R.layout.forum_info, container, false);
            mRootView = new WeakReference<View>(view);
            ButterKnife.bind(this, view);
            //initFlowLayout(view);
            initRefresh(view);
            initSwipeRecyclerView();
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
        for (Forum forum : mForumDao.loadAll()) {
            ClasstoItem.ForumToForumItem(forum, forumItemList);
        }

    }

    public void initRefresh(final View view) {
        forumSpinner.setDrawingCacheBackgroundColor(Color.RED);


        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.forum_refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // onCreate(null);
                initData();
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

    private void initSwipeRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.forum_recyclerView);
        adapter = new ForumAdapter(forumItemList);

        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        // recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_forum);
        fab.attachToRecyclerView(recyclerView);
        UiUtil.revealatFab(forumFrame, fab, activityAddForumContentLeft);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 2:
                switch (resultCode) {
                    case 1:
                        Images one = (Images) data.getSerializableExtra("Return_images");
                        setHeadview(one.getPath()); //这里去获得返回来的数据 地址！然后获得图片
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void setHeadview(String path) {
        //在这里去设置框的装饰！！！！！！！！！

        forumChooseImage.setBackground(null);
        forumChooseImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        forumChooseImage.setCornerRadius(20f);
        forumChooseImage.setBorderWidth(1f);
        forumChooseImage.setBorderColor(getResources().getColor(R.color.light_gray));
        Glide.with(this).load(path).transform(new GlideRoundTransform(MyApplication.getContext(), 10)).into(forumChooseImage);

    }

    @OnClick({R.id.forum_chooseImage, R.id.activity_add_forum_send_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forum_chooseImage:
                final TwoOptionsDialog dialog = SmartisanDialog.createTwoOptionsDialog(getActivity());
                dialog.setTitle("请选择")
                        .setOp1Text("拍照")   // 设置第一个选项的文本
                        .setOp2Text("从相册中选择")   // 设置第二个选项的文本
                        .show();
                dialog.setOnSelectListener(new TwoOptionsDialog.OnSelectListener() {
                    @Override
                    public void onOp1() {
                        dialog.dismiss();
                        Intent intent = new Intent(MyApplication.getContext(), CameraFragmentMainActivity.class);
                        startActivityForResult(intent, 2);
                    }

                    @Override
                    public void onOp2() {
                        dialog.dismiss();
                        Intent intent = new Intent(MyApplication.getContext(), GetPhotoActivity.class);
                        startActivityForResult(intent, 2);
                    }
                });
                break;
            case R.id.activity_add_forum_send_button:
                Revealator.unreveal(forumFrame)
                        .to(fabForum)
                        .withCurvedTranslation()
                        .start();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        initSwipeRecyclerView();
    }
}
