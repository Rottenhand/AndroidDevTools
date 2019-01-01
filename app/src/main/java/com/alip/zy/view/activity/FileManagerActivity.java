package com.alip.zy.view.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alip.zy.tools.R;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by ZY on 2018/2/9.
 */
public class FileManagerActivity extends BaseImmersiveActivity{

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
    }

    private void dsds() {
        // check whether has specific permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {

            // has permission or no need permission

        } else {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


    }

    // result callback
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    /**
     * get current task top app package name
     * @return the top app package name
     */
    private String getTaskTopAppPackageName() {
        // request the permission to get top task package name
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !hasGetUsageStatsPermission()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return "";
        }

        String packageName = "";
        ActivityManager activityManager = (ActivityManager) (getSystemService(Context.ACTIVITY_SERVICE));
        // if the sdk > 20. It can only use getRunningAppProcesses to get task top package name
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usage = (UsageStatsManager)getSystemService(USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List<UsageStats> stats = usage.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10, time);
            if (stats != null) {
                SortedMap<Long, UsageStats> runningTask = new TreeMap<Long,UsageStats>();
                for (UsageStats usageStats : stats) {
                    runningTask.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (runningTask.isEmpty()) {
                    return null;
                }
                packageName =  runningTask.get(runningTask.lastKey()).getPackageName();
            }
        } else {// if sdk <= 20, can use getRunningTasks
//           <uses-permission android:name="android.permission.PACKAGE_USAGE_STATS" tools:ignore="ProtectedPermissions" />
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
            packageName = runningTaskInfos.get(0).topActivity.getPackageName();
            if (runningTaskInfos != null) {
                ComponentName f = runningTaskInfos.get(0).topActivity;
                String topActivityClassName = f.getClassName();
            }

        }
        return packageName;
    }

    //判断应用是否在前端运行
    public boolean isRunningForeground(Context context) {
//        String packageName = context.getPackageName();
//        String topActivityClassName = getTopActivityName(context);
//        if (packageName != null && topActivityClassName != null && topActivityClassName.startsWith(packageName)) {
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }
    /**
     * check whether has permission to get usage stats
     * @return true if have, false otherwise
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean hasGetUsageStatsPermission() {
        AppOpsManager appOps = (AppOpsManager)getSystemService(Context.APP_OPS_SERVICE);
        int mode = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(), getPackageName());
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }

}
