package com.diplom.angai.diplom.home.di;

import com.diplom.angai.diplom.app.di.AppComponent;
import com.diplom.angai.diplom.home.business.HomeInteractor;
import com.diplom.angai.diplom.home.data.HomeRepository;
import com.diplom.angai.diplom.home.presentation.presenter.HomePresenter;
import com.diplom.angai.diplom.home.presentation.view.HomeFragment;

import dagger.Component;

@HomeScope
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {

    void inject(HomeInteractor homeInteractor);

    void inject(HomeRepository homeRepository);

    void inject(HomePresenter homePresenter);

    void inject(HomeFragment homeFragment);
}
