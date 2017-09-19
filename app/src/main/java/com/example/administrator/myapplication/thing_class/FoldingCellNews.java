package com.example.administrator.myapplication.thing_class;

/**
 * Created by Administrator on 2017/9/3 0003.
 */

public class FoldingCellNews {
        String userName,title,content,time,address;
    int userId,image;
    int []addImages;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int[] getAddImages() {
        return addImages;
    }

    public void setAddImages(int[] addImages) {
        this.addImages = addImages;
    }

    public FoldingCellNews(String userName, String title, String content, String time, String address, int userId, int image, int[] addImages) {
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.time = time;
        this.address = address;
        this.userId = userId;
        this.image = image;
        this.addImages = addImages;
    }
}
