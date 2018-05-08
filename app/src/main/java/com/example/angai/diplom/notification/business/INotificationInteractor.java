package com.example.angai.diplom.notification.business;

import java.util.ArrayList;

public interface INotificationInteractor {

    void saveNotifications(ArrayList<RouteNotification> notifications);

    ArrayList<RouteNotification> getNotifications();

}
