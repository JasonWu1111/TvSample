package com.example.tvsample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.HotSearchInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 02/01/2018
 */

public class SearchHistoryAdapter extends BaseRvAdapter<HotSearchInfo.HotSearchEntity> {

    public SearchHistoryAdapter(Context context) {
        super(context);
    }

    @SuppressLint("InflateParams")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_search_history, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SearchHistoryViewHolder)holder).bind(position);
    }

    class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.history_text)
        TextView historyText;
        @BindView(R.id.btn_close)
        ImageView btnClose;

        SearchHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position){
            btnClose.setOnClickListener(v ->{
                onItemClickListener.onClick(position, null);
            });
            historyText.setText(getData().get(position).getTitle());
        }
    }
}
