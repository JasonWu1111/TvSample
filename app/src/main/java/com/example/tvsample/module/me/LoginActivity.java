package com.example.tvsample.module.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.btn_check)
    Button btnCheck;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        btnCheck.setSelected(true);
    }

    @Override
    protected void updateData() {

    }

    @OnClick({R.id.btn_close, R.id.btn_facebook_login, R.id.btn_google_login, R.id.btn_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                break;
            case R.id.btn_facebook_login:
                break;
            case R.id.btn_google_login:
                break;
            case R.id.btn_check:
                btnCheck.setSelected(!btnCheck.isSelected());
                break;
        }
    }
}
