package com.example.administrator.happygame.mvp.model;

import org.reactivestreams.Subscription;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public interface ActivitiesModel {
    Subscription getActivityData(String id);
}
