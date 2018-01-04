package com.example.tvsample.base;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tvsample.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JasonWu on 04/01/2018
 */

@SuppressLint("Registered")
public class BaseBarActivity extends BaseActivity {
    @BindView(R.id.title)
    public TextView toolbar;
    @BindView(R.id.btn_clear)
    public TextView btnClear;
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base_bar;
    }

    @Override
    protected void initViews() {
        initToolbar();
    }

    protected void initToolbar() {
    }

    @Override
    protected void updateData() {
    }

    protected void clearData() {

    }

    @OnClick({R.id.btn_back, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_clear:
                clearData();
                break;
        }
    }
}