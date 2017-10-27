package com.example.administrator.happygame.been;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/9/17 0017.
 */
@Entity
public class User implements Parcelable {
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
    @Id
    private String id;
    private String praise_num;
    private String praise_id;
    private String six;
    private String credit;
    private String signature;
    private String address;
    private String name;
    private String friends;
    private String collectActivities;
    private String doingActivities;
    private String image;
    private String password;
    private String posts;
    private String attentionId;
    private String beattentionId;

    @Generated(hash = 2039019418)
    public User(String id, String praise_num, String praise_id, String six,
                String credit, String signature, String address, String name,
                String friends, String collectActivities, String doingActivities,
                String image, String password, String posts, String attentionId,
                String beattentionId) {
        this.id = id;
        this.praise_num = praise_num;
        this.praise_id = praise_id;
        this.six = six;
        this.credit = credit;
        this.signature = signature;
        this.address = address;
        this.name = name;
        this.friends = friends;
        this.collectActivities = collectActivities;
        this.doingActivities = doingActivities;
        this.image = image;
        this.password = password;
        this.posts = posts;
        this.attentionId = attentionId;
        this.beattentionId = beattentionId;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.praise_num = in.readString();
        this.praise_id = in.readString();
        this.six = in.readString();
        this.credit = in.readString();
        this.signature = in.readString();
        this.address = in.readString();
        this.name = in.readString();
        this.friends = in.readString();
        this.collectActivities = in.readString();
        this.doingActivities = in.readString();
        this.image = in.readString();
        this.password = in.readString();
        this.posts = in.readString();
        this.attentionId = in.readString();
        this.beattentionId = in.readString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPraise_num() {
        return this.praise_num;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public String getPraise_id() {
        return this.praise_id;
    }

    public void setPraise_id(String praise_id) {
        this.praise_id = praise_id;
    }

    public String getSix() {
        return this.six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getCredit() {
        return this.credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFriends() {
        return this.friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getCollectActivities() {
        return this.collectActivities;
    }

    public void setCollectActivities(String collectActivities) {
        this.collectActivities = collectActivities;
    }

    public String getDoingActivities() {
        return this.doingActivities;
    }

    public void setDoingActivities(String doingActivities) {
        this.doingActivities = doingActivities;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosts() {
        return this.posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getAttentionId() {
        return this.attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }

    public String getBeattentionId() {
        return this.beattentionId;
    }

    public void setBeattentionId(String beattentionId) {
        this.beattentionId = beattentionId;
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
        dest.writeString(this.six);
        dest.writeString(this.credit);
        dest.writeString(this.signature);
        dest.writeString(this.address);
        dest.writeString(this.name);
        dest.writeString(this.friends);
        dest.writeString(this.collectActivities);
        dest.writeString(this.doingActivities);
        dest.writeString(this.image);
        dest.writeString(this.password);
        dest.writeString(this.posts);
        dest.writeString(this.attentionId);
        dest.writeString(this.beattentionId);
    }
}
