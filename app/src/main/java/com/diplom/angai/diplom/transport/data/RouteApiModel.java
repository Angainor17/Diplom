package com.diplom.angai.diplom.transport.data;

import com.google.gson.annotations.SerializedName;

public class RouteApiModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("transportType")
    private int transportType;

    public int getTransportType() {
        return transportType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
