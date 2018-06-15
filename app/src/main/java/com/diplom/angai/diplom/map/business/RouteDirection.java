package com.diplom.angai.diplom.map.business;

import android.location.Location;

import com.diplom.angai.diplom.home.business.BusStop;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

public class RouteDirection implements Serializable {

    private ArrayList<LatLng> directions;
    private BusStop busStopStart;
    private BusStop busStopStop;

    public RouteDirection(BusStop busStopStart, BusStop busStopStop) {
        this.busStopStart = busStopStart;
        this.busStopStop = busStopStop;
    }

    public ArrayList<LatLng> getDirections() {
        return directions == null ? new ArrayList<>() : directions;
    }

    public boolean hasDirections() {
        return directions != null && !directions.isEmpty();
    }

    public void setDirections(ArrayList<LatLng> directions) {
        this.directions = directions;
    }

    public boolean hasRoute() {
        return busStopStart != null && busStopStop != null;
    }

    public Location getStartLocation() {
        return busStopStart.getLocation();
    }

    public Location getStopLocation() {
        return busStopStop.getLocation();
    }

    public BusStop getBusStopStart() {
        return busStopStart;
    }

    public BusStop getBusStopStop() {
        return busStopStop;
    }
}
