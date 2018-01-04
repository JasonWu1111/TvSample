package com.example.tvsample.module.category;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tvsample.R;
import com.example.tvsample.adapter.VideoListAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.player.YouTubePlayerActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by JasonWu on 2018/1/1
 */

public class CategoryListFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category_list;
    }

    @Override
    protected void initViews() {
        VideoListInfo baseInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/videoList.json"), VideoListInfo.class);
        VideoListAdapter mVideoListAdapter = new VideoListAdapter(getContext(),2);
        mVideoListAdapter.setData(baseInfo.getData());
        mVideoListAdapter.setOnItemClickListener((position, action, playListId) -> YouTubePlayerActivity.launch(getContext(), playListId, 0));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mVideoListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void updateData() {

    }

}
