package com.diplom.angai.diplom.router.di;

import com.diplom.angai.diplom.app.di.AppComponent;
import com.diplom.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.diplom.angai.diplom.router.presentation.view.MainActivity;

import dagger.Component;

@RouterScope
@Component(dependencies = AppComponent.class, modules = {RouterModule.class})
public interface RouterComponent {

    void inject(MainActivity router);

    void inject(IRouterPresenter iRouterPresenter);
}
