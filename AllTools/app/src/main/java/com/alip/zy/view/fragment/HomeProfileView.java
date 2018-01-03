package com.alip.zy.view.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.alip.zy.tools.R;

/**
 * 我的
 *
 * Created by ZY on 2018/1/2.
 */
public class HomeProfileView extends FrameLayout {
    public HomeProfileView(Context context) {
        super(context);
        initViews(context);
    }

    public HomeProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public HomeProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public HomeProfileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fragment_home_profile, this, true);
    }
}
