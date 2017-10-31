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
public class Forum implements Parcelable {

    public static final Creator<Forum> CREATOR = new Creator<Forum>() {
        @Override
        public Forum createFromParcel(Parcel source) {
            return new Forum(source);
        }

        @Override
        public Forum[] newArray(int size) {
            return new Forum[size];
        }
    };
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

    public Forum() {
    }

    protected Forum(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.userId = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.comment = in.readString();
        this.image = in.readString();
        this.like = in.readString();
        this.data = in.readString();
    }

    @Generated(hash = 1182769316)
    public Forum(String id, String type, String userId, String title, String content,
                 String comment, String image, String like, String data) {
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

    private Forum(Builder builder) {
        setId(builder.id);
        setType(builder.type);
        setUserId(builder.userId);
        setTitle(builder.title);
        setContent(builder.content);
        setComment(builder.comment);
        setImage(builder.image);
        setLike(builder.like);
        setData(builder.data);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.userId);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.comment);
        dest.writeString(this.image);
        dest.writeString(this.like);
        dest.writeString(this.data);
    }

    public static final class Builder {
        private String id;
        private String type;
        private String userId;
        private String title;
        private String content;
        private String comment;
        private String image;
        private String like;
        private String data;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder comment(String val) {
            comment = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder like(String val) {
            like = val;
            return this;
        }

        public Builder data(String val) {
            data = val;
            return this;
        }

        public Forum build() {
            return new Forum(this);
        }
    }
}
