package com.example.angai.diplom.notification.data;

import com.example.angai.diplom.notification.business.RouteNotification;

import java.util.ArrayList;

public interface INotificationRepository {

    void saveNotifications(ArrayList<RouteNotification> notifications);

    ArrayList<RouteNotification> getNotifications();

}
