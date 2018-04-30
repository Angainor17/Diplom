package com.example.angai.diplom.transport.business;

import com.example.angai.diplom.transport.data.RouteApiModel;

public class Route {

    private String name;

    public Route(RouteApiModel routeApiModel) {
        this.name = routeApiModel.getName();
    }

    public String getName() {
        return name;
    }
}
