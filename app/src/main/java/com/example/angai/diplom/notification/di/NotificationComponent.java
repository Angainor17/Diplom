package com.example.angai.diplom.notification.di;

import com.example.angai.diplom.app.di.AppComponent;
import com.example.angai.diplom.notification.business.NotificationInteractor;
import com.example.angai.diplom.notification.presentation.presenter.NotificationPresenter;
import com.example.angai.diplom.notification.presentation.view.NotificationFragment;

import dagger.Component;

@NotificationScope
@Component(dependencies = AppComponent.class, modules = NotificationModule.class)
public interface NotificationComponent {

    void inject(NotificationFragment notificationFragment);

    void inject(NotificationPresenter notificationPresenter);

    void inject(NotificationInteractor notificationInteractor);
}
