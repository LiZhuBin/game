package com.example.administrator.happygame.childfragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.IntentHelp;
import com.example.administrator.happygame.util.MyApplication;
import com.jaouan.revealator.Revealator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;


public class ForumContentFragment extends BaseFragment {

    TextView title;
    TextView content;
    TextView data;
    TextView name;
   ImageView userImage;
    View view;
    ForumItem obj;
    @Bind(R.id.forum_my_add)
    Button forumMyAdd;
    @Bind(R.id.item_edit_text)
    TextView itemEditText;
    @Bind(R.id.item_edit)
    EditText itemEdit;
    @Bind(R.id.edit_ensure)
    Button editEnsure;
    @Bind(R.id.edit_frame)
    FrameLayout editFrame;
    private GridLayoutManager manager;
Forum forum;
    public static ForumContentFragment getInstance() {
        ForumContentFragment sf = new ForumContentFragment();
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
        ButterKnife.bind(this, view);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(final Forum forum) {
        this.forum=forum;
        title = (TextView) view.findViewById(R.id.fragment_forum_list_title);
        name = (TextView) view.findViewById(R.id.fragment_forum_list_name);
        content = (TextView) view.findViewById(R.id.fragment_forum_list_content);
        data = (TextView) view.findViewById(R.id.fragment_forum_list_time);
        userImage = (ImageView) view.findViewById(R.id.fragment_forum_list_image);
        User user =mUserDao.load(forum.getUserId());
        new MyAsyncTask(user, forum).execute();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fragment_forum_list_image,R.id.forum_my_add, R.id.item_edit_text, R.id.item_edit, R.id.edit_ensure, R.id.edit_frame})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_forum_list_image:
                startActivity(IntentHelp.toPersonActivity(forum.getId(), 0));
                break;
            case R.id.forum_my_add:
                Revealator.reveal(editFrame)
                        .from(forumMyAdd)
                        .withCurvedTranslation()
                        .withChildsAnimation()
                        .start();
                break;
            case R.id.item_edit_text:
                break;
            case R.id.item_edit:
                break;
            case R.id.edit_ensure:
                Revealator.unreveal(editFrame)
                        .to(forumMyAdd)
                        .withCurvedTranslation()
                        .start();
                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("您的好友请求已发送")
                        .setContentText("请等待对方同意")
                        .setConfirmText(" 好的 ")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();
                break;
            case R.id.edit_frame:
                break;
                default:
                    break;
        }
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
            Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + user.getImage()).into(userImage);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }

}
