package com.example.tvsample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.widget.RoundCornerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 2017/12/30
 */

public class VideoListAdapter extends BaseRvAdapter<VideoListInfo.PlayListEntity> {

    public VideoListAdapter(Context context) {
        super(context);
    }

    @SuppressLint("InflateParams")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_video_list, null, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VideoListViewHolder)holder).bind(position);
    }

    class VideoListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        RoundCornerImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;

        VideoListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int position){
            Glide.with(mContext).load(getData().get(position).getImageUrl()).into(image);
            title.setText(getData().get(position).getName());
            description.setText(getData().get(position).getDescription());
            itemView.setOnClickListener(v -> onItemClickListener.onClick(position, getData().get(position).getPlayListId()));
        }
    }
}
