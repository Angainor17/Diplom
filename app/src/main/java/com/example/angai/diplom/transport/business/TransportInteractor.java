package com.example.angai.diplom.transport.business;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.directions.data.IDirectionRepository;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.home.data.BusStopApiModel;
import com.example.angai.diplom.transport.data.ITransportRepository;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Single;

public class TransportInteractor implements ITransportInteractor {

    @Inject
    ITransportRepository transportRepository;

    @Inject
    IDirectionRepository directionRepository;

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

    @Override
    public Single<ArrayList<LatLng>> getRoutePoints(Route route) {
        return directionRepository.getDirection("" + route.getId());
    }

    @Override
    public Single<ArrayList<BusStop>> getRouteBusStops(Route route) {
        return directionRepository.getBusStops(route.getId()).map(
                busStopApiModels -> {
                    ArrayList<BusStop> busStops = new ArrayList<>();

                    for (BusStopApiModel busStopApiModel : busStopApiModels) {
                        busStops.add(new BusStop(busStopApiModel));
                    }

                    return busStops;
                }
        );
    }
}
