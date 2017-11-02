package com.example.administrator.happygame.child_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.ChatAdapter;
import com.example.administrator.happygame.thing_class.ChatItem;
import com.example.administrator.happygame.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：Administrator on 2017/11/2 0002 18:33
 * 邮箱：xjs250@163.com
 */

public class MessageChatFragment extends Fragment {
    ChatAdapter chatAdapter;
    List<ChatItem> chatItemList = new ArrayList<>();
    @Bind(R.id.recycler)
    RecyclerView chatRecycler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_recycle, container, false);

        ButterKnife.bind(this, view);
        initRecycle();
        return view;
    }

    public static MessageChatFragment getInstance() {
        MessageChatFragment sf = new MessageChatFragment();
        return sf;
    }

    public void initRecycle() {
        chatAdapter = new ChatAdapter(chatItemList);
        chatRecycler.setAdapter(chatAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        chatRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
