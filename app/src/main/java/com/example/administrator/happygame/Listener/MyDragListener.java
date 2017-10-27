package com.example.administrator.happygame.Listener;

import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;



/**
 * @author Administrator
 */
public class MyDragListener implements BaiduMap.OnMarkerDragListener {

    private Context MyContext;

    public MyDragListener(Context context) {
        MyContext = context;
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(MyContext, "End__Position:" + "X" + marker.getPosition() + " Y:" + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(MyContext, "StartPosition:" + "X" + marker.getPosition() + " Y:" + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
    }

}
