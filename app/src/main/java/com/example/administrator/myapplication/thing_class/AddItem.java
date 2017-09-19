package com.example.administrator.myapplication.thing_class;

import android.view.View;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class AddItem {
    String inputTime,address,uerName,activityTime,title,content;
    int userImage;
    int []addImages;
    private View.OnClickListener requestBtnClickListener;

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }
    public AddItem() {
    }
    public AddItem(String inputTime, String address, String uerName, String activityTime, String title, String content, int userImage, int[] addImages) {
        this.inputTime = inputTime;
        this.address = address;
        this.uerName = uerName;
        this.activityTime = activityTime;
        this.title = title;
        this.content = content;
        this.userImage = userImage;
        this.addImages = addImages;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUerName() {
        return uerName;
    }

    public void setUerName(String uerName) {
        this.uerName = uerName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
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

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public int[] getAddImages() {
        return addImages;
    }

    public void setAddImages(int[] addImages) {
        this.addImages = addImages;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddItem item = (AddItem) o;
//        String inputTime,address,uerName,activityTime,title,content;
//        int userImage;
//        int []addImages;
        if (inputTime != item.inputTime) return false;
        if (address != null ? !address.equals(item.address) : item.address != null) return false;
        if (uerName != null ? !uerName.equals(item.uerName) : item.uerName != null)
            return false;
        if (activityTime != null ? !activityTime.equals(item.activityTime) : item.activityTime != null)
            return false;
        if (title != null ? !title.equals(item.title) : item.title != null)
            return false;
        if (content != null ? !content.equals(item.content) : item.content != null) return false;
        return false;

    }
    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<AddItem> getTestingList() {
        ArrayList<AddItem> items = new ArrayList<>();
        items.add(new AddItem("2分钟","地点1","人1","明天","一起玩","我需要3人", R.drawable.image_scrolling_head,getInitImageId(R.drawable.image_scrolling_head)));
        items.add(new AddItem("3分钟","地点2","人1","明天","一起玩","我需要3人", R.drawable.image_scrolling_head,getInitImageId(R.drawable.image_scrolling_head)));
        items.add(new AddItem("4分钟","地点3","人1","明天","一起玩","我需要3人", R.drawable.image_scrolling_head,getInitImageId(R.drawable.image_scrolling_head)));
        items.add(new AddItem("5分钟","地点4","人1","明天","一起玩","我需要3人", R.drawable.image_scrolling_head,getInitImageId(R.drawable.image_scrolling_head)));
        return items;

    }
    public static int[] getInitImageId(int userImage){
        int[] addImageId={userImage};
        return addImageId;
    }
}
