package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.administrator.myapplication.activity.SearchActivity;
import com.example.administrator.myapplication.adapter.ViewPagerAdapter;
import com.example.administrator.myapplication.main_fragment.AddFragment;
import com.example.administrator.myapplication.main_fragment.ForumFragment;
import com.example.administrator.myapplication.main_fragment.RecommentFragment;
import com.example.administrator.myapplication.main_fragment.UserFragment;
import com.example.administrator.myapplication.util.SPUtil;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final String TAG = "MainActivity";

    private  BottomNavigationViewEx navigation;
    private ViewPager viewPager;

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

        final FloatingSearchView mSearchView=(FloatingSearchView)findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                mSearchView.clearFocus();
            }

            @Override
            public void onFocusCleared() {

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



public void initBottomNavigationViewEx(){
    navigation= (BottomNavigationViewEx) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
   // navigation.enableAnimation(false);
    navigation.enableShiftingMode(false);
    navigation.enableItemShiftingMode(false);
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

