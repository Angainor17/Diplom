package com.diplom.angai.diplom.notification.di;

import com.diplom.angai.diplom.app.di.AppComponent;
import com.diplom.angai.diplom.notification.business.NotificationInteractor;
import com.diplom.angai.diplom.notification.data.NotificationRepository;
import com.diplom.angai.diplom.notification.presentation.presenter.detail.RouteNotificationDetailPresenter;
import com.diplom.angai.diplom.notification.presentation.presenter.main.NotificationPresenter;
import com.diplom.angai.diplom.notification.presentation.view.detail.RouteNotificationDetailView;
import com.diplom.angai.diplom.notification.presentation.view.main.NotificationFragment;

import dagger.Component;

@NotificationScope
@Component(dependencies = AppComponent.class, modules = NotificationModule.class)
public interface NotificationComponent {

    void inject(NotificationFragment notificationFragment);

    void inject(NotificationPresenter notificationPresenter);

    void inject(NotificationInteractor notificationInteractor);

    void inject(NotificationRepository notificationRepository);

    void inject(RouteNotificationDetailPresenter routeNotificationDetailPresenter);

    void inject(RouteNotificationDetailView routeNotificationDetailView);
}
