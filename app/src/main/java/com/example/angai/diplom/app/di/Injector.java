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

    private AppComponent appComponent;
    private HomeComponent homeComponent;
    private RouterComponent routerComponent;
    private NotificationComponent notificationComponent;
    private MapComponent mapComponent;

    private Context context;

    public Injector(Context context) {
        this.context = context;
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = initAppComponent();
        }
        return appComponent;
    }

    public HomeComponent getHomeComponent() {
        if (homeComponent == null) {
            homeComponent = initHomeComponent();
        }
        return homeComponent;
    }

    public RouterComponent getRouterComponent() {
        if (routerComponent == null) {
            routerComponent = initRouterComponent();
        }
        return routerComponent;
    }

    public NotificationComponent getNotificationComponent() {
        if (notificationComponent == null) {
            notificationComponent = initNotificationComponent();
        }
        return notificationComponent;
    }

    public MapComponent getMapComponent() {
        if (mapComponent == null) {
            mapComponent = initMapComponent();
        }
        return mapComponent;
    }

    private NotificationComponent initNotificationComponent() {
        return DaggerNotificationComponent
                .builder()
                .appComponent(getAppComponent())
                .notificationModule(new NotificationModule())
                .build();
    }

    private MapComponent initMapComponent() {
        return DaggerMapComponent
                .builder()
                .appComponent(getAppComponent())
                .mapModule(new MapModule())
                .build();
    }

    private AppComponent initAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(context))
                .build();
    }

    private RouterComponent initRouterComponent() {
        return DaggerRouterComponent
                .builder()
                .appComponent(getAppComponent())
                .routerModule(new RouterModule())
                .build();
    }

    private HomeComponent initHomeComponent() {
        return DaggerHomeComponent
                .builder()
                .homeModule(new HomeModule())
                .appComponent(getAppComponent())
                .build();
    }
}
