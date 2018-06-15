package com.diplom.angai.diplom.transport.di;

import com.diplom.angai.diplom.app.di.AppComponent;
import com.diplom.angai.diplom.transport.business.TransportInteractor;
import com.diplom.angai.diplom.transport.data.TransportRepository;
import com.diplom.angai.diplom.transport.presentation.presenter.TransportPresenter;
import com.diplom.angai.diplom.transport.presentation.view.TransportFragment;

import dagger.Component;

@TransportScope
@Component(dependencies = AppComponent.class, modules = TransportModule.class)
public interface TransportComponent {

    void inject(TransportFragment transportFragment);

    void inject(TransportInteractor transportInteractor);

    void inject(TransportPresenter transportPresenter);

    void inject(TransportRepository transportRepository);
}
