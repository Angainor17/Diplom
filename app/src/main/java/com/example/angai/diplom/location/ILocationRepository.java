package com.example.angai.diplom.location;

import android.location.Location;

import com.example.angai.diplom.transport.data.RouteApiModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ILocationRepository {

    Observable<Location> getCurrentLocation();

    Single<ArrayList<RouteApiModel>> sendLocation(Location location);

    void cancelSendLocation();
}
