package com.example.administrator.myapplication.been;

/**
 * Created by Administrator on 2017/9/17 0017.
 */

public class Activity {
    int id,user_num,praise_num;
    String type,organizes,time,address,image,remark,participatorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_num() {
        return user_num;
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }

    public int getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(int praise_num) {
        this.praise_num = praise_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganizes() {
        return organizes;
    }

    public void setOrganizes(String organizes) {
        this.organizes = organizes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParticipatorId() {
        return participatorId;
    }

    public void setParticipatorId(String participatorId) {
        this.participatorId = participatorId;
    }

    public Activity(int id, int user_num, int praise_num, String type, String organizes, String time, String address, String image, String remark, String participatorId) {
        this.id = id;
        this.user_num = user_num;
        this.praise_num = praise_num;
        this.type = type;
        this.organizes = organizes;
        this.time = time;
        this.address = address;
        this.image = image;
        this.remark = remark;
        this.participatorId = participatorId;
    }

    public Activity() {
    }
}
