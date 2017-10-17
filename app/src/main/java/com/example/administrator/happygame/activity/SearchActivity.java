package com.example.administrator.happygame.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.administrator.happygame.R;
import com.example.administrator.happygame.base.BaseActivity;

public class SearchActivity extends BaseActivity {

    FloatingSearchView searchView;
    private Boolean searchview_isFocus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override

            public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search_menu_action:

                        String a = searchView.getQuery();

                        //getQuery方法获得文本
                        searchView.clearQuery();//clear搜索栏
                        String b = searchView.getCurrentMenuItems().toString();

                        //get获得搜索栏点击的名字！！
                        break;
                }
            }
        });
        searchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() { //左边的home按钮点击
                if (!searchview_isFocus) {

                    finish();
                }
            }
        });
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {  //在搜索栏获取焦点退出时候让数据消失！！
                searchview_isFocus = true;

                searchView.clearQuery();
            }

            @Override
            public void onFocusCleared() {
                searchview_isFocus = false;

                //你也可以将已经打上的搜索字符保存，以致在下一次点击的时候，搜索栏内还保存着之前输入的字符
                //mSearchView.setSearchText(searchSuggestion.getBody());
            }
        });
    }


}
