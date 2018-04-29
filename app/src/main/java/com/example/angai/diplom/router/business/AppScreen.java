package com.example.angai.diplom.router.business;

import android.support.v4.app.Fragment;

import com.example.angai.diplom.router.business.screens.HomeScreen;
import com.example.angai.diplom.router.business.screens.MapScreen;
import com.example.angai.diplom.router.business.screens.NotificationsScreen;

public abstract class AppScreen {

    public abstract int getId();

    public abstract Fragment getFragment();

    public static AppScreen[] getList() {
        return new AppScreen[]{
                new HomeScreen(),
                new MapScreen(),
                new NotificationsScreen()
        };
    }

    public static AppScreen getById(int resId) {
        for (AppScreen appScreen : getList()) {
            if (appScreen.getId() == resId) {
                return appScreen;
            }
        }
        return getList()[0];
    }
}
