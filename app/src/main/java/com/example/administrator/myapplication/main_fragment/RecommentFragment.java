package com.example.administrator.myapplication.main_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RecyclerView_itemdecoration.headitemdire;
import com.example.administrator.myapplication.RecyclerView_itemdecoration.hotpoint_item;
import com.example.administrator.myapplication.RecyclerView_itemdecoration.hottie_fenxi;
import com.example.administrator.myapplication.adapter.ForumAdapter;
import com.example.administrator.myapplication.adapter.MyRecyclerAdapter;
import com.example.administrator.myapplication.adapter.headviewAdapter;
import com.example.administrator.myapplication.been.Forum;
import com.example.administrator.myapplication.been.News;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.my_ui.GlideImageLoader;
import com.example.administrator.myapplication.my_ui.Headview;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.util.StringUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecommentFragment extends Fragment  {
    List<News> newsList ;

    protected WeakReference<View> mRootView;//缓存fragment数据
    RecyclerView recyclerView;
    private static final String TAG = "RecommentFragment";
    List<News> ChooseNews ;
    MyRecyclerAdapter adapter ;
    RecyclerView hottieRecyclerview;
    long    Reflash = 1500;
    List<Headview> headviewslist;

    private List<ForumItem> forumItemList=new ArrayList<>();
    private ForumAdapter forumAdapter;
    private GridLayoutManager manager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            View view = inflater.inflate(R.layout.recomment_info, container, false);
            mRootView = new WeakReference<View>(view);
            initRefresh(view);
            setLeftDaohang1(view);
           initRecycle(view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        return mRootView.get();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Boolean> {
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
       //
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            publishProgress(1);
            return true;
        }
    }
    public void initRefresh(final View view){
        initHeadviewlist(view);
        initForum();
        initNewlist();
       // initNeswlist();
        initBanner(view);
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.recomment_refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initRefresh(view);

                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }
    private void initForum(){
        forumItemList = new ArrayList<ForumItem>();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressForum+"php/userData.php",  new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Forum> appList = HttpUtil.getListForum(response);
                for (final Forum forum : appList) {
                    ForumItem forumItem=new ForumItem(forum.getTitle(), StringUtil.httpArrayStringLength(forum.getCommentId()),forum.getLike(),forum.getImage(),Integer.parseInt(forum.getId()));
                    forumItemList.add(forumItem);
                }
            }
        });
    }
    private void initRecycle(View view){
        RecyclerView headviewRecyclerView = (RecyclerView) view.findViewById(R.id.headviewRecyclerView);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        hottieRecyclerview = (RecyclerView)view.findViewById(R.id.Recycler2);
        headviewRecyclerView.setAdapter(new headviewAdapter(headviewslist));
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ApplicationUtil.getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        headviewRecyclerView.setLayoutManager(layoutManager1);
        headviewRecyclerView.addItemDecoration(new headitemdire(ApplicationUtil.getContext()));
        /*
        * */

        adapter = new MyRecyclerAdapter(ChooseNews);  //定义好数据
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(ApplicationUtil.getContext(),2); //设置布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new hotpoint_item(ApplicationUtil.getContext())); //进行边界装饰
        /*
        * */

        //  hottieAdapter = new HottieAdapter(ChooseTie) ;
        forumAdapter=new ForumAdapter(forumItemList);
        hottieRecyclerview.setAdapter(forumAdapter);
        final GridLayoutManager gridLayoutManager=new GridLayoutManager(ApplicationUtil.getContext(),2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                return 1;
            }
        });
        hottieRecyclerview.setLayoutManager(gridLayoutManager);
        hottieRecyclerview.addItemDecoration(new hottie_fenxi(ApplicationUtil.getContext()));

    }
    private void initHeadviewlist(View view) {
        headviewslist = new ArrayList<Headview>();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressUser+"php/userData.php",  new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<User> appList = HttpUtil.getListUser(response);
                for (final User user : appList) {
                    Headview headview=new Headview(user.getId(),user.getImage(),user.getName());
                    headviewslist.add(headview);
                }
            }
        });

    }

    private void setLeftDaohang1(final View view) {

        final ImageView imageView = (ImageView)view.findViewById(R.id.Refresh1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressBar progressbar = (ProgressBar)view.findViewById(R.id.firstBar1);
                Animation animation = AnimationUtils.loadAnimation(ApplicationUtil.getContext(),R.anim.refelsh);
                animation.setDuration(Reflash);


                imageView.startAnimation(animation); // 上面是旋转动画，然后出现转圈
            }
        });
    }
    private void initNewlist() {
        ChooseNews = new ArrayList<>();
        HttpUtil.sendOkHttpResquest(GlobalData.httpAddressNew+"php/userData.php",  new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<News> appList = HttpUtil.getListNews(response);
                for (final News news : appList) {
                    ChooseNews.add(new News(news.getNew_id(),news.getNew_title(),news.getNew_content(),news.getNew_image()));

                }
            }
        });

    }




    private void initBanner(final View view) {

        /*----------------需要被展示的新闻------------------------*/
        List<News> list = new ArrayList<News>() ;
        list.add(new News(R.drawable.image_activity_background,"Title1","2"));
        list.add(new News(R.drawable.image_scrolling_head,"Title2","3"));
        list.add(new News(R.drawable.hezhao,"Title3","4"));
        /*-------------------------------------------------------*/
        Banner banner = (Banner)view.findViewById(R.id.Banner);
        banner.setImageLoader(new GlideImageLoader()); //该ImageLoader 只要就是要重写displayImageview后，去对传入的 Imagelist操作，传入的东西可以是Object然后我们对他解析，提取西药的东西
        banner.setImages(list);//这里已经被我重写成可以放入需要展示的NewsList，传入News对象后，就会解析并提取相应的图片再显示。
        banner.setDelayTime(3000);//这里设置轮番的时间
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//数字模式的数字只能显示在右边！！
       // banner.setBackgroundColor(R.color);
        banner.setBannerTitles(getTitleList(list));//从NewsList中获取到 标题List！
        //从这里应该可以扩展list提取到的Title，可以从这里控制Title的长度！！
        //banner.setBannerAnimation(Transformer.ZoomIn); //配置各种图片切换时候的动画！！ 从 Tranformer中来提取
        // banner.setIndicatorGravity(BannerConfig.LEFT); //这里配置的是指示器在图片上的方位，有 左中右
        banner.start();

    }


    public List<String> getTitleList(List<News> list){

        List<String> TitleList = new ArrayList<>();

        for (News one : list)
        {
            String title = one.getNew_title();
            //这里可以对title进行处理

            TitleList.add( title );
        }
        return  TitleList ;
    }
}
