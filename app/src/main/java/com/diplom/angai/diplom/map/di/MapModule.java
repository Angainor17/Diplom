package com.diplom.angai.diplom.map.di;

import com.diplom.angai.diplom.location.ILocationRepository;
import com.diplom.angai.diplom.location.LocationRepository;
import com.diplom.angai.diplom.map.business.IMapInteractor;
import com.diplom.angai.diplom.map.business.MapInteractor;
import com.diplom.angai.diplom.map.data.IMapRepository;
import com.diplom.angai.diplom.map.data.MapRepository;
import com.diplom.angai.diplom.map.presentation.presenter.IMapPresenter;
import com.diplom.angai.diplom.map.presentation.presenter.MapPresenter;

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
