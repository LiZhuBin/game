package com.example.administrator.happygame.mvp.view;

/**
 * Created by Administrator on 2017/10/19 0019.
 */
public interface BaseView {

    //显示dialog
    void showLoadingDialog(String msg);

    //取消dialog
    void dismissLoadingDialog();
}