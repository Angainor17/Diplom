package com.diplom.angai.diplom.router.business.screens;

import android.support.v4.app.Fragment;

import com.diplom.angai.diplom.R;
import com.diplom.angai.diplom.home.presentation.view.HomeFragment_;
import com.diplom.angai.diplom.router.business.AppScreen;

public class HomeScreen extends AppScreen {

    @Override
    public int getId() {
        return R.id.navigation_home;
    }

    @Override
    public Fragment getFragment() {
        return new HomeFragment_();
    }

    @Override
    public String toString() {
        return "HomeScreen";
    }
}
