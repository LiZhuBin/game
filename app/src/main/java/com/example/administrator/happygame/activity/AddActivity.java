package com.example.administrator.happygame.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.SPUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.melnykov.fab.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AddActivity extends BaseActivity {

    private static final String TAG = "AddActivity";
    @Bind(R.id.add_background)
    CoordinatorLayout addBackground;
    @Bind(R.id.Back_button)
    ImageView BackButton;
    @Bind(R.id.newactivity_toolbar)
    Toolbar newactivityToolbar;
    @Bind(R.id.iv_blur)
    KenBurnsView ivBlur;
    @Bind(R.id.fab_add_add)
    FloatingActionButton fabAddAdd;
    Activity activity;


    private String[] mTitles_3 = {"首页", "讨论"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);

        getData();

        ImageView imageView = (ImageView) findViewById(R.id.Back_button);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtil.get(AddActivity.this, "hasAdd", false);
    }


    @OnClick(R.id.fab_add_add)
    public void onViewClicked() {
        new SweetAlertDialog(AddActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("终于等到你")
                .setContentText("还好我没放弃")
                .setConfirmText("嗯，我来了")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();
        RequestBody body = new FormBody.Builder()
                .add("actiivityID", activity.getId())
                .add("userid", UserFragment.me.getId())
                .build();
        HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_USER + "php/addDoingActivity.php", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        fabAddAdd.setImageResource(R.drawable.icon_add_success);
        SPUtil.put(AddActivity.this, "hasAdd", true);


        // fabAddAdd.setText("进入组聊");
    }


    public void getData() {
        Intent intent = getIntent();
        String obj = (String) intent.getSerializableExtra("Object_userId");
        HttpUtil.sendOkHttpResquest(GlobalData.HTTP_ADDRESS_ACTIVITY + "php/getById.php", obj, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                activity = HttpUtil.getSingleActivity(response);

                EventBus.getDefault().post(activity);
                new MyAsyncTask(activity).execute();

            }
        });
        initViewPager(mTitles_3, "add", 0);
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        Activity activity;

        public MyAsyncTask(Activity activity) {
            super();
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + activity.getImage()).into(ivBlur);
            PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
            photoView.enable();
            final FrameLayout frameLayout = (FrameLayout) findViewById(R.id.photo_view_frame_layout);
            UiUtil.photoView(ivBlur, ivBlur, frameLayout, photoView);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }


}
