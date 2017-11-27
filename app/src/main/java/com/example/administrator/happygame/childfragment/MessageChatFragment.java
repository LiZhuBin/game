package com.example.administrator.happygame.childfragment;

import android.graphics.Rect;
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
import com.example.administrator.happygame.been.Chat;
import com.example.administrator.happygame.util.MyApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.administrator.happygame.util.GlobalData.mChatDao;

/**
 * 作者：Administrator on 2017/11/2 0002 18:33
 * 邮箱：xjs250@163.com
 */

public class MessageChatFragment extends Fragment {
    ChatAdapter chatAdapter;
    List<Chat> chatItemList = new ArrayList<>();
    @Bind(R.id.recycler)
    RecyclerView chatRecycler;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_recycle, container, false);

        ButterKnife.bind(this, view);
        initRefresh();
        initRecycle();
        return view;
    }

    public static MessageChatFragment getInstance() {
        MessageChatFragment sf = new MessageChatFragment();
        return sf;
    }

    public void initRecycle() {
        chatItemList.clear();
        List<Chat>chatList=mChatDao.loadAll();
        Collections.sort(chatList, new Comparator<Chat>() {
            @Override
            public int compare(Chat arg0, Chat arg1) {
//            第一次比较专业
                int i = arg0.getGetMsgTime().compareTo(arg1.getGetMsgTime());
//            如果专业相同则进行第二次比较

                return i;
            }
        });
        for (Chat chat : chatList) {
            chatItemList.add(new Chat.Builder().userId(chat.getUserId())
                    .userName(chat.getUserName())
                    .userImageUrl(chat.getUserImageUrl())
                    .lastMessage(chat.getLastMessage()).build());
        }
        chatAdapter = new ChatAdapter(chatItemList);
        chatRecycler.setAdapter(chatAdapter);
        chatRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //设定底部边距为1px
                outRect.set(0, 0, 0, 1);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        chatRecycler.setLayoutManager(linearLayoutManager);
    }

    public void initRefresh() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //onCreate(null);
                initRecycle();
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecycle();
    }
}
