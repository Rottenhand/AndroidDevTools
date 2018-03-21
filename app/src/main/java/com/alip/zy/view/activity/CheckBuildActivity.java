package com.alip.zy.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alip.zy.tools.R;

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
        buildInfo.put("SUPPORTED_ABIS", getABIString(Build.SUPPORTED_ABIS));
        buildInfo.put("SUPPORTED_32_BIT_ABIS", getABIString(Build.SUPPORTED_32_BIT_ABIS));
        buildInfo.put("SUPPORTED_64_BIT_ABIS", getABIString(Build.SUPPORTED_64_BIT_ABIS));
        buildInfo.put("VERSION Start ---", "");
        buildInfo.put("INCREMENTAL", Build.VERSION.INCREMENTAL);
        buildInfo.put("RELEASE", Build.VERSION.RELEASE);
        buildInfo.put("BASE_OS", Build.VERSION.BASE_OS);
        buildInfo.put("SECURITY_PATCH", Build.VERSION.SECURITY_PATCH);
        buildInfo.put("SDK", Build.VERSION.SDK);
        buildInfo.put("SDK_INT", Build.VERSION.SDK_INT + "");
        buildInfo.put("PREVIEW_SDK_INT", Build.VERSION.PREVIEW_SDK_INT+"");
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
}
