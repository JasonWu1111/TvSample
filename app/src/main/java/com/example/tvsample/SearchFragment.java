package com.example.tvsample;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tvsample.adapter.HotSearchAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.HotSearchInfo;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by JasonWu on 28/12/2017
 */

public class SearchFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView hotSearchRecyclerView;

    private HotSearchAdapter hotSearchAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initViews() {
        hotSearchAdapter = new HotSearchAdapter(getContext());
        hotSearchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        hotSearchRecyclerView.setAdapter(hotSearchAdapter);
    }

    @Override
    protected void updateData() {
        HotSearchInfo hotSearchInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/hotSearch.json"), HotSearchInfo.class);
        Log.d(getTAG(), hotSearchInfo.toString());
        hotSearchAdapter.setData(hotSearchInfo.getData());
    }
}
