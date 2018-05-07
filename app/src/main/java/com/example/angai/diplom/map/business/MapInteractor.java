package com.example.angai.diplom.map.business;

import android.location.Location;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.location.ILocationRepository;
import com.example.angai.diplom.map.data.IMapRepository;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import javax.inject.Inject;

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
