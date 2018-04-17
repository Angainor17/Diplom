package com.example.angai.diplom.notification.di;

import com.example.angai.diplom.app.di.AppComponent;

import dagger.Component;

@NotificationScope
@Component(dependencies = AppComponent.class, modules = NotificationModule.class)
public interface NotificationComponent {
}
