package com.alip.zy.view.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.alip.zy.view.fragment.iview.BaseViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alip.zy.tools.R;
import com.alip.zy.view.fragment.HomeEntryView;
import com.alip.zy.view.fragment.HomeProfileView;
import com.alip.zy.view.fragment.HomeToolsView;

public class HomeActivity extends BaseImmersiveActivity {

    private static final int VIEW_PAGER_COUNT = 3;

    private BottomNavigationView mBottomNav;
    private ViewPager mHomePager;
    private BaseViewFragment[] mViewPagerArray = new BaseViewFragment[VIEW_PAGER_COUNT];

    private HomeViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomePager = (ViewPager) findViewById(R.id.home_view_pager);
        mBottomNav = (BottomNavigationView) findViewById(R.id.home_bottom_navigation_view);

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (BaseViewFragment baseView : mViewPagerArray) {
            if (baseView != null) {
                baseView.onResume();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (BaseViewFragment baseView : mViewPagerArray) {
            if (baseView != null) {
                baseView.onPause();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePager.clearOnPageChangeListeners();
        for (BaseViewFragment baseView : mViewPagerArray) {
            if (baseView != null) {
                baseView.onDestroy();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
//            if (isLocationEnable(this)) {
//                //定位已打开的处理
//            } else {
//                //定位依然没有打开的处理
//            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 2: {
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

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void initViews() {
        mPagerAdapter = new HomeViewPagerAdapter();
        mHomePager.setAdapter(mPagerAdapter);
        mHomePager.setOffscreenPageLimit(2);
        mHomePager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int itemId = 0;
                switch (position) {
                    case 0:
                        itemId = R.id.bottom_nav_ui;
                        break;
                    case 1:
                        itemId = R.id.bottom_nav_data;
                        break;
                    case 2:
                        itemId = R.id.bottom_nav_service;
                        break;
                }
                mBottomNav.setSelectedItemId(itemId);
            }
        });

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_ui:
                        mHomePager.setCurrentItem(0, true);
                        break;
                    case R.id.bottom_nav_data:
                        mHomePager.setCurrentItem(1, true);
                        break;
                    case R.id.bottom_nav_service:
                        mHomePager.setCurrentItem(2, true);
                        break;
//                    case R.id.bottom_nav_net:
//                        Toast.makeText(HomeActivity.this, "bottom_nav_net", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.bottom_nav_media:
//                        Toast.makeText(HomeActivity.this, "bottom_nav_media", Toast.LENGTH_SHORT).show();
//                        break;
                }
                return true;
            }
        });
        mBottomNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Toast.makeText(HomeActivity.this, "reClick Item " + item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private class HomeViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return VIEW_PAGER_COUNT;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseViewFragment fragment = mViewPagerArray[position];
            if (fragment != null) {
                container.addView(fragment);
                return fragment;
            } else {
                switch (position){
                    case 0:
                        fragment = new HomeEntryView(HomeActivity.this);
                        break;
                    case 1:
                        fragment = new HomeToolsView(HomeActivity.this);
                        break;
                    case 2:
                        fragment = new HomeProfileView(HomeActivity.this);
                        break;
                }
                mViewPagerArray[position] = fragment;
                container.addView(fragment);
                return fragment;
            }
        }
    }
}
