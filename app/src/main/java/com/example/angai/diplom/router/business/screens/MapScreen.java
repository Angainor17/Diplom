package com.example.angai.diplom.router.business.screens;

import android.support.v4.app.Fragment;

import com.example.angai.diplom.R;
import com.example.angai.diplom.router.business.AppScreen;
import com.google.android.gms.maps.SupportMapFragment;

public class MapScreen extends AppScreen {

    @Override
    public int getId() {
        return R.id.navigation_map;
    }

    @Override
    public Fragment getFragment() {
//        return new MapFragment();
        return new SupportMapFragment();
    }
}
