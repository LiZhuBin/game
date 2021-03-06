package com.example.administrator.happygame.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.activity.fifth.InviteActivity;
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
import com.sdsmdg.tastytoast.TastyToast;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.TwoOptionsDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.administrator.happygame.util.GlobalData.mActivityDao;


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


    private String[] mTitles_3 = {"信息","讨论"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newactivity_toolbar);
        setSupportActionBar(toolbar);



        ImageView imageView = (ImageView) findViewById(R.id.Back_button);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getData();
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtil.get(AddActivity.this, "hasAdd", false);
    }


    @OnClick(R.id.fab_add_add)
    public void onViewClicked() {
        final TwoOptionsDialog dialog = SmartisanDialog.createTwoOptionsDialog(this);
        dialog.setTitle("请选择")
                .setOp1Text("邀请好友")   // 设置第一个选项的文本
                .setOp2Text("加入")   // 设置第二个选项的文本
                .show();
        dialog.setOnSelectListener(new TwoOptionsDialog.OnSelectListener() {
            @Override
            public void onOp1() {
                dialog.dismiss();
startActivity(new Intent(AddActivity.this, InviteActivity.class));
            }

            @Override
            public void onOp2() {
                dialog.dismiss();
                add();
            }
        });

    }
private void invite(){

}
private void add(){
    if(GlobalData.hasAdd(activity.getId(),UserFragment.me.getDoingActivities())){
        final WarningDialog dialog = SmartisanDialog.createWarningDialog(this);
        dialog.setTitle("你已加入")
                .setConfirmText("退出")
                .show();
        dialog.setOnConfirmListener(new WarningDialog.OnConfirmListener() {
            @Override
            public void onConfirm() {
                dialog.dismiss();
                fabAddAdd.setImageResource(R.drawable.icon_fab_add);
                TastyToast.makeText(getApplicationContext(), "退出成功", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            }
        });
    }else {
        new SweetAlertDialog(AddActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("加入成功")
                .setContentText("欢迎您的到来")
                .setConfirmText("嗯，我来了")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .show();

        HttpUtil.addDoingActivity(activity.getId(), UserFragment.me.getId());
        fabAddAdd.setImageResource(R.drawable.icon_add_success);
        SPUtil.put(AddActivity.this, "hasAdd", true);
    }
}
    private void getData() {

        Intent intent = getIntent();
        String obj = (String) intent.getSerializableExtra("Object_userId");
        activity=mActivityDao.load(obj);

        EventBus.getDefault().postSticky(activity);
        new MyAsyncTask(activity).execute()
        ;

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
            if (GlobalData.hasAdd(activity.getId(),UserFragment.me.getDoingActivities())){
                fabAddAdd.setImageResource(R.drawable.icon_add_success);

            }else {
                fabAddAdd.setImageResource(R.drawable.icon_fab_add);
            }
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
