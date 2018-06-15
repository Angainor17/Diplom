package com.diplom.angai.diplom.map.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MapPointResponse {

    @SerializedName("locations")
    private ArrayList<MapPointApiModel> locations;

    public ArrayList<MapPointApiModel> getLocations() {
        return locations;
    }

    public MapPointResponse() {
        this.locations = new ArrayList<>();
    }
}
