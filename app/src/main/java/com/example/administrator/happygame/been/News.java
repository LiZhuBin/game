package com.example.administrator.happygame.been;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2017/9/6.
 */
@Entity
public class News implements Parcelable {
    /**
     * new_id : 1
     * new_build_time : 0000-00-00
     * new_like_num : 0
     * new_praise_like : 0
     * new_praise_unlike : 0
     * new_title : 王者荣耀新英雄女娲，逆天技能成就最强法师！
     * new_content : 女娲是很久之前就开始制作的一个英雄了。不过多次上下体验服，更有传闻女娲不会上线正式服了。不过，随着最近一次体验服的更新，女娲又一次出现在了体验服，而且还有技能展示哦.其实在背景故事中，女娲就已经存在很久了。所以，这个英雄的上线是迟早的问题。具体技能介绍王者解说就不在叙述了，小伙伴们可以自己看下图片哦。不过王者解说还是要说句，目前体验服的女娲绝对是几大英雄的结合体。女娲一共三个主动技能，并非之前传说的有四个技能。而这三个主动技能，分别是墨子的一技能，周瑜的一技能，黄忠的大招。
     * new_image : new/new1.jpg
     * new_comment_id : 2
     * new_comment : 111
     */
@Id
    private String new_id;
    private String new_build_time;
    private String new_like_num;
    private String new_praise_like;
    private String new_praise_unlike;
    private String new_title;
    private String new_content;
    private String new_image;
    private String new_comment_id;
    private String new_comment;

    private News(Builder builder) {
        setNew_id(builder.new_id);
        setNew_build_time(builder.new_build_time);
        setNew_like_num(builder.new_like_num);
        setNew_praise_like(builder.new_praise_like);
        setNew_praise_unlike(builder.new_praise_unlike);
        setNew_title(builder.new_title);
        setNew_content(builder.new_content);
        setNew_image(builder.new_image);
        setNew_comment_id(builder.new_comment_id);
        setNew_comment(builder.new_comment);
    }

    public void setNew_id(String new_id) {
        this.new_id = new_id;
    }

    public void setNew_build_time(String new_build_time) {
        this.new_build_time = new_build_time;
    }

    public void setNew_like_num(String new_like_num) {
        this.new_like_num = new_like_num;
    }

    public void setNew_praise_like(String new_praise_like) {
        this.new_praise_like = new_praise_like;
    }

    public void setNew_praise_unlike(String new_praise_unlike) {
        this.new_praise_unlike = new_praise_unlike;
    }

    public void setNew_title(String new_title) {
        this.new_title = new_title;
    }

    public void setNew_content(String new_content) {
        this.new_content = new_content;
    }

    public void setNew_image(String new_image) {
        this.new_image = new_image;
    }

    public void setNew_comment_id(String new_comment_id) {
        this.new_comment_id = new_comment_id;
    }

    public void setNew_comment(String new_comment) {
        this.new_comment = new_comment;
    }

    public String getNew_id() {
        return new_id;
    }

    public String getNew_build_time() {
        return new_build_time;
    }

    public String getNew_like_num() {
        return new_like_num;
    }

    public String getNew_praise_like() {
        return new_praise_like;
    }

    public String getNew_praise_unlike() {
        return new_praise_unlike;
    }

    public String getNew_title() {
        return new_title;
    }

    public String getNew_content() {
        return new_content;
    }

    public String getNew_image() {
        return new_image;
    }

    public String getNew_comment_id() {
        return new_comment_id;
    }

    public String getNew_comment() {
        return new_comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.new_id);
        dest.writeString(this.new_build_time);
        dest.writeString(this.new_like_num);
        dest.writeString(this.new_praise_like);
        dest.writeString(this.new_praise_unlike);
        dest.writeString(this.new_title);
        dest.writeString(this.new_content);
        dest.writeString(this.new_image);
        dest.writeString(this.new_comment_id);
        dest.writeString(this.new_comment);
    }

    public News() {
    }

    protected News(Parcel in) {
        this.new_id = in.readString();
        this.new_build_time = in.readString();
        this.new_like_num = in.readString();
        this.new_praise_like = in.readString();
        this.new_praise_unlike = in.readString();
        this.new_title = in.readString();
        this.new_content = in.readString();
        this.new_image = in.readString();
        this.new_comment_id = in.readString();
        this.new_comment = in.readString();
    }

    @Generated(hash = 1221955450)
    public News(String new_id, String new_build_time, String new_like_num, String new_praise_like, String new_praise_unlike, String new_title, String new_content, String new_image, String new_comment_id, String new_comment) {
        this.new_id = new_id;
        this.new_build_time = new_build_time;
        this.new_like_num = new_like_num;
        this.new_praise_like = new_praise_like;
        this.new_praise_unlike = new_praise_unlike;
        this.new_title = new_title;
        this.new_content = new_content;
        this.new_image = new_image;
        this.new_comment_id = new_comment_id;
        this.new_comment = new_comment;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public static final class Builder {
        private String new_id;
        private String new_build_time;
        private String new_like_num;
        private String new_praise_like;
        private String new_praise_unlike;
        private String new_title;
        private String new_content;
        private String new_image;
        private String new_comment_id;
        private String new_comment;

        public Builder() {
        }

        public Builder new_id(String val) {
            new_id = val;
            return this;
        }

        public Builder new_build_time(String val) {
            new_build_time = val;
            return this;
        }

        public Builder new_like_num(String val) {
            new_like_num = val;
            return this;
        }

        public Builder new_praise_like(String val) {
            new_praise_like = val;
            return this;
        }

        public Builder new_praise_unlike(String val) {
            new_praise_unlike = val;
            return this;
        }

        public Builder new_title(String val) {
            new_title = val;
            return this;
        }

        public Builder new_content(String val) {
            new_content = val;
            return this;
        }

        public Builder new_image(String val) {
            new_image = val;
            return this;
        }

        public Builder new_comment_id(String val) {
            new_comment_id = val;
            return this;
        }

        public Builder new_comment(String val) {
            new_comment = val;
            return this;
        }

        public News build() {
            return new News(this);
        }
    }
}