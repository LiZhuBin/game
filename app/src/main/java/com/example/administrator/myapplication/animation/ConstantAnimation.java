package com.example.administrator.myapplication.animation;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2017/8/28 0028.
 */

public class ConstantAnimation {
    public static  Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                                Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF,
                   -1.0f,Animation.RELATIVE_TO_SELF, 0.0f);

}
