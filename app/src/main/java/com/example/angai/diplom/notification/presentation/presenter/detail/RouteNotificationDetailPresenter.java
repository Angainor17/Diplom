package com.example.angai.diplom.notification.presentation.presenter.detail;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.home.business.BusStop;
import com.example.angai.diplom.notification.business.INotificationInteractor;
import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.notification.presentation.view.detail.IRouteNotificationDetailView;
import com.example.angai.diplom.notification.presentation.view.detail.RouteNotificationDetailParams;
import com.example.angai.diplom.transport.business.Route;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RouteNotificationDetailPresenter extends MvpBasePresenter<IRouteNotificationDetailView> implements IRouteNotificationDetailPresenter {

    @Inject
    INotificationInteractor notificationInteractor;

    private RouteNotificationDetailParams params;
    private RouteNotification routeNotification;

    private boolean isNotificationRemoved;
    private boolean isNotificationChecked;

    private BusStop[] busStops;
    private Route[] routes;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RouteNotificationDetailPresenter() {
        App.getInjector().getNotificationComponent().inject(this);
    }

    @Override
    public void setParams(RouteNotificationDetailParams params) {
        this.params = params;
        initRouteNotification();
        if (isViewAttached()) {
            initSpinners();
            getView().initView(routeNotification);
        }
    }

    private void initSpinners() {
        compositeDisposable.add(notificationInteractor.getAllBusStops()
                .subscribe(busStops -> {
                    this.busStops = busStops;
                    if (isViewAttached()) {
                        getView().initBusStopSpinner(busStops);
                    }
                }, throwable -> {

                }));

        compositeDisposable.add(notificationInteractor.getAllRoutes().subscribe(routes -> {
            this.routes = routes;
            if (isViewAttached()) {
                getView().initRouteSpinner(routes);
            }
        }, throwable -> {

        }));
    }

    @Override
    public void backBtnClick() {
        if (isViewAttached()) {
            getView().setActivityResult(routeNotification);
        }
    }

    @Override
    public void applyClick() {
        if (isViewAttached()) {
            updateRouteNotificationFromView();
            getView().backButtonClick();
        }
    }

    @Override
    public boolean notificationCheckedBtnClick() {
        if (isNotificationChecked) {
            isNotificationChecked = false;
        } else {
            isNotificationChecked = true;
        }

        return isNotificationChecked;
    }

    @Override
    public boolean notificationRemovedBtnClick() {
        if (isNotificationRemoved) {
            isNotificationRemoved = false;
        } else {
            isNotificationRemoved = true;
        }

        return isNotificationRemoved;
    }

    @Override
    public void onViewDestroyed() {
        compositeDisposable.clear();
    }

    private void updateRouteNotificationFromView() {
        if (isNotificationRemoved) {
            routeNotification.setRemoved(true);
            return;
        }

        if (!isViewAttached()) {
            return;
        }

        routeNotification.setActive(isNotificationChecked);
        routeNotification.setBusStop(getBusStopFromString());
        routeNotification.setRoute(getRouteFromString());
        routeNotification.setTimeBefore(getView().getTimeBefore());
        routeNotification.setTimeWhen(getView().getTimeWhen());
    }

    private void initRouteNotification() {
        if (params.isEditing()) {
            routeNotification = params.getRouteNotification();
            isNotificationChecked = routeNotification.isChecked();
            return;
        }

        if (params.isNewOne()) {
            routeNotification = new RouteNotification();
            isNotificationChecked = true;
            return;
        }
    }

    private BusStop getBusStopFromString() {
        if (isViewAttached()) {
            String busStopString = getView().getSelectedBusStop();
            for (BusStop busStop : busStops) {
                if (busStop.getName().equals(busStopString)) {
                    return busStop;
                }
            }
        }
        return null;
    }

    private Route getRouteFromString() {
        if (isViewAttached()) {
            String routeString = getView().getSelectedRoute();
            for (Route route : routes) {
                if (route.getName().equals(routeString)) {
                    return route;
                }
            }
        }
        return null;
    }
}
