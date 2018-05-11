package com.example.angai.diplom.home.business;

import android.location.Location;

import com.example.angai.diplom.home.data.BusStopApiModel;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class BusStop implements Serializable {

    private String id;
    private String name;
    private Location location;

    public BusStop(BusStopApiModel busStopApiModel) {
        this.name = busStopApiModel.getName();
        this.id = busStopApiModel.getId();
        this.location = busStopApiModel.getLocation();
    }

    public String getName() {
        return name;
    }

    public static String[] getStrings(BusStop[] busStops) {
        String[] strings = new String[busStops.length];
        for (int i = 0; i < busStops.length; i++) {
            strings[i] = busStops[i].getName();
        }
        return strings;
    }

    public LatLng getLatLng() {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public float getDistanceTo(Location location) {
        return getLocation().distanceTo(location);
    }

    public Location getLocation() {
        return location;
    }

    public static BusStop[] getArray(BusStopApiModel[] busStopsApiModels) {
        BusStop[] busStops = new BusStop[busStopsApiModels.length];
        for (int i = 0; i < busStopsApiModels.length; i++) {
            busStops[i] = new BusStop(busStopsApiModels[i]);
        }
        return busStops;
    }

    public static String[] toStringArray(BusStop[] busStops) {
        String[] strings = new String[busStops.length];
        for (int i = 0; i < busStops.length; i++) {
            strings[i] = busStops[i].getName();
        }

        return strings;
    }
}
