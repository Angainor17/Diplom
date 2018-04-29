package com.example.angai.diplom.home.di;

import com.example.angai.diplom.app.di.AppComponent;
import com.example.angai.diplom.home.business.HomeInteractor;
import com.example.angai.diplom.home.data.HomeRepository;
import com.example.angai.diplom.home.presentation.presenter.HomePresenter;
import com.example.angai.diplom.home.presentation.view.HomeFragment;

import dagger.Component;

@HomeScope
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {

    void inject(HomeInteractor homeInteractor);

    void inject(HomeRepository homeRepository);

    void inject(HomePresenter homePresenter);

    void inject(HomeFragment homeFragment);
}
