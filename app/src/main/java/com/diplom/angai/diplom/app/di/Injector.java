package com.diplom.angai.diplom.app.di;

import android.content.Context;

import com.diplom.angai.diplom.home.di.DaggerHomeComponent;
import com.diplom.angai.diplom.home.di.HomeComponent;
import com.diplom.angai.diplom.home.di.HomeModule;
import com.diplom.angai.diplom.map.di.DaggerMapComponent;
import com.diplom.angai.diplom.map.di.MapComponent;
import com.diplom.angai.diplom.map.di.MapModule;
import com.diplom.angai.diplom.notification.di.DaggerNotificationComponent;
import com.diplom.angai.diplom.notification.di.NotificationComponent;
import com.diplom.angai.diplom.notification.di.NotificationModule;
import com.diplom.angai.diplom.router.di.DaggerRouterComponent;
import com.diplom.angai.diplom.router.di.RouterComponent;
import com.diplom.angai.diplom.router.di.RouterModule;
import com.diplom.angai.diplom.transport.di.DaggerTransportComponent;
import com.diplom.angai.diplom.transport.di.TransportComponent;
import com.diplom.angai.diplom.transport.di.TransportModule;

public class Injector {

    private AppComponent appComponent;
    private HomeComponent homeComponent;
    private RouterComponent routerComponent;
    private NotificationComponent notificationComponent;
    private MapComponent mapComponent;
    private TransportComponent transportComponent;

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

    public TransportComponent getTransportComponent() {
        if (transportComponent == null) {
            transportComponent = initTransportComponent();
        }
        return transportComponent;
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

    private TransportComponent initTransportComponent() {
        return DaggerTransportComponent
                .builder()
                .appComponent(getAppComponent())
                .transportModule(new TransportModule())
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
