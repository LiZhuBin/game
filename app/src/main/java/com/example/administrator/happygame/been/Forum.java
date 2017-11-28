package com.example.administrator.happygame.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/9/17 0017.
 */
@Entity
public class Forum  {
    @Id
    private String id;
    private String type;
    private String userId;
    private String title;
    private String content;
    private String comment;
    private String image;
    private String like;
    private String data;
    @Generated(hash = 1182769316)
    public Forum(String id, String type, String userId, String title,
            String content, String comment, String image, String like,
            String data) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.image = image;
        this.like = like;
        this.data = data;
    }
    @Generated(hash = 2136154180)
    public Forum() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getLike() {
        return this.like;
    }
    public void setLike(String like) {
        this.like = like;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }}
