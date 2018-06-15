package com.diplom.angai.diplom.router.business.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.diplom.angai.diplom.R;
import com.diplom.angai.diplom.map.presentation.view.MapFragment_;
import com.diplom.angai.diplom.map.presentation.view.MapScreenParams;
import com.diplom.angai.diplom.router.business.AppScreen;
import com.google.gson.Gson;

public class MapScreen extends AppScreen {

    @Override
    public int getId() {
        return R.id.navigation_map;
    }

    @Override
    public Fragment getFragment() {
        return new MapFragment_();
    }

    @Override
    public String toString() {
        return "MapScreen";
    }

    public static Bundle getBundle(MapScreenParams mapScreenParams) {
        Bundle bundle = new Bundle();
        bundle.putString("param", new Gson().toJson(mapScreenParams));

        return bundle;
    }
}
