package com.diplom.angai.diplom.router.di;

import com.diplom.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.diplom.angai.diplom.router.presentation.presenter.RouterPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class RouterModule {

    @Provides
    IRouterPresenter getRouterPresenter() {
        return new RouterPresenter();
    }
}
