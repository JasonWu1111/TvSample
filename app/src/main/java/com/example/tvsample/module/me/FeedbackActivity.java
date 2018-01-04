package com.example.tvsample.module.me;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseActivity;
import com.example.tvsample.widget.SimpleTextWatcher;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.title)
    public TextView toolbar;
    @BindView(R.id.btn_clear)
    public TextView btnClear;
    @BindView(R.id.feedback_text)
    EditText feedbackText;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.text_num)
    TextView textNum;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private boolean isEmailNull = true;
    private boolean isFeedbackNull = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_feedback;
    }


    @Override
    protected void initViews() {
        toolbar.setText(getString(R.string.feedback));
        btnClear.setVisibility(View.GONE);

        feedbackText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                textNum.setText(s.length() + "/50");
                isFeedbackNull = s.length() == 0;
                changeButtonUI();
            }
        });

        email.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                isEmailNull = s.length() == 0;
                changeButtonUI();
            }
        });
    }

    @Override
    protected void updateData() {

    }

    public void changeButtonUI() {
        if (!isEmailNull && !isFeedbackNull) {
            btnSubmit.setBackgroundResource(R.drawable.frame_long_blue);
            btnSubmit.setClickable(true);
        } else {
            btnSubmit.setBackgroundResource(R.drawable.frame_long_grey);
            btnSubmit.setClickable(false);
        }
    }


    @OnClick({R.id.btn_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_submit:
                btnSubmit.setClickable(false);
                String suggestion = feedbackText.getText().toString();
                String emailText = email.getText().toString();
                break;
        }
    }
}
