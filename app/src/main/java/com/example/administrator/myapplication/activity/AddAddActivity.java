package com.example.administrator.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

public class AddAddActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_add);
        Toolbar toolbar = (Toolbar)findViewById(R.id.newactivity_toolbar);
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
