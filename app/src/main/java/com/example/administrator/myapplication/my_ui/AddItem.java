package com.example.administrator.myapplication.my_ui;

/**
 * Created by Administrator on 2017/9/16 0016.
 */

public class AddItem {

    public int imageId;
    String forumUserName;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getForumUserName() {
        return forumUserName;
    }

    public void setForumUserName(String forumUserName) {
        this.forumUserName = forumUserName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getForumNum() {
        return forumNum;
    }

    public void setForumNum(String forumNum) {
        this.forumNum = forumNum;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getForumContent() {
        return forumContent;
    }

    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }

    public String getForumTime() {
        return forumTime;
    }

    public void setForumTime(String forumTime) {
        this.forumTime = forumTime;
    }

    public String getForumAddress() {
        return forumAddress;
    }

    public void setForumAddress(String forumAddress) {
        this.forumAddress = forumAddress;
    }

    public AddItem(int imageId, String forumUserName, String time, String forumNum, String forumTitle, String forumContent, String forumTime, String forumAddress) {

        this.imageId = imageId;
        this.forumUserName = forumUserName;
        this.time = time;
        this.forumNum = forumNum;
        this.forumTitle = forumTitle;
        this.forumContent = forumContent;
        this.forumTime = forumTime;
        this.forumAddress = forumAddress;
    }

    String time;
    String forumNum;
    String forumTitle;
    String forumContent;
    String forumTime;
    String forumAddress;
}
