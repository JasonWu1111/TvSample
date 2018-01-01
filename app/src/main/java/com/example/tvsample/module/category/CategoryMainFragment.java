package com.example.tvsample.module.category;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvsample.R;
import com.example.tvsample.adapter.MainAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.module.CategoryListFragment;
import com.example.tvsample.module.me.MeMainFragment;
import com.example.tvsample.module.recommend.RecommendMainFragment;
import com.example.tvsample.module.search.SearchMainFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by JasonWu on 2018/1/1
 */

public class CategoryMainFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private MainAdapter mMainAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category_main;
    }

    @Override
    protected void initViews() {
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("精選");
        titles.add("台劇");
        titles.add("大陸劇");
        titles.add("港劇");
        titles.add("韓劇");
        titles.add("美劇");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CategoryListFragment());
        fragments.add(new CategoryListFragment());
        fragments.add(new CategoryListFragment());
        fragments.add(new CategoryListFragment());
        fragments.add(new CategoryListFragment());
        fragments.add(new CategoryListFragment());
        fragments.add(new CategoryListFragment());
        MainAdapter mainAdapter = new MainAdapter(getChildFragmentManager(), titles, fragments);
        viewPager.setAdapter(mainAdapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    protected void updateData() {

    }
}
