package com.example.angai.diplom.directions.data;

import com.example.angai.diplom.directions.api.DirectionApi;
import com.example.angai.diplom.directions.models.LatLngApiModel;
import com.example.angai.diplom.home.data.BusStopApiModel;
import com.example.angai.diplom.utils.CustomRetrofit;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DirectionRepository implements IDirectionRepository {

    private DirectionApi directionApi;

    public DirectionRepository() {
        directionApi = CustomRetrofit.get().create(DirectionApi.class);
    }

    @Override
    public Single<ArrayList<LatLng>> getDirection(String routeId) {
        return directionApi.getRoutePoints(routeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(latLngApiModels -> {
                    ArrayList<LatLng> latLngs = new ArrayList<>();
                    for (LatLngApiModel latLngApiModel : latLngApiModels) {
                        latLngs.add(latLngApiModel.getLatLng());
                    }
                    return latLngs;
                });
    }

    @Override
    public Single<ArrayList<BusStopApiModel>> getBusStops(int routeId) {
        return directionApi.getBusStops(routeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
