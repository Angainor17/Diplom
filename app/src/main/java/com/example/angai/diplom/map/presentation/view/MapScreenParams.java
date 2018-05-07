package com.example.angai.diplom.map.presentation.view;

import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.map.business.RouteDirection;

import java.io.Serializable;

public class MapScreenParams implements Serializable {

    private RouteDirection routeDirection;
    private boolean needToShowRoute;

    public MapScreenParams(RouteDirection routeDirection) {
        this.routeDirection = routeDirection;
    }

    public MapScreenParams(BusStop busStopStart, BusStop busStopStop) {
        routeDirection = new RouteDirection(busStopStart, busStopStop);
    }

    public boolean isNeedToShowRoute() {
        return needToShowRoute && routeDirection.hasRoute();
    }

    public void setNeedToShowRoute(boolean needToShowRoute) {
        this.needToShowRoute = needToShowRoute;
    }

    public RouteDirection getRouteDirection() {
        return routeDirection;
    }
}
