package com.alip.zy.view.fragment.iview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

public abstract class BaseViewFragment extends FrameLayout {

    public BaseViewFragment(Context context) {
        super(context);
    }

    public BaseViewFragment(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewFragment(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onDestroy();
}
