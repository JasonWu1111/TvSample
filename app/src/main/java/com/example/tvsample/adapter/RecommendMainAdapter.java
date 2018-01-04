package com.example.tvsample.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseRvAdapter;
import com.example.tvsample.entity.RecommendVideoInfo;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.player.YouTubePlayerActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 02/01/2018
 */

public class RecommendMainAdapter extends BaseRvAdapter<RecommendVideoInfo.HeadlineEntity> {
    

    public RecommendMainAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter_recommend_video, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecommendMainViewHolder)holder).bind(position);
    }

    class RecommendMainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.headline)
        TextView headline;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        RecommendMainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        
        void bind(int position){
            headline.setText(getData().get(position).getHeadline());
            VideoListInfo baseInfo = new Gson().fromJson(AssetsHelper.readData(mContext, "test/videoList.json"), VideoListInfo.class);
            VideoListAdapter videoListAdapter = new VideoListAdapter(mContext, 1);
            videoListAdapter.setData(baseInfo.getData());
            videoListAdapter.setOnItemClickListener((pos, action, playListId) -> YouTubePlayerActivity.launch(mContext, playListId, 0));
            GridLayoutManager manager = new GridLayoutManager(mContext, 2);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int count = 1;
                    if (position == 0) {
                        count = 2;
                    }
                    return count;
                }
            });
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(videoListAdapter);
            recyclerView.setNestedScrollingEnabled(false);
        }
    }
}
