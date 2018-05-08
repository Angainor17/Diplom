package com.example.angai.diplom.notification.business;

import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.transport.business.Route;

public class RouteNotification {

    private String time;
    private boolean isActive;
    private BusStop busStop;
    private Route route;

    public boolean isCheched() {
        return isActive;
    }

    public String getTime() {
        return time;
    }

    public String getRouteName() {
        return route.getName();
    }

    public String getBusStopName() {
        return busStop.getName();
    }

    public String getRemainingTime() {
        return "Осталось 17 мин";// TODO: 08.05.2018
    }
}
