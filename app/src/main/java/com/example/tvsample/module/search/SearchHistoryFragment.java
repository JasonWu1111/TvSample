package com.example.tvsample.module.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tvsample.R;
import com.example.tvsample.adapter.SearchHistoryAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.HotSearchInfo;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by JasonWu on 02/01/2018
 */

public class SearchHistoryFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_history;
    }

    @Override
    protected void initViews() {
        SearchHistoryAdapter mAdapter = new SearchHistoryAdapter(getContext());
        HotSearchInfo hotSearchInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/hotSearch.json"), HotSearchInfo.class);
        mAdapter.setData(hotSearchInfo.getData());
        mAdapter.setOnItemClickListener((position, text) -> {
            mAdapter.deleteItem(position);
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void updateData() {

    }

}
