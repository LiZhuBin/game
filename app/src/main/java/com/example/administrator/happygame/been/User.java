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
    /**
     * id : 1
     * praise_num : 3
     * praise_id : 2|3|4
     * six : 1
     * credit : 800
     * signature : 我就是我，是不一样的烟火
     * address : null
     * name : 李主彬
     * friends : 4|5|6
     * collectForum : 1|2
     * collectNews : 3|4
     * collectActivities : 2|3
     * doingActivities : 1|3|4
     * image : user/user_picture1.jpg
     * password : ddd
     * posts : 1
     * attentionId : 2|3
     * beattentionId : 2|4
     */
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
    private String collectForum;
    private String collectNews;
    private String collectActivities;
    private String doingActivities;
    private String image;
    private String password;
    private String posts;
    private String attentionId;
    private String beattentionId;

    private User(Builder builder) {
        setId(builder.id);
        setPraise_num(builder.praise_num);
        setPraise_id(builder.praise_id);
        setSix(builder.six);
        setCredit(builder.credit);
        setSignature(builder.signature);
        setAddress(builder.address);
        setName(builder.name);
        setFriends(builder.friends);
        setCollectForum(builder.collectForum);
        setCollectNews(builder.collectNews);
        setCollectActivities(builder.collectActivities);
        setDoingActivities(builder.doingActivities);
        setImage(builder.image);
        setPassword(builder.password);
        setPosts(builder.posts);
        setAttentionId(builder.attentionId);
        setBeattentionId(builder.beattentionId);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public void setPraise_id(String praise_id) {
        this.praise_id = praise_id;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public void setCollectForum(String collectForum) {
        this.collectForum = collectForum;
    }

    public void setCollectNews(String collectNews) {
        this.collectNews = collectNews;
    }

    public void setCollectActivities(String collectActivities) {
        this.collectActivities = collectActivities;
    }

    public void setDoingActivities(String doingActivities) {
        this.doingActivities = doingActivities;
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

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }

    public void setBeattentionId(String beattentionId) {
        this.beattentionId = beattentionId;
    }

    public String getId() {
        return id;
    }

    public String getPraise_num() {
        return praise_num;
    }

    public String getPraise_id() {
        return praise_id;
    }

    public String getSix() {
        return six;
    }

    public String getCredit() {
        return credit;
    }

    public String getSignature() {
        return signature;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getFriends() {
        return friends;
    }

    public String getCollectForum() {
        return collectForum;
    }

    public String getCollectNews() {
        return collectNews;
    }

    public String getCollectActivities() {
        return collectActivities;
    }

    public String getDoingActivities() {
        return doingActivities;
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

    public String getAttentionId() {
        return attentionId;
    }

    public String getBeattentionId() {
        return beattentionId;
    }

    public static final class Builder {
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
        private String collectForum;
        private String collectNews;
        private String collectActivities;
        private String doingActivities;
        private String image;
        private String password;
        private String posts;
        private String attentionId;
        private String beattentionId;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder praise_num(String val) {
            praise_num = val;
            return this;
        }

        public Builder praise_id(String val) {
            praise_id = val;
            return this;
        }

        public Builder six(String val) {
            six = val;
            return this;
        }

        public Builder credit(String val) {
            credit = val;
            return this;
        }

        public Builder signature(String val) {
            signature = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder friends(String val) {
            friends = val;
            return this;
        }

        public Builder collectForum(String val) {
            collectForum = val;
            return this;
        }

        public Builder collectNews(String val) {
            collectNews = val;
            return this;
        }

        public Builder collectActivities(String val) {
            collectActivities = val;
            return this;
        }

        public Builder doingActivities(String val) {
            doingActivities = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder posts(String val) {
            posts = val;
            return this;
        }

        public Builder attentionId(String val) {
            attentionId = val;
            return this;
        }

        public Builder beattentionId(String val) {
            beattentionId = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
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
        dest.writeString(this.collectForum);
        dest.writeString(this.collectNews);
        dest.writeString(this.collectActivities);
        dest.writeString(this.doingActivities);
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
        this.six = in.readString();
        this.credit = in.readString();
        this.signature = in.readString();
        this.address = in.readString();
        this.name = in.readString();
        this.friends = in.readString();
        this.collectForum = in.readString();
        this.collectNews = in.readString();
        this.collectActivities = in.readString();
        this.doingActivities = in.readString();
        this.image = in.readString();
        this.password = in.readString();
        this.posts = in.readString();
        this.attentionId = in.readString();
        this.beattentionId = in.readString();
    }

    @Generated(hash = 880417976)
    public User(String id, String praise_num, String praise_id, String six, String credit,
            String signature, String address, String name, String friends,
            String collectForum, String collectNews, String collectActivities,
            String doingActivities, String image, String password, String posts,
            String attentionId, String beattentionId) {
        this.id = id;
        this.praise_num = praise_num;
        this.praise_id = praise_id;
        this.six = six;
        this.credit = credit;
        this.signature = signature;
        this.address = address;
        this.name = name;
        this.friends = friends;
        this.collectForum = collectForum;
        this.collectNews = collectNews;
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
