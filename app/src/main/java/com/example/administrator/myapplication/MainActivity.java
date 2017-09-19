package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.main_fragment.AddFragment;
import com.example.administrator.myapplication.main_fragment.ForumFragment;
import com.example.administrator.myapplication.main_fragment.RecommentFragment;
import com.example.administrator.myapplication.main_fragment.UserFragment;
import com.example.administrator.myapplication.util.SPUtil;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.ButterKnife;

import static com.example.administrator.myapplication.BuilderManager.getImageResource;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final String TAG = "MainActivity";
  private  BottomNavigationViewEx navigation;
    private ViewPager viewPager;
    private BoomMenuButton bmb;
    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);

                break;
                case R.id.navigation_dashboard:

                    viewPager.setCurrentItem(1);

                    break;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.navigation_email2:
                    viewPager.setCurrentItem(3);
                    break;
                default:
                    return false;
            }
            return true;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//全屏没有状态栏。

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        TastyToast.makeText(getApplicationContext(), "你好，悦游!", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
        ButterKnife.bind(this);

        initBottomNavigationViewEx();

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(this);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        initData();
        floatButton();
        initRefresh();
        final FloatingSearchView mSearchView=(FloatingSearchView)findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        //找到searchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        return super.onCreateOptionsMenu(menu);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecommentFragment());
        adapter.addFragment(new AddFragment());
        adapter.addFragment(new ForumFragment());
        adapter.addFragment(new UserFragment());
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void floatButton() {
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        int[] buttonText = {R.string.bottom1, R.string.bottom2, R.string.bottom3, R.string.bottom4,R.string.bottom4};
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            viewPager.setCurrentItem(index);
                        }
                    })
                    .isRound(false)
                    .shadowCornerRadius(Util.dp2px(40))
                    .buttonCornerRadius(Util.dp2px(40))

                    .normalImageRes(getImageResource())
                    .textSize(20)
                    .normalTextRes(buttonText[i]);
            bmb.addBuilder(builder);
        }
    }
public void initBottomNavigationViewEx(){
    navigation= (BottomNavigationViewEx) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
   // navigation.enableAnimation(false);
    navigation.enableShiftingMode(false);
    navigation.enableItemShiftingMode(false);
}
public void initRefresh(){
    RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.main_refreshLayout);
    refreshLayout.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
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


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                navigation.setSelectedItemId(R.id.navigation_home);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.navigation_dashboard);
                break;
            case 2:
                navigation.setSelectedItemId(R.id.navigation_notifications);
                break;
            case 3:
                navigation.setSelectedItemId(R.id.navigation_email2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void initData(){
        if(SPUtil.get(MainActivity.this,"UserId",1)==null) {
            SPUtil.put(MainActivity.this, "UserId", "1");
        }

    }
}

