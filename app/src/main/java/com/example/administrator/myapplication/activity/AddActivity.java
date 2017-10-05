package com.example.administrator.myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.SPUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flyco.tablayout.SegmentTabLayout;
import com.melnykov.fab.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.administrator.myapplication.child_fragment.AddListFragment.thisActivity;

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


    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles_3 = {"首页", "讨论"};
    private View mDecorView;
    private SegmentTabLayout mTabLayout_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);
        getData();
        initViewPager(mTitles_3,"add");
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
      SPUtil.get(AddActivity.this,"hasAdd",false);
    }



    @OnClick(R.id.fab_add_add)
    public void onViewClicked() {
        if(!GlobalData.hasAdd(thisActivity.getId())){
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
            fabAddAdd.setImageResource(R.drawable.icon_add_success);
            SPUtil.put(AddActivity.this,"hasAdd",true);

        }else {
            fabAddAdd.setImageResource(R.drawable.icon_add_success);
            new SweetAlertDialog(AddActivity.this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("你已经是其中一员了")
                    .setConfirmText("嗯，我知道了")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();
        }

       // fabAddAdd.setText("进入组聊");
    }


    public void getData() {
        Intent intent = getIntent();
        String obj = (String) intent.getSerializableExtra("Object_userId");


        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressActivity + "php/getById.php", obj, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                thisActivity = HttpUtil.getSingleActivity(response);
                new MyAsyncTask(thisActivity).execute();
            }
        });
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
           // Picasso.with(ApplicationUtil.getContext()) .load(GlobalData.httpAddressPicture + activity.getImage()).into(ivBlur);
            Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture + activity.getImage()).into(ivBlur);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }


}
