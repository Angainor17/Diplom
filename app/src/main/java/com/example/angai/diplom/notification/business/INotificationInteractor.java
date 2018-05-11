package com.example.angai.diplom.notification.business;

import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.transport.business.Route;

import java.util.ArrayList;

import io.reactivex.Single;

public interface INotificationInteractor {

    void saveNotifications(ArrayList<RouteNotification> notifications);

    ArrayList<RouteNotification> getNotifications();

    Single<Route[]> getAllRoutes();

    Single<BusStop[]> getAllBusStops();

}
