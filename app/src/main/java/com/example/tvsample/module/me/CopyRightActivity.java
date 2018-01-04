package com.example.tvsample.module.me;

import android.view.View;
import android.widget.TextView;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JasonWu on 04/01/2018
 */

public class CopyRightActivity extends BaseActivity {
    @BindView(R.id.title)
    public TextView toolbar;
    @BindView(R.id.btn_clear)
    public TextView btnClear;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_copyright;
    }

    @Override
    protected void initViews() {
        toolbar.setText(getString(R.string.copyright));
        btnClear.setVisibility(View.GONE);
    }

    @Override
    protected void updateData() {

    }

    @OnClick({R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
