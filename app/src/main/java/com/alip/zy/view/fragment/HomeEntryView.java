package com.alip.zy.view.fragment;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alip.zy.tools.R;
import com.alip.zy.util.ThreadUtil;
import com.alip.zy.view.activity.HomeActivity;
import com.alip.zy.view.adapter.HomeEntryListAdapter;
import com.alip.zy.view.fragment.iview.BaseViewFragment;
import com.alip.zy.view.model.AppInfoModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 首页
 *
 * Created by ZY on 2018/1/2.
 */
public class HomeEntryView extends BaseViewFragment {

    private ProgressBar progress;
    private RecyclerView mHomeList;
    private HomeEntryListAdapter mEntryListAdapter;

    private HomeActivity activity;

    public HomeEntryView(Context context) {
        super(context);
        activity = (HomeActivity) context;
        initViews(context);
    }

    public HomeEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (HomeActivity) context;
        initViews(context);
    }

    public HomeEntryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (HomeActivity) context;
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
        LayoutInflater.from(context).inflate(R.layout.fragment_home_entry, this, true);

        mHomeList = findViewById(R.id.home_entry_list);
        // Create a progress bar to display while the list loads
        progress = findViewById(R.id.home_entry_loading);

        //设置布局管理器
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(RecyclerView.VERTICAL);
        mHomeList.setLayoutManager(layout);
        //设置Item增加、移除动画
        mHomeList.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        mEntryListAdapter = new HomeEntryListAdapter(getContext());
        mEntryListAdapter.setOnItemClickListener(new HomeEntryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, AppInfoModel data) {
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
        mHomeList.setAdapter(mEntryListAdapter);

        initListData();
    }

    private void initListData() {
        progress.setVisibility(VISIBLE);
        ThreadUtil.runTask(new Runnable() {
            @Override
            public void run() {
                loadAppInfo();
            }
        });
    }

    private void loadAppInfo() {
        PackageManager pm = getContext().getPackageManager();
        List<PackageInfo> packageList = pm.getInstalledPackages(PackageManager.GET_META_DATA);

        Iterator<PackageInfo> iterator = packageList.iterator();
        List<AppInfoModel> appInfoModels = new ArrayList<>(packageList.size());
        while (iterator.hasNext()) {
            PackageInfo packageInfo = iterator.next();
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                iterator.remove();
                continue;
            }
            AppInfoModel appInfo = new AppInfoModel();
//            appInfo.name = packageInfo.applicationInfo.loadLabel();
            appInfo.appIcon = getContext().getPackageManager().getApplicationIcon(packageInfo.applicationInfo);
            appInfo.packageName = packageInfo.packageName;
            appInfo.targetSdk = packageInfo.applicationInfo.targetSdkVersion;
            appInfo.versionName = packageInfo.versionName;
            appInfo.versionCode = packageInfo.versionCode;
            appInfoModels.add(appInfo);
        }
        updateAppInfo(appInfoModels);
    }

    private void updateAppInfo(final List<AppInfoModel> appInfoList) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(GONE);
                mEntryListAdapter.addAllData(appInfoList);
            }
        });
    }
}
