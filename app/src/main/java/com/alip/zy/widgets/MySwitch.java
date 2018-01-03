package com.alip.zy.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

import com.alip.zy.tools.R;

/**
 * Created by ZY on 2016/12/21.
 */
public class MySwitch extends Switch{
    public MySwitch(Context context) {
        super(context);
        init();
    }

    public MySwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        super.setThumbResource(R.drawable.test_switch_thumb);
        super.setTrackResource(R.drawable.switch_track);
        super.setSwitchMinWidth(240);

    }


}
