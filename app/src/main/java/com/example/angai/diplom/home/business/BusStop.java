package com.example.angai.diplom.home.business;

import com.example.angai.diplom.home.data.BusStopApiModel;

public class BusStop {

    private String id;
    private String name;
    private Coordinate coordinate;

    public BusStop(String name) {
        this.name = name;
    }

    public BusStop(BusStopApiModel busStopApiModel) {
        this.name = busStopApiModel.getName();
        this.id = busStopApiModel.getId();
        this.coordinate = new Coordinate(busStopApiModel.getCoordinate());
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

    public static BusStop[] getArray(BusStopApiModel[] busStopsApiModels) {
        BusStop[] busStops = new BusStop[busStopsApiModels.length];
        for (int i = 0; i < busStopsApiModels.length; i++) {
            busStops[i] = new BusStop(busStopsApiModels[i]);
        }
        return busStops;
    }
}
