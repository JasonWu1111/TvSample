package com.example.tvsample.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.VideoListEntity;
import com.example.tvsample.entity.VideoListInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 2017/12/30
 */

public class VideoListAdapter extends BaseRvAdapter<VideoListEntity> {
    private static final int NORMAL_VIEW_TYPE = 1;
    private static final int LONG_VIEW_TYPE = 2;
    private static final int LARGE_VIEW_TYPE = 3;
    private int type;

    public VideoListAdapter(Context context, int type) {
        super(context);
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resLayoutId;
        if (type == NORMAL_VIEW_TYPE) {
            if (viewType == LARGE_VIEW_TYPE) {
                resLayoutId = R.layout.item_adapter_video_list_large;
            } else {
                resLayoutId = R.layout.item_adapter_video_list_normal;
            }
        } else {
            resLayoutId = R.layout.item_adapter_video_list_long;
        }
        return new VideoListViewHolder(LayoutInflater.from(parent.getContext()).inflate(resLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VideoListViewHolder) holder).bind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (type == NORMAL_VIEW_TYPE) {
            if (position == 0) {
                return LARGE_VIEW_TYPE;
            } else {
                return NORMAL_VIEW_TYPE;
            }
        }
        return LONG_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return type == 1 ? 5 : super.getItemCount();
    }

    class VideoListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;

        VideoListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final int position) {
            Glide.with(mContext).load(getData().get(position).getCover()).into(image);
            title.setText(getData().get(position).getTitle());
            description.setText(getData().get(position).getDescription());
            itemView.setOnClickListener(v -> onItemClickListener.onClick(position, null, getData().get(position).getId()));
        }
    }
}
