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
     * comment : 1|上次我就是不跳了结果输了|2|有道理|3|嗯嗯
     * image : forum/狼人杀/picture1.jpg
     * like : 5
     * data : 2017-10-04
     */

    private String id;
    private String type;
    private String userId;
    private String title;
    private String content;
    private String comment;
    private String image;
    private String like;
    private String data;

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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getComment() {
        return comment;
    }

    public String getImage() {
        return image;
    }

    public String getLike() {
        return like;
    }

    public String getData() {
        return data;
    }
}
