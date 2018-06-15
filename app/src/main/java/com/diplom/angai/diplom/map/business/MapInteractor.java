package com.diplom.angai.diplom.map.business;

import android.location.Location;

import com.diplom.angai.diplom.app.App;
import com.diplom.angai.diplom.location.ILocationRepository;
import com.diplom.angai.diplom.map.data.IMapRepository;
import com.diplom.angai.diplom.map.data.MapPointApiModel;
import com.diplom.angai.diplom.map.data.MapPointsRequestParams;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MapInteractor implements IMapInteractor {

    @Inject
    IMapRepository mapRepository;

    @Inject
    ILocationRepository locationRepository;

    public MapInteractor() {
        App.getInjector().getMapComponent().inject(this);
    }

    @Override
    public Observable<ArrayList<MapTransport>> getTransportMap(MapPointsRequestParams params) {
        return mapRepository.getMapPoints(params).map(mapPointApiModels -> {
            ArrayList<MapTransport> mapTransports = new ArrayList<>();
            for (MapPointApiModel mapPointApiModel : mapPointApiModels) {
                mapTransports.add(new MapTransport(mapPointApiModel));
            }

            return mapTransports;
        });
    }

    @Override
    public void unSubscribeFromMapUpdates() {
        mapRepository.unsubscribeFromMapPoints();
    }

    @Override
    public Single<Location> getUserLocation() {
        return locationRepository.getCurrentLocation()
                .first(getDefaultLocation());
    }

    @Override
    public Location getDefaultLocation() {
        Location location = new Location("");
        location.setLatitude(44.5883671);
        location.setLongitude(33.4800674);
        return location;
    }

    @Override
    public Single<ArrayList<LatLng>> getDirection(RouteDirection routeDirection) {
        return mapRepository.getDirection(
                routeDirection.getStartLocation(),
                routeDirection.getStopLocation())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread());
    }
}
