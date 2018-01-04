package com.example.tvsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.VideoListEntity;
import com.example.tvsample.widget.RoundCornerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 04/01/2018
 */

public class SimpleVideoListAdapter extends BaseRvAdapter<VideoListEntity> {
    private String type;

    public SimpleVideoListAdapter(Context context, String type) {
        super(context);
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleVideoListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_favorite, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SimpleVideoListViewHolder) holder).bind(position);
    }

    class SimpleVideoListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cover)
        RoundCornerImageView cover;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.episode)
        TextView episode;
        @BindView(R.id.btn_delete)
        TextView btnDelete;

        SimpleVideoListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            if (type.equals("favorite")) {
                btnDelete.setText(R.string.cancel_favorite);
            }else {
                btnDelete.setText(R.string.delete);
            }
            Glide.with(mContext).load(getData().get(position).getCover()).into(cover);
            title.setText(getData().get(position).getTitle());
            episode.setText("已更新" + getData().get(position).getEpisode());
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });
        }
    }
}
