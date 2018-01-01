package com.example.tvsample.module.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.tvsample.R;
import com.example.tvsample.utils.AudioUtil;
import com.example.tvsample.utils.StatusBarUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YouTubePlayerActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener,
        YouTubePlayer.OnFullscreenListener,
        YouTubePlayer.PlayerStateChangeListener {

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView playerView;

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public static final String VIDEO_ID = "video_id";
    public static final String PLAYLIST_ID = "playList_id";
    public static final String START_INDEX = "start_index";

    private String googleApiKey = "AIzaSyBTZiYLfxjewjFvVn4rO_Bk6nsCg2R797o";
    private String videoId;
    private String playListId;
    private int startIndex;
    private int mCurTimeMillis;

    private YouTubePlayer mPlayer;
    private boolean isFullScreen = false;

    public static void launch(Context context, String videoId) {
        Intent intent = new Intent(context, YouTubePlayerActivity.class);
        intent.putExtra(VIDEO_ID, videoId);
        context.startActivity(intent);
    }

    public static void launch(Context context, String playListId, int startIndex) {
        Intent intent = new Intent(context, YouTubePlayerActivity.class);
        intent.putExtra(PLAYLIST_ID, playListId);
        intent.putExtra(START_INDEX, startIndex);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        ButterKnife.bind(this);

        videoId = getIntent().getStringExtra(VIDEO_ID);
        playListId = getIntent().getStringExtra(PLAYLIST_ID);
        startIndex = getIntent().getIntExtra(START_INDEX, 1);

        playerView.initialize(googleApiKey, this);
        playerView.setBackgroundResource(android.R.color.black);
        StatusBarUtil.hide(this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        mPlayer = player;
        mPlayer.setOnFullscreenListener(this);
        mPlayer.setPlayerStateChangeListener(this);

        mPlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
                | YouTubePlayer.FULLSCREEN_FLAG_CONTROL_SYSTEM_UI);

        mPlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        if (!wasRestored) {
//            mPlayer.loadVideo(videoId);
            mPlayer.loadPlaylist(playListId, startIndex, 0);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, errorReason.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            playerView.initialize(googleApiKey, this);
        }
    }

    @Override
    public void onFullscreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }


    @Override
    public void onError(ErrorReason reason) {
        Log.e("onError", "onError : " + reason.name());
    }

    @Override
    public void onAdStarted() {
    }

    @Override
    public void onLoaded(String videoId) {
    }

    @Override
    public void onLoading() {
    }

    @Override
    public void onVideoEnded() {
    }

    @Override
    public void onVideoStarted() {
        StatusBarUtil.hide(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            AudioUtil.adjustMusicVolume(getApplicationContext(), true);
            StatusBarUtil.hide(this);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            AudioUtil.adjustMusicVolume(getApplicationContext(), false);
            StatusBarUtil.hide(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (isFullScreen) {
            mPlayer.setFullscreen(false);
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(mPlayer != null){
//            playerView.invalidate();
        playerView.initialize(googleApiKey, this);
//            mPlayer.loadPlaylist(PLAYLIST_ID, 0, mCurTimeMillis);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer != null) {
            mPlayer.pause();
            mCurTimeMillis = mPlayer.getCurrentTimeMillis();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
