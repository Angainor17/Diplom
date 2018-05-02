package com.example.angai.diplom.home.di;

import com.example.angai.diplom.home.business.HomeInteractor;
import com.example.angai.diplom.home.business.IHomeInteractor;
import com.example.angai.diplom.home.data.HomeRepository;
import com.example.angai.diplom.home.data.IHomeRepository;
import com.example.angai.diplom.home.data.IMyLocationRepository;
import com.example.angai.diplom.home.data.MyLocationRepository;
import com.example.angai.diplom.home.presentation.presenter.HomePresenter;
import com.example.angai.diplom.home.presentation.presenter.IHomePresenter;

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
    IMyLocationRepository locationRepository() {
        return new MyLocationRepository();
    }

}
