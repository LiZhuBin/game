package com.example.administrator.happygame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.UiUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;


public class NewsActivity extends BaseActivity {
    private static News one;
    private String[] mTitles_3 = {"首页", "讨论"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.enable();
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.photo_view_frame_layout);

        TextView toolBarText = (TextView) findViewById(R.id.toolbar_text);
        toolBarText.setText("热点信息");
        KenBurnsView imageview = (KenBurnsView) findViewById(R.id.iv_blur);
        UiUtil.photoView(imageview, imageview, frameLayout, photoView);
        ImageView imageView = (ImageView) findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getNews();
        initViewPager(mTitles_3, "new", 0);
        if (one.getNew_image() != null) {
            Glide.with(NewsActivity.this).load(GlobalData.httpAddressPicture + one.getNew_image()).into(imageview);
        } else {
            Glide.with(NewsActivity.this).load(one.getNew_drawable()).into(imageview);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getNews() {
        Intent intent = getIntent();
        News obj = (News) intent.getParcelableExtra("Object_news");
        one = obj;
    }

}
