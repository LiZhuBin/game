package com.example.administrator.happygame.util;

import android.app.Activity;


/**
 * Created by lenovo on 2017/10/5.
 */

public class Until {

    static public Object Parse_Advertisement(com.example.administrator.happygame.thing_class.Advertisement advertisement) {
        if (advertisement.getImage_ResourceId() != 0) {
            return advertisement.getImage_ResourceId();
        } else if (advertisement.getImage_url() != null) {
            return advertisement.getImage_url();
        }
        return null;
    }

    static public int dp2px(Activity activity, float dpValue) {
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
