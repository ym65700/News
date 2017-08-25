package com.ming.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by Administrator on 2016/9/16.
 */
public class TabEntity implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;


    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.selectedIcon = selectedIcon;
        this.title = title;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }


    @Override

    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
