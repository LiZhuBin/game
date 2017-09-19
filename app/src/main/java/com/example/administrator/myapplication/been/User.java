package com.example.administrator.myapplication.been;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/17 0017.
 */

public class User extends DataSupport implements Serializable{
    /**
     * id : 1
     * praise_num : 0
     * name : 李主彬
     * friends : aaa|bbb|ccc
     * readingActivities : 1|2|3
     * collectActivities : 2|3
     * doingActivities : 1
     * completeActivities : 2|4|5
     * image : picture/view.jpg
     * password : ddd
     * posts : 2|3
     */

    private String id;
    private String praise_num;
    private String name;
    private String friends;
    private String readingActivities;
    private String collectActivities;
    private String doingActivities;
    private String completeActivities;
    private String image;
    private String password;
    private String posts;

    public void setId(String id) {
        this.id = id;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public void setReadingActivities(String readingActivities) {
        this.readingActivities = readingActivities;
    }

    public void setCollectActivities(String collectActivities) {
        this.collectActivities = collectActivities;
    }

    public void setDoingActivities(String doingActivities) {
        this.doingActivities = doingActivities;
    }

    public void setCompleteActivities(String completeActivities) {
        this.completeActivities = completeActivities;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getId() {
        return id;
    }

    public String getPraise_num() {
        return praise_num;
    }

    public String getName() {
        return name;
    }

    public String getFriends() {
        return friends;
    }

    public String getReadingActivities() {
        return readingActivities;
    }

    public String getCollectActivities() {
        return collectActivities;
    }

    public String getDoingActivities() {
        return doingActivities;
    }

    public String getCompleteActivities() {
        return completeActivities;
    }

    public String getImage() {
        return image;
    }

    public String getPassword() {
        return password;
    }

    public String getPosts() {
        return posts;
    }
}
