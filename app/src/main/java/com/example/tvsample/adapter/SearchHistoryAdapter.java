package com.example.tvsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvsample.Constants;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.SearchHistoryEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 02/01/2018
 */

public class SearchHistoryAdapter extends BaseRvAdapter<SearchHistoryEntity> {


    public SearchHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_search_history, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SearchHistoryViewHolder) holder).bind(position);
    }

//    @Override
//    public int getItemCount() {
//        return 4;
//    }

    class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.history_text)
        TextView historyText;
        @BindView(R.id.btn_close)
        ImageView btnClose;

        SearchHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            btnClose.setOnClickListener(v -> onItemClickListener.onClick(position, Constants.ACTION_DELETE, getData().get(position).getText()));
            itemView.setOnClickListener(v -> onItemClickListener.onClick(position,  Constants.ACTION_SEARCH, getData().get(position).getText()));
            historyText.setText(getData().get(position).getText());
        }
    }
}
