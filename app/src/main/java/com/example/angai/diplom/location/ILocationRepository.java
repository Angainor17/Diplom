package com.example.angai.diplom.location;

import android.location.Location;

import io.reactivex.Observable;

public interface ILocationRepository {

    Observable<Location> getCurrentLocation();

}