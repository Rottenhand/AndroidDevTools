package com.alip.zy.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alip.zy.tools.R;
import com.alip.zy.view.model.AppInfoModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ZY on 2018/1/2.
 */
public class HomeEntryListAdapter extends RecyclerView.Adapter<HomeEntryListAdapter.ItemViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position, AppInfoModel data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView appIcon;
        TextView appVersion;
        TextView appTargetSdk;

        private ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.app_info_icon);
            appVersion = itemView.findViewById(R.id.app_info_version_text);
            appTargetSdk = itemView.findViewById(R.id.app_info_target_ver_text);
        }
    }

    private Context mContext;
    private List<AppInfoModel> dataList = new ArrayList<>();
    private HomeEntryListAdapter.OnItemClickListener mOnItemClickListener;

    public HomeEntryListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public HomeEntryListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ItemViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeEntryListAdapter.ItemViewHolder holder, int position) {

        final AppInfoModel appInfo = dataList.get(position);
        holder.appVersion.setText(appInfo.packageName);
        holder.appTargetSdk.setText(String.valueOf(appInfo.targetSdk));
        holder.appIcon.setImageDrawable(appInfo.appIcon);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos, appInfo);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.layout_app_info_item;
    }

    public void addAllData(List<AppInfoModel> data) {
        if (dataList == null) {
            dataList = new ArrayList<AppInfoModel>();
        }
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(AppInfoModel data, int index) {
        if (index < 0 || index > dataList.size()) {
            index = dataList.size();
        }
        dataList.add(index, data);
        notifyItemInserted(index);
    }

    public void removeItem(int index) {
        if (index < 0 || index > dataList.size()) {
            index = dataList.size();
        }
        dataList.remove(index);
        notifyItemRemoved(index);
    }

    public void setOnItemClickListener(HomeEntryListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
