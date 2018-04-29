package com.example.angai.diplom.router.business.screens;

import android.support.v4.app.Fragment;

import com.example.angai.diplom.R;
import com.example.angai.diplom.map.presentation.view.MapFragment_;
import com.example.angai.diplom.router.business.AppScreen;

public class MapScreen extends AppScreen {

    @Override
    public int getId() {
        return R.id.navigation_map;
    }

    @Override
    public Fragment getFragment() {
        return new MapFragment_();
    }
}
