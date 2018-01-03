package com.example.tvsample.widget;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;

import com.example.tvsample.R;
import com.example.tvsample.utils.ShareUtil;
import com.example.tvsample.utils.ToastUtil;
import com.flyco.dialog.widget.base.BottomBaseDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShareBottomDialog extends BottomBaseDialog<ShareBottomDialog> {

    private String mUrl;
    private String mTitle;
    private Context mContext;

    public ShareBottomDialog(Context context, String url, String title) {
        super(context);
        mContext = context;
        mUrl = url;
        mTitle = title;
    }


    @Override
    public View onCreateView() {
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.view_dialog_bottom_share, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {

    }


    private void CopyUrl(String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if (mClipboardManager != null) {
            mClipboardManager.setPrimaryClip(ClipData.newPlainText("copy", text));
        }
        ToastUtil.showToast(mContext.getString(R.string.copied));
    }


    @OnClick({R.id.btn_share_facebook, R.id.btn_share_Line, R.id.btn_share_message, R.id.btn_share_twitter, R.id.btn_copy, R.id.btn_complaint, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share_facebook:
                ShareUtil.shareLink(mContext, mUrl, mTitle, "facebook");
                dismiss();
                break;
            case R.id.btn_share_Line:
                ShareUtil.shareLink(mContext, mUrl, mTitle, "facebook");
                dismiss();
                break;
            case R.id.btn_share_message:
                ShareUtil.shareLink(mContext, mUrl, mTitle, "facebook");
                dismiss();
                break;
            case R.id.btn_share_twitter:
                ShareUtil.shareLink(mContext, mUrl, mTitle, "facebook");
                dismiss();
                break;
            case R.id.btn_copy:
                CopyUrl(mUrl);
                dismiss();
                break;
            case R.id.btn_complaint:
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

}
