package com.example.angai.diplom.location;

import android.location.Location;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ILocationRepository {

    Observable<Location> getCurrentLocation();

    Completable sendLocation(Location location);

    void cancelSendLocation();
}
