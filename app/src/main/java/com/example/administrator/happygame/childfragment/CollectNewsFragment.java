package com.example.administrator.happygame.childfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aopa.greendao.NewsDao;
import com.aopa.greendao.UserDao;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.RecyclerView_itemdecoration.hotpoint_item;
import com.example.administrator.happygame.adapter.MyRecyclerAdapter;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.main_fragment.UserFragment;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.StringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.administrator.happygame.util.GlobalData.mNewsDao;

public class CollectNewsFragment extends Fragment {
    List<News> choosenews;
boolean isSeek=false;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
MyRecyclerAdapter adapter;
String newsString;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_recycle, container, false);
        ButterKnife.bind(this, view);
        if(isSeek==false) {
            initData();
        }
        adapter = new MyRecyclerAdapter(choosenews);  //定义好数据
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.getContext(), 2); //设置布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new hotpoint_item(MyApplication.getContext())); //进行边界装饰
        return view;
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(String currentQuery) {
        isSeek=true;
        List<News> joes = GlobalData.mNewsDao.queryBuilder()
                .where(NewsDao.Properties.New_title.like(currentQuery),
                        NewsDao.Properties.New_content.like(currentQuery),
                        NewsDao.Properties.New_id.like("%"+UserDao.Properties.Name.eq((currentQuery))+"%"))
                .orderAsc(NewsDao.Properties.New_like_num)
                .list();
        for(News news:joes){
            ClasstoItem.NewToChooseNews(news, choosenews);
        }

    }
public void initData(){

    choosenews = new ArrayList<>();

    String[] strings= StringUtil.httpArray(UserFragment.me.getCollectNews());
    for (String string:strings){
      News news=mNewsDao.load(string);
        ClasstoItem.NewToChooseNews(news, choosenews);
    }

}

    public static CollectNewsFragment getInstance() {
        CollectNewsFragment sf = new CollectNewsFragment();
        return sf;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
