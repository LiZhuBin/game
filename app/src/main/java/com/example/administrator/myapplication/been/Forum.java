package com.example.administrator.myapplication.been;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/17 0017.
 */

public class Forum implements Serializable {
    /**
     * id : 1
     * type : 狼人杀
     * userId : 1
     * title : 狼人杀心得
     * content : 平安夜预言家最好别跳
     * commentId : 1|2
     * commentInfo : 说得对|我反对
     * image : forum/狼人杀/picture1.jpg
     * like : 0000000005
     */

    private String id;
    private String type;
    private String userId;
    private String title;
    private String content;
    private String commentId;
    private String commentInfo;
    private String image;
    private String like;

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public String getImage() {
        return image;
    }

    public String getLike() {
        return like;
    }
}
