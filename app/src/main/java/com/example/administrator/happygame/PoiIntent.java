package com.example.administrator.happygame;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/10/22.
 */

public class PoiIntent implements Serializable {

    private double longt;
    private double latt;
    private String name;
    private String address;

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public double getLatt() {
        return latt;
    }

    public void setLatt(double latt) {
        this.latt = latt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
