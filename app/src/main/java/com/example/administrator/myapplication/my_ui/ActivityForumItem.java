package com.example.administrator.myapplication.my_ui;

/**
 * Created by Administrator on 2017/9/16 0016.
 */

public class ActivityForumItem {
    public String mImageUrl;
    public ActivityForumItem(String mImageUrl, String mUsername, String mLou, String mTime, String mDetile) {
        this.mImageUrl = mImageUrl;
        this.mUsername = mUsername;
        this.mLou = mLou;
        this.mTime = mTime;
        this.mDetile = mDetile;
    }

    public String getmImageUrl() {

        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public int mImageId;
    public String mUsername;
    public String mLou;
    public String mTime;
    public String mDetile;

    public ActivityForumItem(int mImageId, String mUsername, String mLou, String mTime, String mDetile) {
        this.mImageId = mImageId;
        this.mUsername = mUsername;
        this.mLou = mLou;
        this.mTime = mTime;
        this.mDetile = mDetile;
    }

    public int getmImageId() {
        return mImageId;
    }

    public void setmImageId(int mImageId) {
        this.mImageId = mImageId;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmLou() {
        return mLou;
    }

    public void setmLou(String mLou) {
        this.mLou = mLou;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmDetile() {
        return mDetile;
    }

    public void setmDetile(String mDetile) {
        this.mDetile = mDetile;
    }
}
