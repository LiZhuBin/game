package com.example.administrator.myapplication.adapter;


import android.support.v7.widget.CardView;

public interface CardAdapter {
    CardView getCardViewAt(int position);

    int getCount();

    int getMaxElevationFactor();

    void setMaxElevationFactor(int MaxElevationFactor);
}