package com.example.administrator.happygame.mvp.view;

import com.example.administrator.happygame.been.Activity;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public interface ActivitiesView {
    void showProgress();

    void hideProgress();


    void getActivityData(List<Activity> activityList);
}
