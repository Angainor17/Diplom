package com.example.angai.diplom.notification.business;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.home.business.IHomeInteractor;
import com.example.angai.diplom.notification.data.INotificationRepository;
import com.example.angai.diplom.transport.business.ITransportInteractor;
import com.example.angai.diplom.transport.business.Route;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;

public class NotificationInteractor implements INotificationInteractor {

    @Inject
    INotificationRepository notificationRepository;

    @Inject
    IHomeInteractor homeInteractor;

    @Inject
    ITransportInteractor transportInteractor;

    public NotificationInteractor() {
        App.getInjector().getNotificationComponent().inject(this);
    }

    @Override
    public void saveNotifications(ArrayList<RouteNotification> notifications) {
        notificationRepository.saveNotifications(notifications);
    }

    @Override
    public ArrayList<RouteNotification> getNotifications() {
        return notificationRepository.getNotifications();
    }

    @Override
    public Single<Route[]> getAllRoutes() {
        return transportInteractor.getAllRoutes();
    }

    @Override
    public Single<BusStop[]> getAllBusStops() {
        return homeInteractor.getBusStops();
    }
}
