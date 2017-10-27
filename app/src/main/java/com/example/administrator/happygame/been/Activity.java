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
public class Activity implements Parcelable {
    /**
     * id : 1
     * build_data : 2017-10-04
     * user_num : 5
     * praise_num : 1
     * type : 狼人杀
     * time : 2017-10-06
     * address :  五邑大学第三房学堂
     * image : activity/狼人杀/picture1.jpg
     * remark : 需要会玩的
     * participatorId : 1
     * title : 一起玩狼人杀啊
     * add_id : 1|2|3
     * comment : 1|大家一起玩嘛|2|是啊|3|加我一个|4|是12号吗
     */
@Id
    private String id;
    private String build_data;
    private String user_num;
    private String praise_num;
    private String type;
    private String time;
    private String address;
    private String image;
    private String remark;
    private String participatorId;
    private String title;
    private String add_id;
    private String comment;

    private Activity(Builder builder) {
        setId(builder.id);
        setBuild_data(builder.build_data);
        setUser_num(builder.user_num);
        setPraise_num(builder.praise_num);
        setType(builder.type);
        setTime(builder.time);
        setAddress(builder.address);
        setImage(builder.image);
        setRemark(builder.remark);
        setParticipatorId(builder.participatorId);
        setTitle(builder.title);
        setAdd_id(builder.add_id);
        setComment(builder.comment);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBuild_data(String build_data) {
        this.build_data = build_data;
    }

    public void setUser_num(String user_num) {
        this.user_num = user_num;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setParticipatorId(String participatorId) {
        this.participatorId = participatorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAdd_id(String add_id) {
        this.add_id = add_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getBuild_data() {
        return build_data;
    }

    public String getUser_num() {
        return user_num;
    }

    public String getPraise_num() {
        return praise_num;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getRemark() {
        return remark;
    }

    public String getParticipatorId() {
        return participatorId;
    }

    public String getTitle() {
        return title;
    }

    public String getAdd_id() {
        return add_id;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.build_data);
        dest.writeString(this.user_num);
        dest.writeString(this.praise_num);
        dest.writeString(this.type);
        dest.writeString(this.time);
        dest.writeString(this.address);
        dest.writeString(this.image);
        dest.writeString(this.remark);
        dest.writeString(this.participatorId);
        dest.writeString(this.title);
        dest.writeString(this.add_id);
        dest.writeString(this.comment);
    }

    public Activity() {
    }

    protected Activity(Parcel in) {
        this.id = in.readString();
        this.build_data = in.readString();
        this.user_num = in.readString();
        this.praise_num = in.readString();
        this.type = in.readString();
        this.time = in.readString();
        this.address = in.readString();
        this.image = in.readString();
        this.remark = in.readString();
        this.participatorId = in.readString();
        this.title = in.readString();
        this.add_id = in.readString();
        this.comment = in.readString();
    }

    @Generated(hash = 1876531887)
    public Activity(String id, String build_data, String user_num,
            String praise_num, String type, String time, String address,
            String image, String remark, String participatorId, String title,
            String add_id, String comment) {
        this.id = id;
        this.build_data = build_data;
        this.user_num = user_num;
        this.praise_num = praise_num;
        this.type = type;
        this.time = time;
        this.address = address;
        this.image = image;
        this.remark = remark;
        this.participatorId = participatorId;
        this.title = title;
        this.add_id = add_id;
        this.comment = comment;
    }

    public static final Creator<Activity> CREATOR = new Creator<Activity>() {
        public Activity createFromParcel(Parcel source) {
            return new Activity(source);
        }

        public Activity[] newArray(int size) {
            return new Activity[size];
        }
    };

    public static final class Builder {
        private String id;
        private String build_data;
        private String user_num;
        private String praise_num;
        private String type;
        private String time;
        private String address;
        private String image;
        private String remark;
        private String participatorId;
        private String title;
        private String add_id;
        private String comment;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder build_data(String val) {
            build_data = val;
            return this;
        }

        public Builder user_num(String val) {
            user_num = val;
            return this;
        }

        public Builder praise_num(String val) {
            praise_num = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder time(String val) {
            time = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder participatorId(String val) {
            participatorId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder add_id(String val) {
            add_id = val;
            return this;
        }

        public Builder comment(String val) {
            comment = val;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }
}
