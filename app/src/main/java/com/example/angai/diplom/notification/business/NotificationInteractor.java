package com.example.angai.diplom.notification.business;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.notification.data.INotificationRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class NotificationInteractor implements INotificationInteractor {

    @Inject
    INotificationRepository notificationRepository;

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
}
