package com.example.administrator.myapplication.thing_class;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ForumItem implements Serializable {
   String forumTitle,forumAddNum,forumLikeNum;
    int id,forumImage;

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

    public int getForumImage() {
        return forumImage;
    }

    public void setForumImage(int forumImage) {
        this.forumImage = forumImage;
    }

    public ForumItem(String forumTitle, String forumAddNum, String forumLikeNum, int id, int forumImage) {
        this.forumTitle = forumTitle;
        this.forumAddNum = forumAddNum;
        this.forumLikeNum = forumLikeNum;
        this.id = id;
        this.forumImage = forumImage;
    }
}
