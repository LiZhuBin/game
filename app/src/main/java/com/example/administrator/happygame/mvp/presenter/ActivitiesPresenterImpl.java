package com.example.administrator.happygame.mvp.presenter;

import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.mvp.model.ActivitiesModel;
import com.example.administrator.happygame.mvp.model.ActivitiesModelImpl;
import com.example.administrator.happygame.mvp.view.ActivitiesView;
import com.example.administrator.happygame.util.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class ActivitiesPresenterImpl extends BasePresenter<ActivitiesView> implements ActivitiesPresenter,ActivitiesModelImpl.OnActivitiesListener {
    private ActivitiesModel mActivitiesModel;
    private ActivitiesView mActivitiesView;
    public ActivitiesPresenterImpl(ActivitiesView movieView) {
        this.mActivitiesView = movieView;
        mActivitiesModel = new ActivitiesModelImpl(this);
    }


    @Override
    public void getActivityData(String id) {
        mActivitiesView.showProgress();

    }

    @Override
    public void onSuccess(List<Activity> activityList) {
        mActivitiesView.hideProgress();
        LogUtil.e("ffffff");
        mActivitiesView.getActivityData(activityList);
    }

    @Override
    public void onFailure(Throwable throwable) {
        mActivitiesView.hideProgress();
    }
}
