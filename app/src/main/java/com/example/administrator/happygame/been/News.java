package com.example.administrator.happygame.been;


import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lenovo on 2017/9/6.
 */
@Entity
public class News implements Parcelable ,SearchSuggestion {
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
    private int new_drawable;
    @Generated(hash = 1023926316)
    public News(String new_id, String new_build_time, String new_like_num,
            String new_praise_like, String new_praise_unlike, String new_title,
            String new_content, String new_image, String new_comment_id,
            String new_comment, int new_drawable) {
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
        this.new_drawable = new_drawable;
    }
    @Generated(hash = 1579685679)
    public News() {
    }

    private News(Builder builder) {
        setNew_id(builder.new_id);
        setNew_build_time(builder.new_build_time);
        setNew_like_num(builder.new_like_num);
        setNew_praise_like(builder.new_praise_like);
        setNew_praise_unlike(builder.new_praise_unlike);
        setNew_title(builder.new_title);
        setNew_content(builder.new_content);
        setNew_comment_id(builder.new_comment_id);
        setNew_comment(builder.new_comment);
        setNew_drawable(builder.new_drawable);
    }

    public String getNew_id() {
        return this.new_id;
    }
    public void setNew_id(String new_id) {
        this.new_id = new_id;
    }
    public String getNew_build_time() {
        return this.new_build_time;
    }
    public void setNew_build_time(String new_build_time) {
        this.new_build_time = new_build_time;
    }
    public String getNew_like_num() {
        return this.new_like_num;
    }
    public void setNew_like_num(String new_like_num) {
        this.new_like_num = new_like_num;
    }
    public String getNew_praise_like() {
        return this.new_praise_like;
    }
    public void setNew_praise_like(String new_praise_like) {
        this.new_praise_like = new_praise_like;
    }
    public String getNew_praise_unlike() {
        return this.new_praise_unlike;
    }
    public void setNew_praise_unlike(String new_praise_unlike) {
        this.new_praise_unlike = new_praise_unlike;
    }
    public String getNew_title() {
        return this.new_title;
    }
    public void setNew_title(String new_title) {
        this.new_title = new_title;
    }
    public String getNew_content() {
        return this.new_content;
    }
    public void setNew_content(String new_content) {
        this.new_content = new_content;
    }
    public String getNew_image() {
        return this.new_image;
    }
    public void setNew_image(String new_image) {
        this.new_image = new_image;
    }
    public String getNew_comment_id() {
        return this.new_comment_id;
    }
    public void setNew_comment_id(String new_comment_id) {
        this.new_comment_id = new_comment_id;
    }
    public String getNew_comment() {
        return this.new_comment;
    }
    public void setNew_comment(String new_comment) {
        this.new_comment = new_comment;
    }
    public int getNew_drawable() {
        return this.new_drawable;
    }
    public void setNew_drawable(int new_drawable) {
        this.new_drawable = new_drawable;
    }

    @Override
    public String getBody() {
        return new_title;
    }

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
        private int new_drawable;

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

        public Builder new_drawable(int val) {
            new_drawable = val;
            return this;
        }

        public News build() {
            return new News(this);
        }
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
        dest.writeInt(this.new_drawable);
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
        this.new_drawable = in.readInt();
    }

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
}