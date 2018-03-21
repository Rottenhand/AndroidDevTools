package com.alip.zy.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alip.zy.layoutanimation.LayoutAnimation;
import com.alip.zy.tabTest.TabLayoutActivity;
import com.alip.zy.textpackage.PaoMaDeng;
import com.alip.zy.tools.FilesTestActivity;
import com.alip.zy.tools.NotificationActivity;
import com.alip.zy.tools.R;
import com.alip.zy.tools.ScreenSize;
import com.alip.zy.view.activity.JSONToolsActivity;
import com.alip.zy.viewanimation.ViewAnimation;

/**
 * 工具Tab
 *
 * Created by ZY on 2017/12/6.
 */
public class HomeToolsView extends FrameLayout{

    private Button mBtnScreenSize, mBtnAnimation, mBtnLayoutAnimation, mPaoMaDeng, mFilesDir, mParseColor, mNotification, mTabLayout, mBtnJson;
    private EditText colorInt;

    public HomeToolsView(Context context) {
        super(context);
        initViews(context);
    }

    public HomeToolsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public HomeToolsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fragment_home_tools, this, true);
        colorInt = findViewById(R.id.showColorInt);

        mBtnScreenSize = findViewById(R.id.btnScreenSize);
        mBtnAnimation = findViewById(R.id.btnViewAnimation);
        mBtnLayoutAnimation = findViewById(R.id.btnLayoutAnimation);
        mPaoMaDeng = findViewById(R.id.paoMaDeng);
        mFilesDir = findViewById(R.id.files_Dir);
        mParseColor = findViewById(R.id.parse_color);
        mNotification = findViewById(R.id.btn_notification);
        mTabLayout = findViewById(R.id.btn_tab_layout);
        mBtnJson = findViewById(R.id.btn_json_tool);

        initViewListener();

    }

    private void initViewListener() {
        mBtnScreenSize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScreenSize.class);
                getContext().startActivity(intent);
            }
        });
        mBtnAnimation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ViewAnimation.class);
                getContext().startActivity(intent);
            }
        });
        mBtnLayoutAnimation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LayoutAnimation.class);
                getContext().startActivity(intent);
            }
        });
        mPaoMaDeng.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PaoMaDeng.class);
                getContext().startActivity(intent);
            }
        });
        mFilesDir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FilesTestActivity.class);
                getContext().startActivity(intent);
            }
        });
        mParseColor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                parseColor();
            }
        });
        mNotification.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                getContext().startActivity(intent);
            }
        });
        mTabLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TabLayoutActivity.class);
                getContext().startActivity(intent);
            }
        });
        mBtnJson.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), JSONToolsActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    private void parseColor() {
//        int colorInt =  Color.parseColor(STATUSBAR_TRANSPARENT);

        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        Toast.makeText(getContext(), height + "", Toast.LENGTH_LONG).show();

        String colorStringTemp = colorInt.getText() + "";
        if (TextUtils.isEmpty(colorStringTemp)){
            return;
        }
        String colorString = Color.parseColor(colorStringTemp) + "";
        colorInt.setTextColor(Color.parseColor(colorStringTemp));
        colorInt.setText(colorString);
    }

}
