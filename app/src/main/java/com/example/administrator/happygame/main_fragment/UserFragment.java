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

import com.allen.library.SuperTextView;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.my_ui.CircleTransformation;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.IntentHelp;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;
import com.example.administrator.happygame.util.UiUtil;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;

import static com.example.administrator.happygame.util.GlobalData.mUserDao;
import static com.example.administrator.happygame.util.GlobalData.qq;


public class UserFragment extends BaseFragment {


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
    FloatingSearchView floatingSearchView;
public  static User me;
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
       me=mUserDao.load("1");

        new MyAsyncTask(me).execute();

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

    @OnClick({R.id.my_information, R.id.my_attention, R.id.my_activities, R.id.my_post, R.id.my_collection, R.id.my_friends, R.id.settings, R.id.imports})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_information:
                startActivity(IntentHelp.toMyInformationActivity(me, userMySmallImage));
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
                startActivity(IntentHelp.toCollectActivity(me.getId()));
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
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                               // EMClient.getInstance().logout(true);
                            }
                        }).start();

                        //这里可以放一个bitmap
                        if (qq.isAuthValid()) {
                            qq.removeAccount(true);
                        }
                        startActivity(IntentHelp.toLoginActivity(userMySmallImage));
                    }
                });

                break;
            default:
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
            me = user;
            userMyName.setText(user.getName());
            Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + user.getImage()).asBitmap()
                    .into(userMyBigImage);

            Glide.with(MyApplication.getContext()).load(GlobalData.HTTP_ADDRESS_PICTURE + user.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .bitmapTransform(new CircleTransformation(MyApplication.getContext(),2,MyApplication.getContext().getResources().getColor(R.color.blue0)))
                    .into(userMySmallImage);

myCollection.setRightString(StringUtil.httpArrayStringLength(user.getCollectForum()+"|"+user.getCollectActivities()+"|"+user.getCollectNews()));
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