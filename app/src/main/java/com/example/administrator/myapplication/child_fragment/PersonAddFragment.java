package com.example.administrator.myapplication.child_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adapter.AddAdapter;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.thing_class.AddItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.ClasstoItem;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class PersonAddFragment extends BaseFragment {
    private List<AddItem> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    protected ViewPagerAdapter adapter;
    View view;
    private Handler handler=new Handler(){
        public  void handleMessage(Message msg){
            switch (msg.what){
                case 1:initSwipeRecyclerView(view);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view=inflater.inflate(R.layout.activity_person_activity, container, false);
     //   getActivities(StringUtil.httpArray(user.getDoingActivities()));
        initSwipeRecyclerView(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventBackgroundThread(User user1){
        getActivities(StringUtil.httpArray(user1.getDoingActivities()));
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
                    ClasstoItem.ActivityToAddItem(activity, addList);
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
            });
        }

    }
    private void initSwipeRecyclerView(View view) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.person_activity_recycle);
        addAdapter = new AddAdapter(addList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApplicationUtil.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addAdapter);

    }
    public static PersonAddFragment getInstance() {
        PersonAddFragment sf = new PersonAddFragment();
        return sf;
    }

}
