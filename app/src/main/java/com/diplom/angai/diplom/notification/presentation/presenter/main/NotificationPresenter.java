package com.diplom.angai.diplom.notification.presentation.presenter.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diplom.angai.diplom.app.App;
import com.diplom.angai.diplom.notification.business.INotificationInteractor;
import com.diplom.angai.diplom.notification.business.RouteNotification;
import com.diplom.angai.diplom.notification.presentation.view.detail.RouteNotificationDetailParams;
import com.diplom.angai.diplom.notification.presentation.view.main.INotificationView;
import com.diplom.angai.diplom.router.business.detailScreen.RouteNotificationDetailScreen;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class NotificationPresenter extends MvpBasePresenter<INotificationView> implements INotificationPresenter {

    @Inject
    INotificationInteractor interactor;

    private ArrayList<RouteNotification> notifications;

    public NotificationPresenter() {
        App.getInjector().getNotificationComponent().inject(this);
    }

    @Override
    public void afterUiInit() {
        initNotificationsList();
    }

    @Override
    public void addNotificationClick() {
        if (isViewAttached()) {
            Bundle bundle = new Bundle();

            bundle.putString(
                    "param",
                    new Gson().toJson(new RouteNotificationDetailParams(true))
            );

            getView().getRouter().showDetailScreen(
                    new RouteNotificationDetailScreen(),
                    bundle,
                    (requestCode, resultCode, data) -> {
                        RouteNotification notification = getRouteNotificationFromResult(data);
                        if (notification != null) {
                            notifications.add(notification);
                            refreshItemsList();
                        }
                    }
            );
        }
    }

    @Override
    public void onItemSelected(RouteNotification item) {
        if (isViewAttached()) {
            Bundle bundle = new Bundle();
            RouteNotificationDetailParams params = new RouteNotificationDetailParams(item);
            params.setEditing(true);
            bundle.putString("param", new Gson().toJson(params));

            getView().getRouter().showDetailScreen(
                    new RouteNotificationDetailScreen(),
                    bundle,
                    (requestCode, resultCode, data) -> {
                        RouteNotification notification = getRouteNotificationFromResult(data);
                        updateNotification(notification);
                        refreshItemsList();
                    }
            );
        }
    }

    @Nullable
    private RouteNotification getRouteNotificationFromResult(Intent data) {
        Bundle resultBundle = data.getExtras();
        if (resultBundle == null || !resultBundle.containsKey("result")) {
            return null;
        }

        return new Gson().fromJson(
                resultBundle.getString("result"),
                RouteNotification.class
        );
    }

    private void initNotificationsList() {
        if (isViewAttached()) {
            notifications = interactor.getNotifications();
            getView().setNotificationItems(notifications);
        }
    }

    private void refreshItemsList() {
        if (isViewAttached()) {
            getView().refreshItems();
        }
        interactor.saveNotifications(notifications);
    }

    private void updateNotification(RouteNotification updatedNotification) {
        for (int i = 0; i < notifications.size(); i++) {
            if (updatedNotification.getId() == notifications.get(i).getId()) {
                if (updatedNotification.isRemoved()) {
                    notifications.remove(i);
                    return;
                }

                notifications.get(i).update(updatedNotification);
                return;
            }
        }
    }
}
