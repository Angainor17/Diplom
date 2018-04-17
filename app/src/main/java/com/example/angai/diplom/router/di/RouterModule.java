package com.example.angai.diplom.router.di;

import com.example.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.example.angai.diplom.router.presentation.presenter.RouterPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class RouterModule {

    @Provides
    IRouterPresenter getRouterPresenter() {
        return new RouterPresenter();
    }
}
