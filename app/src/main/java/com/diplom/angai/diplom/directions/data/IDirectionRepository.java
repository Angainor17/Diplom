package com.diplom.angai.diplom.directions.data;

import com.diplom.angai.diplom.home.data.BusStopApiModel;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Single;

public interface IDirectionRepository {

    Single<ArrayList<LatLng>> getDirection(String routeId);

    Single<ArrayList<BusStopApiModel>> getBusStops(int routeId);

}
