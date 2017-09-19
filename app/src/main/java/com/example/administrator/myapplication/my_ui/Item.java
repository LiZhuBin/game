package com.example.administrator.myapplication.my_ui;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/13 0013.
 */

public class Item {

    private String head_title;
    private String center_title;
    private String button01_title;


    private View.OnClickListener requestBtnClickListener;

    public Item(String head_title, String center_title, String button01_title) {
        this.head_title = head_title;
        this.center_title = center_title;
        this.button01_title = button01_title;
    }

    public String getHead_title() {
        return head_title;
    }

    public void setHead_title(String head_title) {
        this.head_title = head_title;
    }

    public String getCenter_title() {
        return center_title;
    }

    public void setCenter_title(String center_title) {
        this.center_title = center_title;
    }

    public String getButton01_title() {
        return button01_title;
    }

    public void setButton01_title(String button01_title) {
        this.button01_title = button01_title;
    }

    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("aaa","bbb","ccc"));
        items.add(new Item("aaa","bbb","ccc"));
        items.add(new Item("aaa","bbb","ccc"));
        items.add(new Item("aaa","bbb","ccc"));
        return items;

    }

}