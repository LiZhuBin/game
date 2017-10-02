package com.example.administrator.myapplication.main_fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.administrator.myapplication.LoginActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.FriendsActivity;
import com.example.administrator.myapplication.activity.PersonActivity;
import com.example.administrator.myapplication.activity.PhotoViewActivity;
import com.example.administrator.myapplication.activity.PostActivity;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.SPUtil;
import com.example.administrator.myapplication.util.StringUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UserFragment extends Fragment {

    View view;
    public static  User me;

    public  static  Object userId;
    ImageView userMySmallImage;
ImageView userMyBigImage;
    TextView userMyName;
    SuperTextView myAttention;
    SuperTextView myActivities;
    SuperTextView myCollection;
    SuperTextView myFriends;
    SuperTextView myPost;
    SuperTextView settings;
    SuperTextView imports;
    String friendsData;
    protected WeakReference<View> mRootView;//缓存fragment数据
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
        view = inflater.inflate(R.layout.user_info, container, false);
            mRootView = new WeakReference<View>(view);
        initData(view);
        ButterKnife.bind(this, view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        return mRootView.get();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    public void initData(final View view) {

        userMyBigImage=(ImageView)view.findViewById(R.id.iv_blur) ;
         userMySmallImage=(ImageView)view.findViewById(R.id.iv_avatar);
userMySmallImage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(ApplicationUtil.getContext(), PhotoViewActivity.class);
        intent.putExtra("imageUrl",GlobalData.httpAddressUser+me.getImage());
        startActivity(intent);
    }
});



        myAttention=(SuperTextView)view.findViewById(R.id.my_attention);
        userMyName=(TextView)view.findViewById(R.id.my_name);
        myActivities=(SuperTextView)view.findViewById(R.id.my_activities);
        myCollection=(SuperTextView)view.findViewById(R.id.my_collection);
        myFriends=(SuperTextView)view.findViewById(R.id.my_friends);
        myPost=(SuperTextView)view.findViewById(R.id.my_post);
        settings=(SuperTextView)view.findViewById(R.id.settings);
        imports=(SuperTextView)view.findViewById(R.id.imports);

         userId = SPUtil.get(ApplicationUtil.getContext(), "UserId", 1);
        RequestBody body = new FormBody.Builder()
                .add("id", userId.toString())//添加键值对
                .build();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser+"php/getById.php", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                    me=HttpUtil.getSingleUser(response);
                initSuperTextView(view);
                    new MyAsyncTask(me).execute();
            }
        });
            }

public  void initSuperTextView(View view){
    SuperTextView myActivity = (SuperTextView) view.findViewById(R.id.my_activities);
    myActivity.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
           Intent intent=new Intent(ApplicationUtil.getContext(),PersonActivity.class);
            intent.putExtra("id",me.getId());
            startActivity(intent);
        }
    });

    SuperTextView myFriends = (SuperTextView) view.findViewById(R.id.my_friends);
    myFriends.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
            Bundle bundle=new Bundle();
            bundle.putString("friends", me.getFriends());
            ActivityUtils.startActivity(bundle,getActivity(), FriendsActivity.class, R.anim.enter_anim, R.anim.slide_out_right);
        }
    });
    SuperTextView myCollects = (SuperTextView) view.findViewById(R.id.my_collection);
    myCollects.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
           // Bundle bundle=new Bundle();
            //bundle.putInt("my_id", Integer.parseInt(me.getId()));
            ActivityUtils.startActivity(getActivity(), PostActivity.class);
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
         Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+user.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                 .bitmapTransform(new BlurTransformation(ApplicationUtil.getContext(), 20), new CenterCrop(ApplicationUtil.getContext()))
                 .into(userMyBigImage);

         Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressPicture+user.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                 .bitmapTransform(new CropCircleTransformation(ApplicationUtil.getContext()))
                 .into(userMySmallImage);
         myAttention.setCenterBottomString(user.getPraise_num());
         myActivities.setRightString(StringUtil.httpArrayStringLength(user.getDoingActivities()));
         myCollection.setRightString(StringUtil.httpArrayStringLength(user.getCollectActivities()));
         myFriends.setRightString(StringUtil.httpArrayStringLength(user.getFriends()));
         myPost.setRightString(StringUtil.httpArrayStringLength(user.getPosts()));
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
