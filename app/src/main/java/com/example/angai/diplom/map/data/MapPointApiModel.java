package com.example.angai.diplom.map.data;

import com.example.angai.diplom.transport.data.RouteApiModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MapPointApiModel {

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("userId")
    private String userId;

    @SerializedName("routes")
    private ArrayList<RouteApiModel> routes;

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<RouteApiModel> getRoutes() {
        return routes;
    }
}
