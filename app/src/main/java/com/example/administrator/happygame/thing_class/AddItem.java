package com.example.administrator.happygame.thing_class;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/16 0016.
 */

public class AddItem implements Serializable {
    public String activityId;
    public String imageId;
    String activityNum;
    String activityTitle;
    String activityContent;
    String activityTime;
    String activityAddress;

    public AddItem(String activityId, String imageId, String activityNum, String activityTitle, String activityContent, String activityTime, String activityAddress) {
        this.activityId = activityId;
        this.imageId = imageId;
        this.activityNum = activityNum;
        this.activityTitle = activityTitle;
        this.activityContent = activityContent;
        this.activityTime = activityTime;
        this.activityAddress = activityAddress;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(String activityNum) {
        this.activityNum = activityNum;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }
}
