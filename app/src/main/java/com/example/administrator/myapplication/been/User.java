package com.example.administrator.myapplication.been;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/9/17 0017.
 */

public class User implements Parcelable {
    /**
     * id : 1
     * praise_num : 0
     * praise_id:2|3
     * name : 李主彬
     * friends : aaa|bbb|ccc
     * readingActivities : 1|2|3
     * collectActivities : 2|3
     * doingActivities : 1
     * completeActivities : 2|4|5
     * image : picture/view.jpg
     * password : ddd
     * posts : 2|3
     * attentionId:
     *beattentionId:
     */

    private String id;
    private String praise_num;
    private String praise_id;
    private String name;
    private String friends;
    private String postActivities;
    private String collectActivities;
    private String doingActivities;
    private String completeActivities;
    private String image;
    private String password;
    private String posts;
private String attentionId;
private String beattentionId;

    public String getPraise_id() {
        return praise_id;
    }

    public void setPraise_id(String praise_id) {
        this.praise_id = praise_id;
    }

    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }

    public String getBeattentionId() {
        return beattentionId;
    }

    public void setBeattentionId(String beattentionId) {
        this.beattentionId = beattentionId;
    }

    public User(String id, String praise_num,String praise_id, String name, String friends, String postActivities, String collectActivities, String doingActivities, String completeActivities, String image, String password, String posts,String attentionId,String beattentionId) {
        this.id = id;
        this.praise_num = praise_num;
        this.praise_id=praise_id;
        this.name = name;
        this.friends = friends;
        this.postActivities = postActivities;
        this.collectActivities = collectActivities;
        this.doingActivities = doingActivities;
        this.completeActivities = completeActivities;
        this.image = image;
        this.password = password;
        this.posts = posts;
        this.attentionId=attentionId;
        this.beattentionId=attentionId;
    }

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

    public String getPostActivities() {
        return postActivities;
    }

    public void setPostActivities(String postActivities) {
        this.postActivities = postActivities;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.praise_num);
        dest.writeString(this.praise_id);
        dest.writeString(this.name);
        dest.writeString(this.friends);
        dest.writeString(this.postActivities);
        dest.writeString(this.collectActivities);
        dest.writeString(this.doingActivities);
        dest.writeString(this.completeActivities);
        dest.writeString(this.image);
        dest.writeString(this.password);
        dest.writeString(this.posts);
        dest.writeString(this.attentionId);
        dest.writeString(this.beattentionId);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.praise_num = in.readString();
        this.praise_id = in.readString();
        this.name = in.readString();
        this.friends = in.readString();
        this.postActivities = in.readString();
        this.collectActivities = in.readString();
        this.doingActivities = in.readString();
        this.completeActivities = in.readString();
        this.image = in.readString();
        this.password = in.readString();
        this.posts = in.readString();
        this.attentionId = in.readString();
        this.beattentionId = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
