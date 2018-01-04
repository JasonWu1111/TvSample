package com.example.tvsample.module.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvsample.R;
import com.example.tvsample.adapter.EpisodeAdapter;
import com.example.tvsample.entity.VideoDetailEntity;
import com.example.tvsample.entity.VideoDetailInfo;
import com.example.tvsample.utils.AssetsHelper;
import com.example.tvsample.utils.AudioUtil;
import com.example.tvsample.widget.ShareBottomDialog;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//todo YoutubePlayerActivity代码过多，可考虑替换成YoutubePlayerFragment
public class YouTubePlayerActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener,
        YouTubePlayer.OnFullscreenListener,
        YouTubePlayer.PlayerStateChangeListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public static final String VIDEO_ID = "video_id";
    public static final String START_INDEX = "start_index";

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView playerView;
    @BindView(R.id.episode_recycler_view)
    RecyclerView episodeRecyclerView;
    @BindView(R.id.btn_open_intro)
    TextView btnOpenIntro;
    @BindView(R.id.btn_close_intro)
    ImageView btnCloseIntro;
    @BindView(R.id.btn_open_episode)
    TextView btnOpenEpisode;
    @BindView(R.id.btn_close_episode)
    ImageView btnCloseEpisode;
    @BindView(R.id.favorite_container)
    FrameLayout favoriteContainer;
    @BindView(R.id.intro)
    TextView introText;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tags)
    TextView tags;
    @BindView(R.id.comment_count)
    TextView commentCount;
    @BindView(R.id.btn_favorite)
    ImageView btnFavorite;

    private String googleApiKey = "AIzaSyBTZiYLfxjewjFvVn4rO_Bk6nsCg2R797o";
    private int startIndex;
    private int mCurTimeMillis = 0;

    private YouTubePlayer mPlayer;
    private boolean isFullScreen = false;

    private EpisodeAdapter mEpisodeAdapter;
    private int episodes;

    private List<String> listId;

    public static void launch(Context context, String videoId, int startIndex) {
        Intent intent = new Intent(context, YouTubePlayerActivity.class);
        intent.putExtra(VIDEO_ID, videoId);
        intent.putExtra(START_INDEX, startIndex);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        //初始化playerView
        startIndex = getIntent().getIntExtra(START_INDEX, 1);
        playerView.initialize(googleApiKey, this);

        VideoDetailInfo videoDetailInfo = new Gson().fromJson(AssetsHelper.readData(this, "test/videoDetail.json"), VideoDetailInfo.class);
        VideoDetailEntity videoDetailEntity = videoDetailInfo.getData();

        episodes = videoDetailEntity.getEpisode();
        title.setText(videoDetailEntity.getTitle());
        StringBuffer sb = new StringBuffer();
        for(String tag : videoDetailEntity.getTags()){
            sb.append(tag).append("·");
        }
        sb.append("全").append(String.valueOf(episodes)).append("集").append("·")
                .append(String.valueOf(videoDetailEntity.getViewCount())).append("次播放");
        tags.setText(sb);
        btnFavorite.setSelected(videoDetailEntity.getIsFavorite() == 1);
        introText.setText(videoDetailEntity.getDescription());
        btnOpenEpisode.setText("全" + episodes + "集 >");

        mEpisodeAdapter = new EpisodeAdapter(this, episodes, startIndex);
        if (episodes > 6) {
            mEpisodeAdapter.setEpisodes(6);
        }

        listId = new ArrayList<>();
        for (VideoDetailEntity.DetailListBean bean : videoDetailInfo.getData().getDetailList()) {
            listId.add(bean.getVideoId());
        }

        mEpisodeAdapter.setOnItemClickListener((position, action, data) -> mPlayer.loadVideos(listId, position, 0));
        episodeRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        episodeRecyclerView.setAdapter(mEpisodeAdapter);
        episodeRecyclerView.setNestedScrollingEnabled(false);

        getFragmentManager().beginTransaction().add(R.id.favorite_container, new PlayerFavoriteFragment()).commit();
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
            mPlayer.loadVideos(listId, startIndex, mCurTimeMillis);
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
//        StatusBarUtil.hide(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            AudioUtil.adjustMusicVolume(getApplicationContext(), true);
//            StatusBarUtil.hide(this);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            AudioUtil.adjustMusicVolume(getApplicationContext(), false);
//            StatusBarUtil.hide(this);
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

    @OnClick({R.id.btn_open_intro, R.id.btn_close_intro, R.id.btn_open_episode, R.id.btn_close_episode, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_open_intro:
                btnOpenIntro.setVisibility(View.GONE);
                btnCloseIntro.setVisibility(View.VISIBLE);
                introText.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_close_intro:
                btnOpenIntro.setVisibility(View.VISIBLE);
                btnCloseIntro.setVisibility(View.GONE);
                introText.setVisibility(View.GONE);
                break;
            case R.id.btn_open_episode:
                btnOpenEpisode.setVisibility(View.GONE);
                btnCloseEpisode.setVisibility(View.VISIBLE);
                mEpisodeAdapter.setEpisodes(episodes);
                break;
            case R.id.btn_close_episode:
                btnOpenEpisode.setVisibility(View.VISIBLE);
                btnCloseEpisode.setVisibility(View.GONE);
                mEpisodeAdapter.setEpisodes(6);
                break;
            case R.id.btn_share:
                new ShareBottomDialog(YouTubePlayerActivity.this, null, null).show();
                break;
        }
    }
}
