package com.example.angai.diplom.home.business;

import android.location.Location;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.data.BusStopApiModel;
import com.example.angai.diplom.home.data.IHomeRepository;
import com.example.angai.diplom.location.ILocationRepository;
import com.example.angai.diplom.map.business.RouteDirection;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class HomeInteractor implements IHomeInteractor {

    @Inject
    IHomeRepository homeRepository;

    @Inject
    ILocationRepository myLocationRepository;

    public HomeInteractor() {
        App.getInjector().getHomeComponent().inject(this);
    }

    @Override
    public Single<BusStop[]> getBusStops() {
        return homeRepository.getBusStopList().map(busStopApiModels -> {
                    if (busStopApiModels != null) {
                        return BusStop.getArray(busStopApiModels.toArray(
                                new BusStopApiModel[busStopApiModels.size()])
                        );
                    }
                    return new BusStop[0];
                }
        );
    }

    @Override
    public Observable<Location> getUserLocation() {
        return myLocationRepository.getCurrentLocation();
    }

    @Override
    public Single<BusStop> getClosestBusStop() {
        return Single.zip(getBusStops(), getUserLocation().first(new Location("")), (busStops, location) -> {
            BusStop closestBusStop = busStops[0];
            double minDistance = closestBusStop.getDistanceTo(location);

            for (BusStop busStop : busStops) {
                float newDistance = busStop.getDistanceTo(location);
                if (newDistance < minDistance) {
                    closestBusStop = busStop;
                    minDistance = newDistance;
                }
            }
            return closestBusStop;
        });
    }

    @Override
    public void setRouteDirection(RouteDirection routeDirection) {
        homeRepository.saveRouteDirection(routeDirection);
    }
}
