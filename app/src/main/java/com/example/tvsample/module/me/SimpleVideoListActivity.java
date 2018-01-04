package com.example.tvsample.module.me;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tvsample.R;
import com.example.tvsample.adapter.SimpleVideoListAdapter;
import com.example.tvsample.base.BaseBarActivity;
import com.example.tvsample.entity.VideoListInfo;
import com.example.tvsample.utils.AssetsHelper;
import com.google.gson.Gson;

/**
 * Created by JasonWu on 04/01/2018
 */

public class SimpleVideoListActivity extends BaseBarActivity {
    private String type;


    @Override
    protected void initToolbar() {
        type = getIntent().getStringExtra("type");
        if(type.equals("favorite")){
            toolbar.setText(getString(R.string.my_favorite));
        }else {
            toolbar.setText(getString(R.string.recent_play));
        }

    }

    @Override
    protected void updateData() {
        super.updateData();
        VideoListInfo baseInfo = new Gson().fromJson(AssetsHelper.readData(this, "test/videoList.json"), VideoListInfo.class);
        SimpleVideoListAdapter adapter = new SimpleVideoListAdapter(this, type);
        adapter.setData(baseInfo.getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
