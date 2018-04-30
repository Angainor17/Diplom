package com.example.angai.diplom.notification.di;

import com.example.angai.diplom.notification.business.INotificationInteractor;
import com.example.angai.diplom.notification.business.NotificationInteractor;
import com.example.angai.diplom.notification.data.INotificationRepository;
import com.example.angai.diplom.notification.data.NotificationRepository;
import com.example.angai.diplom.notification.presentation.presenter.INotificationPresenter;
import com.example.angai.diplom.notification.presentation.presenter.NotificationPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationModule {

    @Provides
    INotificationPresenter getNotificationPresenter() {
        return new NotificationPresenter();
    }

    @Provides
    INotificationInteractor getNotificationInteractor() {
        return new NotificationInteractor();
    }

    @Provides
    INotificationRepository getNotificationRepository() {
        return new NotificationRepository();
    }
}
