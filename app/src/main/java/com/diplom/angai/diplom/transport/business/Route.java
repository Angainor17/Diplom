package com.diplom.angai.diplom.transport.business;

import com.diplom.angai.diplom.transport.data.RouteApiModel;

public class Route {

    private int id;
    private String name;
    private TransportType[] transportTypes;

    public Route(RouteApiModel routeApiModel) {
        this.id = routeApiModel.getId();
        this.name = routeApiModel.getName();
        this.transportTypes = TransportType.getTransportTypes(routeApiModel.getTransportType());
    }

    public TransportType[] getTransportTypes() {
        return transportTypes;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static String[] toStringArray(Route[] routes) {
        String[] strings = new String[routes.length];
        for (int i = 0; i < routes.length; i++) {
            strings[i] = routes[i].getName();
        }

        return strings;
    }
}
