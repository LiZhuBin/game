package com.example.administrator.happygame.main_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.administrator.happygame.Listener.MyPageChangeListener;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.RecyclerView_itemdecoration.headitemdire;
import com.example.administrator.happygame.RecyclerView_itemdecoration.hotpoint_item;
import com.example.administrator.happygame.RecyclerView_itemdecoration.hottie_fenxi;
import com.example.administrator.happygame.adapter.CardPagerAdapter;
import com.example.administrator.happygame.adapter.ForumAdapter;
import com.example.administrator.happygame.adapter.MyRecyclerAdapter;
import com.example.administrator.happygame.adapter.headviewAdapter;
import com.example.administrator.happygame.base.BaseFragment;
import com.example.administrator.happygame.been.Forum;
import com.example.administrator.happygame.been.News;
import com.example.administrator.happygame.been.User;
import com.example.administrator.happygame.my_ui.BezierViewPager;
import com.example.administrator.happygame.my_ui.GlideImageLoader;
import com.example.administrator.happygame.my_ui.Headview;
import com.example.administrator.happygame.thing_class.Advertisement;
import com.example.administrator.happygame.thing_class.ForumItem;
import com.example.administrator.happygame.util.ClasstoItem;
import com.example.administrator.happygame.util.GlobalData;
import com.example.administrator.happygame.util.LogUtil;
import com.example.administrator.happygame.util.MyApplication;
import com.example.administrator.happygame.util.UiUtil;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdsmdg.tastytoast.TastyToast;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.administrator.happygame.util.GlobalData.mForumDao;
import static com.example.administrator.happygame.util.GlobalData.mNewsDao;
import static com.example.administrator.happygame.util.GlobalData.mUserDao;

public class RecommentFragment extends BaseFragment {

    protected WeakReference<View> mRootView;//缓存fragment数据
    BezierViewPager viewPager;
    RecyclerView recyclerView;
    List<News> ChooseNews;
    MyRecyclerAdapter adapter;
    RecyclerView hottieRecyclerview;
    List<News> list;
    List<Headview> headviewslist;
    CardPagerAdapter cardAdapter;

    TextView recommentTextNew;
    TextView recommentTextPost;

    View view;
    BottomNavigationViewEx bottomNavigationViewEx;
    FloatingSearchView searchView;
    @Bind(R.id.recomment_text_person)
    TextView recommentTextPerson;
    @Bind(R.id.recomment_title_text)
    TextView recommentTitleText;
    private List<ForumItem> forumItemList = new ArrayList<>();
    private ForumAdapter forumAdapter;
    private List<Advertisement> advertisementList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            view = inflater.inflate(R.layout.recomment_info, container, false);

            mRootView = new WeakReference<View>(view);
            ButterKnife.bind(this, view);
            initData();

        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }

        ButterKnife.bind(this, mRootView.get());
        return mRootView.get();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void initRefresh(final View view) {
        recommentTextNew = (TextView) view.findViewById(R.id.recomment_text_new);
        recommentTextPerson = (TextView) view.findViewById(R.id.recomment_text_person);
        recommentTextPost = (TextView) view.findViewById(R.id.recomment_text_post);
        UiUtil.jumpBean(recommentTextNew);
        UiUtil.jumpBean(recommentTextPerson);
        UiUtil.jumpBean(recommentTextPost);
        UiUtil.jumpBean(recommentTitleText);
        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.recomment_refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //onCreate(null);


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

    private void initHeadBanner() {

        advertisementList.add(new Advertisement(GlobalData.HTTP_ADDRESS_PICTURE + "advertise/4703fbe8dcfc601964ad8f92b61d98a6.png"));
        advertisementList.add(new Advertisement(GlobalData.HTTP_ADDRESS_PICTURE + "advertise/2457f0f5834ea526a9118b023ad6c00d.jpg"));
        advertisementList.add(new Advertisement(GlobalData.HTTP_ADDRESS_PICTURE + "advertise/b1c912544ddb731b847bfd148165f936.jpg"));
    }


    private void initRecycle(View view) {

        RecyclerView headviewRecyclerView = (RecyclerView) view.findViewById(R.id.headviewRecyclerView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        hottieRecyclerview = (RecyclerView) view.findViewById(R.id.Recycler2);
        headviewRecyclerView.setAdapter(new headviewAdapter(headviewslist));
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MyApplication.getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        headviewRecyclerView.setLayoutManager(layoutManager1);
        headviewRecyclerView.addItemDecoration(new headitemdire(MyApplication.getContext()));
        /*
        * */

        adapter = new MyRecyclerAdapter(ChooseNews);  //定义好数据
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.getContext(), 2); //设置布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new hotpoint_item(MyApplication.getContext())); //进行边界装饰
        /*
        * */

        //  hottieAdapter = new HottieAdapter(ChooseTie) ;
        forumAdapter = new ForumAdapter(forumItemList);
        hottieRecyclerview.setAdapter(forumAdapter);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        hottieRecyclerview.setLayoutManager(gridLayoutManager);
        hottieRecyclerview.addItemDecoration(new hottie_fenxi(MyApplication.getContext()));

        Banner banner = (Banner) view.findViewById(R.id.Banner);
        banner.setImageLoader(new GlideImageLoader()); //该ImageLoader 只要就是要重写displayImageview后，去对传入的 Imagelist操作，传入的东西可以是Object然后我们对他解析，提取西药的东西
        banner.setImages(list);//这里已经被我重写成可以放入需要展示的NewsList，传入News对象后，就会解析并提取相应的图片再显示。
        banner.setDelayTime(3000);//这里设置轮番的时间
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//数字模式的数字只能显示在右边！！
        // banner.setBackgroundColor(R.color);
        banner.setBannerTitles(getTitleList(list));//从NewsList中获取到 标题List！
        banner.start();
    }

    private void initHeadviewlist() {
        headviewslist = new ArrayList<Headview>();

        List<User> users = mUserDao.loadAll();

        for (User user : users) {
            LogUtil.e(user.getImage());
            ClasstoItem.UserToHeadview(user, headviewslist);
        }

        forumItemList = new ArrayList<ForumItem>();
        int i=0;
        for (Forum forum : mForumDao.loadAll()) {
            i++;
            if(i<5) {

                ClasstoItem.ForumToForumItem(forum, forumItemList);
            }
        }
        ChooseNews = new ArrayList<>();

        for (News news : mNewsDao.loadAll()) {

                ClasstoItem.NewToChooseNews(news, ChooseNews);

        }
    }


    private void initBusineBanner() {

        /*----------------需要被展示的新闻------------------------*/
        list = new ArrayList<News>();
        News news=new News.Builder()
                .new_drawable(R.drawable.image_news1)
                .new_title("英雄联盟网吧联赛等你来战")
                .new_content("2017英特尔杯《英雄联盟》（简称：LOL）QQ网吧冠军联赛（以下称网吧联赛）" +
                        "自4月4日火爆开战起，随着比赛在全国范围的深入展开，迄今已有近4000家QQ网吧报名参赛，" +
                        "而参与此次线下赛事的玩家人数也已将近10万。在《英雄联盟》大电竞战略的不断实践当中，" +
                        "全民电竞计划以网吧联赛为依托，开辟出了一条火爆全国的网吧电竞之路。")
                .new_build_time("2017-10-03").build();
        list.add(news);
        news=new News.Builder()
                .new_drawable(R.drawable.image_news3)
                .new_title("三国王者战")
                .new_content("王者之战 编辑\n" +
                        "三国杀王者之战伴随着三国杀的发展，也来到了第5个年头。无论从体系还是规则都正在趋于成熟，是目前国内专业性最强、级别最高、奖金最多、影响力最广的桌游竞技赛事。获得了广大三国杀玩家的认可，在玩家群体中深具影响力。\n" +
                        "“2017三国杀王者之战”即将开启新的篇章。2017年6月18日，本届王者之战启动仪式将在北京正通创意中心举行。届时酷6网将进行全程的现场直播。")
                .new_build_time("2017-10-04").build();
        list.add(news);
        news=new News.Builder()
                .new_drawable(R.drawable.image_news4)
                .new_title("狼人杀高校挑战赛")
                .new_content("2016，北京狼人杀高校挑战赛，等你来战")
                .new_build_time("2017-10-05").build();
        list.add(news);
        /*-------------------------------------------------------*/


    }

    public void initData() {

        bottomNavigationViewEx = (BottomNavigationViewEx) getActivity().findViewById(R.id.bottom_navigation);
        searchView = (FloatingSearchView) getActivity().findViewById(R.id.floating_search_view);
        initHeadBanner();
        initBusineBanner();
        initHeadviewlist();
        cardAdapter = new CardPagerAdapter(MyApplication.getContext(), advertisementList);
        //只要在adapter加入广告列即可
        viewPager = (BezierViewPager) view.findViewById(R.id.view_page);
        viewPager.setCurrentItem(2);
        // viewPager.setDataList(AdvertisementList);
        viewPager.setClipToPadding(false);
        viewPager.setAdapter(cardAdapter);
        viewPager.showTransformer(0.5f);
        viewPager.setPadding(200, 100, 200, 100);
        viewPager.setOnPageChangeListener(new MyPageChangeListener(MyApplication.getContext(), viewPager));
        initRefresh(view);
        initRecycle(view);

        TastyToast.makeText(MyApplication.getContext(), "你好，悦游!", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);


    }

    public List<String> getTitleList(List<News> list) {

        List<String> TitleList = new ArrayList<>();

        for (News one : list) {
            String title = one.getNew_title();
            //这里可以对title进行处理

            TitleList.add(title);
        }
        return TitleList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }
}
