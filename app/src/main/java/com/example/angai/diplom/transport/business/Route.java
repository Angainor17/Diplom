package com.example.angai.diplom.transport.business;

import com.example.angai.diplom.transport.data.RouteApiModel;

public class Route {

    private String name;
    private TransportType[] transportTypes;

    public Route(RouteApiModel routeApiModel) {
        this.name = routeApiModel.getName();
        this.transportTypes = TransportType.getTransportTypes(routeApiModel.getTransportType());
    }

    public TransportType[] getTransportTypes() {
        return transportTypes;
    }

    public String getName() {
        return name;
    }
}
