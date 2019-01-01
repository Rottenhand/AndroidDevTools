package com.alip.zy.view.activity;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.material.snackbar.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alip.zy.tools.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckBuildActivity extends BaseImmersiveActivity {

    private ListView listBuildInfo;

    private List<Map.Entry<String, String>> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_build);
        listBuildInfo = findViewById(R.id.list_build_info);
        checkBuild();

        listBuildInfo.setAdapter(new BuildInfoAdapter());



        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(listBuildInfo, isNotificationEnable(CheckBuildActivity.this) ? "已允许通知" : "请开启通知", Snackbar.LENGTH_LONG).show();
            }
        }, 2000);
    }

    private void checkBuild() {
        LinkedHashMap<String, String> buildInfo;
        buildInfo = new LinkedHashMap<>();
        buildInfo.put("ID", Build.ID);
        buildInfo.put("DISPLAY", Build.DISPLAY);
        buildInfo.put("PRODUCT", Build.PRODUCT);
        buildInfo.put("DEVICE", Build.DEVICE);
        buildInfo.put("BOARD", Build.BOARD);
        buildInfo.put("CPU_ABI", Build.CPU_ABI);
        buildInfo.put("CPU_ABI2", Build.CPU_ABI2);
        buildInfo.put("MANUFACTURER", Build.MANUFACTURER);
        buildInfo.put("BRAND", Build.BRAND);
        buildInfo.put("MODEL", Build.MODEL);
        buildInfo.put("BOOTLOADER", Build.BOOTLOADER);
        buildInfo.put("RADIO", Build.RADIO);
        buildInfo.put("HARDWARE", Build.HARDWARE);
        buildInfo.put("SERIAL", Build.SERIAL);
//        buildInfo.put("getSerial", Build.getSerial());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buildInfo.put("SUPPORTED_ABIS", getABIString(Build.SUPPORTED_ABIS));
            buildInfo.put("SUPPORTED_32_BIT_ABIS", getABIString(Build.SUPPORTED_32_BIT_ABIS));
            buildInfo.put("SUPPORTED_64_BIT_ABIS", getABIString(Build.SUPPORTED_64_BIT_ABIS));
        }
        buildInfo.put("VERSION Start ---", "");
        buildInfo.put("INCREMENTAL", Build.VERSION.INCREMENTAL);
        buildInfo.put("RELEASE", Build.VERSION.RELEASE);
        buildInfo.put("BASE_OS", Build.VERSION.BASE_OS);
        buildInfo.put("SECURITY_PATCH", Build.VERSION.SECURITY_PATCH);
        buildInfo.put("SDK", Build.VERSION.SDK);
        buildInfo.put("SDK_INT", Build.VERSION.SDK_INT + "");
        buildInfo.put("PREVIEW_SDK_INT", Build.VERSION.PREVIEW_SDK_INT + "");
        buildInfo.put("CODENAME", Build.VERSION.CODENAME);
        buildInfo.put("VERSION End ---", "");
        buildInfo.put("TYPE", Build.TYPE);
        buildInfo.put("TAGS", Build.TAGS);
        buildInfo.put("FINGERPRINT", Build.FINGERPRINT);
        buildInfo.put("TIME", Build.TIME + "");
        buildInfo.put("USER", Build.USER);
        buildInfo.put("HOST", Build.HOST);

        infoList = new ArrayList<>(buildInfo.entrySet());
    }

    private String getABIString(String[] ABI) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ABI.length; i++) {
            sb.append(ABI[i]);
            if (i != ABI.length - 1) {
                sb.append(", \n");
            }
        }
        return sb.toString();
    }

    private class BuildInfoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return infoList.size();
        }

        @Override
        public Map.Entry<String, String> getItem(int position) {
            return infoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Map.Entry<String, String> entry = getItem(position);

            BuildInfoViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(CheckBuildActivity.this).inflate(R.layout.layout_build_info_item, parent, false);
                holder = new BuildInfoViewHolder();
                holder.mTvItemName = convertView.findViewById(R.id.tv_build_info_name);
                holder.mTvItemValue = convertView.findViewById(R.id.tv_build_info_value);
                convertView.setTag(holder);
            } else {
                holder = (BuildInfoViewHolder) convertView.getTag();
            }
            if (holder == null) {
                holder = new BuildInfoViewHolder();
                holder.mTvItemName = convertView.findViewById(R.id.tv_build_info_name);
                holder.mTvItemValue = convertView.findViewById(R.id.tv_build_info_value);
            }
            holder.mTvItemName.setText(entry.getKey());
            holder.mTvItemValue.setText(entry.getValue());
            return convertView;
        }


        private class BuildInfoViewHolder {
            public TextView mTvItemName;
            public TextView mTvItemValue;
        }

    }

    /*
     * 判断通知权限是否打开
     * 4.3下没有关闭通知功能
     */
    private boolean isNotificationEnable(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            return notificationManager.areNotificationsEnabled();

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();

            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;

            Class appOpsClass = null; /* Context.APP_OPS_MANAGER */

            try {
                appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);

                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (int) opPostNotificationValue.get(Integer.class);
                return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        } else {
            return true;
        }
    }
}
