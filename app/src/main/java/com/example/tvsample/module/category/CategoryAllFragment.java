package com.example.tvsample.module.category;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvsample.R;
import com.example.tvsample.adapter.VideoListAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.TabEntity;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.player.YouTubePlayerActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JasonWu on 02/01/2018
 */

public class CategoryAllFragment extends BaseFragment {

    @BindView(R.id.tab_layout_state)
    CommonTabLayout stateTabLayout;
    @BindView(R.id.tab_layout_country)
    CommonTabLayout CountryTabLayout;
    @BindView(R.id.tab_layout_type)
    CommonTabLayout typeTabLayout;
    @BindView(R.id.tab_layout_type_year)
    CommonTabLayout yearTabLayout;

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

        ArrayList<CustomTabEntity> stateTabs = new ArrayList<>();
        ArrayList<CustomTabEntity> countryTabs = new ArrayList<>();
        ArrayList<CustomTabEntity> typeTabs = new ArrayList<>();
        ArrayList<CustomTabEntity> yearTabs = new ArrayList<>();

        for (String state : states) {
            stateTabs.add(new TabEntity(state));
        }
        for (String country : countries) {
            countryTabs.add(new TabEntity(country));
        }
        for (String type : types) {
            typeTabs.add(new TabEntity(type));
        }
        for (String year : years) {
            yearTabs.add(new TabEntity(year));
        }

        stateTabLayout.setTabData(stateTabs);
        CountryTabLayout.setTabData(countryTabs);
        typeTabLayout.setTabData(typeTabs);
        yearTabLayout.setTabData(yearTabs);

        stateTabLayout.requestFocus();

    }

    @Override
    protected void updateData() {
        VideoListInfo videoListInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/videoList.json"), VideoListInfo.class);
        VideoListAdapter mVideoListAdapter = new VideoListAdapter(getContext(),2);
        mVideoListAdapter.setData(videoListInfo.getData());
        mVideoListAdapter.setOnItemClickListener((position, playListId) -> YouTubePlayerActivity.launch(getContext(), playListId, 0));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mVideoListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

}
