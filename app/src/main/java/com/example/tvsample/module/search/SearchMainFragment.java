package com.example.tvsample.module.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.example.tvsample.R;
import com.example.tvsample.adapter.HotSearchAdapter;
import com.example.tvsample.adapter.OnItemClickListener;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.HotSearchInfo;
import com.example.tvsample.module.me.MeMainFragment;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by JasonWu on 28/12/2017
 */

public class SearchMainFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView hotSearchRecyclerView;
    @BindView(R.id.search_text)
    EditText searchText;

    private HotSearchAdapter hotSearchAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_main;
    }

    @Override
    protected void initViews() {
        hotSearchAdapter = new HotSearchAdapter(getContext());
        hotSearchAdapter.setOnItemClickListener((position, text) -> {
            searchText.setText(text);
        });
        hotSearchRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        hotSearchRecyclerView.setAdapter(hotSearchAdapter);
        hotSearchRecyclerView.setNestedScrollingEnabled(false);

        getChildFragmentManager().beginTransaction().add(R.id.search_history_container, new SearchHistoryFragment()).commit();
        getChildFragmentManager().beginTransaction().add(R.id.hot_recommend_container, new SearchRecommendFragment()).commit();
    }

    @Override
    protected void updateData() {
        HotSearchInfo hotSearchInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/hotSearch.json"), HotSearchInfo.class);
        hotSearchAdapter.setData(hotSearchInfo.getData());
    }
}
