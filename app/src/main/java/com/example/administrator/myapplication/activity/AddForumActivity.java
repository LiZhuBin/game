package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

public class AddForumActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.collapsing_toolbar);
        // getSupportActionBar().hide();
        setSupportActionBar(toolbar);
        ImageView imageView = (ImageView)findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
