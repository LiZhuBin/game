package com.example.administrator.myapplication.child_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.been.Forum;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ForumListFragment extends Fragment {

TextView title;
TextView content;
TextView data;
TextView name;
CircleImageView userImage;

    ForumItem obj;
    Forum forum;
    private GridLayoutManager manager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_forum_list, container, false);
        obj= (ForumItem) getActivity().getIntent().getSerializableExtra("Object_forum");
        initData(view);
        return view;
    }

public void initData(View view){
   title=(TextView)view.findViewById(R.id.fragment_forum_list_title);
   name=(TextView)view.findViewById(R.id.fragment_forum_list_name);
   content=(TextView)view.findViewById(R.id.fragment_forum_list_content);
   data=(TextView)view.findViewById(R.id.fragment_forum_list_time);
    userImage=(CircleImageView)view.findViewById(R.id.fragment_forum_list_image);


    HttpUtil.sendOkHttpResquest(GlobalData.httpAddressForum + "php/getById.php", obj.getId(), new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            forum = HttpUtil.getSingleForum(response);
            title.setText(forum.getTitle());

        }
    });
    HttpUtil.sendOkHttpResquest(GlobalData.httpAddressForum + "php/getById.php", obj.getId(), new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            forum = HttpUtil.getSingleForum(response);
            title.setText(forum.getTitle());
            data.setText(forum.getData());
            content.setText(forum.getContent());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getUser(forum.getUserId());
                }
            }).start();

        }

    });
}
public void getUser(String userId){
    HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", userId, new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

           User user=HttpUtil.getSingleUser(response);
         new MyAsyncTask(user).execute();
            name.setText(user.getName());
        }

    });
}
    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
       User user;

        public MyAsyncTask(User  user) {
            super();
            this.user = user;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {


        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+user.getImage()).into(userImage);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }
    public static ForumListFragment getInstance(String title) {
        ForumListFragment sf = new ForumListFragment();
        return sf;
    }

}
