package com.diplom.angai.diplom.notification.di;

import com.diplom.angai.diplom.home.business.HomeInteractor;
import com.diplom.angai.diplom.home.business.IHomeInteractor;
import com.diplom.angai.diplom.notification.business.INotificationInteractor;
import com.diplom.angai.diplom.notification.business.NotificationInteractor;
import com.diplom.angai.diplom.notification.data.INotificationRepository;
import com.diplom.angai.diplom.notification.data.NotificationRepository;
import com.diplom.angai.diplom.notification.presentation.presenter.detail.IRouteNotificationDetailPresenter;
import com.diplom.angai.diplom.notification.presentation.presenter.detail.RouteNotificationDetailPresenter;
import com.diplom.angai.diplom.notification.presentation.presenter.main.INotificationPresenter;
import com.diplom.angai.diplom.notification.presentation.presenter.main.NotificationPresenter;
import com.diplom.angai.diplom.transport.business.ITransportInteractor;
import com.diplom.angai.diplom.transport.business.TransportInteractor;

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
