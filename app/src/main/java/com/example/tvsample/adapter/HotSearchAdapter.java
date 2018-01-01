package com.example.tvsample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.HotSearchInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 28/12/2017
 */

public class HotSearchAdapter extends BaseRvAdapter<HotSearchInfo.HotSearchEntity> {

    public HotSearchAdapter(Context context) {
        super(context);
    }

    @SuppressLint("InflateParams")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotSearchViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_adapter_hot_search, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HotSearchViewHolder)holder).bind(position);
    }

    class HotSearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rank)
        TextView rank;
        @BindView(R.id.title)
        TextView title;

        HotSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position){
            rank.setText(String.valueOf(getData().get(position).getRank()));
            title.setText(getData().get(position).getTitle());
            if(getData().get(position).getRank() < 4){
                rank.setBackgroundResource(R.drawable.round_point_red);
            }else {
                rank.setBackgroundResource(R.drawable.round_point_grey);
            }
        }
    }
}
