package com.example.administrator.happygame.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.fangxu.allangleexpandablebutton.AllAngleExpandableButton;
import com.fangxu.allangleexpandablebutton.ButtonData;
import com.fangxu.allangleexpandablebutton.ButtonEventListener;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.happygame.util.UiUtil.mGoodView;


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
            Glide.with(NewsActivity.this).load(GlobalData.HTTP_ADDRESS_PICTURE + one.getNew_image()).into(imageview);
        } else {
            Glide.with(NewsActivity.this).load(one.getNew_drawable()).into(imageview);
        }
        installButton90to180();
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
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getNews() {
        Intent intent = getIntent();
        News obj = (News) intent.getParcelableExtra("Object_news");
        one = obj;
    }
    private void installButton90to180() {
        final AllAngleExpandableButton button = (AllAngleExpandableButton) findViewById(R.id.button_expandable_90_180);
        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.drawable.icon_about,R.drawable.icon_good, R.drawable.icon_like, R.drawable.icon_unlike};
        int[] color = {R.color.white,R.color.transparent,R.color.transparent, R.color.transparent, R.color.transparent};
        for (int i = 0; i < 4; i++) {
            ButtonData buttonData;
            if (i == 0) {
                buttonData = ButtonData.buildIconButton(this, drawable[i], 0);
            } else {
                buttonData = ButtonData.buildIconButton(this, drawable[i], 0);
            }

            buttonData.setBackgroundColorId(this, color[i]);
            buttonDatas.add(buttonData);
        }
        button.setButtonDatas(buttonDatas);
        setListener(button);

    }
    private void setListener(final AllAngleExpandableButton button) {
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                switch (index){
                    case 1:
                        mGoodView.setTextInfo("+1", Color.RED, 30);
                        mGoodView.show(button);
                        break;
                    case 2: mGoodView.setTextInfo("收藏成功", Color.RED, 20);
                        mGoodView.show(button);

                        break;
                    case 3:
                        mGoodView.setTextInfo("-1", Color.BLACK, 30);
                        mGoodView.show(button);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onExpand() {
//                showToast("onExpand");
            }

            @Override
            public void onCollapse() {
//                showToast("onCollapse");
            }
        });
    }
}
