package com.example.angai.diplom.home.data;

import android.location.Location;

import io.reactivex.Observable;

public interface ILocationRepository {

    Observable<Location> getCurrentLocation();

}
