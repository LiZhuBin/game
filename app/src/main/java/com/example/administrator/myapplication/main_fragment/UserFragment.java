package com.example.administrator.myapplication.main_fragment;

import android.content.Intent;
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
import com.example.administrator.myapplication.LoginActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.PersonActivity;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.IntentHelp;
import com.example.administrator.myapplication.util.SPUtil;
import com.example.administrator.myapplication.util.StringUtil;
import com.example.administrator.myapplication.util.UiUtil;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class UserFragment extends BaseFragment {

    View view;
    public static  User me;
    public  static  Object userId;
    ImageView userMySmallImage;
ImageView userMyBigImage;
    TextView userMyName;
    PhotoView photoView;
    SuperTextView myAttention;
    SuperTextView myActivities;
    SuperTextView myCollection;
    SuperTextView myFriends;
    SuperTextView myPost;
    SuperTextView settings;
    SuperTextView imports;
    protected WeakReference<View> mRootView;//缓存fragment数据
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
        return mRootView.get();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    public void initData() {



         userId = SPUtil.get(ApplicationUtil.getContext(), "UserId", 1);

        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser+"php/getById.php", userId.toString(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                    me=HttpUtil.getSingleUser(response);

                    new MyAsyncTask(me).execute();
            }
        });
            }

public  void initSuperTextView(View view){
    userMyBigImage=(ImageView)view.findViewById(R.id.user_myBigImage) ;
    userMySmallImage=(ImageView)view.findViewById(R.id.iv_avatar);
    photoView=(PhotoView)view.findViewById(R.id.photo_view);
    final FrameLayout frameLayout=(FrameLayout)view.findViewById(R.id.photo_view_frame_layout);
    photoView.setMaxScale(5f);
    UiUtil.photoView(userMySmallImage,userMyBigImage,frameLayout,photoView);



    myAttention=(SuperTextView)view.findViewById(R.id.my_attention);
    userMyName=(TextView)view.findViewById(R.id.my_name);
    myActivities=(SuperTextView)view.findViewById(R.id.my_activities);
    myCollection=(SuperTextView)view.findViewById(R.id.my_collection);
    myFriends=(SuperTextView)view.findViewById(R.id.my_friends);
    myPost=(SuperTextView)view.findViewById(R.id.my_post);
    settings=(SuperTextView)view.findViewById(R.id.settings);
    imports=(SuperTextView)view.findViewById(R.id.imports);
    final SuperTextView myAttention = (SuperTextView) view.findViewById(R.id.my_attention);
   myAttention.setLeftTopTvClickListener(new SuperTextView.OnLeftTopTvClickListener() {
       @Override
       public void onClickListener() {
           startActivity(IntentHelp.toFriendsActivity(me.getAttentionId(),myAttention.getLeftTopString()));
       }
   }).setLeftBottomTvClickListener(new SuperTextView.OnLeftBottomTvClickListener() {
       @Override
       public void onClickListener() {
           startActivity(IntentHelp.toFriendsActivity(me.getAttentionId(),myAttention.getLeftTopString()));
       }
   }).setCenterTopTvClickListener(new SuperTextView.OnCenterTopTvClickListener() {
       @Override
       public void onClickListener() {
           startActivity(IntentHelp.toFriendsActivity(me.getPraise_id(),myAttention.getCenterTopString()));
       }
   }).setCenterBottomTvClickListener(new SuperTextView.OnCenterBottomTvClickListener() {
       @Override
       public void onClickListener() {
           startActivity(IntentHelp.toFriendsActivity(me.getPraise_id(),myAttention.getCenterTopString()));
       }
   }).setRightTopTvClickListener(new SuperTextView.OnRightTopTvClickListener() {
       @Override
       public void onClickListener() {
           startActivity(IntentHelp.toFriendsActivity(me.getBeattentionId(),myAttention.getRightTopString()));
       }
   }).setRightBottomTvClickListener(new SuperTextView.OnRightBottomTvClickListener() {
       @Override
       public void onClickListener() {
           startActivity(IntentHelp.toFriendsActivity(me.getBeattentionId(),myAttention.getRightTopString()));
       }
   });
    SuperTextView myActivity = (SuperTextView) view.findViewById(R.id.my_activities);
    myActivity.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
           Intent intent=new Intent(ApplicationUtil.getContext(),PersonActivity.class);
            intent.putExtra("id",me.getId());
            intent.putExtra("pager",0);
            startActivity(intent);
        }
    });
SuperTextView myPost=(SuperTextView)view.findViewById(R.id.my_post);
    myPost.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
            Intent intent=new Intent(ApplicationUtil.getContext(),PersonActivity.class);
            intent.putExtra("id",me.getId());
            intent.putExtra("pager",1);
            startActivity(intent);
        }
    });
    SuperTextView myFriends = (SuperTextView) view.findViewById(R.id.my_friends);
    myFriends.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
            startActivity(IntentHelp.toFriendsActivity(me.getFriends(),"我的好友"));
        }
    });
    SuperTextView myCollects = (SuperTextView) view.findViewById(R.id.my_collection);
    myCollects.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
            TastyToast.makeText(ApplicationUtil.getContext(),"敬请期待", Toast.LENGTH_LONG,TastyToast.SUCCESS);
        }
    });
    SuperTextView superTextView2 = (SuperTextView) view.findViewById(R.id.imports);
    superTextView2.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {

            ActivityUtils.startActivity(getActivity(),LoginActivity.class);

        }
    });
}
 class MyAsyncTask extends AsyncTask<Void,Integer,Boolean>{
     User user;
     public MyAsyncTask(User user) {
         super();
         this.user=user;
     }

     @Override
     protected void onPreExecute() {



     }

     @Override
     protected void onPostExecute(Boolean aBoolean) {

     }

     @Override
     protected void onProgressUpdate(Integer... values) {
         userMyName.setText(user.getName());
         Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+user.getImage())
                 .into(userMyBigImage);

         Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+user.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}
