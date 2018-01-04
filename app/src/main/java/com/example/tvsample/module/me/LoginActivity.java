package com.example.tvsample.module.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseActivity;
import com.example.tvsample.entity.FBLoginEntity;
import com.example.tvsample.utils.GoogleServicesUtil;
import com.example.tvsample.utils.ToastUtil;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity {
    private static final int GOOGLE_LOGIN = 1;

    @BindView(R.id.btn_check)
    Button btnCheck;

    private CallbackManager mCallbackManager;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        mCallbackManager = CallbackManager.Factory.create();

        if (GoogleServicesUtil.isGooglePlayServiceAvailable(this)) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, connectionResult -> Log.d(getTAG(), "-------------------" + connectionResult))
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }

        btnCheck.setSelected(true);
    }

    @Override
    protected void updateData() {}

    @OnClick({R.id.btn_close, R.id.btn_facebook_login, R.id.btn_google_login, R.id.btn_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                break;
            case R.id.btn_facebook_login:
                startFacebookLogin();
                break;
            case R.id.btn_google_login:
                startGoogleLogin();
                break;
            case R.id.btn_check:
                btnCheck.setSelected(!btnCheck.isSelected());
                break;
        }
    }

    public void startFacebookLogin() {
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookLogin(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        ToastUtil.showToast(getString(R.string.system_error));
                    }
                });
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }

    public void handleFacebookLogin(final AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, (object, response) -> {
            if (object != null) {
                Log.e(getTAG(), "----------------------------" + object.toString());
                FBLoginEntity fbLoginEntity = new Gson().fromJson(object.toString(), FBLoginEntity.class);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,link,gender,birthday,email,picture,locale,updated_time" +
                ",timezone,age_range,first_name,last_name");
        request.setParameters(bundle);
        request.executeAsync();
    }

    public void startGoogleLogin() {
        if (GoogleServicesUtil.isGooglePlayServiceAvailableForToast(this)) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, GOOGLE_LOGIN);
        }
    }

    public void handleGoogleLogin(GoogleSignInResult result) {
        if (result != null && result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            if (acct != null) {
                JSONObject object = new JSONObject();
                RequestBody body = RequestBody.create(MediaType.parse("application/json"), object.toString());
            }
        } else {
            ToastUtil.showToast(getString(R.string.system_error));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GOOGLE_LOGIN:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleGoogleLogin(result);
                break;
        }
    }
}
