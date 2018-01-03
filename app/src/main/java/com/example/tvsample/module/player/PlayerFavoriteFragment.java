package com.example.tvsample.module.player;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvsample.R;
import com.example.tvsample.adapter.VideoListAdapter;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JasonWu on 03/01/2018
 */

public class PlayerFavoriteFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        VideoListInfo videoListInfo = new Gson().fromJson(AssetsHelper.readData(getActivity(), "test/videoList.json"), VideoListInfo.class);
        VideoListAdapter mVideoListAdapter = new VideoListAdapter(getActivity(),2);
        mVideoListAdapter.setData(videoListInfo.getData());
        mVideoListAdapter.setOnItemClickListener((position, action, playListId) -> YouTubePlayerActivity.launch(getActivity(), playListId, 0));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(mVideoListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
