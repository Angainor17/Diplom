package com.example.angai.diplom.map.business;

import android.location.Location;

import io.reactivex.Single;

public interface IMapInteractor {

    Single<Location> getUserLocation();

    Location getDefaultLocation();

}
