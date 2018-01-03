package com.example.tvsample.module.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvsample.R;
import com.example.tvsample.adapter.RecommendMainAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.RecommendVideoInfo;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.player.YouTubePlayerActivity;
import com.example.tvsample.module.search.SearchActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.example.tvsample.widget.BannerView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
        bannerView.init(videoListInfo.getData());

        RecommendVideoInfo recommendVideoInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/recommendVideo.json"), RecommendVideoInfo.class);
        RecommendMainAdapter mMainAdapter = new RecommendMainAdapter(getContext());
        mMainAdapter.setData(recommendVideoInfo.getData());
        mMainAdapter.setOnItemClickListener((position, action, playListId) -> YouTubePlayerActivity.launch(getContext(), playListId, 0));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMainAdapter);
        if (getContext() != null) {
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void updateData() {

    }

    @OnClick(R.id.search_text)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }
}
