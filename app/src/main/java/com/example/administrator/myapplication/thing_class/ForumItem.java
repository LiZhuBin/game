package com.example.administrator.myapplication.thing_class;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ForumItem {
        private String userName,forumTime,forumTitle;
    private int imageId;

    public ForumItem( String forumTitle, int imageId) {

        this.forumTitle = forumTitle;
        this.imageId = imageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getForumTime() {
        return forumTime;
    }

    public void setForumTime(String forumTime) {
        this.forumTime = forumTime;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
