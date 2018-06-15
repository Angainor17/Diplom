package com.diplom.angai.diplom.home.data;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

public class BusStopApiModel {

    @SerializedName("id")
    private String id;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public Location getLocation() {
        Location location = new Location("");
        if (latitude == null || longitude == null) {
            return location;
        }

        location.setLongitude(Double.valueOf(longitude));
        location.setLatitude(Double.valueOf(latitude));

        return location;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getName() {
        return name;
    }
}
