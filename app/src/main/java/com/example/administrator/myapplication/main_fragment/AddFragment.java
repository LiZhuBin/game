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
import com.example.administrator.myapplication.adapter.AddAdapter;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.my_ui.AddItem;
import com.example.administrator.myapplication.util.ActivityUtils;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;


public class AddFragment extends Fragment {

    public static AddItem[] addItemses={
            new AddItem(R.drawable.image_whilte_me,"李","22分钟前","ffff","fff","22分钟前","ffff","fff"),
            new AddItem(R.drawable.image_whilte_me,"李彬","22分钟前","ffff","fff","22分钟前","ffff","fff"),
            new AddItem(R.drawable.image_whilte_me,"李主彬","22分钟前","ffff","fff","22分钟前","ffff","fff"),
            new AddItem(R.drawable.image_whilte_me,"李主彬","22分钟前","ffff","fff","22分钟前","ffff","fff")
    };
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
        View view = inflater.inflate(R.layout.add_info, container, false);
        ButterKnife.bind(this, view);
initAdd();
        initSwipeRecyclerView(view);
//        Button button=(Button)view.findViewById(R.id.add_button);
//        final ExpandableListView expandableListView=(ExpandableListView)view.findViewById(R.id.left_expandable_list);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                expandableListView.setVisibility(View.VISIBLE);
//            }
//        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


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
}
    private void initAdd() {
        addList.clear();
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int index = random.nextInt(addItemses.length);
            addList.add(addItemses[index]);
        }
    }
}
