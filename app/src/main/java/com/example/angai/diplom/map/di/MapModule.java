package com.example.angai.diplom.map.di;

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
}
