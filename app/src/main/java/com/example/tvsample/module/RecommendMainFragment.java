package com.example.tvsample.module;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tvsample.R;
import com.example.tvsample.adapter.OnItemClickListener;
import com.example.tvsample.adapter.VideoListAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.utils.AssetsHelper;
import com.example.tvsample.widget.BannerView;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by JasonWu on 27/12/2017
 */

public class RecommendMainFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.banner)
    BannerView bannerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recommend_main;
    }

    @Override
    protected void initViews() {
        VideoListInfo videoListInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/videoList.json"), VideoListInfo.class);
        bannerView.build(videoListInfo.getData());

        VideoListAdapter mVideoListAdapter = new VideoListAdapter(getContext());
        mVideoListAdapter.setData(videoListInfo.getData());
        mVideoListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position, String playListId) {
                YouTubePlayerActivity.launch(getContext(), playListId, 1);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(mVideoListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void updateData() {

    }
}
