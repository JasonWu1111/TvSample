package com.example.tvsample.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by JasonWu on 02/01/2018
 */

public class TabEntity implements CustomTabEntity {
    private String tabTitle;

    public TabEntity(String tabTitle){
        this.tabTitle = tabTitle;
    }


    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
