package com.example.administrator.myapplication.child_fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.ForumInfoAdapter;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.been.Forum;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.my_ui.ActivityForumItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.administrator.myapplication.child_fragment.AddChatFragment.id;
import static com.example.administrator.myapplication.child_fragment.AddChatFragment.string;


public class ForumCommentFragment extends BaseFragment {

View view;
    private static List<ActivityForumItem> forumItemList = new ArrayList<>();
    private ForumInfoAdapter adapter;
    Forum forum;
    private  static  List<User> userList=new ArrayList<>();
private  static List<String> stringList=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventBackgroundThread(final Forum forum) {
        string = StringUtil.httpArray(forum.getComment());
        for (int i = 0; i < string.length; i++) {

            if (i % 2 == 0) {
                id = string[i];
            } else {
                stringList.add(string[i]);
                HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", id, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        User user = HttpUtil.getSingleUser(response);
                        userList.add(user);

                    }
                });
            }
        }
        for (int i1 = 0; i1 < userList.size(); i1++) {
            //LogUtil.d("useridlist"+userIdList.get(i1));
            forumItemList.add(new ActivityForumItem(userList.get(i1).getImage(), userList.get(i1).getName(), i1+"楼", forum.getData(),stringList.get(i1)));
        }
        initRecyclerView();
    }
    public  void initRecyclerView(){

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.activity_forum_recycler);

        adapter = new ForumInfoAdapter(forumItemList);
        LinearLayoutManager manager = new LinearLayoutManager(ApplicationUtil.getContext());
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
      view=inflater.inflate(R.layout.fragment_forum_comment, container, false);


        return view;
    }

    public static ForumCommentFragment getInstance() {
        ForumCommentFragment sf = new ForumCommentFragment();
        return sf;
    }

}
