package com.example.tvsample.module.me;

import android.content.Intent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tvsample.MyApplication;
import com.example.tvsample.R;
import com.example.tvsample.base.BaseActivity;
import com.example.tvsample.entity.BaseInfo;
import com.example.tvsample.network.RetrofitService;
import com.example.tvsample.utils.AccountHelper;
import com.example.tvsample.utils.DataCleanUtil;
import com.example.tvsample.utils.FcmUtil;
import com.example.tvsample.utils.ShareUtil;
import com.example.tvsample.utils.SharedPreferencesUtil;
import com.example.tvsample.utils.ToastUtil;
import com.example.tvsample.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.subscribers.DisposableSubscriber;


/**
 * Created by JasonWu on 2017/9/19
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView toolbar;
    @BindView(R.id.btn_clear)
    TextView btnClear;
    @BindView(R.id.text_cache)
    TextView mCache;
    @BindView(R.id.switch_push)
    Switch mPushSwitch;

    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {

        toolbar.setText(R.string.setting);
        btnClear.setVisibility(View.GONE);
        loadingDialog = new LoadingDialog(this);
        setCache();

        mPushSwitch.setChecked(SharedPreferencesUtil.getInstance().getBoolean("isSubscribe", true));
        mPushSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                FcmUtil.connectToFcm();
                SharedPreferencesUtil.getInstance().putBoolean("isSubscribe", true);
            } else {
                FcmUtil.unConnectToFcm();
                SharedPreferencesUtil.getInstance().putBoolean("isSubscribe", false);
            }
        });
    }

    @Override
    protected void updateData() {

    }

    public void setCache() {
        String cache = "";
        try {
            cache = DataCleanUtil.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCache.setText(cache.length() == 0 ? cache : getString(R.string.total) + cache);
    }

    @OnClick({R.id.btn_personal, R.id.btn_push, R.id.btn_clear_cache
            , R.id.btn_rate, R.id.btn_privacy, R.id.btn_about, R.id.btn_logout, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_personal:
                if (!AccountHelper.isLogin()) {
                    startActivity(new Intent(this, LoginActivity.class));
                    return;
                }
//                startActivity(new Intent(this, PersonalInformationActivity.class));
                break;
            case R.id.btn_clear_cache:
                try {
                    ToastUtil.showToast(getString(R.string.deleted) + DataCleanUtil.getTotalCacheSize(MyApplication.getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DataCleanUtil.clearAllCache(MyApplication.getContext());
                setCache();
                break;
            case R.id.btn_push:
                mPushSwitch.setChecked(!mPushSwitch.isChecked());
                break;
            case R.id.btn_rate:
                ShareUtil.rate(this);
                break;
            case R.id.btn_privacy:
//                Intent intent = new Intent(this, BrowserActivity.class);
//                intent.putExtra("url", RetrofitService.NEWS_HOST + "static/index.html#/privacykuai");
//                startActivity(intent);
                break;
            case R.id.btn_about:
                break;
            case R.id.btn_logout:
                if (!AccountHelper.isLogin()) {
                    ToastUtil.showToast(getString(R.string.logouted));
                } else {
                    onLogout();
                }
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void onLogout() {
        loadingDialog.show();
        RetrofitService.getLogoutInfo()
                .subscribe(new DisposableSubscriber<BaseInfo>() {
                    @Override
                    public void onNext(BaseInfo baseInfo) {
                        if (baseInfo.getCode().equals("200")) {
                            ToastUtil.showToast(getString(R.string.logout_success));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        loadingDialog.hide();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingDialog.dismiss();
    }
}
