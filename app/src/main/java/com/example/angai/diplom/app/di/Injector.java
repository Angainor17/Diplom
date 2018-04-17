package com.example.angai.diplom.app.di;

import android.content.Context;

import com.example.angai.diplom.home.di.DaggerHomeComponent;
import com.example.angai.diplom.home.di.HomeComponent;
import com.example.angai.diplom.home.di.HomeModule;
import com.example.angai.diplom.map.di.DaggerMapComponent;
import com.example.angai.diplom.map.di.MapComponent;
import com.example.angai.diplom.map.di.MapModule;
import com.example.angai.diplom.notification.di.DaggerNotificationComponent;
import com.example.angai.diplom.notification.di.NotificationComponent;
import com.example.angai.diplom.notification.di.NotificationModule;
import com.example.angai.diplom.router.di.DaggerRouterComponent;
import com.example.angai.diplom.router.di.RouterComponent;
import com.example.angai.diplom.router.di.RouterModule;

public class Injector {

    private static Context context;

    private AppComponent appComponent;
    private HomeComponent homeComponent;
    private RouterComponent routerComponent;
    private NotificationComponent notificationComponent;
    private MapComponent mapComponent;

    public Injector(Context context) {
        Injector.context = context;

        initAppComponent();
        initHomeComponent();
        initRouterComponent();
        initNotificationComponent();
        initMapComponent();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public HomeComponent getHomeComponent() {
        return homeComponent;
    }

    public RouterComponent getRouterComponent() {
        return routerComponent;
    }

    public NotificationComponent getNotificationComponent() {
        return notificationComponent;
    }

    public MapComponent getMapComponent() {
        return mapComponent;
    }

    private void initNotificationComponent() {
        notificationComponent = DaggerNotificationComponent
                .builder()
                .appComponent(appComponent)
                .notificationModule(new NotificationModule())
                .build();
    }

    private void initMapComponent() {
        mapComponent = DaggerMapComponent
                .builder()
                .appComponent(appComponent)
                .mapModule(new MapModule())
                .build();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(context))
                .build();
    }

    private void initRouterComponent() {
        routerComponent = DaggerRouterComponent
                .builder()
                .appComponent(appComponent)
                .routerModule(new RouterModule())
                .build();
    }

    private void initHomeComponent() {
        homeComponent = DaggerHomeComponent
                .builder()
                .homeModule(new HomeModule())
                .appComponent(appComponent)
                .build();
    }
}
