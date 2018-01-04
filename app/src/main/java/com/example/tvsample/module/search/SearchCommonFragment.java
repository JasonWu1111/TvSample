package com.example.tvsample.module.search;


import android.content.Intent;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tvsample.Constants;
import com.example.tvsample.MyApplication;
import com.example.tvsample.R;
import com.example.tvsample.adapter.HotSearchAdapter;
import com.example.tvsample.adapter.SearchHistoryAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.entity.HotSearchInfo;
import com.example.tvsample.entity.SearchHistoryEntity;
import com.example.tvsample.greendao.SearchHistoryEntityDao;
import com.example.tvsample.module.MainActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JasonWu on 03/01/2018
 */

public class SearchCommonFragment extends BaseFragment {

    @BindView(R.id.history_recycler_view)
    RecyclerView historyRecyclerView;
    @BindView(R.id.hot_recycler_view)
    RecyclerView hotRecyclerView;

    private SearchHistoryAdapter mAdapter;
    private SearchHistoryEntityDao searchHistoryEntityDao;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_common;
    }

    @Override
    protected void initViews() {
        HotSearchInfo hotSearchInfo = new Gson().fromJson(AssetsHelper.readData(getContext(), "test/hotSearch.json"), HotSearchInfo.class);
        searchHistoryEntityDao = MyApplication.getDaoSession().getSearchHistoryEntityDao();

        mAdapter = new SearchHistoryAdapter(getContext());
        List<SearchHistoryEntity> searchHistories = searchHistoryEntityDao.queryBuilder().list();
        mAdapter.setData(searchHistories);
        mAdapter.setOnItemClickListener((position, action, text) -> {
            switch (action) {
                case Constants.ACTION_DELETE:
                    mAdapter.deleteItem(position);
                    SearchHistoryEntity searchHistoryEntity = searchHistoryEntityDao.queryBuilder().where(SearchHistoryEntityDao.Properties.Text.eq(text)).build().unique();
                    if(searchHistoryEntity != null){
                        searchHistoryEntityDao.deleteByKey(searchHistoryEntity.getId());
                    }
                    break;
                case Constants.ACTION_SEARCH:
                    if (getActivity() instanceof MainActivity) {
                        startActivity(new Intent(getContext(), SearchActivity.class).putExtra(Constants.SEARCH_TITLE, text));
                    } else if (getActivity() instanceof SearchActivity) {
                        ((SearchActivity) getActivity()).search(text);
                    }
                    break;
            }
        });
        historyRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        historyRecyclerView.setAdapter(mAdapter);
        historyRecyclerView.setNestedScrollingEnabled(false);

        HotSearchAdapter hotSearchAdapter = new HotSearchAdapter(getContext());
        hotSearchAdapter.setData(hotSearchInfo.getData());
        hotSearchAdapter.setOnItemClickListener((position, action, text) -> {
            if (getActivity() instanceof MainActivity) {
                startActivity(new Intent(getContext(), SearchActivity.class).putExtra(Constants.SEARCH_TITLE, text));
            } else if (getActivity() instanceof SearchActivity) {
                ((SearchActivity) getActivity()).search(text);
            }
        });
        hotRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        hotRecyclerView.setAdapter(hotSearchAdapter);
        hotRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void updateData() {
    }


    @OnClick(R.id.btn_clear)
    public void onViewClicked() {
        mAdapter.setData(null);
//        searchHistoryDao.deleteAll();
    }
}
