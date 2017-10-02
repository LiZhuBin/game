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
import com.example.administrator.myapplication.been.News;
import com.example.administrator.myapplication.util.GlobalData;


public class NewsActivity extends BaseActivity {
    private News one ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar)findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);
        getNews();
        initLayoutview();

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

    private void initLayoutview() {

        ImageView imageview = (ImageView)findViewById(R.id.image_inside);
        // TextView  textview_title = (TextView)findViewById(R.id.NewAcitivity_titletext);
        TextView textview_main_content = (TextView)findViewById(R.id.NewAcitivity_Title_mainText);

        String Main_Context = one.getNew_content();
        String Title_Context = one.getNew_title();
        if(one.getNew_image()!=null) {
            Glide.with(this).load(GlobalData.httpAddressPicture + one.getNew_image()).into(imageview);
        }else {
            Glide.with(this).load(one.getNew_drawable()).into(imageview);
        }
        //       textview_title.setText(Title_Context);
        textview_main_content.setText(Main_Context);
        ImageView imageView = (ImageView)findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void getNews() {
        Intent intent = getIntent();
        News obj = (News) intent.getSerializableExtra("Object_news");
        one = obj ;
    }
}
