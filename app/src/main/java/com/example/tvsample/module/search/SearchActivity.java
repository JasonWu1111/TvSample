package com.example.tvsample.module.search;


import android.view.View;
import android.widget.EditText;

import com.example.tvsample.Constants;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_text)
    EditText searchView;

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
    protected void updateData() {}

    public void search(String text){
        searchView.setText(text);
    }


    @OnClick({R.id.btn_back, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_search:
                break;
        }
    }
}
