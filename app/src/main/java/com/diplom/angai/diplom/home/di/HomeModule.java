package com.diplom.angai.diplom.home.di;

import com.diplom.angai.diplom.home.business.HomeInteractor;
import com.diplom.angai.diplom.home.business.IHomeInteractor;
import com.diplom.angai.diplom.home.data.HomeRepository;
import com.diplom.angai.diplom.home.data.IHomeRepository;
import com.diplom.angai.diplom.home.presentation.presenter.HomePresenter;
import com.diplom.angai.diplom.home.presentation.presenter.IHomePresenter;
import com.diplom.angai.diplom.location.ILocationRepository;
import com.diplom.angai.diplom.location.LocationRepository;

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
