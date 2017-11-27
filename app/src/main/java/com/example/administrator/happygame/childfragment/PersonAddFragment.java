package com.example.administrator.happygame.childfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.happygame.R;
import com.example.administrator.happygame.adapter.AddAdapter;
import com.example.administrator.happygame.adapter.ViewPagerAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.thing_class.AddItem;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.happygame.util.GlobalData.mActivityDao;


public class PersonAddFragment extends BaseFragment {
    protected ViewPagerAdapter adapter;
    View view;
    private List<AddItem> addList = new ArrayList<>();
    private AddAdapter addAdapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    initSwipeRecyclerView(view);
                    break;
                default:
                    break;
            }
        }
    };

    public static PersonAddFragment getInstance() {
        PersonAddFragment sf = new PersonAddFragment();
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_person_activity, container, false);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(User user1) {
        getActivities(StringUtil.httpArray(user1.getDoingActivities()));
    }


    private void getActivities(String[] friendsId) {
        for (int i = 0; i < friendsId.length; i++) {
            Activity activity=mActivityDao.load(friendsId[i]);
            ClasstoItem.ActivityToAddItem(activity, addList);

            Message message = handler.obtainMessage();
            message.what = 1;
            handler.sendMessage(message);

        }

    }

    private void initSwipeRecyclerView(View view) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.person_activity_recycle);
        addAdapter = new AddAdapter(addList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addAdapter);

    }

}
