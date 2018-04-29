package com.example.angai.diplom.router.di;

import com.example.angai.diplom.app.di.AppComponent;
import com.example.angai.diplom.router.presentation.presenter.IRouterPresenter;
import com.example.angai.diplom.router.presentation.view.IRouter;

import dagger.Component;

@RouterScope
@Component(dependencies = AppComponent.class, modules = {RouterModule.class})
public interface RouterComponent {

    void inject(IRouter router);

    void inject(IRouterPresenter iRouterPresenter);
}
