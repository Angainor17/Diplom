package com.example.angai.diplom.home.data;

import com.google.gson.annotations.SerializedName;

public class BusStopApiModel {

    @SerializedName("id")
    private String id;

    @SerializedName("coordinateX")
    private String coordinateX;

    @SerializedName("coordinateY")
    private String coordinateY;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public CoordinateApiModel getCoordinate() {
        return new CoordinateApiModel(coordinateX, coordinateY);
    }

    public String getName() {
        return name;
    }
}
