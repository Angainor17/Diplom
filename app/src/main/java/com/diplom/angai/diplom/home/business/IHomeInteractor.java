package com.diplom.angai.diplom.home.business;

import android.location.Location;

import com.diplom.angai.diplom.map.business.RouteDirection;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IHomeInteractor {

    Single<BusStop[]> getBusStops();

    Observable<Location> getUserLocation();

    Single<BusStop> getClosestBusStop();

    void setRouteDirection(RouteDirection routeDirection);

}
