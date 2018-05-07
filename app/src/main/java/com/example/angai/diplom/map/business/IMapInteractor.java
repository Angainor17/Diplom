package com.example.angai.diplom.map.business;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Single;

public interface IMapInteractor {

    Single<Location> getUserLocation();

    Location getDefaultLocation();

    Single<ArrayList<LatLng>> getDirection(RouteDirection routeDirection);

}
