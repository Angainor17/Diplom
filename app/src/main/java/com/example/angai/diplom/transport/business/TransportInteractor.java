package com.example.angai.diplom.transport.business;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.transport.data.ITransportRepository;

import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Single;

public class TransportInteractor implements ITransportInteractor {

    @Inject
    ITransportRepository transportRepository;

    public TransportInteractor() {
        App.getInjector().getTransportComponent().inject(this);
    }

    @Override
    public Single<Route[]> getAllRoutes() {
        return transportRepository.getAllBusRoutes().map(routeApiModels -> {
            Route[] result = new Route[routeApiModels.size()];
            Collections.sort(
                    routeApiModels,
                    (o1, o2) -> Integer.valueOf(o1.getName()).compareTo(Integer.valueOf(o2.getName()))
            );
            for (int i = 0; i < routeApiModels.size(); i++) {
                result[i] = new Route(routeApiModels.get(i));
            }
            return result;
        });
    }
}
