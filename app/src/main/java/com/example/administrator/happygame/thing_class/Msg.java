package com.example.administrator.happygame.thing_class;

import java.io.Serializable;

/**
 * Created by 10619 on 2017/9/21.
 */

public class Msg implements Serializable {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    private String content;
    private int type;
    private String imageUrl;

    public Msg(String imageUrl, String content) {
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public static int getTypeReceived() {
        return TYPE_RECEIVED;
    }

    public static int getTypeSend() {
        return TYPE_SEND;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
