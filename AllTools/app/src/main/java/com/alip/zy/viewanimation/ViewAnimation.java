package com.alip.zy.viewanimation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.alip.zy.tools.R;
import com.alip.zy.view.activity.BaseImmersiveActivity;

public class ViewAnimation extends BaseImmersiveActivity {

    private CustomAnim customAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        customAnim = new CustomAnim();
        customAnim.setDuration(2000);

    }

    public void btnAnimate (View view){
        view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btnanimate));
    }

    public void btnRotate (View view){
        view.startAnimation(AnimationUtils.loadAnimation(ViewAnimation.this, R.anim.btnroate));
    }

    public void btnTranslate(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(ViewAnimation.this, R.anim.btntranslate));
    }

    public void btnScale(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(ViewAnimation.this, R.anim.btnsacle));
    }

    public void btnMixAnim(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(ViewAnimation.this, R.anim.btnmixanim));
    }

    public void btnShake(View view) {
        view.startAnimation(customAnim);
    }
}
