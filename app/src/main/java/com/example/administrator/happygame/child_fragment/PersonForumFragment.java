package com.example.administrator.happygame.child_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.ForumAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.LogUtil;
import com.example.administrator.happygame.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PersonForumFragment extends BaseFragment {
    List<ForumItem> forumItemList = new ArrayList<>();
    User user1;
    RecyclerView recyclerView;
    TextView title;
    View view;
    private ForumAdapter adapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initRecycle();
                    break;
                default:
                    break;
            }
        }
    };

    public static PersonForumFragment getInstance() {
        PersonForumFragment sf = new PersonForumFragment();
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.activity_person_post, container, false);
        // Inflate the layout for this fragment
        title = (TextView) view.findViewById(R.id.activity_person_post_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.person_post_recycle);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(User user1) {
        for (String string : StringUtil.httpArray(user1.getPosts())) {
            HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_FORUM + "php/getById.php", string, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Forum forum = HttpUtil.getSingleForum(response);
                    LogUtil.e(forum.getImage());
                    ClasstoItem.ForumToForumItem(forum, forumItemList);
                    //
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            });
        }

    }

    public void initRecycle() {


        LogUtil.e("recycle");
        adapter = new ForumAdapter(forumItemList);
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

}
