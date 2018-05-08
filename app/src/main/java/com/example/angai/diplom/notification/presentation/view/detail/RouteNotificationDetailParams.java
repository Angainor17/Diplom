package com.example.angai.diplom.notification.presentation.view.detail;

import com.example.angai.diplom.notification.business.RouteNotification;

public class RouteNotificationDetailParams {

    private RouteNotification routeNotification;
    private boolean isEditing = false;
    private boolean isNewOne = false;

    public RouteNotificationDetailParams(RouteNotification routeNotification) {
        this.routeNotification = routeNotification;
    }

    public RouteNotificationDetailParams(boolean isNewOne) {
        this.isNewOne = isNewOne;
    }

    public RouteNotification getRouteNotification() {
        return routeNotification;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public boolean isNewOne() {
        return isNewOne;
    }

    public void setNewOne(boolean newOne) {
        isNewOne = newOne;
    }
}
