package com.example.administrator.happygame.thing_class;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class Images implements Serializable {

    private String path;
    private long time;
    private String name;

    public Images(String path, long time, String name) {
        this.path = path;
        this.time = time;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}