package com.example.administrator.happygame.child_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.ApplicationUtil;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ForumListFragment extends BaseFragment {

    TextView title;
    TextView content;
    TextView data;
    TextView name;
    CircleImageView userImage;
    View view;
    ForumItem obj;
    private GridLayoutManager manager;

    public static ForumListFragment getInstance() {
        ForumListFragment sf = new ForumListFragment();
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
        view = inflater.inflate(R.layout.fragment_forum_list, container, false);
        obj = (ForumItem) getActivity().getIntent().getSerializableExtra("Object_forum");
        this.view = view;
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventBackgroundThread(final Forum forum) {
        title = (TextView) view.findViewById(R.id.fragment_forum_list_title);
        name = (TextView) view.findViewById(R.id.fragment_forum_list_name);
        content = (TextView) view.findViewById(R.id.fragment_forum_list_content);
        data = (TextView) view.findViewById(R.id.fragment_forum_list_time);
        userImage = (CircleImageView) view.findViewById(R.id.fragment_forum_list_image);
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", forum.getUserId(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                User user = HttpUtil.getSingleUser(response);
                new MyAsyncTask(user, forum).execute();

            }

        });
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        User user;
        Forum forum;

        public MyAsyncTask(User user, Forum forum) {
            super();
            this.user = user;
            this.forum = forum;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            title.setText(forum.getTitle());
            data.setText(forum.getData());
            content.setText(forum.getContent());
            name.setText(user.getName());
            Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture + user.getImage()).into(userImage);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }

}
