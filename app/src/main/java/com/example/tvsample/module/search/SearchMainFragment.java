package com.example.tvsample.module.search;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.tvsample.R;
import com.example.tvsample.adapter.MainAdapter;
import com.example.tvsample.base.BaseFragment;
import com.example.tvsample.module.category.CategoryListFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JasonWu on 28/12/2017
 */

public class SearchMainFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_search_main;
    }

    @Override
    protected void initViews() {
        getChildFragmentManager().beginTransaction().add(R.id.search_common_container, new SearchCommonFragment()).commit();

        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("精選");
        titles.add("台劇");
        titles.add("大陸劇");
        titles.add("港劇");
        titles.add("韓劇");
        titles.add("美劇");
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new CategoryListFragment());
        }
        MainAdapter mainAdapter = new MainAdapter(getChildFragmentManager(), titles, fragments);
        viewPager.setAdapter(mainAdapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    protected void updateData() {
    }


    @OnClick(R.id.search_text)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }
}
