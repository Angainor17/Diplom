package com.example.angai.diplom.directions.models;

import com.example.angai.diplom.map.data.CoordinatesModel;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class DirectionsBodyModel {

    private String routeId;
    private ArrayList<CoordinatesModel> points;

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public void setPoints(ArrayList<LatLng> newPoints) {
        this.points = new ArrayList<>();

        for (LatLng latLng : newPoints) {
            points.add(new CoordinatesModel(
                    latLng.longitude,
                    latLng.latitude
            ));
        }
    }
}
