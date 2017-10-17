package com.example.administrator.happygame.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.MsgAdapter;
import com.example.administrator.happygame.adapter.TextWatcherAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.thing_class.Msg;
import com.example.administrator.happygame.util.LogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconGridFragment;
import io.github.rockerhieu.emojicon.EmojiconsFragment;
import io.github.rockerhieu.emojicon.emoji.Emojicon;

public class ChatActivity extends BaseActivity implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener {

    @Bind(R.id.layout_send_emojicon)
    CircleImageView layoutSendEmojicon;
    @Bind(R.id.emojicons)
    FrameLayout emojicons;
    private List<Msg> msgList = new ArrayList<>();

    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    EmojiconEditText mEditEmojicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            msgList = (List<Msg>) savedInstanceState.getSerializable("msgList");
            LogUtil.d(msgList.get(0).getContent() + "<<<<<<<<<");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEditEmojicon = (EmojiconEditText) findViewById(R.id.editEmojicon);
        mEditEmojicon.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        setEmojiconFragment(false);
        initMsgs(); //初始化消息数据

        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.layout_recycle_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);

        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e(mEditEmojicon.getText().toString());
                emojicons.setVisibility(View.GONE);
                String content = mEditEmojicon.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, UserFragment.me.getImage());
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);   //当有新消息时，刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行
                    mEditEmojicon.setText("");//清空输入框中的内容
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("msgList", (Serializable) msgList);
    }

    private void initMsgs() {

    }

    @Override
    public void onEmojiconClicked(Emojicon emojicon) {
        EmojiconsFragment.input(mEditEmojicon, emojicon);
    }

    @Override
    public void onEmojiconBackspaceClicked(View v) {
        EmojiconsFragment.backspace(mEditEmojicon);
    }

    private void setEmojiconFragment(boolean useSystemDefault) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.emojicons, EmojiconsFragment.newInstance(useSystemDefault))
                .commit();
    }

    @OnClick(R.id.layout_send_emojicon)
    public void onViewClicked() {
emojicons.setVisibility(View.VISIBLE);
    }
}