package com.example.angai.diplom.notification.presentation.presenter.detail;

import com.example.angai.diplom.app.App;
import com.example.angai.diplom.notification.business.INotificationInteractor;
import com.example.angai.diplom.notification.presentation.view.detail.IRouteNotificationDetailView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

public class RouteNotificationDetailPresenter extends MvpBasePresenter<IRouteNotificationDetailView> implements IRouteNotificationDetailPresenter {

    @Inject
    INotificationInteractor notificationInteractor;

    public RouteNotificationDetailPresenter() {
        App.getInjector().getNotificationComponent().inject(this);
    }

}
