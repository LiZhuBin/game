package com.example.administrator.myapplication.main_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.myapplication.LoginActivity;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.FriendsActivity;
import com.example.administrator.myapplication.activity.PersonActivity;
import com.example.administrator.myapplication.activity.PostActivity;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.SPUtil;
import com.example.administrator.myapplication.util.StringUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.io.IOException;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UserFragment extends Fragment {

    View view;
    User me;
    KenBurnsView userMyBigImage;
    CircleImageView userMySmallImage;
    TextView userMyName;
    TextView userMyLike;
    SuperTextView myActivities;
    SuperTextView myCollection;
    SuperTextView myFriends;
    SuperTextView myPost;
    SuperTextView settings;
    SuperTextView imports;
    String friendsData;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.user_info, container, false);
        initData(view);
        initSuperTextView(view);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    public void initData(View view) {

        userMyBigImage=(KenBurnsView)view.findViewById(R.id.person_kenBurnsView);
         userMySmallImage=(CircleImageView)view.findViewById(R.id.user_mySmallImage);

        userMyName=(TextView)view.findViewById(R.id.user_myName);
        userMyLike=(TextView)view.findViewById(R.id.user_myLike);
        myActivities=(SuperTextView)view.findViewById(R.id.my_activities);
        myCollection=(SuperTextView)view.findViewById(R.id.my_collection);
        myFriends=(SuperTextView)view.findViewById(R.id.my_friends);
        myPost=(SuperTextView)view.findViewById(R.id.my_post);
        settings=(SuperTextView)view.findViewById(R.id.settings);
        imports=(SuperTextView)view.findViewById(R.id.imports);

        Object userId = SPUtil.get(ApplicationUtil.getContext(), "UserId", 1);
        RequestBody body = new FormBody.Builder()
                .add("id", "1")//添加键值对
                .build();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser+"php/getById.php", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                    me=HttpUtil.getSingleUser(response);
                    friendsData=me.getFriends();
                    new MyAsyncTask(me).execute();
            }
        });
            }

public  void initSuperTextView(View view){
    SuperTextView myActivity = (SuperTextView) view.findViewById(R.id.my_activities);
    myActivity.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
            Bundle bundle = new Bundle();
            if(me!=null) {
                bundle.putInt("my_id", Integer.parseInt(me.getId()));
            }
            ActivityUtils.startActivity(bundle,getActivity(), PersonActivity.class, R.anim.enter_anim, R.anim.slide_out_right);
        }
    });

    SuperTextView myFriends = (SuperTextView) view.findViewById(R.id.my_friends);
    myFriends.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
        @Override
        public void onClickListener(SuperTextView superTextView) {
            Bundle bundle=new Bundle();
            bundle.putString("friends", friendsData);
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
         Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddressUser+user.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(userMySmallImage);
         userMyLike.setText(user.getPraise_num());
       //  Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddress+user.getImage()).into(userMyBigImage);
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
