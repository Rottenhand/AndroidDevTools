package com.alip.zy.viewanimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zy on 15/12/27.
 */
public class CustomAnim extends Animation {

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

//        t.setAlpha(interpolatedTime);

        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime*20)*30), 0);

        super.applyTransformation(interpolatedTime, t);
    }
}
