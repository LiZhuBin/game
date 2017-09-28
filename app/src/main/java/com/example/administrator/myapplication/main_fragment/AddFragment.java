package com.example.administrator.myapplication.main_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.AddActivity;
import com.example.administrator.myapplication.activity.AddAddActivity;
import com.example.administrator.myapplication.adapter.AddAdapter;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.been.Activity;
import com.example.administrator.myapplication.thing_class.AddItem;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.melnykov.fab.FloatingActionButton;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class AddFragment extends Fragment {

    public static AddItem[] addItemses={

    };
    protected WeakReference<View> mRootView;//缓存fragment数据
    private List<AddItem> addList=new ArrayList<>();
    private AddAdapter addAdapter;
    private GridLayoutManager manager;
    protected ViewPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
        View view = inflater.inflate(R.layout.add_info, container, false);
            mRootView = new WeakReference<View>(view);
        ButterKnife.bind(this, view);
      //  initRefresh(view);
       // initAdd();
        initData();
        initSwipeRecyclerView(view);
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

//    public void initRefresh(View view){
//        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.add_refreshLayout);
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000);
//            }
//        });
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadmore(2000);
//            }
//        });
//    }
private  void initSwipeRecyclerView(View view){
    final SwipeMenuRecyclerView recyclerView=(SwipeMenuRecyclerView)view.findViewById(R.id.add_right_fragment_list);
    addAdapter=new AddAdapter(addList);
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ApplicationUtil.getContext());
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(addAdapter);
    recyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            ActivityUtils.startActivity(getActivity(), AddActivity.class);
        }
    });
    recyclerView.setLongPressDragEnabled(true); // 开启拖拽。
    //recyclerView.setItemViewSwipeEnabled(true); // 开启滑动删除。f
    OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            int fromPosition = srcHolder.getAdapterPosition();
            int toPosition = targetHolder.getAdapterPosition();

            // Item被拖拽时，交换数据，并更新adapter。
            Collections.swap(addList, fromPosition, toPosition);
            addAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
            int position = srcHolder.getAdapterPosition();
            // Item被侧滑删除时，删除数据，并更新adapter。
            addList.remove(position);
            addAdapter.notifyItemRemoved(position);
        }
    };
    recyclerView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI。
    FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add);
    fab.attachToRecyclerView(recyclerView);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityUtils.startActivity(getActivity(), AddAddActivity.class, R.anim.enter_anim, R.anim.slide_out_right);
        }
    });
}

    public void initData(){
        addList.clear();
        addList = new ArrayList<AddItem>();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressActivity+"php/userData.php",  new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Activity> appList = HttpUtil.getListActivity(response);
                for (final Activity activity : appList) {
                   // GlobalData.httpAddressActivity+activity.getImage(),
                    AddItem addItem=new AddItem(activity.getType()+activity.getImage(),
                            activity.getUser_num(),
                            activity.getTitle(),
                            activity.getAddress(),
                           activity.getTime(),
                            activity.getAddress());
                    addList.add(addItem);

                }

            }
        });
    }
}
