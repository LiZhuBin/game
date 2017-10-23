package com.example.administrator.happygame.activity;

import android.os.Bundle;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class Main2Activity extends BaseActivity {

    public Function<Observable, ObservableSource> composeFunction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


    }



}




