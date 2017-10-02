package com.example.administrator.myapplication.thing_class;

/**
 * Created by 10619 on 2017/9/21.
 */

public class Msg {

    public static final int TYPE_RECEIVED=0;
    public static final int TYPE_SEND=1;

    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
