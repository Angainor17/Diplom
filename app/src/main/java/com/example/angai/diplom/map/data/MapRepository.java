package com.example.angai.diplom.map.data;

import android.content.Context;

import com.example.angai.diplom.app.App;

import javax.inject.Inject;

public class MapRepository implements IMapRepository {

    @Inject
    Context context;

    public MapRepository() {
        App.getInjector().getMapComponent().inject(this);
    }
}
