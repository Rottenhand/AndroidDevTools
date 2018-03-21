package com.alip.zy.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alip.zy.tools.R;
import com.alip.zy.view.adapter.HomeEntryListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 *
 * Created by ZY on 2018/1/2.
 */
public class HomeEntryView extends FrameLayout {

    private ListView mHomeList;
    private HomeEntryListAdapter mEntryListAdapter;

    public HomeEntryView(Context context) {
        super(context);
        initViews(context);
    }

    public HomeEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public HomeEntryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fragment_home_entry, this, true);

        mHomeList = findViewById(R.id.home_entry_list);


        TextView header = new TextView(getContext());
        header.setText("this is header");
        header.setTextColor(Color.CYAN);
        header.setTextSize(24);
        mHomeList.addHeaderView(header);


        mEntryListAdapter = new HomeEntryListAdapter(getContext());
        mHomeList.setAdapter(mEntryListAdapter);
//        mhomeList.setOnScrollListener(new FeedsListScrollListener());
//        mhomeList.setOnItemClickListener()
//
//        mhomeList.addHeaderView(text, null, true);
//        mhomeList.setHeaderDividersEnabled(false);



        // Create a progress bar to display while the list loads
        ProgressBar progressBar = findViewById(R.id.home_entry_loading);
        mHomeList.setEmptyView(progressBar);

        // Must add the progress bar to the root of the layout
//        ViewGroup root = (ViewGroup) ((Activity)getContext()).findViewById(android.R.id.content);
//        root.addView(progressBar);

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                addDataToList();
            }
        }, 3000);
    }

    public void addDataToList() {
        List<String> dataList = new ArrayList<>(50);
        for (int i=0 ; i<50; i++) {
            dataList.add(i, "dataList_"+i);
        }
        mEntryListAdapter.addAllData(dataList);
    }
}
