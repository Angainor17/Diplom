package com.example.angai.diplom.router.business.screens;

import android.support.v4.app.Fragment;

import com.example.angai.diplom.R;
import com.example.angai.diplom.home.presentation.view.HomeFragment;
import com.example.angai.diplom.router.business.AppScreen;

public class HomeScreen extends AppScreen {

    @Override
    public int getId() {
        return  R.id.navigation_home;
    }

    @Override
    public Fragment getFragment() {
        return new HomeFragment();
    }
}
