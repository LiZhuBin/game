package com.example.administrator.happygame.mvp.model;

import com.example.administrator.happygame.been.Activity;
import com.example.administrator.happygame.mvp.api.ApiServiceManager;
import com.example.administrator.happygame.util.LogUtil;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class ActivitiesModelImpl implements ActivitiesModel {
    private OnActivitiesListener mOnActivitiesListener;

    public ActivitiesModelImpl(OnActivitiesListener onActivitiesListener) {
        this.mOnActivitiesListener = onActivitiesListener;
    }

    //        ApiServiceManager.getActivityData("1")            //获取Observable对象
//                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
//                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
//                .subscribe(new Consumer<List<Activity>>() {
//                    @Override
//                    public void accept(List<Activity> activityList) throws Exception {
//                        for (final Activity activity : activityList) {
//                            // GlobalData.HTTP_ADDRESS_ACTIVITY+activity.getImage(),
//                            ClasstoItem.ActivityToAddItem(activity, addList);
//
//                        }
//                    }
//                });
    @Override
    public Subscription getActivityData(String id) {
        Observable<List<Activity>> observable = ApiServiceManager.getActivityData("1");
        Subscription subscribe = (Subscription) observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Activity>>() {
                    @Override
                    public void accept(List<Activity> activityList) throws Exception {
                        if (mOnActivitiesListener != null) {
                            LogUtil.e(activityList.get(0).getImage());
                            mOnActivitiesListener.onSuccess(activityList);
                        } else {
                            LogUtil.e("ffffffff");
                        }
                    }
                });
        return subscribe;
    }

    public interface OnActivitiesListener {
        void onSuccess(List<Activity> activityList);

        void onFailure(Throwable throwable);
    }

}

