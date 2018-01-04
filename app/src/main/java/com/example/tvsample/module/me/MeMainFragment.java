package com.example.tvsample.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.event.UserStateChangeEvent;
import com.example.tvsample.utils.AccountHelper;
import com.example.tvsample.utils.SharedPreferencesUtil;
import com.example.tvsample.widget.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JasonWu on 28/12/2017
 */

public class MeMainFragment extends BaseFragment {

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.text_my_message)
    TextView myMessage;
    @BindView(R.id.nickname)
    TextView nickname;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me_main;
    }

    @Override
    protected void initViews() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void updateData() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void onLoginStateChange() {
        if (AccountHelper.isLogin()) {
            Glide.with(this).load(SharedPreferencesUtil.getInstance().getString("avatar", "")).into(avatar);
            nickname.setText(SharedPreferencesUtil.getInstance().getString("nickname", ""));
        } else {
            avatar.setBackgroundResource(R.drawable.personal_default);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.btn_login, R.id.btn_my_favorite, R.id.btn_my_comment, R.id.btn_my_message})
    public void onViewClicked(View view) {
        if (!AccountHelper.isLogin()) {
            startActivity(new Intent(getContext(), LoginActivity.class));
        } else {
            switch (view.getId()) {
                case R.id.btn_login:
                    break;
                case R.id.btn_my_favorite:
                    startActivity(new Intent(getContext(), SimpleVideoListActivity.class).putExtra("type", "favorite"));
                    break;
                case R.id.btn_my_comment:
                    break;
                case R.id.btn_my_message:
                    break;
            }
        }

    }

    @OnClick({R.id.btn_recent_play, R.id.btn_feedback, R.id.btn_setting, R.id.btn_copyright})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.btn_recent_play:
                startActivity(new Intent(getContext(), SimpleVideoListActivity.class).putExtra("type", "recent"));
                break;
            case R.id.btn_feedback:
                startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
            case R.id.btn_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.btn_copyright:
                startActivity(new Intent(getContext(), CopyRightActivity.class));
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserStateChangeEvent(UserStateChangeEvent event) {
        Log.d(getTAG(), "---------------event");
        onLoginStateChange();
    }

}
