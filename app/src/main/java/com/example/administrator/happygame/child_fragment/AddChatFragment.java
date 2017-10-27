package com.example.administrator.happygame.child_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.MsgAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.Msg;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class AddChatFragment extends BaseFragment {
    public static String[] string;
    public static User user;
    public static String id = null;
    private static List<String> userIdList = new ArrayList<>();
    private static List<String> userCommentList = new ArrayList<>();
    View view;
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    msgList.clear();
                    for (int i1 = 0; i1 < userIdList.size(); i1++) {
                        //LogUtil.d("useridlist"+userIdList.get(i1));
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

        send = (Button) view.findViewById(R.id.send);
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
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventBackgroundThread(Activity activity) {
        string = StringUtil.httpArray(activity.getComment());
        for (int i = 0; i < string.length; i++) {

            if (i % 2 == 0) {

                id = string[i];
            } else {
                userCommentList.add(string[i]);
                HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_USER + "php/getById.php", id, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        user = HttpUtil.getSingleUser(response);
                        userIdList.add(user.getImage());
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                });
            }


        }


    }
}
