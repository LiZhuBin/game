package com.example.administrator.happygame.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.SPUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.sackcentury.shinebuttonlib.ShineButton;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.administrator.happygame.util.UiUtil.good;

public class PersonActivity extends BaseActivity {
    public static User user;
    @Bind(R.id.bottom_button)
    Button addAsFriend;
    @Bind(R.id.user_myBigImage)
    KenBurnsView userMyBigImage;
    @Bind(R.id.diagonalLayout)
    DiagonalLayout diagonalLayout;
    @Bind(R.id.user_mySmallImage)
    CircleImageView userMySmallImage;
    @Bind(R.id.user_myName)
    TextView userMyName;
    @Bind(R.id.shineButton)
    ShineButton shineButton;
    @Bind(R.id.user_myLike)
    TextView userMyLike;
    @Bind(R.id.main_head)
    RelativeLayout mainHead;
    @Bind(R.id.Back_button)
    ImageView BackButton;
    @Bind(R.id.newactivity_toolbar)
    Toolbar newactivityToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    ShineButton praise;
    int pager;
    String userId;
    int isFriend = -1;
    private String[] mTitles_3 = {"约战", "帖子"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initAdd();
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        // getSupportActionBar().hide();


        SPUtil.put(PersonActivity.this, "isFrist", true);
        praise = (ShineButton) findViewById(R.id.shineButton);

        good(praise, "+1", userMyLike);

        setSupportActionBar(toolbar);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.photo_view_frame_layout);
        UiUtil.photoView(userMySmallImage, userMyBigImage, frameLayout, photoView);
        ImageView imageView = (ImageView) findViewById(R.id.Back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addAsFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFriend == 1) {
                    Intent intent = new Intent(PersonActivity.this, com.example.administrator.happygame.activity.ChatActivity.class);
                    startActivity(intent);
                } else if (isFriend == -1) {
                    new SweetAlertDialog(PersonActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("您的好友请求已发送")
                            .setContentText("请等待对方同意")
                            .setConfirmText("好的")
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();
                }
            }
        });


    }


    private void initAdd() {

        userId = getIntent().getExtras().getString("id");
        pager = getIntent().getExtras().getInt("pager");
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", userId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                user = HttpUtil.getSingleUser(response);

                EventBus.getDefault().post(user);
                //   EventBus.getDefault().post(user);
                isFriend = GlobalData.isFriend(user.getId());

                new MyAsyncTask(user).execute();

            }

        });
        initViewPager(mTitles_3, "person", pager);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        User user;

        public MyAsyncTask(User user) {
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

            if (isFriend == 1) {
                addAsFriend.setText("发消息");
            } else if (isFriend == 0) {
                addAsFriend.setVisibility(View.INVISIBLE);
            }
            Glide.with(PersonActivity.this).load(GlobalData.httpAddressPicture + user.getImage()).into(userMySmallImage);
            Glide.with(PersonActivity.this).load(GlobalData.httpAddressPicture + user.getImage()).into(userMyBigImage);
            userMyName.setText(user.getName());
            userMyLike.setText(user.getPraise_num());

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }
}