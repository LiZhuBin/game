package com.example.administrator.happygame.adapter;

import android.view.View;

import cn.sharesdk.framework.TitleLayout;
import cn.sharesdk.framework.authorize.AuthorizeAdapter;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class ShareAdapter extends AuthorizeAdapter {
    View rv = (View) getBodyView().getParent();
    AuthorizeAdapter adapter = new AuthorizeAdapter();

    @Override
    public void onCreate() {

        super.onCreate();
        hideShareSDKLogo();
        TitleLayout llTitle = getTitleLayout();
        llTitle.getChildAt(1).setVisibility(View.GONE);
    }
}
