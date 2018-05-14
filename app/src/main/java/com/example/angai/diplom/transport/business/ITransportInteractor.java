package com.example.angai.diplom.transport.business;

import com.example.angai.diplom.home.business.BusStop;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Single;

public interface ITransportInteractor {

    Single<Route[]> getAllRoutes();

    Single<ArrayList<LatLng>> getRoutePoints(Route route);

    Single<ArrayList<BusStop>> getRouteBusStops(Route route);

}
