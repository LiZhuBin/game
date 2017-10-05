package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.been.News;
import com.example.administrator.myapplication.util.GlobalData;
import com.flaviofaria.kenburnsview.KenBurnsView;


public class NewsActivity extends BaseActivity {
    private News one ;
    private String[] mTitles_3 = {"首页", "讨论"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar)findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);
        TextView toolBarText=(TextView)findViewById(R.id.toolbar_text);
        toolBarText.setText("热点信息");
        KenBurnsView imageview = (KenBurnsView)findViewById(R.id.iv_blur);
        ImageView imageView = (ImageView)findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getNews();
        initViewPager(mTitles_3,"new");
        if(one.getNew_image()!=null) {
            Glide.with(NewsActivity.this).load(GlobalData.httpAddressPicture + one.getNew_image()).into(imageview);
        }else {
            Glide.with(NewsActivity.this).load(one.getNew_drawable()).into(imageview);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu2,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void getNews() {
        Intent intent = getIntent();
        News obj = (News) intent.getSerializableExtra("Object_news");
        one = obj ;
    }

}
