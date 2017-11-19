package com.example.administrator.happygame.childfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.ForumInfoAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.my_ui.ActivityForumItem;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.rockerhieu.emojicon.EmojiconEditText;

import static com.example.administrator.happygame.childfragment.AddChatFragment.id;
import static com.example.administrator.happygame.childfragment.AddChatFragment.string;
import static com.example.administrator.happygame.util.GlobalData.mUserDao;


public class ForumChatFragment extends BaseFragment {

    private static List<ActivityForumItem> forumItemList = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();
    private static List<String> stringList = new ArrayList<>();
    View view;
    @Bind(R.id.forum_recycle_view)
    RecyclerView recyclerView;
    @Bind(R.id.editEmojicon)
    EmojiconEditText editEmojicon;
    @Bind(R.id.layout_send_emojicon)
    ImageButton layoutSendEmojicon;
    @Bind(R.id.send)
    ImageButton send;
    @Bind(R.id.emojicons)
    FrameLayout emojicons;
    private ForumInfoAdapter adapter;
    Forum forum;


    public static ForumChatFragment getInstance() {
        ForumChatFragment sf = new ForumChatFragment();
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(final Forum forum) {
        this.forum = forum;
        string = StringUtil.httpArray(forum.getComment());
        for (int i = 0; i < string.length; i++) {

            if (i % 2 == 0) {
                id = string[i];
            } else {
                stringList.add(string[i]);
                User user = mUserDao.load(id);
                userList.add(user);
                forumItemList.clear();
                for (int i1 = 0; i1 < userList.size(); i1++) {

                    forumItemList.add(new ActivityForumItem(userList.get(i1).getImage(), userList.get(i1).getName(), "第" + StringUtil.getAddOne(i1) + "楼", forum.getData(), stringList.get(i1)));
                }
                initRecyclerView();

            }
        }

    }

    public void initRecyclerView() {


        adapter = new ForumInfoAdapter(forumItemList);
        LinearLayoutManager manager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
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
        view = inflater.inflate(R.layout.fragment_forum_comment, container, false);
        ButterKnife.bind(this, view);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editEmojicon.getText().toString();
                if (!"".equals(content)) {
                    forumItemList.add(new ActivityForumItem(UserFragment.me.getImage(), UserFragment.me.getName(), "第" + StringUtil.getAddOne(forumItemList.size())+ "楼","刚刚", content));
                    editEmojicon.setText("");//清空输入框中的内容
                }
                initRecyclerView();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        forumItemList.clear();
        userList.clear();
        stringList.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
