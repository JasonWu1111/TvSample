package com.example.tvsample.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by JasonWu on 28/12/2017
 */

public class MeMainFragment extends BaseFragment {
    private static final int CODE_LOGIN = 1;

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.text_my_message)
    TextView myMessage;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.btn_login, R.id.btn_recent_play, R.id.btn_my_collection, R.id.btn_my_comment, R.id.btn_my_message, R.id.btn_feedback, R.id.btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivityForResult(new Intent(getContext(), LoginActivity.class), CODE_LOGIN);
                break;
            case R.id.btn_recent_play:
                break;
            case R.id.btn_my_collection:
                break;
            case R.id.btn_my_comment:
                break;
            case R.id.btn_my_message:
                break;
            case R.id.btn_feedback:
                break;
            case R.id.btn_setting:
                break;
        }
    }
}
