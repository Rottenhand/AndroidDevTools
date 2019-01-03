package com.alip.zy.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alip.zy.tools.R;
import com.alip.zy.view.adapter.RecyclerBottomAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends BaseImmersiveActivity {

    private RecyclerView mBottomList;
    private RecyclerBottomAdapter mBottomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
    }

    private void initView() {

        mBottomList = findViewById(R.id.recycler_bottom_list);

        mBottomAdapter = new RecyclerBottomAdapter();

        //设置布局管理器
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBottomList.setLayoutManager(layout);
        //设置adapter
        mBottomList.setAdapter(mBottomAdapter);
        //设置Item增加、移除动画
        mBottomList.setItemAnimator(new DefaultItemAnimator());
//        mBottomList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        initData();
        mBottomAdapter.setOnItemClickLitener(new RecyclerBottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(RecyclerViewActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position, String data) {
                Toast.makeText(RecyclerViewActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        List<String> mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
        mBottomAdapter.setData(mDatas);
    }
}
