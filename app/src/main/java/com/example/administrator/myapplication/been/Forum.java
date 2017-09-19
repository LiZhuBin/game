package com.example.administrator.myapplication.been;

/**
 * Created by Administrator on 2017/9/17 0017.
 */

public class Forum {
    int id,userId,like;
    String type,title,content,commentId,commentInfo,image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Forum(int id, int userId, int like, String type, String title, String content, String commentId, String commentInfo, String image) {

        this.id = id;
        this.userId = userId;
        this.like = like;
        this.type = type;
        this.title = title;
        this.content = content;
        this.commentId = commentId;
        this.commentInfo = commentInfo;
        this.image = image;
    }
}
