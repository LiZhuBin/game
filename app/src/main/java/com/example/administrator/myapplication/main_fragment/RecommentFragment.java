package com.example.administrator.myapplication.main_fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.administrator.myapplication.been.News;
import com.example.administrator.myapplication.been.User;
import com.example.administrator.myapplication.my_ui.GlideImageLoader;
import com.example.administrator.myapplication.thing_class.ForumItem;
import com.example.administrator.myapplication.util.ApplicationUtil;
import com.example.administrator.myapplication.util.GlobalData;
import com.example.administrator.myapplication.util.HttpUtil;
import com.example.administrator.myapplication.zairuclas.Headview;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecommentFragment extends Fragment  {
    List<News> newsList ;

    protected WeakReference<View> mRootView;//缓存fragment数据
    RecyclerView recyclerView;

    List<News> ChooseNews = new ArrayList<>();
    MyRecyclerAdapter adapter ;
    RecyclerView hottieRecyclerview;
    long    Reflash = 1500;
    List<Headview> headviewslist;
    private ForumItem[] forumItems={new ForumItem("一起来玩吧","5","2",1,R.drawable.image_scrolling_head),
            new ForumItem("一起来玩吧","5","2",2,R.drawable.image_scrolling_head),
            new ForumItem("一起来玩吧","5","2",3,R.drawable.image_scrolling_head),
            new ForumItem("一起来玩吧","5","2",4,R.drawable.image_scrolling_head),

    };
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
            initNewlist();
            initNeswlist();
            initBanner(view);
            initHeadviewlist(view);
            setGridlayout_adapter_and_data(view);
            setLinearlayout_hottie_anything(view);
            setLeftDaohang1(view);
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
//                    SPUtil.put(ApplicationUtil.getContext(),"me",user);
//                    User user1=(User)SPUtil.get(ApplicationUtil.getContext(),"me",user);
                    Headview headview=new Headview(GlobalData.httpAddressUser+user.getImage(),user.getName());
                    headviewslist.add(headview);
                    new MyAsyncTask(user).execute();

                }
            }
        });
        RecyclerView headviewRecyclerView = (RecyclerView) view.findViewById(R.id.headviewRecyclerView);
        headviewRecyclerView.setAdapter(new headviewAdapter(headviewslist));
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ApplicationUtil.getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        headviewRecyclerView.setLayoutManager(layoutManager1);
        headviewRecyclerView.addItemDecoration(new headitemdire(ApplicationUtil.getContext()));
    }

    private void setLeftDaohang1(final View view) {

        final ImageView imageView = (ImageView)view.findViewById(R.id.Refresh1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressBar progressbar = (ProgressBar)view.findViewById(R.id.firstBar1);
                Animation animation = AnimationUtils.loadAnimation(ApplicationUtil.getContext(),R.anim.refelsh);
                animation.setDuration(Reflash);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        progressbar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initNeswlist();
                            }
                        },Reflash);
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        progressbar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                imageView.startAnimation(animation); // 上面是旋转动画，然后出现转圈
            }
        });
    }

    private void setLinearlayout_hottie_anything(View view) {
        hottieRecyclerview = (RecyclerView)view.findViewById(R.id.Recycler2);
      //  hottieAdapter = new HottieAdapter(ChooseTie) ;
        for (int i=0;i<forumItems.length;i++){
            forumItemList.add(forumItems[i]);
        }
        forumAdapter=new ForumAdapter(forumItemList);
        hottieRecyclerview.setAdapter(forumAdapter);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ApplicationUtil.getContext());
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

    private void setGridlayout_adapter_and_data(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        adapter = new MyRecyclerAdapter(ChooseNews);  //定义好数据
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(ApplicationUtil.getContext(),2); //设置布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new hotpoint_item(ApplicationUtil.getContext())); //进行边界装饰
    }




    private void initNewlist() {
        newsList = new ArrayList<>();
        newsList.add(new News(R.drawable.hezhao,"Title1","\"现在Android7.0在份额在不断的增加(个人感觉Android升级给用户带来最多的并不是更流畅而是更安全),许多应用都已经开始或者已经适配了Android7.0,如果你还不了解Android7.0有哪些新特性的小伙伴们,请移步,查看详细的介绍. 如果你的App是一个轻量级的应用,那么你很有可能会去调用系统的一些功能如拍照,裁剪,应用下载,安装,而不是依靠第三方库,那么你肯定用的到这个FileProvide.要了解一个你不熟悉的东西,最好的方式就是看官网的API文档(教材),那么我们就来翻译一下大致内容(英语水平有限,大神勿喷,不好的地方还请指正):\n" +
                "FileProviderAPI翻译\n" +
                "FileProvider是ContentProvider(有助于App共享文件更加安全的组件)的一个特殊的子类,通过 content:// Uri获取一个文件而不是file:/// Uri. 当你创建一个包含有content URI的Intent的时候,Content URI赋予你临时的读写权限.为了可以给一个目标app(原文client app,这里应该是目标App)发送一个特定的content URI,你也可以通过调用Intent.setFlags()去添加权限,这些权限一直有效,只要接收的Activity在栈中还存活着.要是跳转到Service,只要这个Service在运行,权限就有效. 相比较而言,用file:/// Uri来获取文件,你不得不修改系统底层的文件权限.在你改变文件权限之前,你授予权限,对于任何app都是可用的,所以授予这个级别的权限从根本上是不安全的.(就是liunx的rwx) content URI提高了文件安全访问的级别,使FileProvider成为Android的安全体系重要组成部分。\n" +
                "FileProvider 可大致分为下面几个部分\n" +
                "自定义一个FileProvider\n" +
                "指定可用文件\n" +
                "给文件生成content URI\n" +
                "授予URI一个临时权限\n" +
                "给另一个App提供URI\n" +
                "自定义一个FileProvider\n" +
                "因为FileProvider的默认功能已经可以在你App的XML中通过 元素去指定一个FileProvider,包括指定FileProvider的组件,给 android.s如果你控制"));
        newsList.add(new News(R.drawable.hezhao,"Title1","2"));
        newsList.add(new News(R.drawable.hezhao,"Title2","3"));
        newsList.add(new News(R.drawable.hezhao,"Title3","4"));
        newsList.add(new News(R.drawable.hezhao,"Title4","5"));
        newsList.add(new News(R.drawable.hezhao,"Title5","6"));
        newsList.add(new News(R.drawable.hezhao,"Title6","7"));
        newsList.add(new News(R.drawable.hezhao,"Title7","8"));
        newsList.add(new News(R.drawable.hezhao,"Title8","9"));
        newsList.add(new News(R.drawable.hezhao,"Title9","10"));
    }


    private void initNeswlist(){
        ChooseNews.clear();
        List<Integer> alist = new ArrayList<>();
        int size = 0;
        while (size < 6)
        {
            //防止数据重复
            boolean isRepair = false;
            int index = 0;
            Random random = new Random();
            index = random.nextInt(newsList.size());
            News one = newsList.get(index);
            if (one.isUse()==true){continue;}
            one.setUse(true);
            ChooseNews.add(one);
            size++;
            alist.add(index);
        };
        for (int i : alist)
        {
            News one = newsList.get(i);
            one.setUse(false);
        }
        if (adapter!=null)
            adapter.notifyDataSetChanged();
    }


    private void initBanner(View view) {

        /*----------------需要被展示的新闻------------------------*/
        List<News> list = new ArrayList<News>() ;
        list.add(new News(News.NewType.LangRenSha,R.drawable.image_activity_background,"Title1","2"));
        list.add(new News(News.NewType.LangRenSha,R.drawable.image_whilte_me,"Title2","3"));
        list.add(new News(News.NewType.LangRenSha,R.drawable.hezhao,"Title3","4"));
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
            String title = one.getImage_title();
            //这里可以对title进行处理

            TitleList.add( title );


        }


        return  TitleList ;
    }
}
