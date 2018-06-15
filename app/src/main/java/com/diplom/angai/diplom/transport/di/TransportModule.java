package com.diplom.angai.diplom.transport.di;

import com.diplom.angai.diplom.directions.data.DirectionRepository;
import com.diplom.angai.diplom.directions.data.IDirectionRepository;
import com.diplom.angai.diplom.transport.business.ITransportInteractor;
import com.diplom.angai.diplom.transport.business.TransportInteractor;
import com.diplom.angai.diplom.transport.data.ITransportRepository;
import com.diplom.angai.diplom.transport.data.TransportRepository;
import com.diplom.angai.diplom.transport.presentation.presenter.ITransportPresenter;
import com.diplom.angai.diplom.transport.presentation.presenter.TransportPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class TransportModule {

    @Provides
    ITransportRepository transportRepository() {
        return new TransportRepository();
    }

    @Provides
    ITransportInteractor transportInteractor() {
        return new TransportInteractor();
    }

    @Provides
    ITransportPresenter transportPresenter() {
        return new TransportPresenter();
    }

    @Provides
    IDirectionRepository directionRepository() {
        return new DirectionRepository();
    }
}
