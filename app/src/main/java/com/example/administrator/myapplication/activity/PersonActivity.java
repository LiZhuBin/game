package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.AddAdapter;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.thing_class.AddItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.ClasstoItem;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.LogUtil;
import com.example.administrator.myapplication.util.SPUtil;
import com.example.administrator.myapplication.util.StringUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PersonActivity extends BaseActivity {
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

    private List<AddItem> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    private GridLayoutManager manager;
    protected ViewPagerAdapter adapter;
    User user;
int isFriend=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        // getSupportActionBar().hide();
        initAdd();

        SPUtil.put(PersonActivity.this,"isFrist",true);
        setSupportActionBar(toolbar);
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

                if(isFriend==1){
                    Intent intent=new Intent(PersonActivity.this,ChatActivity.class);
                    startActivity(intent);
                }else if(isFriend==-1){
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

    private void initSwipeRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.person_activity_recycle);
        addAdapter = new AddAdapter(addList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApplicationUtil.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addAdapter);

    }

    private void initAdd() {

        String userId = getIntent().getExtras().getString("id");

        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", userId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                user = HttpUtil.getSingleUser(response);
                    isFriend=GlobalData.isFriend(user.getId());
               getActivities(StringUtil.httpArray(user.getDoingActivities()));
                new MyAsyncTask(user).execute();
            }
        });

     //  getActivities(StringUtil.httpArray(user.getDoingActivities()));
    }
    private void getActivities(String[] friendsId) {
        for (int i = 0; i < friendsId.length; i++) {
            HttpUtil.sendOkHttpResquest(GlobalData.httpAddressActivity + "php/getById.php", friendsId[i], new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Activity activity = HttpUtil.getSingleActivity(response);
                    ClasstoItem.ActivitytoAddItem(activity, addList);
                    LogUtil.d( user.getImage() + "<<<<<<<<<<<<<<");
                }
            });
        }
        initSwipeRecyclerView();
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
            if(isFriend==1){
                addAsFriend.setText("发消息");
            }else if(isFriend==0){
                addAsFriend.setVisibility(View.INVISIBLE);
            }
            Glide.with(PersonActivity.this).load(GlobalData.httpAddressPicture+user.getImage()).into(userMySmallImage);
            Glide.with(PersonActivity.this).load(GlobalData.httpAddressPicture+user.getImage()).into(userMyBigImage);
            userMyName.setText(user.getName());
            userMyLike.setText(user.getPraise_num());

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }
}