package com.diplom.angai.diplom.directions.models;

import com.google.android.gms.maps.model.LatLng;

public class LatLngApiModel {

    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public LatLng getLatLng() {
        return new LatLng(
                Double.parseDouble(getLatitude()),
                Double.parseDouble(getLongitude())
        );
    }
}
