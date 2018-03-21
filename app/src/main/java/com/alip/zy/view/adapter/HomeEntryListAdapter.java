package com.alip.zy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZY on 2018/1/2.
 */
public class HomeEntryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> dataList = new ArrayList<>(50);

    public HomeEntryListAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);

        String data = (String) getItem(position);
        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setText(data);

        return convertView;
    }

    public void addAllData(List<String> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

}
