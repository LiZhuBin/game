package com.example.administrator.myapplication.thing_class;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ForumItem implements Serializable {
   String forumTitle,forumAddNum,forumLikeNum,forumImage;
    int id;

    public String getForumImage() {
        return forumImage;
    }

    public void setForumImage(String forumImage) {
        this.forumImage = forumImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getForumAddNum() {
        return forumAddNum;
    }

    public void setForumAddNum(String forumAddNum) {
        this.forumAddNum = forumAddNum;
    }

    public String getForumLikeNum() {
        return forumLikeNum;
    }

    public void setForumLikeNum(String forumLikeNum) {
        this.forumLikeNum = forumLikeNum;
    }

    public ForumItem() {
    }

    public ForumItem(String forumTitle, String forumAddNum, String forumLikeNum, String forumImage, int id) {

        this.forumTitle = forumTitle;
        this.forumAddNum = forumAddNum;
        this.forumLikeNum = forumLikeNum;
        this.forumImage = forumImage;
        this.id = id;
    }
}
