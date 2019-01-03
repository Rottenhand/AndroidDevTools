package com.alip.zy.view.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alip.zy.tools.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerBottomAdapter extends RecyclerView.Adapter<RecyclerBottomAdapter.ItemViewHolder> {

    public interface OnItemClickListener {

        void onItemClick(View view, int position, String data);

        void onItemLongClick(View view, int position, String data);
    }

    private List<String> mDatas;
    private OnItemClickListener mOnItemClickListener;

    @Override
    public int getItemViewType(int position) {
//            return super.getItemViewType(position);
        return R.layout.layout_bottom_list_item;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ItemViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final String data = mDatas.get(position);
        holder.tv.setText(data);
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos, data);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos, data);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setData(List<String> data) {
        if (mDatas == null) {
            mDatas = new ArrayList<String>();
        }
        this.mDatas.clear();
        this.mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(String data, int index) {
        if (index < 0 || index > mDatas.size()) {
            index = mDatas.size();
        }
        mDatas.add(index, data);
        notifyItemInserted(index);
    }

    public void removeItem(int index) {
        if (index < 0 || index > mDatas.size()) {
            index = mDatas.size();
        }
        mDatas.remove(index);
        notifyItemRemoved(index);
    }

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ItemViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.bottom_item_name);
        }
    }

}
