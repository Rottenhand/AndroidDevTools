package com.alip.zy.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alip.zy.tools.R;
import com.alip.zy.view.activity.BaseImmersiveActivity;
import com.alip.zy.view.activity.CheckBuildActivity;
import com.alip.zy.view.activity.FileManagerActivity;
import com.alip.zy.view.activity.RecyclerViewActivity;
import com.alip.zy.view.fragment.iview.BaseViewFragment;

/**
 * 我的
 *
 * Created by ZY on 2018/1/2.
 */
public class HomeProfileView extends BaseViewFragment {

    private static final int REQUEST_CODE_LOCATION_SETTINGS = 2;

    private Button mBtnMyFiles, mBtnSetting, mBtnBuild, mRecycler;
    private BaseImmersiveActivity activity;

    public HomeProfileView(Context context) {
        super(context);
        activity = (BaseImmersiveActivity) context;
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

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fragment_home_profile, this, true);
        mBtnMyFiles = findViewById(R.id.btn_profile_my_files);
        mBtnSetting = findViewById(R.id.btn_to_setting);
        mBtnBuild = findViewById(R.id.btn_check_build_info);
        mRecycler = findViewById(R.id.btn_test_recycler_view);
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
                setLocationService();
                if (TextUtils.isEmpty("")) return;

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
//                Intent intent = new Intent(getContext(), TransProgressActivity.class);
                getContext().startActivity(intent);
            }
        });

        mRecycler.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RecyclerViewActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    private void setLocationService() {
        Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(locationIntent, REQUEST_CODE_LOCATION_SETTINGS);
    }

    /**
     * Location service if enable
     *
     * @param context
     * @return location is enable if return true, otherwise disable.
     */
    private boolean isLocationEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean networkProvider = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean gpsProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (networkProvider || gpsProvider) return true;
        return false;
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Permission is not granted
            // Should we show an explanation?
            if (activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                activity.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 2);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

}
