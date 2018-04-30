package com.example.angai.diplom.transport.di;

import com.example.angai.diplom.app.di.AppComponent;
import com.example.angai.diplom.transport.business.TransportInteractor;
import com.example.angai.diplom.transport.data.TransportRepository;
import com.example.angai.diplom.transport.presentation.presenter.TransportPresenter;
import com.example.angai.diplom.transport.presentation.view.TransportFragment;

import dagger.Component;

@TransportScope
@Component(dependencies = AppComponent.class, modules = TransportModule.class)
public interface TransportComponent {

    void inject(TransportFragment transportFragment);

    void inject(TransportInteractor transportInteractor);

    void inject(TransportPresenter transportPresenter);

    void inject(TransportRepository transportRepository);
}
