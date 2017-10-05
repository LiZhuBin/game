package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.been.Forum;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.melnykov.fab.FloatingActionButton;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForumActivity extends BaseActivity {

public static Forum forum;
    FloatingActionButton fabAddAdd;
    private ForumItem one;
    private String[] mTitles_3 = {"首页", "讨论"};
KenBurnsView kenBurnsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        initViewPager(mTitles_3, "forum");

        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);

        fabAddAdd=(FloatingActionButton)findViewById(R.id.fab_add_add);
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void initForumContent() {

        ForumItem obj = (ForumItem) getIntent().getSerializableExtra("Object_forum");
        one = obj;
        kenBurnsView=(KenBurnsView)findViewById(R.id.iv_blur);
      Glide.with(this).load(GlobalData.httpAddressPicture+one.getForumImage()).into(kenBurnsView);
        TextView title=(TextView)findViewById(R.id.toolbar_text);
        title.setText("帖子");
//        username.setText(one.get);
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressForum + "php/getById", one.getId(),new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //forum=HttpUtil.getSingleForum(response);
            }
        });
    }

    @OnClick(R.id.fab_add_add)
    public void onViewClicked() {
    }
}
