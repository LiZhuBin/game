package com.example.administrator.happygame.been;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2017/9/6.
 */

public class News implements Parcelable {


    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
    private String new_id;
    private String new_title;
    private String new_content;
    private String new_image;
    private String new_comment_id;
    private String new_comment;
    private int new_drawable;

    public News(int new_drawable, String new_title, String new_content) {
        this.new_title = new_title;
        this.new_content = new_content;
        this.new_drawable = new_drawable;
    }

    public News(String new_id, String new_title, String new_content, String new_image) {
        this.new_id = new_id;
        this.new_title = new_title;
        this.new_content = new_content;
        this.new_image = new_image;
    }

    public News(String new_id, String new_title, String new_content, String new_image, String new_comment_id, String new_comment) {
        this.new_id = new_id;
        this.new_title = new_title;
        this.new_content = new_content;
        this.new_image = new_image;
        this.new_comment_id = new_comment_id;
        this.new_comment = new_comment;
    }

    protected News(Parcel in) {
        this.new_id = in.readString();
        this.new_title = in.readString();
        this.new_content = in.readString();
        this.new_image = in.readString();
        this.new_comment_id = in.readString();
        this.new_comment = in.readString();
        this.new_drawable = in.readInt();
    }

    public int getNew_drawable() {
        return new_drawable;
    }

    public void setNew_drawable(int new_drawable) {
        this.new_drawable = new_drawable;
    }

    public String getNew_id() {
        return new_id;
    }

    public void setNew_id(String new_id) {
        this.new_id = new_id;
    }

    public String getNew_title() {
        return new_title;
    }

    public void setNew_title(String new_title) {
        this.new_title = new_title;
    }

    public String getNew_content() {
        return new_content;
    }

    public void setNew_content(String new_content) {
        this.new_content = new_content;
    }

    public String getNew_image() {
        return new_image;
    }

    public void setNew_image(String new_image) {
        this.new_image = new_image;
    }

    public String getNew_comment_id() {
        return new_comment_id;
    }

    public void setNew_comment_id(String new_comment_id) {
        this.new_comment_id = new_comment_id;
    }

    public String getNew_comment() {
        return new_comment;
    }

    public void setNew_comment(String new_comment) {
        this.new_comment = new_comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.new_id);
        dest.writeString(this.new_title);
        dest.writeString(this.new_content);
        dest.writeString(this.new_image);
        dest.writeString(this.new_comment_id);
        dest.writeString(this.new_comment);
        dest.writeInt(this.new_drawable);
    }

    /**
     * new_id : 1
     * new_title : 王者荣耀新英雄女娲，逆天技能成就最强法师！
     * new_content : 女娲是很久之前就开始制作的一个英雄了。不过多次上下体验服，更有传闻女娲不会上线正式服了。不过，随着最近一次体验服的更新，女娲又一次出现在了体验服，而且还有技能展示哦.其实在背景故事中，女娲就已经存在很久了。所以，这个英雄的上线是迟早的问题。具体技能介绍王者解说就不在叙述了，小伙伴们可以自己看下图片哦。不过王者解说还是要说句，目前体验服的女娲绝对是几大英雄的结合体。女娲一共三个主动技能，并非之前传说的有四个技能。而这三个主动技能，分别是墨子的一技能，周瑜的一技能，黄忠的大招。
     * new_image : new/post/post1.jpg
     * new_comment_id : 2
     * new_comment : 111
     */
    public enum NewType {

        LangRenSha(0), SanGuoSha(1), YouXiWang(2);

        private int mCode;

        private NewType(int Code) {
            mCode = Code;
        }


        static private int getListSize() {
            return 3;
        }


        @Override
        public String toString() {
            return super.toString();
        }
    }
}