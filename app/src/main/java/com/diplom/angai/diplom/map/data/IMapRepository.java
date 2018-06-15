package com.diplom.angai.diplom.map.data;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IMapRepository {

    Single<ArrayList<LatLng>> getDirection(Location locationStart, Location locationStop);

    Observable<ArrayList<MapPointApiModel>> getMapPoints(MapPointsRequestParams params);

    void unsubscribeFromMapPoints();

}
