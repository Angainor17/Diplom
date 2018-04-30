package com.example.angai.diplom.notification.business;

import com.example.angai.diplom.app.App;

public class NotificationInteractor implements INotificationInteractor {

    public NotificationInteractor() {
        App.getInjector().getNotificationComponent().inject(this);
    }
}
