package com.example.angai.diplom.notification.presentation.view.detail;

import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.transport.business.Route;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface IRouteNotificationDetailView extends MvpView {

    void setActivityResult(RouteNotification result);

    void initView(RouteNotification routeNotification);

    void initBusStopSpinner(BusStop[] busStops);

    void initRouteSpinner(Route[] routes);

    String getSelectedRoute();

    String getSelectedBusStop();

    RouteNotification.Time getTimeWhen();

    RouteNotification.Time getTimeBefore();

    void backButtonClick();
}
