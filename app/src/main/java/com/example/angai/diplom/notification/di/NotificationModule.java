package com.example.angai.diplom.notification.di;

import com.example.angai.diplom.home.business.HomeInteractor;
import com.example.angai.diplom.home.business.IHomeInteractor;
import com.example.angai.diplom.notification.business.INotificationInteractor;
import com.example.angai.diplom.notification.business.NotificationInteractor;
import com.example.angai.diplom.notification.data.INotificationRepository;
import com.example.angai.diplom.notification.data.NotificationRepository;
import com.example.angai.diplom.notification.presentation.presenter.detail.IRouteNotificationDetailPresenter;
import com.example.angai.diplom.notification.presentation.presenter.detail.RouteNotificationDetailPresenter;
import com.example.angai.diplom.notification.presentation.presenter.main.INotificationPresenter;
import com.example.angai.diplom.notification.presentation.presenter.main.NotificationPresenter;
import com.example.angai.diplom.transport.business.ITransportInteractor;
import com.example.angai.diplom.transport.business.TransportInteractor;

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

    @Provides
    public IRouteNotificationDetailPresenter getNotificationDetailPresenter() {
        return new RouteNotificationDetailPresenter();
    }

    @Provides
    IHomeInteractor homeInteractor() {
        return new HomeInteractor();
    }

    @Provides
    ITransportInteractor transportInteractor() {
        return new TransportInteractor();
    }
}
