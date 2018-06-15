package com.diplom.angai.diplom.notification.data;

import com.diplom.angai.diplom.notification.business.RouteNotification;
import com.diplom.angai.diplom.utils.dataManager.SharedPreferencesHelper;

import java.util.ArrayList;

public class NotificationRepository implements INotificationRepository {

    private SharedPreferencesHelper sharedPreferences;

    public NotificationRepository() {
        sharedPreferences = new SharedPreferencesHelper();
    }

    @Override
    public void saveNotifications(ArrayList<RouteNotification> notifications) {
        sharedPreferences.saveNotifications(notifications);
    }

    @Override
    public ArrayList<RouteNotification> getNotifications() {
        return sharedPreferences.getNotifications();
    }
}
