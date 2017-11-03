package com.example.administrator.happygame.activity;

import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.MsgAdapter;
import com.example.administrator.happygame.adapter.TextWatcherAdapter;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.Chat;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.Msg;
import com.example.administrator.happygame.util.LogUtil;
import com.example.administrator.happygame.util.TimeUtil;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.rockerhieu.emojicon.EmojiconEditText;
import io.github.rockerhieu.emojicon.EmojiconGridFragment;
import io.github.rockerhieu.emojicon.EmojiconsFragment;
import io.github.rockerhieu.emojicon.emoji.Emojicon;

import static com.example.administrator.happygame.util.GlobalData.mChatDao;
import static com.example.administrator.happygame.util.GlobalData.mUserDao;


public class ChatActivity extends BaseActivity implements EmojiconGridFragment.OnEmojiconClickedListener, EmojiconsFragment.OnEmojiconBackspaceClickedListener, EMMessageListener {


    @Bind(R.id.emojicons)
    FrameLayout emojicons;
    EmojiconEditText mEditEmojicon;
    @Bind(R.id.layout_send_emojicon)
    ImageButton layoutSendEmojicon;
    private List<Msg> msgList = new ArrayList<>();
    private List< EMMessage>emMessageList=new ArrayList<>();
    private ImageButton send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        if (getIntent().getExtras().getString("id") != null) {
            user = mUserDao.load(getIntent().getExtras().getString("id"));
        }
        getAllMessage();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(user.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditEmojicon = (EmojiconEditText) findViewById(R.id.editEmojicon);
        mEditEmojicon.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        setEmojiconFragment(false);

        send = (ImageButton) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.layout_recycle_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);

        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMMessage message = EMMessage.createTxtSendMessage(mEditEmojicon.getText().toString(), user.getId());
//如果是群聊，设置chattype，默认是单聊
//                    if (chatType == CHATTYPE_GROUP)
                if (message != null) {

                    message.setChatType(EMMessage.ChatType.Chat);
//发送消息
                    emMessageList.add(message);
                    EMClient.getInstance().chatManager().sendMessage(message);
                    LogUtil.e(mEditEmojicon.getText().toString());
                    emojicons.setVisibility(View.GONE);
                    String content = mEditEmojicon.getText().toString();
                    Msg msg = new Msg(content, Msg.TYPE_SEND,mUserDao.load(message.getUserName()).getImage(),TimeUtil.getTimeFormatText(new Date(message.getMsgTime())));

                    mChatDao.insertOrReplace(new Chat.Builder().userId(message.getUserName())
                            .userName(mUserDao.load(message.getUserName()).getName())
                            .userImageUrl(msg.getImageUrl())
                            .getMsgTime(msg.getGetMsgTime())
                            .lastMessage(content)
                            .build());
                    setMsg(msg);
                }
            }
        });
    }

    public void setMsg(Msg msg) {


        if (!"".equals(msg.getContent())) {
            msgList.add(msg);
            adapter.notifyItemInserted(msgList.size() - 1);   //当有新消息时，刷新RecyclerView中的显示
        //    msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行
            mEditEmojicon.setText("");//清空输入框中的内容
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("msgList", (Serializable) msgList);
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

    @Override
    public void onMessageReceived(final List<EMMessage> messages) {
        for (final EMMessage message : messages) {
            runOnUiThread(new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    String str = (((EMTextMessageBody) message.getBody()).getMessage());
                    LogUtil.e(str);
                    Msg   msg = new Msg(str, Msg.TYPE_RECEIVED,mUserDao.load(message.getUserName()).getImage(), TimeUtil.getTimeFormatText(new Date(message.getMsgTime())));
                    setMsg(msg);

                }
            }));
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().importMessages(emMessageList);
        EMClient.getInstance().chatManager().removeMessageListener(this);
    }

    protected void getAllMessage() {
        // 获取当前conversation对象

        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(user.getId());
//获取此会话的所有消息


if(conversation!=null) {
    List<EMMessage> msgs = conversation.getAllMessages();
    for (EMMessage message : msgs) {
        String str = (((EMTextMessageBody) message.getBody()).getMessage());
        LogUtil.e(str);
        Msg msg;
        if (message.isDelivered()) {
            msg = new Msg(str, Msg.TYPE_RECEIVED,mUserDao.load(message.getUserName()).getImage(), TimeUtil.getTimeFormatText(new Date(message.getMsgTime())));
        } else {
            msg = new Msg(str, Msg.TYPE_SEND,mUserDao.load(message.getUserName()).getImage(),TimeUtil.getTimeFormatText(new Date(message.getMsgTime())));
        }

        msgList.add(msg);

        mChatDao.insertOrReplace(new Chat.Builder().userId(message.getUserName())
                .userName(mUserDao.load(message.getUserName()).getName())
                .userImageUrl(msg.getImageUrl())
                .lastMessage(str)
                .getMsgTime(msg.getGetMsgTime())
                .build());
    }
    String msgId = null;
    if (msgs != null && msgs.size() > 0) {
        msgId = msgs.get(0).getMsgId();
    }

}
    }


}