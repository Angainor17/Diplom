package com.example.angai.diplom.directions.data;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Single;

public interface IDirectionRepository {

    Single<ArrayList<LatLng>> getDirection(String routeId);

}
