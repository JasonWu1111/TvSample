package com.example.tvsample.module.search;



import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.tvsample.Constants;
import com.example.tvsample.MyApplication;
import com.example.tvsample.R;
import com.example.tvsample.adapter.SearchResultAdapter;
import com.example.tvsample.base.BaseActivity;
import com.example.tvsample.entity.SearchHistoryEntity;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.module.player.YouTubePlayerActivity;
import com.example.tvsample.utils.AssetsHelper;
import com.example.tvsample.utils.ToastUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_text)
    EditText searchView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.search_common_container)
    FrameLayout commonView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        String text = getIntent().getStringExtra(Constants.SEARCH_TITLE);
        searchView.setText(text);
        getSupportFragmentManager().beginTransaction().add(R.id.search_common_container, new SearchCommonFragment()).commit();
    }

    @Override
    protected void updateData() {
    }

    public void search(String text) {
        searchView.setText(text);
        commonView.setVisibility(View.GONE);

        SearchHistoryEntity newSearchHistoryEntity = new SearchHistoryEntity(null,text);
//        MyApplication.getDaoSession().getSearchHistoryDao().insert(newSearchHistoryEntity);

        VideoListInfo videoListInfo = new Gson().fromJson(AssetsHelper.readData(this, "test/videoList.json"), VideoListInfo.class);
        SearchResultAdapter adapter = new SearchResultAdapter(this);
        adapter.setData(videoListInfo.getData());
        adapter.setOnItemClickListener((position, action, playListId) -> {
            switch (action) {
                case Constants.ACTION_PLAY:
                    YouTubePlayerActivity.launch(SearchActivity.this, playListId, 0);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.btn_back, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_search:
                if(searchView.getText().toString().length() == 0){
                    ToastUtil.showToast(getString(R.string.search_null));
                }else {
                    search(searchView.getText().toString());
                }
                break;
        }
    }

}
