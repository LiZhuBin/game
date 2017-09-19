package com.example.administrator.myapplication.main_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.SPUtil;
import com.example.administrator.myapplication.util.StringUtil;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class UserFragment extends Fragment {

    View view;

    KenBurnsView userMyBigImage;
    CircleImageView userMySmallImage;
    TextView userMyName;
    TextView userMyLike;
    SuperTextView myActivities;
    SuperTextView myCollection;
    SuperTextView myFriends;
    SuperTextView myReading;
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
        SuperTextView superTextView1 = (SuperTextView) view.findViewById(R.id.my_friends);
        superTextView1.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                    Bundle bundle=new Bundle();
                bundle.putString("friends", friendsData);
                Log.d(TAG, "onClickListener: "+bundle);
               ActivityUtils.startActivity(bundle,getActivity(), FriendsActivity.class, R.anim.enter_anim, R.anim.slide_out_right);
            }
        });
        SuperTextView superTextView2 = (SuperTextView) view.findViewById(R.id.imports);
        superTextView2.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {

                ActivityUtils.startActivity(getActivity(),LoginActivity.class);

            }
        });
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
        myReading=(SuperTextView)view.findViewById(R.id.my_reading);
        settings=(SuperTextView)view.findViewById(R.id.settings);
        imports=(SuperTextView)view.findViewById(R.id.imports);
        Object userId = SPUtil.get(ApplicationUtil.getContext(), "UserId", 1);
        RequestBody body = new FormBody.Builder()
                .add("id", "1")//添加键值对
                .build();
        HttpUtil.sendOkHttpResquest(GlobalData.httpLocalhostAddress+"/user/getById.php", body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<User> appList = HttpUtil.parseUserJSONWithGSON(response);
                for (final User user : appList) {
                    friendsData=user.getFriends();
                    new MyAsyncTask(user).execute();

                }
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
         super.onPreExecute();
     }

     @Override
     protected void onPostExecute(Boolean aBoolean) {
         super.onPostExecute(aBoolean);
     }

     @Override
     protected void onProgressUpdate(Integer... values) {
         userMyName.setText(user.getName());
         Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddress+user.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(userMySmallImage);
         userMyLike.setText(user.getPraise_num());
       //  Glide.with(ApplicationUtil.getContext()).load(GlobalData.httpAddress+user.getImage()).into(userMyBigImage);
         myActivities.setRightString(StringUtil.httpArrayStringLength(user.getDoingActivities()));
         myCollection.setRightString(StringUtil.httpArrayStringLength(user.getCollectActivities()));
         myFriends.setRightString(StringUtil.httpArrayStringLength(user.getFriends()));
         myReading.setRightString(StringUtil.httpArrayStringLength(user.getReadingActivities()));
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
