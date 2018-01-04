package com.example.tvsample.module.category;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tvsample.R;
import com.example.tvsample.adapter.SimpleTabAdapter;
import com.example.tvsample.adapter.VideoListAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.player.YouTubePlayerActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;


import java.util.Arrays;

import butterknife.BindView;


/**
 * Created by JasonWu on 02/01/2018
 */

public class CategoryAllFragment extends BaseFragment {

    @BindView(R.id.recycler_view_state)
    RecyclerView stateRecyclerView;
    @BindView(R.id.recycler_view_country)
    RecyclerView countryRecyclerView;
    @BindView(R.id.recycler_view_type)
    RecyclerView typeRecyclerView;
    @BindView(R.id.recycler_view_year)
    RecyclerView yearRecyclerView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category_all;
    }

    @Override
    protected void initViews() {
        String[] states = {"最熱", "最新", "好评"};
        String[] countries = {"全部", "台灣", "大陸", "香港", "韓國", "日本", "美國", "其他"};
        String[] types = {"全部", "古裝", "武俠", "警匪", "軍事", "神話", "科幻", "懸疑", "其他"};
        String[] years = {"全部", "2017", "2016", "2015", "2014", "2013", "2012", "其他"};

        SimpleTabAdapter stateAdapter = new SimpleTabAdapter(getContext(), states.length);
        SimpleTabAdapter countryAdapter = new SimpleTabAdapter(getContext(), countries.length);
        SimpleTabAdapter typeAdapter = new SimpleTabAdapter(getContext(), types.length);
        SimpleTabAdapter yearAdapter = new SimpleTabAdapter(getContext(), years.length);

        stateAdapter.setData(Arrays.asList(states));
        countryAdapter.setData(Arrays.asList(countries));
        typeAdapter.setData(Arrays.asList(types));
        yearAdapter.setData(Arrays.asList(years));

        stateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        typeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        yearRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        stateRecyclerView.setAdapter(stateAdapter);
        countryRecyclerView.setAdapter(countryAdapter);
        typeRecyclerView.setAdapter(typeAdapter);
        yearRecyclerView.setAdapter(yearAdapter);

    }

    @Override
    protected void updateData() {
        VideoListInfo baseInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/videoList.json"), VideoListInfo.class);
        VideoListAdapter mVideoListAdapter = new VideoListAdapter(getContext(), 2);
        mVideoListAdapter.setData(baseInfo.getData());
        mVideoListAdapter.setOnItemClickListener((position, action, playListId) -> YouTubePlayerActivity.launch(getContext(), playListId, 0));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mVideoListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

}
