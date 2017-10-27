package com.example.administrator.happygame;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionResult;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/10/20.
 */

 /*
 * 该类为内容储存类，对象类
 * 放的是 推荐点的名称,和UID
 * */

public class SuggestionPoi implements Serializable {  //实现序列化接口！！
    /**/

    private String poi_Uid;
    private String Key;
    private SuggestionResult.SuggestionInfo info;
    private String address;
    private LatLng ll;

    public SuggestionPoi(String key, String address, LatLng latLng) {
        this.Key = key;
        this.address = address;
        ll = latLng;
    }

    public SuggestionPoi(String key, SuggestionResult.SuggestionInfo info) {
        this.Key = key;
        this.info = info;
    }

    public LatLng getLl() {
        return ll;
    }

    public void setLl(LatLng ll) {
        this.ll = ll;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SuggestionResult.SuggestionInfo getInfo() {
        return info;
    }

    public void setInfo(SuggestionResult.SuggestionInfo info) {
        this.info = info;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getPoi_Uid() {
        return poi_Uid;
    }

    public void setPoi_Uid(String poi_Uid) {
        this.poi_Uid = poi_Uid;
    }
}
