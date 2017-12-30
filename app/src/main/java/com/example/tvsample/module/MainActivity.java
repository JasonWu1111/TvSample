package com.example.tvsample.module;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.example.tvsample.R;
import com.example.tvsample.base.BaseActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.demo.youtubeplayer.YouTubePlayerActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    boolean showAudioUi;

    private static String VIDEO_ID = "iS1g8G_njx8";

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
//        List<String> titles = new ArrayList<>();
//        titles.add("推薦");
//        titles.add("分類");
//        titles.add("搜索");
//        titles.add("我的");
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new RecommendMainFragment());
//        fragments.add(new RecommendMainFragment());
//        fragments.add(new SearchMainFragment());
//        fragments.add(new MeMainFragment());
//        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), titles, fragments);
//        viewPager.setAdapter(mainAdapter);
//        tabLayout.setViewPager(viewPager);

        showAudioUi = true;

        Intent intent = new Intent(MainActivity.this, YouTubePlayerActivity.class);
        intent.putExtra(YouTubePlayerActivity.EXTRA_VIDEO_ID, VIDEO_ID);
        intent.putExtra(YouTubePlayerActivity.EXTRA_SHOW_AUDIO_UI, showAudioUi);
        intent.putExtra(YouTubePlayerActivity.EXTRA_HANDLE_ERROR, true);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void updateData() {
    }


}
