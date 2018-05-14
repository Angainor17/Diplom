package com.example.angai.diplom.directions.models;

import com.example.angai.diplom.home.data.BusStopApiModel;
import com.example.angai.diplom.transport.data.RouteApiModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DirectionsApiModel {

    @SerializedName("route")
    private RouteApiModel route;

    @SerializedName("busStops")
    private ArrayList<BusStopApiModel> busStopApiModels;

    public DirectionsApiModel(RouteApiModel route, ArrayList<BusStopApiModel> busStopApiModels) {
        this.route = route;
        this.busStopApiModels = busStopApiModels;
    }

    public RouteApiModel getRoute() {
        return route;
    }

    public ArrayList<BusStopApiModel> getBusStopApiModels() {
        return busStopApiModels;
    }

    public ArrayList<LatLng> getLatLngs() {
        ArrayList<LatLng> latLngs = new ArrayList<>();
        for (BusStopApiModel busStopApiModel : busStopApiModels) {
            latLngs.add(new LatLng(
                    Double.valueOf(busStopApiModel.getLatitude()),
                    Double.valueOf(busStopApiModel.getLongitude())
            ));
        }

        return latLngs;
    }
}
