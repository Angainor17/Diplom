package com.example.angai.diplom.transport.data;

import com.example.angai.diplom.home.data.BusStopApiModel;
import com.google.gson.annotations.SerializedName;

public class BusRouteApiModel {

    @SerializedName("id")
    private int id;

    @SerializedName("position")
    private int position;

    @SerializedName("busStop")
    private BusStopApiModel busStop;

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public BusStopApiModel getBusStop() {
        return busStop;
    }
}
