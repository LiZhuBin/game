package com.example.administrator.happygame;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    protected Handler mHandler = new Handler();
    @Bind(R.id.imageview4)
    ImageView imageview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

     Bitmap mBitmap = CodeUtils.createImage("55", 400, 400, null);
        imageview4.setImageBitmap(mBitmap);

    }
}
