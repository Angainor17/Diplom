package com.example.angai.diplom.map.di;

import com.example.angai.diplom.location.ILocationRepository;
import com.example.angai.diplom.location.LocationRepository;
import com.example.angai.diplom.map.business.IMapInteractor;
import com.example.angai.diplom.map.business.MapInteractor;
import com.example.angai.diplom.map.data.IMapRepository;
import com.example.angai.diplom.map.data.MapRepository;
import com.example.angai.diplom.map.presentation.presenter.IMapPresenter;
import com.example.angai.diplom.map.presentation.presenter.MapPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MapModule {

    @Provides
    IMapPresenter mapPresenter() {
        return new MapPresenter();
    }

    @Provides
    IMapInteractor mapInteractor() {
        return new MapInteractor();
    }

    @Provides
    IMapRepository mapRepository() {
        return new MapRepository();
    }

    @Provides
    ILocationRepository locationRepository() {
        return new LocationRepository();
    }
}
