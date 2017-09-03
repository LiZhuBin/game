package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.main_fragment.AddFragment;
import com.example.administrator.myapplication.main_fragment.ForumFragment;
import com.example.administrator.myapplication.main_fragment.RecommentFragment;
import com.example.administrator.myapplication.main_fragment.UserFragment;
import com.example.administrator.myapplication.my_ui.NoScrollViewPager;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.ButterKnife;

import static com.example.administrator.myapplication.BuilderManager.getImageResource;

public class MainActivity extends AppCompatActivity {


  private  BottomNavigationViewEx navigation;
    private NoScrollViewPager viewPager;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//全屏没有状态栏。

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNavigationViewEx();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // getSupportActionBar().hide();
        setSupportActionBar(toolbar);
        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        floatButton();
        initRefresh();
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
    navigation.enableAnimation(false);
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
}

