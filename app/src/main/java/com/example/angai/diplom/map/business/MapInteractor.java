package com.example.angai.diplom.map.business;

import com.example.angai.diplom.app.App;

public class MapInteractor implements IMapInteractor {

    public MapInteractor() {
        App.getInjector().getMapComponent().inject(this);
    }
}
