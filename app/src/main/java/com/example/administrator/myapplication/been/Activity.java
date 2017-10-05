package com.example.administrator.myapplication.been;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/17 0017.
 */

public class Activity implements Serializable {
    /**
     * id : 1
     * user_num : 0000000005
     * build_data:
     * praise_num : 3
     * type : 狼人杀
     * organizes :  李主彬
     * time :  五月12号
     * address :  五邑大学第三房学堂
     * image :  b.jpg
     * remark : 需要会玩的
     * participatorId : 1
     * title
     * add_id
     *comment 1|大家一起玩嘛|2|是啊|3|加我一个|是12号吗
     */

    private String id;
    private String user_num;
    private String praise_num;
    private String type;
    private String organizes;
    private String time;
    private String address;
    private String image;
    private String remark;
    private String participatorId;
    private String title;
    private  String add_id;
private String build_data;
    private String comment;

    public String getBuild_data() {
        return build_data;
    }

    public void setBuild_data(String build_data) {
        this.build_data = build_data;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdd_id() {
        return add_id;
    }

    public void setAdd_id(String add_id) {
        this.add_id = add_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Activity(String id, String user_num, String praise_num, String type, String organizes, String time, String address, String image, String remark, String participatorId, String title,String add_id) {
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
        this.title = title;
        this.add_id=add_id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setOrganizes(String organizes) {
        this.organizes = organizes;
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

    public String getId() {
        return id;
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

    public String getOrganizes() {
        return organizes;
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
}
