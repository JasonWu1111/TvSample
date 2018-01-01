package com.example.tvsample.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.tvsample.adapter.OnItemClickListener;

import java.util.List;

/**
 * Created by JasonWu on 28/12/2017
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter{
    private List<T> mData;
    public Context mContext;
    public OnItemClickListener onItemClickListener;

    public BaseRvAdapter(Context context){
        mContext = context;
    }

    public List<T> getData(){
        return mData;
    }

    public void setData(List<T> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<T> data){
        mData.addAll(data);
        notifyItemRangeChanged(mData.size(), data.size());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
