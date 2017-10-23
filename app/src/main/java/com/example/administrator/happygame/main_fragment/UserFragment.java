package com.example.administrator.happygame.main_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.util.ApplicationUtil;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.HttpUtil;
import com.example.administrator.happygame.util.IntentHelp;
import com.example.administrator.happygame.util.SPUtil;
import com.example.administrator.happygame.util.StringUtil;
import com.example.administrator.happygame.util.UiUtil;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class UserFragment extends BaseFragment {

    public static User me;
    public static Object userId;
    protected WeakReference<View> mRootView;//缓存fragment数据
    View view;
    ImageView userMySmallImage;
    ImageView userMyBigImage;
    TextView userMyName;
    PhotoView photoView;
    @Bind(R.id.my_attention)
    SuperTextView myAttention;
    @Bind(R.id.my_activities)
    SuperTextView myActivities;
    @Bind(R.id.my_post)
    SuperTextView myPost;
    @Bind(R.id.my_collection)
    SuperTextView myCollection;
    @Bind(R.id.my_friends)
    SuperTextView myFriends;
    @Bind(R.id.settings)
    SuperTextView settings;
    @Bind(R.id.imports)
    SuperTextView imports;
    @Bind(R.id.my_information)
    SuperTextView myInformation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            view = inflater.inflate(R.layout.user_info, container, false);
            mRootView = new WeakReference<View>(view);

            initData();
            ButterKnife.bind(this, view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        initSuperTextView(view);
        ButterKnife.bind(this, mRootView.get());
        return mRootView.get();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    public void initData() {


        userId = SPUtil.get(ApplicationUtil.getContext(), "UserId", 1);
//        GlobalData.login(userId.toString()).subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
//                        LogUtil.e(user.getImage());
//                    }
//                });

//        GlobalData.getRetrofit()
//                .getSingleUser(userId.toString())
//                //获取Observable对象
//                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
//                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User users) throws Exception {
//                        me = users;
//
//                        new MyAsyncTask(me).execute();
//                    }
//                });


        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser + "php/getById.php", userId.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                me = HttpUtil.getSingleUser(response);

                new MyAsyncTask(me).execute();
            }
        });
    }


    public void initSuperTextView(View view) {
        userMyBigImage = (ImageView) view.findViewById(R.id.user_myBigImage);
        userMySmallImage = (ImageView) view.findViewById(R.id.iv_avatar);
        photoView = (PhotoView) view.findViewById(R.id.photo_view);
        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.photo_view_frame_layout);
        photoView.setMaxScale(5f);
        UiUtil.photoView(userMySmallImage, userMyBigImage, frameLayout, photoView);

        userMyName = (TextView) view.findViewById(R.id.my_name);

        final SuperTextView myAttention = (SuperTextView) view.findViewById(R.id.my_attention);
        myAttention.setLeftTopTvClickListener(new SuperTextView.OnLeftTopTvClickListener() {
            @Override
            public void onClickListener() {
                startActivity(IntentHelp.toFriendsActivity(me.getAttentionId(), myAttention.getLeftTopString()));
            }
        }).setLeftBottomTvClickListener(new SuperTextView.OnLeftBottomTvClickListener() {
            @Override
            public void onClickListener() {
                startActivity(IntentHelp.toFriendsActivity(me.getAttentionId(), myAttention.getLeftTopString()));
            }
        }).setCenterTopTvClickListener(new SuperTextView.OnCenterTopTvClickListener() {
            @Override
            public void onClickListener() {
                startActivity(IntentHelp.toFriendsActivity(me.getPraise_id(), myAttention.getCenterTopString()));
            }
        }).setCenterBottomTvClickListener(new SuperTextView.OnCenterBottomTvClickListener() {
            @Override
            public void onClickListener() {
                startActivity(IntentHelp.toFriendsActivity(me.getPraise_id(), myAttention.getCenterTopString()));
            }
        }).setRightTopTvClickListener(new SuperTextView.OnRightTopTvClickListener() {
            @Override
            public void onClickListener() {
                startActivity(IntentHelp.toFriendsActivity(me.getBeattentionId(), myAttention.getRightTopString()));
            }
        }).setRightBottomTvClickListener(new SuperTextView.OnRightBottomTvClickListener() {
            @Override
            public void onClickListener() {
                startActivity(IntentHelp.toFriendsActivity(me.getBeattentionId(), myAttention.getRightTopString()));
            }
        });


    }

    @OnClick({R.id.my_information,R.id.my_attention, R.id.my_activities, R.id.my_post, R.id.my_collection, R.id.my_friends, R.id.settings, R.id.imports})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_information:
                startActivity(IntentHelp.toMyInformationActivity(me,userMySmallImage));
                break;
            case R.id.my_attention:
                break;
            case R.id.my_activities:

                startActivity(IntentHelp.toPersonActivity(me.getId(), 0));
                break;
            case R.id.my_post:
                startActivity(IntentHelp.toPersonActivity(me.getId(), 1));
                break;
            case R.id.my_collection:
                TastyToast.makeText(ApplicationUtil.getContext(), "敬请期待", Toast.LENGTH_LONG, TastyToast.SUCCESS);
                break;
            case R.id.my_friends:
                startActivity(IntentHelp.toFriendsActivity(me.getFriends(), "我的好友"));
                break;
            case R.id.settings:
                startActivity(IntentHelp.toSetActivity());
                break;
            case R.id.imports:
                final WarningDialog dialog = SmartisanDialog.createWarningDialog(getActivity());
                dialog.setTitle("确定退出账号吗")
                        .setConfirmText("退出账号")
                        .show();
                dialog.setOnConfirmListener(new WarningDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        dialog.dismiss();

                        //这里可以放一个bitmap
                        startActivity(IntentHelp.toLoginActivity(userMySmallImage));
                    }
                });

                break;
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
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
            me=user;
            userMyName.setText(user.getName());
            Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture + user.getImage()).asBitmap()
                    .into(userMyBigImage);

            // Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture + user.getImage()).into(userMySmallImage);
            Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture + user.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .bitmapTransform(new CropCircleTransformation(ApplicationUtil.getContext()))
                    .into(userMySmallImage);


            myActivities.setRightString(StringUtil.httpArrayStringLength(user.getDoingActivities()));
            myCollection.setRightString(StringUtil.httpArrayStringLength(user.getCollectActivities()));
            myFriends.setRightString(StringUtil.httpArrayStringLength(user.getFriends()));
            myPost.setRightString(StringUtil.httpArrayStringLength(user.getPosts()));
            myAttention.setCenterBottomString(user.getPraise_num());
            myAttention.setLeftBottomString(StringUtil.httpArrayStringLength(user.getAttentionId()));
            myAttention.setRightBottomString(StringUtil.httpArrayStringLength(user.getBeattentionId()));
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }

}