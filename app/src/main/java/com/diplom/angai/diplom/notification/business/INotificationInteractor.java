package com.diplom.angai.diplom.notification.business;

import com.diplom.angai.diplom.home.business.BusStop;
import com.diplom.angai.diplom.transport.business.Route;

import java.util.ArrayList;

import io.reactivex.Single;

public interface INotificationInteractor {

    void saveNotifications(ArrayList<RouteNotification> notifications);

    ArrayList<RouteNotification> getNotifications();

    Single<Route[]> getAllRoutes();

    Single<BusStop[]> getAllBusStops();

}
