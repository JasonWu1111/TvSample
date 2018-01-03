package com.example.tvsample.module.search;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tvsample.Constants;
import com.example.tvsample.R;
import com.example.tvsample.adapter.HotSearchAdapter;
import com.example.tvsample.adapter.OnItemClickListener;
import com.example.tvsample.adapter.SearchHistoryAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.HotSearchInfo;
import com.example.tvsample.module.MainActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import butterknife.BindView;


/**
 * Created by JasonWu on 03/01/2018
 */

public class SearchCommonFragment extends BaseFragment {

    @BindView(R.id.history_recycler_view)
    RecyclerView historyRecyclerView;
    @BindView(R.id.hot_recycler_view)
    RecyclerView hotRecyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_common;
    }

    @Override
    protected void initViews() {
        HotSearchInfo hotSearchInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/hotSearch.json"), HotSearchInfo.class);

        SearchHistoryAdapter mAdapter = new SearchHistoryAdapter(getContext());
        mAdapter.setData(hotSearchInfo.getData());
        mAdapter.setOnItemClickListener((position, data) -> {
            if (data.equals(Constants.ACTION_DELETE)) {
                mAdapter.deleteItem(position);
            }else {
                if(getActivity() instanceof MainActivity){
                    startActivity(new Intent(getContext(), SearchActivity.class).putExtra(Constants.SEARCH_TITLE, data));
                }else if(getActivity() instanceof SearchActivity){
                    ((SearchActivity)getActivity()).search(data);
                }
            }
        });
        historyRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        historyRecyclerView.setAdapter(mAdapter);
        historyRecyclerView.setNestedScrollingEnabled(false);

        HotSearchAdapter hotSearchAdapter = new HotSearchAdapter(getContext());
        hotSearchAdapter.setData(hotSearchInfo.getData());
        hotSearchAdapter.setOnItemClickListener((position, data) -> {
            if(getActivity() instanceof MainActivity){
                startActivity(new Intent(getContext(), SearchActivity.class).putExtra(Constants.SEARCH_TITLE, data));
            }else if(getActivity() instanceof SearchActivity){
                ((SearchActivity)getActivity()).search(data);
            }
        });
        hotRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        hotRecyclerView.setAdapter(hotSearchAdapter);
        hotRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void updateData() {
    }

}
