package com.example.angai.diplom.router.business;

import android.support.v4.app.Fragment;

import com.example.angai.diplom.router.business.screens.HomeScreen;
import com.example.angai.diplom.router.business.screens.MapScreen;
import com.example.angai.diplom.router.business.screens.NotificationsScreen;
import com.example.angai.diplom.router.business.screens.TransportScreen;

public abstract class AppScreen {

    public abstract int getId();

    public abstract Fragment getFragment();

    public abstract String toString();

    public static AppScreen[] getList() {
        return new AppScreen[]{
                new HomeScreen(),
                new MapScreen(),
                new NotificationsScreen(),
                new TransportScreen()
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
