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

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 2018/1/3
 */

public class EpisodeAdapter extends BaseRvAdapter {

    //维护一个HashMap来控制选集时界面变化
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Boolean> map = new HashMap<>();

    private int episodes = 1;

    //当前播放的位置
    private int curIndex = 0;
    //记录切换位置前的位置
    private int preIndex = -1;

    public EpisodeAdapter(Context context, int episodes, int startIndex) {
        super(context);
        this.episodes = episodes;
        this.curIndex = startIndex;
        for (int i = 0; i < episodes; i++) {
            map.put(i, false);
        }
        map.put(startIndex, true);
    }

    public void setEpisodes(int episodes){
        this.episodes = episodes;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EpisodeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_episode, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EpisodeViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return episodes;
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.episode_frame)
        ImageView episodeFrame;
        @BindView(R.id.episode_text)
        TextView episodeText;

        EpisodeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            episodeFrame.setSelected(map.get(position));
            int colorRes = map.get(position) ? R.color.colorAccent : R.color.grey;
            episodeText.setTextColor(mContext.getResources().getColor(colorRes));
            episodeText.setText(String.valueOf(position + 1));

            itemView.setOnClickListener(v -> {
                if (position != curIndex) {
                    preIndex = curIndex;
                    curIndex = position;
                    map.put(preIndex, false);
                    map.put(curIndex, true);
                    notifyDataSetChanged();
                    onItemClickListener.onClick(position, null, null);
                }
            });
        }
    }
}
