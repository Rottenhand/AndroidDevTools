package com.alip.zy.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alip.zy.tools.R;
import com.alip.zy.view.activity.CheckBuildActivity;
import com.alip.zy.view.activity.FileManagerActivity;

/**
 * 我的
 *
 * Created by ZY on 2018/1/2.
 */
public class HomeProfileView extends FrameLayout {

    private Button mBtnMyFiles, mBtnSetting, mBtnBuild;

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

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fragment_home_profile, this, true);
        mBtnMyFiles = findViewById(R.id.btn_profile_my_files);
        mBtnSetting = findViewById(R.id.btn_to_setting);
        mBtnBuild = findViewById(R.id.btn_check_build_info);
        initListener();
    }

    private void initListener() {
        mBtnMyFiles.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FileManagerActivity.class);
                getContext().startActivity(intent);
            }
        });

        mBtnSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS); // APP设置
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION); // 显示在其他应用上层
//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

                Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                intent.setData(uri);
                getContext().startActivity(intent);
            }
        });

        mBtnBuild.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckBuildActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
