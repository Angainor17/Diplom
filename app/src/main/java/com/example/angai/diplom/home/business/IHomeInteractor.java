package com.example.angai.diplom.home.business;

import android.location.Location;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IHomeInteractor {

    Single<BusStop[]> getBusStops();

    Observable<Location> getUserLocation();

    Single<BusStop> getClosestBusStop();

}
