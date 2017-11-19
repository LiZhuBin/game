package com.example.administrator.happygame.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.SPUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.melnykov.fab.FloatingActionButton;
import com.sdsmdg.tastytoast.TastyToast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.administrator.happygame.util.GlobalData.mForumDao;

public class ForumActivity extends BaseActivity {

    public static Forum forum;

    KenBurnsView kenBurnsView;
    TextView textView;
    @Bind(R.id.fab_add_add)
    FloatingActionButton fabAddAdd;
    private ForumItem one;
    private String[] mTitles_3 = {"首页", "讨论"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        initViewPager(mTitles_3, "forum", 0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);

        ImageView backButton = (ImageView) findViewById(R.id.Back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initForumContent();
        ButterKnife.bind(this);
    }


    public void initForumContent() {
        String string = getIntent().getExtras().getString("id");
        kenBurnsView = (KenBurnsView) findViewById(R.id.iv_blur);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.photo_view_frame_layout);
        UiUtil.photoView(kenBurnsView, kenBurnsView, frameLayout, photoView);

        TextView title = (TextView) findViewById(R.id.toolbar_text);
        title.setText("帖子");
forum=mForumDao.load(string);
        HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_FORUM + "php/getById.php", string, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                forum = HttpUtil.getSingleForum(response);
                new MyAsyncTask(forum).execute();
                EventBus.getDefault().post(forum);

            }
        });
    }

    @OnClick(R.id.fab_add_add)
    public void onViewClicked() {
        if (GlobalData.hasAdd(forum.getId(), UserFragment.me.getDoingActivities())) {
            final WarningDialog dialog = SmartisanDialog.createWarningDialog(this);
            dialog.setTitle("你已加入")
                    .setConfirmText("退出")
                    .show();
            dialog.setOnConfirmListener(new WarningDialog.OnConfirmListener() {
                @Override
                public void onConfirm() {
                    dialog.dismiss();
                    fabAddAdd.setImageResource(R.drawable.icon_fab_add);
                    TastyToast.makeText(getApplicationContext(), "退出成功", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
            });
        } else {
            new SweetAlertDialog(ForumActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("加入成功")
                    .setContentText("欢迎您的到来")
                    .setConfirmText("嗯，我来了")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();

            HttpUtil.addDoingActivity(forum.getId(), UserFragment.me.getId());
            fabAddAdd.setImageResource(R.drawable.icon_add_success);
            SPUtil.put(ForumActivity.this, "hasAdd", true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        Forum forum;

        public MyAsyncTask(Forum forum) {
            super();
            this.forum = forum;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (GlobalData.hasAdd(forum.getId(),UserFragment.me.getDoingActivities())){
                fabAddAdd.setImageResource(R.drawable.icon_add_success);

            }else {
                fabAddAdd.setImageResource(R.drawable.icon_fab_add);
            }
            Glide.with(ForumActivity.this).load(GlobalData.HTTP_ADDRESS_PICTURE + forum.getImage()).into(kenBurnsView);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }}

