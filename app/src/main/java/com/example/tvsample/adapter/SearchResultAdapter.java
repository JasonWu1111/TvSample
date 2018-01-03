package com.example.tvsample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvsample.Constants;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.player.YouTubePlayerActivity;
import com.example.tvsample.module.search.SearchActivity;
import com.example.tvsample.widget.RoundCornerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 03/01/2018
 */

public class SearchResultAdapter extends BaseRvAdapter<VideoListInfo.PlayListEntity> {

    public SearchResultAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_search_result, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SearchResultViewHolder)holder).bind(position);
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        RoundCornerImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.btn_play)
        Button btnPlay;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position){
            Glide.with(mContext).load(getData().get(position).getImageUrl()).into(image);
            title.setText(getData().get(position).getName());
            btnPlay.setOnClickListener(v -> {
                onItemClickListener.onClick(position, Constants.ACTION_PLAY, getData().get(position).getPlayListId());
            });

            Episode2Adapter adapter = new Episode2Adapter(mContext, 20);
            adapter.setOnItemClickListener((position1, action, data) -> YouTubePlayerActivity.launch(mContext, getData().get(position1).getPlayListId(), position1));
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 6));
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
