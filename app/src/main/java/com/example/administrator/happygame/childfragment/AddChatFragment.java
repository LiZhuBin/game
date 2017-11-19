package com.example.administrator.happygame.childfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.MsgAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.Msg;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.rockerhieu.emojicon.EmojiconEditText;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;


public class AddChatFragment extends BaseFragment {
    public static String[] string;
    public static User user;
    public static String id = null;
    private static List<String> userIdList = new ArrayList<>();
    private static List<String> userCommentList = new ArrayList<>();
    View view;
    @Bind(R.id.editEmojicon)
    EmojiconEditText inputText;
    private List<Msg> msgList = new ArrayList<>();

    private ImageButton send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    msgList.clear();
                    for (int i1 = 0; i1 < userIdList.size(); i1++) {

                        msgList.add(new Msg(userIdList.get(i1), userCommentList.get(i1)));
                    }
                    msgRecyclerView = (RecyclerView) view.findViewById(R.id.msg_recycler_view);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
                    msgRecyclerView.setLayoutManager(layoutManager);

                    adapter = new MsgAdapter(msgList);
                    msgRecyclerView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };

    public static AddChatFragment getInstance(String title) {
        AddChatFragment sf = new AddChatFragment();
        return sf;
    }

    public static AddChatFragment getInstance() {
        AddChatFragment sf = new AddChatFragment();
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_chat, container, false);

        send = (ImageButton) view.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);   //当有新消息时，刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });
        ButterKnife.bind(this, view);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(Activity activity) {
        string = StringUtil.httpArray(activity.getComment());
        for (int i = 0; i < string.length; i++) {

            if (i % 2 == 0) {

                id = string[i];
            } else {
                userCommentList.add(string[i]);
                user = mUserDao.load(id);
                userIdList.add(user.getImage());
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);

            }


        }


    }

    @Override
    public void onStop() {
        super.onStop();
        userIdList.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
