package com.example.administrator.happygame.childfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseFragment;

public class NewsListFragment extends BaseFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recomment_forum, container, false);
    }

    public static NewsListFragment getInstance() {
        NewsListFragment sf = new NewsListFragment();
        return sf;
    }
}
