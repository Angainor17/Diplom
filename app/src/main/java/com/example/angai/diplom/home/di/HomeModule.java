package com.example.angai.diplom.home.di;

import com.example.angai.diplom.home.business.HomeInteractor;
import com.example.angai.diplom.home.business.IHomeInteractor;
import com.example.angai.diplom.home.data.HomeRepository;
import com.example.angai.diplom.home.data.IHomeRepository;
import com.example.angai.diplom.home.presentation.presenter.HomePresenter;
import com.example.angai.diplom.home.presentation.presenter.IHomePresenter;
import com.example.angai.diplom.location.ILocationRepository;
import com.example.angai.diplom.location.LocationRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    IHomeInteractor homeInteractor() {
        return new HomeInteractor();
    }

    @Provides
    IHomeRepository homeRepository() {
        return new HomeRepository();
    }

    @Provides
    IHomePresenter homePresenter() {
        return new HomePresenter();
    }

    @Provides
    ILocationRepository locationRepository() {
        return new LocationRepository();
    }

}
