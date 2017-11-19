package com.example.administrator.happygame.childfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.happygame.R;

/**
 * 作者：Administrator on 2017/11/2 0002 18:37
 * 邮箱：xjs250@163.com
 */

public class MessageSystemFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_recycle, container, false);
        return view;
    }
    public static MessageSystemFragment getInstance() {
        MessageSystemFragment sf = new MessageSystemFragment();
        return sf;
    }
}
