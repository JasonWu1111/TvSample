package com.example.tvsample.module;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.example.tvsample.R;
import com.example.tvsample.adapter.MainAdapter;
import com.example.tvsample.base.BaseActivity;
import com.example.tvsample.module.category.CategoryMainFragment;
import com.example.tvsample.module.me.MeMainFragment;
import com.example.tvsample.module.recommend.RecommendMainFragment;
import com.example.tvsample.module.search.SearchMainFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

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
        List<String> titles = new ArrayList<>();
        titles.add("推薦");
        titles.add("分類");
        titles.add("搜索");
        titles.add("我的");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendMainFragment());
        fragments.add(new CategoryMainFragment());
        fragments.add(new SearchMainFragment());
        fragments.add(new MeMainFragment());
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(mainAdapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    protected void updateData() {
    }


}
