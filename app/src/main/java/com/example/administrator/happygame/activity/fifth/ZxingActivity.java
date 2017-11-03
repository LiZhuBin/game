package com.example.administrator.happygame.activity.fifth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZxingActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.item_image)
    ImageView itemImage;
    @Bind(R.id.image_frame)
    FrameLayout imageFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        ButterKnife.bind(this);
        toolbar.setTitle("二维码");
setSupportActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent != null && intent.getParcelableExtra("BITMAP") != null) {
            Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("BITMAP");
            itemImage.setImageBitmap(bitmap);
        }
    }
}
