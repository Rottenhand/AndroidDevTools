package com.alip.zy.tools;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.alip.zy.view.activity.BaseImmersiveActivity;

public class ScreenSize extends BaseImmersiveActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_size);

        textView = (TextView) findViewById(R.id.tvShowSize);
    }

    public void btnGetWindowSize(View view) {
        Point point = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(point);
        textView.setText(point.toString());
        //getrealsize

    }

    public void btnGetScreenSize2(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        textView.setText("width:" + displayMetrics.widthPixels+"  Height:"+displayMetrics.heightPixels);

    }

    public void btnGetScreenSize3(View view) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        textView.setText("width:" + displayMetrics.widthPixels+"  Height:"+displayMetrics.heightPixels);

    }

    public void btnGetScreenDensity(View view) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        textView.setText("Density: "+ displayMetrics.density+"  DPI: " + displayMetrics.densityDpi);
    }

    public void btnSwitchImmersive(View view) {
        Window window = getWindow();
        //add flags and change statusBar color to transparent
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(Color.BLACK);
//        window.setNavigationBarColor(Color.BLACK);
//        if (Build.VERSION.SDK_INT >= 26) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                    | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//        }
        window.getDecorView().setBackgroundColor(Color.CYAN);

        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


    }

//    private void getScreenSizeOfDevice2() {
//        Point point = new Point();
//        getWindowManager().getDefaultDisplay().getRealSize(point);
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        double x = Math.pow(point.x/ dm.xdpi, 2);
//        double y = Math.pow(point.y / dm.ydpi, 2);
//        double screenInches = Math.sqrt(x + y);
//        Log.d(TAG, "Screen inches : " + screenInches);
//    }


//    //pixel = dip*density;
//    private int convertDpToPixel(int dp) {
//        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
//        return (int)(dp*displayMetrics.density);
//    }
//
//    private int convertPixelToDp(int pixel) {
//        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
//        return (int)(pixel/displayMetrics.density);
//    }

}
