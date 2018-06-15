package com.diplom.angai.diplom.map.business;

import android.location.Location;

import com.diplom.angai.diplom.map.data.MapPointsRequestParams;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMapInteractor {

    Single<Location> getUserLocation();

    Location getDefaultLocation();

    Single<ArrayList<LatLng>> getDirection(RouteDirection routeDirection);

    Observable<ArrayList<MapTransport>> getTransportMap(MapPointsRequestParams params);

    void unSubscribeFromMapUpdates();

}
