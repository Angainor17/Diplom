package com.example.angai.diplom.notification.presentation.presenter.main;

import android.os.Bundle;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.notification.business.INotificationInteractor;
import com.example.angai.diplom.notification.business.RouteNotification;
import com.example.angai.diplom.notification.presentation.view.detail.RouteNotificationDetailParams;
import com.example.angai.diplom.notification.presentation.view.main.INotificationView;
import com.example.angai.diplom.router.business.detailScreen.RouteNotificationDetailScreen;
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

            getView().getRouter().showDetailScreen(new RouteNotificationDetailScreen(), bundle);
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
                    new RouteNotificationDetailScreen(), bundle
            );
        }
    }

    private void initNotificationsList() {
        if (isViewAttached()) {
            notifications = interactor.getNotifications();
            getView().setNotificationItems(notifications);
        }
    }
}
