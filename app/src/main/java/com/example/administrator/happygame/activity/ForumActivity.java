package com.example.administrator.happygame.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.melnykov.fab.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForumActivity extends BaseActivity {

    public static Forum forum;
    FloatingActionButton fabAddAdd;
    KenBurnsView kenBurnsView;
    TextView textView;
    private ForumItem one;
    private String[] mTitles_3 = {"首页", "讨论"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        initViewPager(mTitles_3, "forum", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.bottom_button);
        textView.setText("评论");
        ImageView backButton = (ImageView) findViewById(R.id.Back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fabAddAdd = (FloatingActionButton) findViewById(R.id.fab_add_add);
        fabAddAdd.setImageResource(R.drawable.icon_fab_add);
        fabAddAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        initForumContent();
        ButterKnife.bind(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initForumContent() {
        String string = getIntent().getExtras().getString("id");
        kenBurnsView = (KenBurnsView) findViewById(R.id.iv_blur);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.photo_view_frame_layout);
        UiUtil.photoView(kenBurnsView, kenBurnsView, frameLayout, photoView);

        TextView title = (TextView) findViewById(R.id.toolbar_text);
        title.setText("帖子");
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
    }
}
